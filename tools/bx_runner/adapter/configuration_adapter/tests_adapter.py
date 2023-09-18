import logging as log
import os

import pandas as pd
from configuration_view.test_case_node import TestCaseNode
from configuration_view.test_tool_node import TestToolNode
from configuration_view.test_class_node import TestClassNode
from configuration_view.test_project_node import TestProjectNode
from view_util.checkbox import CheckboxStatus
from model.tests import UpdateStatus
from util.signal import Signal

class TestsAdapter:
    """
    Adapter for tests view.
    """
    def __init__(self, model, tests_view):
        """
        Init this adapter and add slots to signals of view and model.
        :param model: model object
        :type model: model.model.Model
        :param tests_view: tests view
        :type tests_view: TestsView
        """
        self.__model = model
        self.__tests_view = tests_view
        self.__update_job = None
        self.__to_run = None

        self.__export_clicked = Signal('export_clicked')

        ### config junit_finder
        # button starts junit_finder
        cb = self.__find_junit_pressed
        tests_view.widgets['update_tests_btn'].clicked.connect(cb)

        cb = self.__new_junit_test_found
        model.junit_finder.new_result.connect(cb)

        cb = self.__finder_ended
        model.junit_finder.ended.connect(cb)

        # configure JUnitRunner
        cb = self.__new_junit_result
        model.junit_runner.new_result.connect(cb)

        cb = self.__tests_view.stop_stopwatch
        model.junit_runner.ended.connect(cb)

        # reaction on update of tests in model
        cb = self.__tests_updated
        model.tests.updated.connect(cb)

        cb = self.update
        tests_view.sorting_dropdown.selected.connect(cb)
        tests_view.filter_dropdown.selected.connect(cb)
        tests_view.contains_entry.text_changed.connect(self.__update_later)
        tests_view.contains_entry.bind('<Return>',cb)
        tests_view.add_cb_selected_to_pass_fail_not_expected_unexpected_dropdown(cb)

        cb = self.export_clicked.emit
        tests_view.add_cb_export_pressed(cb)

    @property
    def export_clicked(self):
        """
        Get signal and connect to it to receive notifications when export button was clicked.
        :return: signal
        :rtype: Signal
        """
        return self.__export_clicked

    def start(self):
        """
        Start executing test cases.
        """
        self.__model.junit_runner.stop()
        self.__model.tests.clear_results()
        self.__tests_view.show_find_area(False)
        self.__tests_view.show_info_area(True)
        self.__tests_view.show_explanation_lbl(True)
        #self.__tests_view.show_categories_not_treeview(True)

        selected = self.__model.tests.get_selected_entries()
        self.__to_run = selected.copy()
        self.__tests_view.categories.reset(list(selected.tool.unique()))
        self.__tests_view.reset_progress(selected.shape[0])
        self.__tests_view.start_stopwatch()
        self.__model.junit_runner.start(self.__model.settings.jar_path, selected)

    def stop(self):
        """
        Stop executing test cases.
        """
        self.__model.junit_runner.stop()
        self.__tests_view.stop_stopwatch()

    def reset(self):
        """
        Reset results and information of last run.
        """
        self.__model.tests.clear_results()
        treeview = self.__tests_view.widgets['treeview']
        self.__reset_junit_result_recursive(treeview.child_nodes)
        self.__tests_view.show_find_area(True)
        self.__tests_view.show_info_area(False)
        self.__tests_view.show_categories_not_treeview(False)
        self.__tests_view.show_pass_fail_not_expected_result_explanation_lbl(None)

    def update(self, *args):
        """
        Update test cases with data of model.
        :param args: ignored
        """
        status = UpdateStatus.ALL
        df = self.__model.tests.data
        self.__tests_updated(status, df)

    def __update_later(self, *args):
        """
        Start a timer to run update later.
        :param args: ignored
        """
        if self.__update_job is None:
            self.__update_job = self.__tests_view.contains_entry.after(500, self.__update_now)

    def __update_now(self):
        """
        Update test cases now and reset update job.
        """
        self.__update_job = None
        self.update()

    def __find_junit_pressed(self):
        """
        Callback function which is called when user selected to find JUnit tests.
        """
        if self.__model.junit_runner.is_running():
            log.getLogger(__name__).warning('Finding new tests is not possible while running tests.')
            return

        benchmarx_path = self.__model.settings.benchmarx_path
        if not os.path.isdir(benchmarx_path):
            log.getLogger(__name__).warning('No valid path to Benchmarx project directory: "{}". '.format(benchmarx_path) +
                                            'Please configure in settings.')
            return

        self.__model.junit_finder.start(self.__model.settings.jar_path, benchmarx_path)
        self.__tests_view.show_progressbar()

    def __finder_ended(self):
        """
        Callback function which is called when JUnit finder ended.
        """
        self.__tests_view.hide_progressbar()

    def __tests_updated(self, status, df):
        """
        Callback function which is called when tests in model updated.
        :param status: specifies what exactly updated
        :type status: UpdateStatus
        :param df: dataframe
        :type df: pandas.DataFrame
        """
        treeview = self.__tests_view.widgets['treeview']

        if status == UpdateStatus.ALL:
            treeview.reset()
            self.__add_df_to_treeview(df)

        elif status == UpdateStatus.RESULT_RESET:
            self.__reset_junit_result_recursive(treeview.child_nodes)
            self.__update_df_results(df)

    def __reset_junit_result_recursive(self, nodes):
        """
        Resets results of JUnit test cases in nodes of treeview recursively.
        :param nodes: list of nodes to reset
        :type nodes: List[Node]
        """
        for node in nodes.values():
            self.__reset_junit_result_recursive(node.child_nodes)
            node.reset_results()

    def __update_df_results(self, df):
        """
        Update shown test results in treeview with data of a dataframe
        :param df: dataframe
        :type df: pandas.Dataframe
        """
        hierarchy = self.__get_hierarchy()
        treeview = self.__tests_view.widgets['treeview']
        show_pass_fail = self.__tests_view.show_pass_fail_not_expected_unexpected
        for index, row in df.iterrows():
            node = treeview.find_node([row[hierarchy[0]], row[hierarchy[1]], row[hierarchy[2]], row[hierarchy[3]]])
            if not node:
                log.getLogger(__name__).debug('A node was not found.')
                continue

            expected = self.__model.expectations.get_expectation(row['class'], row['tool'], row['test'])
            res = row['result']
            if (show_pass_fail and res is True) or (not show_pass_fail and res is False and not expected) or (not show_pass_fail and res is True and expected):
                node.set_runs_info(num_suc=1, num_fails=0, num_to_run=1, pass_fail_not_expected_unexpected=show_pass_fail, expected_pass=expected)
            elif (show_pass_fail and res is False) or (not show_pass_fail and res is False and expected) or (not show_pass_fail and res is True and not expected):
                node.set_runs_info(num_suc=0, num_fails=1, num_to_run=1, pass_fail_not_expected_unexpected=show_pass_fail, expected_pass=expected)
            elif res is None:
                node.set_runs_info(num_suc=0, num_fails=0, num_to_run=1)
            else:
                log.getLogger(__name__).warning('Unknown result found.')

            error_msg = row['error_msg']
            node.set_error_msg(error_msg)

    def __add_df_to_treeview(self, df):
        """
        Add entries of a dataframe to treeview.
        :param df: dataframe
        :type df: pandas.Dataframe
        """
        treeview = self.__tests_view.widgets['treeview']

        df = self.__filter(df)
        df = self.__apply_contains(df)

        hierarchy = self.__get_hierarchy()
        node_classes = {'tool': TestToolNode, 'test': TestCaseNode, 'class': TestClassNode, 'project': TestProjectNode}

        for index, row in df.iterrows():
            nodes = [treeview]
            for layer in hierarchy:
                identifier = row[layer]
                node = nodes[-1].find_node([identifier])
                if not node: node = nodes[-1].add_child(node_classes[layer], identifier)
                nodes.append(node)

            last_node = nodes[-1]
            self.__set_expectation(last_node)
            last_node.selection_changed.connect(self.__selection_status_changed)
            last_node.expectation_changed.connect(self.__expectation_changed)

            if row['selected'] is True:
                last_node.activate(notify=False)
            else:
                last_node.deactivate(notify=False)

            expected = self.__model.expectations.get_expectation(row['class'], row['tool'], row['test'])
            show_pass_fail = self.__tests_view.show_pass_fail_not_expected_unexpected
            res = row['result']
            if (show_pass_fail and res is True) or (not show_pass_fail and res is False and not expected) or (not show_pass_fail and res is True and expected):
                last_node.set_runs_info(num_suc=1, num_fails=0, num_to_run=1, pass_fail_not_expected_unexpected=show_pass_fail, expected_pass=expected)
            elif (show_pass_fail and res is False) or (not show_pass_fail and res is False and expected) or (not show_pass_fail and res is True and not expected):
                last_node.set_runs_info(num_suc=0, num_fails=1, num_to_run=1, pass_fail_not_expected_unexpected=show_pass_fail, expected_pass=expected)
            else: #res is None:
                if self.__model.junit_runner.is_running() and self.__get_to_run(row['class'], row['tool'], row['test']):
                    last_node.set_runs_info(num_suc=0, num_fails=0, num_to_run=1)
                else:
                    last_node.set_runs_info(num_suc=None, num_fails=None, num_to_run=None)

            features = row['features']
            last_node.set_features(features)

            error_msg = row['error_msg']
            last_node.set_error_msg(error_msg)

        treeview.update_selections()

    def __new_junit_test_found(self, new_test):
        """
        Callback function which is called when a new test case is found by JUnit finder.
        :param new_test: new found test
        """
        df = self.__model.tests.add_found_test(new_test)
        self.__add_df_to_treeview(df)

    def __new_junit_result(self, res):
        """
        Callback function which is called when a new result of a JUnit test is available.
        :param res: new result as dictionary describing the result
        """
        df = self.__model.tests.add_junit_result(res)
        self.__add_df_to_treeview(df)

        class_name = res['class']
        if '.alignment_based.' in class_name:
            batch_not_incr = False
        elif '.batch.' in class_name:
            batch_not_incr = True
        else:
            log.getLogger(__name__).warning('Unknown category for junit test result: {}'.format(res))
            return

        if '.bwd.' in class_name:
            fwd_not_bwd = False
        elif '.fwd.' in class_name:
            fwd_not_bwd = True
        else:
            log.getLogger(__name__).warning('Unknown category for junit test result: {}'.format(res))
            return

        passed = res['success']
        expected = self.__model.expectations.get_expectation(class_name, res['tool'], res['test'])
        if (passed and expected) or (not passed and not expected):
            expected = True
        else:
            expected = False

        self.__tests_view.categories.add(res['tool'], batch_not_incr, fwd_not_bwd, expected, res['success'], 1)
        self.__tests_view.increment_progress()

    def __filter(self, df):
        """
        Apply a filter to a dataframe.
        :param df: dataframe
        :type df: pandas.Dataframe
        :return: filtered dataframe
        :rtype: pandas.DataFrame
        """
        option = self.__tests_view.filter_dropdown.get_selection()
        if option == 'Selected':
            df = df.loc[df['selected'] == True] # '==' not 'is' for dataframe comparison
        elif option == 'Not selected':
            df = df.loc[df['selected'] != True] # '==' not 'is' for dataframe comparison
        elif option == 'Pass expected':
            df = df.merge(self.__model.expectations.df, indicator=True, how='left', on=['class', 'tool', 'test']).loc[lambda x: x['_merge']!='both']
        elif option == 'Fail expected':
            df = pd.merge(df, self.__model.expectations.df, how='inner', on=['class', 'tool', 'test'])
        elif option == '-':
            pass
        else:
            log.getLogger(__name__).warning('Unknown filter: {}'.format(option))
        return df

    def __apply_contains(self, df):
        """
        Apply filter for words which nodes have to contain to be shown (also known as search in gui).
        :param df: dataframe
        :type df: pandas.Dataframe
        :return: filtered dataframe
        :rtype: pandas.DataFrame
        """
        to_filter = self.__tests_view.contains_entry.text
        if not to_filter: return df

        to_filter = to_filter.split()

        ### Logical AND filter
        for word in to_filter:
            mask = df.loc[:, 'class'].str.contains(word, case=False)
            mask |= df.loc[:, 'tool'].str.contains(word, case=False)
            mask |= df.loc[:, 'test'].str.contains(word, case=False)
            mask |= df.loc[:, 'project'].str.contains('\\Benchmarx' + word, case=False)
            mask |= df.loc[:, 'features'].str.contains(word, case=False)
            df = df[mask]

        return df

    def __get_hierarchy(self):
        """
        Get info about sorting of treeview as list.
        :return: list of sorting order described as strings
        :rtype: List[str]
        """
        sorting = self.__tests_view.sorting_dropdown.get_selection()
        sorting = sorting.replace('- ', '')
        sorting = sorting.lower()
        sorting = sorting.replace('test', 'class test')
        return sorting.split()

    def __selection_status_changed(self, new_status, node):
        """
        Callback function which is called when the selection status of a node changed.
        :param new_status: new selection status of node
        :type new_status: CheckboxStatus
        :param node: node for which selection status changed
        :type node: TestNode
        """
        node_path = node.get_qualified_name()
        class_name = node_path['class']
        tool = node_path['tool']
        test = node_path['test']

        if new_status == CheckboxStatus.ACTIVATED:
            self.__model.tests.update_selected(class_name, tool, test, True)
        elif new_status == CheckboxStatus.DEACTIVATED:
            self.__model.tests.update_selected(class_name, tool, test, False)
        else:
            log.getLogger(__name__).warning('Unknown checkbox status found.')

    def __expectation_changed(self, new_expectation, node):
        """
        Callback function which is called when expectation of test result changed in a node.
        :param new_expectation: True: pass expected. False: fail expected
        :type new_expectation: bool
        :param node: node for which the expectation changed
        :type node: TestNode
        """
        node_path = node.get_qualified_name()
        class_name = node_path['class']
        tool = node_path['tool']
        test = node_path['test']

        expectations = self.__model.expectations
        if new_expectation is True:
            expectations.set_expectation(class_name, tool, test, True)
        elif new_expectation is False:
            expectations.set_expectation(class_name, tool, test, False)
        else:
            log.getLogger(__name__).warning('Unknown expectation found. Try True or False.')

    def __set_expectation(self, node):
        """
        Update the expectation of a node with model data.
        :param node: node to update
        :type node: TestNode
        """
        node_path = node.get_qualified_name()
        class_name = node_path['class']
        tool = node_path['tool']
        test = node_path['test']

        expectations = self.__model.expectations
        if expectations.get_expectation(class_name, tool, test):
            node.set_expectation(True)
        else:
            node.set_expectation(False)

    def __get_to_run(self, class_name, tool, test):
        """
        Get if a specific test is to run or not.
        :param class_name: class name of test
        :type class_name: str
        :param tool: tool name
        :type tool: str
        :param test: test name
        :type test: str
        :return: True: test is to run. False: test is not to run
        :rtype: bool
        """
        df = self.__to_run
        if df is None: return False
        df = df[(df['class'] == class_name) & (df['tool'] == tool) & (df['test'] == test)]
        if len(df) < 1: return False
        if len(df) > 1:
            log.getLogger(__name__).warning('Multiple rows in dataframe found. Expected one row.')
            return False
        for index, row in df.iterrows():
            return row['selected'] is True
        return False

