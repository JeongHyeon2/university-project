package °úÁ¦4_2¹ø;

public class TreeNode {
	private TreeNode left;
	private TreeNode right;
	private int value;
	private Token token;

	public TreeNode(Token t, TreeNode l, TreeNode r) {
		token = t;
		left = l;
		right = r;

	}

	public TreeNode getLeft() {
		return left;
	}

	public TreeNode getRight() {
		return right;
	}

	public void setLeft(TreeNode n) {
		this.left = n;
	}

	public void setRight(TreeNode n) {
		this.right = n;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int v) {
		this.value = v;
	}

	public void setToken(Token t) {
		this.token = t;
	}

	public Token getToken() {
		return token;
	}
}
