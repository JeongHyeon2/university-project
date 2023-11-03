package assignment_12;

public class List<E extends Comparable<E>> {
	private E[] list;
	private int last;

	public List() {
		list = (E[]) new Object[100];
		last = -1;
	}

	public E[] getList() {
		return list;
	}

	public void setList(E[] list) {
		this.list = list;
	}

	public int getLast() {
		return last;
	}

	public void setLast(int last) {
		this.last = last;
	}

	public void input(E e) throws FullListException {
		if (last == 99)
			throw new FullListException();
		list[++last] = e;
	}

//	public void delete(E e) {
//		int k = find(e);
//		list[k] = list[last];
//		list[last] = null;
//		last--;
//	}
//
//	public int find(E e) {
//		for (int i = 0; i < last + 1; i++) {
//			if (list[i].compareTo(e) == 0) {
//				return i;
//			}
//		}
//		return -1;
//	}

}