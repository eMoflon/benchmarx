package org.benchmarx.dag.core;

import org.benchmarx.dag.core.DagModelBuilder.Direction;

import dag.ArithmeticOperator;
import dag.Number;
import dag.Model;

public class DagHelper {
	private DagModelBuilder builder = null;
	
	public void create42(Model model) {
		builder = new DagModelBuilder(model);
		builder.number(Direction.UP).setValue(42);
	}
	
	public void createTextSum(Model model) {
		builder = new DagModelBuilder(model);
		builder.operator(Direction.UP).setOp(ArithmeticOperator.ADD);
		builder.operator(Direction.LEFT).setOp(ArithmeticOperator.ADD);
		builder.variable(Direction.LEFT).setName("Answer to the Ultimate Question of Life, The Universe, and Everything");
		builder.navigate(Direction.UP).variable(Direction.RIGHT).setName("Deep Thought");
		builder.navigateToRoot().variable(Direction.RIGHT).setName("7.5 million years");
	}
	
	public void createComplexNumberExample(Model model) {
		builder = new DagModelBuilder(model);
		builder.operator(Direction.UP).setOp(ArithmeticOperator.ADD);
		builder.operator(Direction.LEFT).setOp(ArithmeticOperator.SUBTRACT);
		builder.operator(Direction.LEFT).setOp(ArithmeticOperator.MULTIPLY);
		builder.operator(Direction.LEFT).setOp(ArithmeticOperator.ADD);
		builder.number(Direction.LEFT).setValue(10).saveReference("10").navigate(Direction.UP);
		builder.number(Direction.RIGHT).setValue(1).saveReference("1").navigate(Direction.UP, Direction.UP);
		builder.operator(Direction.RIGHT).setOp(ArithmeticOperator.DIVIDE).saveReference("2");
		builder.reference(Direction.LEFT, "10").navigate(Direction.UP);
		builder.number(Direction.RIGHT).setValue(5).saveReference("5").navigate(Direction.UP, Direction.UP, Direction.UP);
		builder.reference(Direction.RIGHT, "1").navigateToRoot();
		
		builder.operator(Direction.RIGHT).setOp(ArithmeticOperator.SUBTRACT);
		builder.operator(Direction.LEFT).setOp(ArithmeticOperator.MULTIPLY);
		builder.operator(Direction.RIGHT).setOp(ArithmeticOperator.ADD);
		builder.reference(Direction.RIGHT, "10").navigate(Direction.UP);
		builder.reference(Direction.LEFT, "1").navigate(Direction.UP, Direction.UP);
		builder.reference(Direction.LEFT, "2").setOp(ArithmeticOperator.DIVIDE).navigate(Direction.UP, Direction.UP);
		builder.reference(Direction.RIGHT, "1").navigateToRoot();
	}
	
	public void createMulitpleSubtrees(Model model) {
		builder = new DagModelBuilder(model);
		builder.operator(Direction.UP).setOp(ArithmeticOperator.SUBTRACT)
			.operator(Direction.LEFT).setOp(ArithmeticOperator.MULTIPLY)
				.operator(Direction.LEFT).setOp(ArithmeticOperator.ADD).saveReference("7")
					.operator(Direction.LEFT).setOp(ArithmeticOperator.ADD).saveReference("2")
						.number(Direction.LEFT).setValue(1).saveReference("1").navigate(Direction.UP)
						.reference(Direction.RIGHT, "1").navigate(Direction.UP, Direction.UP)
					.operator(Direction.RIGHT).setOp(ArithmeticOperator.ADD)
						.reference(Direction.LEFT, "1").navigate(Direction.UP)
						.operator(Direction.RIGHT).setOp(ArithmeticOperator.ADD)
							.reference(Direction.LEFT, "2").navigate(Direction.UP)
							.reference(Direction.RIGHT, "2").navigateToRoot().navigate(Direction.LEFT)
				.reference(Direction.RIGHT, "7").navigateToRoot()
			.operator(Direction.RIGHT).setOp(ArithmeticOperator.DIVIDE)
				.operator(Direction.LEFT).setOp(ArithmeticOperator.ADD)
					.reference(Direction.LEFT, "7").navigate(Direction.UP)
					.reference(Direction.RIGHT, "7").navigate(Direction.UP, Direction.UP)
				.reference(Direction.RIGHT, "2").navigateToRoot();
	}
	
	public void createBestDigit(Model model) {
		builder = new DagModelBuilder(model);
		builder.operator(Direction.UP).setOp(ArithmeticOperator.SUBTRACT)
			.operator(Direction.LEFT).setOp(ArithmeticOperator.MULTIPLY)
				.number(Direction.LEFT).setValue(7).saveReference("7").navigate(Direction.UP)
				.variable(Direction.RIGHT).setName("sieben").navigate(Direction.UP, Direction.UP)
			.reference(Direction.RIGHT, "7").navigateToRoot();
	}
	
