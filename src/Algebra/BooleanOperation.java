package Algebra;

public interface BooleanOperation {
	public boolean operation(boolean a, boolean b);
	
	public default boolean meth(boolean a, boolean b) {
		return operation(a,b);
	}
}
