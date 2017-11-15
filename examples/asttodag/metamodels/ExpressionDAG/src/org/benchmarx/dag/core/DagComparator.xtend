package org.benchmarx.dag.core

import java.util.LinkedHashMap

import static org.junit.Assert.*

import org.benchmarx.emf.Comparator

import dag.Model
import dag.Expression
import dag.Number
import dag.Variable
import dag.Operator

class DagComparator implements Comparator<Model> {
	def static modelToString(Model model) {
		var Expression root;
		for (Expression expression : model.exprs) {
			if (expression.leftInverse.empty && expression.rightInverse.empty) {
				root = expression;
			}
		}
		
		if (root == null) {
			//return "invalid DagModel: no root!"
			return "DagModel { }"
		}
		
		val String result = "DagModel " + expressionToString(root)
		
		for (Expression expression : model.exprs) {
			if (!expressionPreoderIds.keySet.contains(expression)) {
				return "invalid DagModel: multiple trees!"
			}
		}
		
		return result;
	}
	
	override assertEquals(Model expected, Model actual) {
		assertTrue(modelToString(expected).startsWith("DagModel"))
		assertEquals(modelToString(expected), modelToString(actual))
	}
	
	var private static int nextPreorderId;
	var private static LinkedHashMap<Expression, Integer> expressionPreoderIds;
	
	def private static String expressionToString(Expression expression) {
		nextPreorderId = 0
		expressionPreoderIds = new LinkedHashMap<Expression, Integer>()
		return expressionToStringHelper(expression)
	}
	
	def private static String expressionToStringHelper(Expression expression) {
		if (expressionPreoderIds.get(expression) != null) {
			return "{@" + expressionPreoderIds.get(expression) + "}"
		}
		val int id = nextPreorderId++;
		expressionPreoderIds.put(expression, id)
		
		if (expression instanceof Variable) {
			return "{id: " + id + ", " + expression.name + ", " + expression.incrementalID +"}"
		}
		
		if (expression instanceof Number) {
			return "{id: " + id + ", " + expression.value + ", " + expression.incrementalID + "}"
		}
		
		val operator = expression as Operator
		return "{id: " + id + ", " + operator.op + ", " + expression.incrementalID + ", "
				+ expressionToStringHelper(operator.left) + ", " + expressionToStringHelper(operator.right) + "}";
	}
}