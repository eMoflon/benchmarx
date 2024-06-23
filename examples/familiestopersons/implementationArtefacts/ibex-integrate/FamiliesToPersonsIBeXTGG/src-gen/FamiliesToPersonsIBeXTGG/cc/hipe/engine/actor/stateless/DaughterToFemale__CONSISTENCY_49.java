package FamiliesToPersonsIBeXTGG.cc.hipe.engine.actor.stateless;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;

import hipe.engine.actor.Port;
import hipe.engine.util.HiPESet;
import hipe.engine.match.EdgeMatch;
import hipe.engine.match.HMatch;
import hipe.engine.match.OverlapMatch;
import hipe.engine.match.LocalSearchMatch;
import hipe.engine.actor.junction.PortJunction;
import hipe.engine.actor.junction.PortJunctionLeft;
import hipe.engine.actor.junction.PortJunctionRight;
import hipe.engine.message.input.AttributeChanged;
import hipe.engine.message.input.ReferenceAdded;
import hipe.engine.message.input.ReferenceDeleted;
import hipe.engine.message.production.MatchAdded;
import hipe.engine.message.production.MatchDeleted;
import hipe.engine.message.stateless.*;
import hipe.engine.util.CollectionUtil;

import hipe.network.LocalSearchNode;

import hipe.generic.match.GenericJunctionMatch;
import hipe.generic.actor.junction.GenericJunctionActor;
import hipe.generic.actor.local.GenericLocalSearchActor;
import hipe.generic.actor.stateless.*;
import hipe.generic.actor.stateless.enums.*;
import hipe.generic.actor.stateless.search.*;
import hipe.generic.actor.search.misc.*;

import org.eclipse.emf.ecore.EObject;

public class DaughterToFemale__CONSISTENCY_49 extends GenericStatelessSearchActor{
	ConstraintChecker constraint_checker;
	DeltaAwareEdgeExplorer edge_explorer;
	DeltaAwareEdgeExplorer edge_explorer_3;
	DeltaAwareEdgeExplorer edge_explorer_4;
	DeltaAwareEdgeExplorer edge_explorer_5;
	DeltaAwareEdgeExplorer edge_explorer_6;
	DeltaAwareEdgeExplorer edge_explorer_7;
	DeltaAwareEdgeExplorer edge_explorer_8;
	DeltaAwareEdgeExplorer edge_explorer_9;
	DeltaAwareEdgeExplorer edge_explorer_10;
	DeltaAwareEdgeExplorer edge_explorer_11;
	DeltaAwareEdgeExplorer edge_explorer_12;
	DeltaAwareEdgeExplorer edge_explorer_13;
	DeltaAwareEdgeExplorer edge_explorer_14;
	DeltaAwareEdgeExplorer edge_explorer_15;
	NACQueryChecker nac;
	NACQueryChecker nac_0;
	NACQueryChecker nac_1;
	DisjointExplorer disjoint_explorer_6;
	
	SearchOrchestration<StatelessExplorer> edge_explorer_0_orchestration;
	SearchOrchestration<StatelessExplorer> edge_explorer_1_orchestration;
	SearchOrchestration<StatelessExplorer> edge_explorer_2_orchestration;
	SearchOrchestration<StatelessExplorer> edge_explorer_3_0_orchestration;
	SearchOrchestration<StatelessExplorer> edge_explorer_3_1_orchestration;
	SearchOrchestration<StatelessExplorer> edge_explorer_3_2_orchestration;
	SearchOrchestration<StatelessExplorer> edge_explorer_4_0_orchestration;
	SearchOrchestration<StatelessExplorer> edge_explorer_4_1_orchestration;
	SearchOrchestration<StatelessExplorer> edge_explorer_5_0_orchestration;
	SearchOrchestration<StatelessExplorer> edge_explorer_5_1_orchestration;
	SearchOrchestration<StatelessExplorer> edge_explorer_6_0_orchestration;
	SearchOrchestration<StatelessExplorer> edge_explorer_7_0_orchestration;
	SearchOrchestration<StatelessExplorer> edge_explorer_7_1_orchestration;
	SearchOrchestration<StatelessExplorer> edge_explorer_7_2_orchestration;
	SearchOrchestration<StatelessExplorer> edge_explorer_8_0_orchestration;
	SearchOrchestration<StatelessExplorer> edge_explorer_9_0_orchestration;
	SearchOrchestration<StatelessExplorer> edge_explorer_10_0_orchestration;
	SearchOrchestration<StatelessExplorer> edge_explorer_11_0_orchestration;
	SearchOrchestration<StatelessExplorer> edge_explorer_12_0_orchestration;
	SearchOrchestration<StatelessExplorer> edge_explorer_13_0_orchestration;
	SearchOrchestration<StatelessExplorer> edge_explorer_14_0_orchestration;
	SearchOrchestration<StatelessExplorer> edge_explorer_15_0_orchestration;
	SearchOrchestration<StatelessExplorer> nac_orchestration;
	SearchOrchestration<StatelessExplorer> nac_0_orchestration;
	SearchOrchestration<StatelessExplorer> nac_1_orchestration;
	
