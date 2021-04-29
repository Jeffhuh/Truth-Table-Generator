package Algebra;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.NoSuchElementException;
import java.util.Set;

public class TruthTable { 
	private static final String[] OPERATORS = { "xor", "equals", "implies", "if","nor","nand","or", "and","-"};
	public static Deque<Boolean> path = new ArrayDeque<>();
	public static char[] terms;
	public static boolean[][] tt;
	String exp; 
	
	public TruthTable(String s) throws LeftRightException {
		exp = s;
		s = s.replace(" ","");
		Deque<Character> stack = new ArrayDeque<>();
		boolean b = false;
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '(') {
				stack.offerFirst('(');
			} else if (s.charAt(i) == ')') {
				try {
					stack.removeFirst();
				} catch (NoSuchElementException e) {
					b = true;
					break;
				}
				
			}
		}
		if (stack.size() != 0 || b) {
			System.out.println("Close the brackets");
			System.exit(0);
		}
		s = s.replace("(","").replace(")","");
		for (int i = 0; i < OPERATORS.length; i++) {
			s = s.replace(OPERATORS[i], "");
		}
		char[] chars = s.toCharArray();
		Set<Character> charSet = new LinkedHashSet<Character>();
		for (char c : chars) {
		    charSet.add(c);
		}

		StringBuilder sb = new StringBuilder();
		for (Character character : charSet) {
		    sb.append(character);
		}
		terms = sb.toString().toCharArray();
		permutation();
		createBinaryTree(exp, true);
	}
	
	public static Deque<Boolean> getDuplicatePath() {
		Deque<Boolean> clone = new ArrayDeque<>();
		Iterator<Boolean> iter = path.descendingIterator();
		while (iter.hasNext()) {
			if (iter.next() == true) {
				clone.offerFirst(true);
			} else {
				clone.offerFirst(false);
			}
		}
		return clone;
	}
	
	private void permutation() {
		int kb = (int)Math.pow(2, terms.length);
		tt = new boolean[kb][terms.length+1];
		for (int i = 0; i < kb; i++) {
			int c = i;
			int k = 0;
			int j = (kb/2);
			while (j != 0) { 
				if (c / j == 1) {
					tt[i][k] = true;
					c = c % j;
				}
				k++;
				j /=2;
			}
		}
	}
	
	private void createBinaryTree(String subExp, boolean direction) throws LeftRightException {
		path.offerFirst(direction);
		subExp = subExp.trim();
		String op = operators(subExp);
		switch (op) {
			case "-": 
				BinaryTree.add(getDuplicatePath(), new Term("-"));
				break;
				
			case "(":
				BinaryTree.add(getDuplicatePath(), new Term("("));
				break;
				
			case "and":
				BinaryTree.add(getDuplicatePath(), new Term("and"));
				break;
				
			case "or":
				BinaryTree.add(getDuplicatePath(), new Term("or"));
				break;
				
			case "xor":
				BinaryTree.add(getDuplicatePath(), new Term("xor"));
				break;
				
			case "nor":
				BinaryTree.add(getDuplicatePath(), new Term("nor"));
				break;
				
			case "nand":
				BinaryTree.add(getDuplicatePath(), new Term("nand"));
				break;
				
			case "if":
				BinaryTree.add(getDuplicatePath(), new Term("ifThen"));
				break;
				
			case "implies":
				BinaryTree.add(getDuplicatePath(), new Term("implies"));
				break;
				
			case "equals":
				BinaryTree.add(getDuplicatePath(), new Term("equals"));
				break;
				
			default: 
				for (int i = 0; i < terms.length; i++) {
					if (op.charAt(0) == terms[i]) {
						BinaryTree.add(getDuplicatePath(), new Term(terms[i], false));
						path.removeFirst();
						return;
					}
				}
		}
		int ix = indexOfOperator(subExp, op);
		if (ix == -2) {
			String left = subExp.substring(1, subExp.length()-1);
			createBinaryTree(left, true);
		} else if (!"-".equals(op)){
			String left = subExp.substring(0, ix);
			String right = subExp.substring(ix+op.length(), subExp.length());
			createBinaryTree(left, true);
			createBinaryTree(right, false);
		} else {
			String left = subExp.substring(1, subExp.length());
			createBinaryTree(left, true);
		}
		path.removeFirst();
	}
	
	private String operators(String subExp) {
		StringBuilder str = new StringBuilder(subExp); 
		while (str.indexOf("(") != -1) {
			Deque<Character> br = new ArrayDeque<>();
			int j = str.indexOf("(");
			for (int i = j; i < str.length(); i++) {
				if (str.charAt(i) == '(') {
					br.offerFirst('(');
				} else if (str.charAt(i) == ')') {
					if (br.size() == 1) {
						br.removeFirst();
						str.delete(j, i+1);
						break;
					}
					br.removeFirst();
				}
			}
		}
		String[] s = str.toString().trim().split(" ");
		for (int i = 0; i < OPERATORS.length; i++) {
			for (int j = 0; j < s.length; j++) {
				if (OPERATORS[i].equals(s[j])) return s[j];
			}
		}
		if ("".equals(s[0])) {
			return "(";
		}
		if (s[0].charAt(0) == '-') return "-";
		return s[0];
	}
	
	private int indexOfOperator(String subExp, String operator) {
		if (operator.charAt(0) == '(') return -2;
		for (int i = 0; i < subExp.length(); i++) {
			if (subExp.charAt(i) == '(') {
				Deque<Character> queue = new ArrayDeque<>();
				queue.offerFirst('(');
				for (int j = i+1; j < subExp.length(); j++) {
					if ('(' == subExp.charAt(j)) {
						queue.offerFirst('(');
					} else if (')' == subExp.charAt(j)) {
						if (queue.size() == 1) {
							i = j;
							queue.clear();
							break;
						}
						queue.removeFirst();
					}
				}
			} else if (subExp.charAt(i) == operator.charAt(0) && subExp.substring(i, i+operator.length()).equals(operator)) {
				return i;
			}
		}
		return -1;
	}
}
