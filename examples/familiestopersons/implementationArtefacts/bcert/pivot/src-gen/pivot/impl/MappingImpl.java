/**
 */
package pivot.impl;

import Families.FamilyMember;

import Persons.Person;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import pivot.Mapping;
import pivot.PivotPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Mapping</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link pivot.impl.MappingImpl#getMMap <em>MMap</em>}</li>
 *   <li>{@link pivot.impl.MappingImpl#getPMap <em>PMap</em>}</li>
 * </ul>
 *
 * @generated
 */
public class MappingImpl extends EObjectImpl implements Mapping {
	/**
	 * The cached value of the '{@link #getMMap() <em>MMap</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMMap()
	 * @generated
	 * @ordered
	 */
	protected FamilyMember mMap;

	/**
	 * The cached value of the '{@link #getPMap() <em>PMap</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPMap()
	 * @generated
	 * @ordered
	 */
	protected Person pMap;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected MappingImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return PivotPackage.Literals.MAPPING;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public FamilyMember getMMap() {
		if (mMap != null && mMap.eIsProxy()) {
			InternalEObject oldMMap = (InternalEObject)mMap;
			mMap = (FamilyMember)eResolveProxy(oldMMap);
			if (mMap != oldMMap) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, PivotPackage.MAPPING__MMAP, oldMMap, mMap));
			}
		}
		return mMap;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FamilyMember basicGetMMap() {
		return mMap;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setMMap(FamilyMember newMMap) {
		FamilyMember oldMMap = mMap;
		mMap = newMMap;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PivotPackage.MAPPING__MMAP, oldMMap, mMap));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Person getPMap() {
		if (pMap != null && pMap.eIsProxy()) {
			InternalEObject oldPMap = (InternalEObject)pMap;
			pMap = (Person)eResolveProxy(oldPMap);
			if (pMap != oldPMap) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, PivotPackage.MAPPING__PMAP, oldPMap, pMap));
			}
		}
		return pMap;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Person basicGetPMap() {
		return pMap;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setPMap(Person newPMap) {
		Person oldPMap = pMap;
		pMap = newPMap;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PivotPackage.MAPPING__PMAP, oldPMap, pMap));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case PivotPackage.MAPPING__MMAP:
				if (resolve) return getMMap();
				return basicGetMMap();
			case PivotPackage.MAPPING__PMAP:
				if (resolve) return getPMap();
				return basicGetPMap();
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
			case PivotPackage.MAPPING__MMAP:
				setMMap((FamilyMember)newValue);
				return;
			case PivotPackage.MAPPING__PMAP:
				setPMap((Person)newValue);
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
			case PivotPackage.MAPPING__MMAP:
				setMMap((FamilyMember)null);
				return;
			case PivotPackage.MAPPING__PMAP:
				setPMap((Person)null);
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
			case PivotPackage.MAPPING__MMAP:
				return mMap != null;
			case PivotPackage.MAPPING__PMAP:
				return pMap != null;
		}
		return super.eIsSet(featureID);
	}

} //MappingImpl
