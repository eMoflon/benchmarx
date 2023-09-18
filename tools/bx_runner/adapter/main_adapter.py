import logging as log

from .configuration_adapter import ConfigurationAdapter
from settings_adapter import SettingsAdapter
from export_adapter import ExportAdapter
from util.signal import Signal


class MainAdapter:
    """
    Class for main adapter.
    """
    def __init__(self, model, view):
        """
        Init adapters.
        :param model: model object
        :type model: model.model.Model
        :param view: view object
        :type view: view.view.View
        """
        self.__model = model
        Signal.set_job_manager(view)
        
        ConfigurationAdapter(model, view)
        SettingsAdapter(model, view)
        ExportAdapter(model, export_view=view.export_view, plotter=view.configuration_view.measurements.plotter)

        # Load current configuration of junit test cases.
        self.__model.tests.load_from_json()

        # Load current configuration of measurements.
        self.__model.measurements.load_from_json()

        self.__window = view.window
        view.window.protocol("WM_DELETE_WINDOW", self.__close_pressed)
        
    def __close_pressed(self):
        """
        Callback function which is called when close was pressed.
        """
        log.getLogger(__name__).info('Close pressed. Preparing exit.')

        # Save current configuration of
        self.__model.settings.safe_as_json()
        self.__model.expectations.safe_as_json()
        self.__model.tests.safe_as_json()
        self.__model.measurements.safe_as_json()

        self.__model.junit_runner.stop()
        self.__model.junit_finder.stop()
        self.__model.performance_runner.stop()
        
        self.__window.after(1, self.__window.destroy)
        
        