package org.benchmarx.examples.familiestopersons.implementations.eneo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.benchmarx.config.Configurator;
import org.benchmarx.edit.ChangeAttribute;
import org.benchmarx.edit.CreateEdge;
import org.benchmarx.edit.CreateNode;
import org.benchmarx.edit.DeleteEdge;
import org.benchmarx.edit.DeleteNode;
import org.benchmarx.edit.IEdit;
import org.benchmarx.edit.MoveNode;
import org.benchmarx.examples.familiestopersons.testsuite.Decisions;

import Families.FamiliesPackage;
import Families.Family;
import Families.FamilyMember;
import Families.FamilyRegister;
import Persons.Female;
import Persons.Male;
import Persons.PersonRegister;
import Persons.PersonsPackage;

public class ENeoInput {
	private MODE mode;
	private List<ENeoEdit> sourceEdit = new ArrayList<>();
	private List<ENeoEdit> targetEdit = new ArrayList<>();
	private Boolean preferParents = null;
	private Boolean preferExistingFamilies = null;

	public Boolean getPreferParents() {
		return preferParents;
	}

	public void setPreferParents(Boolean preferParents) {
		this.preferParents = preferParents;
	}

	public Boolean getPreferExistingFamilies() {
		return preferExistingFamilies;
	}

	public void setPreferExistingFamilies(Boolean preferExistingFamilies) {
		this.preferExistingFamilies = preferExistingFamilies;
	}

