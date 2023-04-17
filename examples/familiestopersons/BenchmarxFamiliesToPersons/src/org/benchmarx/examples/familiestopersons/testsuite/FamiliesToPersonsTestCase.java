package org.benchmarx.examples.familiestopersons.testsuite;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Collection;
import java.util.Deque;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

import org.benchmarx.BXTool;
import org.benchmarx.edit.ChangeAttribute;
import org.benchmarx.edit.CreateEdge;
import org.benchmarx.edit.CreateNode;
import org.benchmarx.edit.Edit;
import org.benchmarx.edit.IEdit;
import org.benchmarx.emf.Comparator;
//import org.benchmarx.examples.familiestopersons.implementations.bxtend.UbtXtendFamiliesToPersons;
//import org.benchmarx.examples.familiestopersons.implementations.emoflon.EMoflonFamiliesToPersons;
import org.benchmarx.examples.familiestopersons.implementations.eneo.ENEoFamiliesToPersons;
//import org.benchmarx.examples.familiestopersons.implementations.ibextgg.IBeXTGGFamiliesToPersons;
//import org.benchmarx.examples.familiestopersons.implementations.jtl.JTLFamiliesToPersons;
//import org.benchmarx.examples.familiestopersons.implementations.medini.MediniQVTFamiliesToPersons;
//import org.benchmarx.examples.familiestopersons.implementations.medini.MediniQVTFamiliesToPersonsConfig;
import org.benchmarx.families.core.FamiliesComparator;
import org.benchmarx.families.core.FamilyHelper;
import org.benchmarx.persons.core.PersonHelper;
import org.benchmarx.persons.core.PersonsComparator;
import org.benchmarx.util.BenchmarxUtil;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import Families.FamiliesPackage;
import Families.FamilyRegister;
import Persons.PersonRegister;
import Persons.PersonsPackage;

@RunWith(Parameterized.class)
public abstract class FamiliesToPersonsTestCase {

	protected BXTool<FamilyRegister, PersonRegister, Decisions> tool;
	protected Comparator<FamilyRegister> familiesComparator;
	protected Comparator<PersonRegister> personsComparator;
	protected BenchmarxUtil<FamilyRegister, PersonRegister, Decisions> util;
	protected FamilyHelper helperFamily;
	protected PersonHelper helperPerson;
	protected Deque<IEdit<FamilyRegister>> sourceEdit;
	protected Deque<IEdit<PersonRegister>> targetEdit;

	@Before
	public void initialise() {
		// Make sure packages are registered
		FamiliesPackage.eINSTANCE.getName();
		PersonsPackage.eINSTANCE.getName();

		// Initialise all helpers
		familiesComparator = new FamiliesComparator();
		personsComparator = new PersonsComparator();
		util = new BenchmarxUtil<>(tool);

		// Initialise the bx tool
		tool.initiateSynchronisationDialogue();

		FamilyRegister fregister = tool.getSourceModel();
		sourceEdit = new ArrayDeque<>();
		Runnable startNewSourceEdit = () -> sourceEdit.push(new Edit<FamilyRegister>());
		Supplier<IEdit<FamilyRegister>> getSourceEdit = () -> sourceEdit.pop();
		Consumer<EObject> createSourceNode = (n) -> sourceEdit.peek().getSteps().add(new CreateNode<FamilyRegister>(n));
		BiConsumer<EReference, List<EObject>> createSourceEdge = (ref, sourceTarget) -> {
			sourceEdit.peek().getSteps().add(new CreateEdge<FamilyRegister>(ref, sourceTarget.get(0), sourceTarget.get(1)));
		};
		BiConsumer<EAttribute, List<?>> changeSourceAttribute = (attr, nodeOldNew) -> {
			sourceEdit.peek().getSteps().add(
					new ChangeAttribute<FamilyRegister>(attr, (EObject) nodeOldNew.get(0), nodeOldNew.get(1), nodeOldNew.get(2)));
		};
		helperFamily = new FamilyHelper(fregister, startNewSourceEdit, getSourceEdit, createSourceNode,
				createSourceEdge, changeSourceAttribute);

		PersonRegister pregister = tool.getTargetModel();
		targetEdit = new ArrayDeque<>();
		Runnable startNewTargetEdit = () -> targetEdit.push(new Edit<PersonRegister>());
		Supplier<IEdit<PersonRegister>> getTargetEdit = () -> targetEdit.pop();
		Consumer<EObject> createTargetNode = (n) -> targetEdit.peek().getSteps().add(new CreateNode<PersonRegister>(n));
		BiConsumer<EReference, List<EObject>> createTargetEdge = (ref, sourceTarget) -> {
			targetEdit.peek().getSteps().add(new CreateEdge<PersonRegister>(ref, sourceTarget.get(0), sourceTarget.get(1)));
		};
		BiConsumer<EAttribute, List<?>> changeTargetAttribute = (attr, nodeOldNew) -> {
			targetEdit.peek().getSteps().add(
					new ChangeAttribute<PersonRegister>(attr, (EObject) nodeOldNew.get(0), nodeOldNew.get(1), nodeOldNew.get(2)));
		};
		helperPerson = new PersonHelper(pregister, startNewTargetEdit, getTargetEdit, createTargetNode,
				createTargetEdge, changeTargetAttribute);
	}

	@After
	public void terminate() {
		tool.terminateSynchronisationDialogue();
	}

	// Solutions requiring additional setup are commented out.
	@Parameters(name = "{0}")
	public static Collection<BXTool<FamilyRegister, PersonRegister, Decisions>> tools() {
		return Arrays.asList(//
				/*
				 * See setup instructions: /implementations/bigul/README-SETUP
				 */
				// new BiGULFamiliesToPersons(), // Currently 9 failures

				/*
				 * Excluded due to problems with Closure
				 */
				// new FunnyQTFamiliesToPerson(), // Currently 10 failures

				/*
				 * See setup instructions: /implementations/nmf/README-SETUP
				 */
				// new NMFFamiliesToPersonsIncremental(), // Currently 3 failures

				/*
				 * Excluded due to problems with Emftext
				 */
				// new JTLFamiliesToPersons(), // Currently 11 failures

//				new EMoflonFamiliesToPersons(), // Currently 6 failures
//				new MediniQVTFamiliesToPersons(), // Currently 19 failures
//				new MediniQVTFamiliesToPersonsConfig(), // Currently 12 failures
//				new UbtXtendFamiliesToPersons(), // Currently 0 failures
//				new IBeXTGGFamiliesToPersons(), // Currently 5 failures
				new ENEoFamiliesToPersons());
	}

	protected FamiliesToPersonsTestCase(BXTool<FamilyRegister, PersonRegister, Decisions> tool) {
		this.tool = tool;
	}
}
