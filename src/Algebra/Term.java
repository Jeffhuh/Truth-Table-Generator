package Algebra;

public class Term {
	private boolean value;
	private char term;
	private String operator;
	private boolean handled;
	
	public Term(char term, boolean value) {
		setValue(value);
		setTerm(term);
		setHandled(false);
	}
	
	public Term(String operator) {
		setOperator(operator);
		setHandled(false);
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}
	public boolean getValue() {
		return value;
	}

	public void setValue(boolean value) {
		this.value = value;
	}

	public char getTerm() {
		return term;
	}

	public void setTerm(char term) {
		this.term = term;
	}

	public boolean isHandled() {
		return handled;
	}

	public void setHandled(boolean handled) {
		this.handled = handled;
	}
}
