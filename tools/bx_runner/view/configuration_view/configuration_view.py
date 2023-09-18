import tkinter as tk

from view_util.button import Button
from .tests_view import TestsView
from .measurements_view import MeasurementsView


class ConfigurationView(tk.Frame):
    """
    View for header of tests view and measurements view.
    """
    def __init__(self, master):
        """
        Create header and init.
        :param master: parent widget
        :type master: tkinter.Frame
        """
        super().__init__(master)

        self.columnconfigure(0, weight=1)
        self.rowconfigure(0, weight=0)
        self.rowconfigure(1, weight=1)

        self.__widgets = {}
        self.__showing_tests = True

        ### Create header
        header = tk.Frame(self)
        header.columnconfigure(0, weight=1)
        header.columnconfigure(3, weight=1)
        header.rowconfigure(0, weight=1)
        header.grid(row=0, column=0, sticky='ew')

        btn = Button(header, 'Settings', hover=True, relief=tk.FLAT, width=10, height=3)
        btn.grid(row=0, column=0, sticky="nsw", padx=0, pady=0)
        self.__widgets['settings_btn'] = btn

        # Buttons for changing tests/measurements
        button_width = 8
        btn = Button(header, 'Tests', width=button_width, relief=tk.FLAT)
        btn.grid(row=0, column=1, sticky="nse", padx=[15, 0], pady=15)
        self.__widgets['tests_btn'] = btn
        btn.bind("<Enter>", self.__mouse_enters)
        btn.bind("<Leave>", self.__mouse_leaves)

        btn = Button(header, 'Measure', width=button_width, relief=tk.FLAT)
        btn.grid(row=0, column=2, sticky="nsw", padx=[0, 15], pady=15)
        self.__widgets['measurements_btn'] = btn
        btn.bind("<Enter>", self.__mouse_enters)
        btn.bind("<Leave>", self.__mouse_leaves)

        # Start button
        start_btn = Button(header, text='Start', hover=True, relief=tk.FLAT, width=10, height=3)
        start_btn.grid(row=0, column=4, sticky="nse", padx=0, pady=0)
        self.__start_btn = start_btn

        config_view_tests = TestsView(self)
        config_view_tests.grid(row=1, column=0, sticky='nsew')
        self.__widgets['config_view_tests'] = config_view_tests

        config_view_measurements = MeasurementsView(self)
        config_view_measurements.grid(row=1, column=0, sticky='nsew')
        self.__widgets['config_view_measurements'] = config_view_measurements

        self.show_tests()

    def show_tests(self):
        """
        Show tests view (hide measurements view).
        """
        self.__showing_tests = True
        widgets = self.__widgets
        widgets['tests_btn']['bg'] = 'gray77'
        widgets['tests_btn']['activebackground'] = 'gray77'
        widgets['measurements_btn']['bg'] = 'gray85'
        widgets['measurements_btn']['activebackground'] = 'gray85'
        widgets['config_view_measurements'].grid_remove()
        widgets['config_view_tests'].grid()

    def show_measurements(self):
        """
        Show measurements view (hide tests view).
        """
        self.__showing_tests = False
        widgets = self.__widgets
        widgets['tests_btn']['bg'] = 'gray85'
        widgets['tests_btn']['activebackground'] = 'gray85'
        widgets['measurements_btn']['bg'] = 'gray77'
        widgets['measurements_btn']['activebackground'] = 'gray77'
        widgets['config_view_tests'].grid_remove()
        widgets['config_view_measurements'].grid()

    @property
    def widgets(self):
        """
        Get a dictionary with the widgets of this view as values and describing strings as keys.
        :return: dictionary with the widgets of this view as values and describing strings as keys.
        :rtype: Dict{str: tkinter.Widget}
        """
        return self.__widgets.copy()

    @property
    def tests(self):
        """
        Get tests view widget.
        :return: tests view widget
        :rtype: TestsView
        """
        return self.__widgets['config_view_tests']

    @property
    def measurements(self):
        """
        Get measurements view widget.
        :return: measurements view widget
        :rtype: MeasurementsView
        """
        return self.__widgets['config_view_measurements']

    @property
    def start_btn(self):
        """
        Get start button widget.
        :return: start button
        :rtype: Button
        """
        return self.__start_btn

    @property
    def is_showing_tests(self):
        """
        Get if tests view is shown or measurements view.
        :return: True: tests view is shown. False: measurements view is shown
        :rtype: bool
        """
        return self.__showing_tests is True

    def __mouse_enters(self, event):
        """
        Private callback function which is called when mouse enters a widget.
        :param event: mouse event
        """
        fg = '#0f8bff'
        event.widget['foreground'] = fg
        event.widget['activeforeground'] = fg

    def __mouse_leaves(self, event):
        """
        Private callback function which is called when mouse leaves a widget.
        :param event: mouse event
        """
        fg = 'black'
        event.widget['foreground'] = fg
        event.widget['activeforeground'] = fg