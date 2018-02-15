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

import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

import sql.Column;
import sql.Key;
import sql.Property;
import sql.SqlPackage;
import sql.Table;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Column</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link sql.impl.ColumnImpl#getOwningTable <em>Owning Table</em>}</li>
 *   <li>{@link sql.impl.ColumnImpl#getType <em>Type</em>}</li>
 *   <li>{@link sql.impl.ColumnImpl#getKeys <em>Keys</em>}</li>
 *   <li>{@link sql.impl.ColumnImpl#getProperties <em>Properties</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ColumnImpl extends NamedElementImpl implements Column {
	/**
	 * The default value of the '{@link #getType() <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected static final String TYPE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getType() <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected String type = TYPE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getKeys() <em>Keys</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getKeys()
	 * @generated
	 * @ordered
	 */
	protected EList<Key> keys;

	/**
	 * The cached value of the '{@link #getProperties() <em>Properties</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProperties()
	 * @generated
	 * @ordered
	 */
	protected EList<Property> properties;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ColumnImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return SqlPackage.Literals.COLUMN;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Table getOwningTable() {
		if (eContainerFeatureID() != SqlPackage.COLUMN__OWNING_TABLE) return null;
		return (Table)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOwningTable(Table newOwningTable, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newOwningTable, SqlPackage.COLUMN__OWNING_TABLE, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOwningTable(Table newOwningTable) {
		if (newOwningTable != eInternalContainer() || (eContainerFeatureID() != SqlPackage.COLUMN__OWNING_TABLE && newOwningTable != null)) {
			if (EcoreUtil.isAncestor(this, newOwningTable))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newOwningTable != null)
				msgs = ((InternalEObject)newOwningTable).eInverseAdd(this, SqlPackage.TABLE__OWNED_COLUMNS, Table.class, msgs);
			msgs = basicSetOwningTable(newOwningTable, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SqlPackage.COLUMN__OWNING_TABLE, newOwningTable, newOwningTable));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getType() {
		return type;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setType(String newType) {
		String oldType = type;
		type = newType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SqlPackage.COLUMN__TYPE, oldType, type));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Key> getKeys() {
		if (keys == null) {
			keys = new EObjectWithInverseResolvingEList<Key>(Key.class, this, SqlPackage.COLUMN__KEYS, SqlPackage.KEY__COLUMN);
		}
		return keys;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Property> getProperties() {
		if (properties == null) {
			properties = new EDataTypeUniqueEList<Property>(Property.class, this, SqlPackage.COLUMN__PROPERTIES);
		}
		return properties;
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
			case SqlPackage.COLUMN__OWNING_TABLE:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetOwningTable((Table)otherEnd, msgs);
			case SqlPackage.COLUMN__KEYS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getKeys()).basicAdd(otherEnd, msgs);
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
			case SqlPackage.COLUMN__OWNING_TABLE:
				return basicSetOwningTable(null, msgs);
			case SqlPackage.COLUMN__KEYS:
				return ((InternalEList<?>)getKeys()).basicRemove(otherEnd, msgs);
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
			case SqlPackage.COLUMN__OWNING_TABLE:
				return eInternalContainer().eInverseRemove(this, SqlPackage.TABLE__OWNED_COLUMNS, Table.class, msgs);
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
			case SqlPackage.COLUMN__OWNING_TABLE:
				return getOwningTable();
			case SqlPackage.COLUMN__TYPE:
				return getType();
			case SqlPackage.COLUMN__KEYS:
				return getKeys();
			case SqlPackage.COLUMN__PROPERTIES:
				return getProperties();
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
			case SqlPackage.COLUMN__OWNING_TABLE:
				setOwningTable((Table)newValue);
				return;
			case SqlPackage.COLUMN__TYPE:
				setType((String)newValue);
				return;
			case SqlPackage.COLUMN__KEYS:
				getKeys().clear();
				getKeys().addAll((Collection<? extends Key>)newValue);
				return;
			case SqlPackage.COLUMN__PROPERTIES:
				getProperties().clear();
				getProperties().addAll((Collection<? extends Property>)newValue);
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
			case SqlPackage.COLUMN__OWNING_TABLE:
				setOwningTable((Table)null);
				return;
			case SqlPackage.COLUMN__TYPE:
				setType(TYPE_EDEFAULT);
				return;
			case SqlPackage.COLUMN__KEYS:
				getKeys().clear();
				return;
			case SqlPackage.COLUMN__PROPERTIES:
				getProperties().clear();
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
			case SqlPackage.COLUMN__OWNING_TABLE:
				return getOwningTable() != null;
			case SqlPackage.COLUMN__TYPE:
				return TYPE_EDEFAULT == null ? type != null : !TYPE_EDEFAULT.equals(type);
			case SqlPackage.COLUMN__KEYS:
				return keys != null && !keys.isEmpty();
			case SqlPackage.COLUMN__PROPERTIES:
				return properties != null && !properties.isEmpty();
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
		result.append(" (type: ");
		result.append(type);
		result.append(", properties: ");
		result.append(properties);
		result.append(')');
		return result.toString();
	}

} //ColumnImpl
