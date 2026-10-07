/**
 */
package pivot;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Strategy</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see pivot.PivotPackage#getStrategy()
 * @model
 * @generated
 */
public enum Strategy implements Enumerator {
	/**
	 * The '<em><b>BWD</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #BWD_VALUE
	 * @generated
	 * @ordered
	 */
	BWD(0, "BWD", "BWD"),

	/**
	 * The '<em><b>FWD</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #FWD_VALUE
	 * @generated
	 * @ordered
	 */
	FWD(1, "FWD", "FWD"), /**
	 * The '<em><b>BWD FWD</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #BWD_FWD_VALUE
	 * @generated
	 * @ordered
	 */
	BWD_FWD(0, "BWD_FWD", "BWD_FWD");

	/**
	 * The '<em><b>BWD</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #BWD
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int BWD_VALUE = 0;

	/**
	 * The '<em><b>FWD</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #FWD
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int FWD_VALUE = 1;

	/**
	 * The '<em><b>BWD FWD</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #BWD_FWD
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int BWD_FWD_VALUE = 0;

	/**
	 * An array of all the '<em><b>Strategy</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final Strategy[] VALUES_ARRAY =
		new Strategy[] {
			BWD,
			FWD,
			BWD_FWD,
		};

	/**
	 * A public read-only list of all the '<em><b>Strategy</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<Strategy> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Strategy</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param literal the literal.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static Strategy get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			Strategy result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Strategy</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param name the name.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static Strategy getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			Strategy result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Strategy</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the integer value.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static Strategy get(int value) {
		switch (value) {
			case BWD_VALUE: return BWD;
			case FWD_VALUE: return FWD;
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
	private Strategy(int value, String name, String literal) {
		this.value = value;
		this.name = name;
		this.literal = literal;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int getValue() {
	  return value;
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
	
} //Strategy
