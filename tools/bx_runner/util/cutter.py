
class Cutter:

    @staticmethod
    def cut_project(project):
        """
        Utility function to extract project name of complete project path.
        :param project: name of project
        :type project: str
        :return: extracted name of project
        :rtype: str
        """
        idx = project.rfind('\\')
        if idx >= 0: project = project[idx + 1:]
        to_filter = 'Benchmarx'
        if project[0:len(to_filter)] == to_filter: project = project[len(to_filter):]
        return project
