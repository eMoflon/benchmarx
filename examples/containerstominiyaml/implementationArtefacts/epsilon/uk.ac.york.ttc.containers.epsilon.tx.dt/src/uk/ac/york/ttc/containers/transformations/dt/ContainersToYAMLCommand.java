package uk.ac.york.ttc.containers.transformations.dt;

import java.io.File;
import java.util.Iterator;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.handlers.HandlerUtil;

import uk.ac.york.ttc.containers.transformations.MergingContainersToYAML;

public class ContainersToYAMLCommand extends AbstractHandler {

	private static final String FILE_EXTENSION = ".containers";

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		ISelection selection = HandlerUtil.getActiveWorkbenchWindow(event).getActivePage().getSelection();
		if (selection instanceof IStructuredSelection) {
			IStructuredSelection structured = (IStructuredSelection) selection;
			for (Iterator<?> it = structured.iterator(); it.hasNext(); ) {
				Object e = it.next();

				if (e instanceof IFile && ((IFile) e).getName().toLowerCase().endsWith(FILE_EXTENSION)) {
					IFile iFile = (IFile) e;
					File sourceFile = iFile.getLocation().toFile();

					final String sourceWithoutExtension = sourceFile.getName().substring(0, sourceFile.getName().length() - FILE_EXTENSION.length());
					final File targetFile = new File(sourceFile.getParentFile(), sourceWithoutExtension + ".yml");

					Job job = Job.create("Transform Containers to YAML", monitor -> {
						try {
							new MergingContainersToYAML().run(sourceFile, targetFile);
							iFile.getProject().refreshLocal(IResource.DEPTH_INFINITE, monitor);
						} catch (Exception ex) {
							Platform.getLog(getClass()).error(ex.getMessage(), ex);
						}
					});
					job.schedule();
				}
			}
		}

		return null;
	}

}