	public void setEdits(IEdit<FamilyRegister> sourceEdit, IEdit<PersonRegister> targetEdit) {
		for (var se : sourceEdit.getSteps()) {
			var ee = new ENeoEdit();
			if (se instanceof CreateNode<?>) {
				CreateNode<?> cn = (CreateNode<?>) se;
				if (cn.getNode() instanceof Family) {
					ee.setEditType(EDIT_TYPE.CREATE_FAMILY);
					ee.setName(((Family) cn.getNode()).getName());
					ee.setId(cn.getNode().hashCode());
				} else if (cn.getNode() instanceof FamilyMember) {
					ee.setEditType(EDIT_TYPE.CREATE_FAMILY_MEMBER);
					ee.setName(((FamilyMember) cn.getNode()).getName());
					ee.setId(cn.getNode().hashCode());
				}
			} else if (se instanceof CreateEdge) {
				var ce = (CreateEdge<FamilyRegister>) se;
				createSourceEdge(ee, ce);
			} else if (se instanceof ChangeAttribute) {
				var ca = (ChangeAttribute<FamilyRegister>) se;
				if (ca.getAttribute() == FamiliesPackage.Literals.FAMILY__NAME) {
					ee.setEditType(EDIT_TYPE.CHANGE_FAMILY__NAME);
					ee.setNewValue(ca.getNewValue());
					ee.setId(ca.getNode().hashCode());
				} else {
					throw new IllegalArgumentException("Unable to handle change attribute: " + ca.getAttribute());
				}
			} else if (se instanceof DeleteNode) {
				var dn = (DeleteNode<FamilyRegister>) se;
				if (dn.getNode() instanceof Family) {
					ee.setEditType(EDIT_TYPE.DELETE_FAMILY);
					ee.setId(dn.getNode().hashCode());
				} else if (dn.getNode() instanceof FamilyMember) {
					ee.setEditType(EDIT_TYPE.DELETE_FAMILY_MEMBER);
					ee.setId(dn.getNode().hashCode());
				} else {
					throw new IllegalArgumentException("Unable to delete node: " + dn.getNode());
				}
			} else if (se instanceof DeleteEdge) {
				var de = (DeleteEdge<FamilyRegister>) se;
				deleteSourceEdge(ee, de);
			} else if (se instanceof MoveNode) {
				var mn = (MoveNode<FamilyRegister>) se;
				if (mn.getNode() instanceof FamilyMember) {
					ee.setEditType(EDIT_TYPE.MOVE_FAMILY_MEMBER);
					var eeDel = new ENeoEdit();
					ee.setDeleteEdge(eeDel);
					deleteSourceEdge(eeDel, mn.getDeleteEdge());
					var eeCre = new ENeoEdit();
					ee.setCreateEdge(eeCre);
					createSourceEdge(eeCre, mn.getCreateEdge());
					ee.setId(mn.getNode().hashCode());
				} else {
					throw new IllegalArgumentException("Unable to move node: " + mn.getNode());
				}
			}
			this.sourceEdit.add(ee);
		}
		for (var t : targetEdit.getSteps()) {
			var ee = new ENeoEdit();
			if (t instanceof CreateNode<?>) {
				var cn = (CreateNode<PersonRegister>) t;

				if (cn.getNode() instanceof Male) {
					var p = (Male) cn.getNode();
					ee.setEditType(EDIT_TYPE.CREATE_MALE);
					ee.setName(p.getName());
					ee.setId(p.hashCode());
					ee.setBirthday(new SimpleDateFormat("yyyy-MM-dd").format(p.getBirthday()));
				} else if (cn.getNode() instanceof Female) {
					var p = (Female) cn.getNode();
					ee.setEditType(EDIT_TYPE.CREATE_FEMALE);
					ee.setName(p.getName());
					ee.setId(p.hashCode());
					ee.setBirthday(new SimpleDateFormat("yyyy-MM-dd").format(p.getBirthday()));
				} else {
					throw new IllegalArgumentException("Unable to handle created node: " + cn.getNode());
				}
			} else if (t instanceof DeleteNode) {
				var dn = (DeleteNode<PersonRegister>) t;
				ee.setEditType(EDIT_TYPE.DELETE_PERSON);
				ee.setId(dn.getNode().hashCode());
			} else if (t instanceof CreateEdge) {
				var ce = (CreateEdge<PersonRegister>) t;
				if (ce.getType().equals(PersonsPackage.Literals.PERSON_REGISTER__PERSONS)) {
					ee.setEditType(EDIT_TYPE.CREATE_PERSON_REGISTER__PERSONS);
					ee.setId(ce.getTarget().hashCode());
				} else {
					throw new IllegalArgumentException("Unable to handle created edge: " + ce.getType());
				}
			} else if (t instanceof DeleteEdge) {
				var de = (DeleteEdge<PersonRegister>) t;
				if (de.getType().equals(PersonsPackage.Literals.PERSON_REGISTER__PERSONS)) {
					ee.setEditType(EDIT_TYPE.DELETE_PERSON_REGISTER__PERSONS);
					ee.setId(de.getTarget().hashCode());
				}
			} else if (t instanceof ChangeAttribute) {
				var ca = (ChangeAttribute<PersonRegister>) t;
				if (ca.getAttribute().equals(PersonsPackage.Literals.PERSON__BIRTHDAY)) {
					ee.setEditType(EDIT_TYPE.CHANGE_BIRTHDAY);
					ee.setNewValue(new SimpleDateFormat("yyyy-MM-dd").format(ca.getNewValue()));
					ee.setId(ca.getNode().hashCode());
				} else if (ca.getAttribute().equals(PersonsPackage.Literals.PERSON__NAME)) {
					ee.setEditType(EDIT_TYPE.CHANGE_PERSON__NAME);
					ee.setNewValue(ca.getNewValue());
					ee.setId(ca.getNode().hashCode());
				} else {
					throw new IllegalArgumentException("Unable to handle changed attribute: " + ca.getAttribute());
				}
			} else {
				throw new IllegalArgumentException("Unable to handle atomic edit: " + t);
			}

			this.targetEdit.add(ee);
		}

	}

