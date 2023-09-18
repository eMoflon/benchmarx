from abc import ABC, abstractmethod
import subprocess
import logging as log
import threading
from util.signal import Signal


class Runner(ABC):
    """
    Abstract runner class which can create new processes and communicates with them.
    """
    def __init__(self):
        self._process = None
        self._mutex = threading.RLock()

        self.__new_result = Signal('new_result')
        self.__started = Signal('started')
        self.__ended = Signal('ended')
        
    @property
    def new_result(self):
        """
        Get signal and connect to it to receive notifications when a new result is available.
        :return: signal
        :rtype: Signal
        """
        return self.__new_result

    @property
    def started(self):
        """
        Get signal and connect to it to receive notifications when runner started.
        :return: signal
        :rtype: Signal
        """
        return self.__started

    @property
    def ended(self):
        """
        Get signal and connect to it to receive notifications when runner ended.
        :return: signal
        :rtype: Signal
        """
        return self.__ended
    
    def start(self, command, *, working_dir=None, stop=True):
        """
        Start running a process.
        :param command: command for staring process
        :type command: str
        :param working_dir: working directory for new process
        :type working_dir: str
        :param stop: True: run stop command before starting
        :type stop: bool
        """
        with self._mutex:
            if stop: self.stop()

            try:
                if working_dir:
                    p = subprocess.Popen(command, stdout=subprocess.PIPE, stderr=subprocess.STDOUT,
                                         universal_newlines=True, start_new_session=False, cwd=working_dir)
                else:
                    p = subprocess.Popen(command, stdout=subprocess.PIPE, stderr=subprocess.STDOUT,
                                         universal_newlines=True, start_new_session=False)

                threading.Thread(target=self._forward_output, args=[p], daemon=True).start()

            except Exception as e:
                log.getLogger(__name__).warning(
                    'An error occurred during start of subprocess:',
                    stack_info=True, exc_info=e)
                return

            self._process = p
            self.started.emit()

    def stop(self, notify=True):
        """
        Stop running process.
        :param notify: True: emit signal that process was stopped
        :type notify: bool
        """
        with self._mutex:
            if self._process:
                try:
                    pid = self._process.pid
                    subprocess.Popen("TASKKILL /F /PID {pid} /T".format(pid=pid))
                except Exception as e:
                    log.getLogger(__name__).debug(
                        'An error occurred during kill of subprocess:',
                        stack_info=True, exc_info=e)
                self._process = None
                if notify: self.ended.emit()

    def is_running(self):
        """
        Get if a runner is running or not.
        :return: True: runner is running. False: runner is not running
        :rtype: bool
        """
        with self._mutex:
            if self._process:
                return True
            else:
                return False

    def _forward_output(self, p):
        """
        Get output of process and extract results/forward to std out.
        :param p: process
        """
        while True:
            out = p.stdout.readline()
            if out == '' and p.poll() is not None: break
            if out: self._extract_results(out)

        return_code = p.poll()
        if return_code != 0: log.getLogger(__name__).info('Return code of subprocess not 0: {}'.format(return_code))

        with self._mutex:
            if self._process == p:
                self._process = None
                self.ended.emit()

    @abstractmethod
    def _extract_results(self, out):
        """
        Abstract method to extract results of output of process.
        :param out: output of process
        :type out: str
        """
        pass

