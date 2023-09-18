from .test_node import TestNode
from view_util.label import Label


class TestCaseNode(TestNode):

    def __init__(self, *args, **kwargs):
        """
        A node of a treeview which represents a test case
        :param args: arguments which are forwarded to super type TestNode
        :param kwargs: keyword arguments which are forwarded to super type TestNode
        """
        super().__init__(*args, **kwargs)

        self.__features = None
        self.__features_header = None
        self.__features_lbl = None

    def get_qualified_name(self, key=None, dictionary=None):
        """
        Returns qualified name of this node to identify it (as dictionary).
        :param key: the key for the entry in the dictionary. If None name of node will be used
        :type key: str
        :param dictionary: a dictionary in which the qualified name should be stored. If None a new one will be created.
                            The values of the dictionary are the names of the nodes
        :type dictionary: {str: str}
        :return: a dictionary which describes the qualified name of this node.
                The keys of the dictionary may describe the type of node or are simply the names of the nodes.
                The values of the dictionary are the names of the nodes
        :rtype: {str: str}
        """
        return super().get_qualified_name(key='test', dictionary=dictionary)

    def _lazy_init(self):
        """
        Do lazy initialization of this node.
        """
        super()._lazy_init()

        content = self._content

        lbl = self.__features_header = Label(content)
        lbl.grid(row=0, column=0, sticky='nw')
        lbl.text = 'Features:'

        lbl = self.__features_lbl = Label(content, wrap=True, justify='left', anchor='w')
        lbl.grid(row=0, column=1, sticky='new')
        self.set_features(self.__features)

        self.__show_features()

    def set_features(self, features):
        """
        Set features.
        :param features: a string describing the features
        :type features: str
        """
        self.__features = features
        lbl = self.__features_lbl
        if lbl:
            lbl.text = features
            self.__show_features()

    def __show_features(self):
        """
        Show the label with the feature description.
        """
        if self.__features:
            self.__features_header.grid()
            self.__features_lbl.grid()
            self._content.grid()
        else:
            self.__features_header.grid_remove()
            self.__features_lbl.grid_remove()
