/**
 */
package pn.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

import pn.Place;
import pn.PnPackage;
import pn.Transition;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Place</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link pn.impl.PlaceImpl#getTrgP2T <em>Trg P2T</em>}</li>
 *   <li>{@link pn.impl.PlaceImpl#getSrcT2P <em>Src T2P</em>}</li>
 *   <li>{@link pn.impl.PlaceImpl#getNoOfTokens <em>No Of Tokens</em>}</li>
 * </ul>
 *
 * @generated
 */
public class PlaceImpl extends NetElementImpl implements Place {
	/**
	 * The cached value of the '{@link #getTrgP2T() <em>Trg P2T</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTrgP2T()
	 * @generated
	 * @ordered
	 */
	protected EList<Transition> trgP2T;

	/**
	 * The cached value of the '{@link #getSrcT2P() <em>Src T2P</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSrcT2P()
	 * @generated
	 * @ordered
	 */
	protected EList<Transition> srcT2P;

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
		return PnPackage.Literals.PLACE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Transition> getTrgP2T() {
		if (trgP2T == null) {
			trgP2T = new EObjectWithInverseResolvingEList.ManyInverse<Transition>(Transition.class, this, PnPackage.PLACE__TRG_P2T, PnPackage.TRANSITION__SRC_P2T);
		}
		return trgP2T;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Transition> getSrcT2P() {
		if (srcT2P == null) {
			srcT2P = new EObjectWithInverseResolvingEList.ManyInverse<Transition>(Transition.class, this, PnPackage.PLACE__SRC_T2P, PnPackage.TRANSITION__TRG_T2P);
		}
		return srcT2P;
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
			eNotify(new ENotificationImpl(this, Notification.SET, PnPackage.PLACE__NO_OF_TOKENS, oldNoOfTokens, noOfTokens));
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
			case PnPackage.PLACE__TRG_P2T:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getTrgP2T()).basicAdd(otherEnd, msgs);
			case PnPackage.PLACE__SRC_T2P:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getSrcT2P()).basicAdd(otherEnd, msgs);
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
			case PnPackage.PLACE__TRG_P2T:
				return ((InternalEList<?>)getTrgP2T()).basicRemove(otherEnd, msgs);
			case PnPackage.PLACE__SRC_T2P:
				return ((InternalEList<?>)getSrcT2P()).basicRemove(otherEnd, msgs);
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
			case PnPackage.PLACE__TRG_P2T:
				return getTrgP2T();
			case PnPackage.PLACE__SRC_T2P:
				return getSrcT2P();
			case PnPackage.PLACE__NO_OF_TOKENS:
				return getNoOfTokens();
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
			case PnPackage.PLACE__TRG_P2T:
				getTrgP2T().clear();
				getTrgP2T().addAll((Collection<? extends Transition>)newValue);
				return;
			case PnPackage.PLACE__SRC_T2P:
				getSrcT2P().clear();
				getSrcT2P().addAll((Collection<? extends Transition>)newValue);
				return;
			case PnPackage.PLACE__NO_OF_TOKENS:
				setNoOfTokens((Integer)newValue);
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
			case PnPackage.PLACE__TRG_P2T:
				getTrgP2T().clear();
				return;
			case PnPackage.PLACE__SRC_T2P:
				getSrcT2P().clear();
				return;
			case PnPackage.PLACE__NO_OF_TOKENS:
				setNoOfTokens(NO_OF_TOKENS_EDEFAULT);
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
			case PnPackage.PLACE__TRG_P2T:
				return trgP2T != null && !trgP2T.isEmpty();
			case PnPackage.PLACE__SRC_T2P:
				return srcT2P != null && !srcT2P.isEmpty();
			case PnPackage.PLACE__NO_OF_TOKENS:
				return noOfTokens != NO_OF_TOKENS_EDEFAULT;
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
		result.append(" (noOfTokens: ");
		result.append(noOfTokens);
		result.append(')');
		return result.toString();
	}

} //PlaceImpl
