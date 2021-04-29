package Algebra;

import java.util.Deque;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws LeftRightException {
		Scanner scan = new Scanner(System.in);
		String expression = scan.nextLine();
		TruthTable t = new TruthTable(expression);
		int nOfVar = TruthTable.terms.length;
		for (int i = 0; i < nOfVar; i++) {
			System.out.print(TruthTable.terms[i]+"\t");
		}
		System.out.println("result\n");
		for (int i = 0; i < TruthTable.tt.length; i++) {
			BinaryTree.row = i;
			TruthTable.tt[i][nOfVar] = BinaryTree.evaluate(BinaryTree.root.left);
			for (int j = 0; j < TruthTable.tt[i].length; j++) {
				System.out.print((TruthTable.tt[i][j] ? "1":"0")+"\t");
			}
			System.out.println();
		}
		Optimization.numberOfOnes = Optimization.numberOfOnes();
		Optimization.numberOfZeros = TruthTable.tt.length - Optimization.numberOfOnes;
		System.out.println(Optimization.numberOfOnes+" "+Optimization.numberOfZeros);
		System.out.println(Optimization.knf());
		System.out.println(Optimization.dnf());
	}

}
