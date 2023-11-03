package °úÁ¦4_1¹ø;

public class CircularQueue {
	private Node[] data;
	private int front;
	private int rear;
	private int size;

	public CircularQueue() {
		data = new Node[4];
		front = 0;
		rear = 0;
		size = 0;
	}

	public int size() {
		return size;
	}
	public boolean isEmpty() {
		return size==0;
	}

	public void enqueue(Node n) {
		if ((rear + 1) % data.length == front)
			resize(2 * data.length);
		rear = (rear + 1) % data.length;
		data[rear] = n;
		size++;
	}

	public Node dequeue(){
	

		front = (front + 1) % data.length;
		Node n = data[front];
		data[front] = null;
		size--;
		if (size > 0 && size == data.length / 4)
			resize(data.length / 2);
		return n;
	}


	public void resize(int newsize) {
		Node[] n = new Node[newsize];
		for (int i = 1, j = front + 1; i < size + 1; i++, j++) {
			n[i] = data[j % data.length];
		}
		front = 0;
		rear = size;
		data = n;
	}

}
