package org.benchmarx.examples.familiestopersons.implementations.chatgpt;

import static org.junit.Assert.fail;

import java.io.IOException;
import java.util.function.Supplier;

import org.benchmarx.config.Configurator;
import org.benchmarx.edit.IEdit;
import org.benchmarx.emf.BXToolForEMF;
import org.benchmarx.examples.familiestopersons.testsuite.Decisions;
import org.benchmarx.families.core.FamiliesComparator;
import org.benchmarx.persons.core.PersonsComparator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

import Families.FamiliesFactory;
import Families.FamilyRegister;
import Persons.PersonRegister;
import Persons.PersonsFactory;
import de.university.hof.genai.f2p.ModelTransformer;

public class GPTFamiliesToPersons extends BXToolForEMF<FamilyRegister, PersonRegister, Decisions> {
	private ResourceSet set = new ResourceSetImpl();
	private Resource source;
	private Resource target;
	private Resource corr;
	private ModelTransformer gptTrans;
	private Configurator<Decisions> conf;
	private Configurator<Decisions> defaultConf;
	private FamilyRegister familiesRoot;
	private PersonRegister personRoot;

	private static final String RESULTPATH = "results/ChatGPT";

	public GPTFamiliesToPersons() {
		super(new FamiliesComparator(), new PersonsComparator());
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String getName() {
		return "ChatGPT Transformation";
	}

	@Override
	public void initiateSynchronisationDialogue() {
		setConfigurator(new Configurator<Decisions>().makeDecision(Decisions.PREFER_CREATING_PARENT_TO_CHILD, true)
				.makeDecision(Decisions.PREFER_EXISTING_FAMILY_TO_NEW, true));

		set.getResourceFactoryRegistry().getExtensionToFactoryMap().put("family", new XMIResourceFactoryImpl());
		set.getResourceFactoryRegistry().getExtensionToFactoryMap().put("person", new XMIResourceFactoryImpl());
		set.getResourceFactoryRegistry().getExtensionToFactoryMap().put("corr", new XMIResourceFactoryImpl());

		source = set.createResource(URI.createURI("sourceModel.family"));
		target = set.createResource(URI.createURI("targetModel.person"));
		corr = set.createResource(URI.createURI("corrModel.corr"));
		familiesRoot = FamiliesFactory.eINSTANCE.createFamilyRegister();
		personRoot = PersonsFactory.eINSTANCE.createPersonRegister();
		source.getContents().add(familiesRoot);
		target.getContents().add(personRoot);
		gptTrans = new ModelTransformer(source, target);
		gptTrans.transformFamiliesToPersons(familiesRoot, personRoot);		
	}
	
	/**
	 * Perform an edit delta on the target model and propagate the change to the
	 * source model
	 * 
	 * @param edit : the source edit delta
	 */
	@Override
	public void performAndPropagateTargetEdit(Supplier<IEdit<PersonRegister>> edit) {
		edit.get();
		gptTrans.transformPersonsToFamilies(personRoot, familiesRoot, 
				conf.decide(Decisions.PREFER_EXISTING_FAMILY_TO_NEW), conf.decide(Decisions.PREFER_CREATING_PARENT_TO_CHILD));
	}

	/**
	 * Perform an edit delta on the source model and propagate the change to the
	 * target model
	 * 
	 * @param edit : the source edit delta
	 */
	@Override
	public void performAndPropagateSourceEdit(Supplier<IEdit<FamilyRegister>> edit) {
		edit.get();		
		gptTrans.transformFamiliesToPersons(familiesRoot, personRoot);
	}	

	@Override
	public void performAndPropagateEdit(Supplier<IEdit<FamilyRegister>> sourceEdit,
			Supplier<IEdit<PersonRegister>> targetEdit) {
		// TODO Auto-generated method stub
		fail("Concurrent edits not supported.");
	}

	@Override
	public void setConfigurator(Configurator<Decisions> configurator) {
		if (defaultConf == null)
			defaultConf = configurator;
		conf = configurator;
	}

	@Override
	public FamilyRegister getSourceModel() {
		return familiesRoot;
	}

	@Override
	public PersonRegister getTargetModel() {
		return personRoot;
	}

	@Override
	public void saveModels(String name) {
		ResourceSet set = new ResourceSetImpl();
		set.getResourceFactoryRegistry().getExtensionToFactoryMap().put(Resource.Factory.Registry.DEFAULT_EXTENSION,
				new XMIResourceFactoryImpl());
		URI srcURI = URI.createFileURI(RESULTPATH + "/" + name + "Family.xmi");
		URI trgURI = URI.createFileURI(RESULTPATH + "/" + name + "Person.xmi");
		Resource resSource = set.createResource(srcURI);
		Resource resTarget = set.createResource(trgURI);

		EObject colSource = EcoreUtil.copy(getSourceModel());
		EObject colTarget = EcoreUtil.copy(getTargetModel());

		resSource.getContents().add(colSource);
		resTarget.getContents().add(colTarget);

		try {
			resSource.save(null);
			resTarget.save(null);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

}
