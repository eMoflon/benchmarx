package org.benchmarx.examples.familiestopersons.implementations.nmf;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import org.benchmarx.edit.AtomicEdit;
import org.benchmarx.edit.ChangeAttribute;
import org.benchmarx.edit.CreateEdge;
import org.benchmarx.edit.CreateNode;
import org.benchmarx.edit.DeleteEdge;
import org.benchmarx.edit.DeleteNode;
import org.benchmarx.edit.IEdit;
import org.benchmarx.edit.MoveNode;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;

import Families.FamilyRegister;
import Persons.PersonRegister;

import org.eclipse.emf.ecore.EReference;

class ChangeRecorder extends org.eclipse.emf.ecore.util.EContentAdapter {
	
	private static SimpleDateFormat compatibleFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
	
	private EObject root;
	private String metamodel;
	private String modelPath;
	private StringBuffer buffer = new StringBuffer();
	private int changeCounter = -1;
	private HashMap<EObject, String> uris = new HashMap<EObject, String>();

    public void observeFamilyRegister(FamilyRegister r, boolean attach){
    	if (attach) {
    		r.eAdapters().add(this);
    	}
        root = r;
        modelPath = "ttc:source";
        metamodel = "platform:/plugin/Families/model/Families.ecore";
    	this.registerDescendants(r);
    	this.writeStart();
    }
    
    public void observePersonsRegister(PersonRegister r, boolean attach) {
    	if (attach) {
    		r.eAdapters().add(this);
    	}
    	root = r;
    	modelPath = "ttc:target";
    	metamodel = "platform:/plugin/Persons/model/Persons.ecore";
    	this.registerDescendants(r);
    	this.writeStart();
    }
    
    public String stopAndExport() {
    	this.writeEnd();
    	unsetTarget(root);
    	return buffer.toString();
    }
    
    private void registerDescendants(EObject modelElement) {
    	String uri = modelPath + "#//" + EcoreUtil.getRelativeURIFragmentPath(null, modelElement);
    	uris.put(modelElement, uri);
    	for (EObject child : modelElement.eContents()) {
    		this.registerDescendants(child);
    	}
    }
    
    private void writeStart() {
    	buffer.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n");
    	buffer.append("<changes:ModelChangeSet xmi:version=\"2.0\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xmi=\"http://www.omg.org/XMI\" xmlns:changes=\"http://nmf.codeplex.com/changes\">\n");
    }
    
    private void writeEnd() {
    	buffer.append("</changes:ModelChangeSet>");
    }
    
    public void WriteEdit(IEdit<?> edit) {
    	var handled = new HashSet<EObject>();
    	for (var step : edit.getSteps()) {
    		WriteStep(step, handled);
    	}
    }
    
    private void registerAdditions(EObject element, HashSet<EObject> handled) {
    	if (handled.add(element)) {
    		for (var child : element.eContents()) {
    			registerAdditions(child, handled);
    		}
    	}
    }
    
