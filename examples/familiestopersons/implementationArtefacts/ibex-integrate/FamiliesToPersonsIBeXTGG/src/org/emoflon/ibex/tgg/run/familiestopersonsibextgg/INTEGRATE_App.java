package org.emoflon.ibex.tgg.run.familiestopersonsibextgg;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.benchmarx.edit.AtomicEdit;
import org.benchmarx.edit.ChangeAttribute;
import org.benchmarx.edit.CreateEdge;
import org.benchmarx.edit.CreateNode;
import org.benchmarx.edit.DeleteEdge;
import org.benchmarx.edit.DeleteNode;
import org.benchmarx.edit.EdgeEdit;
import org.benchmarx.edit.IEdit;
import org.benchmarx.edit.MoveNode;
import org.benchmarx.families.smartemf.core.FamiliesComparator;
import org.benchmarx.persons.smartemf.core.PersonsComparator;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.emoflon.ibex.common.emf.EMFManipulationUtils;
import org.emoflon.ibex.common.engine.IMatch;
import org.emoflon.ibex.tgg.run.familiestopersonsibextgg.config._DefaultRegistrationHelper;
import org.emoflon.ibex.tgg.runtime.config.IRegistrationHelper;
import org.emoflon.ibex.tgg.runtime.matches.ITGGMatch;
import org.emoflon.ibex.tgg.runtime.matches.container.ImmutableMatchContainer;
import org.emoflon.ibex.tgg.runtime.strategies.integrate.FragmentProvider;
import org.emoflon.ibex.tgg.runtime.strategies.integrate.INTEGRATE;
import org.emoflon.ibex.tgg.runtime.strategies.integrate.conflicts.AttributeConflict;
import org.emoflon.ibex.tgg.runtime.strategies.integrate.conflicts.Conflict;
import org.emoflon.ibex.tgg.runtime.strategies.integrate.conflicts.ConflictContainer;
import org.emoflon.ibex.tgg.runtime.strategies.integrate.conflicts.DelPreserveAttrConflict;
import org.emoflon.ibex.tgg.runtime.strategies.integrate.conflicts.DelPreserveEdgeConflict;
import org.emoflon.ibex.tgg.runtime.strategies.integrate.conflicts.DeletePreserveConflict;
import org.emoflon.ibex.tgg.runtime.strategies.integrate.conflicts.OperationalMultiplicityConflict;
import org.emoflon.ibex.tgg.runtime.strategies.integrate.conflicts.resolution.CRS_PreferTarget;
import org.emoflon.ibex.tgg.runtime.strategies.integrate.conflicts.resolution.util.ConflictResolver;
import org.emoflon.ibex.tgg.runtime.strategies.integrate.pattern.IntegrationFragment;
import org.emoflon.ibex.tgg.runtime.strategies.integrate.pattern.IntegrationPattern;
import org.emoflon.ibex.tgg.runtime.strategies.modules.TGGResourceHandler;

import Families.FamiliesFactory;
import Families.FamiliesPackage;
import Families.Family;
import Families.FamilyMember;
import Families.FamilyRegister;
import FamiliesSmartEMF.FamiliesSmartEMFFactory;
import FamiliesSmartEMF.FamiliesSmartEMFPackage;
import Persons.Female;
import Persons.Male;
import Persons.PersonRegister;
import Persons.PersonsFactory;
import Persons.PersonsPackage;
import PersonsSmartEMF.PersonsSmartEMFFactory;
import PersonsSmartEMF.PersonsSmartEMFPackage;

public class INTEGRATE_App extends INTEGRATE {

	public static final List<IntegrationFragment> FIRST_ITERATION_FRAGMENTS = Arrays.asList( //
			FragmentProvider.CHECK_LOCAL_CONSISTENCY, //
			FragmentProvider.REPAIR,
			FragmentProvider.RESOLVE_CONFLICTS, //
			FragmentProvider.RESOLVE_BROKEN_MATCHES, //
			FragmentProvider.TRANSLATE,
			FragmentProvider.CLEAN_UP 
	);
	
