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

import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

import pnw.NamedElement;
import pnw.PTEdge;
import pnw.PnwPackage;
import pnw.TPEdge;
import pnw.Transition;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Transition</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link pnw.impl.TransitionImpl#getName <em>Name</em>}</li>
 *   <li>{@link pnw.impl.TransitionImpl#getOutTPEdges <em>Out TP Edges</em>}</li>
 *   <li>{@link pnw.impl.TransitionImpl#getInPTEdges <em>In PT Edges</em>}</li>
 * </ul>
 *
 * @generated
 */
public class TransitionImpl extends NetElementImpl implements Transition {
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
	 * The cached value of the '{@link #getOutTPEdges() <em>Out TP Edges</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOutTPEdges()
	 * @generated
	 * @ordered
	 */
	protected EList<TPEdge> outTPEdges;

	/**
	 * The cached value of the '{@link #getInPTEdges() <em>In PT Edges</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInPTEdges()
	 * @generated
	 * @ordered
	 */
	protected EList<PTEdge> inPTEdges;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TransitionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return PnwPackage.Literals.TRANSITION;
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
			eNotify(new ENotificationImpl(this, Notification.SET, PnwPackage.TRANSITION__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<TPEdge> getOutTPEdges() {
		if (outTPEdges == null) {
			outTPEdges = new EObjectWithInverseResolvingEList<TPEdge>(TPEdge.class, this, PnwPackage.TRANSITION__OUT_TP_EDGES, PnwPackage.TP_EDGE__FROM_TRANSITION);
		}
		return outTPEdges;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<PTEdge> getInPTEdges() {
		if (inPTEdges == null) {
			inPTEdges = new EObjectWithInverseResolvingEList<PTEdge>(PTEdge.class, this, PnwPackage.TRANSITION__IN_PT_EDGES, PnwPackage.PT_EDGE__TO_TRANSITION);
		}
		return inPTEdges;
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
			case PnwPackage.TRANSITION__OUT_TP_EDGES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOutTPEdges()).basicAdd(otherEnd, msgs);
			case PnwPackage.TRANSITION__IN_PT_EDGES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getInPTEdges()).basicAdd(otherEnd, msgs);
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
			case PnwPackage.TRANSITION__OUT_TP_EDGES:
				return ((InternalEList<?>)getOutTPEdges()).basicRemove(otherEnd, msgs);
			case PnwPackage.TRANSITION__IN_PT_EDGES:
				return ((InternalEList<?>)getInPTEdges()).basicRemove(otherEnd, msgs);
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
			case PnwPackage.TRANSITION__NAME:
				return getName();
			case PnwPackage.TRANSITION__OUT_TP_EDGES:
				return getOutTPEdges();
			case PnwPackage.TRANSITION__IN_PT_EDGES:
				return getInPTEdges();
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
			case PnwPackage.TRANSITION__NAME:
				setName((String)newValue);
				return;
			case PnwPackage.TRANSITION__OUT_TP_EDGES:
				getOutTPEdges().clear();
				getOutTPEdges().addAll((Collection<? extends TPEdge>)newValue);
				return;
			case PnwPackage.TRANSITION__IN_PT_EDGES:
				getInPTEdges().clear();
				getInPTEdges().addAll((Collection<? extends PTEdge>)newValue);
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
			case PnwPackage.TRANSITION__NAME:
				setName(NAME_EDEFAULT);
				return;
			case PnwPackage.TRANSITION__OUT_TP_EDGES:
				getOutTPEdges().clear();
				return;
			case PnwPackage.TRANSITION__IN_PT_EDGES:
				getInPTEdges().clear();
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
			case PnwPackage.TRANSITION__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case PnwPackage.TRANSITION__OUT_TP_EDGES:
				return outTPEdges != null && !outTPEdges.isEmpty();
			case PnwPackage.TRANSITION__IN_PT_EDGES:
				return inPTEdges != null && !inPTEdges.isEmpty();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
		if (baseClass == NamedElement.class) {
			switch (derivedFeatureID) {
				case PnwPackage.TRANSITION__NAME: return PnwPackage.NAMED_ELEMENT__NAME;
				default: return -1;
			}
		}
		return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
		if (baseClass == NamedElement.class) {
			switch (baseFeatureID) {
				case PnwPackage.NAMED_ELEMENT__NAME: return PnwPackage.TRANSITION__NAME;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
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
		result.append(')');
		return result.toString();
	}

} //TransitionImpl
