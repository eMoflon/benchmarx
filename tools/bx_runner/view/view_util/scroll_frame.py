import tkinter as tk
from tkinter import ttk


class AutoScrollBar(ttk.Scrollbar):
    """
    A scrollbar which hides automatically
    """
    def __init__(self, *args, **kwargs):
        """
        A scrollbar which hides automatically
        :param args: arguments for super class initialisation
        :param kwargs: keyword arguments for super class initialisation
        """
        super().__init__(*args, **kwargs)

    def set(self, lo, hi):
        """
        Overrides function to hide scrollbar
        :param lo: lower relative bound of scrollbar
        :type lo: float
        :param hi: higher relative bound of scrollbar
        :type hi: float
        """
        if float(lo) <= 0.0 and float(hi) >= 1.0: self.grid_remove()
        else: self.grid()
        super().set(lo, hi)


class ScrollFrame(tk.Frame):
    """
    A frame that makes all inserted children widgets scrollable.
    """
    def __init__(self, parent, *args, vertical_not_horizontal=True, **kwargs):
        """
        A frame that makes all inserted children widgets scrollable.
        :param parent: parent widget
        :type parent: tkinter.Frame
        :param args: arguments for super class initialisation
        :param vertical_not_horizontal: vertical scrolling for True, horizontal scrolling for False
        :type vertical_not_horizontal: bool
        :param kwargs: keyword arguments for super class initialisation
        """
        super().__init__(parent, *args, **kwargs)

        self.columnconfigure(0, weight=1)
        self.rowconfigure(0, weight=1)
        
        self.__vertical_not_horizontal = vertical_not_horizontal

        self.bind('<Enter>', self.__on_enter)
        self.bind('<Leave>', self.__on_leave)
        
        canvas = self.__canvas = tk.Canvas(self, borderwidth=0, background='white', highlightthickness=0)

        if vertical_not_horizontal: bar = AutoScrollBar(self, orient='vertical', command=canvas.yview)
        else: bar = AutoScrollBar(self, orient='horizontal', command=canvas.xview)
        
        canvas.bind('<Configure>', self.__frame_width)
        if vertical_not_horizontal:
            canvas.configure(yscrollcommand=bar.set, bg='white')
            canvas.grid(row=0, column=0, sticky='nsew')
        else:
            canvas.configure(xscrollcommand=bar.set, bg='white')
            canvas.grid(row=0, column=0, sticky='nsew')
        
        self.__scrollable_frame = tk.Frame(canvas, bg='white')
        if vertical_not_horizontal:
            self.__scrollable_frame.columnconfigure(0, weight=1)
        else:
            self.__scrollable_frame.rowconfigure(0, weight=1)
        self.__scrollable_frame.bind('<Configure>', lambda e: canvas.configure(scrollregion=canvas.bbox('all')))

        if vertical_not_horizontal: bar.grid(row=0, column=1, sticky='ns')
        else: bar.grid(row=1, column=0, sticky='ew')

        self.__canvas_frame = canvas.create_window((0, 0), window=self.__scrollable_frame, anchor='nw')

    @property
    def port(self):
        """
        Get port to add child widgets to this frame to make them scrollable
        :return: port
        :rtype: tkinter.Frame
        """
        return self.__scrollable_frame

    def __frame_width(self, event):
        """
        Callback function is called if frame widget changed to set new width
        :param event: description of occurred event
        """
        if self.__vertical_not_horizontal:
            self.__canvas.itemconfig(self.__canvas_frame, width=event.width)
        else:
            self.__canvas.itemconfig(self.__canvas_frame, height=event.height)

    def __on_enter(self, event):
        """
        Callback function is called if mouse enters frame
        :param event: description of occurred event
        """
        if self.__vertical_not_horizontal:
            self.__canvas.bind_all('<MouseWheel>', self.__mousewheel_event)

    def __on_leave(self, event):
        """
        Callback function is called if mouse leaves frame
        :param event: description of occurred event
        """
        if self.__vertical_not_horizontal:
            self.__canvas.unbind_all('<MouseWheel>')

    def __mousewheel_event(self, event):
        """
        Callback function is called if a mouse scroll event occurs
        :param event: description of occurred event
        """
        self.__canvas.yview_scroll(int(-1 * (event.delta / 120)), 'units')
