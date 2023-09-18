import logging as log
import threading
import subprocess
import os
import numpy as np

from util.signal import Signal
from util.cutter import Cutter
from model_util.runner import Runner


class PerformanceRunner(Runner):
    """
    PerformanceRunner runs a java program which runs performance measurements in a JAR-file.
    """
    def __init__(self):
        super().__init__()

        self.__status_updated = Signal('status_updated')
        self.__tests_to_run = []
        self.__current_running = None
        self.__remaining_to_run = []
        self.__status = '-'
        self.__jar_path = None
        self.__total_iterations = 0
        self.__iterations_done = 0

    @property
    def status_updated(self):
        """
        Get signal and connect to it to receive notifications when status of runner changed.
        :return: signal
        :rtype: Signal
        """
        return self.__status_updated

    # noinspection PyMethodOverriding
    def start(self, start, stop, num, iterations, *, jar_path):
        """
        Start running performance measurements.
        :param start: start model size
        :type start: int
        :param stop: end model size
        :type stop: int
        :param num: number of measurements
        :type num: int
        :param iterations: number of iterations per measurement
        :type iterations: int
        :param jar_path: path to JAR file
        :type jar_path: str
        """
        with self._mutex:
            self.stop(notify=True)

            self.__jar_path = jar_path
            if len(self.__tests_to_run) == 0:
                log.getLogger(__name__).warning('No tests to run specified.')
                return

            self.__remaining_to_run = self.__tests_to_run.copy()
            for entry in self.__remaining_to_run:
                sizes = []
                one = np.linspace(start=start, stop=stop, num=num).tolist()
                # one = [10, 100, 1000, 10000, 100000]
                for i in range(len(one)):
                    sizes += iterations * one[i:i+1]
                num = len(one)
                entry['sizes'] = sizes

            self.__iterations_done = 0
            self.__total_iterations = len(self.__remaining_to_run) * num * iterations
            self.__start_next()

    def stop(self, notify=True):
        """
        Overrides method in super class Runner. Stop running performance measurements.
        :param notify: True: signal will be emitted to notify end of running measurements
        :type notify: bool
        """
        self.__status = '-'
        self.status_updated.emit(self.__status)
        self.__remaining_to_run = []
        super().stop(notify)


    def add_test_to_run(self, suite, test, tool, project):
        """
        Add a performance test which should be run.
        :param suite: qualified name of class which contains tests
        :type suite: str
        :param test: test name
        :type test: str
        :param tool: tool name
        :type tool: str
        :param project: path to project of class containing this test
        :type project: str
        """
        entry = {'class': suite,
                 'test': test,
                 'tool': tool,
                 'project': project}
        self.__tests_to_run.append(entry)

    def reset_tests_to_run(self):
        """
        Reset saved tests to run.
        """
        self.__tests_to_run = []

    def skip(self):
        """
        Skip current measurement and start next (if available).
        """
        with self._mutex:
            if self.__current_running in self.__remaining_to_run: self.__remaining_to_run.remove(self.__current_running)
            self.__iterations_done += len(self.__current_running['sizes']) + 1
            remaining = self.__remaining_to_run
            notify = len(remaining) == 0
            self.stop(notify=notify)
            self.__remaining_to_run = remaining
            self.__start_next()

    @property
    def progress(self):
        """
        Get progress of measurements.
        :return: number of measurements done, number of measurements total to do
        :rtype: int, int
        """
        with self._mutex:
            return self.__iterations_done, self.__total_iterations

    @property
    def status(self):
        """
        Get status of runner.
        :return: status of runner
        :rtype: str
        """
        with self._mutex:
            return self.__status

    def __start_next(self):
        """
        Start next process.
        """
        with self._mutex:
            if len(self.__remaining_to_run) == 0: return
            to_run = self.__remaining_to_run[0]
            self.__current_running = to_run
            sizes = to_run['sizes']
            size = str(int(sizes.pop(0)))
            if len(sizes) == 0: self.__remaining_to_run.pop(0)
            java_util_path = os.getcwd() + '\\model\\model_util\\JavaUtil.jar'
            command = ['java', '-jar', '-Xmx4000m', java_util_path, 'PerformanceRunner', self.__jar_path] \
                      + [size, to_run['class'], to_run['tool'], to_run['test']]

            self.__status = 'Running {} in {} with {} for size {}'.format(to_run['test'], Cutter.cut_project(to_run['project']),to_run['tool'], size)
            self.status_updated.emit(self.__status)

            try:
                p = subprocess.Popen(command, stdout=subprocess.PIPE,
                                     stderr=subprocess.STDOUT,
                                     universal_newlines=True,
                                     start_new_session=False,
                                     cwd=to_run['project']
                                     )
            except Exception as e:
                log.getLogger(__name__).warning(
                    'An error occurred during start of subprocess.\nCommand used: {}\n'
                    'Maybe check your paths under Settings. Maybe check also if using "java" in cmd is possible on '
                    'your system.'.format(command),
                    stack_info=True, exc_info=e)
                self.__start_next()
                return

            threading.Thread(target=self._forward_output, args=[p], daemon=True).start()
            self._process = p

    def _forward_output(self, p):
        """
        Get output of process and extract results/forward to std out. Overrides super class method because a more
        specific method is needed.
        :param p: process
        """
        result_extracted = False
        while True:
            out = p.stdout.readline()
            if out == '' and p.poll() is not None: break
            if out: result_extracted = result_extracted or self.__extract_results(out)

        return_code = p.poll()
        if return_code != 0: log.getLogger(__name__).debug('Return code of subprocess not 0: {}'.format(return_code))

        with self._mutex:
            self.__status = '-'
            self.status_updated.emit(self.__status)

            if self._process != p: return # false if already stopped or restarted -> end notification would be incorrect

            if len(self.__remaining_to_run) == 0: # last process
                self._process = None
                self.ended.emit()
            elif not result_extracted:
                self.skip()
            else:
                self.__start_next()

    def __extract_results(self, out):
        """
        Extract results from output of process.
        :param out: output of process
        :type out: str
        :return: True: output contained a result. False: output contained no result
        :rtype: bool
        """
        result_identifier = 'Found:'
        if out[:len(result_identifier)] == result_identifier:
            result = out.split()
            num_res = 6
            if len(result) != num_res:
                log.getLogger(__name__).warning('Found result with unknown format: {}'.format(result))
                return

            class_name = result[3]
            tool = result[4]
            test = result[5]

            project = self.__current_running['project']
            idx = project.rfind('\\')
            if idx >= 0: project = project[idx + 1:]

            label = project + " - " + test + " - " + tool
            to_filter = 'Benchmarx'
            if label[0:len(to_filter)] == to_filter: label = label[len(to_filter):]

            result_dict = {'x': float(result[1]), 'y': float(result[2]), 'class': class_name, 'tool': tool, 'test': test, 'label': label}
            self.__iterations_done += 1
            self.new_result.emit(result_dict)
            return True
        else:
            print(out, end='')
        return False

    def _extract_results(self, out):
        """
        Abstract class of super class has to be overridden but is not used in performance runner.
        :param out: output of process
        :type out: str
        """
        log.getLogger(__name__).warning('Function extract results should not be used in performance runner.')

