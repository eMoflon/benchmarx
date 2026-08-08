/**
 */
package pivot;

import Families.FamilyRegister;

import Persons.PersonRegister;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Post Condition</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link pivot.PostCondition#getOutput <em>Output</em>}</li>
 *   <li>{@link pivot.PostCondition#getInput <em>Input</em>}</li>
 *   <li>{@link pivot.PostCondition#getName <em>Name</em>}</li>
 * </ul>
 *
 * @see pivot.PivotPackage#getPostCondition()
 * @model annotation="meeduse constant=''"
 * @generated
 */
public interface PostCondition extends EObject {
	/**
	 * Returns the value of the '<em><b>Input</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Input</em>' reference.
	 * @see #setInput(FamilyRegister)
	 * @see pivot.PivotPackage#getPostCondition_Input()
	 * @model required="true"
	 *        annotation="meeduse constant='' association='inputPost'"
	 * @generated
	 */
	FamilyRegister getInput();

	/**
	 * Sets the value of the '{@link pivot.PostCondition#getInput <em>Input</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Input</em>' reference.
	 * @see #getInput()
	 * @generated
	 */
	void setInput(FamilyRegister value);

	/**
	 * Returns the value of the '<em><b>Output</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Output</em>' reference.
	 * @see #setOutput(PersonRegister)
	 * @see pivot.PivotPackage#getPostCondition_Output()
	 * @model required="true"
	 *        annotation="meeduse constant='' association='outputPost'"
	 * @generated
	 */
	PersonRegister getOutput();

	/**
	 * Sets the value of the '{@link pivot.PostCondition#getOutput <em>Output</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Output</em>' reference.
	 * @see #getOutput()
	 * @generated
	 */
	void setOutput(PersonRegister value);

	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see pivot.PivotPackage#getPostCondition_Name()
	 * @model required="true"
	 *        annotation="meeduse constant=''"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link pivot.PostCondition#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

} // PostCondition