	@Override
	protected void initializeSearchComponents() {
		constraint_checker = new ConstraintChecker(this, this::constraint_checker_method);
		name2explorer.put("constraint_checker", constraint_checker);
		EdgeLookupMethods edge_explorer_methods = new EdgeLookupMethods();
						edge_explorer_methods.checkSourceType = (o) -> o instanceof FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr;
						edge_explorer_methods.checkTargetType = (o) -> o instanceof FamiliesSmartEMF.FamilyMember;
						edge_explorer_methods.unique_lookup = (o) -> ((FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr) o).getSource();
						edge_explorer_methods.multi_opposite_lookup = (o) -> (Collection<? extends Object>) ((FamiliesSmartEMF.FamilyMember) o).eGet(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getFamilyMemberToPersonCorr_Source().getEOpposite());
						edge_explorer = new DeltaAwareEdgeExplorer(this, 4, 2, edge_explorer_methods, FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getFamilyMemberToPersonCorr_Source());
		name2explorer.put("edge_explorer", edge_explorer);
		EdgeLookupMethods edge_explorer_3_methods = new EdgeLookupMethods();
						edge_explorer_3_methods.checkSourceType = (o) -> o instanceof FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterToFemale;
						edge_explorer_3_methods.checkTargetType = (o) -> o instanceof FamiliesSmartEMF.Family;
						edge_explorer_3_methods.unique_lookup = (o) -> ((FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterToFemale) o).getCREATE__SOURCE__f();
						edge_explorer_3_methods.multi_opposite_lookup = (o) -> (Collection<? extends Object>) ((FamiliesSmartEMF.Family) o).eGet(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_DaughterToFemale_CREATE__SOURCE__f().getEOpposite());
						edge_explorer_3 = new DeltaAwareEdgeExplorer(this, 7, 1, edge_explorer_3_methods, FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_DaughterToFemale_CREATE__SOURCE__f());
		name2explorer.put("edge_explorer_3", edge_explorer_3);
		EdgeLookupMethods edge_explorer_4_methods = new EdgeLookupMethods();
						edge_explorer_4_methods.checkSourceType = (o) -> o instanceof FamiliesSmartEMF.FamilyRegister;
						edge_explorer_4_methods.checkTargetType = (o) -> o instanceof FamiliesSmartEMF.Family;
						edge_explorer_4_methods.multi_lookup = (o) -> ((FamiliesSmartEMF.FamilyRegister) o).getFamilies();
						edge_explorer_4_methods.unique_opposite_lookup = (o) -> ((FamiliesSmartEMF.Family) o).getFamiliesInverse();
						edge_explorer_4 = new DeltaAwareEdgeExplorer(this, 0, 1, edge_explorer_4_methods, FamiliesSmartEMF.FamiliesSmartEMFPackage.eINSTANCE.getFamilyRegister_Families());
		name2explorer.put("edge_explorer_4", edge_explorer_4);
		EdgeLookupMethods edge_explorer_5_methods = new EdgeLookupMethods();
						edge_explorer_5_methods.checkSourceType = (o) -> o instanceof FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterToFemale;
						edge_explorer_5_methods.checkTargetType = (o) -> o instanceof FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr;
						edge_explorer_5_methods.unique_lookup = (o) -> ((FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterToFemale) o).getCONTEXT__CORRESPONDENCE__families2persons();
						edge_explorer_5_methods.multi_opposite_lookup = (o) -> (Collection<? extends Object>) ((FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) o).eGet(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_DaughterToFemale_CONTEXT__CORRESPONDENCE__families2persons().getEOpposite());
						edge_explorer_5 = new DeltaAwareEdgeExplorer(this, 7, 3, edge_explorer_5_methods, FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_DaughterToFemale_CONTEXT__CORRESPONDENCE__families2persons());
		name2explorer.put("edge_explorer_5", edge_explorer_5);
		EdgeLookupMethods edge_explorer_6_methods = new EdgeLookupMethods();
						edge_explorer_6_methods.checkSourceType = (o) -> o instanceof FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterToFemale;
						edge_explorer_6_methods.checkTargetType = (o) -> o instanceof FamiliesSmartEMF.FamilyRegister;
						edge_explorer_6_methods.unique_lookup = (o) -> ((FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterToFemale) o).getCONTEXT__SOURCE__families();
						edge_explorer_6_methods.multi_opposite_lookup = (o) -> (Collection<? extends Object>) ((FamiliesSmartEMF.FamilyRegister) o).eGet(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_DaughterToFemale_CONTEXT__SOURCE__families().getEOpposite());
						edge_explorer_6 = new DeltaAwareEdgeExplorer(this, 7, 0, edge_explorer_6_methods, FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_DaughterToFemale_CONTEXT__SOURCE__families());
		name2explorer.put("edge_explorer_6", edge_explorer_6);
		EdgeLookupMethods edge_explorer_7_methods = new EdgeLookupMethods();
						edge_explorer_7_methods.checkSourceType = (o) -> o instanceof PersonsSmartEMF.PersonRegister;
						edge_explorer_7_methods.checkTargetType = (o) -> o instanceof PersonsSmartEMF.Female;
						edge_explorer_7_methods.multi_lookup = (o) -> ((PersonsSmartEMF.PersonRegister) o).getPersons().stream().filter(obj -> obj instanceof PersonsSmartEMF.Female).collect(Collectors.toList());
						edge_explorer_7_methods.unique_opposite_lookup = (o) -> ((PersonsSmartEMF.Person) o).getPersonsInverse();
						edge_explorer_7 = new DeltaAwareEdgeExplorer(this, 5, 6, edge_explorer_7_methods, PersonsSmartEMF.PersonsSmartEMFPackage.eINSTANCE.getPersonRegister_Persons());
		name2explorer.put("edge_explorer_7", edge_explorer_7);
		EdgeLookupMethods edge_explorer_8_methods = new EdgeLookupMethods();
						edge_explorer_8_methods.checkSourceType = (o) -> o instanceof FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterToFemale;
						edge_explorer_8_methods.checkTargetType = (o) -> o instanceof FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr;
						edge_explorer_8_methods.unique_lookup = (o) -> ((FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterToFemale) o).getCREATE__CORRESPONDENCE__familyMember2Persons();
						edge_explorer_8_methods.multi_opposite_lookup = (o) -> (Collection<? extends Object>) ((FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr) o).eGet(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_DaughterToFemale_CREATE__CORRESPONDENCE__familyMember2Persons().getEOpposite());
						edge_explorer_8 = new DeltaAwareEdgeExplorer(this, 7, 4, edge_explorer_8_methods, FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_DaughterToFemale_CREATE__CORRESPONDENCE__familyMember2Persons());
		name2explorer.put("edge_explorer_8", edge_explorer_8);
		EdgeLookupMethods edge_explorer_9_methods = new EdgeLookupMethods();
						edge_explorer_9_methods.checkSourceType = (o) -> o instanceof FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterToFemale;
						edge_explorer_9_methods.checkTargetType = (o) -> o instanceof PersonsSmartEMF.Female;
						edge_explorer_9_methods.unique_lookup = (o) -> ((FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterToFemale) o).getCREATE__TARGET__p();
						edge_explorer_9_methods.multi_opposite_lookup = (o) -> (Collection<? extends Object>) ((PersonsSmartEMF.Female) o).eGet(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_DaughterToFemale_CREATE__TARGET__p().getEOpposite());
						edge_explorer_9 = new DeltaAwareEdgeExplorer(this, 7, 6, edge_explorer_9_methods, FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_DaughterToFemale_CREATE__TARGET__p());
		name2explorer.put("edge_explorer_9", edge_explorer_9);
		EdgeLookupMethods edge_explorer_10_methods = new EdgeLookupMethods();
						edge_explorer_10_methods.checkSourceType = (o) -> o instanceof FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterToFemale;
						edge_explorer_10_methods.checkTargetType = (o) -> o instanceof PersonsSmartEMF.PersonRegister;
						edge_explorer_10_methods.unique_lookup = (o) -> ((FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterToFemale) o).getCONTEXT__TARGET__persons();
						edge_explorer_10_methods.multi_opposite_lookup = (o) -> (Collection<? extends Object>) ((PersonsSmartEMF.PersonRegister) o).eGet(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_DaughterToFemale_CONTEXT__TARGET__persons().getEOpposite());
						edge_explorer_10 = new DeltaAwareEdgeExplorer(this, 7, 5, edge_explorer_10_methods, FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_DaughterToFemale_CONTEXT__TARGET__persons());
		name2explorer.put("edge_explorer_10", edge_explorer_10);
		EdgeLookupMethods edge_explorer_11_methods = new EdgeLookupMethods();
						edge_explorer_11_methods.checkSourceType = (o) -> o instanceof FamiliesSmartEMF.Family;
						edge_explorer_11_methods.checkTargetType = (o) -> o instanceof FamiliesSmartEMF.FamilyMember;
						edge_explorer_11_methods.multi_lookup = (o) -> ((FamiliesSmartEMF.Family) o).getDaughters();
						edge_explorer_11_methods.unique_opposite_lookup = (o) -> ((FamiliesSmartEMF.FamilyMember) o).getDaughtersInverse();
						edge_explorer_11 = new DeltaAwareEdgeExplorer(this, 1, 2, edge_explorer_11_methods, FamiliesSmartEMF.FamiliesSmartEMFPackage.eINSTANCE.getFamily_Daughters());
		name2explorer.put("edge_explorer_11", edge_explorer_11);
		EdgeLookupMethods edge_explorer_12_methods = new EdgeLookupMethods();
						edge_explorer_12_methods.checkSourceType = (o) -> o instanceof FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr;
						edge_explorer_12_methods.checkTargetType = (o) -> o instanceof PersonsSmartEMF.PersonRegister;
						edge_explorer_12_methods.unique_lookup = (o) -> ((FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) o).getTarget();
						edge_explorer_12_methods.multi_opposite_lookup = (o) -> (Collection<? extends Object>) ((PersonsSmartEMF.PersonRegister) o).eGet(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getRegisterToRegisterCorr_Target().getEOpposite());
						edge_explorer_12 = new DeltaAwareEdgeExplorer(this, 3, 5, edge_explorer_12_methods, FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getRegisterToRegisterCorr_Target());
		name2explorer.put("edge_explorer_12", edge_explorer_12);
		EdgeLookupMethods edge_explorer_13_methods = new EdgeLookupMethods();
						edge_explorer_13_methods.checkSourceType = (o) -> o instanceof FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterToFemale;
						edge_explorer_13_methods.checkTargetType = (o) -> o instanceof FamiliesSmartEMF.FamilyMember;
						edge_explorer_13_methods.unique_lookup = (o) -> ((FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterToFemale) o).getCREATE__SOURCE__fm();
						edge_explorer_13_methods.multi_opposite_lookup = (o) -> (Collection<? extends Object>) ((FamiliesSmartEMF.FamilyMember) o).eGet(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_DaughterToFemale_CREATE__SOURCE__fm().getEOpposite());
						edge_explorer_13 = new DeltaAwareEdgeExplorer(this, 7, 2, edge_explorer_13_methods, FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getProtocolNode_DaughterToFemale_CREATE__SOURCE__fm());
		name2explorer.put("edge_explorer_13", edge_explorer_13);
		EdgeLookupMethods edge_explorer_14_methods = new EdgeLookupMethods();
						edge_explorer_14_methods.checkSourceType = (o) -> o instanceof FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr;
						edge_explorer_14_methods.checkTargetType = (o) -> o instanceof FamiliesSmartEMF.FamilyRegister;
						edge_explorer_14_methods.unique_lookup = (o) -> ((FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) o).getSource();
						edge_explorer_14_methods.multi_opposite_lookup = (o) -> (Collection<? extends Object>) ((FamiliesSmartEMF.FamilyRegister) o).eGet(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getRegisterToRegisterCorr_Source().getEOpposite());
						edge_explorer_14 = new DeltaAwareEdgeExplorer(this, 3, 0, edge_explorer_14_methods, FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getRegisterToRegisterCorr_Source());
		name2explorer.put("edge_explorer_14", edge_explorer_14);
		EdgeLookupMethods edge_explorer_15_methods = new EdgeLookupMethods();
						edge_explorer_15_methods.checkSourceType = (o) -> o instanceof FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr;
						edge_explorer_15_methods.checkTargetType = (o) -> o instanceof PersonsSmartEMF.Female;
						edge_explorer_15_methods.unique_lookup = (o) -> {EObject result = ((FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr) o).getTarget(); return (result instanceof PersonsSmartEMF.Female ? result : null);};
						edge_explorer_15_methods.multi_opposite_lookup = (o) -> (Collection<? extends Object>) ((PersonsSmartEMF.Person) o).eGet(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getFamilyMemberToPersonCorr_Target().getEOpposite());
						edge_explorer_15 = new DeltaAwareEdgeExplorer(this, 4, 6, edge_explorer_15_methods, FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getFamilyMemberToPersonCorr_Target());
		name2explorer.put("edge_explorer_15", edge_explorer_15);
		nac = new NACQueryChecker(this, 0, "DaughterToFemale_fm_sons_2_incoming_SOURCE__FILTER_NAC_SRC_33", name2actor.get("DaughterToFemale_fm_sons_2_incoming_SOURCE__FILTER_NAC_SRC_33"), 2);
		name2explorer.put("nac", nac);
		nac_0 = new NACQueryChecker(this, 1, "DaughterToFemale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC_36", name2actor.get("DaughterToFemale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC_36"), 2);
		name2explorer.put("nac_0", nac_0);
		nac_1 = new NACQueryChecker(this, 2, "DaughterToFemale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC_39", name2actor.get("DaughterToFemale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC_39"), 2);
		name2explorer.put("nac_1", nac_1);
		disjoint_explorer_6 = new DisjointExplorer(this, observedResources, 7, (o) -> o instanceof FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterToFemale, true);
		name2explorer.put("disjoint_explorer_6", disjoint_explorer_6);
	}
	
