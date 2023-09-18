import logging as log
import os

from model.measurements import UpdateStatus
from view_util.checkbox import CheckboxStatus
from view_util.treeview.node import Node
from util.signal import Signal
from tkinter.messagebox import askokcancel, WARNING

class MeasurementsAdapter:
    """
    Adapter for measurements view.
    """
    def __init__(self, model, measurements_view):
        """
        Init this adapter and add slots to signals of measurements view and model.
        :param model: model object
        :type model: model.model.Model
        :param measurements_view: measurements view
        :type measurements_view: view.configuration_view.measurements_view.MeasurementsView
        """
        self.__model = model
        self.__measurements_view = measurements_view
        self.__update_job = None

        self.__export_clicked = Signal('export_clicked')

        ### config performance_finder
        # button starts performance_finder
        cb = self.__find_measurements_pressed
        measurements_view.widgets['update_btn'].clicked.connect(cb)

        cb = self.__new_measurement_found
        model.performance_finder.new_result.connect(cb)

        cb = self.__finder_ended
        model.performance_finder.ended.connect(cb)

        # reaction on update of tests in model
        cb = self.__tests_updated
        model.measurements.updated.connect(cb)

        cb = self.update
        measurements_view.sorting_dropdown.selected.connect(cb)
        measurements_view.filter_dropdown.selected.connect(cb)
        measurements_view.contains_entry.text_changed.connect(self.__update_later)
        measurements_view.contains_entry.bind('<Return>', cb)

        cb = self.__new_result
        model.performance_runner.new_result.connect(cb)

        cb = self.__skip_clicked
        measurements_view.skip_btn.clicked.connect(cb)
        measurements_view.timeout.connect(cb)

        cb = self.reset
        measurements_view.reset.connect(cb)

        cb = self.stop
        self.__model.performance_runner.ended.connect(cb)

        cb = self.export_clicked.emit
        measurements_view.export_clicked.connect(cb)

        cb = self.__status_changed
        self.__model.performance_runner.status_updated.connect(cb)

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
        Start performance measurements.
        """
        runner = self.__model.performance_runner
        runner.stop()
        self.__measurements_view.update_progress(0, 0)

        # Parse iterations fields
        try:
            start = int(self.__measurements_view.start_size_entry.text)
            stop = int(self.__measurements_view.stop_size_entry.text)
            num = int(self.__measurements_view.num_size_entry.text)
            iterations = int(self.__measurements_view.iterations)
        except ValueError:
            log.getLogger(__name__).warning('Invalid entries for model sizes.', exc_info=True)
            return

        if start < 1 or stop < 1 or num < 1:
            log.getLogger(__name__).warning('Model sizes have to be bigger than 0.')
            return

        if stop <= start:
            log.getLogger(__name__).warning('Start model size has to be bigger than end model size.')
            return

        if iterations <= 0:
            log.getLogger(__name__).warning('Number of Iteration must be bigger than 0')
            return

        # Parse timeout
        if self.__measurements_view.timeout_selected:
            try:
                timeout = int(self.__measurements_view.timeout_entry)
            except ValueError:
                log.getLogger(__name__).warning('Invalid timeout. Expecting integer.', exc_info=True)
                return

            # Unit for timeout
            timeout_unit = self.__measurements_view.timeout_unit_option_menu.get_selection()
            if timeout_unit == 's':
                pass
            elif timeout_unit == 'min':
                timeout *= 60
            elif timeout_unit == 'h':
                timeout *= 3600
            else:
                log.getLogger(__name__).warning('Unknown unit for timeout: {}. Using seconds.'.format(timeout_unit))
        else:
            timeout = None

        plotter = self.__measurements_view.plotter
        # plotter.reset()
        self.__measurements_view.show_plotter_area(True)

        runner.reset_tests_to_run()
        df = self.__model.measurements.get_selected_entries()
        for index, row in df.iterrows():
            class_name = row['class']
            tool = row['tool']
            test = row['test']
            project = row['project']
            runner.add_test_to_run(class_name, test, tool, project)

        runner.start(start, stop, num, iterations, jar_path=self.__model.settings.jar_path)

        self.__measurements_view.start_stopwatch()
        self.__measurements_view.start_timeout(timeout)
        self.__measurements_view.set_status(runner.status)
        self.__measurements_view.update_progress(*self.__model.performance_runner.progress)
        self.__measurements_view.show_skip_btn(True)

    def stop(self):
        """
        Stop running performance measurements.
        """
        self.__measurements_view.stop_timeout()
        self.__measurements_view.stop_stopwatch()
        self.__model.performance_runner.stop()
        self.__measurements_view.show_skip_btn(False)
        self.__measurements_view.set_status('Stopped')
        self.__measurements_view.update_progress(*self.__model.performance_runner.progress)

    def reset(self):
        """
        Reset results and view of performance measurements.
        """
        self.__model.performance_runner.stop()
        self.__measurements_view.plotter.reset()
        self.__measurements_view.update_progress(0, 0)
        self.__measurements_view.stop_stopwatch(reset_lbl=True)
        self.__measurements_view.set_status(self.__model.performance_runner.status)
        self.__model.measurements.reset_results()

    def update(self, *args):
        """
        Update all results of performance measurements with data of model.
        """
        df = self.__model.measurements.data
        self.__tests_updated(UpdateStatus.ALL, df)

    def __new_measurement_found(self, new_test):
        """
        Callback function which is called when a new performance measurement test is found by performance finder.
        :param new_test: new found performance test
        """
        df = self.__model.measurements.add_found_test(new_test)
        self.__add_df_to_treeview(df)

    def __find_measurements_pressed(self):
        """
        Callback function which is called when user selected button to find performance measurements tests in JAR-file.
        """
        if self.__model.performance_runner.is_running():
            log.getLogger(__name__).warning('Finding new tests is not possible while running measurements.')
            return

        benchmarx_path = self.__model.settings.benchmarx_path
        if not os.path.isdir(benchmarx_path):
            log.getLogger(__name__).warning('No valid path to Benchmarx project directory: "{}". '.format(benchmarx_path) +
                                            'Please configure in settings.')
            return

        self.__model.performance_finder.start(self.__model.settings.jar_path, benchmarx_path)
        self.__measurements_view.show_progressbar()

    def __finder_ended(self):
        """
        Callback function which is called when performance finder ended search
        """
        self.__measurements_view.hide_progressbar()

    def __tests_updated(self, status, df):
        """
        Callback function which is called when tests of model changed
        :param status: describing what updated
        :type status: UpdateStatus
        :param df: dataframe
        :type df: pandas.DataFrame
        """
        treeview = self.__measurements_view.widgets['treeview']

        if status == UpdateStatus.ALL:
            treeview.reset()
            self.__add_df_to_treeview(df)

        elif status == UpdateStatus.FOUND:
            self.__add_df_to_treeview(df)

    def __add_df_to_treeview(self, df):
        """
        Insert data of a dataframe to treeview
        :param df: dataframe
        :type df: pandas.DataFrame
        """
        treeview = self.__measurements_view.widgets['treeview']

        df = self.__filter(df)
        df = self.__apply_contains(df)

        hierarchy = self.__get_hierarchy()
        node_classes = {'tool': Node, 'test': Node, 'class': Node}

        for index, row in df.iterrows():
            nodes = [treeview]
            for layer in hierarchy:
                identifier = row[layer]
                node = nodes[-1].find_node([identifier])
                if not node: node = nodes[-1].add_child(node_classes[layer], identifier)
                if layer == 'class':
                    header_name = row['project']
                    idx = header_name.rfind('\\')
                    if idx >= 0: header_name = header_name[idx + 1:]
                    header_name = header_name[len('Benchmarx'):]
                    node.set_header_name(header_name)
                nodes.append(node)

            last_node = nodes[-1]
            last_node.selection_changed.connect(self.__selection_status_changed)

            if row['selected'] is True:
                last_node.activate(notify=False)
            else:
                last_node.deactivate(notify=False)

            treeview.update_selections()

    def __get_hierarchy(self):
        """
        Get info about sorting of treeview as list.
        :return: list of sorting order described as strings
        :rtype: List[str]
        """
        sorting = self.__measurements_view.sorting_dropdown.get_selection()
        sorting = sorting.replace('- ', '')
        sorting = sorting.lower()
        sorting = sorting.replace('project', 'class')
        return sorting.split()

    def __selection_status_changed(self, new_status, node):
        """
        Callback function which is called when the selection status of a node changed.
        :param new_status: new selection status of node
        :type new_status: CheckboxStatus
        :param node: node for which selection status changed
        :type node: Node
        """
        node_path = node.get_qualified_name()

        node_path = list(node_path.values())
        hierarchiy = self.__get_hierarchy()
        names = {}
        names[hierarchiy[0]] = node_path[2]
        names[hierarchiy[1]] = node_path[1]
        names[hierarchiy[2]] = node_path[0]

        class_name = names['class']
        tool = names['tool']
        test = names['test']

        if new_status == CheckboxStatus.ACTIVATED:
            self.__model.measurements.update_selected(class_name, tool, test, True)
        elif new_status == CheckboxStatus.DEACTIVATED:
            self.__model.measurements.update_selected(class_name, tool, test, False)

    def __new_result(self, res):
        """
        Callback function which is called when a new result of a performance measurement was measured
        :param res: dictionary describing the result with keys and results as values
        """
        self.__model.measurements.add_point(res)
        self.__measurements_view.pull_up_timeout()
        self.__measurements_view.plotter.add_point(res['x'], res['y'], res['label'])
        self.__measurements_view.update_progress(*self.__model.performance_runner.progress)
        self.__measurements_view.set_status(self.__model.performance_runner.status)

    def __skip_clicked(self):
        """
        Callback function which is called when skip button was clicked.
        """
        if not self.__model.performance_runner.is_running(): return

        answer = askokcancel(title='Attention',
                             message="Continue skipping this measurement?",
                             icon=WARNING)
        if answer is not True: return

        print('Skip')
        self.__measurements_view.pull_up_timeout()
        self.__model.performance_runner.skip()
        self.__measurements_view.update_progress(*self.__model.performance_runner.progress)
        self.__measurements_view.set_status(self.__model.performance_runner.status)

    def __filter(self, df):
        """
        Apply a filter to a dataframe.
        :param df: dataframe
        :type df: pandas.Dataframe
        :return: filtered dataframe
        :rtype: pandas.DataFrame
        """
        option = self.__measurements_view.filter_dropdown.get_selection()
        if option == 'Selected':
            df = df.loc[df['selected'] == True] # '==' not 'is' for dataframe comparison
        elif option == 'Not selected':
            df = df.loc[df['selected'] != True] # '==' not 'is' for dataframe comparison
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
        to_filter = self.__measurements_view.contains_entry.text
        if not to_filter: return df

        to_filter = to_filter.split()

        ### And filter
        for word in to_filter:
            mask = df.loc[:, 'class'].str.contains(word, case=False)
            mask |= df.loc[:, 'tool'].str.contains(word, case=False)
            mask |= df.loc[:, 'test'].str.contains(word, case=False)
            df = df[mask]

        return df

    def __update_later(self, *args):
        """
        Call this function to add a job that will be called in 500 ms and will update treeview.
        """
        if self.__update_job is None:
            self.__update_job = self.__measurements_view.contains_entry.after(500, self.__update_now)

    def __update_now(self):
        """
        Update treeview now and delete reset old update job
        """
        self.__update_job = None
        self.update()

    def __status_changed(self, status):
        """
        Callback function which is called when status of performance measurements execution changed in model.
        :param status: new status
        :type status: str
        """
        self.__measurements_view.set_status(status)
