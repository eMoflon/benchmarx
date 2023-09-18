import threading
import logging as log
import tkinter as tk
from tkinter import ttk
import time
import pandas as pd
import numpy as np

from view_util.scroll_frame import ScrollFrame
from view_util.label import Label
from view_util.option_menu import OptionMenu
from view_util.button import Button
from view_util.treeview.treeview import TreeView
from view_util.treeview.node import Node
from view_util.entry import Entry
from view_util.checkbox import Checkbox, CheckboxStatus
from plotter import Plotter
from util.signal import Signal
from tkinter.messagebox import askokcancel, WARNING
from tkinter import filedialog


class MeasurementsView(tk.Frame):
    """
    View for performance measurements.
    """
    def __init__(self, master):
        """
        Init widgets of this view.
        :param master: parent widget
        :type master: tkinter.Frame
        """
        super().__init__(master)

        self.columnconfigure(0, weight=1)
        self.rowconfigure(0, weight=1)

        self.__widgets = {}
        self.__start_time_stopwatch = time.time()
        self.__start_time_timeout = time.time()
        self.__stopwatch_job = None
        self.__timeout_job = None
        self.__timeout_seconds = None
        self.__timeout = Signal('timeout')
        self.__export_clicked = Signal('export_clicked')
        self.__reset_clicked = Signal('reset clicked')

        scroll_frame = ScrollFrame(self, bg='white')
        scroll_frame.grid(row=0, column=0, sticky='nsew', padx=0)

        frame = tk.Frame(scroll_frame.port, bg='white')
        frame.grid(row=0, column=0, sticky='nsew')
        frame.columnconfigure(0, weight=1)

        ### Results area
        results_area = self.__results_area = tk.Frame(frame, bg='white')
        results_area.grid(row=0, column=0, sticky='nsew')
        results_area.columnconfigure(0, weight=1)

        btn_frame = self.__btn_frame = tk.Frame(results_area, bg='white')
        btn_frame.grid(row=results_area.grid_size()[1], column=0, sticky='ew')
        btn_frame.columnconfigure(1, weight=1)

        btn = self.__configure_btn = Button(btn_frame, '< Configure', relief=tk.FLAT, hover=True)
        btn.grid(row=0, column=0, padx=[25, 15], pady=[10, 0])
        btn.clicked.connect(self.__back_clicked)

        btn = self.__reset_btn = Button(btn_frame, 'Reset', relief=tk.FLAT, hover=True)
        btn.grid(row=0, column=1, padx=[0, 25], pady=[10, 0], sticky='w')
        self.__reset_btn.clicked.connect(self.__cb_reset_clicked)

        btn = self.__reset_btn = Button(btn_frame, 'Import', relief=tk.FLAT, hover=True)
        btn.grid(row=0, column=2, padx=[0, 15], pady=[10, 0], sticky='e')
        btn.clicked.connect(self.__import_clicked)

        btn = Button(btn_frame, 'Export', relief=tk.FLAT, hover=True)
        btn.grid(row=0, column=3, padx=[0, 25], pady=[10, 0], sticky='e')
        btn.clicked.connect(self.export_clicked.emit)

        ### Info area
        info_area = self.__info_area = tk.Frame(results_area, bg='white')
        info_area.grid(row=results_area.grid_size()[1], column=0, sticky='nsew', padx=[0, 25], pady=[15, 10])
        info_area.columnconfigure(1, weight=1)

        # Progress
        row = info_area.grid_size()[1]
        lbl = Label(info_area, text='Progress:', bg='white')
        lbl.grid(row=row, column=0, sticky='w', padx=25, pady=5)

        progress_frame = tk.Frame(info_area, bg='white')
        progress_frame.grid(row=row, column=1, sticky='ew')
        progress_frame.columnconfigure(0, weight=1)

        progress_bar = self.__progress_bar = ttk.Progressbar(progress_frame, mode='determinate')
        progress_bar.grid(row=0, column=0, sticky='ew')

        lbl = self.__progress_lbl = Label(progress_frame, text='-/-', bg='white')
        lbl.grid(row=0, column=1)

        # Status
        row = info_area.grid_size()[1]
        lbl = Label(info_area, text='Status:', bg='white')
        lbl.grid(row=row, column=0, sticky='w', padx=25, pady=5)

        lbl = self.__status_lbl = Label(info_area, text='-', bg='white', wrap=True, justify='left', anchor='w')
        lbl.grid(row=row, column=1, sticky='ew')

        # Time
        row = info_area.grid_size()[1]
        lbl = Label(info_area, text='Time:', bg='white')
        lbl.grid(row=row, column=0, sticky='w', padx=25, pady=5)

        lbl = self.__time_lbl = Label(info_area, text='-', bg='white')
        lbl.grid(row=row, column=1, sticky='w')

        # Timeout
        row = info_area.grid_size()[1]
        lbl = Label(info_area, text='Timeout:', bg='white')
        lbl.grid(row=row, column=0, sticky='w', padx=25, pady=5)

        timeout_frame = tk.Frame(info_area, bg='white')
        timeout_frame.grid(row=row, column=1, sticky='ew')
        timeout_frame.columnconfigure(1, weight=1)

        lbl = self.__timeout_lbl = Label(timeout_frame, text='-', bg='white')
        lbl.grid(row=row, column=0, sticky='w')

        btn = self.__skip_btn = Button(timeout_frame, ' Skip ', relief=tk.FLAT, hover=True)
        btn.grid(row=row, column=1, sticky='e')

        ### Plotter area
        plotter_area = self.__plotter_area = tk.Frame(results_area, bg='white')
        plotter_area.grid(row=results_area.grid_size()[1], column=0, sticky='ew')
        plotter_area.columnconfigure(0, weight=1)

        row = plotter_area.grid_size()[1]
        plotter = self.__plotter = Plotter(plotter_area)
        plotter.grid(row=row, column=0, sticky='news')
        self.__plotter_row = row

        row = plotter_area.grid_size()[1]
        plot_spec_area = self.__plot_spec_area = tk.Frame(plotter_area, bg='white')
        plot_spec_area.grid(row=row, column=0, sticky='ew', padx=20, pady=20)
        plot_spec_area.columnconfigure(1, weight=1)

        ### Plot settings
        lbl = Label(plot_spec_area, text='Plot settings', bg='white', font=(None, 12))
        lbl.grid(row=0, column=0, sticky='w', pady=[0, 10])

        # Metric
        row = plot_spec_area.grid_size()[1]
        lbl = Label(plot_spec_area, text='Metric:', bg='white')
        lbl.grid(row=row, column=0, sticky='w', pady=[0, 10])

        dropdown_width = 20
        dropdown = OptionMenu(plot_spec_area, ['median', 'mean'], width=dropdown_width)
        dropdown.grid(row=row, column=1, sticky='w')

        cb = plotter.set_metric
        dropdown.selected.connect(cb)

        # Title
        row = plot_spec_area.grid_size()[1]
        lbl = Label(plot_spec_area, text='Title:', bg='white')
        lbl.grid(row=row, column=0, sticky='w', pady=[0, 10])

        entry = self.__title_entry = Entry(plot_spec_area)
        entry.grid(row=row, column=1, sticky='w')

        cb = plotter.set_title
        entry.text_changed.connect(cb)

        # x label
        row = plot_spec_area.grid_size()[1]
        lbl = Label(plot_spec_area, text='Label x axis:', bg='white')
        lbl.grid(row=row, column=0, sticky='w', pady=[0, 10])

        entry = self.__x_label_entry = Entry(plot_spec_area)
        entry.grid(row=row, column=1, sticky='w')

        cb = plotter.set_x_label
        entry.text_changed.connect(cb)
        entry.text = 'model size'

        # y label
        row = plot_spec_area.grid_size()[1]
        lbl = Label(plot_spec_area, text='Label y axis:', bg='white')
        lbl.grid(row=row, column=0, sticky='w', pady=[0, 10])

        entry = self.__y_label_entry = Entry(plot_spec_area)
        entry.grid(row=row, column=1, sticky='w')

        cb = plotter.set_y_label
        entry.text_changed.connect(cb)
        entry.text = 'time in seconds'

        # x scale
        row = plot_spec_area.grid_size()[1]
        lbl = Label(plot_spec_area, text='Scale x axis:', bg='white')
        lbl.grid(row=row, column=0, sticky='w', pady=[0, 10])

        dropdown_width = 20
        dropdown = self.__x_scale_dropdown = OptionMenu(plot_spec_area, ['linear', 'log'], width=dropdown_width)
        dropdown.grid(row=row, column=1, sticky='w')

        cb = plotter.set_x_scale
        dropdown.selected.connect(cb)

        # y scale
        row = plot_spec_area.grid_size()[1]
        lbl = Label(plot_spec_area, text='Scale y axis:', bg='white')
        lbl.grid(row=row, column=0, sticky='w', pady=[0, 5])

        dropdown = self.__y_scale_dropdown = OptionMenu(plot_spec_area, ['linear', 'log'], width=dropdown_width)
        dropdown.grid(row=row, column=1, sticky='w')

        cb = plotter.set_y_scale
        dropdown.selected.connect(cb)

        # Major grid
        row = plot_spec_area.grid_size()[1]
        lbl = Label(plot_spec_area, text='Major grid:', bg='white')
        lbl.grid(row=row, column=0, sticky='w', pady=5)

        checkbox = self.__major_grid_checkbox = Checkbox(plot_spec_area, bg='white')
        checkbox.grid(row=row, column=1, sticky='w')

        cb = self.__major_grid_selected
        checkbox.status_changed.connect(cb)

        # Minor grid
        row = plot_spec_area.grid_size()[1]
        lbl = Label(plot_spec_area, text='Minor grid:', bg='white')
        lbl.grid(row=row, column=0, sticky='w', pady=5)

        checkbox = self.__minor_grid_checkbox = Checkbox(plot_spec_area, bg='white')
        checkbox.grid(row=row, column=1, sticky='w')

        cb = self.__minor_grid_selected
        checkbox.status_changed.connect(cb)
        checkbox.activate()

        # Separator
        row = plot_spec_area.grid_size()[1]
        lbl = Label(plot_spec_area, text='Separator:', bg='white')
        lbl.grid(row=row, column=0, sticky='w', pady=5)

        dropdown = self.__separator_dropdown = OptionMenu(plot_spec_area, ['Point', 'Comma'], width=dropdown_width)
        dropdown.grid(row=row, column=1, sticky='w')

        cb = self.__separator_changed
        dropdown.selected.connect(cb)

        ### Line settings
        row = plot_spec_area.grid_size()[1]
        lbl = Label(plot_spec_area, text='Line settings', bg='white', font=(None, 12))
        lbl.grid(row=row, column=0, sticky='e', padx=[0, 10], pady=[10, 0])

        dropdown = self.__line_dropdown = OptionMenu(plot_spec_area, [], width=40)
        dropdown.grid(row=row, column=1, sticky='sw')
        dropdown.index_selected.connect(self.__user_changed_line)

        # Show line
        row = plot_spec_area.grid_size()[1]
        lbl = Label(plot_spec_area, text='Show line:', bg='white')
        lbl.grid(row=row, column=0, sticky='w', pady=5)

        checkbox = self.__show_line_checkbox = Checkbox(plot_spec_area, bg='white')
        checkbox.grid(row=row, column=1, sticky='w')

        cb = self.__user_changed_visibility
        checkbox.status_changed.connect(cb)

        # Label
        row = plot_spec_area.grid_size()[1]
        lbl = Label(plot_spec_area, text='Label:', bg='white')
        lbl.grid(row=row, column=0, sticky='w', pady=5)

        entry = self.__line_label_entry = Entry(plot_spec_area)
        entry.grid(row=row, column=1, sticky='ew')

        cb = self.__user_changed_line_label
        entry.text_changed.connect(cb)

        # Line color
        row = plot_spec_area.grid_size()[1]
        lbl = Label(plot_spec_area, text='Line color:', bg='white')
        lbl.grid(row=row, column=0, sticky='w', pady=5)

        colors = ['blue', 'orange', 'green', 'red', 'purple', 'brown', 'pink', 'gray', 'olive', 'cyan']
        dropdown = self.__line_color_dropdown = OptionMenu(plot_spec_area, colors, width=40)
        dropdown.grid(row=row, column=1, sticky='sw')
        dropdown.selected.connect(self.__user_changed_linecolor)
        dropdown.select('')

        # Line style
        row = plot_spec_area.grid_size()[1]
        lbl = Label(plot_spec_area, text='Line style:', bg='white')
        lbl.grid(row=row, column=0, sticky='w', pady=5)

        dropdown = self.__line_style_dropdown = OptionMenu(plot_spec_area, ['solid', 'dashed', 'dashdot', 'dotted', 'None'], width=40)
        dropdown.grid(row=row, column=1, sticky='sw')
        dropdown.selected.connect(self.__user_changed_linestyle)
        dropdown.select('')

        # Marker
        row = plot_spec_area.grid_size()[1]
        lbl = Label(plot_spec_area, text='Marker:', bg='white')
        lbl.grid(row=row, column=0, sticky='w', pady=5)

        colors = ['x', '+', 'X', '.', 'o', 'None']
        dropdown = self.__marker_dropdown = OptionMenu(plot_spec_area, colors, width=40)
        dropdown.grid(row=row, column=1, sticky='sw')
        dropdown.selected.connect(self.__user_changed_marker)
        dropdown.select('')

        ### Configuration area
        config_area = self.__config_area = tk.Frame(frame, bg='white')
        config_area.grid(row=0, column=0, sticky='nsew')
        config_area.columnconfigure(2, weight=1)

        row = config_area.grid_size()[1]
        # Export button
        btn = Button(config_area, 'Export', relief=tk.FLAT, hover=True)
        btn.grid(row=row, column=0, padx=25, pady=[10, 3], sticky='w')
        btn.clicked.connect(self.export_clicked.emit)

        btn = Button(config_area, 'Plotter >', relief=tk.FLAT, hover=True)
        btn.grid(row=row, column=2, sticky='e', padx=25, pady=[10, 3])
        btn.clicked.connect(self.__to_plotter)

        ### Timeout area
        lbl, self.__timeout_entry = self.__create_entry(parent=config_area, lbl_text='Timeout:', entry_text='120')

        row = config_area.grid_size()[1] - 1
        timeout_area = tk.Frame(config_area, bg='white')
        timeout_area.grid(row=row, column=2, sticky='w', padx=[10, 0])

        options = ['s', 'min', 'h']
        option_menu = OptionMenu(timeout_area, options, width=5)
        option_menu.grid(row=0, column=0, sticky='w')
        self.__timeout_unit_option_menu = option_menu

        checkbox = self.__timeout_checkbox = Checkbox(timeout_area, bg='white')
        checkbox.grid(row=0, column=1, sticky='w', padx=5)

        # Start
        lbl, self.__start_size_entry = self.__create_entry(parent=config_area, lbl_text='Start:', entry_text='1102')

        # Stop
        lbl, self.__stop_size_entry = self.__create_entry(parent=config_area, lbl_text='Stop:', entry_text='1013102')

        # #Points
        lbl, self.__num_size_entry = self.__create_entry(parent=config_area, lbl_text='#Points:', entry_text='47')

        # Iterations
        lbl, self.__iterations_entry = self.__create_entry(parent=config_area, lbl_text='#Iterations:', entry_text='1')

        # Seperator
        separator = ttk.Separator(config_area, orient='horizontal')
        separator.grid(row=config_area.grid_size()[1], column=0, sticky='ew', pady=10, columnspan=3)

        ### Update area
        row = config_area.grid_size()[1]
        # Progressbar
        bar = self.__bar = ttk.Progressbar(config_area, mode='indeterminate')
        bar.grid(row=row, column=0, columnspan=2, padx=25, sticky='e')
        bar.grid_remove()

        btn = Button(config_area, 'Update', relief=tk.FLAT, hover=True)
        btn.grid(row=row, column=2, padx=[0, 25], sticky='e')
        self.__widgets['update_btn'] = btn

        # Sorting
        options = ['Project - Tool - Test', 'Tool - Project - Test',
                  # 'Tool - Test - Class', # 'Class - Test - Tool', # 'Test - Tool - Class', # 'Test - Class - Tool',
                  ]
        lbl, self.__sorting_dropdown = self.__create_dropdown(parent=config_area, lbl_text='Sorting:', options=options)

        # Filter
        options = ['-', 'Selected', 'Not selected']
        lbl, self.__filter_dropdown = self.__create_dropdown(parent=config_area, lbl_text='Filter:', options=options)

        # Search bar
        lbl, self.__contains_entry = self.__create_entry(parent=config_area, lbl_text='Search:', entry_expand=True)

        # Treeview
        treeview = TreeView(config_area)
        treeview.grid(row=config_area.grid_size()[1], column=0, sticky='new', padx=25, pady=10, columnspan=3)
        self.__widgets['treeview'] = treeview

        self.show_plotter_area(False)

        plotter.labels_changed.connect(self.__labels_changed)
        self.__disable_line_options(True)

        # On resize
        self.bind("<Configure>", self.__on_resize)

    @property
    def export_clicked(self):
        """
        Get signal and connect to it to receive notifications when exports button was clicked.
        :return: signal
        :rtype: Signal
        """
        return self.__export_clicked

    @property
    def timeout(self):
        """
        Get signal and connect to it to receive notifications when a timeout occurred.
        :return: signal
        :rtype: Signal
        """
        return self.__timeout

    @property
    def reset(self):
        """
        Get signal and connect to it to receive notifications when reset button is clicked.
        :return: signal
        :rtype: Signal
        """
        return self.__reset_clicked

    @property
    def widgets(self):
        """
        Get a dictionary with the widgets of this view as values and describing strings as keys.
        :return: dictionary with the widgets of this view as values and describing strings as keys.
        :rtype: Dict{str: tkinter.Widget}
        """
        return self.__widgets.copy()

    @property
    def sorting_dropdown(self):
        """
        Get signal and connect to it to receive notifications when an entry in dropdown for sorting of treeview is
        selected.
        :return: signal
        :rtype: Signal
        """
        return self.__sorting_dropdown

    @property
    def plotter(self):
        """
        Get plotter widget
        :return: plotter widget
        :rtype: Plotter
        """
        return self.__plotter

    @property
    def start_size_entry(self):
        """
        Get entry widget for start size.
        :return: entry widget
        :rtype: Entry
        """
        return self.__start_size_entry

    @property
    def stop_size_entry(self):
        """
        Get entry widget for stop size.
        :return: entry widget
        :rtype: Entry
        """
        return self.__stop_size_entry

    @property
    def num_size_entry(self):
        """
        Get entry widget for number of different model sizes.
        :return: entry widget
        :rtype: Entry
        """
        return self.__num_size_entry

    @property
    def timeout_selected(self):
        """
        Get checkbox widget for timeout on/off.
        :return: checkbox widget
        :rtype: Checkbox
        """
        return self.__timeout_checkbox.status == CheckboxStatus.ACTIVATED

    @property
    def timeout_entry(self):
        """
        Get entry widget for timeout.
        :return: entry widget
        :rtype: Entry
        """
        return self.__timeout_entry.text

    @property
    def skip_btn(self):
        """
        Get skip button.
        :return: skip button
        :rtype: Button
        """
        return self.__skip_btn

    @property
    def timeout_unit_option_menu(self):
        """
        Get dropdown widget for unit of timeout.
        :return: dropdown widget
        :rtype: OptionMenu
        """
        return self.__timeout_unit_option_menu

    @property
    def iterations(self):
        """
        Get text of entry for number of iterations
        :return: text of entry
        :rtype: str
        """
        return self.__iterations_entry.text

    def set_status(self, new_status):
        """
        Set status of run which is shown in view.
        :param new_status: new status
        :type new_status: str
        """
        self.__status_lbl.text = new_status

    def show_progressbar(self):
        """
        Show and start progressbar.
        """
        self.__bar.start(10)
        self.__bar.grid()

    def hide_progressbar(self):
        """
        Hide and stop progressbar.
        """
        self.__bar.grid_remove()
        self.__bar.stop()

    def add_result(self, result):
        """
        Add a new found measurement test to treeview.
        :param result: list of node names to add to tree
        :type result: List[str]
        """
        # make this function thread safe:
        if threading.current_thread() is not threading.main_thread():
            self.after(0, self.add_result, result)
            return
        name = result[0]
        node = self.__widgets['treeview'].add_child(Node, name)
        for c in result[1:]:
            self.__add_tree(node, c)

    def show_plotter_area(self, show=True):
        """
        Show plotter area or show configuration area.
        :param show: True: show plotter. False: show configuration area.
        :type show: bool
        """
        if show:
            self.__config_area.grid_remove()
            self.__results_area.grid()
        else:
            self.__results_area.grid_remove()
            self.__config_area.grid()

    def show_skip_btn(self, show):
        """
        Show or hide skip button.
        :param show: True: show. False: hide
        :type show: bool
        """
        if show:
            self.__skip_btn.grid()
        else:
            self.__skip_btn.grid_remove()

    def update_progress(self, run, total):
        """
        Update progress of run measurements (points). This is shown in info area.
        :param run: number runs done
        :type run: int
        :param total: total number of runs to do
        :type total: int
        """
        if threading.current_thread() is not threading.main_thread():
            self.after(0, self.update_progress, run, total)
            return

        self.__progress_lbl.text = '{} / {}'.format(run, total)
        if total > 0: percent = run * 100 / total
        else: percent = 0
        self.__progress_bar["value"] = percent

    def start_stopwatch(self):
        """
        Start stopwatch.
        """
        self.__start_time_stopwatch = time.time()
        self.__stopwatch_job = self.after(1000, self.__update_stopwatch)
        self.__time_lbl.text = self.__get_time_str(0)

    def stop_stopwatch(self, reset_lbl=None):
        """
        Stop stopwatch and may reset it.
        :param reset_lbl: True: reset label of stopwatch. False: do not reset
        :type reset_lbl: bool
        """
        if self.__stopwatch_job: self.after_cancel(self.__stopwatch_job)
        self.__stopwatch_job = None
        if reset_lbl: self.__time_lbl.text = self.__get_time_str(None)

    def __update_stopwatch(self):
        """
        Update the text of the label of stopwatch.
        """
        if not self.__stopwatch_job: return
        self.__stopwatch_job = self.after(1000, self.__update_stopwatch)

        current_time = time.time()
        elapsed = int(current_time - self.__start_time_stopwatch)

        time_str = self.__get_time_str(elapsed)

        self.__time_lbl.text = time_str

    def start_timeout(self, seconds):
        """
        Start the timeout.
        :param seconds: seconds for timer
        :type seconds: int
        """
        self.__timeout_seconds = seconds
        if not seconds: return
        self.__start_time_timeout = time.time() + seconds
        self.__timeout_job = self.after(800, self.__update_timeout)
        self.__timeout_lbl.text = self.__get_time_str(seconds)

    def pull_up_timeout(self):
        """
        Pull up timeout.
        """
        if self.__timeout_job: self.after_cancel(self.__timeout_job)
        self.__timeout_job = None
        if not self.__timeout_seconds: return
        self.start_timeout(self.__timeout_seconds)

    def stop_timeout(self):
        """
        Stop timeout.
        """
        if self.__timeout_job: self.after_cancel(self.__timeout_job)
        self.__timeout_job = None
        self.__timeout_lbl.text = '-'

    def __update_timeout(self):
        """
        Update text of label of timeout.
        """
        if not self.__timeout_job: return
        self.__timeout_job = self.after(1000, self.__update_timeout)

        current_time = time.time()
        elapsed = int(self.__start_time_timeout - current_time)
        if elapsed <= 0:
            self.stop_timeout()
            self.timeout.emit()
            elapsed = 0

        time_str = self.__get_time_str(elapsed)

        self.__timeout_lbl.text = time_str

    def __get_time_str(self, sec):
        """
        Get the corresponding string of time for stopwatch or timeout.
        :param sec: number of seconds
        :type sec: int
        :return: time as text
        :rtype: str
        """
        if not sec: return '-'

        hours = sec // 3600
        sec %= 3600

        minutes = sec // 60
        seconds = sec % 60

        if hours == 0 and minutes == 0:
            time_str = '{} s'.format(seconds)
        elif hours == 0:
            time_str = '{} min  {} s'.format(minutes, seconds)
        else:
            time_str = '{} h  {} min  {} s'.format(hours, minutes, seconds)

        return time_str

    def __add_tree(self, parent, tree):
        """
        Recursive function to add a tree of nodes to a node.
        :param parent: parent node
        :type parent: Node
        :param tree: list of node names to add
        :type tree: List[str]
        """
        if len(tree) == 0: return
        name = tree[0]
        child = parent.add_child(Node, name)
        for c in tree[1:]:
            self.__add_tree(child, c)

    def __minor_grid_selected(self, status):
        """
        Callback function which is called when checkbox of minor grid is selected.
        :param status: new selection status
        :type status: CheckboxStatus
        """
        if status == CheckboxStatus.ACTIVATED:
            self.__plotter.minor_grid(True)
            self.__major_grid_checkbox.status = status
        elif status == CheckboxStatus.DEACTIVATED:
            self.__plotter.minor_grid(False)
        else:
            log.getLogger(__name__).warning('Unknown checkbox status: {}'.format(status))

    def __major_grid_selected(self, status):
        """
        Callback function which is called when checkbox of major grid is selected.
        :param status: new selection status
        :type status: CheckboxStatus
        """
        if status == CheckboxStatus.ACTIVATED:
            self.__plotter.major_grid(True)
        elif status == CheckboxStatus.DEACTIVATED:
            self.__plotter.major_grid(False)
            self.__minor_grid_checkbox.status = status
        else:
            log.getLogger(__name__).warning('Unknown checkbox status: {}'.format(status))

    def __back_clicked(self):
        """
        Callback function which is called when back button is clicked.
        """
        self.show_plotter_area(False)

    def __to_plotter(self):
        """
        Callback function which is called when "to plotter" button is clicked.
        """
        self.show_plotter_area(True)

    def __labels_changed(self, new_labels):
        """
        Callback function which is called when labels of lines of plotter changed (for updating dropdown options).
        :param new_labels: new list of all line labels
        :type new_labels: List[str]
        """
        dropdown = self.__line_dropdown
        selected_index = dropdown.get_selection_index()
        dropdown.set_options(new_labels)
        if len(new_labels) == 0:
            dropdown.select('', notify=False)
            self.__disable_line_options(True)
        else:
            if selected_index >= len(new_labels): selected_index = 0
            dropdown.select(new_labels[selected_index], notify=True)
            self.__disable_line_options(False)

    def __user_changed_line_label(self, text):
        """
        Callback function which is called when user changed the label/name of line for plotter.
        :param text: new label
        :type text: str
        """
        index = self.__line_dropdown.get_selection_index()
        self.__plotter.set_legend_label(text, index)

    def __user_changed_line(self, index):
        """
        Callback function which is called when user selected a line in dropdown.
        :param index: index of selection in dropdown
        :type index: int
        """
        info = self.__plotter.get_info(index)
        self.__line_label_entry.text = info['label']
        if info['visible']:
            self.__show_line_checkbox.activate(notify=False)
        else:
            self.__show_line_checkbox.deactivate(notify=False)
        self.__line_color_dropdown.select('')
        self.__line_style_dropdown.select('')
        self.__marker_dropdown.select('')

    def __user_changed_visibility(self, status):
        """
        Callback function which is called when user selected checkbox for showing/hiding a line.
        :param status: status of checkbox
        :type status: CheckboxStatus
        """
        index = self.__line_dropdown.get_selection_index()
        if status == CheckboxStatus.ACTIVATED:
            self.__plotter.set_visible(True, index)
        elif status == CheckboxStatus.DEACTIVATED:
            self.__plotter.set_visible(False, index)
        else:
            log.getLogger(__name__).warning('Unknown checkbox status: {}'.format(status))

    def __user_changed_linestyle(self, style):
        """
        Callback function which is called when user changed linestyle.
        :param style: new linestyle
        :type style: str
        """
        index = self.__line_dropdown.get_selection_index()
        self.__plotter.set_linestyle(style, index)

    def __user_changed_linecolor(self, color):
        """
        Callback function which is called when user changed color of line.
        :param color: new color
        :type color: str
        """
        index = self.__line_dropdown.get_selection_index()
        color = 'tab:' + color
        self.__plotter.set_linecolor(color, index)

    def __user_changed_marker(self, marker):
        """
        Callback function which is called when user changed marker of line.
        :param marker: new marker
        :type marker: str
        """
        index = self.__line_dropdown.get_selection_index()
        self.__plotter.set_marker(marker, index)

    def __disable_line_options(self, disable):
        """
        Private utility function to disable dropdown and checkbox if needed.
        :param disable: True: disable. False: enable
        :type disable: bool
        """
        if disable:
            self.__show_line_checkbox.configure(state='disabled')
            self.__line_label_entry.configure(state='disabled')
            self.__line_style_dropdown.configure(state='disabled')
            self.__line_color_dropdown.configure(state='disabled')
            self.__marker_dropdown.configure(state='disabled')
        else:
            self.__show_line_checkbox.configure(state='normal')
            self.__line_label_entry.configure(state='normal')
            self.__line_style_dropdown.configure(state='readonly')
            self.__line_color_dropdown.configure(state='readonly')
            self.__marker_dropdown.configure(state='readonly')

    def __separator_changed(self, new_option):
        """
        Callback function which is called when decimal separator (comma or point) changed.
        :param new_option: new separator as string
        :type new_option: str
        """
        if new_option == 'Comma':
            self.__plotter.set_separator(',')
        elif new_option == 'Point':
            self.__plotter.set_separator('.')
        else:
            log.getLogger(__name__).warning('Unknown option for separator.', stack_info=True)

    @property
    def filter_dropdown(self):
        """
        Get dropdown widget for filter of treeview.
        :return: dropdown widget for filter of treeview
        :rtype: OptionMenu
        """
        return self.__filter_dropdown

    @property
    def contains_entry(self):
        """
        Get entry widget for search in treeview.
        :return: entry widget
        :rtype: Entry
        """
        return self.__contains_entry

    def __on_resize(self, event):
        """
        Callback function which is called when a resize event occurred.
        :param event: resize event
        """
        try:
            btn_height = self.__btn_frame.winfo_height()
            info_height = self.__info_area.winfo_height()
            height = max(event.height - (btn_height + info_height) - 40, 150)
            self.__plotter_area.rowconfigure(self.__plotter_row, minsize=height)
        except tk.TclError:
            return

    def __create_entry(self, *, parent, lbl_text, entry_text=None, entry_expand=False):
        """
        Private function to reduce code redundancy. Creates a label and a entry with different options.
        :param parent: parent widget
        :type parent: tkiner.Frame
        :param lbl_text: text of label
        :type lbl_text: str
        :param entry_text: text of entry
        :type entry_text: str
        :param entry_expand: True: make entry expandable
        :type entry_expand: bool
        :return: label and entry
        :rtype: Label, Entry
        """
        row = parent.grid_size()[1]
        lbl = Label(parent, text=lbl_text, bg='white')
        lbl.grid(row=row, column=0, sticky='w', padx=[25, 20], pady=5)

        entry = Entry(parent)
        if entry_expand:
            columnspan = 2
            padx = [0, 25]
        else:
            columnspan = 1
            padx = 0
        entry.grid(row=row, column=1, sticky='ew', padx=padx, columnspan=columnspan)
        if not entry_text: entry_text = ''
        entry.text = entry_text

        return lbl, entry

    def __create_dropdown(self, *, parent, lbl_text, options):
        """
        Private function to reduce code redundancy. Creates a label and a dropdown with different options.
        :param parent: parent widget
        :type parent: tkinter.Frame
        :param lbl_text: text of label
        :type lbl_text: str
        :param options: list of selectable options of dropdown
        :type options: List[str]
        :return: label and dropdown widget
        :rtype: Label, OptionMenu
        """
        row = parent.grid_size()[1]

        lbl = Label(parent, text=lbl_text, bg='white')
        lbl.grid(row=row, column=0, sticky='w', padx=[25, 20], pady=5)

        option_menu = OptionMenu(parent, options, width=18)
        option_menu.grid(row=row, column=1, sticky="w", columnspan=1)

        return lbl, option_menu

    def __cb_reset_clicked(self):
        """
        Callback function which is clicked when reset button was clicked.
        """
        answer = askokcancel(title='Attention',
                             message="Resetting will delete all measurement results and can't be undone. Proceed?",
                             icon=WARNING)
        if answer:
            self.__reset_clicked.emit()
            self.__title_entry.text = self.__title_entry.text
            self.__x_label_entry.text = 'model size'
            self.__y_label_entry.text = 'time in seconds'
            self.__x_scale_dropdown.select('linear', True)
            self.__y_scale_dropdown.select('linear', True)
            self.__separator_dropdown.select('Point', True)
            self.__minor_grid_checkbox.activate()

    def __import_clicked(self):
        path = filedialog.askopenfilename(title="Select file to import",
                                             filetypes=(("csv files", "*.csv*"), ("excel files", "*.xlsx*")))
        if path == '':
            log.getLogger(__name__).debug('Choosing file to import was canceled.')
        else:
            log.getLogger(__name__).debug('Chosen path: ' + path)
            if path[-3:] == 'csv':
                df = pd.read_csv(path, header=None)
            elif path[-4:] == 'xlsx':
                df = pd.read_excel(path, header=None)
            else:
                log.getLogger(__name__).warning('Unknown format for import.')
                return
            arr = df.values
            start_row = 5

            for col in range(1, arr.shape[1], 2):
                x = arr[start_row:, col]
                x = list(x[np.logical_not(np.isnan(x.astype(np.float)))])
                x = [float(s) for s in x]

                y = arr[start_row:, col + 1]
                y = list(y[np.logical_not(np.isnan(y.astype(np.float)))])
                y = [float(s) for s in y]

                if len(x) != len(y):
                    log.getLogger(__name__).warning('Imported data has different x/y size. Skipping column.')
                    continue

                project = arr[0][col]
                tool = arr[1][col]
                test = arr[2][col]

                lbl = 'Imp: {} - {} - {}'.format(project, tool, test)
                print('Importing {} - {} - {}'.format(project, tool, test))
                if lbl in self.__plotter.identifiers:
                    for i in range(1, 1000):
                        lbl = 'Imp{}: {} - {} - {}'.format(i, project, tool, test)
                        if lbl not in self.__plotter.identifiers: break
                max_i = len(x)
                for i in range(max_i):
                    self.__plotter.add_point(x[i], y[i], lbl, redraw=i == max_i - 1)

