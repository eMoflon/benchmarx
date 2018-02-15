/**
 */
package pdb2.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EcoreUtil;

import pdb2.Database;
import pdb2.Pdb2Package;
import pdb2.Person;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Person</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link pdb2.impl.PersonImpl#getName <em>Name</em>}</li>
 *   <li>{@link pdb2.impl.PersonImpl#getBirthday <em>Birthday</em>}</li>
 *   <li>{@link pdb2.impl.PersonImpl#getPlaceOfBirth <em>Place Of Birth</em>}</li>
 *   <li>{@link pdb2.impl.PersonImpl#getId <em>Id</em>}</li>
 *   <li>{@link pdb2.impl.PersonImpl#getDatabase <em>Database</em>}</li>
 *   <li>{@link pdb2.impl.PersonImpl#getIncrementalID <em>Incremental ID</em>}</li>
 * </ul>
 *
 * @generated
 */
public class PersonImpl extends MinimalEObjectImpl.Container implements Person {
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
	protected static final String BIRTHDAY_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getBirthday() <em>Birthday</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBirthday()
	 * @generated
	 * @ordered
	 */
	protected String birthday = BIRTHDAY_EDEFAULT;

	/**
	 * The default value of the '{@link #getPlaceOfBirth() <em>Place Of Birth</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPlaceOfBirth()
	 * @generated
	 * @ordered
	 */
	protected static final String PLACE_OF_BIRTH_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPlaceOfBirth() <em>Place Of Birth</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPlaceOfBirth()
	 * @generated
	 * @ordered
	 */
	protected String placeOfBirth = PLACE_OF_BIRTH_EDEFAULT;

	/**
	 * The default value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected static final String ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected String id = ID_EDEFAULT;

	/**
	 * The default value of the '{@link #getIncrementalID() <em>Incremental ID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIncrementalID()
	 * @generated
	 * @ordered
	 */
	protected static final String INCREMENTAL_ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getIncrementalID() <em>Incremental ID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIncrementalID()
	 * @generated
	 * @ordered
	 */
	protected String incrementalID = INCREMENTAL_ID_EDEFAULT;

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
		return Pdb2Package.Literals.PERSON;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Pdb2Package.PERSON__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getBirthday() {
		return birthday;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBirthday(String newBirthday) {
		String oldBirthday = birthday;
		birthday = newBirthday;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Pdb2Package.PERSON__BIRTHDAY, oldBirthday, birthday));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getPlaceOfBirth() {
		return placeOfBirth;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPlaceOfBirth(String newPlaceOfBirth) {
		String oldPlaceOfBirth = placeOfBirth;
		placeOfBirth = newPlaceOfBirth;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Pdb2Package.PERSON__PLACE_OF_BIRTH, oldPlaceOfBirth, placeOfBirth));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getId() {
		return id;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setId(String newId) {
		String oldId = id;
		id = newId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Pdb2Package.PERSON__ID, oldId, id));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Database getDatabase() {
		if (eContainerFeatureID() != Pdb2Package.PERSON__DATABASE) return null;
		return (Database)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetDatabase(Database newDatabase, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newDatabase, Pdb2Package.PERSON__DATABASE, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDatabase(Database newDatabase) {
		if (newDatabase != eInternalContainer() || (eContainerFeatureID() != Pdb2Package.PERSON__DATABASE && newDatabase != null)) {
			if (EcoreUtil.isAncestor(this, newDatabase))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newDatabase != null)
				msgs = ((InternalEObject)newDatabase).eInverseAdd(this, Pdb2Package.DATABASE__PERSONS, Database.class, msgs);
			msgs = basicSetDatabase(newDatabase, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Pdb2Package.PERSON__DATABASE, newDatabase, newDatabase));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getIncrementalID() {
		return incrementalID;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIncrementalID(String newIncrementalID) {
		String oldIncrementalID = incrementalID;
		incrementalID = newIncrementalID;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Pdb2Package.PERSON__INCREMENTAL_ID, oldIncrementalID, incrementalID));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case Pdb2Package.PERSON__DATABASE:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetDatabase((Database)otherEnd, msgs);
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
			case Pdb2Package.PERSON__DATABASE:
				return basicSetDatabase(null, msgs);
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
			case Pdb2Package.PERSON__DATABASE:
				return eInternalContainer().eInverseRemove(this, Pdb2Package.DATABASE__PERSONS, Database.class, msgs);
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
			case Pdb2Package.PERSON__NAME:
				return getName();
			case Pdb2Package.PERSON__BIRTHDAY:
				return getBirthday();
			case Pdb2Package.PERSON__PLACE_OF_BIRTH:
				return getPlaceOfBirth();
			case Pdb2Package.PERSON__ID:
				return getId();
			case Pdb2Package.PERSON__DATABASE:
				return getDatabase();
			case Pdb2Package.PERSON__INCREMENTAL_ID:
				return getIncrementalID();
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
			case Pdb2Package.PERSON__NAME:
				setName((String)newValue);
				return;
			case Pdb2Package.PERSON__BIRTHDAY:
				setBirthday((String)newValue);
				return;
			case Pdb2Package.PERSON__PLACE_OF_BIRTH:
				setPlaceOfBirth((String)newValue);
				return;
			case Pdb2Package.PERSON__ID:
				setId((String)newValue);
				return;
			case Pdb2Package.PERSON__DATABASE:
				setDatabase((Database)newValue);
				return;
			case Pdb2Package.PERSON__INCREMENTAL_ID:
				setIncrementalID((String)newValue);
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
			case Pdb2Package.PERSON__NAME:
				setName(NAME_EDEFAULT);
				return;
			case Pdb2Package.PERSON__BIRTHDAY:
				setBirthday(BIRTHDAY_EDEFAULT);
				return;
			case Pdb2Package.PERSON__PLACE_OF_BIRTH:
				setPlaceOfBirth(PLACE_OF_BIRTH_EDEFAULT);
				return;
			case Pdb2Package.PERSON__ID:
				setId(ID_EDEFAULT);
				return;
			case Pdb2Package.PERSON__DATABASE:
				setDatabase((Database)null);
				return;
			case Pdb2Package.PERSON__INCREMENTAL_ID:
				setIncrementalID(INCREMENTAL_ID_EDEFAULT);
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
			case Pdb2Package.PERSON__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case Pdb2Package.PERSON__BIRTHDAY:
				return BIRTHDAY_EDEFAULT == null ? birthday != null : !BIRTHDAY_EDEFAULT.equals(birthday);
			case Pdb2Package.PERSON__PLACE_OF_BIRTH:
				return PLACE_OF_BIRTH_EDEFAULT == null ? placeOfBirth != null : !PLACE_OF_BIRTH_EDEFAULT.equals(placeOfBirth);
			case Pdb2Package.PERSON__ID:
				return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
			case Pdb2Package.PERSON__DATABASE:
				return getDatabase() != null;
			case Pdb2Package.PERSON__INCREMENTAL_ID:
				return INCREMENTAL_ID_EDEFAULT == null ? incrementalID != null : !INCREMENTAL_ID_EDEFAULT.equals(incrementalID);
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
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (name: ");
		result.append(name);
		result.append(", birthday: ");
		result.append(birthday);
		result.append(", placeOfBirth: ");
		result.append(placeOfBirth);
		result.append(", id: ");
		result.append(id);
		result.append(", incrementalID: ");
		result.append(incrementalID);
		result.append(')');
		return result.toString();
	}

} //PersonImpl
