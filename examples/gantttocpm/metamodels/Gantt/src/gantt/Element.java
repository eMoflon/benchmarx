/**
 */
package gantt;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link gantt.Element#getDiagram <em>Diagram</em>}</li>
 * </ul>
 *
 * @see gantt.GanttPackage#getElement()
 * @model abstract="true"
 * @generated
 */
public interface Element extends EObject {
	/**
	 * Returns the value of the '<em><b>Diagram</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link gantt.GanttDiagram#getElements <em>Elements</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Diagram</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Diagram</em>' container reference.
	 * @see #setDiagram(GanttDiagram)
	 * @see gantt.GanttPackage#getElement_Diagram()
	 * @see gantt.GanttDiagram#getElements
	 * @model opposite="elements" required="true" transient="false"
	 * @generated
	 */
	GanttDiagram getDiagram();

	/**
	 * Sets the value of the '{@link gantt.Element#getDiagram <em>Diagram</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Diagram</em>' container reference.
	 * @see #getDiagram()
	 * @generated
	 */
	void setDiagram(GanttDiagram value);

} // Element
