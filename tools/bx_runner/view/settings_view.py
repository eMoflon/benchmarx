import logging as log
import tkinter as tk
from tkinter import filedialog

from util.signal import Signal
from view_util.button import Button
from view_util.entry import Entry


class SettingsView(tk.Frame):
    """
    View for settings
    """
    def __init__(self, parent):
        """
        Init this view with its widgets
        :param parent: parent widget
        :type parent: tkinter.Widget
        """
        super().__init__(parent)

        self.columnconfigure(0, weight=1)
        self.rowconfigure(0, weight=0)
        self.rowconfigure(1, weight=1)

        self.__path_changed = Signal('path_changed')
        self.__benchmarx_path_changed = Signal('benchmarx_path_changed')
        self.__widgets = {}

        # Create header
        header = tk.Frame(self)
        header.columnconfigure(0, weight=1)
        header.grid(row=0, column=0, sticky='ew')

        btn = Button(header, 'Back', hover=True, relief=tk.FLAT, width=10, height=3)
        btn.grid(row=1, column=0, sticky="e", padx=0, pady=0)
        self.__widgets['back_btn'] = btn

        # Content
        content = tk.Frame(self, bg='white')
        content.grid(row=1, column=0, sticky='nsew')
        content.columnconfigure(0, weight=1)

        # Jar path
        jar_path = tk.LabelFrame(content, text='Path to .jar file',bg='white')
        jar_path.grid(row=0, column=0, padx=25, pady=25, sticky='ew')

        jar_path.columnconfigure(0, weight=1)
        jar_path.columnconfigure(1, weight=0)

        sv = tk.StringVar()
        sv.trace_add('write', self.__notify)
        self.__sv = sv

        entry = tk.Entry(jar_path, textvariable=sv, borderwidth=2, relief=tk.GROOVE)
        entry.grid(row=0, column=0, sticky="ew", padx=15, pady=10, columnspan=2)
        self.__widgets['jar_path_entry'] = entry

        btn = Button(jar_path, text='Choose file ', hover=True, relief=tk.FLAT)
        btn.grid(row=1, column=0, sticky="e", padx=15, pady=5)
        btn.clicked.connect(self.choose_path)

        # benchmarx path
        benchmarx_path_area = tk.LabelFrame(content, text='Path to Benchmarx directory', bg='white')
        benchmarx_path_area.grid(row=1, column=0, padx=25, pady=5, sticky='ew')

        benchmarx_path_area.columnconfigure(0, weight=1)
        benchmarx_path_area.columnconfigure(1, weight=0)

        entry = self.__benchmarx_path_entry = Entry(benchmarx_path_area)
        entry.grid(row=0, column=0, sticky="ew", padx=15, pady=10, columnspan=2)
        entry.text_changed.connect(self.__notify_benchmarx_benchmarx_path_changed)

        btn = Button(benchmarx_path_area, text='Choose directory ', hover=True, relief=tk.FLAT)
        btn.grid(row=1, column=0, sticky="e", padx=15, pady=5)
        btn.clicked.connect(self.__ask_directory)

    @property
    def path_changed(self):
        """
        Get signal and connect to it to receive notifications if path of JAR-file changed.
        :return: signal
        :rtype: Signal
        """
        return self.__path_changed

    @property
    def benchmarx_path_changed(self):
        """
        Get signal and connect to it to receive notifications if Benchmarx directory changed.
        :return: signal
        :rtype: Signal
        """
        return self.__benchmarx_path_changed

    def set_path(self, path):
        """
        Set path of JAR-file
        :param path: path of JAR-file
        :type path: str
        """
        self.__sv.set(path)

    def choose_path(self):
        """
        Choose path to JAR-file with a filedialog window.
        """
        path = filedialog.askopenfilename(title="Select .jar",
                                             filetypes=(("jar files", "*.jar*"),))
        if path == '':
            log.getLogger(__name__).debug('Path choosing was canceled.')
        else:
            log.getLogger(__name__).debug('Chosen path: ' + path)
            self.__sv.set(path)

    def __notify(self, var=None, index=None, mode=None):
        """
        Private callback function to notify that the path of the JAR-file changed.
        """
        log.getLogger(__name__).debug('Entry for path to .jar changed in view: '
                                      + self.__widgets['jar_path_entry'].get())
        self.__path_changed.emit(self.__sv.get())

    def set_benchmarx_path(self, path):
        """
        Set the path to the directory of Benchmarx
        :param path:
        :type path: str
        """
        self.__benchmarx_path_entry.text = path

    def set_cb_benchmarx_benchmarx_path_changed(self, cb):
        """
        Set a callback to be notified if path to Benchmarx changed.
        :param cb: callback function
        :type cb: Callable
        """
        self.benchmarx_path_changed.connect(cb)

    def __notify_benchmarx_benchmarx_path_changed(self, new_path):
        """
        Private callback function to notify that the path of benchmarx changed.
        """
        self.benchmarx_path_changed.emit(new_path)

    def __ask_directory(self):
        """
        Choose directory to Benchmarx with a filedialog window.
        """
        dir = filedialog.askdirectory()
        if dir == '':
            log.getLogger(__name__).debug('Directory choosing was canceled.')
        else:
            log.getLogger(__name__).debug('Chosen directory: ' + dir)
            self.__benchmarx_path_entry.text = dir

    @property
    def widgets(self):
        """
        Get a dictionary with the widgets of this view as values and describing strings as keys
        :return: dictionary with the widgets of this view as values and describing strings as keys
        :rtype: {str: tkinter.Widget}
        """
        return self.__widgets.copy()
