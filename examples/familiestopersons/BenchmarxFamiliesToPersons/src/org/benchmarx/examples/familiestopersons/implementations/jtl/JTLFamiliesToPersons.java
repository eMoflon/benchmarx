package org.benchmarx.examples.familiestopersons.implementations.jtl;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

import org.benchmarx.config.Configurator;
import org.benchmarx.edit.IEdit;
import org.benchmarx.emf.BXToolForEMF;
import org.benchmarx.examples.familiestopersons.testsuite.Decisions;
import org.benchmarx.families.core.FamiliesComparator;
import org.benchmarx.persons.core.PersonsComparator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;

import Families.FamiliesFactory;
import Families.FamilyRegister;
import Persons.PersonRegister;
import Persons.PersonsFactory;
import jtl.launcher.Launcher;

public class JTLFamiliesToPersons extends BXToolForEMF<FamilyRegister, PersonRegister, Decisions> {
	private static int testNumber = 0;

	private static final String SOURCEPATH = "results/jtl/";
	private static final String TARGETPATH = SOURCEPATH + "target/";

	private ResourceSet resourceSet;

	private Map<String, Object> globalPackageRegistry;

	private FamilyRegister familiesModel;
	private PersonRegister personsModel;

	private static final String artefactsPath = "../implementationArtefacts/jtl/";
	private static final String transformation = artefactsPath + "Families2Persons.dl";
	private static final String familiesMetamodel = "../metamodels/Families/model/Families.ecore";
	private static final String personsMetamodel = "../metamodels/Persons/model/Persons.ecore";
	private List<String> additionalConstraints;
	private Configurator<Decisions> configurator;

	public JTLFamiliesToPersons() {
		super(new FamiliesComparator(), new PersonsComparator());
	}

	@Override
	public String getName() {
		return "JTL";
	}

	@Override
	public void initiateSynchronisationDialogue() {

		final File targetPath = new File(TARGETPATH);
		if (!targetPath.exists()) {
			targetPath.mkdirs();
		}

		initResourceSet();

		globalPackageRegistry = new HashMap<String, Object>(EPackage.Registry.INSTANCE);

		familiesModel = FamiliesFactory.eINSTANCE.createFamilyRegister();
		personsModel = PersonsFactory.eINSTANCE.createPersonRegister();

		setConfigurator(new Configurator<Decisions>().makeDecision(Decisions.PREFER_CREATING_PARENT_TO_CHILD, true)
				.makeDecision(Decisions.PREFER_EXISTING_FAMILY_TO_NEW, true));

		testNumber++;
	}

	@Override
	public void performAndPropagateSourceEdit(Supplier<IEdit<FamilyRegister>> edit) {
		edit.get();
		saveModel(familiesModel, "families");
		personsModel = (PersonRegister) loadModel(launchFWD());
	}

	@Override
	public void performAndPropagateTargetEdit(Supplier<IEdit<PersonRegister>> edit) {
		edit.get();
		selectConstraints();
		saveModel(personsModel, "persons");
		familiesModel = (FamilyRegister) loadModel(launchBWD());
	}

	@Override
	public void performIdleSourceEdit(Supplier<IEdit<FamilyRegister>> edit) {
		performAndPropagateSourceEdit(edit);
	}

	@Override
	public void performIdleTargetEdit(Supplier<IEdit<PersonRegister>> edit) {
		performAndPropagateTargetEdit(edit);
	}

	@Override
	public void setConfigurator(Configurator<Decisions> configurator) {
		this.configurator = configurator;
	}

	@Override
	public FamilyRegister getSourceModel() {
		return familiesModel;
	}

	@Override
	public PersonRegister getTargetModel() {
		return personsModel;
	}

	@Override
	public void saveModels(String name) {
	}

