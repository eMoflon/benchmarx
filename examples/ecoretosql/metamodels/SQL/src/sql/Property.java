/**
 */
package sql;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Property</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see sql.SqlPackage#getProperty()
 * @model
 * @generated
 */
public enum Property implements Enumerator {
	/**
	 * The '<em><b>Not Null</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #NOT_NULL_VALUE
	 * @generated
	 * @ordered
	 */
	NOT_NULL(0, "NotNull", "NotNull"),

	/**
	 * The '<em><b>Auto Increment</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #AUTO_INCREMENT_VALUE
	 * @generated
	 * @ordered
	 */
	AUTO_INCREMENT(1, "AutoIncrement", "AutoIncrement"),

	/**
	 * The '<em><b>Unique</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #UNIQUE_VALUE
	 * @generated
	 * @ordered
	 */
	UNIQUE(2, "Unique", "Unique");

	/**
	 * The '<em><b>Not Null</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Not Null</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #NOT_NULL
	 * @model name="NotNull"
	 * @generated
	 * @ordered
	 */
	public static final int NOT_NULL_VALUE = 0;

	/**
	 * The '<em><b>Auto Increment</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Auto Increment</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #AUTO_INCREMENT
	 * @model name="AutoIncrement"
	 * @generated
	 * @ordered
	 */
	public static final int AUTO_INCREMENT_VALUE = 1;

	/**
	 * The '<em><b>Unique</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Unique</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #UNIQUE
	 * @model name="Unique"
	 * @generated
	 * @ordered
	 */
	public static final int UNIQUE_VALUE = 2;

	/**
	 * An array of all the '<em><b>Property</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final Property[] VALUES_ARRAY =
		new Property[] {
			NOT_NULL,
			AUTO_INCREMENT,
			UNIQUE,
		};

	/**
	 * A public read-only list of all the '<em><b>Property</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<Property> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Property</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param literal the literal.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static Property get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			Property result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Property</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param name the name.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static Property getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			Property result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Property</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the integer value.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static Property get(int value) {
		switch (value) {
			case NOT_NULL_VALUE: return NOT_NULL;
			case AUTO_INCREMENT_VALUE: return AUTO_INCREMENT;
			case UNIQUE_VALUE: return UNIQUE;
		}
		return null;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final int value;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final String name;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final String literal;

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private Property(int value, String name, String literal) {
		this.value = value;
		this.name = name;
		this.literal = literal;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getValue() {
	  return value;
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
	public String getLiteral() {
	  return literal;
	}

	/**
	 * Returns the literal value of the enumerator, which is its string representation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		return literal;
	}
	
} //Property
