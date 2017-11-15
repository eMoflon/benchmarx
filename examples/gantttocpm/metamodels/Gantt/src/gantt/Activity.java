/**
 */
package gantt;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Activity</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link gantt.Activity#getName <em>Name</em>}</li>
 *   <li>{@link gantt.Activity#getDuration <em>Duration</em>}</li>
 *   <li>{@link gantt.Activity#getOutgoingDependencies <em>Outgoing Dependencies</em>}</li>
 *   <li>{@link gantt.Activity#getIncomingDependencies <em>Incoming Dependencies</em>}</li>
 * </ul>
 *
 * @see gantt.GanttPackage#getActivity()
 * @model
 * @generated
 */
public interface Activity extends Element {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see gantt.GanttPackage#getActivity_Name()
	 * @model required="true"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link gantt.Activity#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Duration</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Duration</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Duration</em>' attribute.
	 * @see #setDuration(int)
	 * @see gantt.GanttPackage#getActivity_Duration()
	 * @model required="true"
	 * @generated
	 */
	int getDuration();

	/**
	 * Sets the value of the '{@link gantt.Activity#getDuration <em>Duration</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Duration</em>' attribute.
	 * @see #getDuration()
	 * @generated
	 */
	void setDuration(int value);

	/**
	 * Returns the value of the '<em><b>Outgoing Dependencies</b></em>' reference list.
	 * The list contents are of type {@link gantt.Dependency}.
	 * It is bidirectional and its opposite is '{@link gantt.Dependency#getPredecessor <em>Predecessor</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Outgoing Dependencies</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Outgoing Dependencies</em>' reference list.
	 * @see gantt.GanttPackage#getActivity_OutgoingDependencies()
	 * @see gantt.Dependency#getPredecessor
	 * @model opposite="predecessor"
	 * @generated
	 */
	EList<Dependency> getOutgoingDependencies();

	/**
	 * Returns the value of the '<em><b>Incoming Dependencies</b></em>' reference list.
	 * The list contents are of type {@link gantt.Dependency}.
	 * It is bidirectional and its opposite is '{@link gantt.Dependency#getSuccessor <em>Successor</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Incoming Dependencies</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Incoming Dependencies</em>' reference list.
	 * @see gantt.GanttPackage#getActivity_IncomingDependencies()
	 * @see gantt.Dependency#getSuccessor
	 * @model opposite="successor"
	 * @generated
	 */
	EList<Dependency> getIncomingDependencies();

} // Activity
