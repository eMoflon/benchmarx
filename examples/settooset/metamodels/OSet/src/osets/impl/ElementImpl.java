/**
 */
package osets.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EcoreUtil;

import osets.Element;
import osets.MyOrderedSet;
import osets.OsetsPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Element</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link osets.impl.ElementImpl#getValue <em>Value</em>}</li>
 *   <li>{@link osets.impl.ElementImpl#getNext <em>Next</em>}</li>
 *   <li>{@link osets.impl.ElementImpl#getPrevious <em>Previous</em>}</li>
 *   <li>{@link osets.impl.ElementImpl#getOrderedSet <em>Ordered Set</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ElementImpl extends MinimalEObjectImpl.Container implements Element {
	/**
	 * The default value of the '{@link #getValue() <em>Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getValue()
	 * @generated
	 * @ordered
	 */
	protected static final String VALUE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getValue() <em>Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getValue()
	 * @generated
	 * @ordered
	 */
	protected String value = VALUE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getNext() <em>Next</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNext()
	 * @generated
	 * @ordered
	 */
	protected Element next;

	/**
	 * The cached value of the '{@link #getPrevious() <em>Previous</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPrevious()
	 * @generated
	 * @ordered
	 */
	protected Element previous;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ElementImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return OsetsPackage.Literals.ELEMENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getValue() {
		return value;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setValue(String newValue) {
		String oldValue = value;
		value = newValue;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OsetsPackage.ELEMENT__VALUE, oldValue, value));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Element getNext() {
		if (next != null && next.eIsProxy()) {
			InternalEObject oldNext = (InternalEObject)next;
			next = (Element)eResolveProxy(oldNext);
			if (next != oldNext) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, OsetsPackage.ELEMENT__NEXT, oldNext, next));
			}
		}
		return next;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Element basicGetNext() {
		return next;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetNext(Element newNext, NotificationChain msgs) {
		Element oldNext = next;
		next = newNext;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, OsetsPackage.ELEMENT__NEXT, oldNext, newNext);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setNext(Element newNext) {
		if (newNext != next) {
			NotificationChain msgs = null;
			if (next != null)
				msgs = ((InternalEObject)next).eInverseRemove(this, OsetsPackage.ELEMENT__PREVIOUS, Element.class, msgs);
			if (newNext != null)
				msgs = ((InternalEObject)newNext).eInverseAdd(this, OsetsPackage.ELEMENT__PREVIOUS, Element.class, msgs);
			msgs = basicSetNext(newNext, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OsetsPackage.ELEMENT__NEXT, newNext, newNext));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Element getPrevious() {
		if (previous != null && previous.eIsProxy()) {
			InternalEObject oldPrevious = (InternalEObject)previous;
			previous = (Element)eResolveProxy(oldPrevious);
			if (previous != oldPrevious) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, OsetsPackage.ELEMENT__PREVIOUS, oldPrevious, previous));
			}
		}
		return previous;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Element basicGetPrevious() {
		return previous;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetPrevious(Element newPrevious, NotificationChain msgs) {
		Element oldPrevious = previous;
		previous = newPrevious;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, OsetsPackage.ELEMENT__PREVIOUS, oldPrevious, newPrevious);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPrevious(Element newPrevious) {
		if (newPrevious != previous) {
			NotificationChain msgs = null;
			if (previous != null)
				msgs = ((InternalEObject)previous).eInverseRemove(this, OsetsPackage.ELEMENT__NEXT, Element.class, msgs);
			if (newPrevious != null)
				msgs = ((InternalEObject)newPrevious).eInverseAdd(this, OsetsPackage.ELEMENT__NEXT, Element.class, msgs);
			msgs = basicSetPrevious(newPrevious, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OsetsPackage.ELEMENT__PREVIOUS, newPrevious, newPrevious));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MyOrderedSet getOrderedSet() {
		if (eContainerFeatureID() != OsetsPackage.ELEMENT__ORDERED_SET) return null;
		return (MyOrderedSet)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOrderedSet(MyOrderedSet newOrderedSet, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newOrderedSet, OsetsPackage.ELEMENT__ORDERED_SET, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOrderedSet(MyOrderedSet newOrderedSet) {
		if (newOrderedSet != eInternalContainer() || (eContainerFeatureID() != OsetsPackage.ELEMENT__ORDERED_SET && newOrderedSet != null)) {
			if (EcoreUtil.isAncestor(this, newOrderedSet))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newOrderedSet != null)
				msgs = ((InternalEObject)newOrderedSet).eInverseAdd(this, OsetsPackage.MY_ORDERED_SET__ELEMENTS, MyOrderedSet.class, msgs);
			msgs = basicSetOrderedSet(newOrderedSet, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OsetsPackage.ELEMENT__ORDERED_SET, newOrderedSet, newOrderedSet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case OsetsPackage.ELEMENT__NEXT:
				if (next != null)
					msgs = ((InternalEObject)next).eInverseRemove(this, OsetsPackage.ELEMENT__PREVIOUS, Element.class, msgs);
				return basicSetNext((Element)otherEnd, msgs);
			case OsetsPackage.ELEMENT__PREVIOUS:
				if (previous != null)
					msgs = ((InternalEObject)previous).eInverseRemove(this, OsetsPackage.ELEMENT__NEXT, Element.class, msgs);
				return basicSetPrevious((Element)otherEnd, msgs);
			case OsetsPackage.ELEMENT__ORDERED_SET:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetOrderedSet((MyOrderedSet)otherEnd, msgs);
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
			case OsetsPackage.ELEMENT__NEXT:
				return basicSetNext(null, msgs);
			case OsetsPackage.ELEMENT__PREVIOUS:
				return basicSetPrevious(null, msgs);
			case OsetsPackage.ELEMENT__ORDERED_SET:
				return basicSetOrderedSet(null, msgs);
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
			case OsetsPackage.ELEMENT__ORDERED_SET:
				return eInternalContainer().eInverseRemove(this, OsetsPackage.MY_ORDERED_SET__ELEMENTS, MyOrderedSet.class, msgs);
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
			case OsetsPackage.ELEMENT__VALUE:
				return getValue();
			case OsetsPackage.ELEMENT__NEXT:
				if (resolve) return getNext();
				return basicGetNext();
			case OsetsPackage.ELEMENT__PREVIOUS:
				if (resolve) return getPrevious();
				return basicGetPrevious();
			case OsetsPackage.ELEMENT__ORDERED_SET:
				return getOrderedSet();
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
			case OsetsPackage.ELEMENT__VALUE:
				setValue((String)newValue);
				return;
			case OsetsPackage.ELEMENT__NEXT:
				setNext((Element)newValue);
				return;
			case OsetsPackage.ELEMENT__PREVIOUS:
				setPrevious((Element)newValue);
				return;
			case OsetsPackage.ELEMENT__ORDERED_SET:
				setOrderedSet((MyOrderedSet)newValue);
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
			case OsetsPackage.ELEMENT__VALUE:
				setValue(VALUE_EDEFAULT);
				return;
			case OsetsPackage.ELEMENT__NEXT:
				setNext((Element)null);
				return;
			case OsetsPackage.ELEMENT__PREVIOUS:
				setPrevious((Element)null);
				return;
			case OsetsPackage.ELEMENT__ORDERED_SET:
				setOrderedSet((MyOrderedSet)null);
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
			case OsetsPackage.ELEMENT__VALUE:
				return VALUE_EDEFAULT == null ? value != null : !VALUE_EDEFAULT.equals(value);
			case OsetsPackage.ELEMENT__NEXT:
				return next != null;
			case OsetsPackage.ELEMENT__PREVIOUS:
				return previous != null;
			case OsetsPackage.ELEMENT__ORDERED_SET:
				return getOrderedSet() != null;
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
		result.append(" (value: ");
		result.append(value);
		result.append(')');
		return result.toString();
	}

} //ElementImpl
