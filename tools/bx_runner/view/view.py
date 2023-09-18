import tkinter as tk
from tkinter import font
from configuration_view.configuration_view import ConfigurationView
from settings_view import SettingsView
from export_view import ExportView


class View(tk.Tk):
    def __init__(self):
        """
        This class initializes the views and holds instances of them.
        It also provides methods to change between views.
        """
        super().__init__()
        self.columnconfigure(0, weight=1)
        self.rowconfigure(0, weight=1)
        self.minsize(470, 300)
        self.geometry("450x570")
        self.title('bxRunner')

        def_font = font.Font(font="BxRunnerFont")
        def_font.configure(size=9)
        self.option_add("*Font", def_font)

        self.__configuration_view = configuration_view = ConfigurationView(self)
        configuration_view.grid(row=0, column=0, sticky='nsew')
        
        self.__settings_view = settings_view = SettingsView(self)
        settings_view.grid(row=0, column=0, sticky='nsew')

        self.__export_view = export_view = ExportView(self)
        export_view.grid(row=0, column=0, sticky='nsew')
        export_view.back_clicked.connect(self.show_config_view)

        self.show_config_view()

    def show_config_view(self):
        """
        Show configuration view
        """
        self.__configuration_view.tkraise()
    
    def show_settings_view(self):
        """
        Show settings view
        """
        self.__settings_view.tkraise()

    def show_export_measurements(self):
        """
        Show view for exporting measurements
        """
        export_view = self.__export_view
        export_view.show_export_measurements()
        export_view.tkraise()

    def show_export_tests(self):
        """
        Show view for exporting tests
        """
        export_view = self.__export_view
        export_view.show_export_tests()
        export_view.tkraise()

    @property
    def window(self):
        """
        Get window instance
        :return: window
        :rtype: tkinter.Tk
        """
        return self

    @property
    def configuration_view(self):
        """
        Get configuration view widget
        :return: configuration view widget
        :rtype: tkinter.Frame
        """
        return self.__configuration_view

    @property
    def settings_view(self):
        """
        Get settings view widget
        :return: settings view widget
        :rtype: tkinter.Frame
        """
        return self.__settings_view

    @property
    def export_view(self):
        """
        Get export view widget
        :return: export view widget
        :rtype: tkinter.Frame
        """
        return self.__export_view
