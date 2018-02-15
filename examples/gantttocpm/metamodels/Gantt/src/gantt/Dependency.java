/**
 */
package gantt;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Dependency</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link gantt.Dependency#getPredecessor <em>Predecessor</em>}</li>
 *   <li>{@link gantt.Dependency#getSuccessor <em>Successor</em>}</li>
 *   <li>{@link gantt.Dependency#getDependencyType <em>Dependency Type</em>}</li>
 *   <li>{@link gantt.Dependency#getOffset <em>Offset</em>}</li>
 * </ul>
 *
 * @see gantt.GanttPackage#getDependency()
 * @model
 * @generated
 */
public interface Dependency extends Element {
	/**
	 * Returns the value of the '<em><b>Predecessor</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link gantt.Activity#getOutgoingDependencies <em>Outgoing Dependencies</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Predecessor</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Predecessor</em>' reference.
	 * @see #setPredecessor(Activity)
	 * @see gantt.GanttPackage#getDependency_Predecessor()
	 * @see gantt.Activity#getOutgoingDependencies
	 * @model opposite="outgoingDependencies" required="true"
	 * @generated
	 */
	Activity getPredecessor();

	/**
	 * Sets the value of the '{@link gantt.Dependency#getPredecessor <em>Predecessor</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Predecessor</em>' reference.
	 * @see #getPredecessor()
	 * @generated
	 */
	void setPredecessor(Activity value);

	/**
	 * Returns the value of the '<em><b>Successor</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link gantt.Activity#getIncomingDependencies <em>Incoming Dependencies</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Successor</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Successor</em>' reference.
	 * @see #setSuccessor(Activity)
	 * @see gantt.GanttPackage#getDependency_Successor()
	 * @see gantt.Activity#getIncomingDependencies
	 * @model opposite="incomingDependencies" required="true"
	 * @generated
	 */
	Activity getSuccessor();

	/**
	 * Sets the value of the '{@link gantt.Dependency#getSuccessor <em>Successor</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Successor</em>' reference.
	 * @see #getSuccessor()
	 * @generated
	 */
	void setSuccessor(Activity value);

	/**
	 * Returns the value of the '<em><b>Dependency Type</b></em>' attribute.
	 * The literals are from the enumeration {@link gantt.DependencyType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Dependency Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Dependency Type</em>' attribute.
	 * @see gantt.DependencyType
	 * @see #setDependencyType(DependencyType)
	 * @see gantt.GanttPackage#getDependency_DependencyType()
	 * @model required="true"
	 * @generated
	 */
	DependencyType getDependencyType();

	/**
	 * Sets the value of the '{@link gantt.Dependency#getDependencyType <em>Dependency Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Dependency Type</em>' attribute.
	 * @see gantt.DependencyType
	 * @see #getDependencyType()
	 * @generated
	 */
	void setDependencyType(DependencyType value);

	/**
	 * Returns the value of the '<em><b>Offset</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Offset</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Offset</em>' attribute.
	 * @see #setOffset(int)
	 * @see gantt.GanttPackage#getDependency_Offset()
	 * @model required="true"
	 * @generated
	 */
	int getOffset();

	/**
	 * Sets the value of the '{@link gantt.Dependency#getOffset <em>Offset</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Offset</em>' attribute.
	 * @see #getOffset()
	 * @generated
	 */
	void setOffset(int value);

} // Dependency
