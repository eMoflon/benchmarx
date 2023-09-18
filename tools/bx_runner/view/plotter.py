import tkinter as tk
import logging as log
import threading

import matplotlib.pyplot as plt
import matplotlib.ticker as tkr
from matplotlib.backends.backend_tkagg import FigureCanvasTkAgg
from util.signal import Signal


class Plotter(tk.Frame):
    """
    Plotter widget.
    """

    def __init__(self, master):
        """
        Init plotter widget.
        :param master: parent widget
        :type master: tkinter.Frame
        """
        super().__init__(master, bg='white')

        self.__labels_changed = Signal('labels_changed')

        self.__lines = {}
        self.__legend = None
        self.__separator = ','
        self.__ncol = 1
        self.__metric = 'median'

        fig = self.__fig = plt.figure(figsize=(1, 1))
        fig.set_facecolor('white')
        self.__ax = fig.add_subplot(111)
        plt.tight_layout()

        x_ax = self.__ax.get_xaxis()
        x_ax.set_tick_params(pad=1)

        canvas = self.__canvas = FigureCanvasTkAgg(fig, master=self)
        canvas.get_tk_widget().pack(fill=tk.BOTH, expand=True)

        self.__default_formatter_x = self.__ax.xaxis.get_major_formatter()
        self.__default_formatter_y = self.__ax.yaxis.get_major_formatter()

        plt.connect('resize_event', self.__on_resize)

        self.__update_ax_limits()
        self.__on_resize()

        canvas.draw()

    def add_point(self, x, y, label, redraw=True):
        """
        Add a new point to this plot. Label is for identifying the line to which the point corresponds to. Creates new
        line if label not used yet.
        :param x: x value of point
        :type x: float
        :param y: y value of point
        :type y: float
        :param label: for identifying the line to which point corresponds to and show label in legend. Creates new
            line if label not used yet.
        :type label: str
        :param redraw: True: redraw plotter. False: Do not redraw now.
        :type redraw: bool
        """
        # For thread safety
        if threading.current_thread() is not threading.main_thread():
            self.after(0, self.add_point, x, y, label)
            return

        line_info = self.__lines.get(label)
        if line_info is None:
            legend_label = label
            if legend_label[:4] == 'Imp:': legend_label = legend_label[4:]
            line, = self.__ax.plot(x, y, '-', label=legend_label)
            self.__lines[label] = {'line': line, 'label': legend_label, 'visible': True, 'raw_points': {x: [y]}}

            num_lines_visible = 0
            for line in self.__lines.values():
                if line['visible'] is True: num_lines_visible += 1
            if num_lines_visible > 10:
                print('New line in plotter. Line is hidden by default because 10 or more lines are already shown. Show line under "Line settings".')
                self.set_visible(False, len(self.__lines.values())-1) # hide last entry (line of this new point)

            self.__notify_labels_changed()
        else:
            line = line_info['line']
            raw_points = line_info['raw_points']
            if raw_points.get(x) is None: raw_points[x] = []
            raw_points[x].append(y)
            x_data, y_data = self.__apply_metric(raw_points)
            line.set_xdata(x_data)
            line.set_ydata(y_data)

        if redraw:
            self.__update_ax_limits()
            self.__on_resize()
            self.__canvas.draw()

    @property
    def labels_changed(self):
        """
        Get signal and connect to it to receive notifications when labels of lines changed (typically a new line is
        added)
        :return: signal
        :rtype: Signal
        """
        return self.__labels_changed

    @property
    def identifiers(self):
        """
        Get identifiers of lines.
        :return: list of identifiers
        :rtype: List[str]
        """
        return list(self.__lines.keys())

    def set_legend_label(self, new_label, line_index):
        """
        Change label of line for a specific line identified by its index. Label is also used in legend.
        :param new_label: new label
        :type new_label: str
        :param line_index: index of line for which the label should be changed
        :type line_index: int
        """
        lines = list(self.__lines.values())
        lines[line_index]['label'] = new_label
        self.__update_legend()
        self.__canvas.draw()

    def set_visible(self, visible, line_index):
        """
        Set visibility of a line with its index
        :param visible: True if line should be shown else False
        :type visible: bool
        :param line_index: index of line
        :type line_index: int
        """
        line_info = (list(self.__lines.values())[line_index])
        line_info['line'].set_visible(visible)
        line_info['visible'] = visible
        self.__update_ax_limits()
        self.__update_legend()
        self.__canvas.draw()

    def set_linecolor(self, color, index):
        """
        Set color of line.
        :param color: new color
        :type color: str
        :param index: index of line
        :type index: int
        """
        line_info = (list(self.__lines.values())[index])
        line_info['line'].set_color(color)
        self.__update_ax_limits()
        self.__update_legend()
        self.__canvas.draw()

    def set_linestyle(self, style, index):
        """
        Set linestyle of line.
        :param style: new style
        :type style: str
        :param index: index of line
        :type index: int
        """
        line_info = (list(self.__lines.values())[index])
        line_info['line'].set_linestyle(style)
        self.__update_ax_limits()
        self.__update_legend()
        self.__canvas.draw()

    def set_marker(self, marker, index):
        """
        Set marker of line.
        :param style: new marker
        :type style: str
        :param index: index of line
        :type index: int
        """
        line_info = (list(self.__lines.values())[index])
        line_info['line'].set_marker(marker)
        self.__update_ax_limits()
        self.__update_legend()
        self.__canvas.draw()

    def __notify_labels_changed(self):
        """
        Private callback function which is called if labels changed.
        """
        labels = list(self.__lines.keys())
        self.labels_changed.emit(labels)

    def __update_ax_limits(self):
        """
        Private function to update axis limits of the plot
        """
        x_min = None
        x_max = None
        y_min = None
        y_max = None
        for line in self.__lines.values():
            if line['visible'] is not True: continue
            l = line['line']
            x_data = l.get_xdata()
            if x_min is None: x_min = min(x_data)
            else: x_min = min(x_min, min(x_data))
            if x_max is None: x_max = max(x_data)
            else: x_max = max(x_max, max(x_data))

            y_data = l.get_ydata()
            if y_min is None: y_min = min(y_data)
            else: y_min = min(y_min, min(y_data))
            if y_max is None: y_max = max(y_data)
            else: y_max = max(y_max, max(y_data))

        fac = 1.05

        # x lim
        if self.__ax.get_xscale() == 'log':
            if x_min is None or x_min <= 0:
                min_lim = 1
            else:
                min_lim = x_min
        else:
            min_lim = 0

        max_lim = x_max
        if max_lim is None: max_lim = 10

        if min_lim >= max_lim: max_lim = min_lim + 1

        if self.__ax.get_xscale() == 'log':
            self.__ax.set_xlim(min_lim * (2 - fac), fac * max_lim)
        else:
            self.__ax.set_xlim(min_lim, fac * max_lim)

        # y lim
        if self.__ax.get_yscale() == 'log':
            if y_min is None or y_min <= 0:
                min_lim = 0.01
            else:
                min_lim = y_min
        else:
            min_lim = 0

        max_lim = y_max
        if max_lim is None: max_lim = 10
        if max_lim <= min_lim: max_lim = min_lim + 10

        self.__ax.set_ylim((2 - fac) * min_lim, fac * max_lim)

    def set_title(self, new_title):
        """
        Set title of plot.
        :param new_title: new title
        :type new_title: str
        """
        self.__ax.set_title(new_title)
        self.__on_resize()

    def set_x_label(self, new_lbl):
        """
        Set label of x-axis.
        :param new_lbl: new label text
        :type new_lbl: str
        """
        self.__ax.set_xlabel(new_lbl)
        self.__on_resize()

    def set_y_label(self, new_lbl):
        """
        Set label of y-axis.
        :param new_lbl: new label text
        :type new_lbl: str
        """
        self.__ax.set_ylabel(new_lbl)
        self.__on_resize()

    def set_x_scale(self, scale):
        """
        Set scale of x-axis.
        :param scale: 'lin' for linear scale, 'log' for logarithmic scale
        :type scale: str
        """
        plt.xscale(scale)
        self.__update_ax_limits()
        self.__on_resize()

    def set_y_scale(self, scale):
        """
        Set scale of y-axis.
        :param scale: 'lin' for linear scale, 'log' for logarithmic scale
        :type scale: str
        """
        plt.yscale(scale)
        self.__update_ax_limits()
        self.__on_resize()

    def minor_grid(self, show):
        """
        Show or hide minor grid.
        :param show: False if grid should be hidden, True if grid should be hidden.
        :type show: bool
        """
        if show:
            plt.grid(b=True, which='minor')
            plt.minorticks_on()
        else:
            plt.grid(b=None, which='minor')
            plt.minorticks_off()
        self.__canvas.draw()

    def major_grid(self, show):
        """
        Show or hide major grid.
        :param show: False if grid should be hidden, True if grid should be hidden.
        :type show: bool
        """
        if show:
            plt.grid(b=True, which='major')
        else:
            plt.grid(b=None, which='major')
        self.__canvas.draw()

    def reset(self):
        """
        Reset plot.
        """
        self.__lines = {}
        self.__ax.clear()

        self.__canvas.draw()
        self.__notify_labels_changed()

    def save(self, path):
        """
        Save plot as image.
        :param path: path for saving the plot with format of image
        :type path: str
        """
        plt.savefig(path)

    def set_metric(self, metric):
        """
        Set the metric used for points
        :param metric: metric to use ('median' or 'mean')
        :type metric: str
        """
        if metric not in ['median', 'mean']:
            log.getLogger(__name__).warning('Unknown metric: {}'.format(metric))
            return

        self.__metric = metric
        for line_info in self.__lines.values():
            line = line_info['line']
            raw_points = line_info['raw_points']
            x_data, y_data = self.__apply_metric(raw_points)
            line.set_xdata(x_data)
            line.set_ydata(y_data)
        self.__canvas.draw()

    @property
    def supported_formats(self):
        """
        Get supported formats for saving plot as image.
        :return: list fo tuples with names of format and format ending
        :rtype: List[str, str]
        """
        d = self.__canvas.get_supported_filetypes_grouped()
        return tuple(zip(list(d.keys()), list(d.values())))

    def get_info(self, line_index):
        """
        Get info about a line with its index.
        :param line_index: index of line
        :type line_index: int
        :return: dictionary describing the line
        :rtype: Dict[str, matplotlib.lines.Line2D]
        """
        info = list(self.__lines.values())[line_index]
        return info

    def set_separator(self, separator):
        """
        Set separator shown in plot.
        :param separator: ',' or '.'
        :type separator: str
        """
        if separator == ',':
            self.__separator = separator
            formatter = tkr.FuncFormatter(self.__label_formatter)
            self.__ax.xaxis.set_major_formatter(formatter)
            self.__ax.yaxis.set_major_formatter(formatter)
        elif separator == '.':
            self.__separator = separator
            self.__ax.xaxis.set_major_formatter(self.__default_formatter_x)
            self.__ax.yaxis.set_major_formatter(self.__default_formatter_y)
        else:
            log.getLogger(__name__).warning('Unknown separator.')
        self.__on_resize()

    def __on_resize(self, event=None):
        """
        Private function to resize plot
        :param event: ignored
        """
        self.__update_legend()

        plt.subplots_adjust(left=0, right=1, top=1, bottom=0)

        fig_bbox = self.__ax.get_tightbbox(self.__fig.canvas.get_renderer())

        all_bbox = self.__fig.get_window_extent().transformed(self.__fig.dpi_scale_trans.inverted())

        dpi = self.__fig.dpi
        all_width = all_bbox.width * dpi
        all_height = all_bbox.height * dpi
        extend_x = fig_bbox.x0

        extend_y_bottom = fig_bbox.y0

        extend_y_top = 0
        if self.__legend:
            legend_extend = self.__legend.get_window_extent(self.__fig.canvas.get_renderer())
            extend_y_top += legend_extend.height

        title_extend = self.__ax.title.get_window_extent(self.__fig.canvas.get_renderer())
        self.__ax.set_title(self.__ax.get_title(), pad=extend_y_top) # extend_y_top is legend extend
        extend_y_top += title_extend.height

        pad_x = 20
        rel_pad_x = pad_x / all_width

        pad_y_top = 15
        rel_pad_y_top = pad_y_top / all_height

        pad_y_bottom = 30
        rel_pad_y_bottom = pad_y_bottom / all_height

        top = 1 - ((extend_y_top / all_height) + rel_pad_y_top)
        bottom = (-extend_y_bottom / all_width) + rel_pad_y_bottom
        if top < 0.5: top = 0.5
        plt.subplots_adjust(left=(-extend_x / all_width) + rel_pad_x, right=1 - rel_pad_x, top=top, bottom=bottom)

        self.__canvas.draw()

    def __update_legend(self):
        """
        Private function to update legend
        """
        all_bbox = self.__fig.get_window_extent().transformed(self.__fig.dpi_scale_trans.inverted())
        all_width = all_bbox.width * self.__fig.dpi

        if len(self.__ax.lines) < 1: return

        labels = []
        handles = []
        for line in self.__lines.values():
            if line['visible']:
                labels.append(line['label'])
                handles.append(line['line'])
        num_visible_lines = len(labels)

        # Get best number of columns for legend
        self.__ncol += 1    # start with 1 more column for faster convergence of algorithm
        if self.__ncol < 1: self.__ncol = 1
        if self.__ncol > num_visible_lines: self.__ncol = num_visible_lines
        is_smaller = None
        end_flag = False
        while True:
            if self.__ncol <= 0:
                self.__ncol = 1
                end_flag = True
            self.__legend = plt.legend(bbox_to_anchor=(0, 1, 1, 0), loc="lower left", fontsize='small', ncol=self.__ncol, handles=handles,labels=labels)
            legend_extend = self.__legend.get_window_extent(self.__fig.canvas.get_renderer())
            legend_width = legend_extend.x1
            if end_flag: break
            if is_smaller is None: is_smaller = legend_width <= all_width
            if self.__ncol > num_visible_lines: # more columns than lines is useless
                break
            elif is_smaller and not legend_width <= all_width: # ncol - 1 was perfect width
                end_flag = True
                self.__ncol -= 1
            elif not is_smaller and legend_width <= all_width: # legend was too big but now it has perfect size
                break
            elif is_smaller:
                self.__ncol += 1
            else:
                self.__ncol -= 1

    def __label_formatter(self, value, position):
        """
        Change label of ticks if comma is used as decimal separator.
        :param value: value of tick
        :return: new label of tick
        :rtype: str
        """
        as_str = str(value.round(7))
        if '.' in as_str:
            return as_str.rstrip('0').rstrip('.').replace('.', self.__separator)
        else:
            return as_str

    def __apply_metric(self, raw_points):
        """
        Apply metric on points.
        :param raw_points: list of all points
        :type raw_points: List[float]
        :return: new list of points with applied metric
        :rtype: List[float]
        """
        x_data = []
        y_data = []
        for x, y_list in raw_points.items():
            x_data.append(x)
            if self.__metric == 'median':
                sorted_y = sorted(y_list)
                y_data.append(sorted_y[len(sorted_y) // 2])
            elif self.__metric == 'mean':
                mean = 0
                for y in y_list: mean += y
                mean /= len(y_list)
                y_data.append(mean)
            else:
                log.getLogger(__name__).warning('Unknown metric: {}'.format(self.__metric))
                x_data += len(y_list) * [x]
                y_data += y_list

        return x_data, y_data
