package ����2_SinglyLinkedList;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Scanner;

public class Main {
	static SinglyLinkedList<Student> list = new SinglyLinkedList<Student>();
	static Scanner sc = new Scanner(System.in);

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
				cont = false;
				System.out.println("���α׷��� �����մϴ�.");
				saveFile(args[0]);
				break;
			default:
				System.out.println("�߸��� �Է��Դϴ�.");
			}
		}
	}

	public static void pirntMenu() {
		System.out.println("============================");
		System.out.println("(1) �� �л� �Է�");
		System.out.println("(2) �л� ����");
		System.out.println("(3) �й� ������ ��ü ���");
		System.out.println("(4) ���Ͽ� �����ϰ� ����");
		System.out.println("============================");

	}

	// ���� �б�
	public static void readFile(String path) throws IOException {
		File file = new File(path);

		if (file.isFile()) {

			BufferedReader br = null;
			br = new BufferedReader(new InputStreamReader(new FileInputStream(path), "euc-kr"));

			String s = "";
			// ���پ� �о
			while ((s = br.readLine()) != null) {
				add(s);
			}
			br.close();
		}

	}

	// ���� ����
	public static void saveFile(String path) throws IOException {
			OutputStream output = new FileOutputStream(path);
			String str = printAll();
			byte[] by = str.getBytes();
			output.write(by);
	}

	// �л� ����
	public static void addStudent() {
		System.out.print("�й��� �̸�, ���Ƹ��� �Է��ϼ���: ");
		Scanner sc2 = new Scanner(System.in);
		add(sc2.nextLine());
	}

	public static void deleteStudent() { // ���ϴ� �й��� �л� ����
		System.out.print("�й��� �Է��ϼ���: ");

		String sNum = sc.next(); // �й�
		
		Node<Student> prev = new Node<Student>();
		prev = list.getFirst();
		
		if (sNum.equals(list.getFirst().getElement().getStudenNum())) {
			list.deleteFirst();
			System.out.println("�����Ǿ����ϴ�.");
			return;
		}
		while (true) {
			if (sNum.equals(prev.getNext().getElement().getStudenNum())) {
				list.deleteAfter(prev);		
				System.out.println("�����Ǿ����ϴ�.");
				return;	
			}
			prev = prev.getNext();
		}
	}

	public static void add(String s) { // �����ϸ鼭 ����
		String[] sArray = s.split(" "); // �л� ������ �����ϴ� String �迭
		Student student = new Student(sArray[0], sArray[1], clubAdd(s)); // student ��� (�й�, �̸�) ����
		Node<Student> newNode = new Node<Student>(student); // ����� ��Ҹ� ������ ���
	
		if (list.getSize() == 0) {
			list.addFirst(student); // size=0�϶�
		} else
			sort(student, newNode);
	}

	public static String printAll() { // ��ü ���
		String s = ""; // ��ȯ�� ���� ����
		Node<Student> tmp = new Node<Student>();
		Node<Club> club = new Node<Club>();
		tmp = list.getFirst();
		for (int i = 0; i < list.getSize(); i++) { // �л� ���� ���
			s += tmp.getElement().getStudenNum() + " " + tmp.getElement().getName() + " "; //�л� �й�, �̸� ���
			club = tmp.getElement().getClub().getFirst();
			
			s+=printClub(tmp,club)+"\n"; // ���Ƹ� ���
			
			tmp = tmp.getNext();
		}
		return s;
	}
	// ���Ƹ� ���
	public static String printClub(Node<Student> tmp,Node<Club> club) {
		String sClub="";
		for (int j = 0; j < tmp.getElement().getClub().getSize(); j++) // ���Ƹ� ���
		{
			if (j == tmp.getElement().getClub().getSize() - 1) // ������ ���Ƹ��� / ��� x
				sClub += club.getElement().getClubName();
			else
				sClub += club.getElement().getClubName() + "/";
			club = club.getNext();
		}
		return sClub;
	}
	
	public static SinglyLinkedList<Club> clubAdd(String s) { // ���Ƹ��� �����ϸ鼭 ����
		String[] sArray = s.split(" "); // �л� ������ �����ϴ� String �迭
		SinglyLinkedList<Club> c = new SinglyLinkedList<Club>();
		
		if (sArray.length > 2) { // ���Ƹ��� �ִ� ��� ����
			String clubs = "";
			for (int i = 2; i < sArray.length; i++)
				clubs += sArray[i]; // ��� ���Ƹ��� �ϳ��� String ����
			String[] clubArray = clubs.split("/"); // ������ ���Ƹ��� / �� �и�
			
			for (int i = 0; i < clubArray.length; i++)
				c.addLast(new Club(clubArray[i])); // ��忡 �ϳ��� ����
		}
		return c;
	}

	public static void sort(Student student, Node<Student> newNode) { // �����ϸ鼭 ����Ʈ�� ����
		Node<Student> prev = new Node<Student>();

		prev = list.getFirst(); // �������

		if (list.getLast().getElement().getStudenNum().compareTo(newNode.getElement().getStudenNum()) < 0) {
			list.addLast(student); // ���ο� ����� �й��� ������ ��庸�� Ŭ��
			return;
		}

		if (list.getFirst().getElement().getStudenNum().compareTo(newNode.getElement().getStudenNum()) > 0) {
			// ���ο� ����� �й��� ù��° ��庸�� ������
			list.addFirst(student);
			return;
		}

		while (true) { // ���� ���� ���� ��� ���̿� �й��� ��ġ�� �� �� ���̿� �ֱ�
			if (prev.getNext().getElement().getStudenNum().compareTo(newNode.getElement().getStudenNum()) > 0
					&& prev.getElement().getStudenNum().compareTo(newNode.getElement().getStudenNum()) < 0) {
				list.addAfter(student, prev);
				return;
			}
			prev = prev.getNext();
		}

	}

}
