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
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

import sql.Column;
import sql.ForeignKey;
import sql.PrimaryKey;
import sql.Schema;
import sql.SqlPackage;
import sql.Table;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Table</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link sql.impl.TableImpl#getOwnedColumns <em>Owned Columns</em>}</li>
 *   <li>{@link sql.impl.TableImpl#getOwnedPrimaryKey <em>Owned Primary Key</em>}</li>
 *   <li>{@link sql.impl.TableImpl#getOwnedForeignKeys <em>Owned Foreign Keys</em>}</li>
 *   <li>{@link sql.impl.TableImpl#getReferencingForeignKeys <em>Referencing Foreign Keys</em>}</li>
 *   <li>{@link sql.impl.TableImpl#getOwningSchema <em>Owning Schema</em>}</li>
 * </ul>
 *
 * @generated
 */
public class TableImpl extends NamedElementImpl implements Table {
	/**
	 * The cached value of the '{@link #getOwnedColumns() <em>Owned Columns</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedColumns()
	 * @generated
	 * @ordered
	 */
	protected EList<Column> ownedColumns;

	/**
	 * The cached value of the '{@link #getOwnedPrimaryKey() <em>Owned Primary Key</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedPrimaryKey()
	 * @generated
	 * @ordered
	 */
	protected PrimaryKey ownedPrimaryKey;

	/**
	 * The cached value of the '{@link #getOwnedForeignKeys() <em>Owned Foreign Keys</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedForeignKeys()
	 * @generated
	 * @ordered
	 */
	protected EList<ForeignKey> ownedForeignKeys;

	/**
	 * The cached value of the '{@link #getReferencingForeignKeys() <em>Referencing Foreign Keys</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReferencingForeignKeys()
	 * @generated
	 * @ordered
	 */
	protected EList<ForeignKey> referencingForeignKeys;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TableImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return SqlPackage.Literals.TABLE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Column> getOwnedColumns() {
		if (ownedColumns == null) {
			ownedColumns = new EObjectContainmentWithInverseEList<Column>(Column.class, this, SqlPackage.TABLE__OWNED_COLUMNS, SqlPackage.COLUMN__OWNING_TABLE);
		}
		return ownedColumns;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PrimaryKey getOwnedPrimaryKey() {
		return ownedPrimaryKey;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOwnedPrimaryKey(PrimaryKey newOwnedPrimaryKey, NotificationChain msgs) {
		PrimaryKey oldOwnedPrimaryKey = ownedPrimaryKey;
		ownedPrimaryKey = newOwnedPrimaryKey;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SqlPackage.TABLE__OWNED_PRIMARY_KEY, oldOwnedPrimaryKey, newOwnedPrimaryKey);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOwnedPrimaryKey(PrimaryKey newOwnedPrimaryKey) {
		if (newOwnedPrimaryKey != ownedPrimaryKey) {
			NotificationChain msgs = null;
			if (ownedPrimaryKey != null)
				msgs = ((InternalEObject)ownedPrimaryKey).eInverseRemove(this, SqlPackage.PRIMARY_KEY__OWNING_TABLE, PrimaryKey.class, msgs);
			if (newOwnedPrimaryKey != null)
				msgs = ((InternalEObject)newOwnedPrimaryKey).eInverseAdd(this, SqlPackage.PRIMARY_KEY__OWNING_TABLE, PrimaryKey.class, msgs);
			msgs = basicSetOwnedPrimaryKey(newOwnedPrimaryKey, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SqlPackage.TABLE__OWNED_PRIMARY_KEY, newOwnedPrimaryKey, newOwnedPrimaryKey));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ForeignKey> getOwnedForeignKeys() {
		if (ownedForeignKeys == null) {
			ownedForeignKeys = new EObjectContainmentWithInverseEList<ForeignKey>(ForeignKey.class, this, SqlPackage.TABLE__OWNED_FOREIGN_KEYS, SqlPackage.FOREIGN_KEY__OWNING_TABLE);
		}
		return ownedForeignKeys;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ForeignKey> getReferencingForeignKeys() {
		if (referencingForeignKeys == null) {
			referencingForeignKeys = new EObjectWithInverseResolvingEList<ForeignKey>(ForeignKey.class, this, SqlPackage.TABLE__REFERENCING_FOREIGN_KEYS, SqlPackage.FOREIGN_KEY__REFERENCED_TABLE);
		}
		return referencingForeignKeys;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Schema getOwningSchema() {
		if (eContainerFeatureID() != SqlPackage.TABLE__OWNING_SCHEMA) return null;
		return (Schema)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOwningSchema(Schema newOwningSchema, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newOwningSchema, SqlPackage.TABLE__OWNING_SCHEMA, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOwningSchema(Schema newOwningSchema) {
		if (newOwningSchema != eInternalContainer() || (eContainerFeatureID() != SqlPackage.TABLE__OWNING_SCHEMA && newOwningSchema != null)) {
			if (EcoreUtil.isAncestor(this, newOwningSchema))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newOwningSchema != null)
				msgs = ((InternalEObject)newOwningSchema).eInverseAdd(this, SqlPackage.SCHEMA__OWNED_TABLES, Schema.class, msgs);
			msgs = basicSetOwningSchema(newOwningSchema, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SqlPackage.TABLE__OWNING_SCHEMA, newOwningSchema, newOwningSchema));
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
			case SqlPackage.TABLE__OWNED_COLUMNS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOwnedColumns()).basicAdd(otherEnd, msgs);
			case SqlPackage.TABLE__OWNED_PRIMARY_KEY:
				if (ownedPrimaryKey != null)
					msgs = ((InternalEObject)ownedPrimaryKey).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SqlPackage.TABLE__OWNED_PRIMARY_KEY, null, msgs);
				return basicSetOwnedPrimaryKey((PrimaryKey)otherEnd, msgs);
			case SqlPackage.TABLE__OWNED_FOREIGN_KEYS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOwnedForeignKeys()).basicAdd(otherEnd, msgs);
			case SqlPackage.TABLE__REFERENCING_FOREIGN_KEYS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getReferencingForeignKeys()).basicAdd(otherEnd, msgs);
			case SqlPackage.TABLE__OWNING_SCHEMA:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetOwningSchema((Schema)otherEnd, msgs);
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
			case SqlPackage.TABLE__OWNED_COLUMNS:
				return ((InternalEList<?>)getOwnedColumns()).basicRemove(otherEnd, msgs);
			case SqlPackage.TABLE__OWNED_PRIMARY_KEY:
				return basicSetOwnedPrimaryKey(null, msgs);
			case SqlPackage.TABLE__OWNED_FOREIGN_KEYS:
				return ((InternalEList<?>)getOwnedForeignKeys()).basicRemove(otherEnd, msgs);
			case SqlPackage.TABLE__REFERENCING_FOREIGN_KEYS:
				return ((InternalEList<?>)getReferencingForeignKeys()).basicRemove(otherEnd, msgs);
			case SqlPackage.TABLE__OWNING_SCHEMA:
				return basicSetOwningSchema(null, msgs);
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
			case SqlPackage.TABLE__OWNING_SCHEMA:
				return eInternalContainer().eInverseRemove(this, SqlPackage.SCHEMA__OWNED_TABLES, Schema.class, msgs);
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
			case SqlPackage.TABLE__OWNED_COLUMNS:
				return getOwnedColumns();
			case SqlPackage.TABLE__OWNED_PRIMARY_KEY:
				return getOwnedPrimaryKey();
			case SqlPackage.TABLE__OWNED_FOREIGN_KEYS:
				return getOwnedForeignKeys();
			case SqlPackage.TABLE__REFERENCING_FOREIGN_KEYS:
				return getReferencingForeignKeys();
			case SqlPackage.TABLE__OWNING_SCHEMA:
				return getOwningSchema();
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
			case SqlPackage.TABLE__OWNED_COLUMNS:
				getOwnedColumns().clear();
				getOwnedColumns().addAll((Collection<? extends Column>)newValue);
				return;
			case SqlPackage.TABLE__OWNED_PRIMARY_KEY:
				setOwnedPrimaryKey((PrimaryKey)newValue);
				return;
			case SqlPackage.TABLE__OWNED_FOREIGN_KEYS:
				getOwnedForeignKeys().clear();
				getOwnedForeignKeys().addAll((Collection<? extends ForeignKey>)newValue);
				return;
			case SqlPackage.TABLE__REFERENCING_FOREIGN_KEYS:
				getReferencingForeignKeys().clear();
				getReferencingForeignKeys().addAll((Collection<? extends ForeignKey>)newValue);
				return;
			case SqlPackage.TABLE__OWNING_SCHEMA:
				setOwningSchema((Schema)newValue);
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
			case SqlPackage.TABLE__OWNED_COLUMNS:
				getOwnedColumns().clear();
				return;
			case SqlPackage.TABLE__OWNED_PRIMARY_KEY:
				setOwnedPrimaryKey((PrimaryKey)null);
				return;
			case SqlPackage.TABLE__OWNED_FOREIGN_KEYS:
				getOwnedForeignKeys().clear();
				return;
			case SqlPackage.TABLE__REFERENCING_FOREIGN_KEYS:
				getReferencingForeignKeys().clear();
				return;
			case SqlPackage.TABLE__OWNING_SCHEMA:
				setOwningSchema((Schema)null);
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
			case SqlPackage.TABLE__OWNED_COLUMNS:
				return ownedColumns != null && !ownedColumns.isEmpty();
			case SqlPackage.TABLE__OWNED_PRIMARY_KEY:
				return ownedPrimaryKey != null;
			case SqlPackage.TABLE__OWNED_FOREIGN_KEYS:
				return ownedForeignKeys != null && !ownedForeignKeys.isEmpty();
			case SqlPackage.TABLE__REFERENCING_FOREIGN_KEYS:
				return referencingForeignKeys != null && !referencingForeignKeys.isEmpty();
			case SqlPackage.TABLE__OWNING_SCHEMA:
				return getOwningSchema() != null;
		}
		return super.eIsSet(featureID);
	}

} //TableImpl
