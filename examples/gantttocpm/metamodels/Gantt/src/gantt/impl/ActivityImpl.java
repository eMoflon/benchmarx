/**
 */
package gantt.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

import gantt.Activity;
import gantt.Dependency;
import gantt.GanttPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Activity</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link gantt.impl.ActivityImpl#getName <em>Name</em>}</li>
 *   <li>{@link gantt.impl.ActivityImpl#getDuration <em>Duration</em>}</li>
 *   <li>{@link gantt.impl.ActivityImpl#getOutgoingDependencies <em>Outgoing Dependencies</em>}</li>
 *   <li>{@link gantt.impl.ActivityImpl#getIncomingDependencies <em>Incoming Dependencies</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ActivityImpl extends ElementImpl implements Activity {
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
	 * The default value of the '{@link #getDuration() <em>Duration</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDuration()
	 * @generated
	 * @ordered
	 */
	protected static final int DURATION_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getDuration() <em>Duration</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDuration()
	 * @generated
	 * @ordered
	 */
	protected int duration = DURATION_EDEFAULT;

	/**
	 * The cached value of the '{@link #getOutgoingDependencies() <em>Outgoing Dependencies</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOutgoingDependencies()
	 * @generated
	 * @ordered
	 */
	protected EList<Dependency> outgoingDependencies;

	/**
	 * The cached value of the '{@link #getIncomingDependencies() <em>Incoming Dependencies</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIncomingDependencies()
	 * @generated
	 * @ordered
	 */
	protected EList<Dependency> incomingDependencies;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ActivityImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return GanttPackage.Literals.ACTIVITY;
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
			eNotify(new ENotificationImpl(this, Notification.SET, GanttPackage.ACTIVITY__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getDuration() {
		return duration;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDuration(int newDuration) {
		int oldDuration = duration;
		duration = newDuration;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GanttPackage.ACTIVITY__DURATION, oldDuration, duration));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Dependency> getOutgoingDependencies() {
		if (outgoingDependencies == null) {
			outgoingDependencies = new EObjectWithInverseResolvingEList<Dependency>(Dependency.class, this, GanttPackage.ACTIVITY__OUTGOING_DEPENDENCIES, GanttPackage.DEPENDENCY__PREDECESSOR);
		}
		return outgoingDependencies;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Dependency> getIncomingDependencies() {
		if (incomingDependencies == null) {
			incomingDependencies = new EObjectWithInverseResolvingEList<Dependency>(Dependency.class, this, GanttPackage.ACTIVITY__INCOMING_DEPENDENCIES, GanttPackage.DEPENDENCY__SUCCESSOR);
		}
		return incomingDependencies;
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
			case GanttPackage.ACTIVITY__OUTGOING_DEPENDENCIES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOutgoingDependencies()).basicAdd(otherEnd, msgs);
			case GanttPackage.ACTIVITY__INCOMING_DEPENDENCIES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getIncomingDependencies()).basicAdd(otherEnd, msgs);
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
			case GanttPackage.ACTIVITY__OUTGOING_DEPENDENCIES:
				return ((InternalEList<?>)getOutgoingDependencies()).basicRemove(otherEnd, msgs);
			case GanttPackage.ACTIVITY__INCOMING_DEPENDENCIES:
				return ((InternalEList<?>)getIncomingDependencies()).basicRemove(otherEnd, msgs);
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
			case GanttPackage.ACTIVITY__NAME:
				return getName();
			case GanttPackage.ACTIVITY__DURATION:
				return getDuration();
			case GanttPackage.ACTIVITY__OUTGOING_DEPENDENCIES:
				return getOutgoingDependencies();
			case GanttPackage.ACTIVITY__INCOMING_DEPENDENCIES:
				return getIncomingDependencies();
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
			case GanttPackage.ACTIVITY__NAME:
				setName((String)newValue);
				return;
			case GanttPackage.ACTIVITY__DURATION:
				setDuration((Integer)newValue);
				return;
			case GanttPackage.ACTIVITY__OUTGOING_DEPENDENCIES:
				getOutgoingDependencies().clear();
				getOutgoingDependencies().addAll((Collection<? extends Dependency>)newValue);
				return;
			case GanttPackage.ACTIVITY__INCOMING_DEPENDENCIES:
				getIncomingDependencies().clear();
				getIncomingDependencies().addAll((Collection<? extends Dependency>)newValue);
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
			case GanttPackage.ACTIVITY__NAME:
				setName(NAME_EDEFAULT);
				return;
			case GanttPackage.ACTIVITY__DURATION:
				setDuration(DURATION_EDEFAULT);
				return;
			case GanttPackage.ACTIVITY__OUTGOING_DEPENDENCIES:
				getOutgoingDependencies().clear();
				return;
			case GanttPackage.ACTIVITY__INCOMING_DEPENDENCIES:
				getIncomingDependencies().clear();
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
			case GanttPackage.ACTIVITY__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case GanttPackage.ACTIVITY__DURATION:
				return duration != DURATION_EDEFAULT;
			case GanttPackage.ACTIVITY__OUTGOING_DEPENDENCIES:
				return outgoingDependencies != null && !outgoingDependencies.isEmpty();
			case GanttPackage.ACTIVITY__INCOMING_DEPENDENCIES:
				return incomingDependencies != null && !incomingDependencies.isEmpty();
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
		result.append(" (name: ");
		result.append(name);
		result.append(", duration: ");
		result.append(duration);
		result.append(')');
		return result.toString();
	}

} //ActivityImpl
