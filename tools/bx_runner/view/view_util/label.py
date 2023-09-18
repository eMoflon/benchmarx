import tkinter as tk


class Label(tk.Label):
    """
    Wrapper class for label.
    """

    def __init__(self, parent, *, wrap=False, **kwarg):
        """
        Wrapper class for labels
        :param parent: parent widget
        :type parent: tkinter.Widget
        :param wrap: if true text will be wrapped if label is to small
        :type wrap: bool
        :param kwargs: keyword arguments for super class initialisation
        """
        if wrap and kwarg.get('wraplength'):
            super().__init__(parent, **kwarg)
            self.bind('<Configure>', lambda e: self.__config_width())
        elif wrap:
            wraplength = 300  # default
            super().__init__(parent, wraplength=wraplength, **kwarg)
            self.bind('<Configure>', lambda e: self.__config_width())
        else:
            super().__init__(parent, **kwarg)

    @property
    def text(self):
        """
        Get text of label
        :return: text
        :rtype: str
        """
        return self['text']

    @text.setter
    def text(self, value):
        """
        Set text of label
        :param value: new text
        :type value: str
        """
        if not value:
            value = ''
        self.config(text=value)

    def __config_width(self):
        """
        Function is called on resize and sets wraplength of label
        """
        self.config(wraplength=self.winfo_width()-3)
