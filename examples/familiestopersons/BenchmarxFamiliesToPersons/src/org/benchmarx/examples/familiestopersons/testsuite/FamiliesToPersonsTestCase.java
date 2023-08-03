package org.benchmarx.examples.familiestopersons.testsuite;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

import org.benchmarx.BXTool;
import org.benchmarx.edit.ChangeAttribute;
import org.benchmarx.edit.CreateEdge;
import org.benchmarx.edit.CreateNode;
import org.benchmarx.edit.DeleteEdge;
import org.benchmarx.edit.DeleteNode;
import org.benchmarx.edit.Edit;
import org.benchmarx.edit.IEdit;
import org.benchmarx.edit.MoveNode;
import org.benchmarx.examples.familiestopersons.implementations.bxtend.UbtXtendFamiliesToPersons;
import org.benchmarx.examples.familiestopersons.implementations.emoflon.EMoflonFamiliesToPersons;
import org.benchmarx.examples.familiestopersons.implementations.eneo.ENEoFamiliesToPersons;
import org.benchmarx.examples.familiestopersons.implementations.ibextgg.IBeXTGGFamiliesToPersons;
import org.benchmarx.examples.familiestopersons.implementations.medini.MediniQVTFamiliesToPersons;
import org.benchmarx.examples.familiestopersons.implementations.medini.MediniQVTFamiliesToPersonsConfig;
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
	protected BiConsumer<FamilyRegister, FamilyRegister> familiesComparator;
	protected BiConsumer<PersonRegister, PersonRegister> personsComparator;
	protected BenchmarxUtil<FamilyRegister, PersonRegister, Decisions> util;
	protected FamilyHelper helperFamily;
	protected PersonHelper helperPerson;
	protected IEdit<FamilyRegister> sourceEdit;
	protected IEdit<PersonRegister> targetEdit;

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

		Consumer<EObject> createSourceNode = (n) -> sourceEdit.getSteps().add(new CreateNode<FamilyRegister>(n));
		BiConsumer<EReference, List<EObject>> createSourceEdge = (ref, sourceTarget) -> {
			sourceEdit.getSteps().add(new CreateEdge<FamilyRegister>(ref, sourceTarget.get(0), sourceTarget.get(1)));
		};
		BiConsumer<EAttribute, List<?>> changeSourceAttribute = (attr, nodeOldNew) -> {
			sourceEdit.getSteps().add(new ChangeAttribute<FamilyRegister>(attr, (EObject) nodeOldNew.get(0),
					nodeOldNew.get(1), nodeOldNew.get(2)));
		};
		Consumer<EObject> deleteSourceNode = (n) -> sourceEdit.getSteps().add(new DeleteNode<FamilyRegister>(n));
		BiConsumer<EReference, List<EObject>> deleteSourceEdge = (ref, sourceTarget) -> {
			sourceEdit.getSteps().add(new DeleteEdge<FamilyRegister>(ref, sourceTarget.get(0), sourceTarget.get(1)));
		};

		BiConsumer<EObject, List<EObject>> moveSourceNode = (n, oldP_oldRef_newP_newRef) -> sourceEdit.getSteps()
				.add(new MoveNode<FamilyRegister>(n, //
						oldP_oldRef_newP_newRef.get(0), (EReference) oldP_oldRef_newP_newRef.get(1),
						oldP_oldRef_newP_newRef.get(2), (EReference) oldP_oldRef_newP_newRef.get(3)));

		helperFamily = new FamilyHelper(() -> tool.getSourceModel(), createSourceNode, createSourceEdge,
				changeSourceAttribute, deleteSourceNode, moveSourceNode, deleteSourceEdge);

		Consumer<EObject> createTargetNode = (n) -> targetEdit.getSteps().add(new CreateNode<PersonRegister>(n));
		BiConsumer<EReference, List<EObject>> createTargetEdge = (ref, sourceTarget) -> {
			targetEdit.getSteps().add(new CreateEdge<PersonRegister>(ref, sourceTarget.get(0), sourceTarget.get(1)));
		};
		BiConsumer<EAttribute, List<?>> changeTargetAttribute = (attr, nodeOldNew) -> {
			targetEdit.getSteps().add(new ChangeAttribute<PersonRegister>(attr, (EObject) nodeOldNew.get(0),
					nodeOldNew.get(1), nodeOldNew.get(2)));
		};
		Consumer<EObject> deleteTargetNode = (n) -> targetEdit.getSteps().add(new DeleteNode<PersonRegister>(n));
		BiConsumer<EReference, List<EObject>> deleteTargetEdge = (ref, sourceTarget) -> {
			targetEdit.getSteps().add(new DeleteEdge<PersonRegister>(ref, sourceTarget.get(0), sourceTarget.get(1)));
		};

		helperPerson = new PersonHelper(() -> tool.getTargetModel(), createTargetNode, createTargetEdge,
				changeTargetAttribute, deleteTargetNode, deleteTargetEdge);
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

				new EMoflonFamiliesToPersons(), // Currently 6 failures
				new MediniQVTFamiliesToPersons(), // Currently 19 failures
				new MediniQVTFamiliesToPersonsConfig(), // Currently 12 failures
				new UbtXtendFamiliesToPersons(), // Currently 0 failures
				new IBeXTGGFamiliesToPersons(), // Currently 5 failures
				new ENEoFamiliesToPersons()); // Currently 7 failures
	}

	protected FamiliesToPersonsTestCase(BXTool<FamilyRegister, PersonRegister, Decisions> tool) {
		this.tool = tool;
	}

	protected Supplier<IEdit<FamilyRegister>> srcEdit(Runnable... ops) {
		return () -> {
			sourceEdit = new Edit<FamilyRegister>();
			for (var op : ops) {
				op.run();
			}
			return sourceEdit;
		};
	}

	protected Supplier<IEdit<PersonRegister>> trgEdit(Runnable... ops) {
		return () -> {
			targetEdit = new Edit<PersonRegister>();
			for (var op : ops) {
				op.run();
			}
			return targetEdit;
		};
	}
}