	// eMoflon supports other pattern matching engines. Replace _DefaultRegistrationHelper with one of the other registrationHelpers from the *.config-package to choose between them. Default: Democles 
	public static IRegistrationHelper registrationHelper = new _DefaultRegistrationHelper();

	public INTEGRATE_App() throws IOException {
		super(registrationHelper.createIbexOptions().resourceHandler(new TGGResourceHandler() {
			@Override
			public void saveModels() throws IOException {
				super.saveModels();
			}
			
			@Override
			public void loadModels() throws IOException {
				source = createResource(options.project.path() + "/instances/src.xmi");
				target = createResource(options.project.path() + "/instances/trg.xmi");
				corr = createResource(options.project.path() + "/instances/corr.xmi");
				protocol = createResource(options.project.path() + "/instances/protocol.xmi");
			}
		}).integration.conflictSolver(new ConflictResolver() {
			
			@Override
			public void resolveConflict(ConflictContainer conflictContainer) {
				System.out.println("Solving conflictContainer: " + conflictContainer);
				for(var conflict : getSignificantConflicts(conflictContainer.getConflicts())) {
					// unsupported as of yet
					if(conflict instanceof OperationalMultiplicityConflict multiConflict)
						continue;
					else
					if(conflict instanceof CRS_PreferTarget preferTarget) {
						preferTarget.crs_preferTarget();
					}
				}
			}
			
		}).repair.usePGbasedSCruleCreation(false));
	}
	
	@Override
	protected ITGGMatch chooseOneMatch() {
		return getUpdatePolicy().chooseOneMatch(new ImmutableMatchContainer(getMatchContainer()));
	}
	
	private static Collection<Conflict> getSignificantConflicts(Set<Conflict> conflicts) {
		// for each causing match, we only store one conflict that should be resolved
		Map<IMatch, Conflict> match2conflict = new HashMap<>();
		for(var conflict : conflicts) {
			if(conflict instanceof DeletePreserveConflict deletePreserveConflict) {
				for(var causingMatch : deletePreserveConflict.getCausingMatches()) {
					if(match2conflict.containsKey(causingMatch)) {
						if(conflict instanceof DelPreserveEdgeConflict deletePreserveEdgeConflict) {
							// this preserves families
							if(deletePreserveEdgeConflict.getOperationalMatch().getPatternName().contains("Existing")) {
								match2conflict.put(causingMatch, conflict);
							}
							else {
								// we prefer edge conflicts over attributes here
								if(match2conflict.get(causingMatch) instanceof DelPreserveAttrConflict) {
									match2conflict.put(causingMatch, conflict);
								}
							}
						}
					}
					else {
						match2conflict.put(causingMatch, conflict);
					}
				}
			}
			if(conflict instanceof AttributeConflict attributeConflict) {
				ITGGMatch causingMatch = conflict.getMatch();
				match2conflict.putIfAbsent(causingMatch, conflict);
			}
		}
		return match2conflict.values();
	}
	
	@Override
	public void integrate() throws IOException {
		options.integration.pattern(new IntegrationPattern(FIRST_ITERATION_FRAGMENTS));
		super.integrate();
	}
	
	public static void main(String[] args) throws IOException {
		BasicConfigurator.configure();
		Logger.getRootLogger().setLevel(Level.INFO);

		logger.info("Starting INTEGRATE");
		long tic = System.currentTimeMillis();
		INTEGRATE_App integrate = new INTEGRATE_App();
		long toc = System.currentTimeMillis();
		logger.info("Completed init for INTEGRATE in: " + (toc - tic) + " ms");
		
		tic = System.currentTimeMillis();
		integrate.integrate();
		toc = System.currentTimeMillis();
		logger.info("Completed INTEGRATE in: " + (toc - tic) + " ms");
		
		integrate.saveModels();
		integrate.terminate();
	}


	public String getSourceAsString() {
		return new FamiliesComparator().familyToString((FamiliesSmartEMF.FamilyRegister) this.resourceHandler.getSourceResource().getContents().get(0));
	}

	public String getTargetAsString() {
		return new PersonsComparator().personsToString((PersonsSmartEMF.PersonRegister) this.resourceHandler.getTargetResource().getContents().get(0));
	}
	
