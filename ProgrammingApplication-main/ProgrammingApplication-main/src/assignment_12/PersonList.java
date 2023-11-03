package assignment_12;

public class PersonList {
	private Person[] list;
	private int last;

	public PersonList() {
		list = new Person[100];
		last = -1;
	}

	public Person[] getList() {
		return list;
	}

	public void setList(Person[] list) {
		this.list = list;
	}

	public int getLast() {
		return last;
	}

	public void setLast(int last) {
		this.last = last;
	}

	public void input(Person p) throws FullListException, DuplicationNameException {
		if (last == 99)
			throw new FullListException();
		for (int i = 0; i < last + 1; i++)
			if (p.getName().equals(list[i].getName()))
				throw new DuplicationNameException();
		list[++last] = p;
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

