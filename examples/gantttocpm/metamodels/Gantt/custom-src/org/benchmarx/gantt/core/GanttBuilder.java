package org.benchmarx.gantt.core;

import java.util.List;
import java.util.stream.Collectors;

import gantt.Activity;
import gantt.Dependency;
import gantt.DependencyType;
import gantt.GanttDiagram;
import gantt.GanttFactory;

/**
 * Builder class for easily creating Gantt Diagrams using a Java API
 * general assumption: unique names of Activities
 * @author tbuchmann
 *
 */
public class GanttBuilder {
	
	private final GanttDiagram diag;
	private final GanttFactory f = GanttFactory.eINSTANCE;
	
	public GanttBuilder(String name) {
		diag = f.createGanttDiagram();
		diag.setName(name);
	}
	
	public GanttBuilder(GanttDiagram diagram) {
		diag = diagram;
	}
	
	public GanttBuilder name(String name) {
		diag.setName(name);
		return this;
	}
	
	public GanttBuilder activity(String name, int duration) {
		Activity a = f.createActivity();
		a.setName(name);
		a.setDuration(duration);
		diag.getElements().add(a);
		return this;
	}
	
	public GanttBuilder startstart(String act1, String act2, int offset) {
		Dependency dep = createDependency(act1, act2, offset);
		dep.setDependencyType(DependencyType.START_START);
		return this;
	}
	
	public GanttBuilder startend(String act1, String act2, int offset) {
		Dependency dep = createDependency(act1, act2, offset);
		dep.setDependencyType(DependencyType.START_END);
		return this;
	}
	
	public GanttBuilder endstart(String act1, String act2, int offset) {
		Dependency dep = createDependency(act1, act2, offset);
		dep.setDependencyType(DependencyType.END_START);
		return this;
	}
	
	public GanttBuilder endend(String act1, String act2, int offset) {
		Dependency dep = createDependency(act1, act2, offset);
		dep.setDependencyType(DependencyType.END_END);
		return this;
	}
	
	public GanttDiagram end() {
		return diag;
	}
	
	private Dependency createDependency(String act1, String act2, int offset) {
		Activity a1 = findActivityByName(act1);
		Activity a2 = findActivityByName(act2);
		Dependency dep = f.createDependency();
		dep.setOffset(offset);
		dep.setPredecessor(a1);
		dep.setSuccessor(a2);
		diag.getElements().add(dep);
		return dep;
	}
	
	private Activity findActivityByName(String name) {
		List<Activity> result = diag.getElements().stream()
			.filter(Activity.class::isInstance)
			.map(Activity.class::cast)
			.filter(a -> a.getName().equals(name))
			.collect(Collectors.toList());
		
		if (result.size() > 0) return result.get(0);
		else {
			Activity a = f.createActivity();
			a.setName(name);
			diag.getElements().add(a);
			return a;
		}
	}
}