    private void WriteStep(AtomicEdit<?> step, HashSet<EObject> handled) {
    	switch (step) {
    	case ChangeAttribute<?> ca:
    		if (handled.contains(ca.getNode())) return;
    		WriteAttributeValueChange(ca.getNode(), ca.getAttribute(), ca.getOldValue(), ca.getNewValue());
    		break;
    	case CreateEdge<?> createEdge:
    		if (handled.contains(createEdge.getSource())) return;
    		if (createEdge.getType().getUpperBound() == 1) {
    			if (createEdge.getType().isContainment()) {
    				this.WriteCompositionPropertyChange(createEdge.getSource(), createEdge.getType(), null, createEdge.getTarget());
    				registerAdditions(createEdge.getTarget(), handled);
    			}
    			else {
    				this.WriteAssociationPropertyChange(createEdge.getSource(), createEdge.getType(), null, createEdge.getTarget());
    			}
    		}
    		else if (createEdge.getType().isContainment()) {
    			var position = extractPosition(createEdge);
    			if (position == -1) {
    				this.WriteCompositionCollectionInsertion(createEdge.getSource(), createEdge.getType(), createEdge.getTarget());
    			}
    			else {
    				this.WriteCompositionListInsertion(createEdge.getSource(), createEdge.getType(), position, createEdge.getTarget());
    			}
    			registerAdditions(createEdge.getTarget(), handled);
    		}
    		else {
    			var position = extractPosition(createEdge);
    			if (position == -1) {
    				this.WriteAssociationCollectionInsertion(createEdge.getSource(), createEdge.getType(), createEdge.getTarget());
    			}
    			else {
    				this.WriteAssociationListInsertion(createEdge.getSource(), createEdge.getType(), position, createEdge.getTarget());
    			}
    		}
    		break;
    	case CreateNode<?> createNode:
    		break;
    	case DeleteEdge<?> deleteEdge:
    		if (deleteEdge.getType().getUpperBound() == 1) {
    			if (deleteEdge.getType().isContainment()) {
    				this.WriteCompositionPropertyChange(deleteEdge.getSource(), deleteEdge.getType(), deleteEdge.getTarget(), null);
    			}
    			else {
    				this.WriteAssociationPropertyChange(deleteEdge.getSource(), deleteEdge.getType(), deleteEdge.getTarget(), null);    				
    			}
    		}
    		else if (deleteEdge.getType().isContainment()) {
    			this.WriteCompositionCollectionDeletion(deleteEdge.getSource(), deleteEdge.getType(), deleteEdge.getTarget());
    		}
    		else {
    			this.WriteAssociationCollectionDeletion(deleteEdge.getSource(), deleteEdge.getType(), deleteEdge.getTarget());
    		}
    		break;
    	case DeleteNode<?> deleteNode:
    		break;
    	case MoveNode<?> moveNode:
    		this.WriteCompositionMoveToCollection(moveNode.getNode(), moveNode.getDeleteEdge().getType(), moveNode.getCreateEdge().getType(), moveNode.getDeleteEdge().getSource(), moveNode.getCreateEdge().getSource());
    		break;
    	
    	default:
    		break;
    	}
    }

	private int extractPosition(CreateEdge<?> createEdge) {
		var position = -1;
		if (createEdge.getType().isOrdered()) {
			var collection = createEdge.getSource().eGet(createEdge.getType(), true);
			if (collection instanceof EList<?> list) {
				position = list.indexOf(createEdge.getTarget());
			}
		}
		return position;
	}

    public void notifyChanged(Notification n){
        
        super.notifyChanged(n);
        
        if (n.getEventType() == Notification.REMOVING_ADAPTER) return;
        
        EObject affectedElement = (EObject)n.getNotifier();
        EStructuralFeature feature = (EStructuralFeature)n.getFeature();
        
        if (feature == null) {
        	System.out.println("This comes unexpected.");
        }
        
        if (feature instanceof EReference) {
        	EReference reference = (EReference)feature;
        	WriteReferenceChange(n, affectedElement, feature, reference);
        } else {
        	WriteAttributeChange(n, affectedElement, feature);
        }
        
    }

	private void WriteReferenceChange(Notification n, EObject affectedElement, EStructuralFeature feature,
			EReference reference) {
		if (reference.getUpperBound() == 1) {
			if (reference.isContainment()) {
				WriteCompositionPropertyChange(affectedElement, feature, (EObject)n.getOldValue(), (EObject)n.getNewValue());
			} else {
				if (reference.isContainer()) return;
				WriteAssociationPropertyChange(affectedElement, feature, (EObject)n.getOldValue(), (EObject)n.getNewValue());
			}
		}
		else if (reference.isOrdered()) {
			int position = n.getPosition();
			if (reference.isContainment()) {
				if (n.getEventType() == Notification.ADD) {
					WriteCompositionListInsertion(affectedElement, feature, position, (EObject)n.getNewValue());
				} else if (n.getEventType() == Notification.REMOVE) {
					WriteCompositionListDeletion(affectedElement, feature, position, (EObject)n.getOldValue());
				} else {
					buffer.append("(Change type not supported)");
				}
			} else {
				if (reference.isContainer()) return;
				if (n.getEventType() == Notification.ADD) {
					WriteAssociationListInsertion(affectedElement, feature, position, (EObject)n.getNewValue());
				} else if (n.getEventType() == Notification.REMOVE) {
					WriteAssociationListDeletion(affectedElement, feature, position, (EObject)n.getOldValue());
				} else {
					buffer.append("(Change type not supported)");
				}
			}
		} else {
			if (reference.isContainment()) {
				if (n.getEventType() == Notification.ADD) {
					WriteCompositionCollectionInsertion(affectedElement, feature, (EObject)n.getNewValue());
				} else if (n.getEventType() == Notification.REMOVE) {
					WriteCompositionCollectionDeletion(affectedElement, feature, (EObject)n.getOldValue());
				} else {
					System.out.println("Change type not supported");
				}
			} else {
				if (n.getEventType() == Notification.ADD) {
					WriteAssociationCollectionInsertion(affectedElement, feature, (EObject)n.getNewValue());
				} else if (n.getEventType() == Notification.REMOVE) {
					WriteAssociationCollectionDeletion(affectedElement, feature, (EObject)n.getOldValue());
				} else {
					buffer.append("(Change type not supported)");
				}
			}
		}
	}

