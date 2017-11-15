package org.benchmarx.cpm.core;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.util.EcoreUtil;

import cpm.Activity;
import cpm.CPMNetwork;
import cpm.Event;

public class CPMHelper {
	public void createEmptyGantt2CPMProcedure(CPMNetwork net) {
		CPMBuilder builder = new CPMBuilder(net);
		builder.name("Gantt2CPM");
	}
	
	public void createEmptyItalyTankRush(CPMNetwork net) {
		CPMBuilder builder = new CPMBuilder(net);
		builder.name("ItalyTankRush");
	}
	
	public void changeIncrementalID(CPMNetwork net) {
		if ("changed".equals(net.getIncrementalID())) {
			net.setIncrementalID("changed again");
		} else {
			net.setIncrementalID("changed");
		}
	}
	
	public void createSimpleNetwork(CPMNetwork net) {
		CPMBuilder builder = new CPMBuilder(net);
		builder.events(6)
			.activity(1, 2, "A1", 3)
			.activity(2, 4, "A1->A2", 0)
			.activity(3, 4, "A2", 4)
			.activity(3, 5, "A2->A3", 2)
			.activity(5, 6, "A3", 5);
	}
	
	public void createCPM2GanttTestCases(CPMNetwork net) {
		CPMBuilder builder = new CPMBuilder(net);
		builder.name("Gantt2CPM")
		.events(2)
		.activity(1, 2, "Gantt2CPMTestCases", 5);
	}
	
	public void addCPM2GanttHelpers(CPMNetwork net) {
		//Precondition: createCPM2GanttTestCases
		CPMBuilder builder = new CPMBuilder(net);
		builder.events(4)
		.activity(3,4,"GanttHelper", 2)
		.activity(5,6,"CPMHelper", 2)
		.activity(1,6,"Gantt2CPMTestCases->CPMHelper", 4)
		.activity(1,3,"Gantt2CPMTestCases->GanttHelper", 0);
	}
	
	public void addCPM2GanttComparators(CPMNetwork net) {
		//Precondition: addCPM2GanttHelpers
		CPMBuilder builder = new CPMBuilder(net);
		builder.events(4)
		.activity(7,8,"GanttComparator", 3)
		.activity(9,10,"CPMComparator", 1)
		.activity(2,8,"Gantt2CPMTestCases->GanttComparator", 0)
		.activity(2,10,"Gantt2CPMTestCases->CPMComparator",0);
	}
	
	public void addCPM2GanttModels(CPMNetwork net) {
		//Precondition: addCPM2GanttComparators
		CPMBuilder builder = new CPMBuilder(net);
		builder.events(4)
		.activity(11,12,"GanttModel", 1)
		.activity(13,14,"CPMModel", 1)
		.activity(12,1,"GanttModel->Gantt2CPMTestCases", 1)
		.activity(14,1,"CPMModel->Gantt2CPMTestCases",2);
	}
	
	public void addCPM2GanttModelsToComparatorDependencies(CPMNetwork net) {
		//Precondition: addCPM2GanttModels
		CPMBuilder builder = new CPMBuilder(net);
		builder.activity(12,7,"GanttModel->GanttComparator", 3);
		builder.activity(14,9,"CPMModel->CPMComparator", 6);
	}
	
	public void deleteCPM2GanttModelsToComparatorDependencies(CPMNetwork net) {
		EcoreUtil.delete(findActivityByName("GanttModel->GanttComparator", net));
		EcoreUtil.delete(findActivityByName("CPMModel->CPMComparator", net));
	}
	
	public void deleteCPM2GanttHelpers(CPMNetwork net) {
		EcoreUtil.delete(findActivityByName("GanttHelper", net));
		EcoreUtil.delete(findActivityByName("CPMHelper", net));
		EcoreUtil.delete(findActivityByName("Gantt2CPMTestCases->CPMHelper", net));
		EcoreUtil.delete(findActivityByName("Gantt2CPMTestCases->GanttHelper", net));
		EcoreUtil.delete(findEventByNumber(3, net));
		EcoreUtil.delete(findEventByNumber(4, net));
		EcoreUtil.delete(findEventByNumber(5, net));
		EcoreUtil.delete(findEventByNumber(6, net));
	}
	
