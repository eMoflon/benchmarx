import tkinter as tk
from util.signal import Signal


class Button(tk.Button):
    """
    Wrapper class for buttons.
    """
    def __init__(self, master, text, bg=None, hover=None, dynamic_fg=True, **kwargs):
        """
        Wrapper class for buttons.
        :param master: parent widget
        :type master: tkinter.Widget
        :param text: text of button
        :type text: str
        :param bg: background color
        :type bg: str
        :param hover: True if hovering with mouse should change button appearance
        :type hover: bool
        :param dynamic_fg: True if hovering with mouse should change foreground
        :type dynamic_fg: bool
        :param kwargs: keyword arguments for super class initialisation
        """
        super().__init__(master, command = self.__clicked_cb, **kwargs)
        self.__clicked = Signal('clicked')

        self['text'] = text

        self.__dynamic_fg = dynamic_fg
        self.__bg = bg
        if bg is not None:
            self['bg'] = bg

        if hover:
            self.__hover_color_bg = 'gray85'
            self['activebackground'] = self.__hover_color_bg
            if self.__dynamic_fg:
                self.__hover_color_fg = '#0f8bff'
                self['activeforeground'] = self.__hover_color_fg
            self.bind("<Enter>", self.__mouse_enters)
            self.bind("<Leave>", self.__mouse_leaves)
        
    @property
    def clicked(self):
        """
        Get signal and connect to it to receive notifications.
        :return: signal
        :rtype: Signal
        """
        return self.__clicked

    @property
    def text(self):
        """
        Get text of button
        :return: text
        :rtype: str
        """
        return self['text']

    @text.setter
    def text(self, value):
        """
        Setter for text of button
        :param value: new text
        :type value: str
        """
        self['text'] = value
        
    def __clicked_cb(self):
        """
        Callback function which is called if button is clicked. Emits clicked signal.
        """
        self.clicked.emit()

    def __mouse_enters(self, event):
        """
        Callback function which is called if mouse enters button. May changes button appearance.
        :param event: mouse event
        """
        event.widget['background'] = self.__hover_color_bg
        if self.__dynamic_fg:
            event.widget['foreground'] = self.__hover_color_fg

    def __mouse_leaves(self, event):
        """
        Callback function which is called if mouse leaves button. May changes button appearance.
        :param event: mouse event
        """
        if self.__dynamic_fg:
            event.widget['foreground'] = 'black'
        if self.__bg:
            event.widget['background'] = self.__bg
        else:
            event.widget['background'] = 'SystemButtonFace'