	private void WriteAssociationCollectionDeletion(EObject affectedElement,
			EStructuralFeature feature, EObject oldValue) {
		this.WriteStartChange("AssociationCollectionDeletion", feature);
		this.WriteElement(oldValue, "deletedElement");
		this.WriteElement(affectedElement, "affectedElement");
		this.WriteSelfClosingChange();
	}

	private void WriteAssociationCollectionInsertion(EObject affectedElement,
			EStructuralFeature feature, EObject newValue) {
		this.WriteStartChange("AssociationCollectionInsertion", feature);
		this.WriteElement(newValue, "addedElement");
		this.WriteElement(affectedElement, "affectedElement");
		this.WriteSelfClosingChange();
	}

	private void WriteAssociationListDeletion(EObject affectedElement, EStructuralFeature feature,
			int position, EObject oldValue) {
		this.WriteStartChange("AssociationListDeletion", feature);
		this.WriteElement(oldValue, "deletedElement");
		this.WriteElement(affectedElement, "affectedElement");
		this.WritePosition(position);
		this.WriteSelfClosingChange();
	}

	private void WriteCompositionMoveToCollection(EObject movedElement, EStructuralFeature oldFeature, EStructuralFeature feature,
			EObject oldOrigin, EObject newOrigin) {
		if (feature.getUpperBound() == 1) {
			this.WriteStartChange("CompositionMoveIntoProperty", feature);
			this.WriteElement(movedElement, "newValue");
			this.WriteElement(newOrigin, "affectedElement");
			buffer.append(">\n");
			this.WriteOrigin(oldFeature, oldOrigin, movedElement);
			this.WriteEndChange("CompositionMoveIntoProperty");
		}
		else {
			this.WriteStartChange("CompositionMoveToCollection", feature);
			this.WriteElement(movedElement, "movedElement");
			this.WriteElement(newOrigin, "affectedElement");
			buffer.append(">\n");
			this.WriteOrigin(oldFeature, oldOrigin, movedElement);
			this.WriteEndChange("CompositionMoveToCollection");
		}
	}
	
    private void WriteOrigin(EStructuralFeature feature, EObject oldParent, EObject moved) {
    	String changeType;
    	if (feature.getUpperBound() == 1) {
    		changeType = "CompositionPropertyChange";
    	}
    	else {
    		changeType = "CompositionCollectionDeletion";
    	}
    	buffer.append("\t<origin xsi:type=\"changes:" + changeType);
    	buffer.append("\" ");
    	buffer.append("feature=\"");
    	buffer.append(metamodel);
    	buffer.append("#//");
    	buffer.append(EcoreUtil.getRelativeURIFragmentPath(null, feature));
    	buffer.append("\"");
    	if (feature.getUpperBound() == 1) {
    		this.WriteElement(oldParent, "affectedElement");
    		this.WriteElement(moved, "oldValue");
    	}
    	else {
    		this.WriteElement(moved, "deletedElement");
    		this.WriteElement(oldParent, "affectedElement");
    	}
		this.WriteSelfClosingChange();
    }

	private void WriteAssociationListInsertion(EObject affectedElement, EStructuralFeature feature,
			int position, EObject newValue) {
		this.WriteStartChange("AssociationListInsertion", feature);
		this.WriteElement(newValue, "addedElement");
		this.WriteElement(affectedElement, "affectedElement");
		this.WritePosition(position);
		this.WriteSelfClosingChange();
	}

