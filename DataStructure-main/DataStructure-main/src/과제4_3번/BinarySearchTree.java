package 과제4_3번;

public class BinarySearchTree {
	public Node root;

	public BinarySearchTree() {

	}

	public Node getRoot() {
		return root;
	}

	public void setRoot(Node n) {
		root = n;
	}

	public void put(String s) {
		root = put(root, s);
	}

	public Node put(Node n, String s) {
		if (n == null)
			return new Node(s);
		int t = n.getValue().compareTo(s);
		if (t > 0)
			n.setLeft(put(n.getLeft(), s));
		else if (t < 0)
			n.setRight(put(n.getRight(), s));
		else
			n.setValue(s);
		return n;
	}
	
	/*
	 * 자료구조 멘토링 수업 김평안 선배님 코드 참조
	 */
	
	public void printTree() {
		printTree(root, "", "");
	}

	private void printTree(Node n, String front, String child) {

		if (n == null) {
			System.out.println(front + "X");
			return;
		}
		
		
		System.out.println(front + n.getValue());
		if(!haveChildren(n)) return;
		
		printTree(n.getLeft(), child + "├── ", child + "│  ");
		printTree(n.getRight(), child + "└── ", child + "   ");
	}

	private boolean haveChildren(Node node) {
		if (node.getLeft() == null && node.getRight() == null)
			return false;
		return true;
	}

}
