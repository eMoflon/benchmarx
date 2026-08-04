package FamiliesToPersonsIBeXTGG.integrate.hipe.engine.actor.stateless;

import java.util.stream.Collectors;

import org.emoflon.ibex.tgg.run.familiestopersonsibextgg.config.CachedDisjointExplorer;

import hipe.engine.actor.Port;
import hipe.engine.match.HMatch;
import hipe.engine.message.input.AttributeChanged;
import hipe.engine.message.input.ReferenceAdded;
import hipe.engine.message.input.ReferenceDeleted;
import hipe.engine.message.production.MatchAdded;
import hipe.engine.message.production.MatchDeleted;
import hipe.engine.message.stateless.MatchRequest;
import hipe.generic.actor.search.misc.EdgeLookupMethods;
import hipe.generic.actor.search.misc.ModelEdge;
import hipe.generic.actor.search.misc.SearchOrchestration;
import hipe.generic.actor.stateless.GenericStatelessSearchActor;
import hipe.generic.actor.stateless.StatelessDeltaMatch;
import hipe.generic.actor.stateless.StatelessInputType;
import hipe.generic.actor.stateless.enums.UsingDeltaMode;
import hipe.generic.actor.stateless.search.DeltaAwareEdgeExplorer;
import hipe.generic.actor.stateless.search.DisjointExplorer;

public class FatherOfExistingFamilyToMale__BWD_123 extends GenericStatelessSearchActor{
	DeltaAwareEdgeExplorer edge_explorer;
	DisjointExplorer disjoint_explorer;
	DisjointExplorer disjoint_explorer_1;
	
	SearchOrchestration edge_explorer_0_orchestration;
	SearchOrchestration edge_explorer_1_orchestration;
	SearchOrchestration edge_explorer_2_orchestration;
	SearchOrchestration disjoint_explorer_orchestration;
	
	@Override
	protected void initializeSearchComponents() {
		EdgeLookupMethods edge_explorer_methods = new EdgeLookupMethods();
						edge_explorer_methods.checkSourceType = (o) -> o instanceof PersonsSmartEMF.PersonRegister;
						edge_explorer_methods.checkTargetType = (o) -> o instanceof PersonsSmartEMF.Male;
						edge_explorer_methods.multi_lookup = (o) -> ((PersonsSmartEMF.PersonRegister) o).getPersons().stream().filter(obj -> obj instanceof PersonsSmartEMF.Male).collect(Collectors.toList());
						edge_explorer_methods.unique_opposite_lookup = (o) -> ((PersonsSmartEMF.Person) o).getPersonsInverse();
						edge_explorer = new DeltaAwareEdgeExplorer(this, 1, 2, edge_explorer_methods, PersonsSmartEMF.PersonsSmartEMFPackage.eINSTANCE.getPersonRegister_Persons());
		name2explorer.put("edge_explorer", edge_explorer);
		disjoint_explorer = new CachedDisjointExplorer(this, observedResources, 0, (o) -> o instanceof FamiliesSmartEMF.Family, true, FamiliesSmartEMF.Family.class);
		name2explorer.put("disjoint_explorer", disjoint_explorer);
		disjoint_explorer_1 = new CachedDisjointExplorer(this, observedResources, 2, (o) -> o instanceof PersonsSmartEMF.Male, true, PersonsSmartEMF.Male.class);
		name2explorer.put("disjoint_explorer_1", disjoint_explorer_1);
	}
	
	@Override
	protected void initializeOrchestration() {
		edge_explorer_0_orchestration = initializeOrchestration(node.getOrchestrations().get(0).getPlan());
		edge_explorer_1_orchestration = initializeOrchestration(node.getOrchestrations().get(1).getPlan());
		edge_explorer_2_orchestration = initializeOrchestration(node.getOrchestrations().get(2).getPlan());
		disjoint_explorer_orchestration = initializeOrchestration(node.getOrchestrations().get(3).getPlan());
		
		localNodeOrchestrations = new SearchOrchestration[1];
		localNodeOrchestrations[0] = initializeOrchestration(node.getLocalNodeOrchestration().get(0).getPlan());
		
		disjointOrchestration = initializeOrchestration(node.getDisjointOrchestration().getPlan());
	}
	
	@Override
	protected void initializePatternSpecifics() {
		super.initializePatternSpecifics();
		
		numberOfNodes = 3;
		hasLocalNodes = false;
	}
	
