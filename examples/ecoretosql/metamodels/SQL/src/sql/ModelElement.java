/**
 */
package sql;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Model Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link sql.ModelElement#getOwnedAnnotations <em>Owned Annotations</em>}</li>
 * </ul>
 *
 * @see sql.SqlPackage#getModelElement()
 * @model abstract="true"
 * @generated
 */
public interface ModelElement extends EObject {
	/**
	 * Returns the value of the '<em><b>Owned Annotations</b></em>' containment reference list.
	 * The list contents are of type {@link sql.Annotation}.
	 * It is bidirectional and its opposite is '{@link sql.Annotation#getOwningModelElement <em>Owning Model Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Annotations</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Owned Annotations</em>' containment reference list.
	 * @see sql.SqlPackage#getModelElement_OwnedAnnotations()
	 * @see sql.Annotation#getOwningModelElement
	 * @model opposite="owningModelElement" containment="true"
	 * @generated
	 */
	EList<Annotation> getOwnedAnnotations();

} // ModelElement