	@Override
	protected void initializeOrchestration() {
		edge_explorer_0_orchestration = initializeOrchestration(node.getOrchestrations().get(0).getPlan());
		edge_explorer_0_orchestration.setFieldSetter(x -> edge_explorer_0_orchestration = (SearchOrchestration) x);
		edge_explorer_0_orchestration.setFieldGetter(() -> edge_explorer_0_orchestration);
		
		edge_explorer_1_orchestration = initializeOrchestration(node.getOrchestrations().get(1).getPlan());
		edge_explorer_1_orchestration.setFieldSetter(x -> edge_explorer_1_orchestration = (SearchOrchestration) x);
		edge_explorer_1_orchestration.setFieldGetter(() -> edge_explorer_1_orchestration);
		
		edge_explorer_2_orchestration = initializeOrchestration(node.getOrchestrations().get(2).getPlan());
		edge_explorer_2_orchestration.setFieldSetter(x -> edge_explorer_2_orchestration = (SearchOrchestration) x);
		edge_explorer_2_orchestration.setFieldGetter(() -> edge_explorer_2_orchestration);
		
		edge_explorer_3_0_orchestration = initializeOrchestration(node.getOrchestrations().get(3).getPlan());
		edge_explorer_3_0_orchestration.setFieldSetter(x -> edge_explorer_3_0_orchestration = (SearchOrchestration) x);
		edge_explorer_3_0_orchestration.setFieldGetter(() -> edge_explorer_3_0_orchestration);
		
		edge_explorer_3_1_orchestration = initializeOrchestration(node.getOrchestrations().get(4).getPlan());
		edge_explorer_3_1_orchestration.setFieldSetter(x -> edge_explorer_3_1_orchestration = (SearchOrchestration) x);
		edge_explorer_3_1_orchestration.setFieldGetter(() -> edge_explorer_3_1_orchestration);
		
		edge_explorer_3_2_orchestration = initializeOrchestration(node.getOrchestrations().get(5).getPlan());
		edge_explorer_3_2_orchestration.setFieldSetter(x -> edge_explorer_3_2_orchestration = (SearchOrchestration) x);
		edge_explorer_3_2_orchestration.setFieldGetter(() -> edge_explorer_3_2_orchestration);
		
		edge_explorer_4_0_orchestration = initializeOrchestration(node.getOrchestrations().get(6).getPlan());
		edge_explorer_4_0_orchestration.setFieldSetter(x -> edge_explorer_4_0_orchestration = (SearchOrchestration) x);
		edge_explorer_4_0_orchestration.setFieldGetter(() -> edge_explorer_4_0_orchestration);
		
		edge_explorer_4_1_orchestration = initializeOrchestration(node.getOrchestrations().get(7).getPlan());
		edge_explorer_4_1_orchestration.setFieldSetter(x -> edge_explorer_4_1_orchestration = (SearchOrchestration) x);
		edge_explorer_4_1_orchestration.setFieldGetter(() -> edge_explorer_4_1_orchestration);
		
		edge_explorer_5_0_orchestration = initializeOrchestration(node.getOrchestrations().get(8).getPlan());
		edge_explorer_5_0_orchestration.setFieldSetter(x -> edge_explorer_5_0_orchestration = (SearchOrchestration) x);
		edge_explorer_5_0_orchestration.setFieldGetter(() -> edge_explorer_5_0_orchestration);
		
		edge_explorer_5_1_orchestration = initializeOrchestration(node.getOrchestrations().get(9).getPlan());
		edge_explorer_5_1_orchestration.setFieldSetter(x -> edge_explorer_5_1_orchestration = (SearchOrchestration) x);
		edge_explorer_5_1_orchestration.setFieldGetter(() -> edge_explorer_5_1_orchestration);
		
		edge_explorer_6_0_orchestration = initializeOrchestration(node.getOrchestrations().get(10).getPlan());
		edge_explorer_6_0_orchestration.setFieldSetter(x -> edge_explorer_6_0_orchestration = (SearchOrchestration) x);
		edge_explorer_6_0_orchestration.setFieldGetter(() -> edge_explorer_6_0_orchestration);
		
		edge_explorer_7_0_orchestration = initializeOrchestration(node.getOrchestrations().get(11).getPlan());
		edge_explorer_7_0_orchestration.setFieldSetter(x -> edge_explorer_7_0_orchestration = (SearchOrchestration) x);
		edge_explorer_7_0_orchestration.setFieldGetter(() -> edge_explorer_7_0_orchestration);
		
		edge_explorer_7_1_orchestration = initializeOrchestration(node.getOrchestrations().get(12).getPlan());
		edge_explorer_7_1_orchestration.setFieldSetter(x -> edge_explorer_7_1_orchestration = (SearchOrchestration) x);
		edge_explorer_7_1_orchestration.setFieldGetter(() -> edge_explorer_7_1_orchestration);
		
		edge_explorer_7_2_orchestration = initializeOrchestration(node.getOrchestrations().get(13).getPlan());
		edge_explorer_7_2_orchestration.setFieldSetter(x -> edge_explorer_7_2_orchestration = (SearchOrchestration) x);
		edge_explorer_7_2_orchestration.setFieldGetter(() -> edge_explorer_7_2_orchestration);
		
		edge_explorer_8_0_orchestration = initializeOrchestration(node.getOrchestrations().get(14).getPlan());
		edge_explorer_8_0_orchestration.setFieldSetter(x -> edge_explorer_8_0_orchestration = (SearchOrchestration) x);
		edge_explorer_8_0_orchestration.setFieldGetter(() -> edge_explorer_8_0_orchestration);
		
		edge_explorer_9_0_orchestration = initializeOrchestration(node.getOrchestrations().get(15).getPlan());
		edge_explorer_9_0_orchestration.setFieldSetter(x -> edge_explorer_9_0_orchestration = (SearchOrchestration) x);
		edge_explorer_9_0_orchestration.setFieldGetter(() -> edge_explorer_9_0_orchestration);
		
		edge_explorer_10_0_orchestration = initializeOrchestration(node.getOrchestrations().get(16).getPlan());
		edge_explorer_10_0_orchestration.setFieldSetter(x -> edge_explorer_10_0_orchestration = (SearchOrchestration) x);
		edge_explorer_10_0_orchestration.setFieldGetter(() -> edge_explorer_10_0_orchestration);
		
		edge_explorer_11_0_orchestration = initializeOrchestration(node.getOrchestrations().get(17).getPlan());
		edge_explorer_11_0_orchestration.setFieldSetter(x -> edge_explorer_11_0_orchestration = (SearchOrchestration) x);
		edge_explorer_11_0_orchestration.setFieldGetter(() -> edge_explorer_11_0_orchestration);
		
		edge_explorer_12_0_orchestration = initializeOrchestration(node.getOrchestrations().get(18).getPlan());
		edge_explorer_12_0_orchestration.setFieldSetter(x -> edge_explorer_12_0_orchestration = (SearchOrchestration) x);
		edge_explorer_12_0_orchestration.setFieldGetter(() -> edge_explorer_12_0_orchestration);
		
		edge_explorer_13_0_orchestration = initializeOrchestration(node.getOrchestrations().get(19).getPlan());
		edge_explorer_13_0_orchestration.setFieldSetter(x -> edge_explorer_13_0_orchestration = (SearchOrchestration) x);
		edge_explorer_13_0_orchestration.setFieldGetter(() -> edge_explorer_13_0_orchestration);
		
		edge_explorer_14_0_orchestration = initializeOrchestration(node.getOrchestrations().get(20).getPlan());
		edge_explorer_14_0_orchestration.setFieldSetter(x -> edge_explorer_14_0_orchestration = (SearchOrchestration) x);
		edge_explorer_14_0_orchestration.setFieldGetter(() -> edge_explorer_14_0_orchestration);
		
		edge_explorer_15_0_orchestration = initializeOrchestration(node.getOrchestrations().get(21).getPlan());
		edge_explorer_15_0_orchestration.setFieldSetter(x -> edge_explorer_15_0_orchestration = (SearchOrchestration) x);
		edge_explorer_15_0_orchestration.setFieldGetter(() -> edge_explorer_15_0_orchestration);
		
		nac_orchestration = initializeOrchestration(node.getOrchestrations().get(22).getPlan());
		nac_orchestration.setFieldSetter(x -> nac_orchestration = (SearchOrchestration) x);
		nac_orchestration.setFieldGetter(() -> nac_orchestration);
		
		nac_0_orchestration = initializeOrchestration(node.getOrchestrations().get(23).getPlan());
		nac_0_orchestration.setFieldSetter(x -> nac_0_orchestration = (SearchOrchestration) x);
		nac_0_orchestration.setFieldGetter(() -> nac_0_orchestration);
		
		nac_1_orchestration = initializeOrchestration(node.getOrchestrations().get(24).getPlan());
		nac_1_orchestration.setFieldSetter(x -> nac_1_orchestration = (SearchOrchestration) x);
		nac_1_orchestration.setFieldGetter(() -> nac_1_orchestration);
		
		
		localNodeOrchestrations = new SearchOrchestration[1];
		localNodeOrchestrations[0] = initializeOrchestration(node.getLocalNodeOrchestration().get(0).getPlan());
		localNodeOrchestrations[0].setFieldSetter(x -> localNodeOrchestrations[0] = (SearchOrchestration) x);
		localNodeOrchestrations[0].setFieldGetter(() -> localNodeOrchestrations[0]);
				
		
		disjointOrchestration = initializeOrchestration(node.getDisjointOrchestration().getPlan());
		disjointOrchestration.setFieldSetter(x -> disjointOrchestration = (SearchOrchestration) x);
		disjointOrchestration.setFieldGetter(() -> disjointOrchestration);		
	}
	