	public void insertMoreBestDigits(Model model) { // in BestDigit
		builder = new DagModelBuilder(model);
		builder.navigate(Direction.LEFT).saveReference("7*sieben").navigateToRoot();
		builder.navigate(Direction.RIGHT).delete().operator(Direction.RIGHT).setOp(ArithmeticOperator.SUBTRACT)
			.reference(Direction.LEFT, "7*sieben").navigate(Direction.UP)
			.variable(Direction.RIGHT).setName("zweiundvierzig").navigateToRoot();
	}
	
	public void createBestDigitRef(Model model) {
		builder = new DagModelBuilder(model);
		builder.operator(Direction.UP).setOp(ArithmeticOperator.ADD)
			.operator(Direction.LEFT).setOp(ArithmeticOperator.SUBTRACT)
				.operator(Direction.LEFT).setOp(ArithmeticOperator.MULTIPLY)
					.number(Direction.LEFT).setValue(7).saveReference("7").navigate(Direction.UP)
					.variable(Direction.RIGHT).setName("sieben").navigate(Direction.UP, Direction.UP)
				.reference(Direction.RIGHT, "7").navigate(Direction.UP, Direction.UP)
			.operator(Direction.RIGHT).setOp(ArithmeticOperator.MULTIPLY)
				.operator(Direction.LEFT).setOp(ArithmeticOperator.SUBTRACT)
					.number(Direction.LEFT).setValue(14).saveReference("14").navigate(Direction.UP)
					.reference(Direction.RIGHT, "14").navigate(Direction.UP, Direction.UP)
				.operator(Direction.RIGHT).setOp(ArithmeticOperator.DIVIDE)
					.reference(Direction.LEFT, "7").navigate(Direction.UP)
					.number(Direction.RIGHT).setValue(2).navigateToRoot();
	}
	
	public void modifyBestDigitRef(Model model) { // in BestDigitRef
		builder = new DagModelBuilder(model);
		builder.navigate(Direction.LEFT)
			.setOp(ArithmeticOperator.MULTIPLY)
				.navigate(Direction.LEFT).setOp(ArithmeticOperator.SUBTRACT)
					.navigate(Direction.LEFT).delete().number(Direction.LEFT).setValue(8).navigate(Direction.UP)
					.navigate(Direction.RIGHT).setName("zwei").navigate(Direction.UP, Direction.UP, Direction.UP)
			.navigate(Direction.RIGHT)
				.navigate(Direction.LEFT)
					.navigate(Direction.RIGHT).saveReference("14").navigate(Direction.UP, Direction.UP)
				.navigate(Direction.RIGHT).setOp(ArithmeticOperator.MULTIPLY)
					.navigate(Direction.LEFT).delete().reference(Direction.LEFT, "14").navigate(Direction.UP)
					.navigate(Direction.RIGHT).delete().reference(Direction.RIGHT, "14").navigateToRoot();
	}
	
	public void createMoreBestDigits(Model model) {
		createBestDigit(model);
		insertMoreBestDigits(model);
	}
	
	public void removeSomeBestDigits(Model model) { // in MoreBestDigits
		builder = new DagModelBuilder(model);
		builder.navigate(Direction.LEFT).navigate(Direction.LEFT).saveReference("7").navigateToRoot();
		builder.navigate(Direction.RIGHT).delete().navigateToRoot();
		builder.reference(Direction.RIGHT, "7").navigateToRoot();
	}
	
	public void changeIncrementalID(Model model) {
		model.getExprs().stream().forEach(e -> e.setIncrementalID("incrTestValue"));
	}
	
	public void changeIncrementalIDOf8(Model model) {
		model.getExprs().stream().filter(Number.class::isInstance).map(Number.class::cast).filter(n -> n.getValue() == 8).forEach(n -> n.setIncrementalID("incrTestValue8"));
	}
	
//	public void createStillBestDigit(Model model) {
//		builder = new DagModelBuilder(model);
//		builder.operator(Direction.UP).setOp(ArithmeticOperator.ADD)
//			.operator(Direction.LEFT).setOp(ArithmeticOperator.SUBTRACT)
//				.operator(Direction.LEFT).setOp(ArithmeticOperator.MULTIPLY).saveReference("7*sieben")
//					.number(Direction.LEFT).setValue(7).navigate(Direction.UP)
//					.variable(Direction.RIGHT).setName("sieben").navigate(Direction.UP, Direction.UP)
//				.reference(Direction.RIGHT, "7*sieben").navigate(Direction.UP)
//			.variable(Direction.RIGHT).setName("zweiundvierzig").navigateToRoot();
//	}
	
	public void idleDelta(Model model) {	
	}
}
