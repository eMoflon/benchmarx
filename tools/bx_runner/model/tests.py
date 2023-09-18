import logging as log

import pandas as pd
from enum import Enum, auto

from util.signal import Signal


class Tests:
    """
    Model class representing test cases and their results.
    """
    def __init__(self, model):
        self.__model = model

        self.__updated = Signal('updated')

        # Configure JUnitFinder
        junit_finder = model.junit_finder

        cb = self.__new_search_started
        junit_finder.started.connect(cb)

        self.__df = self.__create_empty_df()

    @property
    def updated(self):
        """
        Get signal and connect to it to receive notifications when data of test cases updated.
        :return: signal
        :rtype: Signal
        """
        return self.__updated

    def notify(self, status, df):
        """
        Notify when data of test cases updated.
        :param status: describing what changed
        :type status: model.tests.UpdateStatus
        :param df: dataframe to forward with notification (will be copied)
        :type df: pandas.core.frame.DataFrame
        """
        self.updated.emit(status, df.copy())

    def safe_as_json(self, path=None):
        """
        Safe dataframe containing test cases data to file.
        :param path: path to save file to. If None: default path will be used
        :type path: str
        """
        if not path: path = 'saves/tests.json'
        try:
            df = self.__df
            df = df.loc[:, ['class', 'tool', 'test', 'project', 'selected', 'features']]
            df.to_json(path)
        except Exception:
            log.getLogger(__name__).warning('Error during saving tests to json:', exc_info=True)

    def load_from_json(self, path=None):
        """
        Load dataframe containing test cases to fail from file.
        :param path: path to load from. If None: default path will be used
        :type path: str
        """
        if not path: path = 'saves/tests.json'
        try:
            loaded_df = pd.read_json(path)
            loaded_df['result'] = None
            loaded_df['error_msg'] = None
        except ValueError:
            log.getLogger(__name__).debug('JSON file for tests not created yet.', exc_info=True)
            return
        except Exception:
            log.getLogger(__name__).warning('Error during loading tests from json:', exc_info=True)
            return

        self.__df = loaded_df
        self.notify(UpdateStatus.ALL, loaded_df)

    def update_selected(self, class_name, tool, test, selected):
        """
        Update if a test case is selected or not.
        :param class_name: qualified name of class which contains test case
        :type class_name: str
        :param tool: tool name
        :type tool: str
        :param test: test name
        :type test: str
        :param selected: True: selected. False: not selected
        :type selected: bool
        """
        df = self.__df
        if selected: selected = True
        else: selected = False
        df.loc[(df['class'] == class_name) & (df['tool'] == tool) & (df['test'] == test), 'selected'] = selected

    def get_selected_entries(self):
        """
        Get all tests which are selected as dataframe.
        :return: dataframe with selected tests
        :rtype: pandas.core.frame.DataFrame
        """
        df = self.__df.loc[self.__df['selected'] == True]
        return df.copy()

    def clear_results(self):
        """
        Clear all results of all test cases.
        """
        self.__df = self.__df.assign(result=None)
        self.__df = self.__df.assign(error_msg=None)
        self.notify(UpdateStatus.RESULT_RESET, self.__df.loc[self.__df['selected'] == True])

    @property
    def data(self):
        """
        Get dataframe with all data of test cases.
        :return: dataframe
        :rtype: pandas.DataFrame
        """
        return self.__df.copy()

    def __create_empty_df(self):
        """
        Create an empty dataframe to store data of test cases inside.
        :return: new empty dataframe
        :rtype: pandas.DataFrame
        """
        return pd.DataFrame(columns=['class', 'tool', 'test', 'project', 'selected', 'features', 'result', 'error_msg'])

    def add_found_test(self, result):
        """
        Add a new found test case to dataframe.
        :param result: found test case described by a dictionary
        :type result: Dict[str, str]
        :return: new dataframe with added test
        :rtype: pandas.core.frame.DataFrame
        """
        result['error_msg'] = None
        result['selected'] = False
        result['result'] = None
        new_df = pd.DataFrame([result])
        self.__df = pd.concat([self.__df, new_df], ignore_index=True)
        return new_df

    def __new_search_started(self):
        """
        Callback function which is called when a new search for test cases in JAR-file by JUnitFinder started.
        """
        # Reset tests
        self.__df = self.__create_empty_df()
        self.notify(UpdateStatus.ALL, self.__df)

    def add_junit_result(self, res):
        """
        Add a new result of a JUnit test.
        :param res: dictionary with describing keys and results as values
        :type res: Dict[str, bool]
        :return: a dataframe containing only the new result
        :rtype: pandas.core.frame.DataFrame
        """
        df = self.__df

        class_name = res['class']
        tool = res['tool']
        test = res['test']
        result = res['success']

        df.loc[(df['class'] == class_name) & (df['tool'] == tool) & (df['test'] == test), 'result'] = result

        error_msg = res['error_msg']
        if error_msg:
            df.loc[(df['class'] == class_name) & (df['tool'] == tool) & (df['test'] == test), 'error_msg'] = error_msg

        return df.loc[(df['class'] == class_name) & (df['tool'] == tool) & (df['test'] == test), :]


class UpdateStatus(Enum):
    """
    Enum which describes what exactly updated for notifications.
    """
    ALL = auto()
    FOUND = auto()
    RESULT = auto()
    RESULT_RESET = auto()
