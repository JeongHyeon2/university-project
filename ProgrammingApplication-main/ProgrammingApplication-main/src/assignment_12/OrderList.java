package assignment_12;

public class OrderList {

	private Order[] list;
	private int last;
	private int capacity;

	public OrderList() {
		capacity=4;
		list = new Order[capacity];
		last = -1;
	}

	public Order[] getList() {
		return list;
	}

	public void setList(Order[] list) {
		this.list = list;
	}

	public int getLast() {
		return last;
	}

	public void setLast(int last) {
		this.last = last;
	}

	public void input(Order o) {
		if (last+1 == capacity) resize(2*capacity);
			
		list[++last] = o;
	}

	public void delete(String s) throws NotFoundNameException {
		for (int i = 0; i < last + 1; i++) {
			if (list[i].getFood().getName().equals(s) || list[i].getPerson().getName().equals(s)) {
				list[i] = list[last];
				list[last] = null;
				last--;
				return;
			}
		}
		throw new NotFoundNameException();

	}
	public void resize(int size) {
		Order[] tmp = new Order[size];
		for(int i=0;i<last+1;i++) {
			tmp[i]=list[i];
		}
		capacity*=2;
		list=tmp;
	}

}

