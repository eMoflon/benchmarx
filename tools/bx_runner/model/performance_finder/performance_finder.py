import logging as log

from model_util.runner import Runner


class PerformanceFinder(Runner):
    """
    PerformanceFinder runs a java program to search for performance tests in a JAR-file and returns the found tests.
    """

    # noinspection PyMethodOverriding
    def start(self, jar_path, src_path):
        """
        Overrides method in super class Runner. Start finding performance tests.
        :param jar_path: path to JAR file for searching JUnit tests
        :type jar_path: str
        :param src_path: path to Benchmarx directory, needed for java program
        :type src_path: str
        """
        command = ['java', '-jar', '.\\model\\model_util\\JavaUtil.jar', 'PerformanceFinder', jar_path, src_path]
        super().start(command)

    def _extract_results(self, out):
        """
        Overrides method of super class Runner to extract results of output from process.
        :param out: output of process
        :type out: str
        """
        result_identifier = 'Found:'
        if out[:len(result_identifier)] == result_identifier:
            result = out.split()
            num_res = 5
            if len(result) != num_res:
                log.getLogger(__name__).warning('Found result with unknown format: {}'.format(result))
                return

            result_dict = {'class': result[1], 'tool': result[2], 'test': result[3], 'project': result[4]}
            self.new_result.emit(result_dict)
        else:
            print(out, end='')
