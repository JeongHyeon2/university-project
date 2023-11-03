package assignment_9;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class AddressBook {
	private Person[] personArr;
	private int numPeople; // ��� ��
	private int size; // �迭 ũ��

	public AddressBook() {
		size = 4;
		personArr = new Person[size];
		numPeople = 0;
		
	}
	public void input(Person p) throws IdDulicationException {
		if (isFull())
			resize(2 * size);
		if (isEmpty()) {
			personArr[numPeople++] = p;
			return;
		}
		if (personArr[0].getId() > p.getId()) { // ù��° id ���� ������
			insert(0, p);
			return;
		}
		if (personArr[numPeople - 1].getId() < p.getId()) { // �Ǹ����� id���� Ŭ��
			personArr[numPeople++] = p;
			return;
		}
		for (int i = 0; i < numPeople; i++) { // i�� i+1 ���̿� ������
			if (personArr[i].getId() < p.getId() && personArr[i + 1].getId() > p.getId()) {
				insert(i + 1, p); // �����ֱ�
				return;
			}
		}
		throw new IdDulicationException(); // id�ߺ� ����

	}

	public void delete(int id) throws NotFoundIdException {
		int index = 0;
		if (size / 4 >= numPeople)
			resize(size / 2);
		while (true) {
			if (personArr[index].getId() == id) { // id �߰�
				for (; index < numPeople - 1; index++) { // ����Ʈ
					personArr[index] = personArr[index + 1];
				}
				numPeople--;
				return;
			}
			index++;
			if (index == numPeople) // ã�� ���ϸ� ����
				throw new NotFoundIdException();

		}
	}

	public String printAll() { // ���
		String s = "";
		for (int i = 0; i < numPeople; i++) {
			s += personArr[i].getId() + "," + personArr[i].getName() + "," + personArr[i].getCall() + ","
					+ personArr[i].getDepartment() + "," + personArr[i].getAddress() + "\n";
		}
		return s;

	}

	public void saveFile(String path) throws IOException { // ���� ����
		OutputStream output = new FileOutputStream(path);
		byte[] by =printAll().getBytes();
		output.write(by);
	}

	private boolean isEmpty() {
		return numPeople == 0;
	}

	private boolean isFull() {
		return numPeople == size;
	}

	private void insert(int i, Person p) { // i��°�� p �����ֱ�
		for (int j = numPeople - 1; j >= i; j--) {
			personArr[j + 1] = personArr[j];
		}
		personArr[i] = p;
		numPeople++;
	}

	private void resize(int s) {
		Person[] tmp = new Person[s];
		for (int i = 0; i < numPeople; i++) {
			tmp[i] = personArr[i];
		}
		personArr = tmp;
		size = s;
	}

}

class IdDulicationException extends Exception { // id�ߺ�

}

class NotFoundIdException extends Exception { // id �߰�x

}
