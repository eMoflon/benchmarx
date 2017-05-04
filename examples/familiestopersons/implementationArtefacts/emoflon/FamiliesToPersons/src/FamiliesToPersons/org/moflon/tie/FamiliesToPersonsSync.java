package FamiliesToPersons.org.moflon.tie;

import java.io.IOException;
import org.apache.log4j.BasicConfigurator;
import org.moflon.tgg.algorithm.synchronization.SynchronizationHelper;


import org.moflon.tgg.runtime.CorrespondenceModel;
import java.util.function.BiConsumer;

import FamiliesToPersons.FamiliesToPersonsPackage;


public class FamiliesToPersonsSync extends SynchronizationHelper{

   public FamiliesToPersonsSync()
   {
      super(FamiliesToPersonsPackage.eINSTANCE, ".");
   }
	
	public static void main(String[] args) throws IOException {
		// Create helper
		BasicConfigurator.configure();
		FamiliesToPersonsSync helper = new FamiliesToPersonsSync();

		// Adjust values as required
		String delta = "instances/fwd.trg.delta.xmi";
		String corr  = "instances/fwd.corr.xmi";
		BiConsumer<String, String> synchronizer = helper::syncBackward;
		
		// Propagate changes
		synchronizer.accept(corr, delta);
	}

	public void syncForward(String corr, String delta) {
		setChangeSrc(executeDeltaSpec(delta));
		loadTriple(corr);
		loadSynchronizationProtocol("instances/fwd.protocol.xmi");
		integrateForward();
		saveResult("fwd");

		System.out.println("Completed forward synchronization");
	}

	public void syncBackward(String corr, String delta) {
		setChangeTrg(executeDeltaSpec(delta));
		loadTriple(corr);
		loadSynchronizationProtocol("instances/fwd.protocol.xmi");
		integrateBackward();
		saveResult("fwd");

		System.out.println("Completed backward synchronization");
	}

	private void loadTriple(String corr) {
		try {
			loadCorr(corr);
			CorrespondenceModel corrModel = (CorrespondenceModel) getCorr();
			setSrc(corrModel.getSource());
			setTrg(corrModel.getTarget());
		} catch (IllegalArgumentException iae) {
			System.err.println("Unable to load input triple for " + corr + ", " + iae.getMessage());
		}
	}

	private void saveResult(String direction) {
		saveSrc("instances/" + direction + ".src.xmi");
		saveTrg("instances/" + direction + ".trg.xmi");
		saveCorr("instances/" + direction + ".corr.xmi");
		saveSynchronizationProtocol("instances/" + direction + ".protocol.xmi");
	}
}