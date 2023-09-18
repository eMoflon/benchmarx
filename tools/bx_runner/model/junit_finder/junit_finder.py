import logging as log

from model_util.runner import Runner


class JUnitFinder(Runner):
    """
    JUnitFinder runs a java program to search for JUnit tests in a JAR-file and returns the found tests.
    """

    # noinspection PyMethodOverriding
    def start(self, jar_path, src_path):
        """
        Overrides method in super class Runner. Start finding JUnit tests.
        :param jar_path: path to JAR file for searching JUnit tests
        :type jar_path: str
        :param src_path: path to Benchmarx directory, needed for java program
        :type src_path: str
        """
        command = ['java', '-jar', '.\\model\\model_util\\JavaUtil.jar', 'JUnitFinder', jar_path, src_path]
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
            if len(result) < 5:
                log.getLogger(__name__).warning('Found result with unknown format: {}'.format(result))
            else:
                if len(result) > 5: features = ' '.join(result[5:])
                else: features = ''
                result_dict = {'class': result[1], 'tool': result[2], 'test': result[3], 'project': result[4],
                               'features': features}
                self.new_result.emit(result_dict)
        else:
            print(out, end='')

