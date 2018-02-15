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
import pnw.Place;
import pnw.PnwPackage;
import pnw.TPEdge;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Place</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link pnw.impl.PlaceImpl#getName <em>Name</em>}</li>
 *   <li>{@link pnw.impl.PlaceImpl#getNoOfTokens <em>No Of Tokens</em>}</li>
 *   <li>{@link pnw.impl.PlaceImpl#getOutPTEdges <em>Out PT Edges</em>}</li>
 *   <li>{@link pnw.impl.PlaceImpl#getInTPEdges <em>In TP Edges</em>}</li>
 * </ul>
 *
 * @generated
 */
public class PlaceImpl extends NetElementImpl implements Place {
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
	 * The default value of the '{@link #getNoOfTokens() <em>No Of Tokens</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNoOfTokens()
	 * @generated
	 * @ordered
	 */
	protected static final int NO_OF_TOKENS_EDEFAULT = 1;

	/**
	 * The cached value of the '{@link #getNoOfTokens() <em>No Of Tokens</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNoOfTokens()
	 * @generated
	 * @ordered
	 */
	protected int noOfTokens = NO_OF_TOKENS_EDEFAULT;

	/**
	 * The cached value of the '{@link #getOutPTEdges() <em>Out PT Edges</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOutPTEdges()
	 * @generated
	 * @ordered
	 */
	protected EList<PTEdge> outPTEdges;

	/**
	 * The cached value of the '{@link #getInTPEdges() <em>In TP Edges</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInTPEdges()
	 * @generated
	 * @ordered
	 */
	protected EList<TPEdge> inTPEdges;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PlaceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return PnwPackage.Literals.PLACE;
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
			eNotify(new ENotificationImpl(this, Notification.SET, PnwPackage.PLACE__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getNoOfTokens() {
		return noOfTokens;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setNoOfTokens(int newNoOfTokens) {
		int oldNoOfTokens = noOfTokens;
		noOfTokens = newNoOfTokens;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PnwPackage.PLACE__NO_OF_TOKENS, oldNoOfTokens, noOfTokens));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<PTEdge> getOutPTEdges() {
		if (outPTEdges == null) {
			outPTEdges = new EObjectWithInverseResolvingEList<PTEdge>(PTEdge.class, this, PnwPackage.PLACE__OUT_PT_EDGES, PnwPackage.PT_EDGE__FROM_PLACE);
		}
		return outPTEdges;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<TPEdge> getInTPEdges() {
		if (inTPEdges == null) {
			inTPEdges = new EObjectWithInverseResolvingEList<TPEdge>(TPEdge.class, this, PnwPackage.PLACE__IN_TP_EDGES, PnwPackage.TP_EDGE__TO_PLACE);
		}
		return inTPEdges;
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
			case PnwPackage.PLACE__OUT_PT_EDGES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOutPTEdges()).basicAdd(otherEnd, msgs);
			case PnwPackage.PLACE__IN_TP_EDGES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getInTPEdges()).basicAdd(otherEnd, msgs);
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
			case PnwPackage.PLACE__OUT_PT_EDGES:
				return ((InternalEList<?>)getOutPTEdges()).basicRemove(otherEnd, msgs);
			case PnwPackage.PLACE__IN_TP_EDGES:
				return ((InternalEList<?>)getInTPEdges()).basicRemove(otherEnd, msgs);
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
			case PnwPackage.PLACE__NAME:
				return getName();
			case PnwPackage.PLACE__NO_OF_TOKENS:
				return getNoOfTokens();
			case PnwPackage.PLACE__OUT_PT_EDGES:
				return getOutPTEdges();
			case PnwPackage.PLACE__IN_TP_EDGES:
				return getInTPEdges();
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
			case PnwPackage.PLACE__NAME:
				setName((String)newValue);
				return;
			case PnwPackage.PLACE__NO_OF_TOKENS:
				setNoOfTokens((Integer)newValue);
				return;
			case PnwPackage.PLACE__OUT_PT_EDGES:
				getOutPTEdges().clear();
				getOutPTEdges().addAll((Collection<? extends PTEdge>)newValue);
				return;
			case PnwPackage.PLACE__IN_TP_EDGES:
				getInTPEdges().clear();
				getInTPEdges().addAll((Collection<? extends TPEdge>)newValue);
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
			case PnwPackage.PLACE__NAME:
				setName(NAME_EDEFAULT);
				return;
			case PnwPackage.PLACE__NO_OF_TOKENS:
				setNoOfTokens(NO_OF_TOKENS_EDEFAULT);
				return;
			case PnwPackage.PLACE__OUT_PT_EDGES:
				getOutPTEdges().clear();
				return;
			case PnwPackage.PLACE__IN_TP_EDGES:
				getInTPEdges().clear();
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
			case PnwPackage.PLACE__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case PnwPackage.PLACE__NO_OF_TOKENS:
				return noOfTokens != NO_OF_TOKENS_EDEFAULT;
			case PnwPackage.PLACE__OUT_PT_EDGES:
				return outPTEdges != null && !outPTEdges.isEmpty();
			case PnwPackage.PLACE__IN_TP_EDGES:
				return inTPEdges != null && !inTPEdges.isEmpty();
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
				case PnwPackage.PLACE__NAME: return PnwPackage.NAMED_ELEMENT__NAME;
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
				case PnwPackage.NAMED_ELEMENT__NAME: return PnwPackage.PLACE__NAME;
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
		result.append(", noOfTokens: ");
		result.append(noOfTokens);
		result.append(')');
		return result.toString();
	}

} //PlaceImpl