	@Override
	protected void initializePatternSpecifics() {
		super.initializePatternSpecifics();
		
		numberOfNodes = 8;
		hasLocalNodes = false;
	}
	
	@Override
	protected void added(MatchAdded<HMatch> msg) {
		initialMessage = msg.initialMessage;

		HMatch match = msg.input;
		Object[] objs = match.getNodes();
		outer: switch(match.creator) {
			case "FamilyRegister_object_SP0": 
				{
					if(!lazy_initialization) {
						{
							// families
							var match_0 = new StatelessDeltaMatch(msg, "DaughterToFemale__CONSISTENCY_49", numberOfNodes, 3, UsingDeltaMode.CREATE);
							match_0.getNodes()[0] = objs[0];
							match_0.registerSignatureIndex(0);
							if(options.trackMatchingProcess)
								match_0.registerDelta(UsingDeltaMode.CREATE, objs[0]);
							start(edge_explorer_4_1_orchestration, StatelessInputType.OBJECT, match_0);
						}
					}
				}
				break;
			case "Family_object_SP0": 
				{
					if(!lazy_initialization) {
						{
							// f
							var match_1 = new StatelessDeltaMatch(msg, "DaughterToFemale__CONSISTENCY_49", numberOfNodes, 3, UsingDeltaMode.CREATE);
							match_1.getNodes()[1] = objs[0];
							match_1.registerSignatureIndex(1);
							if(options.trackMatchingProcess)
								match_1.registerDelta(UsingDeltaMode.CREATE, objs[0]);
							start(edge_explorer_3_2_orchestration, StatelessInputType.OBJECT, match_1);
						}
					}
				}
				break;
			case "FamilyMember_object_SP9": 
				{
					if(!lazy_initialization) {
						{
							// fm
							var match_2 = new StatelessDeltaMatch(msg, "DaughterToFemale__CONSISTENCY_49", numberOfNodes, 3, UsingDeltaMode.CREATE);
							match_2.getNodes()[2] = objs[0];
							match_2.registerSignatureIndex(2);
							if(options.trackMatchingProcess)
								match_2.registerDelta(UsingDeltaMode.CREATE, objs[0]);
							start(edge_explorer_2_orchestration, StatelessInputType.OBJECT, match_2);
						}
					}
				}
				break;
			case "RegisterToRegisterCorr_object_SP3": 
				{
					if(!lazy_initialization) {
						{
							// families2persons
							var match_3 = new StatelessDeltaMatch(msg, "DaughterToFemale__CONSISTENCY_49", numberOfNodes, 3, UsingDeltaMode.CREATE);
							match_3.getNodes()[3] = objs[0];
							match_3.registerSignatureIndex(3);
							if(options.trackMatchingProcess)
								match_3.registerDelta(UsingDeltaMode.CREATE, objs[0]);
							start(edge_explorer_5_1_orchestration, StatelessInputType.OBJECT, match_3);
						}
					}
				}
				break;
			case "FamilyMemberToPersonCorr_object_SP1": 
				{
					if(!lazy_initialization) {
						{
							// familyMember2Persons
							var match_4 = new StatelessDeltaMatch(msg, "DaughterToFemale__CONSISTENCY_49", numberOfNodes, 3, UsingDeltaMode.CREATE);
							match_4.getNodes()[4] = objs[0];
							match_4.registerSignatureIndex(4);
							if(options.trackMatchingProcess)
								match_4.registerDelta(UsingDeltaMode.CREATE, objs[0]);
							start(edge_explorer_1_orchestration, StatelessInputType.OBJECT, match_4);
						}
					}
				}
				break;
			case "PersonRegister_object_SP3": 
				{
					if(!lazy_initialization) {
						{
							// persons
							var match_5 = new StatelessDeltaMatch(msg, "DaughterToFemale__CONSISTENCY_49", numberOfNodes, 3, UsingDeltaMode.CREATE);
							match_5.getNodes()[5] = objs[0];
							match_5.registerSignatureIndex(5);
							if(options.trackMatchingProcess)
								match_5.registerDelta(UsingDeltaMode.CREATE, objs[0]);
							start(edge_explorer_7_1_orchestration, StatelessInputType.OBJECT, match_5);
						}
					}
				}
				break;
			case "Female_object_SP1": 
				{
					if(!lazy_initialization) {
						{
							// p
							var match_6 = new StatelessDeltaMatch(msg, "DaughterToFemale__CONSISTENCY_49", numberOfNodes, 3, UsingDeltaMode.CREATE);
							match_6.getNodes()[6] = objs[0];
							match_6.registerSignatureIndex(6);
							if(options.trackMatchingProcess)
								match_6.registerDelta(UsingDeltaMode.CREATE, objs[0]);
							start(edge_explorer_7_2_orchestration, StatelessInputType.OBJECT, match_6);
						}
					}
				}
				break;
			case "ProtocolNode_DaughterToFemale_object": 
				{
					{
						// _eMoflon_ProtocolNode_
						var match_7 = new StatelessDeltaMatch(msg, "DaughterToFemale__CONSISTENCY_49", numberOfNodes, 3, UsingDeltaMode.CREATE);
						match_7.getNodes()[7] = objs[0];
						match_7.registerSignatureIndex(7);
						if(options.trackMatchingProcess)
							match_7.registerDelta(UsingDeltaMode.CREATE, objs[0]);
						start(edge_explorer_3_1_orchestration, StatelessInputType.OBJECT, match_7);
					}
				}
				break;
			case "DaughterToFemale_fm_sons_2_incoming_SOURCE__FILTER_NAC_SRC_33": 
				{
					if(!lazy_initialization) {
						var inputMatch = (StatelessDeltaMatch) match;
						{
							var match_0 = new StatelessDeltaMatch(msg, "DaughterToFemale__CONSISTENCY_49", numberOfNodes, 3, UsingDeltaMode.DELETE);
							match_0.registerInvocationMessage(msg, 0);
							match_0.getNodes()[2] = objs[1];
							
							for(var inputSignatureIndex : inputMatch.deltaSignatureBindingIndices) {
								switch(inputSignatureIndex) {
									case 1: break outer;
								}
							}
							
							// start search
							start(nac_orchestration, StatelessInputType.NAC, match_0);
						}
					}
				}
				break;
			case "DaughterToFemale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC_36": 
				{
					if(!lazy_initialization) {
						var inputMatch = (StatelessDeltaMatch) match;
						{
							var match_1 = new StatelessDeltaMatch(msg, "DaughterToFemale__CONSISTENCY_49", numberOfNodes, 3, UsingDeltaMode.DELETE);
							match_1.registerInvocationMessage(msg, 1);
							match_1.getNodes()[2] = objs[1];
							
							for(var inputSignatureIndex : inputMatch.deltaSignatureBindingIndices) {
								switch(inputSignatureIndex) {
									case 1: break outer;
								}
							}
							
							// start search
							start(nac_0_orchestration, StatelessInputType.NAC, match_1);
						}
					}
				}
				break;
			case "DaughterToFemale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC_39": 
				{
					if(!lazy_initialization) {
						var inputMatch = (StatelessDeltaMatch) match;
						{
							var match_2 = new StatelessDeltaMatch(msg, "DaughterToFemale__CONSISTENCY_49", numberOfNodes, 3, UsingDeltaMode.DELETE);
							match_2.registerInvocationMessage(msg, 2);
							match_2.getNodes()[2] = objs[1];
							
							for(var inputSignatureIndex : inputMatch.deltaSignatureBindingIndices) {
								switch(inputSignatureIndex) {
									case 1: break outer;
								}
							}
							
							// start search
							start(nac_1_orchestration, StatelessInputType.NAC, match_2);
						}
					}
				}
				break;
			default: throw new RuntimeException("Detected unknown match from " + msg.input.creator);
		}
		
		msg.initialMessage.decrement();
	}

