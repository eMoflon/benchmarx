package uk.ac.york.ttc.containers.transformations;

import java.io.File;

public interface ITransformation {

	void run(File source, File target) throws Exception;

}
