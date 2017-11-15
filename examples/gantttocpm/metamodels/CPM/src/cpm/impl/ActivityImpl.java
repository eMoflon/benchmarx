/**
 */
package cpm.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import cpm.Activity;
import cpm.CpmPackage;
import cpm.Event;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Activity</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link cpm.impl.ActivityImpl#getSourceEvent <em>Source Event</em>}</li>
 *   <li>{@link cpm.impl.ActivityImpl#getTargetEvent <em>Target Event</em>}</li>
 *   <li>{@link cpm.impl.ActivityImpl#getName <em>Name</em>}</li>
 *   <li>{@link cpm.impl.ActivityImpl#getDuration <em>Duration</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ActivityImpl extends ElementImpl implements Activity {
	/**
	 * The cached value of the '{@link #getSourceEvent() <em>Source Event</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSourceEvent()
	 * @generated
	 * @ordered
	 */
	protected Event sourceEvent;

	/**
	 * The cached value of the '{@link #getTargetEvent() <em>Target Event</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTargetEvent()
	 * @generated
	 * @ordered
	 */
	protected Event targetEvent;

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
	 * The default value of the '{@link #getDuration() <em>Duration</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDuration()
	 * @generated
	 * @ordered
	 */
	protected static final int DURATION_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getDuration() <em>Duration</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDuration()
	 * @generated
	 * @ordered
	 */
	protected int duration = DURATION_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ActivityImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CpmPackage.Literals.ACTIVITY;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Event getSourceEvent() {
		if (sourceEvent != null && sourceEvent.eIsProxy()) {
			InternalEObject oldSourceEvent = (InternalEObject)sourceEvent;
			sourceEvent = (Event)eResolveProxy(oldSourceEvent);
			if (sourceEvent != oldSourceEvent) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, CpmPackage.ACTIVITY__SOURCE_EVENT, oldSourceEvent, sourceEvent));
			}
		}
		return sourceEvent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Event basicGetSourceEvent() {
		return sourceEvent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetSourceEvent(Event newSourceEvent, NotificationChain msgs) {
		Event oldSourceEvent = sourceEvent;
		sourceEvent = newSourceEvent;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CpmPackage.ACTIVITY__SOURCE_EVENT, oldSourceEvent, newSourceEvent);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSourceEvent(Event newSourceEvent) {
		if (newSourceEvent != sourceEvent) {
			NotificationChain msgs = null;
			if (sourceEvent != null)
				msgs = ((InternalEObject)sourceEvent).eInverseRemove(this, CpmPackage.EVENT__OUTGOING_ACTIVITIES, Event.class, msgs);
			if (newSourceEvent != null)
				msgs = ((InternalEObject)newSourceEvent).eInverseAdd(this, CpmPackage.EVENT__OUTGOING_ACTIVITIES, Event.class, msgs);
			msgs = basicSetSourceEvent(newSourceEvent, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CpmPackage.ACTIVITY__SOURCE_EVENT, newSourceEvent, newSourceEvent));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Event getTargetEvent() {
		if (targetEvent != null && targetEvent.eIsProxy()) {
			InternalEObject oldTargetEvent = (InternalEObject)targetEvent;
			targetEvent = (Event)eResolveProxy(oldTargetEvent);
			if (targetEvent != oldTargetEvent) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, CpmPackage.ACTIVITY__TARGET_EVENT, oldTargetEvent, targetEvent));
			}
		}
		return targetEvent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Event basicGetTargetEvent() {
		return targetEvent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetTargetEvent(Event newTargetEvent, NotificationChain msgs) {
		Event oldTargetEvent = targetEvent;
		targetEvent = newTargetEvent;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CpmPackage.ACTIVITY__TARGET_EVENT, oldTargetEvent, newTargetEvent);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTargetEvent(Event newTargetEvent) {
		if (newTargetEvent != targetEvent) {
			NotificationChain msgs = null;
			if (targetEvent != null)
				msgs = ((InternalEObject)targetEvent).eInverseRemove(this, CpmPackage.EVENT__INCOMING_ACTIVITIES, Event.class, msgs);
			if (newTargetEvent != null)
				msgs = ((InternalEObject)newTargetEvent).eInverseAdd(this, CpmPackage.EVENT__INCOMING_ACTIVITIES, Event.class, msgs);
			msgs = basicSetTargetEvent(newTargetEvent, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CpmPackage.ACTIVITY__TARGET_EVENT, newTargetEvent, newTargetEvent));
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
			eNotify(new ENotificationImpl(this, Notification.SET, CpmPackage.ACTIVITY__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getDuration() {
		return duration;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDuration(int newDuration) {
		int oldDuration = duration;
		duration = newDuration;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CpmPackage.ACTIVITY__DURATION, oldDuration, duration));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case CpmPackage.ACTIVITY__SOURCE_EVENT:
				if (sourceEvent != null)
					msgs = ((InternalEObject)sourceEvent).eInverseRemove(this, CpmPackage.EVENT__OUTGOING_ACTIVITIES, Event.class, msgs);
				return basicSetSourceEvent((Event)otherEnd, msgs);
			case CpmPackage.ACTIVITY__TARGET_EVENT:
				if (targetEvent != null)
					msgs = ((InternalEObject)targetEvent).eInverseRemove(this, CpmPackage.EVENT__INCOMING_ACTIVITIES, Event.class, msgs);
				return basicSetTargetEvent((Event)otherEnd, msgs);
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
			case CpmPackage.ACTIVITY__SOURCE_EVENT:
				return basicSetSourceEvent(null, msgs);
			case CpmPackage.ACTIVITY__TARGET_EVENT:
				return basicSetTargetEvent(null, msgs);
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
			case CpmPackage.ACTIVITY__SOURCE_EVENT:
				if (resolve) return getSourceEvent();
				return basicGetSourceEvent();
			case CpmPackage.ACTIVITY__TARGET_EVENT:
				if (resolve) return getTargetEvent();
				return basicGetTargetEvent();
			case CpmPackage.ACTIVITY__NAME:
				return getName();
			case CpmPackage.ACTIVITY__DURATION:
				return getDuration();
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
			case CpmPackage.ACTIVITY__SOURCE_EVENT:
				setSourceEvent((Event)newValue);
				return;
			case CpmPackage.ACTIVITY__TARGET_EVENT:
				setTargetEvent((Event)newValue);
				return;
			case CpmPackage.ACTIVITY__NAME:
				setName((String)newValue);
				return;
			case CpmPackage.ACTIVITY__DURATION:
				setDuration((Integer)newValue);
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
			case CpmPackage.ACTIVITY__SOURCE_EVENT:
				setSourceEvent((Event)null);
				return;
			case CpmPackage.ACTIVITY__TARGET_EVENT:
				setTargetEvent((Event)null);
				return;
			case CpmPackage.ACTIVITY__NAME:
				setName(NAME_EDEFAULT);
				return;
			case CpmPackage.ACTIVITY__DURATION:
				setDuration(DURATION_EDEFAULT);
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
			case CpmPackage.ACTIVITY__SOURCE_EVENT:
				return sourceEvent != null;
			case CpmPackage.ACTIVITY__TARGET_EVENT:
				return targetEvent != null;
			case CpmPackage.ACTIVITY__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case CpmPackage.ACTIVITY__DURATION:
				return duration != DURATION_EDEFAULT;
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
		result.append(", duration: ");
		result.append(duration);
		result.append(')');
		return result.toString();
	}

} //ActivityImpl
