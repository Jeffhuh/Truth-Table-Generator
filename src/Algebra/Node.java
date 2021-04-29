package Algebra;

public class Node {
	public Term data;
    public Node parent;
    public Node left;
    public Node right;

    public Node(Term data) {
        this.data = data;
        this.parent = null;
        this.left = null;
        this.right = null;
    }
}
