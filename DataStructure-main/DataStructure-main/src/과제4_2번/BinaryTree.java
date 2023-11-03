package 과제4_2번;


public class BinaryTree {

	private TreeNode root;

	public BinaryTree() {

	}

	public void setRoot(TreeNode n) {
		root = n;
	}

	public TreeNode getRoot() {
		return root;
	}

	public void preorder() {
		preorder(root);
		System.out.println();
	}

	public void preorder(TreeNode n) {
		if (n != null) {
			System.out.print(n.getToken().getValue() + " ");
			preorder(n.getLeft());
			preorder(n.getRight());
			
		}
	}

	/*
	 * 자료구조 멘토링 수업 김평안 선배님 코드 참조
	 */
	public void printTree() {
		printTree(root, "", "");
	}

	private void printTree(TreeNode n, String front, String child) {

		if (n == null) {
			System.out.println(front + "X");
			return;
		}

		if (haveChildren(n))
			System.out.println(front + n.getToken().getValue() + "[" + n.getValue() + "]");
		else {
			System.out.println(front + n.getToken().getValue());
			return;
		}
		

		printTree(n.getLeft(), child + "├── ", child + "│  ");
		printTree(n.getRight(), child + "└── ", child + "   ");
	}

	private boolean haveChildren(TreeNode node) {
		if (node.getLeft() == null && node.getRight() == null)
			return false;
		return true;
	}

}

