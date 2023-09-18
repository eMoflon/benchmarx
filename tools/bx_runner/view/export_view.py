import logging as log
import os
import tkinter as tk
from tkinter import filedialog

from util.signal import Signal
from view_util.button import Button
from view_util.entry import Entry
from view_util.label import Label
from view_util.checkbox import Checkbox, CheckboxStatus
from view_util.option_menu import OptionMenu
from view_util.scroll_frame import ScrollFrame


class ExportView(tk.Frame):
    """
    View for exporting results of test cases and performance measurements.
    """

    def __init__(self, parent):
        """
        Create view with its widgets.
        :param parent: parent widget
        :type parent: tkinter.Widget
        """
        super().__init__(parent, bg='white')

        self.__dropdown_width = 18
        self.__entry_width = 35
        self.__show_tests_not_measurements = False

        self.__back_clicked = Signal('back_clicked')
        self.__export_plot = Signal('export_plot')
        self.__export_measurements = Signal('export_measurements')
        self.__export_tests = Signal('export_tests')

        self.columnconfigure(0, weight=1)
        self.rowconfigure(0, weight=0)
        self.rowconfigure(1, weight=1)

        # Create header
        header = tk.Frame(self)
        header.columnconfigure(1, weight=1)
        header.grid(row=0, column=0, sticky='ew')

        btn = self.__back_btn = Button(header, 'Back', hover=True, relief=tk.FLAT, width=10, height=3)
        btn.grid(row=0, column=0, sticky="w", padx=0, pady=0)
        btn.clicked.connect(self.back_clicked.emit)

        btn = self.__export_btn = Button(header, 'Export', hover=True, relief=tk.FLAT, width=10, height=3)
        btn.grid(row=0, column=2, sticky="e", padx=0, pady=0)
        btn.clicked.connect(self.__export_pressed)

        scroll_frame = ScrollFrame(self, bg='white')
        scroll_frame.grid(row=1, column=0, sticky='nsew')

        # Content
        content = tk.Frame(scroll_frame.port, bg='white')
        content.grid(row=0, column=0, sticky='nsew', padx=25, pady=10)
        content.columnconfigure(1, weight=1)

        parent = content
        ### Directory
        row = parent.grid_size()[1]
        lbl = Label(parent, text='Directory', bg='white', font=(None, 12))
        lbl.grid(row=row, column=0, sticky='w', pady=5)

        entry = self.__directory_entry = Entry(parent)
        entry.grid(row=row+1, column=0, sticky="ew", columnspan=2)
        entry.text = os.path.join(os.getcwd(), 'exports')

        btn = Button(parent, text='Choose directory', hover=True, relief=tk.FLAT)
        btn.grid(row=row+2, column=1, sticky="e", pady=[10, 0])
        btn.clicked.connect(self.__ask_directory)

        self.__measurements_widgets = []
        ### Plot
        lbl, checkbox = self.__create_category(parent, 'Plot')
        self.__plot_checkbox = checkbox
        self.__measurements_widgets += [lbl, checkbox]

        # Name entry
        lbl, entry = self.__create_row_entry(parent, 'Name:')
        self.__plot_name_entry = entry
        self.__measurements_widgets += [lbl, entry]

        # Format dropdown
        # formats = ['svg', 'png', 'jpeg', 'jpg', 'pdf', 'eps', 'pgf', 'ps', 'raw', 'rgba', 'svgz', 'tif', 'tiff']
        formats = ['svg', 'png', 'jpeg', 'jpg', 'pdf']
        lbl, dropdown = self.__create_row_dropdown(parent, 'Format:', formats)
        self.__plot_format_dropdown = dropdown
        self.__measurements_widgets += [lbl, dropdown]

        ### Measurements
        lbl, checkbox = self.__create_category(parent, 'Measurements   ')
        self.__measurements_checkbox = checkbox
        self.__measurements_widgets += [lbl, checkbox]

        # Name entry
        lbl, entry = self.__create_row_entry(parent, 'Name:')
        self.__measurements_name_entry = entry
        self.__measurements_widgets += [lbl, entry]

        # Format dropdown
        formats = ['csv', 'xlsx']
        lbl, dropdown = self.__create_row_dropdown(parent, 'Format:', formats)
        self.__measurements_format_dropdown = dropdown
        self.__measurements_widgets += [lbl, dropdown]

        # Source dropdown
        source = ['All', 'Selected']
        lbl, dropdown = self.__create_row_dropdown(parent, 'Source:', source)
        self.__measurements_source_dropdown = dropdown
        self.__measurements_widgets += [lbl, dropdown]

        ### Tests
        self.__tests_widgets = []

        lbl, checkbox = self.__create_category(parent, 'Tests')
        checkbox.grid_forget()
        # self.__tests_checkbox = checkbox
        # self.__tests_widgets += [lbl, checkbox]
        self.__tests_widgets += [lbl]

        # Name entry
        lbl, entry = self.__create_row_entry(parent, 'Name:')
        self.__tests_name_entry = entry
        self.__tests_widgets += [lbl, entry]

        # Format dropdown
        formats = ['txt', 'csv', 'xlsx', 'tex']
        lbl, dropdown = self.__create_row_dropdown(parent, 'Format:', formats)
        self.__tests_format_dropdown = dropdown
        self.__tests_widgets += [lbl, dropdown]

        # Source dropdown
        formats = ['All', 'Selected']
        lbl, dropdown = self.__create_row_dropdown(parent, 'Source:', formats)
        self.__tests_source_dropdown = dropdown
        self.__tests_widgets += [lbl, dropdown]

        # Distinction dropdown
        formats = ['Pass/Fail', 'Expected/Unexpected']
        lbl, dropdown = self.__create_row_dropdown(parent, 'Distinction:', formats)
        self.__distinction_dropdown = dropdown
        self.__tests_widgets += [lbl, dropdown]

        # Presentation dropdown
        formats = ['Tests', 'Categories', 'Projects', 'Metrics']
        lbl, dropdown = self.__create_row_dropdown(parent, 'Presentation:', formats)
        self.__presenation_dropdown = dropdown
        self.__tests_widgets += [lbl, dropdown]
        
        self.show_export_tests()

    @property
    def back_clicked(self):
        """
        Get signal and connect to it to receive notifications if "Back" button was clicked.
        :return: signal
        :rtype: Signal
        """
        return self.__back_clicked

    @property
    def export_plot(self):
        """
        Get signal and connect to it to receive notifications if user selected to export plot.
        :return: signal
        :rtype: Signal
        """
        return self.__export_plot

    @property
    def export_measurements(self):
        """
        Get signal and connect to it to receive notifications if user selected to export measurements.
        :return: signal
        :rtype: Signal
        """
        return self.__export_measurements

    @property
    def export_tests(self):
        """
        Get signal and connect to it to receive notifications if user selected to export test results.
        :return: signal
        :rtype: Signal
        """
        return self.__export_tests

    def show_export_measurements(self):
        """
        Show options for exporting measurements.
        """
        self.__show_tests_not_measurements = False
        for widget in self.__tests_widgets:
            widget.grid_remove()
        for widget in self.__measurements_widgets:
            widget.grid()

    def show_export_tests(self):
        """
        Show options for exporting tests.
        """
        self.__show_tests_not_measurements = True
        for widget in self.__measurements_widgets:
            widget.grid_remove()
        for widget in self.__tests_widgets:
            widget.grid()
        
    def __export_pressed(self):
        """
        React on click on "Export" button
        """
        # Check directory path
        directory = self.__directory_entry.text
        if not os.path.isdir(directory):
            log.getLogger(__name__).warning('Invalid export directory: {}. Export canceled.'.format(directory))
            return

        if not self.__show_tests_not_measurements and self.__plot_checkbox.status == CheckboxStatus.ACTIVATED:
            name = self.__plot_name_entry.text
            if not name: name = 'plot'
            formt = self.__plot_format_dropdown.get_selection()
            path = os.path.join(directory, name + '.' + formt)
            self.export_plot.emit(path)
            
        if not self.__show_tests_not_measurements and self.__measurements_checkbox.status == CheckboxStatus.ACTIVATED:
            name = self.__measurements_name_entry.text
            if not name: name = 'data'
            formt = self.__measurements_format_dropdown.get_selection()
            path = os.path.join(directory, name + '.' + formt)
            source = self.__measurements_source_dropdown.get_selection()
            self.export_measurements.emit(path, source)

        if self.__show_tests_not_measurements:
            name = self.__tests_name_entry.text
            if not name: name = 'tests'
            formt = self.__tests_format_dropdown.get_selection()
            path = os.path.join(directory, name + '.' + formt)
            source = self.__tests_source_dropdown.get_selection()
            distinction = self.__distinction_dropdown.get_selection()
            presentation = self.__presenation_dropdown.get_selection()

            self.export_tests.emit(path, source, distinction, presentation)

    def __ask_directory(self):
        """
        React on click on "Choose directory" button
        """
        dir = filedialog.askdirectory()
        if dir == '':
            log.getLogger(__name__).debug('Directory choosing was canceled.')
        else:
            log.getLogger(__name__).debug('Chosen directory: ' + dir)
            self.__directory_entry.text = dir

    def __create_category(self, parent, name):
        """
        Private function to reduce code redundancy. Creates a label and checkbox widget.
        :param parent: parent widget
        :type parent: tkinter.Widget
        :param name: name of category
        :type name: str
        :return: label widget, checkbox widget
        :rtype: Label, Checkbox
        """
        row = parent.grid_size()[1]

        lbl = Label(parent, text=name, bg='white', font=(None, 12))
        lbl.grid(row=row, column=0, sticky='w', pady=[15, 5])

        checkbox = Checkbox(parent, bg='white')
        checkbox.grid(row=row, column=1, sticky='w', pady=[18, 5])

        return lbl, checkbox

    def __create_row_entry(self, parent, name):
        """
        Private function to reduce code redundancy. Creates a label and entry widget.
        :param parent: parent widget
        :type parent: tkinter.Widget
        :param name: name of option
        :type name: str
        :return: label widget, entry widget
        :rtype: Label, Entry
        """
        row = parent.grid_size()[1]

        lbl = Label(parent, text=name, bg='white')
        lbl.grid(row=row, column=0, sticky='w', pady=5)

        entry = Entry(parent, width=self.__entry_width)
        entry.grid(row=row, column=1, sticky='w')

        return lbl, entry

    def __create_row_dropdown(self, parent, name, entries):
        """
        Private function to reduce code redundancy. Creates a label and dropdown widget.
        :param parent: parent widget
        :type parent: tkinter.Widget
        :param name: name of option
        :type name: str
        :param entries: options for dropdown menu
        :type entries: [str]
        :return: label widget, dropdown widget
        :rtype: Label, OptionMenu
        """
        row = parent.grid_size()[1]

        lbl = Label(parent, text=name, bg='white')
        lbl.grid(row=row, column=0, sticky='w', pady=5)

        dropdown = OptionMenu(parent, entries, width=self.__dropdown_width)
        dropdown.grid(row=row, column=1, sticky='w')

        return lbl, dropdown



