import logging as log
import tkinter as tk
from view_util.scroll_frame import ScrollFrame
from view_util.label import Label


class CategoriesView:
    """
    View for showing results differentiated according to their category (batch/incremental and forward/backward).
    Advanced view differentiated results according to Expected/Unexpected Pass/Fail. Normal view only between Pass/Fail.
    """
    def __init__(self, parent):
        """
        Init widgets of this view.
        :param parent: parent widget
        :type parent: tkinter.Frame
        """
        bg = self.__bg = 'white'
        self.__results_area_height = 0
        self.__show_advanced = True
        self.__tools = []

        frame = self.__frame = tk.Frame(parent, bg=bg)
        frame.columnconfigure(1, weight=1)

        ### Category area
        self.__category_area = self.__create_category_area(frame, advanced=False)
        self.__advanced_category_area = self.__create_category_area(frame, advanced=True)

        ### Result area
        scroll_frame = self.__scroll_frame = ScrollFrame(frame, bg=bg, vertical_not_horizontal=False)
        scroll_frame.grid(row=0, column=1, sticky='nsew')

        scroll_frame.bind("<Configure>", self.__on_resize)
        
        result_area = self.__results_area = tk.Frame(scroll_frame.port, bg=bg)
        result_area.grid(row=0, column=0, sticky='nsew')
        result_area.bind("<Configure>", self.__align_rows_right)

        result_area = self.__advanced_results_area = tk.Frame(scroll_frame.port, bg=bg)
        result_area.grid(row=0, column=0, sticky='nsew')
        result_area.bind("<Configure>", self.__align_rows_right)

        self.reset()
        self.show_advanced(True)

    def reset(self, tools=None):
        """
        Reset shown results of view.
        :param tools: list of tool names to show after resetting
        :type tools: List[str]
        """
        if tools is None or len(tools) == 0: tools = ['Tool1', 'Tool2', 'Tool3', 'Tool4', 'Tool5', 'Tool6', 'Tool7']
        self.__tools = tools
        self.__reset_results_area(advanced=False)
        self.__reset_results_area(advanced=True)   
            
    def show_advanced(self, show):
        """
        Switch between showing results differentiated according to Fail/Pass or between Expected/Unexpected (advanced)
        :param show:
        :type show: bool
        """
        self.__show_advanced = show
        if show:
            self.__category_area.grid_remove()
            self.__results_area.grid_remove()
            self.__advanced_results_area.grid()
            self.__advanced_category_area.grid()
        else:
            self.__advanced_category_area.grid_remove()
            self.__advanced_results_area.grid_remove()
            self.__category_area.grid()
            self.__results_area.grid()

    def add(self, tool: str, batch_not_incr: bool, fwd_not_bwd: bool, expected: bool, passed: bool, value: int):
        """
        Add a new result.
        :param tool: tool of result
        :type tool: str
        :param batch_not_incr: batch or incremental test
        :type batch_not_incr: bool
        :param fwd_not_bwd: forward or backward direction
        :type fwd_not_bwd: bool
        :param expected: expected or unexpected result
        :type expected: bool
        :param passed: test case passed of failed
        :type passed: bool
        :param value: number of tests to increment
        :type value: int
        """
        lbl = self.__get_lbl(tool, batch_not_incr, fwd_not_bwd, expected, passed)
        old_value = self.__get_value(lbl)
        self.__set(tool, batch_not_incr, fwd_not_bwd, expected, passed, old_value + value)

        lbl = self.__get_lbl(tool, batch_not_incr, fwd_not_bwd, None, passed)
        old_value = self.__get_value(lbl)
        self.__set(tool, batch_not_incr, fwd_not_bwd, None, passed, old_value + value)

    def __set(self, tool: str, batch_not_incr: bool, fwd_not_bwd: bool, expected: bool, passed: bool, value: int):
        """
        Set a result.
        :param tool: tool of result
        :type tool: str
        :param batch_not_incr: batch or incremental test
        :type batch_not_incr: bool
        :param fwd_not_bwd: forward or backward direction
        :type fwd_not_bwd: bool
        :param expected: expected or unexpected result
        :type expected: bool
        :param passed: test case passed of failed
        :type passed: bool
        :param value: number of tests to set
        :type value: int
        """
        lbl = self.__get_lbl(tool, batch_not_incr, fwd_not_bwd, expected, passed)
        old_value = self.__get_value(lbl)
        lbl.text = str(value)

        # Total
        lbl = self.__get_total_lbl(tool, expected, passed)
        old_total = self.__get_value(lbl)
        lbl.text = str(old_total + (value - old_value))

    def __get_value(self, lbl):
        """
        Get the int value of a label with a number as text. Default 0
        :param lbl: string representation of value
        :type lbl: Label
        :return: value as integer
        :rtype: int
        """
        try:
            return int(lbl.text)
        except ValueError:
            log.getLogger(__name__).warning('Entry is not a valid integer.', exc_info=True)
            return 0
    
    def __get_lbl(self, tool, batch_not_incr: bool, fwd_not_bwd: bool, expected: bool, passed: bool):
        """
        Get the corresponding label for different parameters
        :param tool: tool name
        :type tool: str
        :param batch_not_incr: batch or increment
        :type batch_not_incr: bool
        :param fwd_not_bwd: fwd or bwd
        :type fwd_not_bwd: bool
        :param expected: expected or unexpected
        :type expected: bool
        :param passed: passed of failed
        :type passed: bool
        :return: corresponding label
        :rtype: Label
        """
        col = self.__tools.index(tool)
        row = 1
        if expected is None:
            results_area = self.__results_area
            if not batch_not_incr: row += 4
            if not fwd_not_bwd: row += 2
            if not passed: row += 1
        else:
            results_area = self.__advanced_results_area
            if not batch_not_incr: row += 8
            if not fwd_not_bwd: row += 4
            if not expected: row += 2
            if not passed: row += 1
        
        return results_area.grid_slaves(row=row, column=col)[0]

    def __get_total_lbl(self, tool, expected: bool, passed: bool):
        """
        Get the corresponding label for different parameters of category "total"
        :param tool: tool name
        :type tool: str
        :param expected: expected or unexpected
        :type expected: bool
        :param passed: passed or failed
        :type passed: bool
        :return: corresponding label
        :rtype: Label
        """
        col = self.__tools.index(tool)
        if expected is None:
            results_area = self.__results_area
            row = 9
            if not passed: row += 1
        else:
            results_area = self.__advanced_results_area
            row = 17
            if not expected: row += 2
            if not passed: row += 1

        return results_area.grid_slaves(row=row, column=col)[0]

    def __reset_results_area(self, advanced):
        """
        Reset widget and show default values.
        :param advanced: True: reset advanced view. False: reset normal view
        :type advanced: bool
        """
        tools = self.__tools
        if advanced: result_area = self.__advanced_results_area
        else: result_area = self.__results_area

        min_col = 3
        num_columns_pre = result_area.grid_size()[0]
        result_area.columnconfigure(list(range(0, max(num_columns_pre, min_col))), weight=0)

        for child in result_area.winfo_children():
            child.grid_forget()

        result_area.columnconfigure(list(range(0, max(len(tools), min_col))), weight=1)

        padx = 5
        column = 0
        for tool in tools:
            text = tool
            if text[0] == '[' and text[-1] == ']': text = text[1:-1]
            
            self.__create_lbl(result_area, row=0, column=column, sticky='n', text=text, padx=padx)
            if advanced: num_rows = 20
            else: num_rows = 10
            for row in range(1, num_rows+1):
                self.__create_lbl(result_area, row=row, column=column, sticky='n', text='0', padx=padx)
            column += 1

    def grid(self, **kwargs):
        """
        Wrapper function.
        :param kwargs: are passed to frame widget of this view
        """
        self.__frame.grid(**kwargs)

    def grid_remove(self):
        """
        Wrapper function.
        :param kwargs: are passed to frame widget of this view
        """
        self.__frame.grid_remove()
                
    def __create_category_area(self, parent, *, advanced):
        """
        Init widgets of this view.
        :param parent: parent widget
        :type parent: tkinter.Frame
        :param advanced: True: init advanced view. False: init normal view
        :type advanced: bool
        :return: widget of this view
        :rtype: tkinter.Frame
        """
        bg = self.__bg
        category_area = tk.Frame(parent, bg=bg)
        category_area.grid(row=0, column=0, sticky='nsew')
        category_area.bind("<Configure>", self.__align_rows_left)
        category_pady = 10
        padx = 5
        pady = 1

        # Header
        self.__create_lbl(category_area, row=0, column=0, sticky='nw', text='Category')
        self.__create_lbl(category_area, row=0, column=1, sticky='nw', text='Result', padx=padx, pady=[0, category_pady])

        # Categories
        categories = ['Batch FWD', 'Batch BWD', 'Incr. FWD', 'Incr. BWD', 'Total']
        row = 1
        if advanced:
            for category in categories:
                self.__create_lbl(category_area, row=row, column=0, sticky='nw', text=category)
                self.__create_lbl(category_area, row=row, column=1, sticky='nw', text='Expected pass', padx=padx, pady=pady)
                self.__create_lbl(category_area, row=row+1, column=1, sticky='nw', text='Expected fail', padx=padx, pady=pady)
                self.__create_lbl(category_area, row=row+2, column=1, sticky='nw', text='Unexpected pass', padx=padx, pady=pady)
                self.__create_lbl(category_area, row=row+3, column=1, sticky='nw', text='Unexpected fail', padx=padx, pady=[pady, pady+category_pady])
                row += 4
        else:
            for category in categories:
                self.__create_lbl(category_area, row=row, column=0, sticky='nw', text=category)
                self.__create_lbl(category_area, row=row, column=1, sticky='nw', text='Pass', padx=padx,
                                  pady=pady)
                self.__create_lbl(category_area, row=row + 1, column=1, sticky='nw', text='Fail',
                                  padx=padx, pady=[pady, pady+category_pady])
                row += 2
        return category_area

    def __create_lbl(self, parent, *, text, **kwargs):
        """
        Private function to reduce code redundancy for creating a label.
        :param parent: parent widget
        :type parent: tkinter.Frame
        :param text: text in label
        :type text: str
        :param kwargs: more keyword arguments which are passed to Label constructor
        :return: created label
        :rtype: Label
        """
        lbl = Label(parent, bg=self.__bg)
        lbl.grid(**kwargs)
        lbl.text = text
        return lbl

    def __on_resize(self, event):
        """
        Callback function which is called when a resize event occurred.
        :param event: resize event
        """
        try: self.__scroll_frame.port.columnconfigure(0, minsize=event.width)
        except tk.TclError: return

    def __align_rows_left(self, event=None):
        """
        Callback function which is called when a resize event occurred. Aligns rows on left side
        :param event: resize event
        """
        if self.__show_advanced:
            category_area = self.__advanced_category_area
            results_area = self.__advanced_results_area
        else:
            category_area = self.__category_area
            results_area = self.__results_area

        for i in range(category_area.grid_size()[1]):
            height = category_area.grid_bbox(row=i, column=0)[3]
            results_area.rowconfigure(i, minsize=height)

    def __align_rows_right(self, event=None):
        """
        Callback function which is called when a resize event occurred. Aligns rows on right side
        :param event: resize event
        """
        if self.__show_advanced:
            category_area = self.__advanced_category_area
            results_area = self.__advanced_results_area
        else:
            category_area = self.__category_area
            results_area = self.__results_area

        for i in range(category_area.grid_size()[1]):
            height = results_area.grid_bbox(row=i, column=0)[3]
            category_area.rowconfigure(i, minsize=height)