	private void WriteCompositionCollectionDeletion(EObject affectedElement,
			EStructuralFeature feature, EObject oldValue) {
		this.WriteStartChange("CompositionCollectionDeletion", feature);
		this.WriteElement(oldValue, "deletedElement");
		this.WriteElement(affectedElement, "affectedElement");
		this.WriteSelfClosingChange();
	}

	private void WriteCompositionCollectionInsertion(EObject affectedElement, EStructuralFeature feature,
			EObject newValue) {
		this.WriteStartChange("CompositionCollectionInsertion", feature);
		this.WriteElement(affectedElement, "affectedElement");
		buffer.append(">\n");
		this.WriteFullElement(newValue, "addedElement");
		this.WriteEndChange("CompositionCollectionInsertion");
	}

	private void WriteCompositionListDeletion(EObject affectedElement, EStructuralFeature feature, int position,
			EObject oldValue) {
		this.WriteStartChange("CompositionListDeletion", feature);
		this.WriteElement(oldValue, "deletedElement");
		this.WriteElement(affectedElement, "affectedElement");
		this.WritePosition(position);
		this.WriteSelfClosingChange();
	}

	private void WriteCompositionListInsertion(EObject affectedElement, EStructuralFeature feature, int position,
			EObject newValue) {
		this.WriteStartChange("CompositionListInsertion", feature);
		this.WriteElement(affectedElement, "affectedElement");
		this.WritePosition(position);
		buffer.append(">\n");
		this.WriteFullElement(newValue, "addedElement");
		this.WriteEndChange("CompositionListInsertion");
	}

	private void WriteAssociationPropertyChange(EObject affectedElement, EStructuralFeature feature, EObject oldValue, EObject newValue) {
		this.WriteStartChange("AssociationPropertyChange", feature);
		if (newValue != null) {
			this.WriteElement(newValue, "newValue");
		}
		if (oldValue != null) {
			this.WriteElement(oldValue, "oldValue");
		}
		this.WriteElement(affectedElement, "affectedElement");
		this.WriteSelfClosingChange();
	}

	private void WriteCompositionPropertyChange(EObject affectedElement, EStructuralFeature feature, EObject oldValue, EObject newValue) {
		this.WriteStartChange("CompositionPropertyChange", feature);
		this.WriteElement(affectedElement, "affectedElement");
		if (oldValue != null) {
			this.WriteElement(oldValue, "oldValue");
		}
		buffer.append(">\n");
		if (newValue != null) {
			this.WriteFullElement(newValue, "newValue");
		}
		this.WriteEndChange("CompositionPropertyChange");
	}

	private void WriteAttributeChange(Notification n, EObject affectedElement, EStructuralFeature feature) {
		if (feature.getUpperBound() == 1) {
			WriteAttributeValueChange(affectedElement, feature, n.getOldValue(), n.getNewValue());
		} else if (feature.isOrdered()) {
			int position = n.getPosition();
			if (n.getEventType() == Notification.ADD) {
				this.WriteStartChange("AttributeListInsertion", feature);
				this.WriteAttribute("addedValue", n.getNewStringValue());
			} else if (n.getEventType() == Notification.REMOVE) {
				this.WriteStartChange("AttributeListDeletion", feature);
				this.WriteAttribute("deletedValue", n.getOldStringValue());
			} else {
				buffer.append("(Change type not supported)");
			}
			this.WriteElement(affectedElement, "affectedElement");
			this.WritePosition(position);
			this.WriteSelfClosingChange();
		} else {
			if (n.getEventType() == Notification.ADD) {
				this.WriteStartChange("AttributeCollectionInsertion", feature);
				this.WriteAttribute("addedValue", n.getNewStringValue());
			} else if (n.getEventType() == Notification.REMOVE) {
				this.WriteStartChange("AttributeCollectionDeletion", feature);
				this.WriteAttribute("deletedValue", n.getOldStringValue());
			} else {
				System.out.println("Change type not supported");
			}
			this.WriteElement(affectedElement, "affectedElement");
			this.WriteSelfClosingChange();
		}
	}

