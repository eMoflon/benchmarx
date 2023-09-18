"""
Run this file to start the application
"""
import sys
import os


def __add_to_sys_path(path):
    if path not in sys.path:
        sys.path.append(path)


# some setups like working directory and logger
base_path = os.getcwd()
cut = 'bx_runner'
if cut not in base_path:
    print('\nWARNING: Working directory does not contain "bx_runner" folder. Modules may not be found.'
          ' Please start application again with "bx_runner" folder or a subfolder as working directory. \n'
          'Working directory: {}\n'.format(base_path))
base_path = base_path[: base_path.rfind(cut) + len(cut)]

os.chdir(base_path)

__add_to_sys_path(base_path)
__add_to_sys_path(base_path + '/model')
__add_to_sys_path(base_path + '/view')
__add_to_sys_path(base_path + '/adapter')
__add_to_sys_path(base_path + '/util')

from model import Model
from view import View
from adapter import MainAdapter
import logging as log
import pandas as pd


def run():
    """
    Run the application.
    """
    log.basicConfig(format='%(levelname)s %(name)s: %(asctime)s\n\t%(message)s')
    log.getLogger().setLevel(log.INFO)
    log.getLogger("matplotlib.font_manager").setLevel(log.WARNING)
    log.getLogger("numexpr.utils").setLevel(log.WARNING)
    
    log.getLogger(__name__).info('Starting application.')

    # pandas settings
    pd.set_option('display.max_columns', None)
    pd.set_option('display.max_colwidth', None)
    pd.set_option('display.width', None)
    pd.set_option('display.max_rows', None)
    
    model = Model()
    
    view = View() 
    
    MainAdapter(model, view)
    
    view.mainloop()
    
    log.getLogger(__name__).info('Closing application.')


if __name__ == "__main__":
    run()
