package Algebra;

import java.util.Deque;

public class BinaryTree {
	public static Node root = new Node(null);
	public static int row; 
	
	public static boolean evaluate(Node node) {
		if (node == null) return false;
		if (node.data.getOperator() == null) {
			for (int i = 0; i < TruthTable.terms.length; i++) {
				if (node.data.getTerm() == TruthTable.terms[i]) {
					return TruthTable.tt[row][i];
				}
			}
			return false;
		} else {
			BooleanOperation calc;
			switch (node.data.getOperator()) {
			case "-": 
				calc = Operation::not;
				break;
				
			case "(":
				calc = Operation::bracket;
				break;
				
			case "and":
				calc = Operation::and;
				break;
				
			case "or":
				calc = Operation::or;
				break;
				
			case "xor":
				calc = Operation::xor;
				break;
				
			case "nor":
				calc = Operation::nor;
				break;
				
			case "nand":
				calc = Operation::nand;
				break;
				
			case "ifThen":
				calc = Operation::ifThen;
				break;
				
			case "implies":
				calc = Operation::implies;
				break;
				
			case "equals":
				calc = Operation::equals;
				break;
				
			default:
				calc = Operation::bracket;
			}
			return calc.operation(evaluate(node.left), evaluate(node.right));
		}
	}
	
	public static void add(Deque<Boolean> path, Term value) throws LeftRightException{
		Node child = new Node(value);
		Node parent = nodeFinder(path, root);
		child.parent = parent;
		if (parent.left == null) {
			parent.left = child;
        } else if (parent.right == null){
        	parent.right = child;
        } else {
        	throw new LeftRightException("Left and right side have an object");
        }
	}
	
	public static Node nodeFinder(Deque<Boolean> path, Node node) {
		if (path.size() == 1) return node;
		if (path.removeLast() == true) {
			return nodeFinder(path, node.left);
		} else {
			return nodeFinder(path, node.right);
		}
	}
}
