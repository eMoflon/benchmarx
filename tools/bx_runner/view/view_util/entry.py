import tkinter as tk

from util.signal import Signal


class Entry(tk.Entry):
    """
    Wrapper class for entry.
    """
    def __init__(self, parent, **kwargs):
        """
        Wrapper class of entry text field.
        :param parent: parent widget
        :type parent: tkinter.Widget
        :param kwargs: keyword arguments for super class initialisation
        """
        sv = self.__sv = tk.StringVar()
        sv.trace("w", lambda name, index, mode: self.__text_changed.emit(self.text))
        super().__init__(
            parent, textvariable=sv, borderwidth=2, relief=tk.GROOVE, highlightbackground='black', **kwargs)

        self.__text_changed = Signal('text_changed')

    @property
    def text_changed(self):
        """
        Get signal and connect to it to receive notifications when text in entry changed.
        :return: signal
        :rtype: Signal
        """
        return self.__text_changed

    @property
    def text(self):
        """
        Get current text of entry.
        :return: text
        :rtype: str
        """
        return self.__sv.get()

    @text.setter
    def text(self, new_text):
        """
        Set text of entry field
        :param new_text: new text
        :type new_text: str
        """
        if not new_text:
            new_text = ''
        self.__sv.set(new_text)

