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

public class SonToMale__CC_200 extends GenericStatelessSearchActor{
	ConstraintChecker constraint_checker;
	DeltaAwareEdgeExplorer edge_explorer;
	DeltaAwareEdgeExplorer edge_explorer_3;
	DeltaAwareEdgeExplorer edge_explorer_4;
	DeltaAwareEdgeExplorer edge_explorer_5;
	DeltaAwareEdgeExplorer edge_explorer_6;
	NACQueryChecker nac;
	NACQueryChecker nac_0;
	NACQueryChecker nac_1;
	DisjointExplorer disjoint_explorer_4;
	
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
	SearchOrchestration<StatelessExplorer> nac_orchestration;
	SearchOrchestration<StatelessExplorer> nac_0_orchestration;
	SearchOrchestration<StatelessExplorer> nac_1_orchestration;
	
	@Override
	protected void initializeSearchComponents() {
		constraint_checker = new ConstraintChecker(this, this::constraint_checker_method);
		name2explorer.put("constraint_checker", constraint_checker);
		EdgeLookupMethods edge_explorer_methods = new EdgeLookupMethods();
						edge_explorer_methods.checkSourceType = (o) -> o instanceof FamiliesSmartEMF.Family;
						edge_explorer_methods.checkTargetType = (o) -> o instanceof FamiliesSmartEMF.FamilyMember;
						edge_explorer_methods.multi_lookup = (o) -> ((FamiliesSmartEMF.Family) o).getSons();
						edge_explorer_methods.unique_opposite_lookup = (o) -> ((FamiliesSmartEMF.FamilyMember) o).getSonsInverse();
						edge_explorer = new DeltaAwareEdgeExplorer(this, 1, 2, edge_explorer_methods, FamiliesSmartEMF.FamiliesSmartEMFPackage.eINSTANCE.getFamily_Sons());
		name2explorer.put("edge_explorer", edge_explorer);
		EdgeLookupMethods edge_explorer_3_methods = new EdgeLookupMethods();
						edge_explorer_3_methods.checkSourceType = (o) -> o instanceof PersonsSmartEMF.PersonRegister;
						edge_explorer_3_methods.checkTargetType = (o) -> o instanceof PersonsSmartEMF.Male;
						edge_explorer_3_methods.multi_lookup = (o) -> ((PersonsSmartEMF.PersonRegister) o).getPersons().stream().filter(obj -> obj instanceof PersonsSmartEMF.Male).collect(Collectors.toList());
						edge_explorer_3_methods.unique_opposite_lookup = (o) -> ((PersonsSmartEMF.Person) o).getPersonsInverse();
						edge_explorer_3 = new DeltaAwareEdgeExplorer(this, 4, 5, edge_explorer_3_methods, PersonsSmartEMF.PersonsSmartEMFPackage.eINSTANCE.getPersonRegister_Persons());
		name2explorer.put("edge_explorer_3", edge_explorer_3);
		EdgeLookupMethods edge_explorer_4_methods = new EdgeLookupMethods();
						edge_explorer_4_methods.checkSourceType = (o) -> o instanceof FamiliesSmartEMF.FamilyRegister;
						edge_explorer_4_methods.checkTargetType = (o) -> o instanceof FamiliesSmartEMF.Family;
						edge_explorer_4_methods.multi_lookup = (o) -> ((FamiliesSmartEMF.FamilyRegister) o).getFamilies();
						edge_explorer_4_methods.unique_opposite_lookup = (o) -> ((FamiliesSmartEMF.Family) o).getFamiliesInverse();
						edge_explorer_4 = new DeltaAwareEdgeExplorer(this, 0, 1, edge_explorer_4_methods, FamiliesSmartEMF.FamiliesSmartEMFPackage.eINSTANCE.getFamilyRegister_Families());
		name2explorer.put("edge_explorer_4", edge_explorer_4);
		EdgeLookupMethods edge_explorer_5_methods = new EdgeLookupMethods();
						edge_explorer_5_methods.checkSourceType = (o) -> o instanceof FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr;
						edge_explorer_5_methods.checkTargetType = (o) -> o instanceof FamiliesSmartEMF.FamilyRegister;
						edge_explorer_5_methods.unique_lookup = (o) -> ((FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) o).getSource();
						edge_explorer_5_methods.multi_opposite_lookup = (o) -> (Collection<? extends Object>) ((FamiliesSmartEMF.FamilyRegister) o).eGet(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getRegisterToRegisterCorr_Source().getEOpposite());
						edge_explorer_5 = new DeltaAwareEdgeExplorer(this, 3, 0, edge_explorer_5_methods, FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getRegisterToRegisterCorr_Source());
		name2explorer.put("edge_explorer_5", edge_explorer_5);
		EdgeLookupMethods edge_explorer_6_methods = new EdgeLookupMethods();
						edge_explorer_6_methods.checkSourceType = (o) -> o instanceof FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr;
						edge_explorer_6_methods.checkTargetType = (o) -> o instanceof PersonsSmartEMF.PersonRegister;
						edge_explorer_6_methods.unique_lookup = (o) -> ((FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) o).getTarget();
						edge_explorer_6_methods.multi_opposite_lookup = (o) -> (Collection<? extends Object>) ((PersonsSmartEMF.PersonRegister) o).eGet(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getRegisterToRegisterCorr_Target().getEOpposite());
						edge_explorer_6 = new DeltaAwareEdgeExplorer(this, 3, 4, edge_explorer_6_methods, FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getRegisterToRegisterCorr_Target());
		name2explorer.put("edge_explorer_6", edge_explorer_6);
		nac = new NACQueryChecker(this, 0, "SonToMale_fm_daughters_3_incoming_SOURCE__FILTER_NAC_SRC_191", name2actor.get("SonToMale_fm_daughters_3_incoming_SOURCE__FILTER_NAC_SRC_191"), 2);
		name2explorer.put("nac", nac);
		nac_0 = new NACQueryChecker(this, 1, "SonToMale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC_194", name2actor.get("SonToMale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC_194"), 2);
		name2explorer.put("nac_0", nac_0);
		nac_1 = new NACQueryChecker(this, 2, "SonToMale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC_197", name2actor.get("SonToMale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC_197"), 2);
		name2explorer.put("nac_1", nac_1);
		disjoint_explorer_4 = new DisjointExplorer(this, observedResources, 5, (o) -> o instanceof PersonsSmartEMF.Male, true);
		name2explorer.put("disjoint_explorer_4", disjoint_explorer_4);
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
		
		nac_orchestration = initializeOrchestration(node.getOrchestrations().get(11).getPlan());
		nac_orchestration.setFieldSetter(x -> nac_orchestration = (SearchOrchestration) x);
		nac_orchestration.setFieldGetter(() -> nac_orchestration);
		
		nac_0_orchestration = initializeOrchestration(node.getOrchestrations().get(12).getPlan());
		nac_0_orchestration.setFieldSetter(x -> nac_0_orchestration = (SearchOrchestration) x);
		nac_0_orchestration.setFieldGetter(() -> nac_0_orchestration);
		
		nac_1_orchestration = initializeOrchestration(node.getOrchestrations().get(13).getPlan());
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
		
		numberOfNodes = 6;
		hasLocalNodes = false;
	}
	
