import logging as log
import os

from model_util.runner import Runner

class JUnitRunner(Runner):
    """
    JUnitFinder runs a java program which runs JUnit test cases in a JAR-file.
    """
    def __init__(self):
        self.__remaining_commands = {}
        super().__init__()

    # noinspection PyMethodOverriding
    def start(self, jar_path, df):
        """
        Overrides method in super class Runner. Start running JUnit tests.
        :param jar_path: path to JAR file
        :type jar_path: str
        :param df: dataframe which specifies tests to run
        :type df: pandas.core.frame.DataFrame
        """
        commands = self.__construct_commands(jar_path, df)
        if len(commands) == 0:
            log.getLogger(__name__).warning('No tests to start.')
            return

        with self._mutex:
            self.stop()
            for i in range(len(commands)):
                project, command = commands.popitem()
                project = project[:project.rfind('--')]
                try:
                    super().start(command, working_dir=project)
                except Exception:
                    log.getLogger(__name__).warning('Error during start of test cases:', exc_info=True)
                    continue
                break
            self.__remaining_commands = commands

            if self._process: self.started.emit()
            else: log.getLogger(__name__).warning('No process could be started.')

    # noinspection PyMethodOverriding
    def stop(self):
        """
        Overrides method in super class Runner. Stop running JUnit tests.
        """
        with self._mutex:
            self.__remaining_commands = {}
            super().stop()

    def _forward_output(self, p):
        """
        Get output of process and extract results/forward to std out. Overrides super class method because a more
        specific method is needed.
        :param p: process
        """
        buffer = ""
        while True:
            out = p.stdout.readline()
            if out == '' and p.poll() is not None: break
            if out: buffer = self.__extract_result(out, buffer)

        return_code = p.poll()
        if return_code != 0: log.getLogger(__name__).debug('Return code of subprocess not 0: {}'.format(return_code))

        if buffer: log.getLogger(__name__).warning(
            'Process ended without transmitting full error msg for failed junit result: {}'.format(buffer))

        with self._mutex:
            commands = self.__remaining_commands
            for i in range(len(self.__remaining_commands)):
                project, command = commands.popitem()
                project = project[:project.rfind('--')]
                try:
                    super().start(command, working_dir=project, stop=False)
                except Exception:
                    log.getLogger(__name__).warning('Error during start of test cases:', exc_info=True)
                    continue
                break
            self.__remaining_commands = commands

            if len(self.__remaining_commands) == 0: # last process
                self._process = None
                self.ended.emit()

    def __extract_result(self, out, buffer):
        """
        Extract results of output from process.
        :param out: output of process
        :type out: str
        :param buffer: buffer containing last output
        :type buffer: str
        :return: new buffer
        :rtype: str
        """
        result_identifier = 'JUnit result: '
        error_msg_start_identifier = '<errormsg>'
        error_msg_end_identifier = '</errormsg>'

        if not buffer and out[:len(result_identifier)] != result_identifier:
            print(out, end='')
            return ""

        end_idx = out.find(error_msg_end_identifier)
        if end_idx == -1: # end of error msg not found
            buffer += out
            return buffer

        # end of error msg found
        buffer += out[:end_idx]

        error_msg_idx = buffer.find(error_msg_start_identifier)
        if error_msg_idx == -1:
            log.getLogger(__name__).warning('Found result with unknown format: {}'.format(buffer))
            return ""

        result = buffer[len(result_identifier):error_msg_idx].split()
        error_msg = buffer[error_msg_idx + len(error_msg_start_identifier):]

        if len(result) != 4:
            log.getLogger(__name__).warning('Found result with unknown format: {}'.format(buffer))
            return ""

        result_dict = {'success': result[0] == '1', 'class': result[1], 'tool': result[2],
                       'test': result[3], 'error_msg': error_msg}
        self.new_result.emit(result_dict)
        return ""

    def __construct_commands(self, jar_path, df):
        """
        Construct commands to start processes to run JUnit tests.
        :param jar_path: path to jar file
        :type jar_path: str
        :param df: dataframe containing tests to run
        :type df: pandas.DataFrame
        :return: dictionary with paths to projects as keys and commands as values
        :rtype: Dict[str, str]
        """
        projects = {}
        for index, row in df.iterrows():
            project = row['project']
            class_name = row['class']
            tool = row['tool']
            test = row['test']
            if not projects.get(project): projects[project] = {}
            classes = projects[project]

            if not classes.get(class_name): classes[class_name] = {}
            tools = classes[class_name]

            if not tools.get(tool): tools[tool] = []
            tools[tool] += [test]

        commands = {}
        java_util_path = os.getcwd() + '\\model\\model_util\\JavaUtil.jar'
        for project, classes in projects.items():
            for class_name, tools in classes.items():
                for tool_name, tests in tools.items():
                    tasks = [class_name, tool_name] + tests
                    command = ['java', '-jar', java_util_path, 'JUnitRunner', jar_path] + tasks
                    commands[project + '--' + tool_name + '-' + class_name] = command

        return commands

    def _extract_results(self, out):
        """
        Abstract class of super class has to be overridden but is not used in junit finder.
        :param out: output of process
        :type out: str
        """
        log.getLogger(__name__).warning('Function extract results should not be used in junit runner.')
