package containerstominiyaml.comparators;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Set;

import org.junit.Assert;

import miniyaml.List;
import miniyaml.Map;
import miniyaml.MapEntry;
import miniyaml.Scalar;
import miniyaml.Value;

/**
 * MiniYAML model comparator which ignores map entry and list element order.
 */
public class MiniYAMLComparator implements Comparator<miniyaml.Map> {

	@Override
	public void assertEquals(Map expected, Map actual) {
		assertEquals("", expected, actual);
	}

	private void assertEquals(String path, Map expected, Map actual) {
		java.util.Map<String, Value> expectedEntries = getEntries(expected);
		java.util.Map<String, Value> actualEntries = getEntries(actual);
		for (Entry<String, Value> expectedEntry : expectedEntries.entrySet()) {
			Value actualValue = actualEntries.remove(expectedEntry.getKey());
			assertEquals(
				path + (path.length() > 0 ? "." : "") + expectedEntry.getKey(),
				expectedEntry.getValue(), actualValue);
		}
		assertTrue(
			String.format("Actual map at '%s' should have the same keys as the expected map, but it also had %s", path, actualEntries.keySet()),
			actualEntries.isEmpty());
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
		Set<String> expectedValues = getValues(expected);
		Set<String> actualValues = getValues(actual);
		Assert.assertEquals(path + ": values should be the same", expectedValues, actualValues);
	}

	private java.util.Map<String, Value> getEntries(Map map) {
		java.util.Map<String, Value> entries = new HashMap<>();
		for (MapEntry me : map.getEntries()) {
			entries.put(me.getKey(), me.getValue());
		}
		return entries;
	}

	private Set<String> getValues(miniyaml.List list) {
		Set<String> values = new HashSet<>();
		for (Value v : list.getValues()) {
			values.add(((Scalar) v).getValue());
		}
		return values;
	}
}