	private void WriteAttributeValueChange(EObject affectedElement, EStructuralFeature feature, Object oldValue, Object newValue) {
		this.WriteStartChange("AttributePropertyChange", feature);
		if (newValue != null) {
			this.WriteAttribute("newValue", toCompatibleString(newValue));
		}
		if (oldValue != null) {
			this.WriteAttribute("oldValue", toCompatibleString(oldValue));
		}
		this.WriteElement(affectedElement, "affectedElement");
		this.WriteSelfClosingChange();
	}
    
    private void RegisterNewElement(EObject element, int changeNumber, String property) {
    	String uri = "#//@changes." + Integer.toString(changeNumber) + "/" + property;
    	uris.put(element, uri);
    }
    
    private void WriteFullElement(EObject element, String type) {
    	this.WriteFullElement(element, type, "@" + type);
    }
    
    @SuppressWarnings("unchecked")
	private void WriteFullElement(EObject element, String type, String path) {
    	buffer.append("\t\t<");
    	buffer.append(type);
    	buffer.append(" xsi:type=\"");
    	EClass eClass = element.eClass();
    	EPackage ePackage = (EPackage)eClass.eContainer();
    	buffer.append(ePackage.getNsPrefix());
    	buffer.append(":");
    	buffer.append(eClass.getName());
    	buffer.append("\" xmlns:");
    	buffer.append(ePackage.getNsPrefix());
    	buffer.append("=\"");
    	buffer.append(ePackage.getNsURI());
    	buffer.append("\"");
    	for (EAttribute att : eClass.getEAllAttributes()) {
    		if (element.eIsSet(att)) {
    			Object value = element.eGet(att);
    			WriteAttribute(att.getName(), toCompatibleString(value));
    		}
    	}
    	for (EReference re : eClass.getEAllReferences()) {
    		if (element.eIsSet(re)) {
    			if (!re.isContainer() && !re.isContainment() && element.eGet(re) instanceof EObject value) {
    				this.WriteElement(value, re.getName());
    			}
    		}
    	}
    	buffer.append(">\n");
    	for (EReference re : eClass.getEAllContainments()) {
    		if (element.eIsSet(re)) {
    			Object value = element.eGet(re);
    			if (re.getUpperBound() == 1) {
    				this.WriteFullElement((EObject)value, re.getName(), path + "/@" + re.getName());
    			} else {
    				List<EObject> list = (List<EObject>)value;
    				int index = 0;
    				for (EObject e : list) {
    					this.WriteFullElement(e, re.getName(), path + "/@" + re.getName() + "." + index);
    					index++;
    				}
    			}
    		}
    	}
    	buffer.append("\t\t</");
    	buffer.append(type);
    	buffer.append(">\n");
    	this.RegisterNewElement(element, this.changeCounter, path);
    }

	private String toCompatibleString(Object value) {
		String valueString;
		if (value instanceof Date) {
			valueString = compatibleFormat.format((Date)value);
		} else {
			valueString = value.toString();
		}
		return valueString;
	}
    
    private void WriteStartChange(String element, EStructuralFeature feature) {
    	buffer.append("\t<changes xsi:type=\"changes:" + element);
    	buffer.append("\" ");
    	buffer.append("feature=\"");
    	buffer.append(metamodel);
    	buffer.append("#//");
    	buffer.append(EcoreUtil.getRelativeURIFragmentPath(null, feature));
    	buffer.append("\"");
    	changeCounter = changeCounter + 1;
    }
    
    private void WriteElement(EObject affectedElement, String type) {
    	this.WriteAttribute(type, uris.get(affectedElement));
    }
    
    private void WriteAttribute(String attribute, String value) {
    	buffer.append(" ");
    	buffer.append(attribute);
    	buffer.append("=\"");
    	buffer.append(value);
    	buffer.append("\"");
    }
    
    private void WritePosition(int position) {
    	buffer.append(" index=\"");
    	buffer.append(position);
    	buffer.append("\"");
    }
    
    private void WriteSelfClosingChange() {
    	buffer.append(" />\n");
    }
    
    private void WriteEndChange(String element) {
    	buffer.append("\t</changes>\n");
    }
}