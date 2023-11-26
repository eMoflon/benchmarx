/**
 */
package containers.impl;

import containers.ContainersPackage;
import containers.Image;
import containers.NamedElement;
import containers.VolumeMount;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Container</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link containers.impl.ContainerImpl#getName <em>Name</em>}</li>
 *   <li>{@link containers.impl.ContainerImpl#getReplicas <em>Replicas</em>}</li>
 *   <li>{@link containers.impl.ContainerImpl#getImage <em>Image</em>}</li>
 *   <li>{@link containers.impl.ContainerImpl#getDependsOn <em>Depends On</em>}</li>
 *   <li>{@link containers.impl.ContainerImpl#getVolumeMounts <em>Volume Mounts</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ContainerImpl extends NodeImpl implements containers.Container {
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
	 * The default value of the '{@link #getReplicas() <em>Replicas</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReplicas()
	 * @generated
	 * @ordered
	 */
	protected static final int REPLICAS_EDEFAULT = 1;

	/**
	 * The cached value of the '{@link #getReplicas() <em>Replicas</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReplicas()
	 * @generated
	 * @ordered
	 */
	protected int replicas = REPLICAS_EDEFAULT;

	/**
	 * The cached value of the '{@link #getImage() <em>Image</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getImage()
	 * @generated
	 * @ordered
	 */
	protected Image image;

	/**
	 * The cached value of the '{@link #getDependsOn() <em>Depends On</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDependsOn()
	 * @generated
	 * @ordered
	 */
	protected EList<containers.Container> dependsOn;

	/**
	 * The cached value of the '{@link #getVolumeMounts() <em>Volume Mounts</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVolumeMounts()
	 * @generated
	 * @ordered
	 */
	protected EList<VolumeMount> volumeMounts;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ContainerImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ContainersPackage.Literals.CONTAINER;
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
			eNotify(new ENotificationImpl(this, Notification.SET, ContainersPackage.CONTAINER__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getReplicas() {
		return replicas;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setReplicas(int newReplicas) {
		int oldReplicas = replicas;
		replicas = newReplicas;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ContainersPackage.CONTAINER__REPLICAS, oldReplicas, replicas));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Image getImage() {
		if (image != null && image.eIsProxy()) {
			InternalEObject oldImage = (InternalEObject)image;
			image = (Image)eResolveProxy(oldImage);
			if (image != oldImage) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ContainersPackage.CONTAINER__IMAGE, oldImage, image));
			}
		}
		return image;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Image basicGetImage() {
		return image;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setImage(Image newImage) {
		Image oldImage = image;
		image = newImage;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ContainersPackage.CONTAINER__IMAGE, oldImage, image));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<containers.Container> getDependsOn() {
		if (dependsOn == null) {
			dependsOn = new EObjectResolvingEList<containers.Container>(containers.Container.class, this, ContainersPackage.CONTAINER__DEPENDS_ON);
		}
		return dependsOn;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<VolumeMount> getVolumeMounts() {
		if (volumeMounts == null) {
			volumeMounts = new EObjectContainmentEList<VolumeMount>(VolumeMount.class, this, ContainersPackage.CONTAINER__VOLUME_MOUNTS);
		}
		return volumeMounts;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ContainersPackage.CONTAINER__VOLUME_MOUNTS:
				return ((InternalEList<?>)getVolumeMounts()).basicRemove(otherEnd, msgs);
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
			case ContainersPackage.CONTAINER__NAME:
				return getName();
			case ContainersPackage.CONTAINER__REPLICAS:
				return getReplicas();
			case ContainersPackage.CONTAINER__IMAGE:
				if (resolve) return getImage();
				return basicGetImage();
			case ContainersPackage.CONTAINER__DEPENDS_ON:
				return getDependsOn();
			case ContainersPackage.CONTAINER__VOLUME_MOUNTS:
				return getVolumeMounts();
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
			case ContainersPackage.CONTAINER__NAME:
				setName((String)newValue);
				return;
			case ContainersPackage.CONTAINER__REPLICAS:
				setReplicas((Integer)newValue);
				return;
			case ContainersPackage.CONTAINER__IMAGE:
				setImage((Image)newValue);
				return;
			case ContainersPackage.CONTAINER__DEPENDS_ON:
				getDependsOn().clear();
				getDependsOn().addAll((Collection<? extends containers.Container>)newValue);
				return;
			case ContainersPackage.CONTAINER__VOLUME_MOUNTS:
				getVolumeMounts().clear();
				getVolumeMounts().addAll((Collection<? extends VolumeMount>)newValue);
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
			case ContainersPackage.CONTAINER__NAME:
				setName(NAME_EDEFAULT);
				return;
			case ContainersPackage.CONTAINER__REPLICAS:
				setReplicas(REPLICAS_EDEFAULT);
				return;
			case ContainersPackage.CONTAINER__IMAGE:
				setImage((Image)null);
				return;
			case ContainersPackage.CONTAINER__DEPENDS_ON:
				getDependsOn().clear();
				return;
			case ContainersPackage.CONTAINER__VOLUME_MOUNTS:
				getVolumeMounts().clear();
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
			case ContainersPackage.CONTAINER__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case ContainersPackage.CONTAINER__REPLICAS:
				return replicas != REPLICAS_EDEFAULT;
			case ContainersPackage.CONTAINER__IMAGE:
				return image != null;
			case ContainersPackage.CONTAINER__DEPENDS_ON:
				return dependsOn != null && !dependsOn.isEmpty();
			case ContainersPackage.CONTAINER__VOLUME_MOUNTS:
				return volumeMounts != null && !volumeMounts.isEmpty();
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
				case ContainersPackage.CONTAINER__NAME: return ContainersPackage.NAMED_ELEMENT__NAME;
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
				case ContainersPackage.NAMED_ELEMENT__NAME: return ContainersPackage.CONTAINER__NAME;
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

		StringBuilder result = new StringBuilder(super.toString());
		result.append(" (name: ");
		result.append(name);
		result.append(", replicas: ");
		result.append(replicas);
		result.append(')');
		return result.toString();
	}

} //ContainerImpl