	public BiConsumer<EObject, EObject> applyDelta(Supplier<IEdit<FamilyRegister>> sourceEdit, Supplier<IEdit<PersonRegister>> targetEdit) {
		return (source, target) -> {
			if(sourceEdit != null)
				applySourceDelta(source, sourceEdit);
			if(targetEdit != null)
				applyTargetDelta(target, targetEdit);
		};
	}

	private FamiliesSmartEMFFactory familyFactory = FamiliesSmartEMFFactory.eINSTANCE;
	private PersonsSmartEMFFactory personsFactory = PersonsSmartEMFFactory.eINSTANCE;
	
	// here we store a mapping between Families and FamiliesSmartEMF objects
	Map<EObject, EObject> foreignToNewObject = new HashMap<>();
	Map<EObject, EObject> newToForeignObject = new HashMap<>();
			
	private void applySourceDelta(EObject source, Supplier<IEdit<FamilyRegister>> sourceEdit) {
		var edit = sourceEdit.get();
		for(var editStep : edit.getSteps()) {
			if(!applySourceAtomicDelta(editStep)) {
				if(editStep instanceof MoveNode<?> moveNode) {
					applySourceAtomicDelta(moveNode.getDeleteEdge());
					applySourceAtomicDelta(moveNode.getCreateEdge());
					continue;
				}
				
				throw new UnsupportedOperationException("Encountered edit that is not supported yet of type " + editStep.getClass().getName());
			}
		}
	}

	private boolean applySourceAtomicDelta(AtomicEdit<?> editStep) {
		if(editStep instanceof CreateNode<?> createNode) {
			var newForeignObject = createNode.getNode();
			if(newForeignObject instanceof FamilyRegister foreignRegister) {
				var newRegister = familyFactory.createFamilyRegister();
				resourceHandler.getSourceResource().getContents().add(newRegister);
				foreignToNewObject.put(newForeignObject, newRegister);
				newToForeignObject.put(newRegister, newForeignObject);
				return true;
			}
			if(newForeignObject instanceof Family foreignFamily) {
				var newFamily = familyFactory.createFamily();
				newFamily.setName(foreignFamily.getName());
				foreignToNewObject.put(newForeignObject, newFamily);
				newToForeignObject.put(newFamily, newForeignObject);
				return true;
			}
			if(newForeignObject instanceof FamilyMember foreignFamilyMember) {
				var newFamilyMember = familyFactory.createFamilyMember();
				newFamilyMember.setName(foreignFamilyMember.getName());
				foreignToNewObject.put(newForeignObject, newFamilyMember);
				newToForeignObject.put(newFamilyMember, newForeignObject);
				return true;
			}
		}
		
		if(editStep instanceof DeleteNode<?> deleteNode) {
			var deletedForeignObject = deleteNode.getNode();
			var toBeDeletedObject = foreignToNewObject.remove(deletedForeignObject);
			newToForeignObject.remove(toBeDeletedObject);
			EMFManipulationUtils.delete(toBeDeletedObject);
			return true;
		}
		
		if(editStep instanceof EdgeEdit<?> edgeEdit) {
			var foreignEdgeSource = edgeEdit.getSource();
			var foreignEdgeTarget = edgeEdit.getTarget();
			var foreignEdgeType = edgeEdit.getType();

			if(foreignEdgeSource instanceof FamilyRegister) {
				foreignToNewObject.put(foreignEdgeSource, resourceHandler.getSourceResource().getContents().get(0));
				newToForeignObject.put(resourceHandler.getSourceResource().getContents().get(0), foreignEdgeSource);
			}
			
			var newSource = foreignToNewObject.get(foreignEdgeSource);
			var newTarget = foreignToNewObject.get(foreignEdgeTarget);
			
			var sourceType = (EClass) FamiliesSmartEMFPackage.eINSTANCE.getEClassifier(newSource.eClass().getName());
			var newEdgeType = (EReference) sourceType.getEStructuralFeature(foreignEdgeType.getName());
			
			if(newEdgeType.isMany()) {
				var content = newSource.eGet(newEdgeType);
				if(content instanceof List<?> list) {
					if(editStep instanceof CreateEdge<?> createEdge) {
						((List) content).add(newTarget);
						return true;
					}
					if(editStep instanceof DeleteEdge<?> createEdge) {
						((List) content).remove(newTarget);
						return true;
					}
				}
				else {
					throw new IllegalStateException("The feature " + newEdgeType.getName() + " should be unbounded but isn't implemented that way.");
				}
			}
			else {
				if(editStep instanceof CreateEdge<?> createEdge) {
					newSource.eSet(newEdgeType,  newTarget);
					return true;
				}
				if(editStep instanceof DeleteEdge<?> deleteEdge) {
					newSource.eSet(newEdgeType, null);
					return true;
				}
			}
		}
		
		if(editStep instanceof ChangeAttribute<?> changeAttribute) {
			var foreignObject = changeAttribute.getNode();
			var foreignAttributeType = changeAttribute.getAttribute();
			
			var objectType = (EClass) FamiliesSmartEMFPackage.eINSTANCE.getEClassifier(foreignObject.eClass().getName());
			var attributeType = (EAttribute) objectType.getEStructuralFeature(foreignAttributeType.getName());
			
			var object = foreignToNewObject.get(foreignObject);
			object.eSet(attributeType, changeAttribute.getNewValue());
			return true;
		}
		
		return false;
	}

