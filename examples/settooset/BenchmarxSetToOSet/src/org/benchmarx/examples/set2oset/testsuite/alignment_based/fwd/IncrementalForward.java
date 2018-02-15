package org.benchmarx.examples.set2oset.testsuite.alignment_based.fwd;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.benchmarx.BXTool;
import org.benchmarx.examples.set2oset.testsuite.Decisions;
import org.benchmarx.examples.set2oset.testsuite.Set2OsetTestCase;
import org.benchmarx.osets.core.OsetHelper;
import org.benchmarx.sets.core.SetHelper;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.junit.Before;
import org.junit.Test;

import org.benchmarx.osets.core.OsetComparator;
import osets.MyOrderedSet;

public class IncrementalForward extends Set2OsetTestCase {
	public IncrementalForward(BXTool<sets.MySet, osets.MyOrderedSet, Decisions> tool) {
		super(tool);
	}
	
	@Before
	public void resetTargetHistory() {
		clearDelta();
		lastAssertedTarget = osets.OsetsFactory.eINSTANCE.createMyOrderedSet();
	}
	
	/**
	 * <b>Test</b> for inserting elements into an existing set, after the order in the corresponding oset has been
	 * changed. <br/>
	 * <b>Expect</b> : New elements are added to the oset, while the old elements
	 * remain unchanged and keep their order. <br/>
	 * <b>Features</b>: fwd, add, fixed
	 */
	@Test
	public void testIncrementalInserts() {
		tool.performAndPropagateSourceEdit(util
				.execute(helperSet::setSetName)
				.andThen(helperSet::createA)
				.andThen(helperSet::createC));
		appendSourceDelta();
		assertPrecondition("acSet", generatePossibleTargets().values());
		//------------
		tool.performAndPropagateSourceEdit(helperSet::createB);
		appendSourceDelta();
		//------------	
		assertPostcondition("FirstThreeLettersSet", generatePossibleTargets().values());
		
		tool.performIdleTargetEdit(helperOset::invert);
		appendTargetDelta();
		tool.performAndPropagateSourceEdit(helperSet::createD);
		appendSourceDelta();
		assertPostcondition("abcdSet", generatePossibleTargets().values());
	}
	
	/**
	 * <b>Test</b> for deleting elements. The elements with value B and D are deleted from a set containing A, B, C
	 * and D, after the order in the corresponding oset has been changed.
	 * <b>Expect</b>: Deletion of B and D in the oset, while the old elements
	 * remain unchanged and keep their order. <br/>
	 * <b>Features</b>: fwd, del, corr-based, structural
	 */
	@Test
	public void testIncrementalDeletions() {
		tool.performAndPropagateSourceEdit(util
				.execute(helperSet::setSetName)
				.andThen(helperSet::createA)
				.andThen(helperSet::createB)
				.andThen(helperSet::createC)
				.andThen(helperSet::createD));
		appendSourceDelta();
		assertPrecondition("abcdSet", generatePossibleTargets().values());
		//------------
		tool.performAndPropagateSourceEdit(helperSet::deleteD);
		appendSourceDelta();
		//------------	
		assertPostcondition("FirstThreeLettersSet", generatePossibleTargets().values());

		tool.performIdleTargetEdit(helperOset::invert);
		appendTargetDelta();
		tool.performAndPropagateSourceEdit(helperSet::deleteB);
		appendSourceDelta();
		assertPostcondition("acSet", generatePossibleTargets().values());
	}
	
	/**
	 * <b>Test</b> for renaming the elements A, B, C to Z, X, Y and back, after the order in the corresponding oset has
	 * been changed.
	 * <b>Expect</b>: Change occurs also in the oset, while the elements keep their order.
	 * <b>Features</b>: fwd, attribute, fixed, structural, corr-based
	 */
	@Test
	public void testIncrementalValueChange() {
		tool.performAndPropagateSourceEdit(util
				.execute(helperSet::setSetName)
				.andThen(helperSet::createA)
				.andThen(helperSet::createB)
				.andThen(helperSet::createC));
		appendSourceDelta();
		assertPrecondition("FirstThreeLettersSet", generatePossibleTargets().values());
		//------------
		tool.performAndPropagateSourceEdit(helperSet::changeABCtoZXY);
		appendSourceDelta();
		//------------	
		assertPostcondition("ZxySet", generatePossibleTargets().values());

		tool.performIdleTargetEdit(helperOset::invert);
		appendTargetDelta();
		tool.performAndPropagateSourceEdit(helperSet::changeZXYtoABC);
		appendSourceDelta();
		assertPostcondition("FirstThreeLettersSet", generatePossibleTargets().values());
	}

