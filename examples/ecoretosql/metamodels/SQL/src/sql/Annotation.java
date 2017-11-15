/**
 */
package sql;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Annotation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link sql.Annotation#getOwningModelElement <em>Owning Model Element</em>}</li>
 *   <li>{@link sql.Annotation#getAnnotation <em>Annotation</em>}</li>
 * </ul>
 *
 * @see sql.SqlPackage#getAnnotation()
 * @model
 * @generated
 */
public interface Annotation extends EObject {
	/**
	 * Returns the value of the '<em><b>Owning Model Element</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link sql.ModelElement#getOwnedAnnotations <em>Owned Annotations</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owning Model Element</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Owning Model Element</em>' container reference.
	 * @see #setOwningModelElement(ModelElement)
	 * @see sql.SqlPackage#getAnnotation_OwningModelElement()
	 * @see sql.ModelElement#getOwnedAnnotations
	 * @model opposite="ownedAnnotations" required="true" transient="false"
	 * @generated
	 */
	ModelElement getOwningModelElement();

	/**
	 * Sets the value of the '{@link sql.Annotation#getOwningModelElement <em>Owning Model Element</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Owning Model Element</em>' container reference.
	 * @see #getOwningModelElement()
	 * @generated
	 */
	void setOwningModelElement(ModelElement value);

	/**
	 * Returns the value of the '<em><b>Annotation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Annotation</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Annotation</em>' attribute.
	 * @see #setAnnotation(String)
	 * @see sql.SqlPackage#getAnnotation_Annotation()
	 * @model required="true"
	 * @generated
	 */
	String getAnnotation();

	/**
	 * Sets the value of the '{@link sql.Annotation#getAnnotation <em>Annotation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Annotation</em>' attribute.
	 * @see #getAnnotation()
	 * @generated
	 */
	void setAnnotation(String value);

} // Annotation