	@Override
	protected void removed(MatchDeleted<HMatch> msg) {
		initialMessage = msg.initialMessage;

		HMatch match = msg.input;
		Object[] objs = match.getNodes();

		outer: switch(match.creator) {
			case "FamilyRegister_object_SP0": 
				{
					{
						// families
						var match_0 = new StatelessDeltaMatch(msg, "DaughterToFemale__CONSISTENCY_49", numberOfNodes, 3, UsingDeltaMode.DELETE);
						match_0.getNodes()[0] = objs[0];
						match_0.registerSignatureIndex(0);
						if(options.trackMatchingProcess)
							match_0.registerDelta(UsingDeltaMode.DELETE, objs[0]);
						start(edge_explorer_4_1_orchestration, StatelessInputType.OBJECT, match_0);
					}
				}
				break;
			case "Family_object_SP0": 
				{
					{
						// f
						var match_1 = new StatelessDeltaMatch(msg, "DaughterToFemale__CONSISTENCY_49", numberOfNodes, 3, UsingDeltaMode.DELETE);
						match_1.getNodes()[1] = objs[0];
						match_1.registerSignatureIndex(1);
						if(options.trackMatchingProcess)
							match_1.registerDelta(UsingDeltaMode.DELETE, objs[0]);
						start(edge_explorer_3_2_orchestration, StatelessInputType.OBJECT, match_1);
					}
				}
				break;
			case "FamilyMember_object_SP9": 
				{
					{
						// fm
						var match_2 = new StatelessDeltaMatch(msg, "DaughterToFemale__CONSISTENCY_49", numberOfNodes, 3, UsingDeltaMode.DELETE);
						match_2.getNodes()[2] = objs[0];
						match_2.registerSignatureIndex(2);
						if(options.trackMatchingProcess)
							match_2.registerDelta(UsingDeltaMode.DELETE, objs[0]);
						start(edge_explorer_2_orchestration, StatelessInputType.OBJECT, match_2);
					}
				}
				break;
			case "RegisterToRegisterCorr_object_SP3": 
				{
					{
						// families2persons
						var match_3 = new StatelessDeltaMatch(msg, "DaughterToFemale__CONSISTENCY_49", numberOfNodes, 3, UsingDeltaMode.DELETE);
						match_3.getNodes()[3] = objs[0];
						match_3.registerSignatureIndex(3);
						if(options.trackMatchingProcess)
							match_3.registerDelta(UsingDeltaMode.DELETE, objs[0]);
						start(edge_explorer_5_1_orchestration, StatelessInputType.OBJECT, match_3);
					}
				}
				break;
			case "FamilyMemberToPersonCorr_object_SP1": 
				{
					{
						// familyMember2Persons
						var match_4 = new StatelessDeltaMatch(msg, "DaughterToFemale__CONSISTENCY_49", numberOfNodes, 3, UsingDeltaMode.DELETE);
						match_4.getNodes()[4] = objs[0];
						match_4.registerSignatureIndex(4);
						if(options.trackMatchingProcess)
							match_4.registerDelta(UsingDeltaMode.DELETE, objs[0]);
						start(edge_explorer_1_orchestration, StatelessInputType.OBJECT, match_4);
					}
				}
				break;
			case "PersonRegister_object_SP3": 
				{
					{
						// persons
						var match_5 = new StatelessDeltaMatch(msg, "DaughterToFemale__CONSISTENCY_49", numberOfNodes, 3, UsingDeltaMode.DELETE);
						match_5.getNodes()[5] = objs[0];
						match_5.registerSignatureIndex(5);
						if(options.trackMatchingProcess)
							match_5.registerDelta(UsingDeltaMode.DELETE, objs[0]);
						start(edge_explorer_7_1_orchestration, StatelessInputType.OBJECT, match_5);
					}
				}
				break;
			case "Female_object_SP1": 
				{
					{
						// p
						var match_6 = new StatelessDeltaMatch(msg, "DaughterToFemale__CONSISTENCY_49", numberOfNodes, 3, UsingDeltaMode.DELETE);
						match_6.getNodes()[6] = objs[0];
						match_6.registerSignatureIndex(6);
						if(options.trackMatchingProcess)
							match_6.registerDelta(UsingDeltaMode.DELETE, objs[0]);
						start(edge_explorer_7_2_orchestration, StatelessInputType.OBJECT, match_6);
					}
				}
				break;
			case "ProtocolNode_DaughterToFemale_object": 
				{
					{
						// _eMoflon_ProtocolNode_
						var match_7 = new StatelessDeltaMatch(msg, "DaughterToFemale__CONSISTENCY_49", numberOfNodes, 3, UsingDeltaMode.DELETE);
						match_7.getNodes()[7] = objs[0];
						match_7.registerSignatureIndex(7);
						if(options.trackMatchingProcess)
							match_7.registerDelta(UsingDeltaMode.DELETE, objs[0]);
						start(edge_explorer_3_1_orchestration, StatelessInputType.OBJECT, match_7);
					}
				}
				break;
			case "DaughterToFemale_fm_sons_2_incoming_SOURCE__FILTER_NAC_SRC_33": 
				{
					var inputMatch = (StatelessDeltaMatch) match;
					{
						var match_0 = new StatelessDeltaMatch(msg, "DaughterToFemale__CONSISTENCY_49", numberOfNodes, 3, UsingDeltaMode.CREATE);
						match_0.registerInvocationMessage(msg, 0);
						match_0.getNodes()[2] = objs[1];
						
						for(var inputSignatureIndex : inputMatch.deltaSignatureBindingIndices) {
							switch(inputSignatureIndex) {
								case 1: break outer;
							}
						}
						
						// start search
						start(nac_orchestration, StatelessInputType.NAC, match_0);
					}
				}
				break;
			case "DaughterToFemale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC_36": 
				{
					var inputMatch = (StatelessDeltaMatch) match;
					{
						var match_1 = new StatelessDeltaMatch(msg, "DaughterToFemale__CONSISTENCY_49", numberOfNodes, 3, UsingDeltaMode.CREATE);
						match_1.registerInvocationMessage(msg, 1);
						match_1.getNodes()[2] = objs[1];
						
						for(var inputSignatureIndex : inputMatch.deltaSignatureBindingIndices) {
							switch(inputSignatureIndex) {
								case 1: break outer;
							}
						}
						
						// start search
						start(nac_0_orchestration, StatelessInputType.NAC, match_1);
					}
				}
				break;
			case "DaughterToFemale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC_39": 
				{
					var inputMatch = (StatelessDeltaMatch) match;
					{
						var match_2 = new StatelessDeltaMatch(msg, "DaughterToFemale__CONSISTENCY_49", numberOfNodes, 3, UsingDeltaMode.CREATE);
						match_2.registerInvocationMessage(msg, 2);
						match_2.getNodes()[2] = objs[1];
						
						for(var inputSignatureIndex : inputMatch.deltaSignatureBindingIndices) {
							switch(inputSignatureIndex) {
								case 1: break outer;
							}
						}
						
						// start search
						start(nac_1_orchestration, StatelessInputType.NAC, match_2);
					}
				}
				break;
			default: throw new RuntimeException("Detected unknown match from " + msg.input.creator);
		}
		
		msg.initialMessage.decrement();
	}
	
