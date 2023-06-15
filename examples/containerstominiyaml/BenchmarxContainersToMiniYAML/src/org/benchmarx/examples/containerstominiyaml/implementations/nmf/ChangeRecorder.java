package org.benchmarx.examples.containerstominiyaml.implementations.nmf;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EReference;

import containers.*;

class ChangeRecorder extends org.eclipse.emf.ecore.util.EContentAdapter {
	
	private static SimpleDateFormat compatibleFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
	
	private EObject root;
	private String metamodel;
	private String modelPath;
	private StringBuffer buffer = new StringBuffer();
	private int changeCounter = -1;
	private HashMap<EObject, String> uris = new HashMap<EObject, String>();

    public void observeComposition(Composition r){
        r.eAdapters().add(this);
        root = r;
        modelPath = "ttc:source";
        metamodel = "http://york.ac.uk/ttc/containers/1.0.0";
    	this.registerDescendants(r);
    	this.writeStart();
    }
    
    public void observeMiniYaml(miniyaml.Map r) {
    	r.eAdapters().add(this);
    	root = r;
    	modelPath = "ttc:target";
    	metamodel = "http://york.ac.uk/ttc/miniyaml/1.0.0";
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
        	if (reference.getUpperBound() == 1) {
        		if (reference.isContainment()) {
        			this.WriteStartChange("CompositionChange", feature);
            		this.WriteElement(affectedElement, "affectedElement");
            		if (n.getOldValue() != null) {
            			this.WriteElement((EObject)n.getOldValue(), "oldValue");
            		}
            		buffer.append(">\n");
        			if (n.getNewValue() != null) {
            			this.WriteFullElement((EObject)n.getNewValue(), "newValue");
            		}
        			this.WriteEndChange("CompositionChange");
        		} else {
        			if (reference.isContainer()) return;
        			this.WriteStartChange("AssociationChange", feature);
            		if (n.getNewValue() != null) {
            			this.WriteElement((EObject)n.getNewValue(), "newValue");
            		}
            		if (n.getOldValue() != null) {
            			this.WriteElement((EObject)n.getOldValue(), "oldValue");
            		}
            		this.WriteElement(affectedElement, "affectedElement");
            		this.WriteSelfClosingChange();
        		}
        	}
        	else if (reference.isOrdered()) {
        		int position = n.getPosition();
        		if (reference.isContainment()) {
        			if (n.getEventType() == Notification.ADD) {
        				this.WriteStartChange("CompositionListInsertion", feature);
        				this.WriteElement(affectedElement, "affectedElement");
        				this.WritePosition(position);
        				buffer.append(">\n");
        				this.WriteFullElement((EObject)n.getNewValue(), "addedElement");
        				this.WriteEndChange("CompositionListInsertion");
        			} else if (n.getEventType() == Notification.REMOVE) {
        				this.WriteStartChange("CompositionListDeletion", feature);
        				this.WriteElement((EObject)n.getOldValue(), "deletedElement");
        				this.WriteElement(affectedElement, "affectedElement");
        				this.WritePosition(position);
        				this.WriteSelfClosingChange();
        			} else {
        				buffer.append("(Change type not supported)");
        			}
        		} else {
        			if (reference.isContainer()) return;
        			if (n.getEventType() == Notification.ADD) {
        				this.WriteStartChange("AssociationListInsertion", feature);
        				this.WriteElement((EObject)n.getNewValue(), "addedElement");
        			} else if (n.getEventType() == Notification.REMOVE) {
        				this.WriteStartChange("AssociationListDeletion", feature);
        				this.WriteElement((EObject)n.getOldValue(), "deletedElement");
        			} else {
        				buffer.append("(Change type not supported)");
        			}
        			this.WriteElement(affectedElement, "affectedElement");
        			this.WritePosition(position);
        			this.WriteSelfClosingChange();
        		}
        	} else {
        		if (reference.isContainment()) {
        			if (n.getEventType() == Notification.ADD) {
        				this.WriteStartChange("CompositionCollectionInsertion", feature);
        				this.WriteElement(affectedElement, "affectedElement");
        				buffer.append(">\n");
        				this.WriteFullElement((EObject)n.getNewValue(), "addedElement");
        				this.WriteEndChange("CompositionCollectionInsertion");
        			} else if (n.getEventType() == Notification.REMOVE) {
        				this.WriteStartChange("CompositionCollectionDeletion", feature);
        				this.WriteElement((EObject)n.getOldValue(), "deletedElement");
        				this.WriteElement(affectedElement, "affectedElement");
        				this.WriteSelfClosingChange();
        			} else {
        				System.out.println("Change type not supported");
        			}
        		} else {
        			if (n.getEventType() == Notification.ADD) {
        				this.WriteStartChange("AssociationCollectionInsertion", feature);
        				this.WriteElement((EObject)n.getNewValue(), "addedElement");
        			} else if (n.getEventType() == Notification.REMOVE) {
        				this.WriteStartChange("AssociationCollectionDeletion", feature);
        				this.WriteElement((EObject)n.getOldValue(), "deletedElement");
        			} else {
        				buffer.append("(Change type not supported)");
        			}
        			this.WriteElement(affectedElement, "affectedElement");
        			this.WriteSelfClosingChange();
        		}
        	}
        } else {
        	if (feature.getUpperBound() == 1) {
        		this.WriteStartChange("AttributeChange", feature);
        		if (n.getNewStringValue() != null) {
        			this.WriteAttribute("newValue", toCompatibleString(n.getNewValue()));
        		}
        		if (n.getOldStringValue() != null) {
        			this.WriteAttribute("oldValue", toCompatibleString(n.getOldValue()));
        		}
        		this.WriteElement(affectedElement, "affectedElement");
        		this.WriteSelfClosingChange();
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
        
    }
    
    private void RegisterNewElement(EObject element, int changeNumber, String property) {
    	String uri = "#//changes." + Integer.toString(changeNumber) + "/" + property;
    	uris.put(element, uri);
    }
    
    private void WriteFullElement(EObject element, String type) {
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
    	buffer.append(" />\n");
    	
    	this.RegisterNewElement(element, this.changeCounter, type);
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