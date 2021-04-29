package Algebra;

public class Operation {
	
	public static boolean bracket(boolean a, boolean b) {
		return a;
	}
	
	public static boolean not(boolean a, boolean b) {
		return !a;
	}
	
	public static boolean and(boolean a, boolean b) {
		return a && b;
	}
	
	public static boolean or(boolean a, boolean b) {
		return a || b;
	}
	
	public static boolean nor(boolean a, boolean b) {
		return !(a || b);
	}
	
	public static boolean nand(boolean a, boolean b) {
		return !(a && b);
	}
	
	public static boolean implies(boolean a, boolean b) {
		return !a || b;
	}
	
	public static boolean ifThen(boolean a, boolean b) {
		return a || !b;
	}
	
	public static boolean equals(boolean a, boolean b) {
		return (a && b) || (!a && !b);
	}
	
	public static boolean xor(boolean a, boolean b) {
		return (!a && b) || (a && !b);
	}
}