	@Override
	protected void added(MatchAdded<HMatch> msg) {
		initialMessage = msg.initialMessage;

		HMatch match = msg.input;
		Object[] objs = match.getNodes();
		outer: switch(match.creator) {
			case "FamilyRegister_object_SP3": 
				{
					if(!lazy_initialization) {
						{
							// families
							var match_0 = new StatelessDeltaMatch(msg, "SonToMale__CC_200", numberOfNodes, 3, UsingDeltaMode.CREATE);
							match_0.getNodes()[0] = objs[0];
							match_0.registerSignatureIndex(0);
							if(options.trackMatchingProcess)
								match_0.registerDelta(UsingDeltaMode.CREATE, objs[0]);
							start(edge_explorer_4_1_orchestration, StatelessInputType.OBJECT, match_0);
						}
					}
				}
				break;
			case "Family_object_SP4": 
				{
					if(!lazy_initialization) {
						{
							// f
							var match_1 = new StatelessDeltaMatch(msg, "SonToMale__CC_200", numberOfNodes, 3, UsingDeltaMode.CREATE);
							match_1.getNodes()[1] = objs[0];
							match_1.registerSignatureIndex(1);
							if(options.trackMatchingProcess)
								match_1.registerDelta(UsingDeltaMode.CREATE, objs[0]);
							start(edge_explorer_1_orchestration, StatelessInputType.OBJECT, match_1);
						}
					}
				}
				break;
			case "FamilyMember_object_SP5": 
				{
					if(!lazy_initialization) {
						{
							// fm
							var match_2 = new StatelessDeltaMatch(msg, "SonToMale__CC_200", numberOfNodes, 3, UsingDeltaMode.CREATE);
							match_2.getNodes()[2] = objs[0];
							match_2.registerSignatureIndex(2);
							if(options.trackMatchingProcess)
								match_2.registerDelta(UsingDeltaMode.CREATE, objs[0]);
							start(edge_explorer_2_orchestration, StatelessInputType.OBJECT, match_2);
						}
					}
				}
				break;
			case "RegisterToRegisterCorr_object_SP0": 
				{
					{
						// families2persons
						var match_3 = new StatelessDeltaMatch(msg, "SonToMale__CC_200", numberOfNodes, 3, UsingDeltaMode.CREATE);
						match_3.getNodes()[3] = objs[0];
						match_3.registerSignatureIndex(3);
						if(options.trackMatchingProcess)
							match_3.registerDelta(UsingDeltaMode.CREATE, objs[0]);
						start(edge_explorer_5_1_orchestration, StatelessInputType.OBJECT, match_3);
					}
				}
				break;
			case "PersonRegister_object_SP1": 
				{
					if(!lazy_initialization) {
						{
							// persons
							var match_4 = new StatelessDeltaMatch(msg, "SonToMale__CC_200", numberOfNodes, 3, UsingDeltaMode.CREATE);
							match_4.getNodes()[4] = objs[0];
							match_4.registerSignatureIndex(4);
							if(options.trackMatchingProcess)
								match_4.registerDelta(UsingDeltaMode.CREATE, objs[0]);
							start(edge_explorer_3_1_orchestration, StatelessInputType.OBJECT, match_4);
						}
					}
				}
				break;
			case "Male_object_SP0": 
				{
					if(!lazy_initialization) {
						{
							// p
							var match_5 = new StatelessDeltaMatch(msg, "SonToMale__CC_200", numberOfNodes, 3, UsingDeltaMode.CREATE);
							match_5.getNodes()[5] = objs[0];
							match_5.registerSignatureIndex(5);
							if(options.trackMatchingProcess)
								match_5.registerDelta(UsingDeltaMode.CREATE, objs[0]);
							start(edge_explorer_3_2_orchestration, StatelessInputType.OBJECT, match_5);
						}
					}
				}
				break;
			case "SonToMale_fm_daughters_3_incoming_SOURCE__FILTER_NAC_SRC_191": 
				{
					if(!lazy_initialization) {
						var inputMatch = (StatelessDeltaMatch) match;
						{
							var match_0 = new StatelessDeltaMatch(msg, "SonToMale__CC_200", numberOfNodes, 3, UsingDeltaMode.DELETE);
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
			case "SonToMale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC_194": 
				{
					if(!lazy_initialization) {
						var inputMatch = (StatelessDeltaMatch) match;
						{
							var match_1 = new StatelessDeltaMatch(msg, "SonToMale__CC_200", numberOfNodes, 3, UsingDeltaMode.DELETE);
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
			case "SonToMale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC_197": 
				{
					if(!lazy_initialization) {
						var inputMatch = (StatelessDeltaMatch) match;
						{
							var match_2 = new StatelessDeltaMatch(msg, "SonToMale__CC_200", numberOfNodes, 3, UsingDeltaMode.DELETE);
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
			case "FamilyRegister_object_SP3": 
				{
					{
						// families
						var match_0 = new StatelessDeltaMatch(msg, "SonToMale__CC_200", numberOfNodes, 3, UsingDeltaMode.DELETE);
						match_0.getNodes()[0] = objs[0];
						match_0.registerSignatureIndex(0);
						if(options.trackMatchingProcess)
							match_0.registerDelta(UsingDeltaMode.DELETE, objs[0]);
						start(edge_explorer_4_1_orchestration, StatelessInputType.OBJECT, match_0);
					}
				}
				break;
			case "Family_object_SP4": 
				{
					{
						// f
						var match_1 = new StatelessDeltaMatch(msg, "SonToMale__CC_200", numberOfNodes, 3, UsingDeltaMode.DELETE);
						match_1.getNodes()[1] = objs[0];
						match_1.registerSignatureIndex(1);
						if(options.trackMatchingProcess)
							match_1.registerDelta(UsingDeltaMode.DELETE, objs[0]);
						start(edge_explorer_1_orchestration, StatelessInputType.OBJECT, match_1);
					}
				}
				break;
			case "FamilyMember_object_SP5": 
				{
					{
						// fm
						var match_2 = new StatelessDeltaMatch(msg, "SonToMale__CC_200", numberOfNodes, 3, UsingDeltaMode.DELETE);
						match_2.getNodes()[2] = objs[0];
						match_2.registerSignatureIndex(2);
						if(options.trackMatchingProcess)
							match_2.registerDelta(UsingDeltaMode.DELETE, objs[0]);
						start(edge_explorer_2_orchestration, StatelessInputType.OBJECT, match_2);
					}
				}
				break;
			case "RegisterToRegisterCorr_object_SP0": 
				{
					{
						// families2persons
						var match_3 = new StatelessDeltaMatch(msg, "SonToMale__CC_200", numberOfNodes, 3, UsingDeltaMode.DELETE);
						match_3.getNodes()[3] = objs[0];
						match_3.registerSignatureIndex(3);
						if(options.trackMatchingProcess)
							match_3.registerDelta(UsingDeltaMode.DELETE, objs[0]);
						start(edge_explorer_5_1_orchestration, StatelessInputType.OBJECT, match_3);
					}
				}
				break;
			case "PersonRegister_object_SP1": 
				{
					{
						// persons
						var match_4 = new StatelessDeltaMatch(msg, "SonToMale__CC_200", numberOfNodes, 3, UsingDeltaMode.DELETE);
						match_4.getNodes()[4] = objs[0];
						match_4.registerSignatureIndex(4);
						if(options.trackMatchingProcess)
							match_4.registerDelta(UsingDeltaMode.DELETE, objs[0]);
						start(edge_explorer_3_1_orchestration, StatelessInputType.OBJECT, match_4);
					}
				}
				break;
			case "Male_object_SP0": 
				{
					{
						// p
						var match_5 = new StatelessDeltaMatch(msg, "SonToMale__CC_200", numberOfNodes, 3, UsingDeltaMode.DELETE);
						match_5.getNodes()[5] = objs[0];
						match_5.registerSignatureIndex(5);
						if(options.trackMatchingProcess)
							match_5.registerDelta(UsingDeltaMode.DELETE, objs[0]);
						start(edge_explorer_3_2_orchestration, StatelessInputType.OBJECT, match_5);
					}
				}
				break;
			case "SonToMale_fm_daughters_3_incoming_SOURCE__FILTER_NAC_SRC_191": 
				{
					var inputMatch = (StatelessDeltaMatch) match;
					{
						var match_0 = new StatelessDeltaMatch(msg, "SonToMale__CC_200", numberOfNodes, 3, UsingDeltaMode.CREATE);
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
			case "SonToMale_fm_father_0_incoming_SOURCE__FILTER_NAC_SRC_194": 
				{
					var inputMatch = (StatelessDeltaMatch) match;
					{
						var match_1 = new StatelessDeltaMatch(msg, "SonToMale__CC_200", numberOfNodes, 3, UsingDeltaMode.CREATE);
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
			case "SonToMale_fm_mother_1_incoming_SOURCE__FILTER_NAC_SRC_197": 
				{
					var inputMatch = (StatelessDeltaMatch) match;
					{
						var match_2 = new StatelessDeltaMatch(msg, "SonToMale__CC_200", numberOfNodes, 3, UsingDeltaMode.CREATE);
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
		case "FamiliesSmartEMF.Family_sons_FamilyMember": 
			{
				{
					if(notificationIndex.isNew(msg.source) || notificationIndex.isNew(msg.target))
						break;
						
					var match = new StatelessDeltaMatch(msg, "SonToMale__CC_200", numberOfNodes, 3, UsingDeltaMode.CREATE);
					Object[] objs = match.getNodes();
					objs[1] = msg.source;
					objs[2] = msg.target;
					if(options.trackMatchingProcess)
						match.registerDelta(UsingDeltaMode.CREATE, new ModelEdge(msg.source, msg.target, msg.refName));
					match.registerSignatureEdge(1, 2);
					start(edge_explorer_0_orchestration, StatelessInputType.EDGE, match);
				}
			}
			break;
		case "PersonsSmartEMF.PersonRegister_persons_Person": 
			{
				if(msg.target instanceof PersonsSmartEMF.Male) 
				{
					if(notificationIndex.isNew(msg.source) || notificationIndex.isNew(msg.target))
						break;
						
					var match = new StatelessDeltaMatch(msg, "SonToMale__CC_200", numberOfNodes, 3, UsingDeltaMode.CREATE);
					Object[] objs = match.getNodes();
					objs[4] = msg.source;
					objs[5] = msg.target;
					if(options.trackMatchingProcess)
						match.registerDelta(UsingDeltaMode.CREATE, new ModelEdge(msg.source, msg.target, msg.refName));
					match.registerSignatureEdge(4, 5);
					start(edge_explorer_3_0_orchestration, StatelessInputType.EDGE, match);
				}
			}
			break;
		case "FamiliesSmartEMF.FamilyRegister_families_Family": 
			{
				{
					if(notificationIndex.isNew(msg.source) || notificationIndex.isNew(msg.target))
						break;
						
					var match = new StatelessDeltaMatch(msg, "SonToMale__CC_200", numberOfNodes, 3, UsingDeltaMode.CREATE);
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
		case "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_source_FamilyRegister": 
			{
				{
					if(notificationIndex.isNew(msg.source) || notificationIndex.isNew(msg.target))
						break;
						
					var match = new StatelessDeltaMatch(msg, "SonToMale__CC_200", numberOfNodes, 3, UsingDeltaMode.CREATE);
					Object[] objs = match.getNodes();
					objs[3] = msg.source;
					objs[0] = msg.target;
					if(options.trackMatchingProcess)
						match.registerDelta(UsingDeltaMode.CREATE, new ModelEdge(msg.source, msg.target, msg.refName));
					match.registerSignatureEdge(3, 0);
					start(edge_explorer_5_0_orchestration, StatelessInputType.EDGE, match);
				}
			}
			break;
		case "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_target_PersonRegister": 
			{
				{
					if(notificationIndex.isNew(msg.source) || notificationIndex.isNew(msg.target))
						break;
						
					var match = new StatelessDeltaMatch(msg, "SonToMale__CC_200", numberOfNodes, 3, UsingDeltaMode.CREATE);
					Object[] objs = match.getNodes();
					objs[3] = msg.source;
					objs[4] = msg.target;
					if(options.trackMatchingProcess)
						match.registerDelta(UsingDeltaMode.CREATE, new ModelEdge(msg.source, msg.target, msg.refName));
					match.registerSignatureEdge(3, 4);
					start(edge_explorer_6_0_orchestration, StatelessInputType.EDGE, match);
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
			case "FamiliesSmartEMF.Family_sons_FamilyMember": 
		{
			if(notificationIndex.isDeleted(msg.source) || notificationIndex.isDeleted(msg.target))
				break;
							
			var match = new StatelessDeltaMatch(msg, "SonToMale__CC_200", numberOfNodes, 3, UsingDeltaMode.DELETE);
			Object[] objs = match.getNodes();
			objs[1] = msg.source;
			objs[2] = msg.target;
			if(options.trackMatchingProcess)
				match.registerDelta(UsingDeltaMode.DELETE, new ModelEdge(msg.source, msg.target, msg.refName));
			match.registerSignatureEdge(1, 2);
			start(edge_explorer_0_orchestration, StatelessInputType.EDGE, match);
		}
		break;
			case "PersonsSmartEMF.PersonRegister_persons_Person": 
		if(msg.target instanceof PersonsSmartEMF.Male) 
		{
			if(notificationIndex.isDeleted(msg.source) || notificationIndex.isDeleted(msg.target))
				break;
							
			var match = new StatelessDeltaMatch(msg, "SonToMale__CC_200", numberOfNodes, 3, UsingDeltaMode.DELETE);
			Object[] objs = match.getNodes();
			objs[4] = msg.source;
			objs[5] = msg.target;
			if(options.trackMatchingProcess)
				match.registerDelta(UsingDeltaMode.DELETE, new ModelEdge(msg.source, msg.target, msg.refName));
			match.registerSignatureEdge(4, 5);
			start(edge_explorer_3_0_orchestration, StatelessInputType.EDGE, match);
		}
		break;
			case "FamiliesSmartEMF.FamilyRegister_families_Family": 
		{
			if(notificationIndex.isDeleted(msg.source) || notificationIndex.isDeleted(msg.target))
				break;
							
			var match = new StatelessDeltaMatch(msg, "SonToMale__CC_200", numberOfNodes, 3, UsingDeltaMode.DELETE);
			Object[] objs = match.getNodes();
			objs[0] = msg.source;
			objs[1] = msg.target;
			if(options.trackMatchingProcess)
				match.registerDelta(UsingDeltaMode.DELETE, new ModelEdge(msg.source, msg.target, msg.refName));
			match.registerSignatureEdge(0, 1);
			start(edge_explorer_4_0_orchestration, StatelessInputType.EDGE, match);
		}
		break;
			case "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_source_FamilyRegister": 
		{
			if(notificationIndex.isDeleted(msg.source) || notificationIndex.isDeleted(msg.target))
				break;
							
			var match = new StatelessDeltaMatch(msg, "SonToMale__CC_200", numberOfNodes, 3, UsingDeltaMode.DELETE);
			Object[] objs = match.getNodes();
			objs[3] = msg.source;
			objs[0] = msg.target;
			if(options.trackMatchingProcess)
				match.registerDelta(UsingDeltaMode.DELETE, new ModelEdge(msg.source, msg.target, msg.refName));
			match.registerSignatureEdge(3, 0);
			start(edge_explorer_5_0_orchestration, StatelessInputType.EDGE, match);
		}
		break;
			case "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_target_PersonRegister": 
		{
			if(notificationIndex.isDeleted(msg.source) || notificationIndex.isDeleted(msg.target))
				break;
							
			var match = new StatelessDeltaMatch(msg, "SonToMale__CC_200", numberOfNodes, 3, UsingDeltaMode.DELETE);
			Object[] objs = match.getNodes();
			objs[3] = msg.source;
			objs[4] = msg.target;
			if(options.trackMatchingProcess)
				match.registerDelta(UsingDeltaMode.DELETE, new ModelEdge(msg.source, msg.target, msg.refName));
			match.registerSignatureEdge(3, 4);
			start(edge_explorer_6_0_orchestration, StatelessInputType.EDGE, match);
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
		if(obj instanceof FamiliesSmartEMF.Family) {
			var match_1 = new StatelessDeltaMatch(initialMessage, "SonToMale__CC_200", numberOfNodes, 3, UsingDeltaMode.ATTRIBUTE);
			match_1.registerSignatureIndex(1);
			match_1.getNodes()[1] = obj;
			start(edge_explorer_1_orchestration, StatelessInputType.ATTRIBUTE, match_1);
		}
		if(obj instanceof PersonsSmartEMF.Male) {
			var match_5 = new StatelessDeltaMatch(initialMessage, "SonToMale__CC_200", numberOfNodes, 3, UsingDeltaMode.ATTRIBUTE);
			match_5.registerSignatureIndex(5);
			match_5.getNodes()[5] = obj;
			start(edge_explorer_3_2_orchestration, StatelessInputType.ATTRIBUTE, match_5);
		}
		if(obj instanceof FamiliesSmartEMF.FamilyMember) {
			var match_2 = new StatelessDeltaMatch(initialMessage, "SonToMale__CC_200", numberOfNodes, 3, UsingDeltaMode.ATTRIBUTE);
			match_2.registerSignatureIndex(2);
			match_2.getNodes()[2] = obj;
			start(edge_explorer_2_orchestration, StatelessInputType.ATTRIBUTE, match_2);
		}
		
		message.initialMessage.decrement();
	}
				
	private boolean constraint_checker_method(ConstraintCheck constraintCheck) {
		var f = (FamiliesSmartEMF.Family) constraintCheck.match().getNodes()[1];
		var fm = (FamiliesSmartEMF.FamilyMember) constraintCheck.match().getNodes()[2];
		var p = (PersonsSmartEMF.Male) constraintCheck.match().getNodes()[5];
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
		org.emoflon.ibex.tgg.runtime.csp.constraints.Concat csp_14 = new org.emoflon.ibex.tgg.runtime.csp.constraints.Concat();
		csp_14.getVariables().add(new org.emoflon.ibex.tgg.runtime.csp.RuntimeTGGAttributeConstraintVariable(true, ", ", "java.lang.String"));
		csp_14.getVariables().add(new org.emoflon.ibex.tgg.runtime.csp.RuntimeTGGAttributeConstraintVariable(true, f_name, "java.lang.String"));
		csp_14.getVariables().add(new org.emoflon.ibex.tgg.runtime.csp.RuntimeTGGAttributeConstraintVariable(true, fm_name, "java.lang.String"));
		csp_14.getVariables().add(new org.emoflon.ibex.tgg.runtime.csp.RuntimeTGGAttributeConstraintVariable(true, p_name, "java.lang.String"));
		csp_14.solve();
		boolean predicate = csp_14.isSatisfied();
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

