/**
 */
package Config.impl;

import Config.ConfigPackage;
import Config.Configuration;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Configuration</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link Config.impl.ConfigurationImpl#isFromPersonsToFamilies <em>From Persons To Families</em>}</li>
 *   <li>{@link Config.impl.ConfigurationImpl#isPreferParentToChild <em>Prefer Parent To Child</em>}</li>
 *   <li>{@link Config.impl.ConfigurationImpl#isPreferExistingToNewFamily <em>Prefer Existing To New Family</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ConfigurationImpl extends MinimalEObjectImpl.Container implements Configuration {
	/**
	 * The default value of the '{@link #isFromPersonsToFamilies() <em>From Persons To Families</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isFromPersonsToFamilies()
	 * @generated
	 * @ordered
	 */
	protected static final boolean FROM_PERSONS_TO_FAMILIES_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isFromPersonsToFamilies() <em>From Persons To Families</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isFromPersonsToFamilies()
	 * @generated
	 * @ordered
	 */
	protected boolean fromPersonsToFamilies = FROM_PERSONS_TO_FAMILIES_EDEFAULT;

	/**
	 * The default value of the '{@link #isPreferParentToChild() <em>Prefer Parent To Child</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isPreferParentToChild()
	 * @generated
	 * @ordered
	 */
	protected static final boolean PREFER_PARENT_TO_CHILD_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isPreferParentToChild() <em>Prefer Parent To Child</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isPreferParentToChild()
	 * @generated
	 * @ordered
	 */
	protected boolean preferParentToChild = PREFER_PARENT_TO_CHILD_EDEFAULT;

	/**
	 * The default value of the '{@link #isPreferExistingToNewFamily() <em>Prefer Existing To New Family</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isPreferExistingToNewFamily()
	 * @generated
	 * @ordered
	 */
	protected static final boolean PREFER_EXISTING_TO_NEW_FAMILY_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isPreferExistingToNewFamily() <em>Prefer Existing To New Family</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isPreferExistingToNewFamily()
	 * @generated
	 * @ordered
	 */
	protected boolean preferExistingToNewFamily = PREFER_EXISTING_TO_NEW_FAMILY_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ConfigurationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ConfigPackage.Literals.CONFIGURATION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isFromPersonsToFamilies() {
		return fromPersonsToFamilies;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFromPersonsToFamilies(boolean newFromPersonsToFamilies) {
		boolean oldFromPersonsToFamilies = fromPersonsToFamilies;
		fromPersonsToFamilies = newFromPersonsToFamilies;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConfigPackage.CONFIGURATION__FROM_PERSONS_TO_FAMILIES, oldFromPersonsToFamilies, fromPersonsToFamilies));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isPreferParentToChild() {
		return preferParentToChild;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPreferParentToChild(boolean newPreferParentToChild) {
		boolean oldPreferParentToChild = preferParentToChild;
		preferParentToChild = newPreferParentToChild;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConfigPackage.CONFIGURATION__PREFER_PARENT_TO_CHILD, oldPreferParentToChild, preferParentToChild));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isPreferExistingToNewFamily() {
		return preferExistingToNewFamily;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPreferExistingToNewFamily(boolean newPreferExistingToNewFamily) {
		boolean oldPreferExistingToNewFamily = preferExistingToNewFamily;
		preferExistingToNewFamily = newPreferExistingToNewFamily;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConfigPackage.CONFIGURATION__PREFER_EXISTING_TO_NEW_FAMILY, oldPreferExistingToNewFamily, preferExistingToNewFamily));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ConfigPackage.CONFIGURATION__FROM_PERSONS_TO_FAMILIES:
				return isFromPersonsToFamilies();
			case ConfigPackage.CONFIGURATION__PREFER_PARENT_TO_CHILD:
				return isPreferParentToChild();
			case ConfigPackage.CONFIGURATION__PREFER_EXISTING_TO_NEW_FAMILY:
				return isPreferExistingToNewFamily();
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
			case ConfigPackage.CONFIGURATION__FROM_PERSONS_TO_FAMILIES:
				setFromPersonsToFamilies((Boolean)newValue);
				return;
			case ConfigPackage.CONFIGURATION__PREFER_PARENT_TO_CHILD:
				setPreferParentToChild((Boolean)newValue);
				return;
			case ConfigPackage.CONFIGURATION__PREFER_EXISTING_TO_NEW_FAMILY:
				setPreferExistingToNewFamily((Boolean)newValue);
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
			case ConfigPackage.CONFIGURATION__FROM_PERSONS_TO_FAMILIES:
				setFromPersonsToFamilies(FROM_PERSONS_TO_FAMILIES_EDEFAULT);
				return;
			case ConfigPackage.CONFIGURATION__PREFER_PARENT_TO_CHILD:
				setPreferParentToChild(PREFER_PARENT_TO_CHILD_EDEFAULT);
				return;
			case ConfigPackage.CONFIGURATION__PREFER_EXISTING_TO_NEW_FAMILY:
				setPreferExistingToNewFamily(PREFER_EXISTING_TO_NEW_FAMILY_EDEFAULT);
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
			case ConfigPackage.CONFIGURATION__FROM_PERSONS_TO_FAMILIES:
				return fromPersonsToFamilies != FROM_PERSONS_TO_FAMILIES_EDEFAULT;
			case ConfigPackage.CONFIGURATION__PREFER_PARENT_TO_CHILD:
				return preferParentToChild != PREFER_PARENT_TO_CHILD_EDEFAULT;
			case ConfigPackage.CONFIGURATION__PREFER_EXISTING_TO_NEW_FAMILY:
				return preferExistingToNewFamily != PREFER_EXISTING_TO_NEW_FAMILY_EDEFAULT;
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
		result.append(" (fromPersonsToFamilies: ");
		result.append(fromPersonsToFamilies);
		result.append(", preferParentToChild: ");
		result.append(preferParentToChild);
		result.append(", preferExistingToNewFamily: ");
		result.append(preferExistingToNewFamily);
		result.append(')');
		return result.toString();
	}

} //ConfigurationImpl
