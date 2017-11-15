package org.benchmarx.ast.core;

import org.eclipse.emf.ecore.util.EcoreUtil;

import ast.ArithmeticOperator;
import ast.AstFactory;
import ast.Expression;
import ast.Model;
import ast.Number;
import ast.Operator;
import ast.Variable;

public class AstModelBuilder {
	enum Direction { UP, LEFT, RIGHT };
	
	public AstModelBuilder(Model m) {
		model = m;
		current = m.getExpr();
	}
	
	public AstModelBuilder navigate(Direction... directions) {
		if (current == null) {
			throw new IllegalStateException("Can't navigate in empty ast.");
		}
		
		for (Direction direction : directions) {
			if (direction == Direction.UP) {
				if (current.getLeftInverse() != null) {
					current = current.getLeftInverse();
				} else if (current.getRightInverse() != null) {
					current = current.getRightInverse();
				} else {
					throw new IllegalStateException("Can't navigate up from root.");
				}
			} else if (direction == Direction.LEFT) {
				current = ((Operator) current).getLeft();
			} else if (direction == Direction.RIGHT) {
				current = ((Operator) current).getRight();
			} else {
				throw new AssertionError("invalid Direction");
			}
		}
		return this;
	}
	
	public AstModelBuilder navigateToRoot() {
		if (current == null) {
			throw new IllegalStateException("Can't navigate in empty ast.");
		}
		
		while (current.getLeftInverse() != null || current.getRightInverse() != null) {
			if (current.getLeftInverse() != null) {
				current = current.getLeftInverse();
			} else {
				current = current.getRightInverse();
			}
		}
		
		return this;
	}
	
	public AstModelBuilder operator(Direction direction) {
		Operator operator = f.createOperator();		
		return expression(direction, operator);
	}
	
	public AstModelBuilder setOp(ArithmeticOperator op) {
		((Operator) current).setOp(op);
		return this;
	}
	
	public AstModelBuilder variable(Direction direction) {
		Variable variable = f.createVariable();		
		return expression(direction, variable);
	}
	
	public AstModelBuilder setName(String name) {
		((Variable) current).setName(name);
		return this;
	}
	
	public AstModelBuilder number(Direction direction) {
		Number number = f.createNumber();		
		return expression(direction, number);
	}
	
	public AstModelBuilder setValue(int value) {
		((Number) current).setValue(value);
		return this;
	}
	
	public AstModelBuilder delete() {
		if (current == null) {
			throw new IllegalStateException("Can't delete a node in empty ast.");
		}
		
		Expression delete = current;
		if (delete.getLeftInverse() == null && delete.getRightInverse() == null) {
			current = null;
		} else {
			navigate(Direction.UP);
		}
		EcoreUtil.delete(delete, true);
		
		return this;
	}
	
	private final Model model;
	private Expression current;
	private final AstFactory f = AstFactory.eINSTANCE;
	
	private AstModelBuilder expression(Direction direction, Expression expression) {	
		if (direction == Direction.UP) {
			model.setExpr(expression);
		} else if (direction == Direction.LEFT) {
			((Operator) current).setLeft(expression);
		} else if (direction == Direction.RIGHT) {
			((Operator) current).setRight(expression);
		} else {
			throw new AssertionError("invalid Direction");
		}
		current = expression;
		return this;
	}
}
