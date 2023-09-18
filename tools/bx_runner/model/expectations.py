import logging as log
import pandas as pd


class Expectations:
    """
    Model class storing expected results for JUnit test cases.
    """
    def __init__(self):
        self.__default_save_path = 'saves/expectations.json'
        self.__df = pd.DataFrame(columns=['class', 'tool', 'test']) # dataframe of tests expected to fail
        self.load_from_json()
        
    def set_expectation(self, class_name, tool, test, expectation):
        """
        Set expected results for a test case.
        :param class_name: qualified name of class which contains test case
        :type class_name: str
        :param tool: tool name
        :type tool: str
        :param test: test name
        :type test: str
        :param expectation: expected result
        :type expectation: bool
        """
        df = self.__df
        if expectation: # delete entry
            df = df[(df['class'] != class_name) & (df['tool'] != tool) & (df['test'] != test)]
        else:
            new_row = {'class': class_name, 'tool': tool, 'test': test}
            df = df.append(new_row, ignore_index=True)
        self.__df = df
        
    def get_expectation(self, class_name, tool, test):
        """
        Get expected result of testcase. If no entry is found in dataframe, pass is expected.
        :param class_name: qualified name of class which contains test case
        :type class_name: str
        :param tool: tool name
        :type tool: str
        :param test: test name
        :type test: str
        :return: True: pass expected. False: fail expected
        :rtype: bool
        """
        df = self.__df
        df = df[(df['class'] == class_name) & (df['tool'] == tool) & (df['test'] == test)]
        return df.empty
        
    def safe_as_json(self, path=None):
        """
        Safe dataframe containing test cases to fail to file.
        :param path: path to save file to. If None: default path will be used
        :type path: str
        """
        if not path: path = self.__default_save_path
        try:
            df = self.__df.drop_duplicates()
            df.to_json(path)
        except Exception:
            log.getLogger(__name__).warning('Error during saving expectations to json:', exc_info=True)

    def load_from_json(self, path=None):
        """
        Load dataframe containing test cases to fail from file.
        :param path: path to load from. If None: default path will be used
        :type path: str
        """
        if not path: path = self.__default_save_path
        try:
            self.__df = pd.read_json(path)
        except ValueError:
            log.getLogger(__name__).debug('JSON file for expectations not created yet.', exc_info=True)
        except Exception:
            log.getLogger(__name__).warning('Error during loading expectations from json:', exc_info=True)

    @property
    def df(self):
        """
        Get dataframe which holds all test cases expected to fail.
        :return: dataframe
        :rtype: pandas.DataFrame
        """
        return self.__df.copy()