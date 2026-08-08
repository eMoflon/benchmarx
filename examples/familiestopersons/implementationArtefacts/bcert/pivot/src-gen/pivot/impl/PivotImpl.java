/**
 */
package pivot.impl;

import Families.FamilyRegister;

import Persons.PersonRegister;

import java.util.Collection;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import pivot.Mapping;
import pivot.Pivot;
import pivot.PivotPackage;
import pivot.PostCondition;
import pivot.PreCondition;
import pivot.Strategy;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Pivot</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link pivot.impl.PivotImpl#getFamilyModel <em>Family Model</em>}</li>
 *   <li>{@link pivot.impl.PivotImpl#getPersonModel <em>Person Model</em>}</li>
 *   <li>{@link pivot.impl.PivotImpl#getPre <em>Pre</em>}</li>
 *   <li>{@link pivot.impl.PivotImpl#getPost <em>Post</em>}</li>
 *   <li>{@link pivot.impl.PivotImpl#getMapping <em>Mapping</em>}</li>
 *   <li>{@link pivot.impl.PivotImpl#getName <em>Name</em>}</li>
 *   <li>{@link pivot.impl.PivotImpl#getStrategie <em>Strategie</em>}</li>
 *   <li>{@link pivot.impl.PivotImpl#isFAMILY_TO_NEW <em>FAMILY TO NEW</em>}</li>
 *   <li>{@link pivot.impl.PivotImpl#isPARENT_TO_CHILD <em>PARENT TO CHILD</em>}</li>
 *   <li>{@link pivot.impl.PivotImpl#isSync <em>Sync</em>}</li>
 * </ul>
 *
 * @generated
 */
public class PivotImpl extends EObjectImpl implements Pivot {
	/**
	 * The cached value of the '{@link #getFamilyModel() <em>Family Model</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFamilyModel()
	 * @generated
	 * @ordered
	 */
	protected FamilyRegister familyModel;

	/**
	 * The cached value of the '{@link #getPersonModel() <em>Person Model</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPersonModel()
	 * @generated
	 * @ordered
	 */
	protected PersonRegister personModel;

	/**
	 * The cached value of the '{@link #getPre() <em>Pre</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPre()
	 * @generated
	 * @ordered
	 */
	protected EList<PreCondition> pre;

	/**
	 * The cached value of the '{@link #getPost() <em>Post</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPost()
	 * @generated
	 * @ordered
	 */
	protected EList<PostCondition> post;

	/**
	 * The cached value of the '{@link #getMapping() <em>Mapping</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMapping()
	 * @generated
	 * @ordered
	 */
	protected EList<Mapping> mapping;

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
	 * The default value of the '{@link #getStrategie() <em>Strategie</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStrategie()
	 * @generated
	 * @ordered
	 */
	protected static final Strategy STRATEGIE_EDEFAULT = Strategy.BWD;

	/**
	 * The cached value of the '{@link #getStrategie() <em>Strategie</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStrategie()
	 * @generated
	 * @ordered
	 */
	protected Strategy strategie = STRATEGIE_EDEFAULT;

	/**
	 * The default value of the '{@link #isFAMILY_TO_NEW() <em>FAMILY TO NEW</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isFAMILY_TO_NEW()
	 * @generated
	 * @ordered
	 */
	protected static final boolean FAMILY_TO_NEW_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isFAMILY_TO_NEW() <em>FAMILY TO NEW</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isFAMILY_TO_NEW()
	 * @generated
	 * @ordered
	 */
	protected boolean familY_TO_NEW = FAMILY_TO_NEW_EDEFAULT;

	/**
	 * The default value of the '{@link #isPARENT_TO_CHILD() <em>PARENT TO CHILD</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isPARENT_TO_CHILD()
	 * @generated
	 * @ordered
	 */
	protected static final boolean PARENT_TO_CHILD_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isPARENT_TO_CHILD() <em>PARENT TO CHILD</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isPARENT_TO_CHILD()
	 * @generated
	 * @ordered
	 */
	protected boolean parenT_TO_CHILD = PARENT_TO_CHILD_EDEFAULT;

	/**
	 * The default value of the '{@link #isSync() <em>Sync</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSync()
	 * @generated
	 * @ordered
	 */
	protected static final boolean SYNC_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isSync() <em>Sync</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSync()
	 * @generated
	 * @ordered
	 */
	protected boolean sync = SYNC_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PivotImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return PivotPackage.Literals.PIVOT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public FamilyRegister getFamilyModel() {
		if (familyModel != null && familyModel.eIsProxy()) {
			InternalEObject oldFamilyModel = (InternalEObject)familyModel;
			familyModel = (FamilyRegister)eResolveProxy(oldFamilyModel);
			if (familyModel != oldFamilyModel) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, PivotPackage.PIVOT__FAMILY_MODEL, oldFamilyModel, familyModel));
			}
		}
		return familyModel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FamilyRegister basicGetFamilyModel() {
		return familyModel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setFamilyModel(FamilyRegister newFamilyModel) {
		FamilyRegister oldFamilyModel = familyModel;
		familyModel = newFamilyModel;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PivotPackage.PIVOT__FAMILY_MODEL, oldFamilyModel, familyModel));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public PersonRegister getPersonModel() {
		if (personModel != null && personModel.eIsProxy()) {
			InternalEObject oldPersonModel = (InternalEObject)personModel;
			personModel = (PersonRegister)eResolveProxy(oldPersonModel);
			if (personModel != oldPersonModel) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, PivotPackage.PIVOT__PERSON_MODEL, oldPersonModel, personModel));
			}
		}
		return personModel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PersonRegister basicGetPersonModel() {
		return personModel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setPersonModel(PersonRegister newPersonModel) {
		PersonRegister oldPersonModel = personModel;
		personModel = newPersonModel;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PivotPackage.PIVOT__PERSON_MODEL, oldPersonModel, personModel));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<PreCondition> getPre() {
		if (pre == null) {
			pre = new EObjectContainmentEList<PreCondition>(PreCondition.class, this, PivotPackage.PIVOT__PRE);
		}
		return pre;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<PostCondition> getPost() {
		if (post == null) {
			post = new EObjectContainmentEList<PostCondition>(PostCondition.class, this, PivotPackage.PIVOT__POST);
		}
		return post;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<Mapping> getMapping() {
		if (mapping == null) {
			mapping = new EObjectContainmentEList<Mapping>(Mapping.class, this, PivotPackage.PIVOT__MAPPING);
		}
		return mapping;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PivotPackage.PIVOT__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Strategy getStrategie() {
		return strategie;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setStrategie(Strategy newStrategie) {
		Strategy oldStrategie = strategie;
		strategie = newStrategie == null ? STRATEGIE_EDEFAULT : newStrategie;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PivotPackage.PIVOT__STRATEGIE, oldStrategie, strategie));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isFAMILY_TO_NEW() {
		return familY_TO_NEW;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setFAMILY_TO_NEW(boolean newFAMILY_TO_NEW) {
		boolean oldFAMILY_TO_NEW = familY_TO_NEW;
		familY_TO_NEW = newFAMILY_TO_NEW;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PivotPackage.PIVOT__FAMILY_TO_NEW, oldFAMILY_TO_NEW, familY_TO_NEW));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isPARENT_TO_CHILD() {
		return parenT_TO_CHILD;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setPARENT_TO_CHILD(boolean newPARENT_TO_CHILD) {
		boolean oldPARENT_TO_CHILD = parenT_TO_CHILD;
		parenT_TO_CHILD = newPARENT_TO_CHILD;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PivotPackage.PIVOT__PARENT_TO_CHILD, oldPARENT_TO_CHILD, parenT_TO_CHILD));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isSync() {
		return sync;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setSync(boolean newSync) {
		boolean oldSync = sync;
		sync = newSync;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PivotPackage.PIVOT__SYNC, oldSync, sync));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case PivotPackage.PIVOT__PRE:
				return ((InternalEList<?>)getPre()).basicRemove(otherEnd, msgs);
			case PivotPackage.PIVOT__POST:
				return ((InternalEList<?>)getPost()).basicRemove(otherEnd, msgs);
			case PivotPackage.PIVOT__MAPPING:
				return ((InternalEList<?>)getMapping()).basicRemove(otherEnd, msgs);
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
			case PivotPackage.PIVOT__FAMILY_MODEL:
				if (resolve) return getFamilyModel();
				return basicGetFamilyModel();
			case PivotPackage.PIVOT__PERSON_MODEL:
				if (resolve) return getPersonModel();
				return basicGetPersonModel();
			case PivotPackage.PIVOT__PRE:
				return getPre();
			case PivotPackage.PIVOT__POST:
				return getPost();
			case PivotPackage.PIVOT__MAPPING:
				return getMapping();
			case PivotPackage.PIVOT__NAME:
				return getName();
			case PivotPackage.PIVOT__STRATEGIE:
				return getStrategie();
			case PivotPackage.PIVOT__FAMILY_TO_NEW:
				return isFAMILY_TO_NEW();
			case PivotPackage.PIVOT__PARENT_TO_CHILD:
				return isPARENT_TO_CHILD();
			case PivotPackage.PIVOT__SYNC:
				return isSync();
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
			case PivotPackage.PIVOT__FAMILY_MODEL:
				setFamilyModel((FamilyRegister)newValue);
				return;
			case PivotPackage.PIVOT__PERSON_MODEL:
				setPersonModel((PersonRegister)newValue);
				return;
			case PivotPackage.PIVOT__PRE:
				getPre().clear();
				getPre().addAll((Collection<? extends PreCondition>)newValue);
				return;
			case PivotPackage.PIVOT__POST:
				getPost().clear();
				getPost().addAll((Collection<? extends PostCondition>)newValue);
				return;
			case PivotPackage.PIVOT__MAPPING:
				getMapping().clear();
				getMapping().addAll((Collection<? extends Mapping>)newValue);
				return;
			case PivotPackage.PIVOT__NAME:
				setName((String)newValue);
				return;
			case PivotPackage.PIVOT__STRATEGIE:
				setStrategie((Strategy)newValue);
				return;
			case PivotPackage.PIVOT__FAMILY_TO_NEW:
				setFAMILY_TO_NEW((Boolean)newValue);
				return;
			case PivotPackage.PIVOT__PARENT_TO_CHILD:
				setPARENT_TO_CHILD((Boolean)newValue);
				return;
			case PivotPackage.PIVOT__SYNC:
				setSync((Boolean)newValue);
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
			case PivotPackage.PIVOT__FAMILY_MODEL:
				setFamilyModel((FamilyRegister)null);
				return;
			case PivotPackage.PIVOT__PERSON_MODEL:
				setPersonModel((PersonRegister)null);
				return;
			case PivotPackage.PIVOT__PRE:
				getPre().clear();
				return;
			case PivotPackage.PIVOT__POST:
				getPost().clear();
				return;
			case PivotPackage.PIVOT__MAPPING:
				getMapping().clear();
				return;
			case PivotPackage.PIVOT__NAME:
				setName(NAME_EDEFAULT);
				return;
			case PivotPackage.PIVOT__STRATEGIE:
				setStrategie(STRATEGIE_EDEFAULT);
				return;
			case PivotPackage.PIVOT__FAMILY_TO_NEW:
				setFAMILY_TO_NEW(FAMILY_TO_NEW_EDEFAULT);
				return;
			case PivotPackage.PIVOT__PARENT_TO_CHILD:
				setPARENT_TO_CHILD(PARENT_TO_CHILD_EDEFAULT);
				return;
			case PivotPackage.PIVOT__SYNC:
				setSync(SYNC_EDEFAULT);
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
			case PivotPackage.PIVOT__FAMILY_MODEL:
				return familyModel != null;
			case PivotPackage.PIVOT__PERSON_MODEL:
				return personModel != null;
			case PivotPackage.PIVOT__PRE:
				return pre != null && !pre.isEmpty();
			case PivotPackage.PIVOT__POST:
				return post != null && !post.isEmpty();
			case PivotPackage.PIVOT__MAPPING:
				return mapping != null && !mapping.isEmpty();
			case PivotPackage.PIVOT__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case PivotPackage.PIVOT__STRATEGIE:
				return strategie != STRATEGIE_EDEFAULT;
			case PivotPackage.PIVOT__FAMILY_TO_NEW:
				return familY_TO_NEW != FAMILY_TO_NEW_EDEFAULT;
			case PivotPackage.PIVOT__PARENT_TO_CHILD:
				return parenT_TO_CHILD != PARENT_TO_CHILD_EDEFAULT;
			case PivotPackage.PIVOT__SYNC:
				return sync != SYNC_EDEFAULT;
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
		result.append(" (name: ");
		result.append(name);
		result.append(", strategie: ");
		result.append(strategie);
		result.append(", FAMILY_TO_NEW: ");
		result.append(familY_TO_NEW);
		result.append(", PARENT_TO_CHILD: ");
		result.append(parenT_TO_CHILD);
		result.append(", sync: ");
		result.append(sync);
		result.append(')');
		return result.toString();
	}

} //PivotImpl
