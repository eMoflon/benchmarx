import logging as log
import pandas as pd
from util.cutter import Cutter

class ExportAdapter:
    """
    Adapter responsible for exporting results of test cases and performance measurements.
    """
    def __init__(self, model, *, export_view, plotter):
        self.__model = model
        self.__export_view = export_view
        self.__plotter = plotter

        cb = self.__export_plot
        export_view.export_plot.connect(cb)

        cb = self.__export_measurements
        export_view.export_measurements.connect(cb)

        cb = self.__export_tests
        export_view.export_tests.connect(cb)

    def __export_plot(self, path):
        """
        Callback function which is called when plot should be exported.
        :param path: path to export to
        :type path: str
        """
        self.__plotter.save(path)

    def __export_measurements(self, path, source):
        """
        Callback function which is called when performance measurements should be exported.
        :param path: path to export to
        :type path: str
        :param source: option for source of data: 'All', 'Selected', 'Plot'
        :type source: str
        """
        sources = ['All', 'Selected', 'Plot']
        if source not in sources:
            log.getLogger(__name__).warning('Invalid source argument: {}. Expected: {}'.format(source, sources))
            return

        df = self.__model.measurements.data

        columns = [['project:', 'tool:', 'test:']]
        for index, row in df.iterrows():
            if source == 'Selected' and row['selected'] is not True: continue
            project = row['project']
            tool = row['tool']
            test = row['test']
            results = row['result']
            if not results or not isinstance(results, list): continue

            project = Cutter.cut_project(project)

            new_columns = list(map(list, zip(*results)))
            columns.append([project, tool, test, None, 'size'] + new_columns[0])
            columns.append([None, None, None, None, 'time'] + new_columns[1])

        if len(columns) <= 1:
            log.getLogger(__name__).warning('No data to export.', stack_info=True)
            return

        max_size = 0
        for col in columns:
            max_size = max(max_size, len(col))

        for col in columns:
            if len(col) < max_size: col += (max_size - len(col)) * [None]

        df = pd.DataFrame(columns)
        df = df.transpose()

        if path[-4:] == 'xlsx':
            df.to_excel(path, index=False, header=False)
        elif path[-3:] == 'csv':
            df.to_csv(path, index=False, header=False)
        else:
            log.getLogger(__name__).warning('Invalid format: {}. Expected .xlsx or .csv'.format(path))

    def __export_tests(self, path, source, distinction, presentation):
        """
        Callback function which is called when results of test cases should be exported.
        :param path: path to export to
        :type path: str
        :param source: option for source of data: 'All', 'Selected'
        :type source: str
        :param distinction: option for distinction of results: 'Expected/Unexpected' or 'Pass/Fail'
        :type distinction: str
        :param presentation: option for presentation of results: 'Tests' 'Categories' 'Projects' 'Metrics'
        :type presentation: str
        """
        if source == 'All':
            df = self.__model.tests.data
        elif source == 'Selected':
            df = self.__model.tests.get_selected_entries()
        else:
            msg = 'Unknown option for source of exported Tests. Expected "All" or "Selected". Got "{}".'.format(source)
            log.getLogger(__name__).warning(msg, stack_info=True)
            return

        if presentation == 'Tests':
            self.__export_tests_results(df, path, distinction)
        elif presentation == 'Categories':
            self.__export_categories(df, path, distinction, categories_not_projects=True)
        elif presentation == 'Projects':
            self.__export_categories(df, path, distinction, categories_not_projects=False)
        elif presentation == 'Metrics':
            self.__export_metrics(df, path)
        else:
            msg = 'Unknown option for presentation of exported Tests. Expected "Tests" or "Categories". Got "{}".'.format(presentation)
            log.getLogger(__name__).warning(msg, stack_info=True)
            return

    def __export_tests_results(self, df, path, distinction):
        """
        Export test results of a dataframe.
        :param df: dataframe
        :type df: pandas.DataFrame
        :param path: path to export to
        :type path: str
        :param distinction: option for distinction of results: 'Expected/Unexpected' or 'Pass/Fail'
        :type distinction: str
        """
        rows = [['Problem', 'Tool', 'Class', 'Test', 'Result']]
        for index, row in df.iterrows():
            project = row['project']
            tool = row['tool']
            test = row['test']
            class_name = row['class']
            result = row['result']
            if not (result is True or result is False): continue

            if distinction == 'Pass/Fail':
                if result is True: result = 'Pass'
                else: result = 'Fail'
            elif distinction == 'Expected/Unexpected':
                expectation = self.__model.expectations.get_expectation(class_name, tool, test)
                if expectation is True and result is True: result = 'Expected pass'
                elif expectation is False and result is True: result = 'Unexpected pass'
                elif expectation is False and result is False: result = 'Expected fail'
                else: result = 'Unexpected fail'
            else:
                msg = 'Unknown option for distinction of exported Tests. Expected "Pass/Fail" or "Expected/Unexpected". Got "{}".'.format(distinction)
                log.getLogger(__name__).warning(msg, stack_info=True)
                return

            # cut project
            project = Cutter.cut_project(project)

            # cut tool name
            if tool[0] == '[' and tool[-1] == ']':
                tool = tool[1:-1]

            # cut class name
            idx = class_name.rfind('.')
            if idx >= 0: class_name = class_name[idx + 1:]

            # cut test
            to_filter = 'test'
            if test[0:len(to_filter)] == to_filter: test = test[len(to_filter):]

            row = [project, tool, class_name, test, result]
            rows.append(row)

        df = pd.DataFrame(rows)
        self.__export_df(df, path)

    def __export_categories(self, df, path, distinction, categories_not_projects):
        """
        Export results of test cases with format like in categories view.
        :param df: dataframe
        :type df: pandas.DataFrame
        :param path: path to export to
        :type path: str
        :param distinction: option for distinction of results: 'Expected/Unexpected' or 'Pass/Fail'
        :type distinction: str
        :param categories_not_projects: True: distinguish by categories. False: distinguish by transformation example
        :type categories_not_projects: bool
        """
        categories = self.__get_categories(df, distinction, categories_not_projects)

        if path[-4:] == 'xlsx':
            df = self.__categories_to_df(categories)
            df.to_excel(path, index=False, header=False)
        elif path[-3:] == 'csv':
            df = self.__categories_to_df(categories)
            df.to_csv(path, index=False, header=False)
        elif path[-3:] == 'txt':
            df = self.__categories_to_df(categories)
            df.to_string(path, index=False, header=False)
        elif path[-3:] == 'tex':
            # self.__to_latex(categories, path)   # self written method
            df = self.__categories_to_df(categories)
            df.to_latex(path, index=False, header=False)  # alternative pandas method
        else:
            log.getLogger(__name__).warning('Invalid format: {}. Expected .xlsx, .csv, .txt or .csv'.format(path))

    def __get_categories(self, df, distinction, categories_not_projects):
        """
        Get a dictionary describing the entries of categories presentation.
        :param df: dataframe
        :type df: pandas.DataFrame
        :param path: path to export to
        :type path: str
        :param distinction: option for distinction of results: 'Expected/Unexpected' or 'Pass/Fail'
        :type distinction: str
        :param categories_not_projects: True: distinguish by categories. False: distinguish by transformation example
        :type categories_not_projects: bool
        :return: dictionary describing the entries of categories presentation.
        """
        batch_fwd_key = 'Batch FWD'
        batch_bwd_key = 'Batch BWD'
        incr_fwd_key = 'Incr. FWD'
        incr_bwd_key = 'Incr. BWD'
        total_key = 'Total'

        pass_key = 'Pass'
        fail_key = 'Fail'
        
        exp_pass_key = 'Expected pass'
        exp_fail_key = 'Expected fail'
        unexp_pass_key = 'Unexpected pass'
        unexp_fail_key = 'Unexpected fail'

        def __create_dict(key_list):
            d = {}
            for k in key_list: d[k] = {}
            return d

        if distinction == 'Pass/Fail':
            keys = [pass_key, fail_key]
        elif distinction == 'Expected/Unexpected':
            keys = [exp_pass_key, exp_fail_key, unexp_pass_key, unexp_fail_key]
        else:
            msg = 'Unknown option for distinction of exported Tests. Expected "Pass/Fail" or "Expected/Unexpected". Got "{}".'.format(
                distinction)
            log.getLogger(__name__).warning(msg, stack_info=True)
            return

        if categories_not_projects:
            categories = {batch_fwd_key: __create_dict(keys), batch_bwd_key: __create_dict(keys),
                          incr_fwd_key: __create_dict(keys), incr_bwd_key: __create_dict(keys)}
        else:
            categories = {}

        tools = []
        for index, row in df.iterrows():
            tool = row['tool']
            test = row['test']
            class_name = row['class']
            project = row['project']
            result = row['result']
            if not (result is True or result is False): continue

            if categories_not_projects:
                if '.alignment_based.' in class_name and '.bwd.' in class_name:
                    category = incr_bwd_key
                elif '.alignment_based.' in class_name and '.fwd.' in class_name:
                    category = incr_fwd_key
                elif '.batch.' in class_name and '.bwd.' in class_name:
                    category = batch_bwd_key
                elif '.batch.' in class_name and '.fwd.' in class_name:
                    category = batch_fwd_key
                else:
                    log.getLogger(__name__).warning('Unknown category for junit test: {}'.format(class_name))
                    continue
            else:
                category = Cutter.cut_project(project)
                if not categories.get(category): categories[category] = __create_dict(keys)

            if distinction == 'Pass/Fail':
                if result is True: result = pass_key
                else: result = fail_key
            elif distinction == 'Expected/Unexpected':
                expectation = self.__model.expectations.get_expectation(class_name, tool, test)
                if expectation is True and result is True: result = exp_pass_key
                elif expectation is False and result is True: result = unexp_pass_key
                elif expectation is False and result is False: result = exp_fail_key
                else: result = unexp_fail_key
            else:
                msg = 'Unknown option for distinction of exported Tests. Expected "Pass/Fail" or "Expected/Unexpected". Got "{}".'.format(distinction)
                log.getLogger(__name__).warning(msg, stack_info=True)
                continue

            tool = self.__cut_tool(tool)
            if tool not in tools: tools.append(tool)

            if categories[category][result].get(tool) is None: categories[category][result][tool] = 0
            categories[category][result][tool] += 1

        # fill empty
        for category, results_dict in categories.items():
            for result, tools_dict in results_dict.items():
                for tool in tools:
                    if tools_dict.get(tool) is None: tools_dict[tool] = 0

        # create dicts for total category
        categories[total_key] = {}
        for result_key in keys:
            categories[total_key][result_key] = __create_dict(tools)
            for tool in tools:
                categories[total_key][result_key][tool] = 0

        # fill total category
        for category, results_dict in categories.items():
            if category == total_key: continue
            for result, tools_dict in results_dict.items():
                for tool, value in tools_dict.items():
                    categories[total_key][result][tool] += value

        # Replace in empty categories zeros with '-'
        for category, results_dict in categories.items():
            if category == total_key: continue
            for tool in tools:
                something_found = False
                for result in results_dict.keys():
                    something_found = something_found or categories[category][result][tool] != 0
                if something_found: continue
                for result in results_dict.keys():
                    categories[category][result][tool] = '-'

        return categories

    def __categories_to_df(self, categories):
        """
        Make a dataframe of a dictionary describing the category presentation.
        :param categories: dictionary describing the category presentation
        :return: dataframe
        :rtype: pandas.DataFrame
        """
        tools = None
        rows = None
        for category, results_dict in categories.items():
            print_category = True   # only a flag
            for result, tools_dict in results_dict.items():
                if tools is None: # fill tools with first iteration
                    tools = sorted(list(tools_dict.keys()), key=str.lower)
                    rows = [['Category', 'Result'] + tools]

                if print_category:
                    print_category = False
                    row = [category, result]
                else:
                    row = ['', result]

                for tool in tools:
                    row.append(tools_dict[tool])
                rows.append(row)

        return pd.DataFrame(rows)

    def __to_latex(self, categories, path):
        """
        Write dataframe to file as latex code.
        :param categories: dictionary with categories as keys and results as values
        :param path: path to write file to
        :type path: str
        """
        s = ''
        num_cols = None
        tools = None
        for category, results_dict in categories.items():
            s += '\\hline \n' + category
            for result, tools_dict in results_dict.items():
                if not num_cols:
                    tools = sorted(list(tools_dict.keys()), key=str.lower)
                    num_cols = len(tools)
                    h = 'Category & Result'
                    for tool in tools:
                        h += ' & ' + tool
                    h += '\\\\ \\hline \n'
                    s = h + s

                s += ' & ' + result
                for tool in tools:
                    s += ' & ' + str(tools_dict[tool])
                s += '\\\\ \n'

        # header
        col_str = '{ |l l|' + num_cols * ' c ' + '| }'
        s = '\\begin{table}[H] \\centering \\begin{tabular}' + col_str + '\n\\hline \n' + s

        # footer
        s += '\\hline \\end{tabular}  \\end{table}'

        print('Exported latex table: \n{}'.format(s))

        file = None
        try:
            file = open(path, "w")
            file.write(s)
        finally:
            if file is not None: file.close()

    def __export_df(self, df, path):
        """
        Export a dataframe to a path. Format is extracted from path.
        :param df: dataframe
        :type df: pandas.DataFrame
        :param path: path to export to
        :type path: str
        """
        if path[-4:] == 'xlsx':
            df.to_excel(path, index=False, header=False)
        elif path[-3:] == 'csv':
            df.to_csv(path, index=False, header=False)
        elif path[-3:] == 'txt':
            df.to_string(path, index=False, header=False)
        elif path[-3:] == 'tex':
            df.to_latex(path, index=False, header=False)
        else:
            log.getLogger(__name__).warning('Invalid format: {}. Expected .xlsx or .csv'.format(path))

    def __export_metrics(self, df, path):
        """
        Exports metrics for results in dataframe.
        :param df: dataframe
        :type df: pandas.DataFrame
        :param path: path to export to
        :type path: str
        """
        res, tools = self.__get_results(df)

        ep_key = 'expected pass'
        ef_key = 'expected fail'
        up_key = 'unexpected pass'
        uf_key = 'unexpected fail'

        batch_key = 'Batch'
        incremental_key = 'Incremental'
        forward_key = 'Forward'
        backward_key = 'Backward'

        rows = [['Benchmark', 'Metric'] + tools]
        for project, tools_dict in res.items():
            metrics = ['Unexpected pass ratio', '', 'Prediction quality', '',
                       'Success rate fwd', '', 'Success rate bwd', '', 'Success rate batch', '', 'Success rate inc', '',
                       'Success rate', '',
                       'Normalized success rate', '']

            columns = [[project] + (len(metrics)-1) * [''], metrics]
            for tool in tools:
                column = []
                columns.append(column)
                counts = res[project].get(tool)
                if not counts:
                    column += len(metrics) * ['-']
                    continue

                ep = counts[forward_key][ep_key] + counts[backward_key][ep_key]
                ef = counts[forward_key][ef_key] + counts[backward_key][ef_key]
                up = counts[forward_key][up_key] + counts[backward_key][up_key]
                uf = counts[forward_key][uf_key] + counts[backward_key][uf_key]

                ep2 = counts[batch_key][ep_key] + counts[incremental_key][ep_key]
                ef2 = counts[batch_key][ef_key] + counts[incremental_key][ef_key]
                up2 = counts[batch_key][up_key] + counts[incremental_key][up_key]
                uf2 = counts[batch_key][uf_key] + counts[incremental_key][uf_key]
                if ep != ep2: print('ep: {} vs. {}'.format(ep, ep2))
                if ef != ef2: print('ef: {} vs. {}'.format(ef, ef2))
                if up != up2: print('up: {} vs. {}'.format(up, up2))
                if uf != uf2: print('uf: {} vs. {}'.format(uf, uf2))

                fwd_total = counts[forward_key][ep_key] + counts[forward_key][ef_key] + counts[forward_key][up_key] + counts[forward_key][uf_key]
                bwd_total = counts[backward_key][ep_key] + counts[backward_key][ef_key] + counts[backward_key][up_key] + counts[backward_key][uf_key]
                batch_total = counts[batch_key][ep_key] + counts[batch_key][ef_key] + counts[batch_key][up_key] + counts[batch_key][uf_key]
                inc_total = counts[incremental_key][ep_key] + counts[incremental_key][ef_key] + counts[incremental_key][up_key] + counts[incremental_key][uf_key]

                total = ep + ef + up + uf

                frac_mask = '{}/{}'
                mask = '{:0.2f}'
                column.append(frac_mask.format(up, total))
                column.append(mask.format(up / total))

                column.append(frac_mask.format(ep + ef, total))
                column.append(mask.format((ep + ef) / total))  # prediction quality

                column.append(frac_mask.format((counts[forward_key][ep_key] + counts[forward_key][up_key]), fwd_total))
                if fwd_total == 0:
                    column.append('-')
                else:
                    column.append(mask.format((counts[forward_key][ep_key] + counts[forward_key][up_key]) / fwd_total))  # success rate fwd

                column.append(frac_mask.format((counts[backward_key][ep_key] + counts[backward_key][up_key]), bwd_total))
                if bwd_total == 0:
                    column.append('-')
                else:
                    column.append(mask.format((counts[backward_key][ep_key] + counts[backward_key][up_key]) / bwd_total))  # success rate bwd

                column.append(frac_mask.format((counts[batch_key][ep_key] + counts[batch_key][up_key]), batch_total))
                if batch_total == 0:
                    column.append('-')
                else:
                    column.append(mask.format((counts[batch_key][ep_key] + counts[batch_key][up_key]) / batch_total))  # success rate batch

                column.append(frac_mask.format((counts[incremental_key][ep_key] + counts[incremental_key][up_key]), inc_total))
                if inc_total == 0:
                    column.append('-')
                else:
                    column.append(mask.format((counts[incremental_key][ep_key] + counts[incremental_key][up_key]) /  inc_total))  # success rate inc

                column.append(frac_mask.format(ep + up, total))
                column.append(mask.format((ep + up) / total))  # success rate

                column.append(frac_mask.format(ep, ep + uf))
                if ep + uf == 0:                                    # normalized success rate
                    column.append('-')
                else:
                    column.append(mask.format(ep / (ep + uf)))

            # transpose
            for row_idx in range(len(columns[0])):
                row = []
                rows.append(row)
                for col in columns:
                    row.append(col[row_idx])

        df = pd.DataFrame(rows)

        self.__export_df(df, path)

    def __get_results(self, df):
        """
        Extract results of a dataframe to a dictionary
        :param df: dataframe
        :type df: pandas.DataFrame
        :return: dictionary with results as values and describing strings as keys.
        """
        ep_key = 'expected pass'
        ef_key = 'expected fail'
        up_key = 'unexpected pass'
        uf_key = 'unexpected fail'
        keys = [ep_key, ef_key, up_key, uf_key]

        batch_key = 'Batch'
        incremental_key = 'Incremental'
        forward_key = 'Forward'
        backward_key = 'Backward'

        res = {}    # project, tool, (category (and total)), results

        def __create_dict(key_list):
            d = {}
            for k in key_list: d[k] = 0
            return d

        tools = []
        for index, row in df.iterrows():
            tool = row['tool']
            test = row['test']
            class_name = row['class']
            project = Cutter.cut_project(row['project'])
            result = row['result']
            if not (result is True or result is False): continue
            expectation = self.__model.expectations.get_expectation(class_name, tool, test)
            tool = self.__cut_tool(row['tool'])
            if tool not in tools: tools.append(tool)

            if not res.get(project): res[project] = {}
            if not res[project].get(tool): res[project][tool] = {
                batch_key: __create_dict(keys), incremental_key: __create_dict(keys),
                forward_key: __create_dict(keys), backward_key: __create_dict(keys)}

            if '.alignment_based.' in class_name and '.bwd.' in class_name:
                direction = backward_key
                inc_or_batch = incremental_key
            elif '.alignment_based.' in class_name and '.fwd.' in class_name:
                direction = forward_key
                inc_or_batch = incremental_key
            elif '.batch.' in class_name and '.bwd.' in class_name:
                direction = backward_key
                inc_or_batch = batch_key
            elif '.batch.' in class_name and '.fwd.' in class_name:
                direction = forward_key
                inc_or_batch = batch_key
            else:
                log.getLogger(__name__).warning('Unknown category for junit test: {}'.format(class_name))
                continue

            if result is True and expectation is True:
                res[project][tool][direction][ep_key] += 1
                res[project][tool][inc_or_batch][ep_key] += 1
            elif result is True and expectation is False:
                res[project][tool][direction][up_key] += 1
                res[project][tool][inc_or_batch][up_key] += 1
            elif result is False and expectation is True:
                res[project][tool][direction][uf_key] += 1
                res[project][tool][inc_or_batch][uf_key] += 1
            elif result is False and expectation is False:
                res[project][tool][direction][ef_key] += 1
                res[project][tool][inc_or_batch][ef_key] += 1
            else:
                log.getLogger(__name__).warning('Other type than boolean found for result or expectation.')

        # all projects
        tools = sorted(tools, key=str.lower)
        all_projects_dict = {}
        for project, tools_dict in res.items():
            for tool in tools:
                if not res[project].get(tool): continue
                if not all_projects_dict.get(tool): all_projects_dict[tool] = {
                    batch_key: __create_dict(keys), incremental_key: __create_dict(keys),
                    forward_key: __create_dict(keys), backward_key: __create_dict(keys)}
                for category in [batch_key, incremental_key, forward_key, backward_key]:
                    for key in keys: all_projects_dict[tool][category][key] += res[project][tool][category][key]

        res['All projects'] = all_projects_dict

        return res, tools

    def __cut_tool(self, tool):
        """
        Cut useless bracket from tool names for cleaner representation.
        :param tool: tool name
        :type tool: str
        :return: cut tool name
        :rtype: str
        """
        if tool[0] == '[' and tool[-1] == ']':
            tool = tool[1:-1]
        return tool


