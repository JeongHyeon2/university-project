package 과제2_SinglyLinkedList;

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
				cont = false;
				System.out.println("프로그램을 종료합니다.");
				saveFile(args[0]);
				break;
			default:
				System.out.println("잘못된 입력입니다.");
			}
		}
	}

	public static void pirntMenu() {
		System.out.println("============================");
		System.out.println("(1) 새 학생 입력");
		System.out.println("(2) 학생 삭제");
		System.out.println("(3) 학번 순으로 전체 출력");
		System.out.println("(4) 파일에 저장하고 종료");
		System.out.println("============================");

	}

	// 파일 읽기
	public static void readFile(String path) throws IOException {
		File file = new File(path);

		if (file.isFile()) {

			BufferedReader br = null;
			br = new BufferedReader(new InputStreamReader(new FileInputStream(path), "euc-kr"));

			String s = "";
			// 한줄씩 읽어서
			while ((s = br.readLine()) != null) {
				add(s);
			}
			br.close();
		}

	}

	// 파일 저장
	public static void saveFile(String path) throws IOException {
			OutputStream output = new FileOutputStream(path);
			String str = printAll();
			byte[] by = str.getBytes();
			output.write(by);
	}

	// 학생 저장
	public static void addStudent() {
		System.out.print("학번과 이름, 동아리를 입력하세요: ");
		Scanner sc2 = new Scanner(System.in);
		add(sc2.nextLine());
	}

	public static void deleteStudent() { // 원하는 학번의 학생 삭제
		System.out.print("학번을 입력하세요: ");

		String sNum = sc.next(); // 학번
		
		Node<Student> prev = new Node<Student>();
		prev = list.getFirst();
		
		if (sNum.equals(list.getFirst().getElement().getStudenNum())) {
			list.deleteFirst();
			System.out.println("삭제되었습니다.");
			return;
		}
		while (true) {
			if (sNum.equals(prev.getNext().getElement().getStudenNum())) {
				list.deleteAfter(prev);		
				System.out.println("삭제되었습니다.");
				return;	
			}
			prev = prev.getNext();
		}
	}

	public static void add(String s) { // 정렬하면서 저장
		String[] sArray = s.split(" "); // 학생 정보를 저장하는 String 배열
		Student student = new Student(sArray[0], sArray[1], clubAdd(s)); // student 요소 (학번, 이름) 저장
		Node<Student> newNode = new Node<Student>(student); // 저장된 요소를 가지는 노드
	
		if (list.getSize() == 0) {
			list.addFirst(student); // size=0일때
		} else
			sort(student, newNode);
	}

	public static String printAll() { // 전체 출력
		String s = ""; // 반환될 문장 저장
		Node<Student> tmp = new Node<Student>();
		Node<Club> club = new Node<Club>();
		tmp = list.getFirst();
		for (int i = 0; i < list.getSize(); i++) { // 학생 정보 출력
			s += tmp.getElement().getStudenNum() + " " + tmp.getElement().getName() + " "; //학생 학번, 이름 출력
			club = tmp.getElement().getClub().getFirst();
			
			s+=printClub(tmp,club)+"\n"; // 동아리 출력
			
			tmp = tmp.getNext();
		}
		return s;
	}
	// 동아리 출력
	public static String printClub(Node<Student> tmp,Node<Club> club) {
		String sClub="";
		for (int j = 0; j < tmp.getElement().getClub().getSize(); j++) // 동아리 출력
		{
			if (j == tmp.getElement().getClub().getSize() - 1) // 마지막 동아리면 / 출력 x
				sClub += club.getElement().getClubName();
			else
				sClub += club.getElement().getClubName() + "/";
			club = club.getNext();
		}
		return sClub;
	}
	
	public static SinglyLinkedList<Club> clubAdd(String s) { // 동아리를 정렬하면서 저장
		String[] sArray = s.split(" "); // 학생 정보를 저장하는 String 배열
		SinglyLinkedList<Club> c = new SinglyLinkedList<Club>();
		
		if (sArray.length > 2) { // 동아리가 있는 경우 실행
			String clubs = "";
			for (int i = 2; i < sArray.length; i++)
				clubs += sArray[i]; // 모든 동아리를 하나의 String 저장
			String[] clubArray = clubs.split("/"); // 저장한 동아리를 / 로 분리
			
			for (int i = 0; i < clubArray.length; i++)
				c.addLast(new Club(clubArray[i])); // 노드에 하나씩 저장
		}
		return c;
	}

	public static void sort(Student student, Node<Student> newNode) { // 정렬하면서 리스트에 저장
		Node<Student> prev = new Node<Student>();

		prev = list.getFirst(); // 이전노드

		if (list.getLast().getElement().getStudenNum().compareTo(newNode.getElement().getStudenNum()) < 0) {
			list.addLast(student); // 새로운 노드의 학번이 마지막 노드보다 클때
			return;
		}

		if (list.getFirst().getElement().getStudenNum().compareTo(newNode.getElement().getStudenNum()) > 0) {
			// 새로운 노드의 학번이 첫번째 노드보다 작을때
			list.addFirst(student);
			return;
		}

		while (true) { // 이전 노드와 다음 노드 사이에 학번이 위치할 때 그 사이에 넣기
			if (prev.getNext().getElement().getStudenNum().compareTo(newNode.getElement().getStudenNum()) > 0
					&& prev.getElement().getStudenNum().compareTo(newNode.getElement().getStudenNum()) < 0) {
				list.addAfter(student, prev);
				return;
			}
			prev = prev.getNext();
		}

	}

}
