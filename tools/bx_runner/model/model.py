
from .settings import Settings
from .junit_runner import JUnitRunner
from .junit_finder import JUnitFinder
from .performance_runner import PerformanceRunner
from .performance_finder import PerformanceFinder
from .tests import Tests
from .measurements import Measurements
from .expectations import Expectations

class Model:
    """
    Model class to init other model classes and store instances of them during runtime.
    """
    def __init__(self):
        """
        Init other model classes and store their instances.
        """
        self.__settings = Settings()

        self.__expectations = Expectations()
        
        self.__junit_runner = JUnitRunner()
    
        self.__junit_finder = JUnitFinder()
    
        self.__performance_runner = PerformanceRunner()
        
        self.__performance_finder = PerformanceFinder()

        self.__tests = Tests(self)

        self.__measurements = Measurements(self)


    @property
    def settings(self):
        """
        Get instance of model class Settings.
        :return: model class instance
        :rtype: Settings
        """
        return self.__settings

    @property
    def expectations(self):
        """
        Get instance of model class Expectations.
        :return: model class instance
        :rtype: Expectations
        """
        return self.__expectations
    
    @property
    def junit_runner(self):
        """
        Get instance of model class JUnitRunner.
        :return: model class instance
        :rtype: JUnitRunner
        """
        return self.__junit_runner
    
    @property
    def junit_finder(self):
        """
        Get instance of model class JUnitFinder.
        :return: model class instance
        :rtype: JUnitFinder
        """
        return self.__junit_finder
    
    @property
    def performance_finder(self):
        """
        Get instance of model class PerformanceFinder.
        :return: model class instance
        :rtype: PerformanceFinder
        """
        return self.__performance_finder
    
    @property
    def performance_runner(self):
        """
        Get instance of model class PerformanceRunner.
        :return: model class instance
        :rtype: PerformanceRunner
        """
        return self.__performance_runner

    @property
    def tests(self):
        """
        Get instance of model class Tests.
        :return: model class instance
        :rtype: Tests
        """
        return self.__tests

    @property
    def measurements(self):
        """
        Get instance of model class Measurements.
        :return: model class instance
        :rtype: Measurements
        """
        return self.__measurements
    
    