	private void createSourceEdge(ENeoEdit ee, CreateEdge<FamilyRegister> ce) {
		if (ce.getType().equals(FamiliesPackage.Literals.FAMILY_REGISTER__FAMILIES)) {
			ee.setEditType(EDIT_TYPE.CREATE_FAMILY_REGISTER__FAMILIES);
			ee.setName(((Family) ce.getTarget()).getName());
			ee.setId(ce.getTarget().hashCode());
		} else if (ce.getType().equals(FamiliesPackage.Literals.FAMILY__SONS)) {
			var family = (Family) ce.getSource();
			ee.setEditType(EDIT_TYPE.CREATE_FAMILY__SONS);
			ee.setName(family.getName());
			ee.setId(family.hashCode());
			var member = (FamilyMember) ce.getTarget();
			ee.setTrgName(member.getName());
			ee.setTrgId(member.hashCode());
		} else if (ce.getType().equals(FamiliesPackage.Literals.FAMILY__DAUGHTERS)) {
			var family = (Family) ce.getSource();
			ee.setEditType(EDIT_TYPE.CREATE_FAMILY__DAUGHTERS);
			ee.setName(family.getName());
			ee.setId(family.hashCode());
			var member = (FamilyMember) ce.getTarget();
			ee.setTrgName(member.getName());
			ee.setTrgId(member.hashCode());
		} else if (ce.getType().equals(FamiliesPackage.Literals.FAMILY__MOTHER)) {
			var family = (Family) ce.getSource();
			ee.setEditType(EDIT_TYPE.CREATE_FAMILY__MOTHER);
			ee.setName(family.getName());
			ee.setId(family.hashCode());
			var member = (FamilyMember) ce.getTarget();
			ee.setTrgName(member.getName());
			ee.setTrgId(member.hashCode());
		} else if (ce.getType().equals(FamiliesPackage.Literals.FAMILY__FATHER)) {
			var family = (Family) ce.getSource();
			ee.setEditType(EDIT_TYPE.CREATE_FAMILY__FATHER);
			ee.setName(family.getName());
			ee.setId(family.hashCode());
			var member = (FamilyMember) ce.getTarget();
			ee.setTrgName(member.getName());
			ee.setTrgId(member.hashCode());
		}
	}

	private void deleteSourceEdge(ENeoEdit ee, DeleteEdge<FamilyRegister> de) {
		if (de.getType() == FamiliesPackage.Literals.FAMILY_REGISTER__FAMILIES) {
			ee.setEditType(EDIT_TYPE.DELETE_FAMILY_REGISTER__FAMILIES);
			ee.setId(de.getTarget().hashCode());
		} else if (de.getType() == FamiliesPackage.Literals.FAMILY__FATHER) {
			ee.setEditType(EDIT_TYPE.DELETE_FAMILY__FATHER);
			ee.setId(de.getTarget().hashCode());
		} else if (de.getType() == FamiliesPackage.Literals.FAMILY__MOTHER) {
			ee.setEditType(EDIT_TYPE.DELETE_FAMILY__MOTHER);
			ee.setId(de.getTarget().hashCode());
		} else if (de.getType() == FamiliesPackage.Literals.FAMILY__SONS) {
			ee.setEditType(EDIT_TYPE.DELETE_FAMILY__SONS);
			ee.setId(de.getTarget().hashCode());
		} else if (de.getType() == FamiliesPackage.Literals.FAMILY__DAUGHTERS) {
			ee.setEditType(EDIT_TYPE.DELETE_FAMILY__DAUGHTERS);
			ee.setId(de.getTarget().hashCode());
		} else {
			throw new IllegalArgumentException("Unable to delete edge: " + de.getType());
		}
	}

	public void setMode(MODE mode) {
		this.mode = mode;
	}

	public void setConfigurator(Configurator<Decisions> c) {
		preferParents = c.decide(Decisions.PREFER_CREATING_PARENT_TO_CHILD);
		preferExistingFamilies = c.decide(Decisions.PREFER_EXISTING_FAMILY_TO_NEW);
	}

	public List<ENeoEdit> getSourceEdit() {
		return sourceEdit;
	}

	public List<ENeoEdit> getTargetEdit() {
		return targetEdit;
	}

	public MODE getMode() {
		return mode;
	}
}
