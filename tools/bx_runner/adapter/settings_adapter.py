class SettingsAdapter:
    """
    Adapter for setting.
    """
    # noinspection PyUnresolvedReferences
    def __init__(self, model, view):
        self.__model = model
        self.__view = view

        # Set "back" button reaction
        cb = view.show_config_view
        view.settings_view.widgets['back_btn'].clicked.connect(cb)

        # Set path to .jar file in view
        view.settings_view.set_path(self.__model.settings.jar_path)

        # set callback to update model after change in view
        view.settings_view.path_changed.connect(self.__jar_path_changed)

        # Benchmarx path
        view.settings_view.set_benchmarx_path(model.settings.benchmarx_path)
        view.settings_view.benchmarx_path_changed.connect(self.__benchmarx_path_changed)

    def __jar_path_changed(self, new_path):
        """
        Callback function which is called when jar path changed in model.
        :param new_path: new path
        :type new_path: str
        """
        self.__model.settings.jar_path = new_path

    def __benchmarx_path_changed(self, new_path):
        """
        Callback function which is called when path of benchmarx directory changed in model.
        :param new_path: new path
        :type new_path: str
        """
        self.__model.settings.benchmarx_path = new_path
