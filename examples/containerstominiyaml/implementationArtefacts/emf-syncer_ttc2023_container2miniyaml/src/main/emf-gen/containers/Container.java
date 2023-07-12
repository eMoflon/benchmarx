/**
 */
package containers;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Container</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link containers.Container#getReplicas <em>Replicas</em>}</li>
 *   <li>{@link containers.Container#getImage <em>Image</em>}</li>
 *   <li>{@link containers.Container#getDependsOn <em>Depends On</em>}</li>
 *   <li>{@link containers.Container#getVolumeMounts <em>Volume Mounts</em>}</li>
 * </ul>
 *
 * @see containers.ContainersPackage#getContainer()
 * @model
 * @generated
 */
public interface Container extends Node, NamedElement {
	/**
	 * Returns the value of the '<em><b>Replicas</b></em>' attribute.
	 * The default value is <code>"1"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Replicas</em>' attribute.
	 * @see #setReplicas(int)
	 * @see containers.ContainersPackage#getContainer_Replicas()
	 * @model default="1"
	 * @generated
	 */
	int getReplicas();

	/**
	 * Sets the value of the '{@link containers.Container#getReplicas <em>Replicas</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Replicas</em>' attribute.
	 * @see #getReplicas()
	 * @generated
	 */
	void setReplicas(int value);

	/**
	 * Returns the value of the '<em><b>Image</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Image</em>' reference.
	 * @see #setImage(Image)
	 * @see containers.ContainersPackage#getContainer_Image()
	 * @model
	 * @generated
	 */
	Image getImage();

	/**
	 * Sets the value of the '{@link containers.Container#getImage <em>Image</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Image</em>' reference.
	 * @see #getImage()
	 * @generated
	 */
	void setImage(Image value);

	/**
	 * Returns the value of the '<em><b>Depends On</b></em>' reference list.
	 * The list contents are of type {@link containers.Container}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Depends On</em>' reference list.
	 * @see containers.ContainersPackage#getContainer_DependsOn()
	 * @model
	 * @generated
	 */
	EList<Container> getDependsOn();

	/**
	 * Returns the value of the '<em><b>Volume Mounts</b></em>' containment reference list.
	 * The list contents are of type {@link containers.VolumeMount}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Volume Mounts</em>' containment reference list.
	 * @see containers.ContainersPackage#getContainer_VolumeMounts()
	 * @model containment="true"
	 * @generated
	 */
	EList<VolumeMount> getVolumeMounts();

} // Container
