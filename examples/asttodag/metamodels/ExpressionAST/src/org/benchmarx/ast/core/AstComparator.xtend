package org.benchmarx.ast.core

import static org.junit.Assert.*

import org.benchmarx.emf.Comparator

import ast.Model
import ast.Expression
import ast.Variable
import ast.Number
import ast.Operator

class AstComparator implements Comparator<Model> {
	def static modelToString(Model model) {
		return "AstModel " + expressionToString(model.expr)
	}
	
	override assertEquals(Model expected, Model actual) {
		assertTrue(modelToString(expected).startsWith("AstModel"))
		assertEquals(modelToString(expected), modelToString(actual))
	}
	
	def private static String expressionToString(Expression expression) {
		if(expression == null) {
			return "{ }"
		}
		if (expression instanceof Variable) {
			return "{" + expression.name + ", " + expression.incrementalID + "}"
		}
		
		if (expression instanceof Number) {
			return "{" + expression.value + ", " + expression.incrementalID + "}"
		}
		
		val operator = expression as Operator
		return "{" + operator.op + ", " + expression.incrementalID + ", "
				+ expressionToString(operator.left) + ", " + expressionToString(operator.right) + "}";
	}
}