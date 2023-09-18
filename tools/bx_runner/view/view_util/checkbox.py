import logging as log
from enum import Enum, auto

import tkinter as tk
from tkinter import ttk
from util.signal import Signal


class Checkbox(ttk.Checkbutton):
    """
    Wrapper class for checkbutton.
    """
    styles = {}

    def __init__(self, parent, bg=None):
        """
        Wrapper class for checkbutton
        :param parent: parent widget
        :type parent: tkinter.Widget
        :param bg: background color
        :type bg: str
        """
        style = None
        if bg:
            s = ttk.Style()
            style = bg + '.TCheckbutton'
            s.configure(style, background=bg)

        self.__status_changed_signal = Signal('status_changed')
        var = self.__var = tk.IntVar()
        super().__init__(parent, style=style, padding=0, variable=var, onvalue=1, offvalue=0, takefocus=0,
                         command=self.__status_changed)
        self.deactivate(notify=False)

    @property
    def status_changed(self):
        """
        Get signal and connect to it to receive notifications when status of checkbox changed.
        :return: signal
        :rtype: Signal
        """
        return self.__status_changed_signal

    @property
    def status(self):
        """
        Get status of checkbox (activated, deactivated or tristate).
        :return: status
        :rtype: CheckboxStatus
        """
        s = self.__var.get()
        if s == 1:
            return CheckboxStatus.ACTIVATED
        elif s == 0:
            return CheckboxStatus.DEACTIVATED
        elif s == -1:
            return CheckboxStatus.TRISTATE
        else:
            log.getLogger(__name__).warning('Unknown status of checkbox: ' + str(s))

    @status.setter
    def status(self, s):
        """
        Set status of checkbox.
        :param s: new status
        :type s: CheckboxStatus
        """
        if s == CheckboxStatus.ACTIVATED:
            self.activate()
        elif s == CheckboxStatus.DEACTIVATED:
            self.deactivate()
        elif s == CheckboxStatus.TRISTATE:
            self.tristate()
        else:
            log.getLogger(__name__).warning('Unknown status of checkbox: ' + str(s))

    def activate(self, notify=True):
        """
        Set status of checkbox to activated.
        :param notify: if True: status change will be notified with signal
        :type notify: bool
        """
        self.__var.set(1)
        self.state(['!alternate'])
        if notify:
            self.__status_changed()

    def deactivate(self, notify=True):
        """
        Set status of checkbox to deactivated.
        :param notify: if True: status change will be notified with signal
        :type notify: bool
        """
        self.__var.set(0)
        self.state(['!alternate'])
        if notify:
            self.__status_changed()

    def tristate(self, notify=True):
        """
        Set status of checkbox to tristate.
        :param notify: if True: status change will be notified with signal
        :type notify: bool
        """
        self.__var.set(-1)
        self.state(['alternate'])
        if notify:
            self.__status_changed()

    def __status_changed(self):
        """
        Callback function which is called if checkbox is clicked. Emits status changed signal.
        """
        self.status_changed.emit(self.status)


class CheckboxStatus(Enum):
    ACTIVATED = auto()
    DEACTIVATED = auto()
    TRISTATE = auto()