	private void applyTargetDelta(EObject target, Supplier<IEdit<PersonRegister>> targetEdit) {
		var edit = targetEdit.get();
		for(var editStep : edit.getSteps()) {
			if(!applyTargetAtomicDelta(editStep)) {
				if(editStep instanceof MoveNode<?> moveNode) {
					applyTargetAtomicDelta(moveNode.getDeleteEdge());
					applyTargetAtomicDelta(moveNode.getCreateEdge());
					continue;
				}
				
				throw new UnsupportedOperationException("Encountered edit that is not supported yet of type " + editStep.getClass().getName());
			}
		}
	}
	
	private boolean applyTargetAtomicDelta(AtomicEdit<?> editStep) {
		if(editStep instanceof CreateNode<?> createNode) {
			var newForeignObject = createNode.getNode();
			if(newForeignObject instanceof PersonRegister foreignRegister) {
				var newRegister = personsFactory.createPersonRegister();
				resourceHandler.getTargetResource().getContents().add(newRegister);
				foreignToNewObject.put(newForeignObject, newRegister);
				newToForeignObject.put(newRegister, newForeignObject);
				return true;
			}
			if(newForeignObject instanceof Male foreignMale) {
				var newMale = personsFactory.createMale();
				newMale.setName(foreignMale.getName());
				newMale.setBirthday(foreignMale.getBirthday());
				foreignToNewObject.put(newForeignObject, newMale);
				newToForeignObject.put(newMale, newForeignObject);
				return true;
			}
			if(newForeignObject instanceof Female foreignFemale) {
				var newFemale = personsFactory.createFemale();
				newFemale.setName(foreignFemale.getName());
				newFemale.setBirthday(foreignFemale.getBirthday());
				foreignToNewObject.put(newForeignObject, newFemale);
				newToForeignObject.put(newFemale, newForeignObject);
				return true;
			}
		}
		
		if(editStep instanceof DeleteNode<?> deleteNode) {
			var deletedForeignObject = deleteNode.getNode();
			var toBeDeletedObject = foreignToNewObject.remove(deletedForeignObject);
			newToForeignObject.remove(toBeDeletedObject);
			EMFManipulationUtils.delete(toBeDeletedObject);
			return true;
		}
		
		if(editStep instanceof EdgeEdit<?> edgeEdit) {
			var foreignEdgeSource = edgeEdit.getSource();
			var foreignEdgeTarget = edgeEdit.getTarget();
			var foreignEdgeType = edgeEdit.getType();
			
			if(foreignEdgeSource instanceof PersonRegister) {
				foreignToNewObject.put(foreignEdgeSource, resourceHandler.getTargetResource().getContents().get(0));
				newToForeignObject.put(resourceHandler.getTargetResource().getContents().get(0), foreignEdgeSource);
			}
			
			var newSource = foreignToNewObject.get(foreignEdgeSource);
			var newTarget = foreignToNewObject.get(foreignEdgeTarget);
			
			var sourceType = (EClass) PersonsSmartEMFPackage.eINSTANCE.getEClassifier(newSource.eClass().getName());
			var newEdgeType = (EReference) sourceType.getEStructuralFeature(foreignEdgeType.getName());
			
			if(newEdgeType.isMany()) {
				var content = newSource.eGet(newEdgeType);
				if(content instanceof List<?> list) {
					if(editStep instanceof CreateEdge<?> createEdge) {
						((List) content).add(newTarget);
						return true;
					}
					if(editStep instanceof DeleteEdge<?> createEdge) {
						((List) content).remove(newTarget);
						return true;
					}
				}
				else {
					throw new IllegalStateException("The feature " + newEdgeType.getName() + " should be unbounded but isn't implemented that way.");
				}
			}
			else {
				if(editStep instanceof CreateEdge<?> createEdge) {
					newSource.eSet(newEdgeType,  newTarget);
					return true;
				}
				if(editStep instanceof DeleteEdge<?> deleteEdge) {
					newSource.eSet(newEdgeType, null);
					return true;
				}
			}
		}
		
		if(editStep instanceof ChangeAttribute<?> changeAttribute) {
			var foreignObject = changeAttribute.getNode();
			var foreignAttributeType = changeAttribute.getAttribute();
			
			var objectType = (EClass) PersonsSmartEMFPackage.eINSTANCE.getEClassifier(foreignObject.eClass().getName());
			var attributeType = (EAttribute) objectType.getEStructuralFeature(foreignAttributeType.getName());
			
			var object = foreignToNewObject.get(foreignObject);
			object.eSet(attributeType, changeAttribute.getNewValue());
			return true;
		}
		
		return false;
	}