	@Override
	protected void added(MatchAdded<HMatch> msg) {
		initialMessage = msg.initialMessage;

		HMatch match = msg.input;
		Object[] objs = match.getNodes();
		outer: switch(match.creator) {
			case "Family_object_SP8": 
				{
					{
						// f
						var match_0 = new StatelessDeltaMatch(msg, "FatherOfExistingFamilyToMale__BWD_123", numberOfNodes, 0, UsingDeltaMode.CREATE);
						match_0.getNodes()[0] = objs[0];
						match_0.registerSignatureIndex(0);
						if(options.trackMatchingProcess)
							match_0.registerDelta(UsingDeltaMode.CREATE, objs[0]);
						start(disjoint_explorer_orchestration, StatelessInputType.OBJECT, match_0);
					}
				}
				break;
			case "PersonRegister_object_SP5": 
				{
					{
						// persons
						var match_1 = new StatelessDeltaMatch(msg, "FatherOfExistingFamilyToMale__BWD_123", numberOfNodes, 0, UsingDeltaMode.CREATE);
						match_1.getNodes()[1] = objs[0];
						match_1.registerSignatureIndex(1);
						if(options.trackMatchingProcess)
							match_1.registerDelta(UsingDeltaMode.CREATE, objs[0]);
						start(edge_explorer_1_orchestration, StatelessInputType.OBJECT, match_1);
					}
				}
				break;
			case "Male_object_SP1": 
				{
					{
						// p
						var match_2 = new StatelessDeltaMatch(msg, "FatherOfExistingFamilyToMale__BWD_123", numberOfNodes, 0, UsingDeltaMode.CREATE);
						match_2.getNodes()[2] = objs[0];
						match_2.registerSignatureIndex(2);
						if(options.trackMatchingProcess)
							match_2.registerDelta(UsingDeltaMode.CREATE, objs[0]);
						start(edge_explorer_2_orchestration, StatelessInputType.OBJECT, match_2);
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
			case "Family_object_SP8": 
				{
					{
						// f
						var match_0 = new StatelessDeltaMatch(msg, "FatherOfExistingFamilyToMale__BWD_123", numberOfNodes, 0, UsingDeltaMode.DELETE);
						match_0.getNodes()[0] = objs[0];
						match_0.registerSignatureIndex(0);
						if(options.trackMatchingProcess)
							match_0.registerDelta(UsingDeltaMode.DELETE, objs[0]);
						start(disjoint_explorer_orchestration, StatelessInputType.OBJECT, match_0);
					}
				}
				break;
			case "PersonRegister_object_SP5": 
				{
					{
						// persons
						var match_1 = new StatelessDeltaMatch(msg, "FatherOfExistingFamilyToMale__BWD_123", numberOfNodes, 0, UsingDeltaMode.DELETE);
						match_1.getNodes()[1] = objs[0];
						match_1.registerSignatureIndex(1);
						if(options.trackMatchingProcess)
							match_1.registerDelta(UsingDeltaMode.DELETE, objs[0]);
						start(edge_explorer_1_orchestration, StatelessInputType.OBJECT, match_1);
					}
				}
				break;
			case "Male_object_SP1": 
				{
					{
						// p
						var match_2 = new StatelessDeltaMatch(msg, "FatherOfExistingFamilyToMale__BWD_123", numberOfNodes, 0, UsingDeltaMode.DELETE);
						match_2.getNodes()[2] = objs[0];
						match_2.registerSignatureIndex(2);
						if(options.trackMatchingProcess)
							match_2.registerDelta(UsingDeltaMode.DELETE, objs[0]);
						start(edge_explorer_2_orchestration, StatelessInputType.OBJECT, match_2);
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
		case "PersonsSmartEMF.PersonRegister_persons_Person": 
			{
				if(msg.target instanceof PersonsSmartEMF.Male) 
				{
					if(notificationIndex.isNew(msg.source) || notificationIndex.isNew(msg.target))
						break;
						
					var match = new StatelessDeltaMatch(msg, "FatherOfExistingFamilyToMale__BWD_123", numberOfNodes, 0, UsingDeltaMode.CREATE);
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
		}
		
		msg.initialMessage.decrement();
	}

	@Override
	protected void removeReference(ReferenceDeleted msg) {
		initialMessage = msg.initialMessage;
		
		switch(msg.refName) {
			case "PersonsSmartEMF.PersonRegister_persons_Person": 
		if(msg.target instanceof PersonsSmartEMF.Male) 
		{
			if(notificationIndex.isDeleted(msg.source) || notificationIndex.isDeleted(msg.target))
				break;
							
			var match = new StatelessDeltaMatch(msg, "FatherOfExistingFamilyToMale__BWD_123", numberOfNodes, 0, UsingDeltaMode.DELETE);
			Object[] objs = match.getNodes();
			objs[1] = msg.source;
			objs[2] = msg.target;
			if(options.trackMatchingProcess)
				match.registerDelta(UsingDeltaMode.DELETE, new ModelEdge(msg.source, msg.target, msg.refName));
			match.registerSignatureEdge(1, 2);
			start(edge_explorer_0_orchestration, StatelessInputType.EDGE, match);
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

