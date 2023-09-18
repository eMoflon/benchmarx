import logging as log
import tkinter as tk

from view_util.label import Label
from view_util.button import Button
from util.signal import Signal
from view_util.checkbox import Checkbox, CheckboxStatus


class Node:
    """
    A visual representation of a node in a treeview widget.
    """
    def __init__(self, parent, name, lazy_init=False):
        """
        A visual representation of a node in a treeview widget.
        :param parent: parent widget
        :param name: name of node
        :type name: str
        :param lazy_init: if True a lazy initialization is used. This means some widgets will not be created until
                            they are really needed.
        :type lazy_init: bool
        """
        self._parent = parent
        self._children = {}
        self.__name = name
        self.__name_lbl = None
        self.__header_name = None

        self.__node_frame = None
        self.__child_frame = None
        self.__collapse_btn = None
        self.__expand_btn = None
        self.__checkbox = None
        self.__selection_changed = Signal('selection_changed')
        self.__selected = CheckboxStatus.DEACTIVATED

        self.__lazy_init_done = False
        if not lazy_init:
            self._lazy_init() # do lazy init already now
            self.collapse()

    @property
    def selection_changed(self):
        """
        Get signal and connect to it to receive notifications when selection status of node changed.
        :return: signal
        :rtype: Signal
        """
        return self.__selection_changed

    def get_qualified_name(self, *, key=None, dictionary=None):
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
        if not key: key = self.name
        if not dictionary: dictionary = {}
        dictionary[key] = self.name
        if issubclass(type(self._parent), Node):
            return self._parent.get_qualified_name(dictionary=dictionary)
        else:
            return dictionary

    def expand(self):
        """
        Expand this node visually
        """
        row = 0
        for child in self._children.values():
            child._lazy_init()
            child.grid(row=row, column=0, sticky='ew')
            row += 1

        has_children = (len(self._children) > 0)
        if has_children:
            self.__child_frame.grid()
            self.__expand_btn.grid_remove()
            self.__collapse_btn.grid()

    def collapse(self):
        """
        Collapse this node visually
        """
        if self.__child_frame: self.__child_frame.grid_remove()
        if self.__collapse_btn: self.__collapse_btn.grid_remove()

        if not self.__expand_btn: return
        has_children = (len(self._children) > 0)
        if has_children:
            self.__expand_btn.grid()
        else:
            self.__expand_btn.grid_remove()

    # noinspection PyTypeChecker,PyCallingNonCallable
    def add_child(self, child_class, name):
        """
        Add a new child node.
        :param child_class: node class
        :type child_class: Node
        :param name: of node
        :type name: str
        :return: initialized node
        :rtype: Node
        """
        if not issubclass(child_class, Node):
            log.getLogger(__name__).warning('Expected class "Node" for new child of treeview.')
            return
        self.__init_child_frame()
        child = child_class(self, name, lazy_init=True)
        self._children[name] = child
        child.selection_changed.connect(self.__child_checkbox_status_changed)
        self.collapse()
        return child

    def find_node(self, path):
        """
        Find a node in the tree with this node as root.
        :param path: List of node names. First entry determines for which child node name is searched next.
                     When next node is found the first entry of this list will be dropped and recursive search
                     with new list will be started in found node.
        :type path: List[str]
        :return: node specified with path
        :rtype: Node or None
        """
        searched_name = path[0]
        child_node = self._children.get(searched_name)
        if child_node:
            if len(path) == 1: return child_node
            node = child_node.find_node(path[1:])
            if node: return node

    def activate(self, notify=True):
        """
        Select this node
        :param notify: if True a notification signal will be emitted that node status changed
        :type notify: bool
        """
        self.__selected = CheckboxStatus.ACTIVATED
        if self.__checkbox:
            self.__checkbox.activate(notify=False)
        for child in self._children.values():
            child.activate(notify=True)
        if notify:
            self.selection_changed.emit(CheckboxStatus.ACTIVATED, self)

    def deactivate(self, notify=True):
        """
        Deselect this node
        :param notify: if True a notification signal will be emitted that node status changed
        :type notify: bool
        """
        self.__selected = CheckboxStatus.DEACTIVATED
        if self.__checkbox:
            self.__checkbox.deactivate(notify=False)
        for child in self._children.values():
            child.deactivate(notify=True)
        if notify:
            self.selection_changed.emit(CheckboxStatus.DEACTIVATED, self)

    def update_selection(self):
        """
        Update the selection status recursively for the subtree with this node as root
        """
        if len(self._children) == 0: return
        for child_node in self._children.values():
            child_node.update_selection()
        self.__child_checkbox_status_changed(None, node=None)

    def grid(self, **kwargs):
        """
        Wrapper function for this widget (needed because of lazy init)
        :param kwargs: keyword arguments which will be passed on
        """
        self.__init_node_frame()
        self.__node_frame.grid(**kwargs)

    def destroy(self):
        """
        Delete this widget.
        """
        if self.__node_frame: self.__node_frame.destroy()

    def set_header_name(self, name):
        """
        Set new name of this node
        :param name: new name
        :type name: str
        """
        self.__header_name = name
        if self.__name_lbl: self.__name_lbl.text = name

    def __checkbox_clicked(self, status):
        """
        Callback function which is called if status of checkbox changed
        :param status: new status of checkbox
        :type status: CheckboxStatus
        """
        if status == CheckboxStatus.ACTIVATED:
            self.__selected = status
            for child in self._children.values():
                child.activate(notify=True)
        elif status == CheckboxStatus.DEACTIVATED:
            self.__selected = status
            for child in self._children.values():
                child.deactivate(notify=True)
        else:
            log.getLogger(__name__).warning('Unknown status: {}'.format(status))

        self.selection_changed.emit(status, self)

    def __child_checkbox_status_changed(self, status, node):
        """
        Recursive callback function which is called if the status of a child node changed
        :param status: new status
        :type status: CheckboxStatus
        :param node: ignored. Only there to fit parameters of callback
        """
        if status == self.selected: return

        # Update my status
        all_activated = True
        all_deactivated = True
        for child in self._children.values():
            child_status = child.selected
            if child_status == CheckboxStatus.TRISTATE:
                all_activated = False
                all_deactivated = False
                break
            elif child_status == CheckboxStatus.ACTIVATED:
                all_deactivated = False
                if not all_activated: break   # must be tristate
            elif child_status == CheckboxStatus.DEACTIVATED:
                all_activated = False
                if not all_deactivated: break   # must be tristate

        if all_activated and all_deactivated:
            log.getLogger(__name__).warning('Invalid selection status.')
        elif all_deactivated:
            self.__selected = CheckboxStatus.DEACTIVATED
            if self.__checkbox: self.__checkbox.deactivate(notify=False)
        elif all_activated:
            self.__selected = CheckboxStatus.ACTIVATED
            if self.__checkbox: self.__checkbox.activate(notify=False)
        else:
            self.__selected = CheckboxStatus.TRISTATE
            if self.__checkbox: self.__checkbox.tristate(notify=False)

        self.selection_changed.emit(self.__selected, self)

    def _lazy_init(self):
        """
        Do lazy initialization of this node.
        """
        if self.__lazy_init_done: return
        self.__lazy_init_done = True

        self.__init_node_frame()
        self.__node_frame.columnconfigure(1, weight=1)

        header = tk.LabelFrame(self.__node_frame, relief=tk.FLAT)
        header.grid(row=0, column=1, sticky='ew', padx=0, pady=5)
        header.columnconfigure(1, weight=1)
        self._header = header

        size = 25
        frame = tk.Frame(self.__node_frame, width=size, height=size, bg='white')
        frame.grid_propagate(False)
        frame.columnconfigure(0, weight=1)
        frame.rowconfigure(0, weight=1)
        frame.grid(row=0, column=0, padx=[0, 10], pady=10)

        btn = self.__collapse_btn = Button(frame, text='-', relief=tk.GROOVE)
        btn.grid(row=0, column=0, sticky='nsew')
        btn.clicked.connect(self.collapse)

        btn = self.__expand_btn = Button(frame, text='+', relief=tk.GROOVE)
        btn.grid(row=0, column=0, sticky='nsew')
        btn.clicked.connect(self.expand)

        lbl = self.__name_lbl = Label(header, wrap=True, justify=tk.LEFT, anchor='w')
        lbl.grid(row=0, column=1, sticky='ew', padx=10, pady=5)

        if self.__header_name:
            name = self.__header_name
        else:
            name = self.__name
            if len(name) > 2 and name[0] == '[' and name[-1] == ']':
                name = name[1: -1]

            # Process name of node
            idx = name.rfind('\\')
            if idx >= 0: name = name[idx+1:]

            idx = name.rfind('.')
            if idx >= 0: name = name[idx+1:]

            all_to_filter = ['Benchmarx', 'test']
            for to_filter in all_to_filter:
                if len(name) > len(to_filter) and name[:len(to_filter)] == to_filter:
                    name = name[len(to_filter):]

        lbl.text = name

        bg = header['background']
        checkbox = self.__checkbox = Checkbox(header, bg)
        checkbox.grid(row=0, column=4, sticky='e', padx=10)
        checkbox.status = self.__selected
        checkbox.status_changed.connect(self.__checkbox_clicked)

        self.__init_child_frame()
        self.__child_frame.grid(row=1, column=1, sticky='ew', padx=[0, 0], pady=0)
        self.__child_frame.columnconfigure(0, weight=1)

        self.collapse()

    def __init_node_frame(self):
        """
        Init frame of this node
        """
        if self.__node_frame: return
        bg = 'white'
        if isinstance(self._parent, Node):
            self.__node_frame = tk.Frame(self._parent.__child_frame, bg=bg)
        else:
            self.__node_frame = tk.Frame(self._parent, bg=bg)

    def __init_child_frame(self):
        """
        Init parent frame for child nodes
        """
        self.__init_node_frame()
        if not self.__child_frame: self.__child_frame = tk.Frame(self.__node_frame, bg='white')

    @property
    def name(self):
        """
        Get name of this node.
        :return: name
        :rtype: str
        """
        return self.__name

    @property
    def child_nodes(self):
        """
        Get child nodes
        :return: dictionary with names of nodes as keys and nodes as values
        :rtype: {str: Node}
        """
        return self._children.copy()

    @property
    def selected(self):
        """
        Get signal and connect to it to receive notifications if the selection status of this node changed.
        :return: signal
        :rtype: Signal
        """
        return self.__selected
