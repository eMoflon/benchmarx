import logging as log
import json
import os


class Settings:
    """
    Model class for settings of application.
    """
    def __init__(self):
        self.__benchmarx_path = os.getcwd() + '\\benchmarx\\workspace'
        self.__jar_path = os.getcwd() + '\\benchmarx\\jar\\BX.jar'
        self.load_from_json()

    def safe_as_json(self, path=None):
        """
        Safe dataframe containing settings to file.
        :param path: path to save file to. If None: default path will be used
        :type path: str
        """
        to_save = {'benchmarx_path': self.__benchmarx_path, 'jar_path': self.__jar_path}
        if not path: path = 'saves/settings.json'
        try:
            with open(path, 'w') as f:
                json.dump(to_save, f)
        except Exception:
            log.getLogger(__name__).warning('Error during saving tests to json:', exc_info=True)

    def load_from_json(self, path=None):
        """
        Load dataframe containing settings from file.
        :param path: path to load from. If None: default path will be used
        :type path: str
        """
        if not path: path = 'saves/settings.json'
        try:
            with open(path, 'r') as f:
                loaded = json.load(f)
        except FileNotFoundError:
            log.getLogger(__name__).debug('File with settings not found.', exc_info=True)
            return
        except Exception:
            log.getLogger(__name__).warning('Error during loading tests from json:', exc_info=True)
            return

        self.__benchmarx_path = loaded.get('benchmarx_path')
        if not self.__benchmarx_path: self.__benchmarx_path = os.getcwd() + '\\benchmarx\\workspace'
        self.__jar_path = loaded.get('jar_path')
        if not self.__jar_path: self.__jar_path = os.getcwd() + '\\benchmarx\\jar\\BX.jar'

    @property
    def benchmarx_path(self):
        """
        Get path to directory containing benchmarx project.
        :return: path to directory containing benchmarx project
        :rtype: str
        """
        if self.__benchmarx_path:
            return self.__benchmarx_path
        else:
            return ''

    @benchmarx_path.setter
    def benchmarx_path(self, value):
        """
        Set path to directory containing benchmarx project
        :param value: new path
        :type value: str
        """
        self.__benchmarx_path = value

    @property
    def jar_path(self):
        """
        Get path of jar file.
        :return: path to jar
        :rtype: str
        """
        if self.__jar_path:
            return self.__jar_path
        else:
            return ''

    @jar_path.setter
    def jar_path(self, value):
        """
        Set path to directory containing benchmarx project
        :param value: new path
        :type value: str
        """
        self.__jar_path = value
