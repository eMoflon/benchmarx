package org.benchmarx.examples.familiestopersons.scalability.runner;

import java.io.OutputStream;
import java.io.PrintStream;
import java.time.Duration;
import java.util.Collection;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.benchmarx.BXTool;
import org.benchmarx.edit.ChangeAttribute;
import org.benchmarx.edit.CreateEdge;
import org.benchmarx.edit.CreateNode;
import org.benchmarx.edit.DeleteEdge;
import org.benchmarx.edit.DeleteNode;
import org.benchmarx.edit.Edit;
import org.benchmarx.edit.IEdit;
import org.benchmarx.edit.MoveNode;
import org.benchmarx.examples.familiestopersons.testsuite.Decisions;
import org.benchmarx.families.core.FamiliesComparator;
import org.benchmarx.families.core.FamilyHelper;
import org.benchmarx.persons.core.PersonHelper;
import org.benchmarx.persons.core.PersonsComparator;
import org.benchmarx.util.BenchmarxUtil;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;

import Families.FamiliesPackage;
import Families.FamilyRegister;
import Persons.PersonRegister;
import Persons.PersonsPackage;
import de.uni_koblenz.jgralab.gretl.SysOut;

public abstract class BenchTestcase {

	protected BXTool<FamilyRegister, PersonRegister, Decisions> tool;
	protected BiConsumer<FamilyRegister, FamilyRegister> familiesComparator;
	protected BiConsumer<PersonRegister, PersonRegister> personsComparator;
	protected BenchmarxUtil<FamilyRegister, PersonRegister, Decisions> util;
	protected FamilyHelper helperFamily;
	protected PersonHelper helperPerson;
	protected IEdit<FamilyRegister> sourceEdit;
	protected IEdit<PersonRegister> targetEdit;
	
	protected final int scaleFactor;

	public BenchTestcase(String name, int scaleFactor) {
		this.tool = getAvailableTools().stream() //
				.filter(tool -> tool.getName().equals(name)).findFirst() //
				.orElseThrow(() -> new IllegalArgumentException("Tool %s is not recognized".formatted(name)));
		this.scaleFactor = scaleFactor;	
	}
	
	public void execute() {
		PrintStream originalOut = System.out;
//		System.setOut(new PrintStream(new OutputStream() {
//		    public void write(int b) {
//		        // Do nothing - suppress output
//		    }
//		}));

		
		initialise();
		
		var out = executeTest(scaleFactor);
		
		tool.terminateSynchronisationDialogue();
		
		try {
			Thread.sleep(Duration.ofSeconds(1L));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		System.setOut(originalOut);
		System.out.println(out);
		
		System.exit(0);
	}
	
	public void initialise() {
		Logger.getRootLogger().setLevel(Level.INFO);
		
		// Make sure packages are registered
		FamiliesPackage.eINSTANCE.getName();
		PersonsPackage.eINSTANCE.getName();

		// Initialise all helpers
		familiesComparator = new FamiliesComparator();
		personsComparator = new PersonsComparator();
		util = new BenchmarxUtil<>(tool);
		
		// Initialise the bx tool
		tool.initiateSynchronisationDialogue();

		helperFamily = createAndInitialiseHelperFamily(() -> tool.getSourceModel(), () -> sourceEdit);
		helperPerson = createAndInitialiseHelperPerson(() -> tool.getTargetModel(), () -> targetEdit);
	}


	protected abstract Collection<BXTool<FamilyRegister, PersonRegister, Decisions>> getAvailableTools();

	public abstract double executeTest(int scaleFactor);

	public static FamilyHelper createAndInitialiseHelperFamily(Supplier<FamilyRegister> familyRegister,
			Supplier<IEdit<FamilyRegister>> sourceEdit) {
		Consumer<EObject> createSourceNode = (n) -> sourceEdit.get().getSteps().add(new CreateNode<FamilyRegister>(n));
		BiConsumer<EReference, List<EObject>> createSourceEdge = (ref, sourceTarget) -> {
			sourceEdit.get().getSteps()
					.add(new CreateEdge<FamilyRegister>(ref, sourceTarget.get(0), sourceTarget.get(1)));
		};
		BiConsumer<EAttribute, List<?>> changeSourceAttribute = (attr, nodeOldNew) -> {
			sourceEdit.get().getSteps().add(new ChangeAttribute<FamilyRegister>(attr, (EObject) nodeOldNew.get(0),
					nodeOldNew.get(1), nodeOldNew.get(2)));
		};
		Consumer<EObject> deleteSourceNode = (n) -> sourceEdit.get().getSteps().add(new DeleteNode<FamilyRegister>(n));
		BiConsumer<EReference, List<EObject>> deleteSourceEdge = (ref, sourceTarget) -> {
			sourceEdit.get().getSteps()
					.add(new DeleteEdge<FamilyRegister>(ref, sourceTarget.get(0), sourceTarget.get(1)));
		};

		BiConsumer<EObject, List<EObject>> moveSourceNode = (n, oldP_oldRef_newP_newRef) -> sourceEdit.get().getSteps()
				.add(new MoveNode<FamilyRegister>(n, //
						oldP_oldRef_newP_newRef.get(0), (EReference) oldP_oldRef_newP_newRef.get(1),
						oldP_oldRef_newP_newRef.get(2), (EReference) oldP_oldRef_newP_newRef.get(3)));

		return new FamilyHelper(familyRegister, createSourceNode, createSourceEdge, changeSourceAttribute,
				deleteSourceNode, moveSourceNode, deleteSourceEdge);
	}

	public static PersonHelper createAndInitialiseHelperPerson(Supplier<PersonRegister> personRegister,
			Supplier<IEdit<PersonRegister>> targetEdit) {
		Consumer<EObject> createTargetNode = (n) -> targetEdit.get().getSteps().add(new CreateNode<PersonRegister>(n));
		BiConsumer<EReference, List<EObject>> createTargetEdge = (ref, sourceTarget) -> {
			targetEdit.get().getSteps()
					.add(new CreateEdge<PersonRegister>(ref, sourceTarget.get(0), sourceTarget.get(1)));
		};
		BiConsumer<EAttribute, List<?>> changeTargetAttribute = (attr, nodeOldNew) -> {
			targetEdit.get().getSteps().add(new ChangeAttribute<PersonRegister>(attr, (EObject) nodeOldNew.get(0),
					nodeOldNew.get(1), nodeOldNew.get(2)));
		};
		Consumer<EObject> deleteTargetNode = (n) -> targetEdit.get().getSteps().add(new DeleteNode<PersonRegister>(n));
		BiConsumer<EReference, List<EObject>> deleteTargetEdge = (ref, sourceTarget) -> {
			targetEdit.get().getSteps()
					.add(new DeleteEdge<PersonRegister>(ref, sourceTarget.get(0), sourceTarget.get(1)));
		};

		return new PersonHelper(personRegister, createTargetNode, createTargetEdge, changeTargetAttribute,
				deleteTargetNode, deleteTargetEdge);
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