	@Override
	protected void addReference(ReferenceAdded msg) {
		initialMessage = msg.initialMessage;
		
		switch(msg.refName) {
		case "FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr_source_FamilyMember": 
			{
				{
					if(notificationIndex.isNew(msg.source) || notificationIndex.isNew(msg.target))
						break;
						
					var match = new StatelessDeltaMatch(msg, "DaughterToFemale__CONSISTENCY_49", numberOfNodes, 3, UsingDeltaMode.CREATE);
					Object[] objs = match.getNodes();
					objs[4] = msg.source;
					objs[2] = msg.target;
					if(options.trackMatchingProcess)
						match.registerDelta(UsingDeltaMode.CREATE, new ModelEdge(msg.source, msg.target, msg.refName));
					match.registerSignatureEdge(4, 2);
					start(edge_explorer_0_orchestration, StatelessInputType.EDGE, match);
				}
			}
			break;
		case "FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterToFemale_CREATE__SOURCE__f_Family": 
			{
				{
					if(notificationIndex.isNew(msg.source) || notificationIndex.isNew(msg.target))
						break;
						
					var match = new StatelessDeltaMatch(msg, "DaughterToFemale__CONSISTENCY_49", numberOfNodes, 3, UsingDeltaMode.CREATE);
					Object[] objs = match.getNodes();
					objs[7] = msg.source;
					objs[1] = msg.target;
					if(options.trackMatchingProcess)
						match.registerDelta(UsingDeltaMode.CREATE, new ModelEdge(msg.source, msg.target, msg.refName));
					match.registerSignatureEdge(7, 1);
					start(edge_explorer_3_0_orchestration, StatelessInputType.EDGE, match);
				}
			}
			break;
		case "FamiliesSmartEMF.FamilyRegister_families_Family": 
			{
				{
					if(notificationIndex.isNew(msg.source) || notificationIndex.isNew(msg.target))
						break;
						
					var match = new StatelessDeltaMatch(msg, "DaughterToFemale__CONSISTENCY_49", numberOfNodes, 3, UsingDeltaMode.CREATE);
					Object[] objs = match.getNodes();
					objs[0] = msg.source;
					objs[1] = msg.target;
					if(options.trackMatchingProcess)
						match.registerDelta(UsingDeltaMode.CREATE, new ModelEdge(msg.source, msg.target, msg.refName));
					match.registerSignatureEdge(0, 1);
					start(edge_explorer_4_0_orchestration, StatelessInputType.EDGE, match);
				}
			}
			break;
		case "FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterToFemale_CONTEXT__CORRESPONDENCE__families2persons_RegisterToRegisterCorr": 
			{
				{
					if(notificationIndex.isNew(msg.source) || notificationIndex.isNew(msg.target))
						break;
						
					var match = new StatelessDeltaMatch(msg, "DaughterToFemale__CONSISTENCY_49", numberOfNodes, 3, UsingDeltaMode.CREATE);
					Object[] objs = match.getNodes();
					objs[7] = msg.source;
					objs[3] = msg.target;
					if(options.trackMatchingProcess)
						match.registerDelta(UsingDeltaMode.CREATE, new ModelEdge(msg.source, msg.target, msg.refName));
					match.registerSignatureEdge(7, 3);
					start(edge_explorer_5_0_orchestration, StatelessInputType.EDGE, match);
				}
			}
			break;
		case "FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterToFemale_CONTEXT__SOURCE__families_FamilyRegister": 
			{
				{
					if(notificationIndex.isNew(msg.source) || notificationIndex.isNew(msg.target))
						break;
						
					var match = new StatelessDeltaMatch(msg, "DaughterToFemale__CONSISTENCY_49", numberOfNodes, 3, UsingDeltaMode.CREATE);
					Object[] objs = match.getNodes();
					objs[7] = msg.source;
					objs[0] = msg.target;
					if(options.trackMatchingProcess)
						match.registerDelta(UsingDeltaMode.CREATE, new ModelEdge(msg.source, msg.target, msg.refName));
					match.registerSignatureEdge(7, 0);
					start(edge_explorer_6_0_orchestration, StatelessInputType.EDGE, match);
				}
			}
			break;
		case "PersonsSmartEMF.PersonRegister_persons_Person": 
			{
				if(msg.target instanceof PersonsSmartEMF.Female) 
				{
					if(notificationIndex.isNew(msg.source) || notificationIndex.isNew(msg.target))
						break;
						
					var match = new StatelessDeltaMatch(msg, "DaughterToFemale__CONSISTENCY_49", numberOfNodes, 3, UsingDeltaMode.CREATE);
					Object[] objs = match.getNodes();
					objs[5] = msg.source;
					objs[6] = msg.target;
					if(options.trackMatchingProcess)
						match.registerDelta(UsingDeltaMode.CREATE, new ModelEdge(msg.source, msg.target, msg.refName));
					match.registerSignatureEdge(5, 6);
					start(edge_explorer_7_0_orchestration, StatelessInputType.EDGE, match);
				}
			}
			break;
		case "FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterToFemale_CREATE__CORRESPONDENCE__familyMember2Persons_FamilyMemberToPersonCorr": 
			{
				{
					if(notificationIndex.isNew(msg.source) || notificationIndex.isNew(msg.target))
						break;
						
					var match = new StatelessDeltaMatch(msg, "DaughterToFemale__CONSISTENCY_49", numberOfNodes, 3, UsingDeltaMode.CREATE);
					Object[] objs = match.getNodes();
					objs[7] = msg.source;
					objs[4] = msg.target;
					if(options.trackMatchingProcess)
						match.registerDelta(UsingDeltaMode.CREATE, new ModelEdge(msg.source, msg.target, msg.refName));
					match.registerSignatureEdge(7, 4);
					start(edge_explorer_8_0_orchestration, StatelessInputType.EDGE, match);
				}
			}
			break;
		case "FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterToFemale_CREATE__TARGET__p_Female": 
			{
				{
					if(notificationIndex.isNew(msg.source) || notificationIndex.isNew(msg.target))
						break;
						
					var match = new StatelessDeltaMatch(msg, "DaughterToFemale__CONSISTENCY_49", numberOfNodes, 3, UsingDeltaMode.CREATE);
					Object[] objs = match.getNodes();
					objs[7] = msg.source;
					objs[6] = msg.target;
					if(options.trackMatchingProcess)
						match.registerDelta(UsingDeltaMode.CREATE, new ModelEdge(msg.source, msg.target, msg.refName));
					match.registerSignatureEdge(7, 6);
					start(edge_explorer_9_0_orchestration, StatelessInputType.EDGE, match);
				}
			}
			break;
		case "FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterToFemale_CONTEXT__TARGET__persons_PersonRegister": 
			{
				{
					if(notificationIndex.isNew(msg.source) || notificationIndex.isNew(msg.target))
						break;
						
					var match = new StatelessDeltaMatch(msg, "DaughterToFemale__CONSISTENCY_49", numberOfNodes, 3, UsingDeltaMode.CREATE);
					Object[] objs = match.getNodes();
					objs[7] = msg.source;
					objs[5] = msg.target;
					if(options.trackMatchingProcess)
						match.registerDelta(UsingDeltaMode.CREATE, new ModelEdge(msg.source, msg.target, msg.refName));
					match.registerSignatureEdge(7, 5);
					start(edge_explorer_10_0_orchestration, StatelessInputType.EDGE, match);
				}
			}
			break;
		case "FamiliesSmartEMF.Family_daughters_FamilyMember": 
			{
				{
					if(notificationIndex.isNew(msg.source) || notificationIndex.isNew(msg.target))
						break;
						
					var match = new StatelessDeltaMatch(msg, "DaughterToFemale__CONSISTENCY_49", numberOfNodes, 3, UsingDeltaMode.CREATE);
					Object[] objs = match.getNodes();
					objs[1] = msg.source;
					objs[2] = msg.target;
					if(options.trackMatchingProcess)
						match.registerDelta(UsingDeltaMode.CREATE, new ModelEdge(msg.source, msg.target, msg.refName));
					match.registerSignatureEdge(1, 2);
					start(edge_explorer_11_0_orchestration, StatelessInputType.EDGE, match);
				}
			}
			break;
		case "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_target_PersonRegister": 
			{
				{
					if(notificationIndex.isNew(msg.source) || notificationIndex.isNew(msg.target))
						break;
						
					var match = new StatelessDeltaMatch(msg, "DaughterToFemale__CONSISTENCY_49", numberOfNodes, 3, UsingDeltaMode.CREATE);
					Object[] objs = match.getNodes();
					objs[3] = msg.source;
					objs[5] = msg.target;
					if(options.trackMatchingProcess)
						match.registerDelta(UsingDeltaMode.CREATE, new ModelEdge(msg.source, msg.target, msg.refName));
					match.registerSignatureEdge(3, 5);
					start(edge_explorer_12_0_orchestration, StatelessInputType.EDGE, match);
				}
			}
			break;
		case "FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterToFemale_CREATE__SOURCE__fm_FamilyMember": 
			{
				{
					if(notificationIndex.isNew(msg.source) || notificationIndex.isNew(msg.target))
						break;
						
					var match = new StatelessDeltaMatch(msg, "DaughterToFemale__CONSISTENCY_49", numberOfNodes, 3, UsingDeltaMode.CREATE);
					Object[] objs = match.getNodes();
					objs[7] = msg.source;
					objs[2] = msg.target;
					if(options.trackMatchingProcess)
						match.registerDelta(UsingDeltaMode.CREATE, new ModelEdge(msg.source, msg.target, msg.refName));
					match.registerSignatureEdge(7, 2);
					start(edge_explorer_13_0_orchestration, StatelessInputType.EDGE, match);
				}
			}
			break;
		case "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_source_FamilyRegister": 
			{
				{
					if(notificationIndex.isNew(msg.source) || notificationIndex.isNew(msg.target))
						break;
						
					var match = new StatelessDeltaMatch(msg, "DaughterToFemale__CONSISTENCY_49", numberOfNodes, 3, UsingDeltaMode.CREATE);
					Object[] objs = match.getNodes();
					objs[3] = msg.source;
					objs[0] = msg.target;
					if(options.trackMatchingProcess)
						match.registerDelta(UsingDeltaMode.CREATE, new ModelEdge(msg.source, msg.target, msg.refName));
					match.registerSignatureEdge(3, 0);
					start(edge_explorer_14_0_orchestration, StatelessInputType.EDGE, match);
				}
			}
			break;
		case "FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr_target_Person": 
			{
				if(msg.target instanceof PersonsSmartEMF.Female) 
				{
					if(notificationIndex.isNew(msg.source) || notificationIndex.isNew(msg.target))
						break;
						
					var match = new StatelessDeltaMatch(msg, "DaughterToFemale__CONSISTENCY_49", numberOfNodes, 3, UsingDeltaMode.CREATE);
					Object[] objs = match.getNodes();
					objs[4] = msg.source;
					objs[6] = msg.target;
					if(options.trackMatchingProcess)
						match.registerDelta(UsingDeltaMode.CREATE, new ModelEdge(msg.source, msg.target, msg.refName));
					match.registerSignatureEdge(4, 6);
					start(edge_explorer_15_0_orchestration, StatelessInputType.EDGE, match);
				}
			}
			break;
		}
		
		msg.initialMessage.decrement();
	}

