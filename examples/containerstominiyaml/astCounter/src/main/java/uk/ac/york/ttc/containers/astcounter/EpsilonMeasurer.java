package uk.ac.york.ttc.containers.astcounter;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.function.Supplier;

import org.eclipse.epsilon.common.module.IModule;
import org.eclipse.epsilon.common.module.ModuleElement;

public class EpsilonMeasurer implements IFileMeasurer {

	private final Supplier<IModule> moduleCreator;

	public EpsilonMeasurer(Supplier<IModule> moduleCreator) {
		this.moduleCreator = moduleCreator;
	}

	@Override
	public int measure(File f) throws IOException {
		try {
			IModule module = moduleCreator.get();
			module.parse(f);
			return countElements(module.getChildren(), 0);
		} catch (Exception e) {
			throw new IOException("Could not parse module", e);
		}
	}

	private int countElements(List<ModuleElement> children, int result) {
		for (var child : children) {
			result = countElements(child.getChildren(), result + 1);
		}
		return result;
	}

}
