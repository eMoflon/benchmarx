import logging as log
import tkinter as tk

from view_util.treeview.node import Node
from view_util.label import Label
from util.signal import Signal
from view_util.button import Button


class TestNode(Node):

    def __init__(self, parent, name, lazy_init=False):
        """
        Node for a test case
        :param parent: parent widget
        :type parent: tkinter.Widget
        :param name: name of this node
        :type name: str
        :param lazy_init: if True a lazy initialization is used. This means some widgets will not be created until
                            they are really needed.
        :type lazy_init: bool
        """
        self.__expectation_changed = Signal('expectation_changed')
        self.__lazy_init_done = False

        self.__runs_info = None
        self.__runs_lbl = None

        self.__expectation = None
        self.__expectation_btn = None

        self._content = None

        self.__error_header_lbl = None
        self.__error_msg = None
        self.__error_lbl = None

        super().__init__(parent, name, lazy_init)

        if not lazy_init:
            self._lazy_init() # do lazy init already now

    @property
    def expectation_changed(self):
        """
        Get signal and connect to it to receive notifications if expectation of node changed.
        :return: signal
        :rtype: Signal
        """
        return self.__expectation_changed

    def _lazy_init(self):
        """
        Do lazy initialization of this node.
        """
        super()._lazy_init()

        if self.__lazy_init_done: return
        self.__lazy_init_done = True

        header = self._header

        btn = self.__expectation_btn = Button(header, text='', hover=True, dynamic_fg=False, relief=tk.FLAT)
        btn.grid(row=0, column=2, padx=0, pady=5)
        self.set_expectation(self.__expectation)
        btn.clicked.connect(self.__a_expectation_changed)

        lbl = self.__runs_lbl = Label(header)
        lbl.grid(row=0, column=3, sticky='ew', padx=10, pady=5)
        lbl.grid_remove()

        content = self._content = tk.Frame(header)
        col = header.grid_size()[0]
        row = header.grid_size()[1]
        content.grid(row=row, column=0, sticky='ew', columnspan=col, padx=10)
        content.columnconfigure(1, weight=1)
        content.grid_remove()

        lbl = self.__error_header_lbl = Label(content)
        lbl.grid(row=1, column=0, sticky='w')
        lbl.text = 'Error:'

        lbl = self.__error_lbl = Label(content, wrap=True, justify='left', fg='red')
        lbl.grid(row=2, column=0, sticky='ew', columnspan=2)
        self.set_error_msg(self.__error_msg)

        info = self.__runs_info
        if info:
            self.set_runs_info(num_suc=info[0], num_fails=info[1], num_to_run=info[2], pass_fail_not_expected_unexpected=info[3], expected_pass=info[4])

    def set_runs_info(self, *, num_suc, num_fails, num_to_run, pass_fail_not_expected_unexpected=None, expected_pass=None, notify=True):
        """
        Set information about successful/failed/total tests
        :param num_suc: number succeeded tests
        :type num_suc: int
        :param num_fails: number failed tests
        :type num_fails: int
        :param num_to_run: number total tests
        :type num_to_run: int
        :param pass_fail_not_expected_unexpected: if true distinction is between passes and fails and not between
                                                    expected and unexpected
        :type pass_fail_not_expected_unexpected: bool
        :param expected_pass: if test is expected to pass
        :type expected_pass: bool
        :param notify: if a notification should be emitted that runs info updated
        :type notify: bool
        """
        lbl = self.__runs_lbl
        if num_suc is None or num_fails is None or num_to_run is None:
            self.__runs_info = None
            if lbl: lbl.grid_remove()
            self.show_expectation_btn(True)
            return
        else:
            self.__runs_info = (num_suc, num_fails, num_to_run, pass_fail_not_expected_unexpected, expected_pass)
            if lbl:
                if pass_fail_not_expected_unexpected is not None:
                    if pass_fail_not_expected_unexpected is True and num_suc == 1 and num_fails == 0 and num_to_run == 1:
                        lbl.text = 'Passed'
                        lbl.config(fg='green')
                    elif pass_fail_not_expected_unexpected is True and num_suc == 0 and num_fails == 1 and num_to_run == 1:
                        lbl.text = 'Failed'
                        lbl.config(fg='red')
                    elif pass_fail_not_expected_unexpected is False and num_suc == 1 and num_fails == 0 and num_to_run == 1 and expected_pass is True:
                        lbl.text = 'Expected Pass'
                        lbl.config(fg='green')
                    elif pass_fail_not_expected_unexpected is False and num_suc == 1 and num_fails == 0 and num_to_run == 1 and expected_pass is False:
                        lbl.text = 'Expected Fail'
                        lbl.config(fg='green')
                    elif pass_fail_not_expected_unexpected is False and num_suc == 0 and num_fails == 1 and num_to_run == 1 and expected_pass is False:
                        lbl.text = 'Unexpected Pass'
                        lbl.config(fg='red')
                    elif pass_fail_not_expected_unexpected is False and num_suc == 0 and num_fails == 1 and num_to_run == 1 and expected_pass is True:
                        lbl.text = 'Unexpected Fail'
                        lbl.config(fg='red')
                    else:
                        log.getLogger(__name__).warning('Unknown arguments.', stack_info=True)
                else:
                    lbl.text = str(num_suc) + '/' + str(num_fails) + '/' + str(num_to_run)
                    if num_suc == num_to_run and num_fails == 0 and num_to_run > 0:
                        lbl.config(fg='green')
                    elif num_fails > 0:
                        lbl.config(fg='red')
                    else:
                        lbl.config(fg='black')
                lbl.grid()
                self.show_expectation_btn(False)

        if notify and issubclass(type(self._parent), TestNode):
            return self._parent.__child_runs_info_updated()

    def set_error_msg(self, error_msg):
        """
        Set a error message for this node.
        :param error_msg: error message
        :type error_msg: str
        """
        self.__error_msg = error_msg
        if self.__error_lbl:
            # print('error_msg: {}'.format(error_msg))
            if error_msg:
                error_msg = error_msg.replace('\r\n', '\n')
                self.__error_lbl.text = error_msg
                self.__error_lbl.grid()
                self.__error_header_lbl.grid()
                self._content.grid()
            else:
                self.__error_header_lbl.grid_remove()
                self.__error_lbl.grid_remove()

    def reset_results(self):
        """
        Reset the results and reset view of this node
        """
        if self.__runs_lbl: self.__runs_lbl.grid_remove()
        self.show_expectation_btn(True)
        if self._content: self._content.grid_remove()
        if self.__error_header_lbl: self.__error_header_lbl.grid_remove()
        if self.__error_lbl: self.__error_lbl.grid_remove()
        self.__runs_info = None
        self.__error_msg = None

    def set_expectation(self, expectation):
        """
        Set expectation of this node
        :param expectation: expectation for corresponding testcase
        :type expectation: bool
        """
        lbl = self.__expectation_btn
        if expectation is None:
            self.__expectation = expectation
            if lbl: lbl.grid_remove()
        elif expectation is True:
            self.__expectation = expectation
            if lbl:
                lbl.text = 'Pass\nexpected'
                lbl.config(fg='green')
                lbl.config(activeforeground='green')
        elif expectation is False:
            self.__expectation = expectation
            if lbl:
                lbl.text = 'Fail\nexpected'
                lbl.config(fg='red')
                lbl.config(activeforeground='red')
        else:
            log.getLogger(__name__).warning(
                'Unknown value for expectation (try True, False, None): {}'.format(expectation))

    def show_expectation_btn(self, show):
        """
        Show or hide button for expectations
        :param show: if should be shown or hidden
        :type show: bool
        """
        btn = self.__expectation_btn
        if show:
            if btn: btn.grid()
        else:
            if btn: btn.grid_remove()
        
    def __a_expectation_changed(self):
        """
        Callback function which is called if a expectation changed
        """
        if self.__expectation is True:
            self.__expectation = False
        elif self.__expectation is False:
            self.__expectation = True
        else:
            log.getLogger(__name__).warning('Label was clicked although it should be hidden.')
            return

        self.set_expectation(self.__expectation)
        self.expectation_changed.emit(self.__expectation, self)

    def __child_runs_info_updated(self):
        """
        Callback function which is called if runs info of child node updated
        """
        num_suc = 0
        num_fails = 0
        num_to_run = 0
        for child_node in self._children.values():
            if not child_node.runs_info: continue
            child_num_suc, child_num_fails, child_num_to_run = child_node.runs_info[:3]
            num_suc += child_num_suc
            num_fails += child_num_fails
            num_to_run += child_num_to_run

        self.set_runs_info(num_suc=num_suc, num_fails=num_fails, num_to_run=num_to_run)

    @property
    def runs_info(self):
        """
        Get runs info of this node
        :return: runs info
        :rtype: a tuple with numbers the tests which passed/failed/total
        """
        return self.__runs_info
