/**
 */
package pnw.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import pnw.Place;
import pnw.PnwPackage;
import pnw.TPEdge;
import pnw.Transition;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>TP Edge</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link pnw.impl.TPEdgeImpl#getFromTransition <em>From Transition</em>}</li>
 *   <li>{@link pnw.impl.TPEdgeImpl#getToPlace <em>To Place</em>}</li>
 * </ul>
 *
 * @generated
 */
public class TPEdgeImpl extends EdgeImpl implements TPEdge {
	/**
	 * The cached value of the '{@link #getFromTransition() <em>From Transition</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFromTransition()
	 * @generated
	 * @ordered
	 */
	protected Transition fromTransition;

	/**
	 * The cached value of the '{@link #getToPlace() <em>To Place</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getToPlace()
	 * @generated
	 * @ordered
	 */
	protected Place toPlace;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TPEdgeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return PnwPackage.Literals.TP_EDGE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Transition getFromTransition() {
		if (fromTransition != null && fromTransition.eIsProxy()) {
			InternalEObject oldFromTransition = (InternalEObject)fromTransition;
			fromTransition = (Transition)eResolveProxy(oldFromTransition);
			if (fromTransition != oldFromTransition) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, PnwPackage.TP_EDGE__FROM_TRANSITION, oldFromTransition, fromTransition));
			}
		}
		return fromTransition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Transition basicGetFromTransition() {
		return fromTransition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetFromTransition(Transition newFromTransition, NotificationChain msgs) {
		Transition oldFromTransition = fromTransition;
		fromTransition = newFromTransition;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PnwPackage.TP_EDGE__FROM_TRANSITION, oldFromTransition, newFromTransition);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFromTransition(Transition newFromTransition) {
		if (newFromTransition != fromTransition) {
			NotificationChain msgs = null;
			if (fromTransition != null)
				msgs = ((InternalEObject)fromTransition).eInverseRemove(this, PnwPackage.TRANSITION__OUT_TP_EDGES, Transition.class, msgs);
			if (newFromTransition != null)
				msgs = ((InternalEObject)newFromTransition).eInverseAdd(this, PnwPackage.TRANSITION__OUT_TP_EDGES, Transition.class, msgs);
			msgs = basicSetFromTransition(newFromTransition, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PnwPackage.TP_EDGE__FROM_TRANSITION, newFromTransition, newFromTransition));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Place getToPlace() {
		if (toPlace != null && toPlace.eIsProxy()) {
			InternalEObject oldToPlace = (InternalEObject)toPlace;
			toPlace = (Place)eResolveProxy(oldToPlace);
			if (toPlace != oldToPlace) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, PnwPackage.TP_EDGE__TO_PLACE, oldToPlace, toPlace));
			}
		}
		return toPlace;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Place basicGetToPlace() {
		return toPlace;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetToPlace(Place newToPlace, NotificationChain msgs) {
		Place oldToPlace = toPlace;
		toPlace = newToPlace;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PnwPackage.TP_EDGE__TO_PLACE, oldToPlace, newToPlace);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setToPlace(Place newToPlace) {
		if (newToPlace != toPlace) {
			NotificationChain msgs = null;
			if (toPlace != null)
				msgs = ((InternalEObject)toPlace).eInverseRemove(this, PnwPackage.PLACE__IN_TP_EDGES, Place.class, msgs);
			if (newToPlace != null)
				msgs = ((InternalEObject)newToPlace).eInverseAdd(this, PnwPackage.PLACE__IN_TP_EDGES, Place.class, msgs);
			msgs = basicSetToPlace(newToPlace, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PnwPackage.TP_EDGE__TO_PLACE, newToPlace, newToPlace));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case PnwPackage.TP_EDGE__FROM_TRANSITION:
				if (fromTransition != null)
					msgs = ((InternalEObject)fromTransition).eInverseRemove(this, PnwPackage.TRANSITION__OUT_TP_EDGES, Transition.class, msgs);
				return basicSetFromTransition((Transition)otherEnd, msgs);
			case PnwPackage.TP_EDGE__TO_PLACE:
				if (toPlace != null)
					msgs = ((InternalEObject)toPlace).eInverseRemove(this, PnwPackage.PLACE__IN_TP_EDGES, Place.class, msgs);
				return basicSetToPlace((Place)otherEnd, msgs);
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
			case PnwPackage.TP_EDGE__FROM_TRANSITION:
				return basicSetFromTransition(null, msgs);
			case PnwPackage.TP_EDGE__TO_PLACE:
				return basicSetToPlace(null, msgs);
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
			case PnwPackage.TP_EDGE__FROM_TRANSITION:
				if (resolve) return getFromTransition();
				return basicGetFromTransition();
			case PnwPackage.TP_EDGE__TO_PLACE:
				if (resolve) return getToPlace();
				return basicGetToPlace();
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
			case PnwPackage.TP_EDGE__FROM_TRANSITION:
				setFromTransition((Transition)newValue);
				return;
			case PnwPackage.TP_EDGE__TO_PLACE:
				setToPlace((Place)newValue);
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
			case PnwPackage.TP_EDGE__FROM_TRANSITION:
				setFromTransition((Transition)null);
				return;
			case PnwPackage.TP_EDGE__TO_PLACE:
				setToPlace((Place)null);
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
			case PnwPackage.TP_EDGE__FROM_TRANSITION:
				return fromTransition != null;
			case PnwPackage.TP_EDGE__TO_PLACE:
				return toPlace != null;
		}
		return super.eIsSet(featureID);
	}

} //TPEdgeImpl
