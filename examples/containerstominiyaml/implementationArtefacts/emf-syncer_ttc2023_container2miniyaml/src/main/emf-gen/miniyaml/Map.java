/**
 */
package miniyaml;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Map</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link miniyaml.Map#getEntries <em>Entries</em>}</li>
 * </ul>
 *
 * @see miniyaml.MiniyamlPackage#getMap()
 * @model
 * @generated
 */
public interface Map extends Value {
	/**
	 * Returns the value of the '<em><b>Entries</b></em>' containment reference list.
	 * The list contents are of type {@link miniyaml.MapEntry}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Entries</em>' containment reference list.
	 * @see miniyaml.MiniyamlPackage#getMap_Entries()
	 * @model containment="true" ordered="false"
	 * @generated
	 */
	EList<MapEntry> getEntries();

} // Map
