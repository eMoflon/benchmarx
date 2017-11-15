/**
 */
package pnw.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;

import pnw.Net;
import pnw.NetElement;
import pnw.PnwPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Net</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link pnw.impl.NetImpl#getElements <em>Elements</em>}</li>
 *   <li>{@link pnw.impl.NetImpl#getIncrementalID <em>Incremental ID</em>}</li>
 * </ul>
 *
 * @generated
 */
public class NetImpl extends NamedElementImpl implements Net {
	/**
	 * The cached value of the '{@link #getElements() <em>Elements</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getElements()
	 * @generated
	 * @ordered
	 */
	protected EList<NetElement> elements;

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
	protected NetImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return PnwPackage.Literals.NET;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<NetElement> getElements() {
		if (elements == null) {
			elements = new EObjectContainmentWithInverseEList<NetElement>(NetElement.class, this, PnwPackage.NET__ELEMENTS, PnwPackage.NET_ELEMENT__NET);
		}
		return elements;
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
			eNotify(new ENotificationImpl(this, Notification.SET, PnwPackage.NET__INCREMENTAL_ID, oldIncrementalID, incrementalID));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case PnwPackage.NET__ELEMENTS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getElements()).basicAdd(otherEnd, msgs);
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
			case PnwPackage.NET__ELEMENTS:
				return ((InternalEList<?>)getElements()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case PnwPackage.NET__ELEMENTS:
				return getElements();
			case PnwPackage.NET__INCREMENTAL_ID:
				return getIncrementalID();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case PnwPackage.NET__ELEMENTS:
				getElements().clear();
				getElements().addAll((Collection<? extends NetElement>)newValue);
				return;
			case PnwPackage.NET__INCREMENTAL_ID:
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
			case PnwPackage.NET__ELEMENTS:
				getElements().clear();
				return;
			case PnwPackage.NET__INCREMENTAL_ID:
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
			case PnwPackage.NET__ELEMENTS:
				return elements != null && !elements.isEmpty();
			case PnwPackage.NET__INCREMENTAL_ID:
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
		result.append(" (incrementalID: ");
		result.append(incrementalID);
		result.append(')');
		return result.toString();
	}

} //NetImpl
