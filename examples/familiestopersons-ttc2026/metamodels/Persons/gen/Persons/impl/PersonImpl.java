/**
 */
package Persons.impl;

import Persons.Person;
import Persons.PersonRegister;
import Persons.PersonsPackage;

import java.util.Date;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EcoreUtil;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Person</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link Persons.impl.PersonImpl#getName <em>Name</em>}</li>
 *   <li>{@link Persons.impl.PersonImpl#getBirthday <em>Birthday</em>}</li>
 *   <li>{@link Persons.impl.PersonImpl#getPersonsInverse <em>Persons Inverse</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class PersonImpl extends EObjectImpl implements Person {
	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getBirthday() <em>Birthday</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBirthday()
	 * @generated
	 * @ordered
	 */
	protected static final Date BIRTHDAY_EDEFAULT = (Date) EcoreFactory.eINSTANCE
			.createFromString(EcorePackage.eINSTANCE.getEDate(), "0000-1-1");

	/**
	 * The cached value of the '{@link #getBirthday() <em>Birthday</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBirthday()
	 * @generated
	 * @ordered
	 */
	protected Date birthday = BIRTHDAY_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PersonImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return PersonsPackage.Literals.PERSON;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PersonsPackage.PERSON__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Date getBirthday() {
		return birthday;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setBirthday(Date newBirthday) {
		Date oldBirthday = birthday;
		birthday = newBirthday;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PersonsPackage.PERSON__BIRTHDAY, oldBirthday,
					birthday));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public PersonRegister getPersonsInverse() {
		if (eContainerFeatureID() != PersonsPackage.PERSON__PERSONS_INVERSE)
			return null;
		return (PersonRegister) eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetPersonsInverse(PersonRegister newPersonsInverse, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject) newPersonsInverse, PersonsPackage.PERSON__PERSONS_INVERSE, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setPersonsInverse(PersonRegister newPersonsInverse) {
		if (newPersonsInverse != eInternalContainer()
				|| (eContainerFeatureID() != PersonsPackage.PERSON__PERSONS_INVERSE && newPersonsInverse != null)) {
			if (EcoreUtil.isAncestor(this, newPersonsInverse))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newPersonsInverse != null)
				msgs = ((InternalEObject) newPersonsInverse).eInverseAdd(this, PersonsPackage.PERSON_REGISTER__PERSONS,
						PersonRegister.class, msgs);
			msgs = basicSetPersonsInverse(newPersonsInverse, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PersonsPackage.PERSON__PERSONS_INVERSE,
					newPersonsInverse, newPersonsInverse));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case PersonsPackage.PERSON__PERSONS_INVERSE:
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			return basicSetPersonsInverse((PersonRegister) otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case PersonsPackage.PERSON__PERSONS_INVERSE:
			return basicSetPersonsInverse(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
		switch (eContainerFeatureID()) {
		case PersonsPackage.PERSON__PERSONS_INVERSE:
			return eInternalContainer().eInverseRemove(this, PersonsPackage.PERSON_REGISTER__PERSONS,
					PersonRegister.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case PersonsPackage.PERSON__NAME:
			return getName();
		case PersonsPackage.PERSON__BIRTHDAY:
			return getBirthday();
		case PersonsPackage.PERSON__PERSONS_INVERSE:
			return getPersonsInverse();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
		case PersonsPackage.PERSON__NAME:
			setName((String) newValue);
			return;
		case PersonsPackage.PERSON__BIRTHDAY:
			setBirthday((Date) newValue);
			return;
		case PersonsPackage.PERSON__PERSONS_INVERSE:
			setPersonsInverse((PersonRegister) newValue);
			return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
		case PersonsPackage.PERSON__NAME:
			setName(NAME_EDEFAULT);
			return;
		case PersonsPackage.PERSON__BIRTHDAY:
			setBirthday(BIRTHDAY_EDEFAULT);
			return;
		case PersonsPackage.PERSON__PERSONS_INVERSE:
			setPersonsInverse((PersonRegister) null);
			return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
		case PersonsPackage.PERSON__NAME:
			return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
		case PersonsPackage.PERSON__BIRTHDAY:
			return BIRTHDAY_EDEFAULT == null ? birthday != null : !BIRTHDAY_EDEFAULT.equals(birthday);
		case PersonsPackage.PERSON__PERSONS_INVERSE:
			return getPersonsInverse() != null;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy())
			return super.toString();

		StringBuilder result = new StringBuilder(super.toString());
		result.append(" (name: ");
		result.append(name);
		result.append(", birthday: ");
		result.append(birthday);
		result.append(')');
		return result.toString();
	}

} //PersonImpl