	@Override
	protected void removeReference(ReferenceDeleted msg) {
		initialMessage = msg.initialMessage;
		
		switch(msg.refName) {
			case "FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr_source_FamilyMember": 
		{
			if(notificationIndex.isDeleted(msg.source) || notificationIndex.isDeleted(msg.target))
				break;
							
			var match = new StatelessDeltaMatch(msg, "DaughterToFemale__CONSISTENCY_49", numberOfNodes, 3, UsingDeltaMode.DELETE);
			Object[] objs = match.getNodes();
			objs[4] = msg.source;
			objs[2] = msg.target;
			if(options.trackMatchingProcess)
				match.registerDelta(UsingDeltaMode.DELETE, new ModelEdge(msg.source, msg.target, msg.refName));
			match.registerSignatureEdge(4, 2);
			start(edge_explorer_0_orchestration, StatelessInputType.EDGE, match);
		}
		break;
			case "FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterToFemale_CREATE__SOURCE__f_Family": 
		{
			if(notificationIndex.isDeleted(msg.source) || notificationIndex.isDeleted(msg.target))
				break;
							
			var match = new StatelessDeltaMatch(msg, "DaughterToFemale__CONSISTENCY_49", numberOfNodes, 3, UsingDeltaMode.DELETE);
			Object[] objs = match.getNodes();
			objs[7] = msg.source;
			objs[1] = msg.target;
			if(options.trackMatchingProcess)
				match.registerDelta(UsingDeltaMode.DELETE, new ModelEdge(msg.source, msg.target, msg.refName));
			match.registerSignatureEdge(7, 1);
			start(edge_explorer_3_0_orchestration, StatelessInputType.EDGE, match);
		}
		break;
			case "FamiliesSmartEMF.FamilyRegister_families_Family": 
		{
			if(notificationIndex.isDeleted(msg.source) || notificationIndex.isDeleted(msg.target))
				break;
							
			var match = new StatelessDeltaMatch(msg, "DaughterToFemale__CONSISTENCY_49", numberOfNodes, 3, UsingDeltaMode.DELETE);
			Object[] objs = match.getNodes();
			objs[0] = msg.source;
			objs[1] = msg.target;
			if(options.trackMatchingProcess)
				match.registerDelta(UsingDeltaMode.DELETE, new ModelEdge(msg.source, msg.target, msg.refName));
			match.registerSignatureEdge(0, 1);
			start(edge_explorer_4_0_orchestration, StatelessInputType.EDGE, match);
		}
		break;
			case "FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterToFemale_CONTEXT__CORRESPONDENCE__families2persons_RegisterToRegisterCorr": 
		{
			if(notificationIndex.isDeleted(msg.source) || notificationIndex.isDeleted(msg.target))
				break;
							
			var match = new StatelessDeltaMatch(msg, "DaughterToFemale__CONSISTENCY_49", numberOfNodes, 3, UsingDeltaMode.DELETE);
			Object[] objs = match.getNodes();
			objs[7] = msg.source;
			objs[3] = msg.target;
			if(options.trackMatchingProcess)
				match.registerDelta(UsingDeltaMode.DELETE, new ModelEdge(msg.source, msg.target, msg.refName));
			match.registerSignatureEdge(7, 3);
			start(edge_explorer_5_0_orchestration, StatelessInputType.EDGE, match);
		}
		break;
			case "FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterToFemale_CONTEXT__SOURCE__families_FamilyRegister": 
		{
			if(notificationIndex.isDeleted(msg.source) || notificationIndex.isDeleted(msg.target))
				break;
							
			var match = new StatelessDeltaMatch(msg, "DaughterToFemale__CONSISTENCY_49", numberOfNodes, 3, UsingDeltaMode.DELETE);
			Object[] objs = match.getNodes();
			objs[7] = msg.source;
			objs[0] = msg.target;
			if(options.trackMatchingProcess)
				match.registerDelta(UsingDeltaMode.DELETE, new ModelEdge(msg.source, msg.target, msg.refName));
			match.registerSignatureEdge(7, 0);
			start(edge_explorer_6_0_orchestration, StatelessInputType.EDGE, match);
		}
		break;
			case "PersonsSmartEMF.PersonRegister_persons_Person": 
		if(msg.target instanceof PersonsSmartEMF.Female) 
		{
			if(notificationIndex.isDeleted(msg.source) || notificationIndex.isDeleted(msg.target))
				break;
							
			var match = new StatelessDeltaMatch(msg, "DaughterToFemale__CONSISTENCY_49", numberOfNodes, 3, UsingDeltaMode.DELETE);
			Object[] objs = match.getNodes();
			objs[5] = msg.source;
			objs[6] = msg.target;
			if(options.trackMatchingProcess)
				match.registerDelta(UsingDeltaMode.DELETE, new ModelEdge(msg.source, msg.target, msg.refName));
			match.registerSignatureEdge(5, 6);
			start(edge_explorer_7_0_orchestration, StatelessInputType.EDGE, match);
		}
		break;
			case "FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterToFemale_CREATE__CORRESPONDENCE__familyMember2Persons_FamilyMemberToPersonCorr": 
		{
			if(notificationIndex.isDeleted(msg.source) || notificationIndex.isDeleted(msg.target))
				break;
							
			var match = new StatelessDeltaMatch(msg, "DaughterToFemale__CONSISTENCY_49", numberOfNodes, 3, UsingDeltaMode.DELETE);
			Object[] objs = match.getNodes();
			objs[7] = msg.source;
			objs[4] = msg.target;
			if(options.trackMatchingProcess)
				match.registerDelta(UsingDeltaMode.DELETE, new ModelEdge(msg.source, msg.target, msg.refName));
			match.registerSignatureEdge(7, 4);
			start(edge_explorer_8_0_orchestration, StatelessInputType.EDGE, match);
		}
		break;
			case "FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterToFemale_CREATE__TARGET__p_Female": 
		{
			if(notificationIndex.isDeleted(msg.source) || notificationIndex.isDeleted(msg.target))
				break;
							
			var match = new StatelessDeltaMatch(msg, "DaughterToFemale__CONSISTENCY_49", numberOfNodes, 3, UsingDeltaMode.DELETE);
			Object[] objs = match.getNodes();
			objs[7] = msg.source;
			objs[6] = msg.target;
			if(options.trackMatchingProcess)
				match.registerDelta(UsingDeltaMode.DELETE, new ModelEdge(msg.source, msg.target, msg.refName));
			match.registerSignatureEdge(7, 6);
			start(edge_explorer_9_0_orchestration, StatelessInputType.EDGE, match);
		}
		break;
			case "FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterToFemale_CONTEXT__TARGET__persons_PersonRegister": 
		{
			if(notificationIndex.isDeleted(msg.source) || notificationIndex.isDeleted(msg.target))
				break;
							
			var match = new StatelessDeltaMatch(msg, "DaughterToFemale__CONSISTENCY_49", numberOfNodes, 3, UsingDeltaMode.DELETE);
			Object[] objs = match.getNodes();
			objs[7] = msg.source;
			objs[5] = msg.target;
			if(options.trackMatchingProcess)
				match.registerDelta(UsingDeltaMode.DELETE, new ModelEdge(msg.source, msg.target, msg.refName));
			match.registerSignatureEdge(7, 5);
			start(edge_explorer_10_0_orchestration, StatelessInputType.EDGE, match);
		}
		break;
			case "FamiliesSmartEMF.Family_daughters_FamilyMember": 
		{
			if(notificationIndex.isDeleted(msg.source) || notificationIndex.isDeleted(msg.target))
				break;
							
			var match = new StatelessDeltaMatch(msg, "DaughterToFemale__CONSISTENCY_49", numberOfNodes, 3, UsingDeltaMode.DELETE);
			Object[] objs = match.getNodes();
			objs[1] = msg.source;
			objs[2] = msg.target;
			if(options.trackMatchingProcess)
				match.registerDelta(UsingDeltaMode.DELETE, new ModelEdge(msg.source, msg.target, msg.refName));
			match.registerSignatureEdge(1, 2);
			start(edge_explorer_11_0_orchestration, StatelessInputType.EDGE, match);
		}
		break;
			case "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_target_PersonRegister": 
		{
			if(notificationIndex.isDeleted(msg.source) || notificationIndex.isDeleted(msg.target))
				break;
							
			var match = new StatelessDeltaMatch(msg, "DaughterToFemale__CONSISTENCY_49", numberOfNodes, 3, UsingDeltaMode.DELETE);
			Object[] objs = match.getNodes();
			objs[3] = msg.source;
			objs[5] = msg.target;
			if(options.trackMatchingProcess)
				match.registerDelta(UsingDeltaMode.DELETE, new ModelEdge(msg.source, msg.target, msg.refName));
			match.registerSignatureEdge(3, 5);
			start(edge_explorer_12_0_orchestration, StatelessInputType.EDGE, match);
		}
		break;
			case "FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterToFemale_CREATE__SOURCE__fm_FamilyMember": 
		{
			if(notificationIndex.isDeleted(msg.source) || notificationIndex.isDeleted(msg.target))
				break;
							
			var match = new StatelessDeltaMatch(msg, "DaughterToFemale__CONSISTENCY_49", numberOfNodes, 3, UsingDeltaMode.DELETE);
			Object[] objs = match.getNodes();
			objs[7] = msg.source;
			objs[2] = msg.target;
			if(options.trackMatchingProcess)
				match.registerDelta(UsingDeltaMode.DELETE, new ModelEdge(msg.source, msg.target, msg.refName));
			match.registerSignatureEdge(7, 2);
			start(edge_explorer_13_0_orchestration, StatelessInputType.EDGE, match);
		}
		break;
			case "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_source_FamilyRegister": 
		{
			if(notificationIndex.isDeleted(msg.source) || notificationIndex.isDeleted(msg.target))
				break;
							
			var match = new StatelessDeltaMatch(msg, "DaughterToFemale__CONSISTENCY_49", numberOfNodes, 3, UsingDeltaMode.DELETE);
			Object[] objs = match.getNodes();
			objs[3] = msg.source;
			objs[0] = msg.target;
			if(options.trackMatchingProcess)
				match.registerDelta(UsingDeltaMode.DELETE, new ModelEdge(msg.source, msg.target, msg.refName));
			match.registerSignatureEdge(3, 0);
			start(edge_explorer_14_0_orchestration, StatelessInputType.EDGE, match);
		}
		break;
			case "FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr_target_Person": 
		if(msg.target instanceof PersonsSmartEMF.Female) 
		{
			if(notificationIndex.isDeleted(msg.source) || notificationIndex.isDeleted(msg.target))
				break;
							
			var match = new StatelessDeltaMatch(msg, "DaughterToFemale__CONSISTENCY_49", numberOfNodes, 3, UsingDeltaMode.DELETE);
			Object[] objs = match.getNodes();
			objs[4] = msg.source;
			objs[6] = msg.target;
			if(options.trackMatchingProcess)
				match.registerDelta(UsingDeltaMode.DELETE, new ModelEdge(msg.source, msg.target, msg.refName));
			match.registerSignatureEdge(4, 6);
			start(edge_explorer_15_0_orchestration, StatelessInputType.EDGE, match);
		}
		break;
		}
		
		msg.initialMessage.decrement();
	}
	