#************************
# Old implementation of performance runner which uses one process for all measurements.
# Use this with revision 91 of repository for compatibility.
#************************
# import logging as log
# import threading
# import subprocess
# import os
#
# from util.cb_manager import CbManager
#
# class PerformanceRunner:
#     def __init__(self):
#         self.__p = []
#         self.__mutex = threading.RLock()
#         self.__cb_manager_new_result = CbManager()
#         self.__cb_manager_ended = CbManager()
#         self.__cb_manager_started = CbManager()
#         self.__remaining_commands = {}
#
#         self.__num = 0
#         self.__start = 0
#         self.__stop = 0
#         self.__remaining_iterations = 0
#         self.__total_iterations = 0
#         self.__tests_to_run = []
#         self.__status = '-'
#         self.__jar_path = None
#
#     def start(self,start, stop, num, total_iterations=None , *, jar_path):
#         self.__jar_path = jar_path
#         if len(self.__tests_to_run) == 0:
#             log.getLogger(__name__).warning('No tests to run specified.')
#             return
#         self.__start = start
#         self.__stop = stop
#         self.__num = num
#         self.__remaining_iterations = num
#         if total_iterations:
#             self.__total_iterations = total_iterations
#         else:
#             self.__total_iterations = num * len(self.__tests_to_run)
#
#         commands = self.__construct_commands(jar_path)
#
#         test_to_run = self.__tests_to_run[0]
#         self.__status = 'running {} with {}'.format(test_to_run['test'], test_to_run['tool'])
#
#         with self.__mutex:
#             self.stop()
#
#             self.__remaining_commands = commands
#
#             num_processes = 1
#             for i in range(num_processes):
#                 project, command = commands.popitem()
#                 process = self.__start_process(project, command)
#                 if not process: continue
#                 self.__p.append(process)
#
#             if self.__p is not None and len(self.__p) > 0:
#                 self.__cb_manager_started.notify()
#             else:
#                 log.getLogger(__name__).warning('No process could be started.')
#
#     def stop(self, notify=True):
#         self.__status = '-'
#
#         try:
#             import psutil  # ToDo: other solution
#         except Exception as e:
#             log.getLogger(__name__).warning(
#                 'Error during importing psutil',
#                 stack_info=True, exc_info=e)
#             return
#
#         with self.__mutex:
#             if not self.is_running(): return
#             for process in self.__p:
#                 try:
#                     for child in psutil.Process(process.pid).children(recursive=True):
#                         child.kill()
#                     process.kill()
#
#                 except Exception as e:
#                     log.getLogger(__name__).warning(
#                         'An error occurred during kill of subprocess:',
#                         stack_info=True, exc_info=e)
#
#             self.__p = []
#             self.__remaining_commands = {}
#             if notify: self.__cb_manager_ended.notify()
#
#     def is_running(self):
#         with self.__mutex:
#             if self.__p:
#                 return True
#             else:
#                 return False
#
#     def add_test_to_run(self, suite, test, tool, project):
#         entry = {'class': suite,
#                  'test': test,
#                  'tool': tool,
#                  'project': project}
#         self.__tests_to_run.append(entry)
#
#     def reset_tests_to_run(self):
#         self.__tests_to_run = []
#
#     def add_cb_new_result(self, cb):
#         self.__cb_manager_new_result.add_cb(cb)
#
#     def add_cb_ended(self, cb):
#         self.__cb_manager_ended.add_cb(cb)
#
#     @property
#     def status(self):
#         if len(self.__tests_to_run) > 0 and self.is_running():
#             test_to_run = self.__tests_to_run[0]
#             return 'Running {} with {}'.format(test_to_run['test'], test_to_run['tool'])
#         else:
#             return '-'
#
#     @property
#     def progress(self):
#         if len(self.__tests_to_run) == 0:
#             return self.__total_iterations, self.__total_iterations
#         else:
#             return self.__total_iterations - (self.__remaining_iterations + self.__num * (len(self.__tests_to_run) - 1)), self.__total_iterations
#
#     def skip(self):
#         if len(self.__tests_to_run) > 1:
#             self.stop(notify=False)
#             self.__tests_to_run = self.__tests_to_run[1:]
#             self.start(self.__start, self.__stop, self.__num, self.__total_iterations, jar_path=self.__jar_path)
#         elif len(self.__tests_to_run) == 1:
#             self.stop(notify=True)
#             self.__tests_to_run = []
#             self.__remaining_iterations = 0
#         else:
#             log.getLogger(__name__).warning('No remaining test to continue after skip.')
#
#     def __forward_output(self, p):
#         while True:
#             out = p.stdout.readline()
#             if out == '' and p.poll() is not None: break
#             if out: self.__extract_results(out)
#
#         return_code = p.poll()
#         if return_code != 0: log.getLogger(__name__).debug('Return code of subprocess not 0: {}'.format(return_code))
#
#         with self.__mutex:
#             process = None
#             for i in range(len(self.__remaining_commands)):
#                 project, command = self.__remaining_commands.popitem()
#                 process = self.__start_process(project, command)
#                 if process:
#                     self.__p.append(process)
#                     break
#
#             if self.__p is not None and len(self.__p) == 1 and self.__p[0] == p:  # last process
#                 self.__p = []
#                 self.__cb_manager_ended.notify()
#
#             if p in self.__p:
#                 self.__p.remove(p)
#
#     def __extract_results(self, out):
#         result_identifier = 'Found:'
#         if out[:len(result_identifier)] == result_identifier:
#             result = out.split()
#             num_res = 6
#             if len(result) != num_res:
#                 log.getLogger(__name__).warning('Found result with unknown format: {}'.format(result))
#                 return
#
#             class_name = result[3]
#             tool = result[4]
#             test = result[5]
#
#             project = self.__tests_to_run[0]['project']
#             idx = project.rfind('\\')
#             if idx >= 0: project = project[idx + 1:]
#
#             label = project + " - " + test + " - " + tool
#             to_filter = 'Benchmarx'
#             if label[0:len(to_filter)] == to_filter: label= label[len(to_filter):]
#
#             result_dict = {'x': float(result[1]), 'y': float(result[2]), 'class': class_name, 'tool': tool, 'test': test, 'label': label}
#             # print("New performance test found: {}".format(result_dict))
#             self.__remaining_iterations -= 1
#             if self.__remaining_iterations == 0:
#                 self.__tests_to_run = self.__tests_to_run[1:]
#                 self.__remaining_iterations = self.__num
#             self.__cb_manager_new_result.notify(result_dict)
#         else:
#             print(out, end='')
#
#     def __start_process(self, project, command):
#         # print('project: {}, command: {}'.format(project, command))
#         p = None
#         try:
#             p = subprocess.Popen(command, stdout=subprocess.PIPE,
#                                  stderr=subprocess.STDOUT,
#                                  universal_newlines=True,
#                                  start_new_session=False,
#                                  cwd=project
#                                  )
#         except Exception as e:
#             log.getLogger(__name__).warning(
#                 'An error occurred during start of subprocess:',
#                 stack_info=True, exc_info=e)
#             return
#
#         if p: threading.Thread(target=self.__forward_output, args=[p], daemon=True).start()
#         return p
#
#     def __construct_commands(self, jar_path):
#         projects = {}
#         for row in self.__tests_to_run:
#             project = row['project']
#             class_name = row['class']
#             tool = row['tool']
#             test = row['test']
#             if not projects.get(project): projects[project] = {}
#             classes = projects[project]
#
#             if not classes.get(class_name): classes[class_name] = {}
#             tools = classes[class_name]
#
#             if not tools.get(tool): tools[tool] = []
#             tools[tool] += [test]
#
#         self.__tests_to_run = []
#         for project, classes in projects.items():
#             for class_name, tools in classes.items():
#                 for tool, tests in tools.items():
#                     for test in tests:
#                         self.__tests_to_run.append({'project': project, 'class': class_name, 'tool': tool, 'test': test})
#
#         commands = {}
#         keys = list(projects.keys())
#         keys.reverse()
#         for project in keys:
#             classes = projects[project]
#             tasks = []
#             for class_name, tools in classes.items():
#                 for tool_name, tests in tools.items():
#                     tasks += [class_name, tool_name, str(len(tests))] + tests
#             java_util_path = os.getcwd() + '\\model\\model_util\\JavaUtil.jar'
#             print('tasks: {}'.format(tasks))
#             command = ['java', '-jar', '-Xmx4000m', java_util_path, 'PerformanceRunner', jar_path] \
#                       + [str(self.__start), str(self.__stop), str(self.__num)] + tasks
#             commands[project] = command
#         print('commands: {}'.format(commands))
#         return commands
