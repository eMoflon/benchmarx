package org.benchmarx.examples.familiestopersons.implementations.bxtend;

import java.util.Date;
import java.util.HashMap;
import java.util.function.Supplier;

import org.benchmarx.edit.ChangeAttribute;
import org.benchmarx.edit.CreateNode;
import org.benchmarx.edit.DeleteNode;
import org.benchmarx.edit.IEdit;
import org.eclipse.emf.ecore.util.EcoreUtil;

import Families.FamilyRegister;
import Persons.Person;
import Persons.PersonRegister;
import Persons.PersonsPackage;

public class WrapperOverBXtendWithMerge extends /*UbtXtendFamiliesToPersons*/ BXtendFamiliesToPersons {
	private PersonRegister copyOfTarget;
	private boolean useCopyOfTargetModel;

	@Override
	public String getName() {
		return "BXtend Wrapped with Merge";
	}

	@Override
	public void performAndPropagateEdit(Supplier<IEdit<FamilyRegister>> sourceEditOp,
			Supplier<IEdit<PersonRegister>> targetEditOp) {

		// 0. Keep unchanged copy of target model
		copyOfTarget = (PersonRegister) EcoreUtil.copy(getOriginalTargetModel());

		// 1. Perform forward propagation
		useCopyOfTargetModel = true;
		performAndPropagateSourceEdit(sourceEditOp);

		// 2. Perform target edit to copy of target model
		IEdit<PersonRegister> targetDelta = targetEditOp.get();

		// 3. Merge of target models
		merge(getOriginalTargetModel(), targetDelta);

		// 4. Perform backward propagation
		useCopyOfTargetModel = false;
		performAndPropagateTargetEdit(() -> IEdit.idleEdit());
	}

	private PersonRegister getOriginalTargetModel() {
		return (PersonRegister) target.getContents().get(0);
	}

	@Override
	public void initiateSynchronisationDialogue() {
		useCopyOfTargetModel = false;
		super.initiateSynchronisationDialogue();
	}

	@Override
	public PersonRegister getTargetModel() {
		// Return copy for applying target edit
		return useCopyOfTargetModel ? copyOfTarget : getOriginalTargetModel();
	}

	private void merge(PersonRegister result, IEdit<PersonRegister> targetDelta) {
		HashMap<String, Person> nameToPerson = new HashMap<>();
		// Match people up with their names
		result.getPersons().forEach(p -> nameToPerson.put(p.getName(), p));

		targetDelta.getSteps().forEach(deltaStep -> {
			// Create missing people
			if (deltaStep instanceof CreateNode) {
				CreateNode<PersonRegister> cn = (CreateNode<PersonRegister>) deltaStep;
				if (!nameToPerson.containsKey(((Person) cn.getNode()).getName()))
					result.getPersons().add((Person) cn.getNode());
			}
			// Change birthdays and names if possible
			if (deltaStep instanceof ChangeAttribute) {
				ChangeAttribute<PersonRegister> ca = (ChangeAttribute<PersonRegister>) deltaStep;
				Person changedNode = (Person) ca.getNode();
				if (ca.getAttribute() == PersonsPackage.eINSTANCE.getPerson_Birthday()) {
					if (nameToPerson.containsKey(changedNode.getName())) {
						nameToPerson.get(changedNode.getName()).setBirthday((Date) ca.getNewValue());
					}
				} else if (ca.getAttribute() == PersonsPackage.eINSTANCE.getPerson_Name()) {
					if (nameToPerson.containsKey(ca.getOldValue())) {
						nameToPerson.get(ca.getOldValue()).setName((String) ca.getNewValue());
					}
				}
			}
			// Delete people if possible
			if (deltaStep instanceof DeleteNode) {
				DeleteNode<PersonRegister> dn = (DeleteNode<PersonRegister>) deltaStep;
				Person toBeDeleted = (Person) dn.getNode();
				if (nameToPerson.containsKey(toBeDeleted.getName())) {
					EcoreUtil.delete(nameToPerson.get(toBeDeleted.getName()));
				}
			}
		});

	}
}