	@Override
	protected void changeAttribute(AttributeChanged message) {
		initialMessage = message.initialMessage;

		for(Port<?> port : allPorts) {
			message.initialMessage.increment();
			port.forwardMessage(message);
		}
		
		Object obj = message.node;
		if(obj instanceof PersonsSmartEMF.Female) {
			var match_6 = new StatelessDeltaMatch(initialMessage, "DaughterToFemale__CONSISTENCY_49", numberOfNodes, 3, UsingDeltaMode.ATTRIBUTE);
			match_6.registerSignatureIndex(6);
			match_6.getNodes()[6] = obj;
			start(edge_explorer_7_2_orchestration, StatelessInputType.ATTRIBUTE, match_6);
		}
		if(obj instanceof FamiliesSmartEMF.Family) {
			var match_1 = new StatelessDeltaMatch(initialMessage, "DaughterToFemale__CONSISTENCY_49", numberOfNodes, 3, UsingDeltaMode.ATTRIBUTE);
			match_1.registerSignatureIndex(1);
			match_1.getNodes()[1] = obj;
			start(edge_explorer_3_2_orchestration, StatelessInputType.ATTRIBUTE, match_1);
		}
		if(obj instanceof FamiliesSmartEMF.FamilyMember) {
			var match_2 = new StatelessDeltaMatch(initialMessage, "DaughterToFemale__CONSISTENCY_49", numberOfNodes, 3, UsingDeltaMode.ATTRIBUTE);
			match_2.registerSignatureIndex(2);
			match_2.getNodes()[2] = obj;
			start(edge_explorer_2_orchestration, StatelessInputType.ATTRIBUTE, match_2);
		}
		
		message.initialMessage.decrement();
	}
				
	private boolean constraint_checker_method(ConstraintCheck constraintCheck) {
		var f = (FamiliesSmartEMF.Family) constraintCheck.match().getNodes()[1];
		var fm = (FamiliesSmartEMF.FamilyMember) constraintCheck.match().getNodes()[2];
		var p = (PersonsSmartEMF.Female) constraintCheck.match().getNodes()[6];
		var f_name = f.getName();
		var fm_name = fm.getName();
		var p_name = p.getName();
		if(constraintCheck.useFormerValues()) {
			{
				var modelAttribute = new ModelAttribute(f, FamiliesSmartEMF.FamiliesSmartEMFPackage.eINSTANCE.getFamily_Name());
				var notification = notificationIndex.element2attributeChangeNotification.get(modelAttribute);
				if(notification != null) {
					f_name = notification.getOldStringValue();
				}
			}
			{
				var modelAttribute = new ModelAttribute(fm, FamiliesSmartEMF.FamiliesSmartEMFPackage.eINSTANCE.getFamilyMember_Name());
				var notification = notificationIndex.element2attributeChangeNotification.get(modelAttribute);
				if(notification != null) {
					fm_name = notification.getOldStringValue();
				}
			}
			{
				var modelAttribute = new ModelAttribute(p, PersonsSmartEMF.PersonsSmartEMFPackage.eINSTANCE.getPerson_Name());
				var notification = notificationIndex.element2attributeChangeNotification.get(modelAttribute);
				if(notification != null) {
					p_name = notification.getOldStringValue();
				}
			}
		}
		if(f_name == null  || fm_name == null || p_name == null) 
			return false;
		return constraint_checker_method(f_name, fm_name, p_name);
		
	}
	
	private boolean constraint_checker_method(String f_name, String fm_name, String p_name) {
		org.emoflon.ibex.tgg.runtime.csp.constraints.Concat csp_3 = new org.emoflon.ibex.tgg.runtime.csp.constraints.Concat();
		csp_3.getVariables().add(new org.emoflon.ibex.tgg.runtime.csp.RuntimeTGGAttributeConstraintVariable(true, ", ", "java.lang.String"));
		csp_3.getVariables().add(new org.emoflon.ibex.tgg.runtime.csp.RuntimeTGGAttributeConstraintVariable(true, f_name, "java.lang.String"));
		csp_3.getVariables().add(new org.emoflon.ibex.tgg.runtime.csp.RuntimeTGGAttributeConstraintVariable(true, fm_name, "java.lang.String"));
		csp_3.getVariables().add(new org.emoflon.ibex.tgg.runtime.csp.RuntimeTGGAttributeConstraintVariable(true, p_name, "java.lang.String"));
		csp_3.solve();
		boolean predicate = csp_3.isSatisfied();
		return predicate;
	}
	
	
	
	
	@Override
	protected void processMatchRequest(MatchRequest message) {
		initialMessage = message.initialMessage;
		
		var requestNodes = message.input.getNodes();
		
		
		message.initialMessage.decrement();
	}
	
	@Override
	protected boolean checkMatchRequestTypes(MatchRequest request, StatelessDeltaMatch match) {
		var objs = match.getNodes();
		return true;
	}
	
	@Override
	protected StatelessDeltaMatch constructMatchRequestAnswer(MatchRequest request, StatelessDeltaMatch result) {
		initialMessage = request.initialMessage;
		
		var resultNodes = result.getNodes();
		var requestCopy = request.input.copy();
		var requestNodes = requestCopy.getNodes();
		
		
		return requestCopy;
	}
}

