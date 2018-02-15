/**
 */
package gantt.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import gantt.Activity;
import gantt.Dependency;
import gantt.DependencyType;
import gantt.GanttPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Dependency</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link gantt.impl.DependencyImpl#getPredecessor <em>Predecessor</em>}</li>
 *   <li>{@link gantt.impl.DependencyImpl#getSuccessor <em>Successor</em>}</li>
 *   <li>{@link gantt.impl.DependencyImpl#getDependencyType <em>Dependency Type</em>}</li>
 *   <li>{@link gantt.impl.DependencyImpl#getOffset <em>Offset</em>}</li>
 * </ul>
 *
 * @generated
 */
public class DependencyImpl extends ElementImpl implements Dependency {
	/**
	 * The cached value of the '{@link #getPredecessor() <em>Predecessor</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPredecessor()
	 * @generated
	 * @ordered
	 */
	protected Activity predecessor;

	/**
	 * The cached value of the '{@link #getSuccessor() <em>Successor</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSuccessor()
	 * @generated
	 * @ordered
	 */
	protected Activity successor;

	/**
	 * The default value of the '{@link #getDependencyType() <em>Dependency Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDependencyType()
	 * @generated
	 * @ordered
	 */
	protected static final DependencyType DEPENDENCY_TYPE_EDEFAULT = DependencyType.START_START;

	/**
	 * The cached value of the '{@link #getDependencyType() <em>Dependency Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDependencyType()
	 * @generated
	 * @ordered
	 */
	protected DependencyType dependencyType = DEPENDENCY_TYPE_EDEFAULT;

	/**
	 * The default value of the '{@link #getOffset() <em>Offset</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOffset()
	 * @generated
	 * @ordered
	 */
	protected static final int OFFSET_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getOffset() <em>Offset</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOffset()
	 * @generated
	 * @ordered
	 */
	protected int offset = OFFSET_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DependencyImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return GanttPackage.Literals.DEPENDENCY;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Activity getPredecessor() {
		if (predecessor != null && predecessor.eIsProxy()) {
			InternalEObject oldPredecessor = (InternalEObject)predecessor;
			predecessor = (Activity)eResolveProxy(oldPredecessor);
			if (predecessor != oldPredecessor) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, GanttPackage.DEPENDENCY__PREDECESSOR, oldPredecessor, predecessor));
			}
		}
		return predecessor;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Activity basicGetPredecessor() {
		return predecessor;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetPredecessor(Activity newPredecessor, NotificationChain msgs) {
		Activity oldPredecessor = predecessor;
		predecessor = newPredecessor;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, GanttPackage.DEPENDENCY__PREDECESSOR, oldPredecessor, newPredecessor);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPredecessor(Activity newPredecessor) {
		if (newPredecessor != predecessor) {
			NotificationChain msgs = null;
			if (predecessor != null)
				msgs = ((InternalEObject)predecessor).eInverseRemove(this, GanttPackage.ACTIVITY__OUTGOING_DEPENDENCIES, Activity.class, msgs);
			if (newPredecessor != null)
				msgs = ((InternalEObject)newPredecessor).eInverseAdd(this, GanttPackage.ACTIVITY__OUTGOING_DEPENDENCIES, Activity.class, msgs);
			msgs = basicSetPredecessor(newPredecessor, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GanttPackage.DEPENDENCY__PREDECESSOR, newPredecessor, newPredecessor));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Activity getSuccessor() {
		if (successor != null && successor.eIsProxy()) {
			InternalEObject oldSuccessor = (InternalEObject)successor;
			successor = (Activity)eResolveProxy(oldSuccessor);
			if (successor != oldSuccessor) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, GanttPackage.DEPENDENCY__SUCCESSOR, oldSuccessor, successor));
			}
		}
		return successor;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Activity basicGetSuccessor() {
		return successor;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetSuccessor(Activity newSuccessor, NotificationChain msgs) {
		Activity oldSuccessor = successor;
		successor = newSuccessor;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, GanttPackage.DEPENDENCY__SUCCESSOR, oldSuccessor, newSuccessor);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSuccessor(Activity newSuccessor) {
		if (newSuccessor != successor) {
			NotificationChain msgs = null;
			if (successor != null)
				msgs = ((InternalEObject)successor).eInverseRemove(this, GanttPackage.ACTIVITY__INCOMING_DEPENDENCIES, Activity.class, msgs);
			if (newSuccessor != null)
				msgs = ((InternalEObject)newSuccessor).eInverseAdd(this, GanttPackage.ACTIVITY__INCOMING_DEPENDENCIES, Activity.class, msgs);
			msgs = basicSetSuccessor(newSuccessor, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GanttPackage.DEPENDENCY__SUCCESSOR, newSuccessor, newSuccessor));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DependencyType getDependencyType() {
		return dependencyType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDependencyType(DependencyType newDependencyType) {
		DependencyType oldDependencyType = dependencyType;
		dependencyType = newDependencyType == null ? DEPENDENCY_TYPE_EDEFAULT : newDependencyType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GanttPackage.DEPENDENCY__DEPENDENCY_TYPE, oldDependencyType, dependencyType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getOffset() {
		return offset;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOffset(int newOffset) {
		int oldOffset = offset;
		offset = newOffset;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GanttPackage.DEPENDENCY__OFFSET, oldOffset, offset));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case GanttPackage.DEPENDENCY__PREDECESSOR:
				if (predecessor != null)
					msgs = ((InternalEObject)predecessor).eInverseRemove(this, GanttPackage.ACTIVITY__OUTGOING_DEPENDENCIES, Activity.class, msgs);
				return basicSetPredecessor((Activity)otherEnd, msgs);
			case GanttPackage.DEPENDENCY__SUCCESSOR:
				if (successor != null)
					msgs = ((InternalEObject)successor).eInverseRemove(this, GanttPackage.ACTIVITY__INCOMING_DEPENDENCIES, Activity.class, msgs);
				return basicSetSuccessor((Activity)otherEnd, msgs);
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
			case GanttPackage.DEPENDENCY__PREDECESSOR:
				return basicSetPredecessor(null, msgs);
			case GanttPackage.DEPENDENCY__SUCCESSOR:
				return basicSetSuccessor(null, msgs);
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
			case GanttPackage.DEPENDENCY__PREDECESSOR:
				if (resolve) return getPredecessor();
				return basicGetPredecessor();
			case GanttPackage.DEPENDENCY__SUCCESSOR:
				if (resolve) return getSuccessor();
				return basicGetSuccessor();
			case GanttPackage.DEPENDENCY__DEPENDENCY_TYPE:
				return getDependencyType();
			case GanttPackage.DEPENDENCY__OFFSET:
				return getOffset();
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
			case GanttPackage.DEPENDENCY__PREDECESSOR:
				setPredecessor((Activity)newValue);
				return;
			case GanttPackage.DEPENDENCY__SUCCESSOR:
				setSuccessor((Activity)newValue);
				return;
			case GanttPackage.DEPENDENCY__DEPENDENCY_TYPE:
				setDependencyType((DependencyType)newValue);
				return;
			case GanttPackage.DEPENDENCY__OFFSET:
				setOffset((Integer)newValue);
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
			case GanttPackage.DEPENDENCY__PREDECESSOR:
				setPredecessor((Activity)null);
				return;
			case GanttPackage.DEPENDENCY__SUCCESSOR:
				setSuccessor((Activity)null);
				return;
			case GanttPackage.DEPENDENCY__DEPENDENCY_TYPE:
				setDependencyType(DEPENDENCY_TYPE_EDEFAULT);
				return;
			case GanttPackage.DEPENDENCY__OFFSET:
				setOffset(OFFSET_EDEFAULT);
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
			case GanttPackage.DEPENDENCY__PREDECESSOR:
				return predecessor != null;
			case GanttPackage.DEPENDENCY__SUCCESSOR:
				return successor != null;
			case GanttPackage.DEPENDENCY__DEPENDENCY_TYPE:
				return dependencyType != DEPENDENCY_TYPE_EDEFAULT;
			case GanttPackage.DEPENDENCY__OFFSET:
				return offset != OFFSET_EDEFAULT;
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
		result.append(" (dependencyType: ");
		result.append(dependencyType);
		result.append(", offset: ");
		result.append(offset);
		result.append(')');
		return result.toString();
	}

} //DependencyImpl
