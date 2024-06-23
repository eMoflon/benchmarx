package FamiliesToPersonsIBeXTGG.integrate.hipe.engine.actor.stateless;

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

public class SonToMale__BWD_357 extends GenericStatelessSearchActor{
	DeltaAwareEdgeExplorer edge_explorer;
	DeltaAwareEdgeExplorer edge_explorer_3;
	DeltaAwareEdgeExplorer edge_explorer_4;
	DisjointExplorer disjoint_explorer_2;
	
	SearchOrchestration<StatelessExplorer> edge_explorer_0_orchestration;
	SearchOrchestration<StatelessExplorer> edge_explorer_1_orchestration;
	SearchOrchestration<StatelessExplorer> edge_explorer_2_orchestration;
	SearchOrchestration<StatelessExplorer> edge_explorer_3_0_orchestration;
	SearchOrchestration<StatelessExplorer> edge_explorer_3_1_orchestration;
	SearchOrchestration<StatelessExplorer> edge_explorer_3_2_orchestration;
	SearchOrchestration<StatelessExplorer> edge_explorer_4_0_orchestration;
	
	@Override
	protected void initializeSearchComponents() {
		EdgeLookupMethods edge_explorer_methods = new EdgeLookupMethods();
						edge_explorer_methods.checkSourceType = (o) -> o instanceof FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr;
						edge_explorer_methods.checkTargetType = (o) -> o instanceof FamiliesSmartEMF.FamilyRegister;
						edge_explorer_methods.unique_lookup = (o) -> ((FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) o).getSource();
						edge_explorer_methods.multi_opposite_lookup = (o) -> (Collection<? extends Object>) ((FamiliesSmartEMF.FamilyRegister) o).eGet(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getRegisterToRegisterCorr_Source().getEOpposite());
						edge_explorer = new DeltaAwareEdgeExplorer(this, 1, 0, edge_explorer_methods, FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getRegisterToRegisterCorr_Source());
		name2explorer.put("edge_explorer", edge_explorer);
		EdgeLookupMethods edge_explorer_3_methods = new EdgeLookupMethods();
						edge_explorer_3_methods.checkSourceType = (o) -> o instanceof PersonsSmartEMF.PersonRegister;
						edge_explorer_3_methods.checkTargetType = (o) -> o instanceof PersonsSmartEMF.Male;
						edge_explorer_3_methods.multi_lookup = (o) -> ((PersonsSmartEMF.PersonRegister) o).getPersons().stream().filter(obj -> obj instanceof PersonsSmartEMF.Male).collect(Collectors.toList());
						edge_explorer_3_methods.unique_opposite_lookup = (o) -> ((PersonsSmartEMF.Person) o).getPersonsInverse();
						edge_explorer_3 = new DeltaAwareEdgeExplorer(this, 2, 3, edge_explorer_3_methods, PersonsSmartEMF.PersonsSmartEMFPackage.eINSTANCE.getPersonRegister_Persons());
		name2explorer.put("edge_explorer_3", edge_explorer_3);
		EdgeLookupMethods edge_explorer_4_methods = new EdgeLookupMethods();
						edge_explorer_4_methods.checkSourceType = (o) -> o instanceof FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr;
						edge_explorer_4_methods.checkTargetType = (o) -> o instanceof PersonsSmartEMF.PersonRegister;
						edge_explorer_4_methods.unique_lookup = (o) -> ((FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) o).getTarget();
						edge_explorer_4_methods.multi_opposite_lookup = (o) -> (Collection<? extends Object>) ((PersonsSmartEMF.PersonRegister) o).eGet(FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getRegisterToRegisterCorr_Target().getEOpposite());
						edge_explorer_4 = new DeltaAwareEdgeExplorer(this, 1, 2, edge_explorer_4_methods, FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage.eINSTANCE.getRegisterToRegisterCorr_Target());
		name2explorer.put("edge_explorer_4", edge_explorer_4);
		disjoint_explorer_2 = new DisjointExplorer(this, observedResources, 3, (o) -> o instanceof PersonsSmartEMF.Male, true);
		name2explorer.put("disjoint_explorer_2", disjoint_explorer_2);
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
		
		numberOfNodes = 4;
		hasLocalNodes = false;
	}
	
