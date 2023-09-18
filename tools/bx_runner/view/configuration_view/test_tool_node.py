from .test_node import TestNode


class TestToolNode(TestNode):
    pass

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
        return super().get_qualified_name(key='tool', dictionary=dictionary)