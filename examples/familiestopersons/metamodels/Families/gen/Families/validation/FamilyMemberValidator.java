/**
 *
 * $Id$
 */
package Families.validation;

import Families.Family;

/**
 * A sample validator interface for {@link Families.FamilyMember}.
 * This doesn't really do anything, and it's not a real EMF artifact.
 * It was generated by the org.eclipse.emf.examples.generator.validator plug-in to illustrate how EMF's code generator can be extended.
 * This can be disabled with -vmargs -Dorg.eclipse.emf.examples.generator.validator=false.
 */
public interface FamilyMemberValidator {
	boolean validate();

	boolean validateName(String value);

	boolean validateFatherInverse(Family value);

	boolean validateMotherInverse(Family value);

	boolean validateSonsInverse(Family value);

	boolean validateDaughtersInverse(Family value);
}
