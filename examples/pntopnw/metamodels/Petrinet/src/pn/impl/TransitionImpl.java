/**
 */
package pn.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

import pn.Place;
import pn.PnPackage;
import pn.Transition;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Transition</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link pn.impl.TransitionImpl#getTrgT2P <em>Trg T2P</em>}</li>
 *   <li>{@link pn.impl.TransitionImpl#getSrcP2T <em>Src P2T</em>}</li>
 * </ul>
 *
 * @generated
 */
public class TransitionImpl extends NetElementImpl implements Transition {
	/**
	 * The cached value of the '{@link #getTrgT2P() <em>Trg T2P</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTrgT2P()
	 * @generated
	 * @ordered
	 */
	protected EList<Place> trgT2P;

	/**
	 * The cached value of the '{@link #getSrcP2T() <em>Src P2T</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSrcP2T()
	 * @generated
	 * @ordered
	 */
	protected EList<Place> srcP2T;

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
		return PnPackage.Literals.TRANSITION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Place> getTrgT2P() {
		if (trgT2P == null) {
			trgT2P = new EObjectWithInverseResolvingEList.ManyInverse<Place>(Place.class, this, PnPackage.TRANSITION__TRG_T2P, PnPackage.PLACE__SRC_T2P);
		}
		return trgT2P;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Place> getSrcP2T() {
		if (srcP2T == null) {
			srcP2T = new EObjectWithInverseResolvingEList.ManyInverse<Place>(Place.class, this, PnPackage.TRANSITION__SRC_P2T, PnPackage.PLACE__TRG_P2T);
		}
		return srcP2T;
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
			case PnPackage.TRANSITION__TRG_T2P:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getTrgT2P()).basicAdd(otherEnd, msgs);
			case PnPackage.TRANSITION__SRC_P2T:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getSrcP2T()).basicAdd(otherEnd, msgs);
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
			case PnPackage.TRANSITION__TRG_T2P:
				return ((InternalEList<?>)getTrgT2P()).basicRemove(otherEnd, msgs);
			case PnPackage.TRANSITION__SRC_P2T:
				return ((InternalEList<?>)getSrcP2T()).basicRemove(otherEnd, msgs);
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
			case PnPackage.TRANSITION__TRG_T2P:
				return getTrgT2P();
			case PnPackage.TRANSITION__SRC_P2T:
				return getSrcP2T();
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
			case PnPackage.TRANSITION__TRG_T2P:
				getTrgT2P().clear();
				getTrgT2P().addAll((Collection<? extends Place>)newValue);
				return;
			case PnPackage.TRANSITION__SRC_P2T:
				getSrcP2T().clear();
				getSrcP2T().addAll((Collection<? extends Place>)newValue);
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
			case PnPackage.TRANSITION__TRG_T2P:
				getTrgT2P().clear();
				return;
			case PnPackage.TRANSITION__SRC_P2T:
				getSrcP2T().clear();
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
			case PnPackage.TRANSITION__TRG_T2P:
				return trgT2P != null && !trgT2P.isEmpty();
			case PnPackage.TRANSITION__SRC_P2T:
				return srcP2T != null && !srcP2T.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //TransitionImpl
