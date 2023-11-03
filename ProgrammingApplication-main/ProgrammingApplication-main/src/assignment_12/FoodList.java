package assignment_12;

public class FoodList  {
	private Food[] list;
	private int last;
	

	public FoodList() {
		list = new Food[100];
		last = -1;
	}

	public Food[] getList() {
		return list;
	}

	public void setList(Food[] list) {
		this.list = list;
	}

	public int getLast() {
		return last;
	}

	public void setLast(int last) {
		this.last = last;
	}

	public void input(Food f) throws FullListException, DuplicationNameException {
		if (last == 99)
			throw new FullListException();
		for (int i = 0; i < last + 1; i++)
			if (f.getName().equals(list[i].getName()))
				throw new DuplicationNameException();

		list[++last] = f;
	}

	public void delete(String s) throws NotFoundNameException {
		for (int i = 0; i < last + 1; i++) {
			if (list[i].getName().equals(s)) {
				list[i] = list[last];
				list[last] = null;
				last--;
				return;
			}
		}
		throw new NotFoundNameException();

	}

}
