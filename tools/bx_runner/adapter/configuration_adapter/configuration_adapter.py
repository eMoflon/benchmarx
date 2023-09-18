from .tests_adapter import TestsAdapter
from .measurements_adapter import MeasurementsAdapter
from tkinter.messagebox import askokcancel, WARNING


class ConfigurationAdapter:
    """
    Adapter for configuration view (configuration view contains tests view and measurements view).
    """
    def __init__(self, model, view):
        """
        Init this adapter and add slots to signals of view and model.
        :param model: model object
        :type model: model.model.Model
        :param view: view object
        :type view: view.view.View
        """
        self.__view = view
        self.__model = model

        self.__start_lbl = 'Start'
        self.__stop_lbl = 'Stop'
        self.__reset_lbl = 'Reset'
        self.__show_reset = False

        cb = self.__show_tests_pressed
        view.configuration_view.widgets['tests_btn'].clicked.connect(cb)

        cb = self.__show_measurements_pressed
        view.configuration_view.widgets['measurements_btn'].clicked.connect(cb)

        cb = self.__junit_runner_ended
        self.__model.junit_runner.ended.connect(cb)

        cb = self.__update_start_stop_lbl
        self.__model.performance_runner.ended.connect(cb)

        # Reaction for "Start" button
        cb = self.__start_btn_pressed
        view.configuration_view.start_btn.clicked.connect(cb)

        # Reaction for "Settings" button
        cb = self.__view.show_settings_view
        view.configuration_view.widgets['settings_btn'].clicked.connect(cb)

        self.__tests_adapter = TestsAdapter(model, view.configuration_view.tests)
        self.__measurements_adapter = MeasurementsAdapter(model, view.configuration_view.measurements)

        cb = self.__view.show_export_measurements
        self.__measurements_adapter.export_clicked.connect(cb)

        cb = self.__view.show_export_tests
        self.__tests_adapter.export_clicked.connect(cb)

    def __start_btn_pressed(self):
        """
        Callback function which is called when start button is pressed.
        """
        start_btn = self.__view.configuration_view.start_btn
        lbl = start_btn.text
        is_showing_tests = self.__view.configuration_view.is_showing_tests

        if lbl == self.__start_lbl:     # Start pressed
            if is_showing_tests: self.__tests_adapter.start()
            else: self.__measurements_adapter.start()
            start_btn.text = self.__stop_lbl
        elif lbl == self.__reset_lbl:   # Reset pressed
            answer = askokcancel(title='Attention',
                                 message="Resetting will delete all results and can't be undone. Proceed?",
                                 icon=WARNING)
            if answer is not True: return

            self.__show_reset = False
            self.__tests_adapter.reset()
            start_btn.text = self.__start_lbl
        else:                           # Stop pressed
            answer = askokcancel(title='Attention',
                                 message="Stop execution?",
                                 icon=WARNING)
            if answer is not True: return

            if is_showing_tests:
                self.__tests_adapter.stop()
                start_btn.text = self.__reset_lbl
            else:
                self.__measurements_adapter.stop()
                start_btn.text = self.__start_lbl

    def __junit_runner_ended(self):
        """
        Callback function which is called when junit runner ends execution of tests.
        """
        self.__show_reset = True
        self.__update_start_stop_lbl()

    def __update_start_stop_lbl(self):
        """
        Updates start/stop label.
        """
        is_showing_tests = self.__view.configuration_view.is_showing_tests
        if is_showing_tests:
            runner = self.__model.junit_runner
        else:
            runner = self.__model.performance_runner

        start_btn = self.__view.configuration_view.start_btn
        if runner.is_running():
            start_btn.text = self.__stop_lbl
        elif is_showing_tests and self.__show_reset:
            start_btn.text = self.__reset_lbl
        else:
            start_btn.text = self.__start_lbl

    def __show_tests_pressed(self):
        """
        Callback function which is called when button to show tests was pressed.
        """
        self.__view.configuration_view.show_tests()
        self.__update_start_stop_lbl()

    def __show_measurements_pressed(self):
        """
        Callback function which is called when button to show measurements was pressed.
        """
        self.__view.configuration_view.show_measurements()
        self.__update_start_stop_lbl()

