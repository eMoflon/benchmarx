package org.benchmarx.examples.familiestopersons.implementations.eneo;

public class ENeoEdit {
	private int id;
	private String name;
	private EDIT_TYPE editType;
	private int trgId;
	private Object newValue;
	private ENeoEdit deleteEdge;
	private ENeoEdit createEdge;
	private String birthday;

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public ENeoEdit getDeleteEdge() {
		return deleteEdge;
	}

	public void setDeleteEdge(ENeoEdit deleteEdge) {
		this.deleteEdge = deleteEdge;
	}

	public ENeoEdit getCreateEdge() {
		return createEdge;
	}

	public void setCreateEdge(ENeoEdit createEdge) {
		this.createEdge = createEdge;
	}

	public Object getNewValue() {
		return newValue;
	}

	public void setNewValue(Object newValue) {
		this.newValue = newValue;
	}

	public int getTrgId() {
		return trgId;
	}

	public void setTrgId(int trgId) {
		this.trgId = trgId;
	}

	public String getTrgName() {
		return trgName;
	}

	public void setTrgName(String trgName) {
		this.trgName = trgName;
	}

	private String trgName;

	public EDIT_TYPE getEditType() {
		return editType;
	}

	public void setEditType(EDIT_TYPE editType) {
		this.editType = editType;
	}

	public int getId() {
		return id;
	}

	public void setId(int i) {
		this.id = i;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
