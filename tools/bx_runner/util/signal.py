import logging as log


class Signal:
    """
    A class representing a signal for signal slot mechanism.
    """

    __job_manager = None

    def __init__(self, name=None):
        """
        Init signal.
        :param name: name of signal. Is useful for debug. Should describe signal
        :type name: str
        """
        if not name: name = ''
        self.__name = name
        self.__slots = []

    def connect(self, slot):
        """
        Connect a new slot (method).
        :param slot: slot to connect to this signal
        :type slot: method
        """
        self.__slots.append(slot)

    def emit(self, *args):
        """
        Emit a signal (call all stored methods).
        :param args: arguments which are forwarded to every method
        """
        for slot in self.__slots:
            try:
                slot(*args)
            except Exception:
                try:
                    slot_name = slot.__name__
                except Exception:
                    log.getLogger(__name__).warning('Error during getting name for slot.')
                    slot_name = 'Unnamed'

                signal_name = self.__name
                if not signal_name: signal_name = 'Unnamed'

                log.getLogger(__name__).warning(
                    'Caught an error during emit of signal "{}" with slot "{}": \n'.format(signal_name, slot_name),
                    exc_info=True, stack_info=False)

    def emit_async(self, *args):
        """
        Emit a signal (call all stored methods) asynchronous with main thread (to avoid threading problems)
        :param args: arguments which are forwarded to every method
        """
        if Signal.__job_manager:
            Signal.__job_manager.after(0, self.emit, *args)
        else:
            log.getLogger(__name__).warning('No asynchronous emit available.')
            self.emit(*args)

    @staticmethod
    def set_job_manager(job_manager):
        """
        Set job manager for asynchronous emit.
        :param job_manager: job manager
        """
        Signal.__job_manager = job_manager
