package uk.ac.york.ttc.containers.astcounter;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseResult;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.comments.Comment;
import com.github.javaparser.ast.visitor.TreeVisitor;

public class JavaMeasurer implements IFileMeasurer {

	@Override
	public int measure(File t) throws IOException {
		FileInputStream fileInputStream = new FileInputStream(t);
		ParseResult<CompilationUnit> compilationUnit = new JavaParser().parse(fileInputStream);
		fileInputStream.close();

		if (compilationUnit.isSuccessful() && compilationUnit.getResult().isPresent()) {
			return countNodes(compilationUnit.getResult().get());
		} else {
			throw new IllegalArgumentException(t + " did not parse correctly");
		}
	}

	public int countNodes(Node node) {
		NodeCountVisitor visitor = new NodeCountVisitor();
		visitor.visitPreOrder(node);
		return visitor.getNodeCount();
	}

	private static class NodeCountVisitor extends TreeVisitor {
		private int nodeCount = 0;

		public int getNodeCount() {
			return nodeCount;
		}

		@Override
		public void process(Node node) {
			if (node instanceof Comment) {
				// ignore
				System.out.println("comment found");
			} else {
				nodeCount++;
			}
		}
	}
}
