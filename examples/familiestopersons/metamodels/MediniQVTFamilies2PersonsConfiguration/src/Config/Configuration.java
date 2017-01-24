/**
 */
package Config;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Configuration</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link Config.Configuration#isFromPersonsToFamilies <em>From Persons To Families</em>}</li>
 *   <li>{@link Config.Configuration#isPreferParentToChild <em>Prefer Parent To Child</em>}</li>
 *   <li>{@link Config.Configuration#isPreferExistingToNewFamily <em>Prefer Existing To New Family</em>}</li>
 * </ul>
 *
 * @see Config.ConfigPackage#getConfiguration()
 * @model
 * @generated
 */
public interface Configuration extends EObject {
	/**
	 * Returns the value of the '<em><b>From Persons To Families</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>From Persons To Families</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>From Persons To Families</em>' attribute.
	 * @see #setFromPersonsToFamilies(boolean)
	 * @see Config.ConfigPackage#getConfiguration_FromPersonsToFamilies()
	 * @model
	 * @generated
	 */
	boolean isFromPersonsToFamilies();

	/**
	 * Sets the value of the '{@link Config.Configuration#isFromPersonsToFamilies <em>From Persons To Families</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>From Persons To Families</em>' attribute.
	 * @see #isFromPersonsToFamilies()
	 * @generated
	 */
	void setFromPersonsToFamilies(boolean value);

	/**
	 * Returns the value of the '<em><b>Prefer Parent To Child</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Prefer Parent To Child</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Prefer Parent To Child</em>' attribute.
	 * @see #setPreferParentToChild(boolean)
	 * @see Config.ConfigPackage#getConfiguration_PreferParentToChild()
	 * @model
	 * @generated
	 */
	boolean isPreferParentToChild();

	/**
	 * Sets the value of the '{@link Config.Configuration#isPreferParentToChild <em>Prefer Parent To Child</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Prefer Parent To Child</em>' attribute.
	 * @see #isPreferParentToChild()
	 * @generated
	 */
	void setPreferParentToChild(boolean value);

	/**
	 * Returns the value of the '<em><b>Prefer Existing To New Family</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Prefer Existing To New Family</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Prefer Existing To New Family</em>' attribute.
	 * @see #setPreferExistingToNewFamily(boolean)
	 * @see Config.ConfigPackage#getConfiguration_PreferExistingToNewFamily()
	 * @model
	 * @generated
	 */
	boolean isPreferExistingToNewFamily();

	/**
	 * Sets the value of the '{@link Config.Configuration#isPreferExistingToNewFamily <em>Prefer Existing To New Family</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Prefer Existing To New Family</em>' attribute.
	 * @see #isPreferExistingToNewFamily()
	 * @generated
	 */
	void setPreferExistingToNewFamily(boolean value);

} // Configuration
