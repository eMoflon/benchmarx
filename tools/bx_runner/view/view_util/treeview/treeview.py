import logging
import tkinter as tk

from view_util.label import Label
from view_util.treeview.node import Node


class TreeView(tk.Frame):

    def __init__(self, parent):
        """
        Treeview widgets holds a tree of nodes and presents them. This class holds only the root nodes. The nodes
        themself hold their child nodes.
        :param parent: parent widget
        :type parent: tkinter.Frame
        """
        super().__init__(parent, bg='white')
        self.columnconfigure(0, weight=1)

        self.__children = {}

        lbl = Label(self, text='No entry. \n\nPress "Update" button to search. \n\nOr change filter settings.', bg='white')
        lbl.grid(row=0, column=0, sticky='ew', padx=10, pady=5)
        self.__empty_lbl = lbl

    # noinspection PyTypeChecker,PyCallingNonCallable
    def add_child(self, child_class, name):
        """
        Add a new node with a name to tree.
        :param child_class: node class
        :type child_class: Node
        :param name: of node
        :type name: str
        :return: initialized node
        :rtype: Node
        """
        if not issubclass(child_class, Node):
            logging.getLogger(__name__).warning('Expected class "Node" for new child of treeview.')
            return
        self.__empty_lbl.grid_remove()
        node = child_class(self, name)
        self.__children[name] = node
        row = self.grid_size()[1]
        node.grid(row=row, column=0, sticky='ew')
        return node

    def reset(self):
        """
        Reset treeview widget. This clears all nodes.
        """
        for name in list(self.__children.keys()):
            self.__children[name].destroy()
            del self.__children[name]
        self.__empty_lbl.grid()

    def update_selections(self):
        """
        Update (redraw) selection status for all nodes of this treeview.
        """
        for child_node in self.__children.values():
            try:
                child_node.update_selection()
            except Exception:
                logging.getLogger(__name__).warning('Error during updating selection of node.', exc_info=True)

    def find_node(self, path):
        """
        Find a node in this treeview.
        :param path: List of node names. First entry determines for which child node name is searched next.
                     When next node is found the first entry of this list will be dropped and recursive search
                     with new list will be started for found node.
        :type path: List[str]
        :return: node specified with path
        :rtype: Node or None
        """
        searched_name = path[0]
        for child_name, child_node in self.__children.items():
            if child_name == searched_name:
                if len(path) == 1: return child_node
                node = child_node.find_node(path[1:])
                if node: return node

    @property
    def child_nodes(self):
        """
        Get all child nodes of this treeview
        :return: child nodes as dictionary with names of nodes as keys and nodes as values.
        :rtype: {str: Node}
        """
        return self.__children.copy()
