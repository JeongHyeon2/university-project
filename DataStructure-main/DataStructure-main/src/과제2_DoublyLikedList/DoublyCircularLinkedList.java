package 引薦2_DoublyLikedList;

public class DoublyCircularLinkedList<E> {
	
		 class Node<E> {
			private E element;
			private Node<E> next;
			private Node<E> prev;
		
			public Node(E e, Node<E> p, Node<E> n) { // 持失切
				element = e;
				next = n;
				prev = p;
			}
		
			public Node() {
		
			}
		
			public Node(E e) {
				element = e;
				next = null;
				prev = null;
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
		
			public void setPrev(Node<E> p) {
				prev = p;
			}
		
			public Node<E> getPrev() {
				return prev;
			}
		}

	private Node<E> head; // dummy head
	private int size = 0;

	public DoublyCircularLinkedList() {
		head = new Node<E>(null, head, head);
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
		return head.getNext();
	}

	public Node<E> getLast() {
		if (isEmpty())
			return null;
		return head.getPrev();
	}

	public void addFirst(E e) {
		addBetween(e, head, head.getNext());
	}

	public void addLast(E e) {
		if (isEmpty()) {
			addFirst(e);
			return;
		}
		addBetween(e, head.getPrev(), head);
	}

	public E removeFirst() {
		if (isEmpty())
			return null;
		return remove(head.getNext());
	}

	public E removeLast() {
		if (isEmpty())
			return null;
		return remove(head.getPrev());
	}

	public void addBetween(E e, Node<E> prevNode, Node<E> nextNode) {
		Node<E> newest = new Node<E>(e, prevNode, nextNode);
		if (size == 0) {
			head.setNext(newest);
			head.setPrev(newest);
		} else {
			prevNode.setNext(newest);
			nextNode.setPrev(newest);
		}
		size++;
	}

	public E remove(Node<E> node) {
		Node<E> prevNode = node.getPrev();
		Node<E> nextNode = node.getNext();
		prevNode.setNext(nextNode);
		nextNode.setPrev(prevNode);
		size--;
		return node.getElement();
	}
}
