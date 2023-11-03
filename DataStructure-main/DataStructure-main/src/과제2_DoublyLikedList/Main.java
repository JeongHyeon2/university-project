package 과제2_DoublyLikedList;

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
			System.out.print("원하는 기능을 선택하세요: ");
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
				System.out.println("프로그램을 종료합니다.");
				saveFile(args[0]);
				break;
			default:
				System.out.println("잘못된 입력입니다.");
			}
		}
	}

	public static void addStudent() { // 학생 정보 받아서 저장
		Scanner sc2 = new Scanner(System.in);
		System.out.print("학번과 이름을 입력하세요: ");
		add(sc2.nextLine());
	}

	public static void pirntMenu() {
		System.out.println("============================");
		System.out.println("(1) 새 학생 입력");
		System.out.println("(2) 학생 삭제");
		System.out.println("(3) 학번 순으로 전체 출력");
		System.out.println("(4) 학번 역순으로 전체 출력");
		System.out.println("(5) 특정 학번부터 전체 출력");
		System.out.println("(6) 파일에 저장하고 종료");
		System.out.println("============================");

	}

	public static void deleteStudent() { // 원하는 학번의 학생 삭제
		System.out.print("학번을 입력하세요: ");
		String sNum = sc.next(); // 학번
		tmp = list.getFirst();
		while (true) {
			if (sNum.equals(tmp.getElement().getStudenNum())) {
				list.remove(tmp);
				break;
			}
			tmp = tmp.getNext();
		}
		System.out.println("삭제되었습니다.");
	}

	public static void add(String s) { // 정렬하면서 저장
		String[] studentArray = s.split(" "); // 학번 이름
		Student student = new Student(studentArray[0], studentArray[1]);
		DoublyCircularLinkedList<Student>.Node<Student> n = list.new Node<Student>(student);
		tmp = list.getFirst();
		if (list.getSize() == 0) // 리스트가 비어있을때
		{
			list.addFirst(student);
			return;
		}
		// 새로운 학번이 첫번째 학번보다 작을때
		if (compare(list.getFirst(), n) > 0) {
			list.addFirst(student);
			return;
		} // 새로운 학번이 마지막 학번보다 클때
		if (compare(list.getLast(), n) < 0) {
			list.addLast(student);
			return;
		}

		while (true) { // 새로운 학번이 prev와 next사이에 있을때
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

	public static String printAll() { // 전체 출력
		String s = "";
		tmp = list.getFirst();
		for (int i = 0; i < list.getSize(); i++) {
			s += tmp.getElement().getStudenNum() + " " + tmp.getElement().getName() + "\n";
			tmp = tmp.getNext();
		}

		return s;
	}

	public static String printRevers() { // 역순 출력
		String s = "";
		tmp = list.getLast();
		for (int i = 0; i < list.getSize(); i++) {
			s += tmp.getElement().getStudenNum() + " " + tmp.getElement().getName() + "\n";
			tmp = tmp.getPrev();
		}
		return s;
	}

	public static String printFromNum() { // 특정 학번부터 출력
		System.out.print("원하는 학번을 입력해주세요: ");
		String sNum = sc.next();
		String s = "";
		tmp = list.getFirst();
		while (true) { // 원하는 학번 찾기
			if (tmp.getElement().getStudenNum().equals(sNum)) {
				break;
			}
			tmp = tmp.getNext();
		}
		// 원하는 학번부터 출력
		for (int i = 0; i < list.getSize(); i++) {
			s += tmp.getElement().getStudenNum() + " " + tmp.getElement().getName() + "\n";
			if (tmp.getNext().getElement() == null) // 마지막 노드이면 처음 노드로 돌아가기
				tmp = list.getFirst();
			else
				tmp = tmp.getNext();
		}

		return s;

	}

	public static void readFile(String path) throws IOException {// 파일 읽기
		// String path = "c:\\Temp\\test2.txt";

		File file = new File(path);

		if (file.isFile()) {

			BufferedReader br = null;
			br = new BufferedReader(new InputStreamReader(new FileInputStream(path), "UTF-8"));

			String s = "";
			// 한줄씩 읽어서
			while ((s = br.readLine()) != null) {
				add(s);
			}
			br.close();
		}

	}

	public static void saveFile(String path) { // 파일 저장
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
