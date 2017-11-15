package org.benchmarx.dag.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;

import dag.ArithmeticOperator;
import dag.DagFactory;
import dag.Expression;
import dag.Model;
import dag.Number;
import dag.Operator;
import dag.Variable;

public class DagModelBuilder {
	enum Direction { UP, LEFT, RIGHT };
	
	public DagModelBuilder(Model m) {
		model = m;
		current = m.getExprs().isEmpty() ? null : m.getExprs().get(0);
		if(current != null)
		while (!current.getLeftInverse().isEmpty() || !current.getRightInverse().isEmpty()) {
			if (!current.getLeftInverse().isEmpty()) {
				current = current.getLeftInverse().get(0);
			} else {
				current = current.getRightInverse().get(0);
			}
		}
		savedReferences = new HashMap<>();
		successors = new Stack<>();
	}
	
	public DagModelBuilder navigate(Direction... directions) {
		if (current == null) {
			throw new IllegalStateException("Can't navigate in empty dag.");
		}
		
		for (Direction direction : directions) {
			if (direction == Direction.UP) {
				if (!successors.isEmpty()) {
					current = successors.pop();
				} else {
					throw new IllegalStateException("Can't navigate up from root.");
				}
			} else if (direction == Direction.LEFT) {
				successors.push(current);
				current = ((Operator) current).getLeft();
			} else if (direction == Direction.RIGHT) {
				successors.push(current);
				current = ((Operator) current).getRight();
			} else {
				throw new AssertionError("invalid Direction");
			}
		}
		return this;
	}
	
	public DagModelBuilder navigateToRoot() {
		if (current == null) {
			throw new IllegalStateException("Can't navigate in empty ast.");
		}
		
		while (!successors.isEmpty()) {
			current = successors.pop();
		}
		
		return this;
	}
	
	public DagModelBuilder operator(Direction direction) {
		Operator operator = f.createOperator();		
		return expression(direction, operator);
	}
	
	public DagModelBuilder setOp(ArithmeticOperator op) {
		((Operator) current).setOp(op);
		return this;
	}
	
	public DagModelBuilder variable(Direction direction) {
		Variable variable = f.createVariable();		
		return expression(direction, variable);
	}
	
	public DagModelBuilder setName(String name) {
		((Variable) current).setName(name);
		return this;
	}
	
	public DagModelBuilder number(Direction direction) {
		Number number = f.createNumber();		
		return expression(direction, number);
	}
	
	public DagModelBuilder setValue(int value) {
		((Number) current).setValue(value);
		return this;
	}
	
	public DagModelBuilder saveReference(String key) {
		if (current == null) {
			throw new IllegalStateException("Can't save reference in empty dag.");
		}
		
		savedReferences.put(key, current);
		return this;
	}
	
	public DagModelBuilder reference(Direction direction, String key) {
		Expression reference = savedReferences.get(key);
		if (reference == null) {
			throw new IllegalArgumentException("No reference saved at key: " + key);
		}
		
		return expression(direction, reference);
	}
	
	public DagModelBuilder delete() {
		if (current == null) {
			throw new IllegalStateException("Can't delete a node in empty dag.");
		}
		
		Expression delete = current;
		if (delete.getLeftInverse().isEmpty() && delete.getRightInverse().isEmpty()) {
			current = null;
		} else {
			delete.getLeftInverse().remove(successors.peek());
			delete.getRightInverse().remove(successors.peek());
			navigate(Direction.UP);
		}
		if (delete.getLeftInverse().isEmpty() && delete.getRightInverse().isEmpty()) {
			List<Expression> unreferencedExpressions = new ArrayList<>();
			unreferencedExpressions.add(delete);
			while (!unreferencedExpressions.isEmpty()) {
				Expression unreferenced = unreferencedExpressions.remove(0);
				Expression left = null;
				Expression right = null;
				if (unreferenced instanceof Operator) {
					left = ((Operator) unreferenced).getLeft();
					right = ((Operator) unreferenced).getRight();
				}
				EcoreUtil.delete(unreferenced, true);
				if (left != null && left.getLeftInverse().isEmpty() && left.getRightInverse().isEmpty()) {
					unreferencedExpressions.add(left);
				}
				if (right != null && right.getLeftInverse().isEmpty() && right.getRightInverse().isEmpty()) {
					unreferencedExpressions.add(right);
				}
			}
			
			for (Iterator<Expression> it = savedReferences.values().iterator(); it.hasNext(); ) {
				Expression reference = it.next();
				boolean referencePresent = false;
				for (EObject element : (Iterable<EObject>) () -> model.eAllContents()) {
					if (reference == element) {
						referencePresent = true;					
					}
				}
				if (!referencePresent) {
					it.remove();
				}
			}
		}
		
		return this;
	}
	
	private final Model model;
	private Expression current;
	private HashMap<String, Expression> savedReferences;
	private Stack<Expression> successors;
	private final DagFactory f = DagFactory.eINSTANCE;
	
	private DagModelBuilder expression(Direction direction, Expression expression) {
		if (direction == Direction.UP) {
			model.getExprs().clear();
			successors.clear();
			savedReferences.clear();
			model.getExprs().add(expression);
		} else if (direction == Direction.LEFT) {
			((Operator) current).setLeft(expression);
			model.getExprs().add(expression);
			successors.push(current);
		} else if (direction == Direction.RIGHT) {
			((Operator) current).setRight(expression);
			model.getExprs().add(expression);
			successors.push(current);
		} else {
			throw new AssertionError("invalid Direction");
		}
		current = expression;
		return this;
	}
}