	public void initialiseFamilyModel() {
		// Create initial src and trg models
		var newFamilyRegister = familyFactory.createFamilyRegister();
		getResourceHandler().getSourceResource().getContents().add(newFamilyRegister);
		try {
			integrate();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		getOrCreateForeignObject(getResourceHandler().getTargetResource().getContents().get(0));
		
		getResourceHandler().getSourceResource().eAdapters().add(new SyncingContentAdapter());
		getResourceHandler().getTargetResource().eAdapters().add(new SyncingContentAdapter());
	}
	
	private EStructuralFeature getForeignFeature(EStructuralFeature feature) {
		var type = FamiliesPackage.eINSTANCE.getEClassifier(feature.getEContainingClass().getName());
		if(type == null) 
			type = PersonsPackage.eINSTANCE.getEClassifier(feature.getEContainingClass().getName());
		if(type == null)
			throw new UnsupportedOperationException("Did not find type " + feature.getEContainingClass().getName());
		
		return ((EClass) type).getEStructuralFeature(feature.getName());
	}
	
	private FamiliesFactory foreignFamiliesFactory = FamiliesFactory.eINSTANCE;
	private PersonsFactory foreignPersonsFactory = PersonsFactory.eINSTANCE;
	
	private EObject getOrCreateForeignObject(EObject object) {
		if(newToForeignObject.containsKey(object))
			return newToForeignObject.get(object);
		
		if(object instanceof FamiliesSmartEMF.FamilyRegister register) {
			return getForeignSource();
		}
		if(object instanceof FamiliesSmartEMF.Family family) {
			var foreignFamily = foreignFamiliesFactory.createFamily();
			foreignFamily.setName(family.getName());
			newToForeignObject.put(family, foreignFamily);
			foreignToNewObject.put(foreignFamily, family);
			return foreignFamily;
		}
		if(object instanceof FamiliesSmartEMF.FamilyMember familyMember) {
			var foreignFamilyMember = foreignFamiliesFactory.createFamilyMember();
			foreignFamilyMember.setName(familyMember.getName());
			newToForeignObject.put(familyMember, foreignFamilyMember);
			foreignToNewObject.put(foreignFamilyMember, familyMember);
			return foreignFamilyMember;
		}
		
		
		if(object instanceof PersonsSmartEMF.PersonRegister register) {
			foreignTarget = foreignPersonsFactory.createPersonRegister();
			newToForeignObject.put(register, foreignTarget);
			foreignToNewObject.put(foreignTarget, register);
			return foreignTarget;
		}
		if(object instanceof PersonsSmartEMF.Male male) {
			var foreignMale = foreignPersonsFactory.createMale();
			foreignMale.setName(male.getName());
			foreignMale.setBirthday(male.getBirthday());
			newToForeignObject.put(male, foreignMale);
			foreignToNewObject.put(foreignMale, male);
			return foreignMale;
		}
		if(object instanceof PersonsSmartEMF.Female female) {
			var foreignFemale = foreignPersonsFactory.createFemale();
			foreignFemale.setName(female.getName());
			foreignFemale.setBirthday(female.getBirthday());
			newToForeignObject.put(female, foreignFemale);
			foreignToNewObject.put(foreignFemale, female);
			return foreignFemale;
		}
		throw new UnsupportedOperationException("Cannot create element of type " + object.eClass().getName());
	}

	private FamilyRegister foreignSource;
	private PersonRegister foreignTarget;
	
	public FamilyRegister getForeignSource() {
		if(foreignSource == null) {
			foreignSource = FamiliesFactory.eINSTANCE.createFamilyRegister();
		}
		return foreignSource;
	}
	
	public PersonRegister getForeignTarget() {
//		if(foreignTarget == null) {
//			foreignTarget = PersonsFactory.eINSTANCE.createPersonRegister();
//		}
		return foreignTarget;
	}
	
	class SyncingContentAdapter extends EContentAdapter {
		
		@Override
		public void notifyChanged(Notification notification) {
			super.notifyChanged(notification);
			
			switch(notification.getEventType()) {
			case Notification.ADD: {
				var refType = (EReference) notification.getFeature();
				var source = (EObject) notification.getNotifier();
				var target = (EObject) notification.getNewValue();

				var foreignSource = getOrCreateForeignObject(source);
				var foreignTarget = getOrCreateForeignObject(target);
				var foreignRefType = (EReference) getForeignFeature(refType);
				
				EMFManipulationUtils.createEdge(foreignSource, foreignTarget, foreignRefType);
				return;
			}
			case Notification.SET: {
				if(notification.getFeature() instanceof EReference referenceType) {
					var refType = (EReference) notification.getFeature();
					var source = (EObject) notification.getNotifier();
					var target = (EObject) notification.getNewValue();

					var foreignSource = getOrCreateForeignObject(source);
					var foreignTarget = target != null ? getOrCreateForeignObject(target) : null;
					var foreignRefType = (EReference) getForeignFeature(refType);
					
					EMFManipulationUtils.createEdge(foreignSource, foreignTarget, foreignRefType);
					return;
				}
				if(notification.getFeature() instanceof EAttribute attributeType) {
					var attrType = (EAttribute) notification.getFeature();
					var source = (EObject) notification.getNotifier();
					var newValue = notification.getNewValue();

					var foreignSource = getOrCreateForeignObject(source);
					var foreignAttrType = (EAttribute) getForeignFeature(attrType);

					foreignSource.eSet(foreignAttrType,  newValue);
					return;
				}
				throw new UnsupportedOperationException("Unsupported feature detected");
			}
			case Notification.REMOVE: {
				if(notification.getFeature() instanceof EReference referenceType) {
					var refType = (EReference) notification.getFeature();
					var source = (EObject) notification.getNotifier();
					var target = (EObject) notification.getOldValue();

					var foreignSource = getOrCreateForeignObject(source);
					var foreignTarget = getOrCreateForeignObject(target);
					var foreignRefType = (EReference) getForeignFeature(refType);
					
					EMFManipulationUtils.deleteEdge(foreignSource, foreignTarget, foreignRefType);
					return;
				}
				throw new UnsupportedOperationException("Unsupported feature detected");
			}
			case Notification.REMOVING_ADAPTER: {
				var notifier = (EObject) notification.getNotifier();
				var foreignObject = newToForeignObject.remove(notifier);
				foreignToNewObject.remove(notifier);
				
				EMFManipulationUtils.delete(foreignObject);
			}
			}
		}
	}
}
