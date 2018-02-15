/**
 */
package dag.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

import dag.DagPackage;
import dag.Expression;
import dag.Model;
import dag.Operator;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Expression</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link dag.impl.ExpressionImpl#getModel <em>Model</em>}</li>
 *   <li>{@link dag.impl.ExpressionImpl#getLeftInverse <em>Left Inverse</em>}</li>
 *   <li>{@link dag.impl.ExpressionImpl#getRightInverse <em>Right Inverse</em>}</li>
 *   <li>{@link dag.impl.ExpressionImpl#getIncrementalID <em>Incremental ID</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class ExpressionImpl extends MinimalEObjectImpl.Container implements Expression {
	/**
	 * The cached value of the '{@link #getLeftInverse() <em>Left Inverse</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLeftInverse()
	 * @generated
	 * @ordered
	 */
	protected EList<Operator> leftInverse;

	/**
	 * The cached value of the '{@link #getRightInverse() <em>Right Inverse</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRightInverse()
	 * @generated
	 * @ordered
	 */
	protected EList<Operator> rightInverse;

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
		return DagPackage.Literals.EXPRESSION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Model getModel() {
		if (eContainerFeatureID() != DagPackage.EXPRESSION__MODEL) return null;
		return (Model)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetModel(Model newModel, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newModel, DagPackage.EXPRESSION__MODEL, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setModel(Model newModel) {
		if (newModel != eInternalContainer() || (eContainerFeatureID() != DagPackage.EXPRESSION__MODEL && newModel != null)) {
			if (EcoreUtil.isAncestor(this, newModel))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newModel != null)
				msgs = ((InternalEObject)newModel).eInverseAdd(this, DagPackage.MODEL__EXPRS, Model.class, msgs);
			msgs = basicSetModel(newModel, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DagPackage.EXPRESSION__MODEL, newModel, newModel));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Operator> getLeftInverse() {
		if (leftInverse == null) {
			leftInverse = new EObjectWithInverseResolvingEList<Operator>(Operator.class, this, DagPackage.EXPRESSION__LEFT_INVERSE, DagPackage.OPERATOR__LEFT);
		}
		return leftInverse;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Operator> getRightInverse() {
		if (rightInverse == null) {
			rightInverse = new EObjectWithInverseResolvingEList<Operator>(Operator.class, this, DagPackage.EXPRESSION__RIGHT_INVERSE, DagPackage.OPERATOR__RIGHT);
		}
		return rightInverse;
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
			eNotify(new ENotificationImpl(this, Notification.SET, DagPackage.EXPRESSION__INCREMENTAL_ID, oldIncrementalID, incrementalID));
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
			case DagPackage.EXPRESSION__MODEL:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetModel((Model)otherEnd, msgs);
			case DagPackage.EXPRESSION__LEFT_INVERSE:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getLeftInverse()).basicAdd(otherEnd, msgs);
			case DagPackage.EXPRESSION__RIGHT_INVERSE:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getRightInverse()).basicAdd(otherEnd, msgs);
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
			case DagPackage.EXPRESSION__MODEL:
				return basicSetModel(null, msgs);
			case DagPackage.EXPRESSION__LEFT_INVERSE:
				return ((InternalEList<?>)getLeftInverse()).basicRemove(otherEnd, msgs);
			case DagPackage.EXPRESSION__RIGHT_INVERSE:
				return ((InternalEList<?>)getRightInverse()).basicRemove(otherEnd, msgs);
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
			case DagPackage.EXPRESSION__MODEL:
				return eInternalContainer().eInverseRemove(this, DagPackage.MODEL__EXPRS, Model.class, msgs);
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
			case DagPackage.EXPRESSION__MODEL:
				return getModel();
			case DagPackage.EXPRESSION__LEFT_INVERSE:
				return getLeftInverse();
			case DagPackage.EXPRESSION__RIGHT_INVERSE:
				return getRightInverse();
			case DagPackage.EXPRESSION__INCREMENTAL_ID:
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
			case DagPackage.EXPRESSION__MODEL:
				setModel((Model)newValue);
				return;
			case DagPackage.EXPRESSION__LEFT_INVERSE:
				getLeftInverse().clear();
				getLeftInverse().addAll((Collection<? extends Operator>)newValue);
				return;
			case DagPackage.EXPRESSION__RIGHT_INVERSE:
				getRightInverse().clear();
				getRightInverse().addAll((Collection<? extends Operator>)newValue);
				return;
			case DagPackage.EXPRESSION__INCREMENTAL_ID:
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
			case DagPackage.EXPRESSION__MODEL:
				setModel((Model)null);
				return;
			case DagPackage.EXPRESSION__LEFT_INVERSE:
				getLeftInverse().clear();
				return;
			case DagPackage.EXPRESSION__RIGHT_INVERSE:
				getRightInverse().clear();
				return;
			case DagPackage.EXPRESSION__INCREMENTAL_ID:
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
			case DagPackage.EXPRESSION__MODEL:
				return getModel() != null;
			case DagPackage.EXPRESSION__LEFT_INVERSE:
				return leftInverse != null && !leftInverse.isEmpty();
			case DagPackage.EXPRESSION__RIGHT_INVERSE:
				return rightInverse != null && !rightInverse.isEmpty();
			case DagPackage.EXPRESSION__INCREMENTAL_ID:
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
