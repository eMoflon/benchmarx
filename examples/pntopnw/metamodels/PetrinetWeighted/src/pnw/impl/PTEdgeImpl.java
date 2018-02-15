/**
 */
package pnw.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import pnw.PTEdge;
import pnw.Place;
import pnw.PnwPackage;
import pnw.Transition;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>PT Edge</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link pnw.impl.PTEdgeImpl#getFromPlace <em>From Place</em>}</li>
 *   <li>{@link pnw.impl.PTEdgeImpl#getToTransition <em>To Transition</em>}</li>
 * </ul>
 *
 * @generated
 */
public class PTEdgeImpl extends EdgeImpl implements PTEdge {
	/**
	 * The cached value of the '{@link #getFromPlace() <em>From Place</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFromPlace()
	 * @generated
	 * @ordered
	 */
	protected Place fromPlace;

	/**
	 * The cached value of the '{@link #getToTransition() <em>To Transition</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getToTransition()
	 * @generated
	 * @ordered
	 */
	protected Transition toTransition;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PTEdgeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return PnwPackage.Literals.PT_EDGE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Place getFromPlace() {
		if (fromPlace != null && fromPlace.eIsProxy()) {
			InternalEObject oldFromPlace = (InternalEObject)fromPlace;
			fromPlace = (Place)eResolveProxy(oldFromPlace);
			if (fromPlace != oldFromPlace) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, PnwPackage.PT_EDGE__FROM_PLACE, oldFromPlace, fromPlace));
			}
		}
		return fromPlace;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Place basicGetFromPlace() {
		return fromPlace;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetFromPlace(Place newFromPlace, NotificationChain msgs) {
		Place oldFromPlace = fromPlace;
		fromPlace = newFromPlace;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PnwPackage.PT_EDGE__FROM_PLACE, oldFromPlace, newFromPlace);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFromPlace(Place newFromPlace) {
		if (newFromPlace != fromPlace) {
			NotificationChain msgs = null;
			if (fromPlace != null)
				msgs = ((InternalEObject)fromPlace).eInverseRemove(this, PnwPackage.PLACE__OUT_PT_EDGES, Place.class, msgs);
			if (newFromPlace != null)
				msgs = ((InternalEObject)newFromPlace).eInverseAdd(this, PnwPackage.PLACE__OUT_PT_EDGES, Place.class, msgs);
			msgs = basicSetFromPlace(newFromPlace, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PnwPackage.PT_EDGE__FROM_PLACE, newFromPlace, newFromPlace));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Transition getToTransition() {
		if (toTransition != null && toTransition.eIsProxy()) {
			InternalEObject oldToTransition = (InternalEObject)toTransition;
			toTransition = (Transition)eResolveProxy(oldToTransition);
			if (toTransition != oldToTransition) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, PnwPackage.PT_EDGE__TO_TRANSITION, oldToTransition, toTransition));
			}
		}
		return toTransition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Transition basicGetToTransition() {
		return toTransition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetToTransition(Transition newToTransition, NotificationChain msgs) {
		Transition oldToTransition = toTransition;
		toTransition = newToTransition;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PnwPackage.PT_EDGE__TO_TRANSITION, oldToTransition, newToTransition);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setToTransition(Transition newToTransition) {
		if (newToTransition != toTransition) {
			NotificationChain msgs = null;
			if (toTransition != null)
				msgs = ((InternalEObject)toTransition).eInverseRemove(this, PnwPackage.TRANSITION__IN_PT_EDGES, Transition.class, msgs);
			if (newToTransition != null)
				msgs = ((InternalEObject)newToTransition).eInverseAdd(this, PnwPackage.TRANSITION__IN_PT_EDGES, Transition.class, msgs);
			msgs = basicSetToTransition(newToTransition, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PnwPackage.PT_EDGE__TO_TRANSITION, newToTransition, newToTransition));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case PnwPackage.PT_EDGE__FROM_PLACE:
				if (fromPlace != null)
					msgs = ((InternalEObject)fromPlace).eInverseRemove(this, PnwPackage.PLACE__OUT_PT_EDGES, Place.class, msgs);
				return basicSetFromPlace((Place)otherEnd, msgs);
			case PnwPackage.PT_EDGE__TO_TRANSITION:
				if (toTransition != null)
					msgs = ((InternalEObject)toTransition).eInverseRemove(this, PnwPackage.TRANSITION__IN_PT_EDGES, Transition.class, msgs);
				return basicSetToTransition((Transition)otherEnd, msgs);
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
			case PnwPackage.PT_EDGE__FROM_PLACE:
				return basicSetFromPlace(null, msgs);
			case PnwPackage.PT_EDGE__TO_TRANSITION:
				return basicSetToTransition(null, msgs);
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
			case PnwPackage.PT_EDGE__FROM_PLACE:
				if (resolve) return getFromPlace();
				return basicGetFromPlace();
			case PnwPackage.PT_EDGE__TO_TRANSITION:
				if (resolve) return getToTransition();
				return basicGetToTransition();
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
			case PnwPackage.PT_EDGE__FROM_PLACE:
				setFromPlace((Place)newValue);
				return;
			case PnwPackage.PT_EDGE__TO_TRANSITION:
				setToTransition((Transition)newValue);
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
			case PnwPackage.PT_EDGE__FROM_PLACE:
				setFromPlace((Place)null);
				return;
			case PnwPackage.PT_EDGE__TO_TRANSITION:
				setToTransition((Transition)null);
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
			case PnwPackage.PT_EDGE__FROM_PLACE:
				return fromPlace != null;
			case PnwPackage.PT_EDGE__TO_TRANSITION:
				return toTransition != null;
		}
		return super.eIsSet(featureID);
	}

} //PTEdgeImpl
