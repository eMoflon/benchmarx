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

public class Families2Persons__BWD_107 extends GenericStatelessSearchActor{
	DisjointExplorer disjoint_explorer;
	
	SearchOrchestration<StatelessExplorer> singleNode_orchestration;
	
	@Override
	protected void initializeSearchComponents() {
		disjoint_explorer = new DisjointExplorer(this, observedResources, 0, (o) -> o instanceof PersonsSmartEMF.PersonRegister, true);
		name2explorer.put("disjoint_explorer", disjoint_explorer);
	}
	
	@Override
	protected void initializeOrchestration() {
		singleNode_orchestration = initializeOrchestration(node.getOrchestrations().get(0).getPlan());
		singleNode_orchestration.setFieldSetter(x -> singleNode_orchestration = (SearchOrchestration) x);
		singleNode_orchestration.setFieldGetter(() -> singleNode_orchestration);
		
		
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
		
		numberOfNodes = 1;
		hasLocalNodes = false;
	}
	
	@Override
	protected void added(MatchAdded<HMatch> msg) {
		initialMessage = msg.initialMessage;

		HMatch match = msg.input;
		Object[] objs = match.getNodes();
		outer: switch(match.creator) {
			case "PersonRegister_object_SP0": 
				{
					{
						// persons
						var match_0 = new StatelessDeltaMatch(msg, "Families2Persons__BWD_107", numberOfNodes, 0, UsingDeltaMode.CREATE);
						match_0.getNodes()[0] = objs[0];
						match_0.registerSignatureIndex(0);
						if(options.trackMatchingProcess)
							match_0.registerDelta(UsingDeltaMode.CREATE, objs[0]);
						start(singleNode_orchestration, StatelessInputType.OBJECT, match_0);
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
			case "PersonRegister_object_SP0": 
				{
					{
						// persons
						var match_0 = new StatelessDeltaMatch(msg, "Families2Persons__BWD_107", numberOfNodes, 0, UsingDeltaMode.DELETE);
						match_0.getNodes()[0] = objs[0];
						match_0.registerSignatureIndex(0);
						if(options.trackMatchingProcess)
							match_0.registerDelta(UsingDeltaMode.DELETE, objs[0]);
						start(singleNode_orchestration, StatelessInputType.OBJECT, match_0);
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
		}
		
		msg.initialMessage.decrement();
	}

	@Override
	protected void removeReference(ReferenceDeleted msg) {
		initialMessage = msg.initialMessage;
		
		switch(msg.refName) {
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

