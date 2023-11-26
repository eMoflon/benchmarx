/**
 */
package de.tbuchmann.ttc.corrmodel.impl;

import de.tbuchmann.ttc.corrmodel.Corr;
import de.tbuchmann.ttc.corrmodel.CorrElem;
import de.tbuchmann.ttc.corrmodel.CorrModelPackage;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Corr</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link de.tbuchmann.ttc.corrmodel.impl.CorrImpl#getRuleId <em>Rule Id</em>}</li>
 *   <li>{@link de.tbuchmann.ttc.corrmodel.impl.CorrImpl#getSource <em>Source</em>}</li>
 *   <li>{@link de.tbuchmann.ttc.corrmodel.impl.CorrImpl#getTarget <em>Target</em>}</li>
 * </ul>
 *
 * @generated
 */
public class CorrImpl extends EObjectImpl implements Corr {
	/**
	 * The default value of the '{@link #getRuleId() <em>Rule Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRuleId()
	 * @generated
	 * @ordered
	 */
	protected static final String RULE_ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getRuleId() <em>Rule Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRuleId()
	 * @generated
	 * @ordered
	 */
	protected String ruleId = RULE_ID_EDEFAULT;

	/**
	 * The cached value of the '{@link #getSource() <em>Source</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSource()
	 * @generated
	 * @ordered
	 */
	protected EList<CorrElem> source;

	/**
	 * The cached value of the '{@link #getTarget() <em>Target</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTarget()
	 * @generated
	 * @ordered
	 */
	protected EList<CorrElem> target;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CorrImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CorrModelPackage.Literals.CORR;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getRuleId() {
		return ruleId;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setRuleId(String newRuleId) {
		String oldRuleId = ruleId;
		ruleId = newRuleId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CorrModelPackage.CORR__RULE_ID, oldRuleId, ruleId));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<CorrElem> getSource() {
		if (source == null) {
			source = new EObjectContainmentEList<CorrElem>(CorrElem.class, this, CorrModelPackage.CORR__SOURCE);
		}
		return source;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<CorrElem> getTarget() {
		if (target == null) {
			target = new EObjectContainmentEList<CorrElem>(CorrElem.class, this, CorrModelPackage.CORR__TARGET);
		}
		return target;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<EObject> flatSrc() {
		EList<EObject> flatSource = new org.eclipse.emf.common.util.BasicEList<>();
		for (CorrElem element : getSource()) {
			if (element instanceof de.tbuchmann.ttc.corrmodel.SingleElem) {
				if (((de.tbuchmann.ttc.corrmodel.SingleElem) element).getElement() != null) {
					flatSource.add(((de.tbuchmann.ttc.corrmodel.SingleElem) element).getElement());
				}
			} else {
				flatSource.addAll(((de.tbuchmann.ttc.corrmodel.MultiElem) element).getElements());
			}
		}
		return flatSource;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<EObject> flatTrg() {
		EList<EObject> flatTarget = new org.eclipse.emf.common.util.BasicEList<>();
		for (CorrElem element : getTarget()) {
			if (element instanceof de.tbuchmann.ttc.corrmodel.SingleElem) {
				if (((de.tbuchmann.ttc.corrmodel.SingleElem) element).getElement() != null) {
					flatTarget.add(((de.tbuchmann.ttc.corrmodel.SingleElem) element).getElement());
				}
			} else {
				flatTarget.addAll(((de.tbuchmann.ttc.corrmodel.MultiElem) element).getElements());
			}
		}
		return flatTarget;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case CorrModelPackage.CORR__SOURCE:
				return ((InternalEList<?>)getSource()).basicRemove(otherEnd, msgs);
			case CorrModelPackage.CORR__TARGET:
				return ((InternalEList<?>)getTarget()).basicRemove(otherEnd, msgs);
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
			case CorrModelPackage.CORR__RULE_ID:
				return getRuleId();
			case CorrModelPackage.CORR__SOURCE:
				return getSource();
			case CorrModelPackage.CORR__TARGET:
				return getTarget();
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
			case CorrModelPackage.CORR__RULE_ID:
				setRuleId((String)newValue);
				return;
			case CorrModelPackage.CORR__SOURCE:
				getSource().clear();
				getSource().addAll((Collection<? extends CorrElem>)newValue);
				return;
			case CorrModelPackage.CORR__TARGET:
				getTarget().clear();
				getTarget().addAll((Collection<? extends CorrElem>)newValue);
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
			case CorrModelPackage.CORR__RULE_ID:
				setRuleId(RULE_ID_EDEFAULT);
				return;
			case CorrModelPackage.CORR__SOURCE:
				getSource().clear();
				return;
			case CorrModelPackage.CORR__TARGET:
				getTarget().clear();
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
			case CorrModelPackage.CORR__RULE_ID:
				return RULE_ID_EDEFAULT == null ? ruleId != null : !RULE_ID_EDEFAULT.equals(ruleId);
			case CorrModelPackage.CORR__SOURCE:
				return source != null && !source.isEmpty();
			case CorrModelPackage.CORR__TARGET:
				return target != null && !target.isEmpty();
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

		StringBuilder result = new StringBuilder(super.toString());
		result.append(" (ruleId: ");
		result.append(ruleId);
		result.append(')');
		return result.toString();
	}

} //CorrImpl