	@Override
	protected void added(MatchAdded<HMatch> msg) {
		initialMessage = msg.initialMessage;

		HMatch match = msg.input;
		Object[] objs = match.getNodes();
		outer: switch(match.creator) {
			case "FamilyRegister_object_SP9": 
				{
					if(!lazy_initialization) {
						{
							// families
							var match_0 = new StatelessDeltaMatch(msg, "SonToMale__BWD_357", numberOfNodes, 0, UsingDeltaMode.CREATE);
							match_0.getNodes()[0] = objs[0];
							match_0.registerSignatureIndex(0);
							if(options.trackMatchingProcess)
								match_0.registerDelta(UsingDeltaMode.CREATE, objs[0]);
							start(edge_explorer_2_orchestration, StatelessInputType.OBJECT, match_0);
						}
					}
				}
				break;
			case "RegisterToRegisterCorr_object_SP3": 
				{
					{
						// families2persons
						var match_1 = new StatelessDeltaMatch(msg, "SonToMale__BWD_357", numberOfNodes, 0, UsingDeltaMode.CREATE);
						match_1.getNodes()[1] = objs[0];
						match_1.registerSignatureIndex(1);
						if(options.trackMatchingProcess)
							match_1.registerDelta(UsingDeltaMode.CREATE, objs[0]);
						start(edge_explorer_1_orchestration, StatelessInputType.OBJECT, match_1);
					}
				}
				break;
			case "PersonRegister_object_SP8": 
				{
					if(!lazy_initialization) {
						{
							// persons
							var match_2 = new StatelessDeltaMatch(msg, "SonToMale__BWD_357", numberOfNodes, 0, UsingDeltaMode.CREATE);
							match_2.getNodes()[2] = objs[0];
							match_2.registerSignatureIndex(2);
							if(options.trackMatchingProcess)
								match_2.registerDelta(UsingDeltaMode.CREATE, objs[0]);
							start(edge_explorer_3_1_orchestration, StatelessInputType.OBJECT, match_2);
						}
					}
				}
				break;
			case "Male_object_SP1": 
				{
					if(!lazy_initialization) {
						{
							// p
							var match_3 = new StatelessDeltaMatch(msg, "SonToMale__BWD_357", numberOfNodes, 0, UsingDeltaMode.CREATE);
							match_3.getNodes()[3] = objs[0];
							match_3.registerSignatureIndex(3);
							if(options.trackMatchingProcess)
								match_3.registerDelta(UsingDeltaMode.CREATE, objs[0]);
							start(edge_explorer_3_2_orchestration, StatelessInputType.OBJECT, match_3);
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
			case "FamilyRegister_object_SP9": 
				{
					{
						// families
						var match_0 = new StatelessDeltaMatch(msg, "SonToMale__BWD_357", numberOfNodes, 0, UsingDeltaMode.DELETE);
						match_0.getNodes()[0] = objs[0];
						match_0.registerSignatureIndex(0);
						if(options.trackMatchingProcess)
							match_0.registerDelta(UsingDeltaMode.DELETE, objs[0]);
						start(edge_explorer_2_orchestration, StatelessInputType.OBJECT, match_0);
					}
				}
				break;
			case "RegisterToRegisterCorr_object_SP3": 
				{
					{
						// families2persons
						var match_1 = new StatelessDeltaMatch(msg, "SonToMale__BWD_357", numberOfNodes, 0, UsingDeltaMode.DELETE);
						match_1.getNodes()[1] = objs[0];
						match_1.registerSignatureIndex(1);
						if(options.trackMatchingProcess)
							match_1.registerDelta(UsingDeltaMode.DELETE, objs[0]);
						start(edge_explorer_1_orchestration, StatelessInputType.OBJECT, match_1);
					}
				}
				break;
			case "PersonRegister_object_SP8": 
				{
					{
						// persons
						var match_2 = new StatelessDeltaMatch(msg, "SonToMale__BWD_357", numberOfNodes, 0, UsingDeltaMode.DELETE);
						match_2.getNodes()[2] = objs[0];
						match_2.registerSignatureIndex(2);
						if(options.trackMatchingProcess)
							match_2.registerDelta(UsingDeltaMode.DELETE, objs[0]);
						start(edge_explorer_3_1_orchestration, StatelessInputType.OBJECT, match_2);
					}
				}
				break;
			case "Male_object_SP1": 
				{
					{
						// p
						var match_3 = new StatelessDeltaMatch(msg, "SonToMale__BWD_357", numberOfNodes, 0, UsingDeltaMode.DELETE);
						match_3.getNodes()[3] = objs[0];
						match_3.registerSignatureIndex(3);
						if(options.trackMatchingProcess)
							match_3.registerDelta(UsingDeltaMode.DELETE, objs[0]);
						start(edge_explorer_3_2_orchestration, StatelessInputType.OBJECT, match_3);
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
		case "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_source_FamilyRegister": 
			{
				{
					if(notificationIndex.isNew(msg.source) || notificationIndex.isNew(msg.target))
						break;
						
					var match = new StatelessDeltaMatch(msg, "SonToMale__BWD_357", numberOfNodes, 0, UsingDeltaMode.CREATE);
					Object[] objs = match.getNodes();
					objs[1] = msg.source;
					objs[0] = msg.target;
					if(options.trackMatchingProcess)
						match.registerDelta(UsingDeltaMode.CREATE, new ModelEdge(msg.source, msg.target, msg.refName));
					match.registerSignatureEdge(1, 0);
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
						
					var match = new StatelessDeltaMatch(msg, "SonToMale__BWD_357", numberOfNodes, 0, UsingDeltaMode.CREATE);
					Object[] objs = match.getNodes();
					objs[2] = msg.source;
					objs[3] = msg.target;
					if(options.trackMatchingProcess)
						match.registerDelta(UsingDeltaMode.CREATE, new ModelEdge(msg.source, msg.target, msg.refName));
					match.registerSignatureEdge(2, 3);
					start(edge_explorer_3_0_orchestration, StatelessInputType.EDGE, match);
				}
			}
			break;
		case "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_target_PersonRegister": 
			{
				{
					if(notificationIndex.isNew(msg.source) || notificationIndex.isNew(msg.target))
						break;
						
					var match = new StatelessDeltaMatch(msg, "SonToMale__BWD_357", numberOfNodes, 0, UsingDeltaMode.CREATE);
					Object[] objs = match.getNodes();
					objs[1] = msg.source;
					objs[2] = msg.target;
					if(options.trackMatchingProcess)
						match.registerDelta(UsingDeltaMode.CREATE, new ModelEdge(msg.source, msg.target, msg.refName));
					match.registerSignatureEdge(1, 2);
					start(edge_explorer_4_0_orchestration, StatelessInputType.EDGE, match);
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
			case "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_source_FamilyRegister": 
		{
			if(notificationIndex.isDeleted(msg.source) || notificationIndex.isDeleted(msg.target))
				break;
							
			var match = new StatelessDeltaMatch(msg, "SonToMale__BWD_357", numberOfNodes, 0, UsingDeltaMode.DELETE);
			Object[] objs = match.getNodes();
			objs[1] = msg.source;
			objs[0] = msg.target;
			if(options.trackMatchingProcess)
				match.registerDelta(UsingDeltaMode.DELETE, new ModelEdge(msg.source, msg.target, msg.refName));
			match.registerSignatureEdge(1, 0);
			start(edge_explorer_0_orchestration, StatelessInputType.EDGE, match);
		}
		break;
			case "PersonsSmartEMF.PersonRegister_persons_Person": 
		if(msg.target instanceof PersonsSmartEMF.Male) 
		{
			if(notificationIndex.isDeleted(msg.source) || notificationIndex.isDeleted(msg.target))
				break;
							
			var match = new StatelessDeltaMatch(msg, "SonToMale__BWD_357", numberOfNodes, 0, UsingDeltaMode.DELETE);
			Object[] objs = match.getNodes();
			objs[2] = msg.source;
			objs[3] = msg.target;
			if(options.trackMatchingProcess)
				match.registerDelta(UsingDeltaMode.DELETE, new ModelEdge(msg.source, msg.target, msg.refName));
			match.registerSignatureEdge(2, 3);
			start(edge_explorer_3_0_orchestration, StatelessInputType.EDGE, match);
		}
		break;
			case "FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr_target_PersonRegister": 
		{
			if(notificationIndex.isDeleted(msg.source) || notificationIndex.isDeleted(msg.target))
				break;
							
			var match = new StatelessDeltaMatch(msg, "SonToMale__BWD_357", numberOfNodes, 0, UsingDeltaMode.DELETE);
			Object[] objs = match.getNodes();
			objs[1] = msg.source;
			objs[2] = msg.target;
			if(options.trackMatchingProcess)
				match.registerDelta(UsingDeltaMode.DELETE, new ModelEdge(msg.source, msg.target, msg.refName));
			match.registerSignatureEdge(1, 2);
			start(edge_explorer_4_0_orchestration, StatelessInputType.EDGE, match);
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
		
		
		message.initialMessage.decrement();
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

