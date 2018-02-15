/**
 */
package sql.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EcoreUtil;

import sql.Action;
import sql.Condition;
import sql.Event;
import sql.ForeignKey;
import sql.SqlPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Event</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link sql.impl.EventImpl#getCondition <em>Condition</em>}</li>
 *   <li>{@link sql.impl.EventImpl#getAction <em>Action</em>}</li>
 *   <li>{@link sql.impl.EventImpl#getOwningForeignKey <em>Owning Foreign Key</em>}</li>
 * </ul>
 *
 * @generated
 */
public class EventImpl extends ModelElementImpl implements Event {
	/**
	 * The default value of the '{@link #getCondition() <em>Condition</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCondition()
	 * @generated
	 * @ordered
	 */
	protected static final Condition CONDITION_EDEFAULT = Condition.DELETE;

	/**
	 * The cached value of the '{@link #getCondition() <em>Condition</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCondition()
	 * @generated
	 * @ordered
	 */
	protected Condition condition = CONDITION_EDEFAULT;

	/**
	 * The default value of the '{@link #getAction() <em>Action</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAction()
	 * @generated
	 * @ordered
	 */
	protected static final Action ACTION_EDEFAULT = Action.CASCADE;

	/**
	 * The cached value of the '{@link #getAction() <em>Action</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAction()
	 * @generated
	 * @ordered
	 */
	protected Action action = ACTION_EDEFAULT;

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
		return SqlPackage.Literals.EVENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Condition getCondition() {
		return condition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCondition(Condition newCondition) {
		Condition oldCondition = condition;
		condition = newCondition == null ? CONDITION_EDEFAULT : newCondition;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SqlPackage.EVENT__CONDITION, oldCondition, condition));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Action getAction() {
		return action;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAction(Action newAction) {
		Action oldAction = action;
		action = newAction == null ? ACTION_EDEFAULT : newAction;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SqlPackage.EVENT__ACTION, oldAction, action));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ForeignKey getOwningForeignKey() {
		if (eContainerFeatureID() != SqlPackage.EVENT__OWNING_FOREIGN_KEY) return null;
		return (ForeignKey)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOwningForeignKey(ForeignKey newOwningForeignKey, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newOwningForeignKey, SqlPackage.EVENT__OWNING_FOREIGN_KEY, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOwningForeignKey(ForeignKey newOwningForeignKey) {
		if (newOwningForeignKey != eInternalContainer() || (eContainerFeatureID() != SqlPackage.EVENT__OWNING_FOREIGN_KEY && newOwningForeignKey != null)) {
			if (EcoreUtil.isAncestor(this, newOwningForeignKey))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newOwningForeignKey != null)
				msgs = ((InternalEObject)newOwningForeignKey).eInverseAdd(this, SqlPackage.FOREIGN_KEY__OWNED_EVENTS, ForeignKey.class, msgs);
			msgs = basicSetOwningForeignKey(newOwningForeignKey, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SqlPackage.EVENT__OWNING_FOREIGN_KEY, newOwningForeignKey, newOwningForeignKey));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case SqlPackage.EVENT__OWNING_FOREIGN_KEY:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetOwningForeignKey((ForeignKey)otherEnd, msgs);
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
			case SqlPackage.EVENT__OWNING_FOREIGN_KEY:
				return basicSetOwningForeignKey(null, msgs);
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
			case SqlPackage.EVENT__OWNING_FOREIGN_KEY:
				return eInternalContainer().eInverseRemove(this, SqlPackage.FOREIGN_KEY__OWNED_EVENTS, ForeignKey.class, msgs);
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
			case SqlPackage.EVENT__CONDITION:
				return getCondition();
			case SqlPackage.EVENT__ACTION:
				return getAction();
			case SqlPackage.EVENT__OWNING_FOREIGN_KEY:
				return getOwningForeignKey();
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
			case SqlPackage.EVENT__CONDITION:
				setCondition((Condition)newValue);
				return;
			case SqlPackage.EVENT__ACTION:
				setAction((Action)newValue);
				return;
			case SqlPackage.EVENT__OWNING_FOREIGN_KEY:
				setOwningForeignKey((ForeignKey)newValue);
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
			case SqlPackage.EVENT__CONDITION:
				setCondition(CONDITION_EDEFAULT);
				return;
			case SqlPackage.EVENT__ACTION:
				setAction(ACTION_EDEFAULT);
				return;
			case SqlPackage.EVENT__OWNING_FOREIGN_KEY:
				setOwningForeignKey((ForeignKey)null);
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
			case SqlPackage.EVENT__CONDITION:
				return condition != CONDITION_EDEFAULT;
			case SqlPackage.EVENT__ACTION:
				return action != ACTION_EDEFAULT;
			case SqlPackage.EVENT__OWNING_FOREIGN_KEY:
				return getOwningForeignKey() != null;
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
		result.append(" (condition: ");
		result.append(condition);
		result.append(", action: ");
		result.append(action);
		result.append(')');
		return result.toString();
	}

} //EventImpl
