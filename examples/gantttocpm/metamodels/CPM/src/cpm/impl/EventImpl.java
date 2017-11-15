/**
 */
package cpm.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

import cpm.Activity;
import cpm.CpmPackage;
import cpm.Event;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Event</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link cpm.impl.EventImpl#getOutgoingActivities <em>Outgoing Activities</em>}</li>
 *   <li>{@link cpm.impl.EventImpl#getIncomingActivities <em>Incoming Activities</em>}</li>
 *   <li>{@link cpm.impl.EventImpl#getNumber <em>Number</em>}</li>
 * </ul>
 *
 * @generated
 */
public class EventImpl extends ElementImpl implements Event {
	/**
	 * The cached value of the '{@link #getOutgoingActivities() <em>Outgoing Activities</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOutgoingActivities()
	 * @generated
	 * @ordered
	 */
	protected EList<Activity> outgoingActivities;

	/**
	 * The cached value of the '{@link #getIncomingActivities() <em>Incoming Activities</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIncomingActivities()
	 * @generated
	 * @ordered
	 */
	protected EList<Activity> incomingActivities;

	/**
	 * The default value of the '{@link #getNumber() <em>Number</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNumber()
	 * @generated
	 * @ordered
	 */
	protected static final int NUMBER_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getNumber() <em>Number</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNumber()
	 * @generated
	 * @ordered
	 */
	protected int number = NUMBER_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EventImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CpmPackage.Literals.EVENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Activity> getOutgoingActivities() {
		if (outgoingActivities == null) {
			outgoingActivities = new EObjectWithInverseResolvingEList<Activity>(Activity.class, this, CpmPackage.EVENT__OUTGOING_ACTIVITIES, CpmPackage.ACTIVITY__SOURCE_EVENT);
		}
		return outgoingActivities;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Activity> getIncomingActivities() {
		if (incomingActivities == null) {
			incomingActivities = new EObjectWithInverseResolvingEList<Activity>(Activity.class, this, CpmPackage.EVENT__INCOMING_ACTIVITIES, CpmPackage.ACTIVITY__TARGET_EVENT);
		}
		return incomingActivities;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getNumber() {
		return number;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setNumber(int newNumber) {
		int oldNumber = number;
		number = newNumber;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CpmPackage.EVENT__NUMBER, oldNumber, number));
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
			case CpmPackage.EVENT__OUTGOING_ACTIVITIES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOutgoingActivities()).basicAdd(otherEnd, msgs);
			case CpmPackage.EVENT__INCOMING_ACTIVITIES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getIncomingActivities()).basicAdd(otherEnd, msgs);
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
			case CpmPackage.EVENT__OUTGOING_ACTIVITIES:
				return ((InternalEList<?>)getOutgoingActivities()).basicRemove(otherEnd, msgs);
			case CpmPackage.EVENT__INCOMING_ACTIVITIES:
				return ((InternalEList<?>)getIncomingActivities()).basicRemove(otherEnd, msgs);
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
			case CpmPackage.EVENT__OUTGOING_ACTIVITIES:
				return getOutgoingActivities();
			case CpmPackage.EVENT__INCOMING_ACTIVITIES:
				return getIncomingActivities();
			case CpmPackage.EVENT__NUMBER:
				return getNumber();
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
			case CpmPackage.EVENT__OUTGOING_ACTIVITIES:
				getOutgoingActivities().clear();
				getOutgoingActivities().addAll((Collection<? extends Activity>)newValue);
				return;
			case CpmPackage.EVENT__INCOMING_ACTIVITIES:
				getIncomingActivities().clear();
				getIncomingActivities().addAll((Collection<? extends Activity>)newValue);
				return;
			case CpmPackage.EVENT__NUMBER:
				setNumber((Integer)newValue);
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
			case CpmPackage.EVENT__OUTGOING_ACTIVITIES:
				getOutgoingActivities().clear();
				return;
			case CpmPackage.EVENT__INCOMING_ACTIVITIES:
				getIncomingActivities().clear();
				return;
			case CpmPackage.EVENT__NUMBER:
				setNumber(NUMBER_EDEFAULT);
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
			case CpmPackage.EVENT__OUTGOING_ACTIVITIES:
				return outgoingActivities != null && !outgoingActivities.isEmpty();
			case CpmPackage.EVENT__INCOMING_ACTIVITIES:
				return incomingActivities != null && !incomingActivities.isEmpty();
			case CpmPackage.EVENT__NUMBER:
				return number != NUMBER_EDEFAULT;
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
		result.append(" (number: ");
		result.append(number);
		result.append(')');
		return result.toString();
	}

} //EventImpl
