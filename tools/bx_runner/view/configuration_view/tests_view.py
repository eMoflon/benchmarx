import logging as log
import tkinter as tk
from tkinter import ttk
import threading
import time

from view_util.scroll_frame import ScrollFrame
from view_util.label import Label
from view_util.option_menu import OptionMenu
from view_util.button import Button
from view_util.treeview.treeview import TreeView
from view_util.entry import Entry
from .categories_view import CategoriesView
from util.signal import Signal


class TestsView(tk.Frame):
    """
    View for selecting and running test cases.
    """
    def __init__(self, master):
        """
        Init widgets.
        :param master: parent widget
        :type master: tkinter.Widget
        """
        super().__init__(master)

        self.columnconfigure(0, weight=1)
        self.rowconfigure(0, weight=1)

        self.__export_pressed_signal = Signal()

        self.__widgets = {}
        self.__progress_total = 0
        self.__progress = 0
        dropdown_width = 17

        self.__start_time_stopwatch = None
        self.__stopwatch_job = None

        scroll_frame = self.__scroll_frame = ScrollFrame(self, bg='white')
        scroll_frame.grid(row=0, column=0, sticky='nsew', padx=0)

        frame = tk.Frame(scroll_frame.port, bg='white')
        frame.grid(row=0, column=0, sticky='nsew', pady=[10, 0])
        frame.columnconfigure(0, weight=1)

        ### Info area
        info_area = self.__info_area = tk.Frame(frame, bg='white')
        info_area.grid(row=frame.grid_size()[1], column=0, sticky='ew', pady=0)
        info_area.columnconfigure(1, weight=1)
        padx = 25

        # Export button
        btn = Button(info_area, 'Export', relief=tk.FLAT, hover=True)
        btn.grid(row=info_area.grid_size()[1], column=1, padx=25, pady=[0, 5], sticky='e')
        btn.clicked.connect(self.__export_pressed_signal.emit)

        # Progress
        row = info_area.grid_size()[1]
        lbl = Label(info_area, text='Progress:', bg='white')
        lbl.grid(row=row, column=0, sticky='w', padx=padx, pady=5)

        progress_frame = tk.Frame(info_area, bg='white')
        progress_frame.grid(row=row, column=1, sticky='ew')
        progress_frame.columnconfigure(0, weight=1)

        progress_bar = self.__progress_bar = ttk.Progressbar(progress_frame, mode='determinate')
        progress_bar.grid(row=0, column=0, sticky='ew')

        lbl = self.__progress_lbl = Label(progress_frame, text='-/-', bg='white')
        lbl.grid(row=0, column=1, padx=[5, 25])

        # Time
        row = info_area.grid_size()[1]
        lbl = Label(info_area, text='Time:', bg='white')
        lbl.grid(row=row, column=0, sticky='w', padx=padx, pady=5)

        lbl = self.__time_lbl = Label(info_area, text='-', bg='white')
        lbl.grid(row=row, column=1, sticky='w')

        # View
        row = info_area.grid_size()[1]
        lbl = Label(info_area, text='View:', bg='white')
        lbl.grid(row=row, column=0, sticky='w', padx=padx, pady=10)

        self.__view_options = ['Treeview', 'Categories']
        option_menu = self.__view_options_menu = OptionMenu(info_area, self.__view_options, width=dropdown_width)
        option_menu.grid(row=row, column=1, sticky="w")
        option_menu.selected.connect(self.__view_option_menu_clicked)

        # Results
        row = info_area.grid_size()[1]
        lbl = Label(info_area, text='Results:', bg='white')
        lbl.grid(row=row, column=0, sticky='w', padx=padx, pady=10)

        self.__result_options = ['JUnit', 'Expected/Unexpected']
        option_menu = self.__result_options_menu = OptionMenu(info_area, self.__result_options, width=dropdown_width)
        option_menu.grid(row=row, column=1, sticky="w")
        option_menu.selected.connect(self.__show_other_results_clicked)

        # Separator
        separator = ttk.Separator(info_area, orient='horizontal')
        separator.grid(row=info_area.grid_size()[1], column=0, sticky='ew', pady=10, columnspan=2)

        # Update area
        update_area = self.__update_area = tk.Frame(frame, bg='white')
        update_area.grid(row=frame.grid_size()[1], column=0, sticky='e')

        bar = self.__bar = ttk.Progressbar(update_area, mode='indeterminate')
        bar.grid(row=0, column=0)
        bar.grid_remove()

        btn = Button(update_area, 'Update', hover=True, relief=tk.FLAT)
        btn.grid(row=0, column=1, padx=25)
        self.__widgets['update_tests_btn'] = btn

        ### Filter area
        filter_area = self.__filter_area = tk.Frame(frame, bg='white')
        filter_area.grid(row=frame.grid_size()[1], column=0, sticky='ew')
        filter_area.columnconfigure(1, weight=1)

        # Sorting
        row = filter_area.grid_size()[1]
        lbl = Label(filter_area, text='Sorting:', bg='white')
        lbl.grid(row=row, column=0, sticky='w', padx=padx, pady=[5, 10])

        self.__sorting_options = [
                                'Project - Tool - Test',
                                'Tool - Project - Test',
                                  # 'Tool - Test - Project',
                                  # 'Project - Test - Tool',
                                  # 'Test - Tool - Project',
                                  # 'Test - Project - Tool',
                                 ]
        option_menu = OptionMenu(filter_area, self.__sorting_options, width=dropdown_width)
        option_menu.grid(row=row, column=1, sticky="w")
        self.__sorting_dropdown = option_menu

        row = filter_area.grid_size()[1]
        lbl = Label(filter_area, text='Filter:', bg='white')
        lbl.grid(row=row, column=0, sticky='w', padx=padx, pady=10)

        filter_options = ['-', 'Selected', 'Not selected', 'Pass expected', 'Fail expected']
        option_menu = self.__filter_dropdown = OptionMenu(filter_area, filter_options, width=dropdown_width)
        option_menu.grid(row=row, column=1, sticky="w")

        # Search bar
        row = filter_area.grid_size()[1]
        lbl = Label(filter_area, text='Search:    ', bg='white')
        lbl.grid(row=row, column=0, sticky='w', padx=padx, pady=[10, 3])

        entry = Entry(filter_area)
        entry.grid(row=row, column=1, sticky='ew', padx=[0, 25], pady=[10, 8])
        self.__contains_entry = entry

        # Result explanation label
        lbl = self.__result_explanation_label = Label(filter_area, bg='white')
        lbl.grid(row=filter_area.grid_size()[1], column=1, sticky='e', padx=25, pady=0)
        lbl.grid_remove()

        row = frame.grid_size()[1]
        # Treeview
        treeview = TreeView(frame)
        treeview.grid(row=row, column=0, sticky='new', padx=25, pady=[0, 15])
        self.__widgets['treeview'] = treeview

        # Categories
        categories_view = self.__categories_view = CategoriesView(frame)
        categories_view.grid(row=row, column=0, sticky='new', padx=25, pady=[10, 15])

        self.show_info_area(False)
        self.show_find_area(True)
        self.show_categories_not_treeview(False)
        self.__result_options_menu.select(self.__result_options[0], notify=True)
        self.show_pass_fail_not_expected_result_explanation_lbl(None)

    def reset_progress(self, total: int):
        """
        Reset progress (before starting new test series)
        :param total: number of total tests to run
        :type total: int
        """
        self.__progress_total = total
        self.__progress = 0
        self.increment_progress(increment=0)

    def increment_progress(self, increment=1):
        """
        Increment progress.
        :param increment: number of tests to increment
        :type increment: int
        """
        if threading.current_thread() is not threading.main_thread():
            self.after(0, self.increment_progress)
            return

        self.__progress += increment
        run = self.__progress
        total = self.__progress_total
        self.__progress_lbl.text = '{} / {}'.format(run, total)
        if total > 0: percent = run * 100 / total
        else: percent = 0
        self.__progress_bar["value"] = percent

    def add_cb_export_pressed(self, cb):
        """
        Add a callback function which is called when export button is pressed.
        :param cb: callback function
        :type cb: method
        """
        self.__export_pressed_signal.connect(cb)

    @property
    def widgets(self):
        """
        Get widgets of this view.
        :return: Dictionary with widgets as values and describing strings as keys
        :rtype: Dict{str: tkinter.Widget}
        """
        return self.__widgets.copy()

    @property
    def sorting_dropdown(self):
        """
        Get dropdown widget for sorting of treeview.
        :return: dropdown widget
        :rtype: OptionMenu
        """
        return self.__sorting_dropdown

    @property
    def filter_dropdown(self):
        """
        Get dropdown widget for filter of treeview.
        :return: dropdown widget
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

    @property
    def categories(self):
        """
        Get categories view widget.
        :return: categories view widget
        :rtype: CategoriesView
        """
        return self.__categories_view

    def show_info_area(self, show):
        """
        Show or hide info area.
        :param show: True: show info area. False: hide info area
        :type show: bool
        """
        if show:
            self.__info_area.grid()
        else:
            self.__info_area.grid_remove()
        self.__scroll_frame.update()

    def show_progressbar(self):
        """
        Show progressbar.
        """
        self.__bar.start(10)
        self.__bar.grid()

    def hide_progressbar(self):
        """
        Hide progressbar.
        """
        self.__bar.grid_remove()
        self.__bar.stop()

    def show_find_area(self, show):
        """
        Show or hide area with button to find JUnit tests.
        :param show: True: area. False: area
        :type show: bool
        """
        if show:
            self.__update_area.grid()
        else:
            self.__update_area.grid_remove()
        self.__scroll_frame.update()

    def show_categories_not_treeview(self, show):
        """
        Show categories view or treeview.
        :param show: True: show categories view. False: show treeview
        :type show: bool
        """
        if show:
            self.__view_options_menu.select(self.__view_options[1])
            self.__widgets['treeview'].grid_remove()
            self.__filter_area.grid_remove()
            self.__categories_view.grid()
        else:
            self.__view_options_menu.select(self.__view_options[0])
            self.__categories_view.grid_remove()
            self.__widgets['treeview'].grid()
            self.__filter_area.grid()
        self.__scroll_frame.update()

    def start_stopwatch(self):
        """
        Start the stopwatch.
        """
        self.__start_time_stopwatch = time.time()
        self.__stopwatch_job = self.after(1000, self.__update_stopwatch)
        self.__time_lbl.text = self.__get_time_str(0)

    def stop_stopwatch(self, reset_lbl=None):
        """
        Stop the stopwatch.
        :param reset_lbl: True: reset time label. False: don't reset
        :type reset_lbl: bool
        """
        if self.__stopwatch_job: self.after_cancel(self.__stopwatch_job)
        self.__stopwatch_job = None
        if reset_lbl: self.__time_lbl.text = self.__get_time_str(None)

    def show_explanation_lbl(self, show):
        """
        Show or hide label for explanation of the results in treeview nodes.
        :param show: True: show. False: hide
        :type show: bool
        """
        lbl = self.__result_explanation_label
        if show is True:
            lbl.grid()
        elif show is False:
            lbl.grid_remove()
        else:
            log.getLogger(__name__).warning('Unknown option for showing explanation label or not: ' + str(show))

    def add_cb_selected_to_pass_fail_not_expected_unexpected_dropdown(self, cb):
        """
        Add a callback function if selection in dropdown changed.
        :param cb: callback function
        :type cb: method
        """
        self.__result_options_menu.selected.connect(cb)

    def show_pass_fail_not_expected_result_explanation_lbl(self, show):
        """
        Show or hide label and different texts on label.
        :param show: True: show 'Pass/Fail/Total'. False: 'Expected/Unexpected/Total'. None: hide label
        :type show: bool
        """
        lbl = self.__result_explanation_label
        if show is True:
            lbl.grid()
            lbl.text = 'Pass/Fail/Total'
            lbl.grid()
        elif show is False:
            lbl.text = 'Expected/Unexpected/Total'
        elif show is None:
            lbl.grid_remove()
        else:
            log.getLogger(__name__).warning('Unknown option: ' + str(show), stack_info=True)

    def __update_stopwatch(self):
        """
        Update label which shows time of stopwatch.
        """
        if not self.__stopwatch_job: return
        self.__stopwatch_job = self.after(1000, self.__update_stopwatch)

        current_time = time.time()
        elapsed = int(current_time - self.__start_time_stopwatch)

        time_str = self.__get_time_str(elapsed)

        self.__time_lbl.text = time_str

    def __get_time_str(self, sec):
        """
        Get time as string.
        :param sec: number seconds
        :type sec: int
        :return: stopwatch time as string
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

    def __view_option_menu_clicked(self, selected_option):
        """
        Private callback function which is called when dropdown for choosing the view (treeview/categories view) is
        selected.
        :param selected_option: selected option in dropdown
        :type selected_option: str
        """
        if selected_option == self.__view_options[0]:      # show Treeview
            self.show_categories_not_treeview(False)
        elif selected_option == self.__view_options[1]:    # show Categories
            self.show_categories_not_treeview(True)
        else:
            log.getLogger(__name__).warning('Unknown option for view: {}'.format(selected_option))

    def __show_other_results_clicked(self, selected_option):
        """
        Private callback function which is called when dropdown for distinction of results is
        selected.
        :param selected_option: selected option in dropdown
        :type selected_option: str
        """
        if selected_option == self.__result_options[0]:
            self.__categories_view.show_advanced(False)
            self.show_pass_fail_not_expected_result_explanation_lbl(True)
        elif selected_option == self.__result_options[1]:
            self.__categories_view.show_advanced(True)
            self.show_pass_fail_not_expected_result_explanation_lbl(False)
        else:
            log.getLogger(__name__).warning('Unknown option for results: {}'.format(selected_option))

    @property
    def show_pass_fail_not_expected_unexpected(self):
        """
        Get if distinction between pass/fail or between expected/unexpected is selected in view.
        :return: True: pass/fail is selected. False: expected/unexpected is selected
        :rtype: bool
        """
        selected_option = self.__result_options_menu.get_selection()
        if selected_option == self.__result_options[1]: return False
        else: return True
