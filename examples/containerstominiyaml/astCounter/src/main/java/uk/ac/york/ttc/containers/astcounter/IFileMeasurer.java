package uk.ac.york.ttc.containers.astcounter;

import java.io.File;
import java.io.IOException;

@FunctionalInterface
public interface IFileMeasurer {

	public int measure(File f) throws IOException;

}
