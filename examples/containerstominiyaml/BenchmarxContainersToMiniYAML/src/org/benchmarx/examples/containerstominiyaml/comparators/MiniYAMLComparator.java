package org.benchmarx.examples.containerstominiyaml.comparators;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Set;

import org.benchmarx.emf.Comparator;
import org.junit.Assert;

import miniyaml.List;
import miniyaml.Map;
import miniyaml.MapEntry;
import miniyaml.Scalar;
import miniyaml.Value;

public class MiniYAMLComparator implements Comparator<miniyaml.Map> {

	@Override
	public void assertEquals(Map expected, Map actual) {
		java.util.Map<String, Value> expectedEntries = getEntries(expected);
		java.util.Map<String, Value> actualEntries = getEntries(actual);
		for (Entry<String, Value> expectedEntry : expectedEntries.entrySet()) {
			Value actualValue = actualEntries.remove(expectedEntry.getKey());
			assertEquals("Value for " + expectedEntry.getKey() + " should be the same", expectedEntry.getValue(), actualValue);
		}
		assertTrue("Actual map should have the same keys as the expected map, but it also had " + actualEntries.keySet(), actualEntries.isEmpty());
	}

	private void assertEquals(String desc, Value expected, Value actual) {
		if (expected == null) {
			assertNull(desc + ": should not have a value", actual);
		} else {
			assertNotNull(desc + ": should have a value", actual);

			Assert.assertEquals(desc + ": should be of the same type",
				expected.getClass().getCanonicalName(), actual.getClass().getCanonicalName());

			if (expected instanceof Map) {
				assertEquals((Map) expected, (Map) actual);
			} else if (expected instanceof miniyaml.List) {
				assertEquals((miniyaml.List) expected, (miniyaml.List) actual);
			} else if (expected instanceof miniyaml.Scalar) {
				assertEquals((miniyaml.Scalar) expected, (miniyaml.Scalar) actual);  
			} else {
				fail("Unexpected type " + expected.getClass().getCanonicalName());
			}
		}
	}

	private void assertEquals(Scalar expected, Scalar actual) {
		Assert.assertEquals("Scalar values should be the same", expected.getValue(), actual.getValue());
	}

	private void assertEquals(List expected, List actual) {
		Set<String> expectedValues = getValues(expected);
		Set<String> actualValues = getValues(actual);
		Assert.assertEquals(expectedValues, actualValues);
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
