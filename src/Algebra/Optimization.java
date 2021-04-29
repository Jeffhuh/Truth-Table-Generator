package Algebra;

public class Optimization {
	public static int numberOfOnes;
	public static int numberOfZeros;
	
	public static int numberOfOnes() {
		int counter = 0; 
		for (int i = 0; i < TruthTable.tt.length; i++) {
			if (TruthTable.tt[i][TruthTable.terms.length] == true) {
				counter++; 
			}
		}
		return counter;
	}
	
	public static String knf() {
		String str = "";
		int count = 0; 
		for (int i = 0; i < TruthTable.tt.length; i++) {
			if (TruthTable.tt[i][TruthTable.terms.length] == true) {
				str += "(";
				for (int j = 0; j < TruthTable.terms.length; j++) {
					if (TruthTable.tt[i][j] == false) {
						str += "-"+TruthTable.terms[j];
					} else {
						str += TruthTable.terms[j];
					}
					if (j != TruthTable.terms.length-1) {
						str += " and ";
					}
				}
				str += ")";
				count++;
				if (count != numberOfOnes) {
					str += " or ";
				}
			}
		}
		return str;
	}
	
	public static String dnf() {
		String str = "";
		int count = 0;
		for (int i = 0; i < TruthTable.tt.length; i++) {
			if (TruthTable.tt[i][TruthTable.terms.length] == false) {
				str += "(";
				for (int j = 0; j < TruthTable.terms.length; j++) {
					if (TruthTable.tt[i][j] == true) {
						str += "-"+TruthTable.terms[j];
					} else {
						str += TruthTable.terms[j];
					}
					if (j != TruthTable.terms.length-1) {
						str += " or ";
					}
				}
				str += ")";
				count++;
				if (count != numberOfZeros) {
					str += " and ";
				}
			}
		}
		return str;
	}
}
