package ����2_DoublyLikedList;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Scanner;

public class Main {
	static DoublyCircularLinkedList<Student> list = new DoublyCircularLinkedList<Student>();
	static Scanner sc = new Scanner(System.in);
	static DoublyCircularLinkedList<Student>.Node<Student> tmp = list.new Node<Student>();

	public static void main(String[] args) throws IOException {
		boolean cont = true;
		pirntMenu();
		readFile(args[0]);
		while (cont) {
			System.out.print("���ϴ� ����� �����ϼ���: ");
			switch (sc.nextInt()) {
			case 1:
				addStudent();
				break;
			case 2:
				deleteStudent();
				break;
			case 3:
				System.out.print(printAll());
				break;
			case 4:
				System.out.print(printRevers());
				break;
			case 5:
				System.out.print(printFromNum());
				break;
			case 6:
				cont = false;
				System.out.println("���α׷��� �����մϴ�.");
				saveFile(args[0]);
				break;
			default:
				System.out.println("�߸��� �Է��Դϴ�.");
			}
		}
	}

	public static void addStudent() { // �л� ���� �޾Ƽ� ����
		Scanner sc2 = new Scanner(System.in);
		System.out.print("�й��� �̸��� �Է��ϼ���: ");
		add(sc2.nextLine());
	}

	public static void pirntMenu() {
		System.out.println("============================");
		System.out.println("(1) �� �л� �Է�");
		System.out.println("(2) �л� ����");
		System.out.println("(3) �й� ������ ��ü ���");
		System.out.println("(4) �й� �������� ��ü ���");
		System.out.println("(5) Ư�� �й����� ��ü ���");
		System.out.println("(6) ���Ͽ� �����ϰ� ����");
		System.out.println("============================");

	}

	public static void deleteStudent() { // ���ϴ� �й��� �л� ����
		System.out.print("�й��� �Է��ϼ���: ");
		String sNum = sc.next(); // �й�
		tmp = list.getFirst();
		while (true) {
			if (sNum.equals(tmp.getElement().getStudenNum())) {
				list.remove(tmp);
				break;
			}
			tmp = tmp.getNext();
		}
		System.out.println("�����Ǿ����ϴ�.");
	}

	public static void add(String s) { // �����ϸ鼭 ����
		String[] studentArray = s.split(" "); // �й� �̸�
		Student student = new Student(studentArray[0], studentArray[1]);
		DoublyCircularLinkedList<Student>.Node<Student> n = list.new Node<Student>(student);
		tmp = list.getFirst();
		if (list.getSize() == 0) // ����Ʈ�� ���������
		{
			list.addFirst(student);
			return;
		}
		// ���ο� �й��� ù��° �й����� ������
		if (compare(list.getFirst(), n) > 0) {
			list.addFirst(student);
			return;
		} // ���ο� �й��� ������ �й����� Ŭ��
		if (compare(list.getLast(), n) < 0) {
			list.addLast(student);
			return;
		}

		while (true) { // ���ο� �й��� prev�� next���̿� ������
			if (tmp.getElement().getStudenNum().compareTo(student.getStudenNum()) < 0
					&& tmp.getNext().getElement().getStudenNum().compareTo(student.getStudenNum()) > 0) {
				list.addBetween(student, tmp, tmp.getNext());
				return;
			}
			tmp = tmp.getNext();
		}

	}

	public static int compare(DoublyCircularLinkedList<Student>.Node<Student> n1,
			DoublyCircularLinkedList<Student>.Node<Student> n2) {
		if (list.getFirst().getElement().getStudenNum().compareTo(n2.getElement().getStudenNum()) > 0) {
			return 1;
		}
		if (list.getLast().getElement().getStudenNum().compareTo(n2.getElement().getStudenNum()) < 0) {
			return -1;
		}
		return 0;
	}

	public static String printAll() { // ��ü ���
		String s = "";
		tmp = list.getFirst();
		for (int i = 0; i < list.getSize(); i++) {
			s += tmp.getElement().getStudenNum() + " " + tmp.getElement().getName() + "\n";
			tmp = tmp.getNext();
		}

		return s;
	}

	public static String printRevers() { // ���� ���
		String s = "";
		tmp = list.getLast();
		for (int i = 0; i < list.getSize(); i++) {
			s += tmp.getElement().getStudenNum() + " " + tmp.getElement().getName() + "\n";
			tmp = tmp.getPrev();
		}
		return s;
	}

	public static String printFromNum() { // Ư�� �й����� ���
		System.out.print("���ϴ� �й��� �Է����ּ���: ");
		String sNum = sc.next();
		String s = "";
		tmp = list.getFirst();
		while (true) { // ���ϴ� �й� ã��
			if (tmp.getElement().getStudenNum().equals(sNum)) {
				break;
			}
			tmp = tmp.getNext();
		}
		// ���ϴ� �й����� ���
		for (int i = 0; i < list.getSize(); i++) {
			s += tmp.getElement().getStudenNum() + " " + tmp.getElement().getName() + "\n";
			if (tmp.getNext().getElement() == null) // ������ ����̸� ó�� ���� ���ư���
				tmp = list.getFirst();
			else
				tmp = tmp.getNext();
		}

		return s;

	}

	public static void readFile(String path) throws IOException {// ���� �б�
		// String path = "c:\\Temp\\test2.txt";

		File file = new File(path);

		if (file.isFile()) {

			BufferedReader br = null;
			br = new BufferedReader(new InputStreamReader(new FileInputStream(path), "UTF-8"));

			String s = "";
			// ���پ� �о
			while ((s = br.readLine()) != null) {
				add(s);
			}
			br.close();
		}

	}

	public static void saveFile(String path) { // ���� ����
		try {
			OutputStream output = new FileOutputStream(path);
			String str = printAll();
			byte[] by = str.getBytes();
			output.write(by);

		} catch (Exception e) {
			e.getStackTrace();
		}
	}
}
