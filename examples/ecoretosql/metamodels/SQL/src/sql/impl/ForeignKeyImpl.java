/**
 */
package sql.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

import sql.Event;
import sql.ForeignKey;
import sql.SqlPackage;
import sql.Table;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Foreign Key</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link sql.impl.ForeignKeyImpl#getReferencedTable <em>Referenced Table</em>}</li>
 *   <li>{@link sql.impl.ForeignKeyImpl#getOwningTable <em>Owning Table</em>}</li>
 *   <li>{@link sql.impl.ForeignKeyImpl#getOwnedEvents <em>Owned Events</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ForeignKeyImpl extends KeyImpl implements ForeignKey {
	/**
	 * The cached value of the '{@link #getReferencedTable() <em>Referenced Table</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReferencedTable()
	 * @generated
	 * @ordered
	 */
	protected Table referencedTable;

	/**
	 * The cached value of the '{@link #getOwnedEvents() <em>Owned Events</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedEvents()
	 * @generated
	 * @ordered
	 */
	protected EList<Event> ownedEvents;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ForeignKeyImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return SqlPackage.Literals.FOREIGN_KEY;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Table getReferencedTable() {
		if (referencedTable != null && referencedTable.eIsProxy()) {
			InternalEObject oldReferencedTable = (InternalEObject)referencedTable;
			referencedTable = (Table)eResolveProxy(oldReferencedTable);
			if (referencedTable != oldReferencedTable) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, SqlPackage.FOREIGN_KEY__REFERENCED_TABLE, oldReferencedTable, referencedTable));
			}
		}
		return referencedTable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Table basicGetReferencedTable() {
		return referencedTable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetReferencedTable(Table newReferencedTable, NotificationChain msgs) {
		Table oldReferencedTable = referencedTable;
		referencedTable = newReferencedTable;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SqlPackage.FOREIGN_KEY__REFERENCED_TABLE, oldReferencedTable, newReferencedTable);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setReferencedTable(Table newReferencedTable) {
		if (newReferencedTable != referencedTable) {
			NotificationChain msgs = null;
			if (referencedTable != null)
				msgs = ((InternalEObject)referencedTable).eInverseRemove(this, SqlPackage.TABLE__REFERENCING_FOREIGN_KEYS, Table.class, msgs);
			if (newReferencedTable != null)
				msgs = ((InternalEObject)newReferencedTable).eInverseAdd(this, SqlPackage.TABLE__REFERENCING_FOREIGN_KEYS, Table.class, msgs);
			msgs = basicSetReferencedTable(newReferencedTable, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SqlPackage.FOREIGN_KEY__REFERENCED_TABLE, newReferencedTable, newReferencedTable));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Table getOwningTable() {
		if (eContainerFeatureID() != SqlPackage.FOREIGN_KEY__OWNING_TABLE) return null;
		return (Table)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOwningTable(Table newOwningTable, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newOwningTable, SqlPackage.FOREIGN_KEY__OWNING_TABLE, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOwningTable(Table newOwningTable) {
		if (newOwningTable != eInternalContainer() || (eContainerFeatureID() != SqlPackage.FOREIGN_KEY__OWNING_TABLE && newOwningTable != null)) {
			if (EcoreUtil.isAncestor(this, newOwningTable))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newOwningTable != null)
				msgs = ((InternalEObject)newOwningTable).eInverseAdd(this, SqlPackage.TABLE__OWNED_FOREIGN_KEYS, Table.class, msgs);
			msgs = basicSetOwningTable(newOwningTable, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SqlPackage.FOREIGN_KEY__OWNING_TABLE, newOwningTable, newOwningTable));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Event> getOwnedEvents() {
		if (ownedEvents == null) {
			ownedEvents = new EObjectContainmentWithInverseEList<Event>(Event.class, this, SqlPackage.FOREIGN_KEY__OWNED_EVENTS, SqlPackage.EVENT__OWNING_FOREIGN_KEY);
		}
		return ownedEvents;
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
			case SqlPackage.FOREIGN_KEY__REFERENCED_TABLE:
				if (referencedTable != null)
					msgs = ((InternalEObject)referencedTable).eInverseRemove(this, SqlPackage.TABLE__REFERENCING_FOREIGN_KEYS, Table.class, msgs);
				return basicSetReferencedTable((Table)otherEnd, msgs);
			case SqlPackage.FOREIGN_KEY__OWNING_TABLE:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetOwningTable((Table)otherEnd, msgs);
			case SqlPackage.FOREIGN_KEY__OWNED_EVENTS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOwnedEvents()).basicAdd(otherEnd, msgs);
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
			case SqlPackage.FOREIGN_KEY__REFERENCED_TABLE:
				return basicSetReferencedTable(null, msgs);
			case SqlPackage.FOREIGN_KEY__OWNING_TABLE:
				return basicSetOwningTable(null, msgs);
			case SqlPackage.FOREIGN_KEY__OWNED_EVENTS:
				return ((InternalEList<?>)getOwnedEvents()).basicRemove(otherEnd, msgs);
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
			case SqlPackage.FOREIGN_KEY__OWNING_TABLE:
				return eInternalContainer().eInverseRemove(this, SqlPackage.TABLE__OWNED_FOREIGN_KEYS, Table.class, msgs);
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
			case SqlPackage.FOREIGN_KEY__REFERENCED_TABLE:
				if (resolve) return getReferencedTable();
				return basicGetReferencedTable();
			case SqlPackage.FOREIGN_KEY__OWNING_TABLE:
				return getOwningTable();
			case SqlPackage.FOREIGN_KEY__OWNED_EVENTS:
				return getOwnedEvents();
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
			case SqlPackage.FOREIGN_KEY__REFERENCED_TABLE:
				setReferencedTable((Table)newValue);
				return;
			case SqlPackage.FOREIGN_KEY__OWNING_TABLE:
				setOwningTable((Table)newValue);
				return;
			case SqlPackage.FOREIGN_KEY__OWNED_EVENTS:
				getOwnedEvents().clear();
				getOwnedEvents().addAll((Collection<? extends Event>)newValue);
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
			case SqlPackage.FOREIGN_KEY__REFERENCED_TABLE:
				setReferencedTable((Table)null);
				return;
			case SqlPackage.FOREIGN_KEY__OWNING_TABLE:
				setOwningTable((Table)null);
				return;
			case SqlPackage.FOREIGN_KEY__OWNED_EVENTS:
				getOwnedEvents().clear();
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
			case SqlPackage.FOREIGN_KEY__REFERENCED_TABLE:
				return referencedTable != null;
			case SqlPackage.FOREIGN_KEY__OWNING_TABLE:
				return getOwningTable() != null;
			case SqlPackage.FOREIGN_KEY__OWNED_EVENTS:
				return ownedEvents != null && !ownedEvents.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //ForeignKeyImpl
