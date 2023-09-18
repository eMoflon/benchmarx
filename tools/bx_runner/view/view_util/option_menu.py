import tkinter as tk
from tkinter import ttk
import logging as log
from util.signal import Signal


class OptionMenu(ttk.Combobox):
    """
          Wrapper class for dropdown menu
    """

    def __init__(self, master, options, width=None):
        """
        Wrapper class for dropdown menu
        :param master: parent widget
        :type master: tkinter.Widget
        :param options: options to choose from
        :type options: List[str]
        :param width: width of dropdown menu
        :type width: int
        """
        self.__notifications_activated = True
        self.__selected = Signal('selected')
        self.__index_selected = Signal('index_selected')

        self.__sv = tk.StringVar()
        self.__sv.trace('w', self.__user_selected)

        super().__init__(master, textvariable=self.__sv, state='readonly')

        self.config(values=options)
        if len(options) > 0: self.select(options[0])

        if width: self.configure(width=width)

        self.unbind_class("TCombobox", "<MouseWheel>")

    @property
    def index_selected(self):
        """
        Get signal and connect to it to receive notifications if user selected a option.
        Signal returns index of selected option in list of all options.
        :return: signal
        :rtype: Signal
        """
        return self.__index_selected

    @property
    def selected(self):
        """
        Get signal and connect to it to receive notifications if user selected a option.
        :return: signal
        :rtype: Signal
        """
        return self.__selected

    def set_options(self, options):
        """
        Set selectable options .
        :param options: new options
        :type options: List[str]
        """
        self.config(values=options)

    def get_selection(self):
        """
        Get current selected entry.
        :return: current selected entry
        :rtype: str
        """
        return self.__sv.get()

    def get_selection_index(self):
        """
        Get index of selected option in list of all selectable options.
        :return: index of selected option
        :rtype: int
        """
        return self.current()
    
    def select(self, option, notify=False):
        """
        Select a option manually
        :param option: option to select
        :type option: str
        :param notify: if true a signal is emitted that selection changed
        :type notify: bool
        """
        if not notify: self.__notifications_activated = False
        try:
            self.__sv.set(option)
        finally:
            self.__notifications_activated = True

    def select_index(self, index, notify=False):
        """
        Select a option manually with index of option
        :param index: index of option to select
        :type index: int
        :param notify: if true a signal is emitted that selection changed
        :type notify: bool
        """
        options = self['values']
        if 0 <= index < len(options): self.select(options[index], notify=notify)
        else: log.getLogger(__name__).warning('Invalid index.', stack_info=True)

    def __user_selected(self, *args):
        """
        Callback function which is called if checkbox is clicked. May emits selection signal.
        """
        if not self.__notifications_activated: return
        self.selected.emit(self.__sv.get())
        self.index_selected.emit(self.current())
