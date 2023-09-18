import logging as log

import pandas as pd
from enum import Enum, auto

from util.signal import Signal


class Measurements:
    """
    Model class representing performance measurements and their results.
    """
    def __init__(self, model):
        """
        Init this class and set slots for signals in model.
        :param model: model object
        :type model: model.model.Model
        """
        self.__model = model

        self.__updated = Signal('updated')

        # Configure PerformanceFinder
        finder = model.performance_finder

        cb = self.__new_search_started
        finder.started.connect(cb)

        self.__df = self.__create_empty_df()
        
    @property
    def updated(self):
        """
        Get signal and connect to it to receive notifications when data of performance measurements updated.
        :return: signal
        :rtype: Signal
        """
        return self.__updated

    def notify(self, status, df):
        """
        Notify when data of performance measurements updated.
        :param status: describing what changed
        :type status: model.measurements.UpdateStatus
        :param df: dataframe to forward with notification (will be copied)
        :type df: pandas.core.frame.DataFrame
        """
        self.updated.emit(status, df.copy())

    def safe_as_json(self, path=None):
        """
        Safe dataframe containing performance measurements data to file.
        :param path: path to save file to. If None: default path will be used
        :type path: str
        """
        if not path: path = 'saves/measurements.json'
        try:
            df = self.__df.loc[:, ['class', 'tool', 'test', 'project', 'selected']]
            df.to_json(path)
        except Exception:
            log.getLogger(__name__).warning('Error during saving tests to json:', exc_info=True)

    def load_from_json(self, path=None):
        """
        Load dataframe containing performance measurements data from file.
        :param path: path to load from. If None: default path will be used
        :type path: str
        """
        if not path: path = 'saves/measurements.json'
        try:
            loaded_df = pd.read_json(path)
            loaded_df['result'] = None
            loaded_df['result'] = loaded_df['result'].astype('object')
        except ValueError:
            log.getLogger(__name__).debug('JSON file for measurement not created yet.', exc_info=True)
            return
        except Exception:
            log.getLogger(__name__).warning('Error during loading measurements from json:', exc_info=True)
            return

        self.__df = loaded_df
        self.notify(UpdateStatus.ALL, loaded_df)

    def update_selected(self, class_name, tool, test, selected):
        """
        Update if a performance test is selected or not.
        :param class_name: qualified name of class which contains test
        :type class_name: str
        :param tool: tool name
        :type tool: str
        :param test: test name
        :type test: str
        :param selected: True: selected. False: not selected
        :type selected: bool
        """
        df = self.__df
        if selected:
            selected = True
        else:
            selected = False
        df.loc[(df['class'] == class_name) & (df['tool'] == tool) & (df['test'] == test), 'selected'] = selected

    def get_selected_entries(self):
        """
        Get all performance tests which are selected as dataframe.
        :return: dataframe with selected tests
        :rtype: pandas.core.frame.DataFrame
        """
        df = self.__df.loc[self.__df['selected'] == True]
        return df.copy()

    def reset_results(self):
        """
        Reset results of performance measurements.
        """
        self.__df = self.__df.assign(result=None)

    def add_point(self, res):
        """
        Add a new measurement result.
        :param res: dictionary with describing strings as keys and results as values
        :type res: Dict[str, float]
        """
        df = self.__df

        class_name = res['class']
        tool = res['tool']
        test = res['test']
        result = res['x'], res['y']

        mask = (df['class'] == class_name) & (df['tool'] == tool) & (df['test'] == test)
        index = df.loc[mask, :].index.values[0]
        results = df.at[index, 'result']
        if not results or not isinstance(results, list):
            results = []
            df.at[index, 'result'] = results
        results.append(result)

    @property
    def data(self):
        """
        Get dataframe with all data of performance measurements.
        :return: dataframe
        :rtype: pandas.DataFrame
        """
        return self.__df.copy()

    def add_found_test(self, result):
        """
        Add a new found test to dataframe.
        :param result: found test described by a dictionary
        :type result: Dict[str, str]
        :return: new dataframe with added test
        :rtype: pandas.core.frame.DataFrame
        """
        result['selected'] = False
        new_df = pd.DataFrame([result])
        self.__df = pd.concat([self.__df, new_df], ignore_index=True)
        return new_df

    def __new_search_started(self):
        """
        Callback function which is called when a new search for performance measurements tests started.
        """
        # Reset tests
        self.__df = self.__create_empty_df()
        self.notify(UpdateStatus.ALL, self.__df)

    def __create_empty_df(self):
        """
        Create an empty dataframe to store data of performance measurements inside.
        :return: new empty dataframe
        :rtype: pandas.DataFrame
        """
        return pd.DataFrame(columns=['class', 'tool', 'test', 'selected', 'project', 'result'])


class UpdateStatus(Enum):
    """
    Enum which describes what exactly updated for notifications.
    """
    ALL = auto()
    FOUND = auto()
    RESULT = auto()
    RESULT_RESET = auto()
