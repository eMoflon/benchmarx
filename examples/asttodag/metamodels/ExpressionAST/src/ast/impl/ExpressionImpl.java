/**
 */
package ast.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EcoreUtil;

import ast.AstPackage;
import ast.Expression;
import ast.Model;
import ast.Operator;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Expression</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link ast.impl.ExpressionImpl#getModel <em>Model</em>}</li>
 *   <li>{@link ast.impl.ExpressionImpl#getLeftInverse <em>Left Inverse</em>}</li>
 *   <li>{@link ast.impl.ExpressionImpl#getRightInverse <em>Right Inverse</em>}</li>
 *   <li>{@link ast.impl.ExpressionImpl#getIncrementalID <em>Incremental ID</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class ExpressionImpl extends MinimalEObjectImpl.Container implements Expression {
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
	protected ExpressionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return AstPackage.Literals.EXPRESSION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Model getModel() {
		if (eContainerFeatureID() != AstPackage.EXPRESSION__MODEL) return null;
		return (Model)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetModel(Model newModel, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newModel, AstPackage.EXPRESSION__MODEL, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setModel(Model newModel) {
		if (newModel != eInternalContainer() || (eContainerFeatureID() != AstPackage.EXPRESSION__MODEL && newModel != null)) {
			if (EcoreUtil.isAncestor(this, newModel))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newModel != null)
				msgs = ((InternalEObject)newModel).eInverseAdd(this, AstPackage.MODEL__EXPR, Model.class, msgs);
			msgs = basicSetModel(newModel, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AstPackage.EXPRESSION__MODEL, newModel, newModel));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Operator getLeftInverse() {
		if (eContainerFeatureID() != AstPackage.EXPRESSION__LEFT_INVERSE) return null;
		return (Operator)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetLeftInverse(Operator newLeftInverse, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newLeftInverse, AstPackage.EXPRESSION__LEFT_INVERSE, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLeftInverse(Operator newLeftInverse) {
		if (newLeftInverse != eInternalContainer() || (eContainerFeatureID() != AstPackage.EXPRESSION__LEFT_INVERSE && newLeftInverse != null)) {
			if (EcoreUtil.isAncestor(this, newLeftInverse))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newLeftInverse != null)
				msgs = ((InternalEObject)newLeftInverse).eInverseAdd(this, AstPackage.OPERATOR__LEFT, Operator.class, msgs);
			msgs = basicSetLeftInverse(newLeftInverse, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AstPackage.EXPRESSION__LEFT_INVERSE, newLeftInverse, newLeftInverse));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Operator getRightInverse() {
		if (eContainerFeatureID() != AstPackage.EXPRESSION__RIGHT_INVERSE) return null;
		return (Operator)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetRightInverse(Operator newRightInverse, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newRightInverse, AstPackage.EXPRESSION__RIGHT_INVERSE, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRightInverse(Operator newRightInverse) {
		if (newRightInverse != eInternalContainer() || (eContainerFeatureID() != AstPackage.EXPRESSION__RIGHT_INVERSE && newRightInverse != null)) {
			if (EcoreUtil.isAncestor(this, newRightInverse))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newRightInverse != null)
				msgs = ((InternalEObject)newRightInverse).eInverseAdd(this, AstPackage.OPERATOR__RIGHT, Operator.class, msgs);
			msgs = basicSetRightInverse(newRightInverse, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AstPackage.EXPRESSION__RIGHT_INVERSE, newRightInverse, newRightInverse));
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
			eNotify(new ENotificationImpl(this, Notification.SET, AstPackage.EXPRESSION__INCREMENTAL_ID, oldIncrementalID, incrementalID));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case AstPackage.EXPRESSION__MODEL:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetModel((Model)otherEnd, msgs);
			case AstPackage.EXPRESSION__LEFT_INVERSE:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetLeftInverse((Operator)otherEnd, msgs);
			case AstPackage.EXPRESSION__RIGHT_INVERSE:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetRightInverse((Operator)otherEnd, msgs);
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
			case AstPackage.EXPRESSION__MODEL:
				return basicSetModel(null, msgs);
			case AstPackage.EXPRESSION__LEFT_INVERSE:
				return basicSetLeftInverse(null, msgs);
			case AstPackage.EXPRESSION__RIGHT_INVERSE:
				return basicSetRightInverse(null, msgs);
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
			case AstPackage.EXPRESSION__MODEL:
				return eInternalContainer().eInverseRemove(this, AstPackage.MODEL__EXPR, Model.class, msgs);
			case AstPackage.EXPRESSION__LEFT_INVERSE:
				return eInternalContainer().eInverseRemove(this, AstPackage.OPERATOR__LEFT, Operator.class, msgs);
			case AstPackage.EXPRESSION__RIGHT_INVERSE:
				return eInternalContainer().eInverseRemove(this, AstPackage.OPERATOR__RIGHT, Operator.class, msgs);
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
			case AstPackage.EXPRESSION__MODEL:
				return getModel();
			case AstPackage.EXPRESSION__LEFT_INVERSE:
				return getLeftInverse();
			case AstPackage.EXPRESSION__RIGHT_INVERSE:
				return getRightInverse();
			case AstPackage.EXPRESSION__INCREMENTAL_ID:
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
			case AstPackage.EXPRESSION__MODEL:
				setModel((Model)newValue);
				return;
			case AstPackage.EXPRESSION__LEFT_INVERSE:
				setLeftInverse((Operator)newValue);
				return;
			case AstPackage.EXPRESSION__RIGHT_INVERSE:
				setRightInverse((Operator)newValue);
				return;
			case AstPackage.EXPRESSION__INCREMENTAL_ID:
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
			case AstPackage.EXPRESSION__MODEL:
				setModel((Model)null);
				return;
			case AstPackage.EXPRESSION__LEFT_INVERSE:
				setLeftInverse((Operator)null);
				return;
			case AstPackage.EXPRESSION__RIGHT_INVERSE:
				setRightInverse((Operator)null);
				return;
			case AstPackage.EXPRESSION__INCREMENTAL_ID:
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
			case AstPackage.EXPRESSION__MODEL:
				return getModel() != null;
			case AstPackage.EXPRESSION__LEFT_INVERSE:
				return getLeftInverse() != null;
			case AstPackage.EXPRESSION__RIGHT_INVERSE:
				return getRightInverse() != null;
			case AstPackage.EXPRESSION__INCREMENTAL_ID:
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

} //ExpressionImpl
