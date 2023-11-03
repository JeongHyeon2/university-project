package 과제4_1번;

public class CompleteBinaryTree {
	private Node[] list;
	private int size;
	private Node root;

	public CompleteBinaryTree(int s) {
		size = s;
		list = new Node[size + 2];
		root = new Node(1);
		list[1] = root;
		setCBT(root);
	}
	
	public int getSize() {
		return size;
	}

	public void setCBT(Node n) {
		setLeft(n);
		setRight(n);
	}

	public void setLeft(Node n) {
		if (n.getKey() * 2 <= size) {
			Node newest = new Node(n.getKey() * 2);
			list[n.getKey() * 2] = newest;
			setCBT(newest);
		}
		return;
	}

	public void setRight(Node n) {
		if (n.getKey() * 2 + 1 <= size) {
			Node newest = new Node(n.getKey() * 2 + 1);
			list[n.getKey() * 2 + 1] = newest;
			setCBT(newest);
		}
		return;
	}

	public Node getLeft(Node n) {
		if (n.getKey() * 2 <= size)
			return list[n.getKey() * 2];
		return null;
	}

	public Node getRight(Node n) {
		if (n.getKey() * 2 + 1 <= size)
			return list[n.getKey() * 2 + 1];
		return null;
	}

	public void preorder() {
		preorder(root);
		System.out.println();
	}

	public void preorder(Node n) {
		if (n != null) {
			System.out.print(n.getKey() + " ");
			preorder(getLeft(n));
			preorder(getRight(n));
		}
	}

	public void inorder() {
		inorder(root);
		System.out.println();
	}

	public void inorder(Node n) {
		if (n != null) {
			inorder(getLeft(n));
			System.out.print(n.getKey() + " ");
			inorder(getRight(n));
		}
	}

	public void postorder() {
		postorder(root);
		System.out.println();

	}

	public void postorder(Node n) {
		if (n != null) {
			postorder(getLeft(n));
			postorder(getRight(n));
			System.out.print(n.getKey() + " ");
		}
	}

	public void levelorder() {
		CircularQueue q = new CircularQueue();
		Node t;
		q.enqueue(root);
		while (!q.isEmpty()) {
			t = q.dequeue();
			System.out.print(t.getKey() + " ");
			if (getLeft(t) != null) {
				q.enqueue(getLeft(t));
			}
			if (getRight(t) != null) {
				q.enqueue(getRight(t));
			}
		}
		System.out.println();
		
	}
	/*
	 * 	자료구조 멘토링 수업
	 * 	김평안 선배님 코드 참조
	 */
	public void printTree() {
		printTree(1, "", "");
	}

	private void printTree(int idx, String front, String child) {
		if (idx >= size + 1) {
			if (idx - 1 < size + 1 && idx % 2 != 0) {
				System.out.println(front + "X");
			}
			return;
		}

		System.out.println(front + list[idx].getKey());

		printTree(2 * idx + 1, child + "├── ", child + "│  ");
		printTree(2 * idx, child + "└── ", child + "   ");
	}
}