	public void changeCPM2GanttHelperToBuilder(CPMNetwork net) {
		findActivityByName("GanttHelper", net).setName("CPMBuilder");
		findActivityByName("CPMHelper", net).setName("GanttBuilder");
		findActivityByName("Gantt2CPMTestCases->GanttHelper", net).setName("Gantt2CPMTestCases->CPMBuilder");
		findActivityByName("Gantt2CPMTestCases->CPMHelper", net).setName("Gantt2CPMTestCases->GanttBuilder");
		
	}
	
	public void changeCPM2GanttModelDuration(CPMNetwork net) {
		findActivityByName("GanttModel", net).setDuration(0);
		findActivityByName("CPMModel", net).setDuration(4);
	}
	
	public void changeCPM2GanttTestCasesNameDuration(CPMNetwork net) {
		Activity a = findActivityByName("Gantt2CPMTestCases", net);
		a.setName("Tests");
		a.setDuration(4);
		Set<Activity> in = new HashSet<Activity>();
		in.addAll(a.getSourceEvent().getIncomingActivities());
		in.addAll(a.getTargetEvent().getIncomingActivities());
		in.remove(a);
		
		Set<Activity> out = new HashSet<Activity>();
		out.addAll(a.getSourceEvent().getOutgoingActivities());
		out.addAll(a.getTargetEvent().getOutgoingActivities());
		out.remove(a);
		for(Activity i : in) {
			i.setName(i.getName().substring(0,i.getName().indexOf("->")+2)+"Tests");
		}
		for(Activity o : out) {
			o.setName("Tests" + o.getName().substring(o.getName().indexOf("->")));
		}
		findActivityByName("Tests->CPMComparator",net).setDuration(1);
		findActivityByName("Tests->GanttComparator",net).setDuration(1);
	}
	
	public void changeCPM2GanttModelToComparatorDependencyTypeDurationTargetAndSource(CPMNetwork net) {
		Activity a = findActivityByName("GanttModel->GanttComparator", net);
		a.setName("CPMModel->CPMBuilder");
		a.setSourceEvent(findEventByNumber(13,net));
		a.setTargetEvent(findEventByNumber(3,net));
		a.setDuration(8);
	}
	
	public void changeEventNumbers(CPMNetwork net) {
		List<Integer> ints = net.getElements().stream().filter(Event.class::isInstance).map(Event.class::cast).map(e->-e.getNumber()).collect(Collectors.toList());
		Collections.shuffle(ints);
		
		net.getElements().stream().filter(Event.class::isInstance).map(Event.class::cast).forEach(e-> {
			e.setNumber(ints.remove(0));
		});
		return;
	}
	
	public void createSimpleTankRush(CPMNetwork net) {
		CPMBuilder builder = new CPMBuilder(net);
		builder.name("ItalyTankRush")
		
			.events(4)
			.activity(1, 2, "spam tanks", 8)
			.activity(3, 4, "win game", 1)
			
			.activity(2, 3, "spam tanks->win game", 180);
	}
	
	public void createComplexTankRush(CPMNetwork net) {
		CPMBuilder builder = new CPMBuilder(net);
		builder.name("ItalyTankRush")
		
			.events(8)
			.activity(1, 2, "build tankbase", 5)
			.activity(3, 4, "research m15", 75)
			.activity(5, 6, "spam tanks", 8)
			.activity(7, 8, "win game", 1)
			
			.activity(1, 3, "build tankbase->research m15", 6)
			.activity(3, 6, "research m15->spam tanks", 84)
			.activity(6, 7, "spam tanks->win game", 180)
			.activity(6, 8, "spam tanks->win game", 181);
	}
	
	public void idleDelta(CPMNetwork net) {
		
	}
	
	private Event findEventByNumber(int number, CPMNetwork net) {
		List<Event> result = net.getElements().stream()
				.filter(Event.class::isInstance)
				.map(Event.class::cast)
				.filter(e -> e.getNumber() == number)
				.collect(Collectors.toList());
			
			if (result.size() > 0) return result.get(0);
			else return null;
	}
	
	private Activity findActivityByName(String name, CPMNetwork net) {
		List<Activity> result = net.getElements().stream()
				.filter(Activity.class::isInstance)
				.map(Activity.class::cast)
				.filter(e -> e.getName().equals(name))
				.collect(Collectors.toList());
			
			if (result.size() > 0) return result.get(0);
			else return null;
	}
}
