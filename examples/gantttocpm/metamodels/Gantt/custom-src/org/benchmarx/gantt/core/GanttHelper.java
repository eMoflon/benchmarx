package org.benchmarx.gantt.core;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.util.EcoreUtil;

import gantt.Activity;
import gantt.Dependency;
import gantt.DependencyType;
import gantt.Element;
import gantt.GanttDiagram;

public class GanttHelper {
	public void createEmptyGantt2CPMProcedure(GanttDiagram diag) {
		GanttBuilder builder = new GanttBuilder(diag);
		builder.name("Gantt2CPM");
	}
	
	public void createEmptyItalyTankRush(GanttDiagram diag) {
		GanttBuilder builder = new GanttBuilder(diag);
		builder.name("ItalyTankRush");
	}
	
	public void createGantt2CPMTestCases(GanttDiagram diag) {
		GanttBuilder builder = new GanttBuilder(diag);
		builder.name("Gantt2CPM")
		.activity("Gantt2CPMTestCases", 5);
	}
	
	public void changeIncrementalID(GanttDiagram diag) {
		if ("changed".equals(diag.getIncrementalID())) {
			diag.setIncrementalID("changed again");
		} else {
			diag.setIncrementalID("changed");
		}
	}
	
	public void addGantt2CPMHelpers(GanttDiagram diag) {
		//Precondition: createGantt2CPMTestCases
		GanttBuilder builder = new GanttBuilder(diag);
		builder.activity("GanttHelper", 2)
		.activity("CPMHelper", 2)
		.startend("Gantt2CPMTestCases", "CPMHelper", 4)
		.startstart("Gantt2CPMTestCases", "GanttHelper", 0);
	}
	
	public void addGantt2CPMComparators(GanttDiagram diag) {
		//Precondition: createGantt2CPMTestCases
		GanttBuilder builder = new GanttBuilder(diag);
		builder.activity("GanttComparator", 3)
		.activity("CPMComparator", 1)
		.endend("Gantt2CPMTestCases", "GanttComparator", 0)
		.endend("Gantt2CPMTestCases","CPMComparator",0);
	}
	
	public void addGantt2CPMModels(GanttDiagram diag) {
		//Precondition: createGantt2CPMTestCases
		GanttBuilder builder = new GanttBuilder(diag);
		builder.activity("GanttModel", 1)
		.activity("CPMModel", 1)
		.endstart("GanttModel", "Gantt2CPMTestCases", 1)
		.endstart("CPMModel","Gantt2CPMTestCases",2);
	}
	
	public void addGantt2CPMModelsToComparatorDependencies(GanttDiagram diag) {
		//Precondition: addGantt2CPMModels, addGantt2CPMComparators
		GanttBuilder builder = new GanttBuilder(diag);
		builder.endstart("GanttModel", "GanttComparator", 3);
		builder.endstart("CPMModel", "CPMComparator", 6);
	}
	
	public void deleteGantt2CPMModelsToComparatorDependencies(GanttDiagram diag) {
		Activity ganttModel = findActivityByName("GanttModel", diag);
		Activity cpmModel = findActivityByName("CPMModel", diag);
		Set<Dependency> s = new HashSet<Dependency>();
		s.addAll(ganttModel.getIncomingDependencies());
		s.addAll(ganttModel.getOutgoingDependencies());
		s.addAll(cpmModel.getIncomingDependencies());
		s.addAll(cpmModel.getOutgoingDependencies());
		for(Dependency e : s) {
			if(e.getSuccessor().getName().equals("CPMComparator") || e.getSuccessor().getName().equals("GanttComparator"))
				EcoreUtil.delete(e,true);
		}
	}
	
	public void deleteGantt2CPMHelpers(GanttDiagram diag) {
		Activity a = findActivityByName("GanttHelper", diag);
		Activity b = findActivityByName("CPMHelper", diag);
		Set<Element> s = new HashSet<Element>();
		s.addAll(a.getIncomingDependencies());
		s.addAll(a.getOutgoingDependencies());
		s.addAll(b.getIncomingDependencies());
		s.addAll(b.getOutgoingDependencies());
		s.add(a); s.add(b);
		for(Element e : s) {
			EcoreUtil.delete(e,true);
		}
	}
	
	public void changeGantt2CPMHelperToBuilder(GanttDiagram diag) {
		findActivityByName("GanttHelper", diag).setName("CPMBuilder");
		findActivityByName("CPMHelper", diag).setName("GanttBuilder");
	}
	
	public void changeGantt2CPMModelDuration(GanttDiagram diag) {
		findActivityByName("GanttModel", diag).setDuration(0);
		findActivityByName("CPMModel", diag).setDuration(4);
	}
	
	public void changeGantt2CPMTestCasesNameDuration(GanttDiagram diag) {
		findActivityByName("Gantt2CPMTestCases", diag).setName("Tests");
		findActivityByName("Tests", diag).setDuration(4);
		List<Dependency> out = findActivityByName("Tests", diag).getOutgoingDependencies();
		for(Dependency d : out) {
			if(d.getSuccessor().getName().equals("CPMComparator") || d.getSuccessor().getName().equals("GanttComparator")) {
				d.setOffset(1);
			}
		}
	}
	
	public void changeGantt2CPMModelToComparatorDependencyTypeDurationTargetAndSource(GanttDiagram diag) {
		List<Dependency> out = new ArrayList<Dependency>();
		out.addAll(findActivityByName("GanttModel", diag).getOutgoingDependencies());
		for(Dependency d : out) {
			if(d.getSuccessor().getName().equals("GanttComparator")) {
				d.setDependencyType(DependencyType.START_START);
				d.setPredecessor(findActivityByName("CPMModel", diag));
				d.setSuccessor(findActivityByName("CPMBuilder", diag));
				d.setOffset(8);
			}
		}
	}
	
	public void createSimpleTankRush(GanttDiagram diag) {
		GanttBuilder builder = new GanttBuilder(diag);
		builder.name("ItalyTankRush")
		
			.activity("spam tanks", 8)
			.activity("win game", 1)
			
			.endstart("spam tanks", "win game", 180);
	}
	
	public void createComplexTankRush(GanttDiagram diag) {
		GanttBuilder builder = new GanttBuilder(diag);
		builder.name("ItalyTankRush")
		
			.activity("build tankbase", 5)
			.activity("research m15", 75)
			.activity("spam tanks", 8)
			.activity("win game", 1)
			
			.startstart("build tankbase", "research m15", 6)
			.startend("research m15", "spam tanks", 84)
			.endstart("spam tanks", "win game", 180)
			.endend("spam tanks", "win game", 181);
	}
	
	public void idleDelta(GanttDiagram diag) {
		
	}
	
	private Activity findActivityByName(String name, GanttDiagram diag) {
		List<Activity> result = diag.getElements().stream()
				.filter(Activity.class::isInstance)
				.map(Activity.class::cast)
				.filter(a -> a.getName().equals(name))
				.collect(Collectors.toList());
			
		if (result.size() > 0) return result.get(0);
		else return null;
	}
}