	private void saveModel(final EObject model, final String basename) {
		final String modelPath = SOURCEPATH + basename + testNumber + ".xmi";
		Resource modelResource = resourceSet.createResource(URI.createFileURI(modelPath));
		modelResource.getContents().add(model);
		try {
			modelResource.save(null);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private EObject loadModel(final String path) {
		initResourceSet();
		final Resource model = resourceSet.getResource(URI.createFileURI(path), true);
		if (model != null) {
			return model.getContents().get(0);
		}
		System.err.println("Model " + path + " was not found.");
		return null;
	}

	private String getTraceFilename(final String modelFilename) {
		final String basename = modelFilename.substring(modelFilename.lastIndexOf('/') + 1);
		final String basepath = basename.substring(0, basename.lastIndexOf('.')) + "_trace.xmi";
		String traceFilename = TARGETPATH + basepath;
		if (!new File(traceFilename).exists()) {
			return null;
		}
		return traceFilename;
	}

	private String launchFWD() {
		final String sourceModel = SOURCEPATH + "families" + testNumber + ".xmi";
		final String targetModel = TARGETPATH + "persons" + testNumber + ".xmi";
		List<String> args = new ArrayList<String>();
		args.add(familiesMetamodel);
		args.add(personsMetamodel);
		args.add(sourceModel);
		args.add(targetModel);
		args.add(transformation);

		launch(args);

		return targetModel;
	}

	private String launchBWD() {
		final String sourceModel = SOURCEPATH + "persons" + testNumber + ".xmi";
		final String targetModel = TARGETPATH + "families" + testNumber + ".xmi";
		List<String> args = new ArrayList<String>();
		args.add(personsMetamodel);
		args.add(familiesMetamodel);
		args.add(sourceModel);
		args.add(targetModel);
		args.add(transformation);
		final String traceModel = getTraceFilename(args.get(2));
		if (traceModel != null) {
			if (new File(traceModel).exists()) {
				args.add("--trace");
				args.add(traceModel);
			}
		}
		for (String constraintsFile : additionalConstraints) {
			args.add("--constraints");
			args.add(constraintsFile);
		}

		launch(args);

		return targetModel;
	}

	private void launch(final List<String> args) {

		// Temporarily suppress System.out
		final PrintStream tmp = System.out;
		System.setOut(new PrintStream(new OutputStream() {
			public void write(int a) {
			}
		}));

		// Launch JTL
		Launcher.main(args.toArray(new String[0]));

		// Restore System.out
		System.setOut(tmp);

		restoreGlobalPackageRegistry();
	}

	private void selectConstraints() {
		additionalConstraints = new ArrayList<String>();

		additionalConstraints.add(artefactsPath + "Families2Persons_prefer_creating_"
				+ (configurator.decide(Decisions.PREFER_CREATING_PARENT_TO_CHILD) ? "parent" : "child") + ".dl");

		additionalConstraints.add(artefactsPath + "Families2Persons_prefer_"
				+ (configurator.decide(Decisions.PREFER_EXISTING_FAMILY_TO_NEW) ? "existing_family" : "new_family")
				+ ".dl");
	}

	private void restoreGlobalPackageRegistry() {
		EPackage.Registry.INSTANCE.clear();
		for (Map.Entry<String, Object> entry : globalPackageRegistry.entrySet()) {
			EPackage.Registry.INSTANCE.put(entry.getKey(), entry.getValue());
		}
	}

	private void initResourceSet() {
		resourceSet = new ResourceSetImpl();
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap()
				.put(Resource.Factory.Registry.DEFAULT_EXTENSION, new JTLXMIResourceFactoryImpl());
	}

	class JTLXMIResourceFactoryImpl extends XMIResourceFactoryImpl {
		@Override
		public Resource createResource(URI uri) {
			return new XMIResourceImpl(uri) {
				@Override
				protected void attachedHelper(EObject eObject) {
					String id = getID(eObject);
					if (id == null) {
						id = DETACHED_EOBJECT_TO_ID_MAP.remove(eObject);
					}
					setID(eObject, id);
				}

				@Override
				protected boolean useUUIDs() {
					return true;
				}
			};
		}
	}

	@Override
	public void performAndPropagateEdit(Supplier<IEdit<FamilyRegister>> sourceEdit,
			Supplier<IEdit<PersonRegister>> targetEdit) {
		throw new UnsupportedOperationException("Concurrent edits not supported.");
	}
}
