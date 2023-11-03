package 引薦2_SinglyLinkedList;

class SinglyLinkedList<E> {
	private Node<E> head = null;
	private int size = 0;
	

	public SinglyLinkedList() {
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public int getSize() {
		return size;
	}
	public Node<E> getFirst() {
		if (isEmpty())
			return null;
		return head;
	}

	public Node<E> getLast() {
		if (isEmpty())
			return null;
		else {
			Node<E> tmp = head;
			for (int i = 0; i < size - 1; i++) {
				tmp = tmp.getNext();
			}
			return tmp;
		}
	}

	public void addFirst(E e) {
		if (size == 0)
			head = new Node<E>(e, null);
		else
			head = new Node<E>(e, head);
		size++;
	}

	public void addLast(E e) {
		if (isEmpty())
			addFirst(e);
		else {
			Node<E> tmp = head;
			for (int i = 0; i < size - 1; i++) {
				tmp = tmp.getNext();
			}
			tmp.setNext(new Node<E>(e, null));
			size++;
		}
	}

	public void addAfter(E e, Node<E> prev) {
		if (isEmpty())
			addFirst(e);
		else
			prev.setNext(new Node<E>(e, prev.getNext()));
		size++;
	}

	public void deleteFirst() {
		if (isEmpty())
			return;
		else {
			head = head.getNext();
			size--;

		}
	}

	public void deleteAfter(Node<E> prev) {
		if (prev.getNext() == null)
			deleteFirst();
		else {
			Node<E> tmp = prev.getNext();
			prev.setNext(tmp.getNext());
			tmp.setNext(null);
		}
		size--;
	}

}

class Node<E> {
	private E element;
	private Node<E> next;

	public Node(E e, Node<E> n) { // 持失切
		element = e;
		next = n;
	}

	public Node() {

	}

	public Node(E e) {
		element = e;
		next = null;
	}

	public void setElement(E e) {
		element = e;
	}

	public void setNext(Node<E> n) {
		next = n;
	}

	public E getElement() {
		return element;
	}

	public Node<E> getNext() {
		return next;
	}

}
