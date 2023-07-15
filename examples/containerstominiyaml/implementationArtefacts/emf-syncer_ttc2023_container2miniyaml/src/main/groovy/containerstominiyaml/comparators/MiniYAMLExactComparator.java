package containerstominiyaml.comparators;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Iterator;

import org.junit.Assert;

import miniyaml.List;
import miniyaml.Map;
import miniyaml.MapEntry;
import miniyaml.Scalar;
import miniyaml.Value;

/**
 * MiniYAML model comparator which checks for exact order across map entries and list elements.
 */
public class MiniYAMLExactComparator implements Comparator<miniyaml.Map> {

	@Override
	public void assertEquals(Map expected, Map actual) {
		assertEquals("", expected, actual);
	}

	private void assertEquals(String path, Map expected, Map actual) {
		Iterator<MapEntry> itExpected = expected.getEntries().iterator();
		Iterator<MapEntry> itActual = actual.getEntries().iterator();

		int i = 0;
		while (itExpected.hasNext() && itActual.hasNext()) {
			MapEntry expectedEntry = itExpected.next();
			MapEntry actualEntry = itActual.next();
			Assert.assertEquals(
				String.format("Keys in position %d should be equal", i++),
				expectedEntry.getKey(), actualEntry.getKey());
			assertEquals(
					path + (path.length() > 0 ? "." : "") + expectedEntry.getKey(),
					expectedEntry.getValue(), actualEntry.getValue());
		}

		assertTrue(
			String.format("Actual map at '%s' should have the same keys as the expected map", path),
			!itExpected.hasNext() && !itActual.hasNext());
	}

	private void assertEquals(String path, Value expected, Value actual) {
		if (expected == null) {
			assertNull(path + ": should not have a value", actual);
		} else {
			assertNotNull(path + ": should have a value", actual);

			Assert.assertEquals(path + ": should be of the same type",
				expected.getClass().getCanonicalName(), actual.getClass().getCanonicalName());

			if (expected instanceof Map) {
				assertEquals(path, (Map) expected, (Map) actual);
			} else if (expected instanceof miniyaml.List) {
				assertEquals(path, (miniyaml.List) expected, (miniyaml.List) actual);
			} else if (expected instanceof miniyaml.Scalar) {
				assertEquals(path, (miniyaml.Scalar) expected, (miniyaml.Scalar) actual);  
			} else {
				fail("Unexpected type " + expected.getClass().getCanonicalName());
			}
		}
	}

	private void assertEquals(String path, Scalar expected, Scalar actual) {
		Assert.assertEquals(path + ": value should be the same", expected.getValue(), actual.getValue());
	}

	private void assertEquals(String path, List expected, List actual) {
		Iterator<Value> itExpected = expected.getValues().iterator();
		Iterator<Value> itActual = actual.getValues().iterator();

		int i = 0;
		while (itExpected.hasNext() && itActual.hasNext()) {
			Value expectedValue = itExpected.next();
			Value actualValue = itActual.next();
			assertEquals(path + i++, expectedValue, actualValue);
		}
	}

}