	/**
	 * <b>Test</b> for stability of the transformation.<br/>
	 * <b>Expect</b> re-running the transformation after an idle source delta does not change the target model.<br/>
	 * <b>Features:</b>: fwd, fixed
	 */
	@Test
	public void testStability() {		
		tool.performAndPropagateSourceEdit(util
				.execute(helperSet::setSetName)
				.andThen(helperSet::createA)
				.andThen(helperSet::createB)
				.andThen(helperSet::createC));
		tool.performIdleTargetEdit(helperOset::changeIncrementalID);
		
		util.assertPrecondition("FirstThreeLettersSet", "FirstThreeLettersChangedOset");
		//------------
		tool.performAndPropagateSourceEdit(helperSet::idleDelta);
		//------------
		util.assertPostcondition("FirstThreeLettersSet", "FirstThreeLettersChangedOset");
	}
	
	/**
	 * <b>Test</b> for hippocraticness of the transformation.<br/>
	 * <b>Expect</b> re-running the transformation after changing the incrementalID does not change the oset.<br/>
	 * <b>Features:</b>: fwd, fixed
	 */
	@Test
	public void testHippocraticness() {
		tool.performAndPropagateSourceEdit(util
				.execute(helperSet::setSetName)
				.andThen(helperSet::createA)
				.andThen(helperSet::createB)
				.andThen(helperSet::createC));
		tool.performIdleTargetEdit(helperOset::changeIncrementalID);
		
		util.assertPrecondition("FirstThreeLettersSet", "FirstThreeLettersChangedOset");
		//------------
		tool.performAndPropagateSourceEdit(helperSet::changeIncrementalID);
		//------------
		util.assertPostcondition("FirstThreeLettersChangedSet", "FirstThreeLettersChangedOset");
	}
	
	/**
	 * The behavior of this function is undefined for invalid input parameters.
	 */
	static Map<String, osets.MyOrderedSet> generatePossibleTargets(
			osets.MyOrderedSet targetBefore, List<Object> sourceDeltas) {
		LinkedHashMap<String, osets.MyOrderedSet> result = new LinkedHashMap<>();
		result.put(OsetComparator.myOrderedSetToString(targetBefore), EcoreUtil.copy(targetBefore));
		
		for (Object delta : sourceDeltas) {
			LinkedHashMap<String, osets.MyOrderedSet> resultDelta = new LinkedHashMap<>();
			
			if (delta instanceof SetHelper.Delta.ElementCreation) {				
				for (osets.MyOrderedSet target : result.values()) {
					if (target.getElements().isEmpty()) {
						osets.Element createdElement = osets.OsetsFactory.eINSTANCE.createElement();
						createdElement.setValue(((SetHelper.Delta.ElementCreation) delta).value);
						createdElement.setOrderedSet(target);
						resultDelta.put(OsetComparator.myOrderedSetToString(target), target);
						break;
					}
					
					osets.Element first = target.getElements().get(0);
					while (first.getPrevious() != null) {
						first = first.getPrevious();
					}
					
					osets.MyOrderedSet createdOset = EcoreUtil.copy(target);
					for (osets.Element element : createdOset.getElements()) {
						if (element.getValue().equals(first.getValue())) {
							osets.Element createdElement = osets.OsetsFactory.eINSTANCE.createElement();
							createdElement.setValue(((SetHelper.Delta.ElementCreation) delta).value);
							createdElement.setNext(element);		
							createdElement.setOrderedSet(createdOset);
							break;
						}
					}
					resultDelta.put(OsetComparator.myOrderedSetToString(createdOset), createdOset);
					
					for (osets.Element current = first; current != null; current =  current.getNext()) {
						createdOset = EcoreUtil.copy(target);
						for (osets.Element element : createdOset.getElements()) {
							if (element.getValue().equals(current.getValue())) {
								osets.Element createdElement = osets.OsetsFactory.eINSTANCE.createElement();
								createdElement.setValue(((SetHelper.Delta.ElementCreation) delta).value);
								createdElement.setNext(element.getNext());
								createdElement.setPrevious(element);			
								createdElement.setOrderedSet(createdOset);
								break;
							}
						}
						resultDelta.put(OsetComparator.myOrderedSetToString(createdOset), createdOset);
					}
				}
				
			} else if (delta instanceof SetHelper.Delta.ElementDeletion) {
				for (osets.MyOrderedSet target : result.values()) {
					for (osets.Element element : target.getElements()) {
						if (element.getValue().equals(((SetHelper.Delta.ElementDeletion) delta).value)) {
							if (element.getPrevious() == null && element.getNext() == null) {
							} else if (element.getPrevious() == null) {
								element.setNext(null);
							} else if (element.getNext() == null) {
								element.setPrevious(null);
							} else {
								element.getPrevious().setNext(element.getNext());
							}
							element.setOrderedSet(null);
							resultDelta.put(OsetComparator.myOrderedSetToString(target), target);
							break;
						}
					}
				}
				
			} else if (delta instanceof SetHelper.Delta.ElementChange) {
				for (osets.MyOrderedSet target : result.values()) {
					for (osets.Element element : target.getElements()) {
						if (element.getValue().equals(((SetHelper.Delta.ElementChange) delta).oldValue)) {
							element.setValue(((SetHelper.Delta.ElementChange) delta).newValue);
							resultDelta.put(OsetComparator.myOrderedSetToString(target), target);
							break;
						}
					}
				}
				
			} else if (delta instanceof SetHelper.Delta.SetNameChange) {
				for (osets.MyOrderedSet target : result.values()) {
					target.setName(((SetHelper.Delta.SetNameChange) delta).newName);
					resultDelta.put(OsetComparator.myOrderedSetToString(target), target);
				}
				
			} else if (delta instanceof OsetHelper.Delta.OsetElementsInversion) {
				for (osets.MyOrderedSet target : result.values()) {
					(new OsetHelper()).invert(target);
					resultDelta.put(OsetComparator.myOrderedSetToString(target), target);
				}
				
			} else {
				throw new IllegalArgumentException("Unsupported delta, can't create target!");
			}
			
			result = resultDelta;
		}
		
		return result;
	}
	
