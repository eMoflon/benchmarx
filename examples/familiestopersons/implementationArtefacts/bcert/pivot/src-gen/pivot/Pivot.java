/**
 */
package pivot;

import Families.FamilyRegister;

import Persons.PersonRegister;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Pivot</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link pivot.Pivot#getFamilyModel <em>Family Model</em>}</li>
 *   <li>{@link pivot.Pivot#getPersonModel <em>Person Model</em>}</li>
 *   <li>{@link pivot.Pivot#getPre <em>Pre</em>}</li>
 *   <li>{@link pivot.Pivot#getPost <em>Post</em>}</li>
 *   <li>{@link pivot.Pivot#getMapping <em>Mapping</em>}</li>
 *   <li>{@link pivot.Pivot#getName <em>Name</em>}</li>
 *   <li>{@link pivot.Pivot#getStrategie <em>Strategie</em>}</li>
 *   <li>{@link pivot.Pivot#isFAMILY_TO_NEW <em>FAMILY TO NEW</em>}</li>
 *   <li>{@link pivot.Pivot#isPARENT_TO_CHILD <em>PARENT TO CHILD</em>}</li>
 *   <li>{@link pivot.Pivot#isSync <em>Sync</em>}</li>
 * </ul>
 *
 * @see pivot.PivotPackage#getPivot()
 * @model annotation="meeduse constant='null'"
 * @generated
 */
public interface Pivot extends EObject {
	/**
	 * Returns the value of the '<em><b>Family Model</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Family Model</em>' reference.
	 * @see #setFamilyModel(FamilyRegister)
	 * @see pivot.PivotPackage#getPivot_FamilyModel()
	 * @model required="true"
	 *        annotation="meeduse association='familyModel' constant='null'"
	 * @generated
	 */
	FamilyRegister getFamilyModel();

	/**
	 * Sets the value of the '{@link pivot.Pivot#getFamilyModel <em>Family Model</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Family Model</em>' reference.
	 * @see #getFamilyModel()
	 * @generated
	 */
	void setFamilyModel(FamilyRegister value);

	/**
	 * Returns the value of the '<em><b>Person Model</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Person Model</em>' reference.
	 * @see #setPersonModel(PersonRegister)
	 * @see pivot.PivotPackage#getPivot_PersonModel()
	 * @model required="true"
	 *        annotation="meeduse association='personModel' constant='null'"
	 * @generated
	 */
	PersonRegister getPersonModel();

	/**
	 * Sets the value of the '{@link pivot.Pivot#getPersonModel <em>Person Model</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Person Model</em>' reference.
	 * @see #getPersonModel()
	 * @generated
	 */
	void setPersonModel(PersonRegister value);

	/**
	 * Returns the value of the '<em><b>Pre</b></em>' containment reference list.
	 * The list contents are of type {@link pivot.PreCondition}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Pre</em>' containment reference list.
	 * @see pivot.PivotPackage#getPivot_Pre()
	 * @model containment="true"
	 *        annotation="meeduse association='pre'"
	 * @generated
	 */
	EList<PreCondition> getPre();

	/**
	 * Returns the value of the '<em><b>Post</b></em>' containment reference list.
	 * The list contents are of type {@link pivot.PostCondition}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Post</em>' containment reference list.
	 * @see pivot.PivotPackage#getPivot_Post()
	 * @model containment="true"
	 *        annotation="meeduse association='post'"
	 * @generated
	 */
	EList<PostCondition> getPost();

	/**
	 * Returns the value of the '<em><b>Mapping</b></em>' containment reference list.
	 * The list contents are of type {@link pivot.Mapping}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Mapping</em>' containment reference list.
	 * @see pivot.PivotPackage#getPivot_Mapping()
	 * @model containment="true"
	 *        annotation="meeduse association='mapping' opposite-lower='1'"
	 * @generated
	 */
	EList<Mapping> getMapping();

	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see pivot.PivotPackage#getPivot_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link pivot.Pivot#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Strategie</b></em>' attribute.
	 * The literals are from the enumeration {@link pivot.Strategy}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Strategie</em>' attribute.
	 * @see pivot.Strategy
	 * @see #setStrategie(Strategy)
	 * @see pivot.PivotPackage#getPivot_Strategie()
	 * @model required="true"
	 * @generated
	 */
	Strategy getStrategie();

	/**
	 * Sets the value of the '{@link pivot.Pivot#getStrategie <em>Strategie</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Strategie</em>' attribute.
	 * @see pivot.Strategy
	 * @see #getStrategie()
	 * @generated
	 */
	void setStrategie(Strategy value);

	/**
	 * Returns the value of the '<em><b>FAMILY TO NEW</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>FAMILY TO NEW</em>' attribute.
	 * @see #setFAMILY_TO_NEW(boolean)
	 * @see pivot.PivotPackage#getPivot_FAMILY_TO_NEW()
	 * @model required="true"
	 * @generated
	 */
	boolean isFAMILY_TO_NEW();

	/**
	 * Sets the value of the '{@link pivot.Pivot#isFAMILY_TO_NEW <em>FAMILY TO NEW</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>FAMILY TO NEW</em>' attribute.
	 * @see #isFAMILY_TO_NEW()
	 * @generated
	 */
	void setFAMILY_TO_NEW(boolean value);

	/**
	 * Returns the value of the '<em><b>PARENT TO CHILD</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>PARENT TO CHILD</em>' attribute.
	 * @see #setPARENT_TO_CHILD(boolean)
	 * @see pivot.PivotPackage#getPivot_PARENT_TO_CHILD()
	 * @model required="true"
	 * @generated
	 */
	boolean isPARENT_TO_CHILD();

	/**
	 * Sets the value of the '{@link pivot.Pivot#isPARENT_TO_CHILD <em>PARENT TO CHILD</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>PARENT TO CHILD</em>' attribute.
	 * @see #isPARENT_TO_CHILD()
	 * @generated
	 */
	void setPARENT_TO_CHILD(boolean value);

	/**
	 * Returns the value of the '<em><b>Sync</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Sync</em>' attribute.
	 * @see #setSync(boolean)
	 * @see pivot.PivotPackage#getPivot_Sync()
	 * @model required="true"
	 * @generated
	 */
	boolean isSync();

	/**
	 * Sets the value of the '{@link pivot.Pivot#isSync <em>Sync</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Sync</em>' attribute.
	 * @see #isSync()
	 * @generated
	 */
	void setSync(boolean value);

} // Pivot