	private List<Object> delta = new ArrayList<>();
	private osets.MyOrderedSet lastAssertedTarget;
	
	private List<Object> getDelta() {
		return delta;
	}
	private void clearDelta() {
		helperSet.clearDelta();
		helperOset.clearDelta();
		delta.clear();
	}
	private void appendSourceDelta() {
		delta.addAll(helperSet.getDelta());
		helperSet.clearDelta();
	}
	private void appendTargetDelta() {
		delta.addAll(helperOset.getDelta());
		helperOset.clearDelta();
	}
	
	private Map<String, osets.MyOrderedSet> generatePossibleTargets() {
		return generatePossibleTargets(lastAssertedTarget, getDelta());
	}
	
	private void assertPrecondition(String srcPath, Collection<MyOrderedSet> possibleTargets) {
		assertCondition(srcPath, possibleTargets, true);
	}
	private void assertPostcondition(String srcPath, Collection<MyOrderedSet> possibleTargets) {
		assertCondition(srcPath, possibleTargets, false);
	}	
	private void assertCondition(String srcPath, Collection<MyOrderedSet> possibleTargets, boolean pre) {
		if (!srcPath.endsWith("Set")) {
			throw new IllegalArgumentException(
					"The source model isn't corresponding to the naming convention (ending with Set)");
		}
		if (possibleTargets.isEmpty()) {
			throw new IllegalArgumentException("At least one possible target is necessary to assert a condition.");
		}
		
		String trgPath = srcPath.substring(0, srcPath.length() - 3) + "CandidateOset";
		for (MyOrderedSet target : possibleTargets) {
			ResourceSet set = new ResourceSetImpl();
			set.getResourceFactoryRegistry().getExtensionToFactoryMap().put(
					Resource.Factory.Registry.DEFAULT_EXTENSION, new XMIResourceFactoryImpl());
			URI uri = URI.createFileURI("resources/" + trgPath + ".xmi");
			Resource res = set.createResource(uri);
			res.getContents().add(EcoreUtil.copy(target));
			try {
				res.save(null);
			} catch (IOException e1) {
				throw new AssertionError("Saving oset candidate failed.");
			}	
			try {
				if (pre) {
					util.assertPrecondition(srcPath, trgPath);
				} else {
					util.assertPostcondition(srcPath, trgPath);
				}
				lastAssertedTarget = target;
				delta.clear();
				return; // condition satisfied
			} catch (AssertionError e) {
				// try next possible target
			}
		}
		if (pre) {
			util.assertPrecondition(srcPath, trgPath);
		} else {
			util.assertPostcondition(srcPath, trgPath);
		}
		throw new AssertionError("Bug in the testcase, the same condition failed and succeeded.");
	}
}
