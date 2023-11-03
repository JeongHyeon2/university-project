package °úÁ¦2_SinglyLinkedList;

public class Student {
	private String studentNum, name;
	private SinglyLinkedList<Club> club;

	public Student(String s, String n, SinglyLinkedList<Club> c) {
		studentNum = s;
		name = n;
		club = c;
	}

	public Student() {

	}

	public void setStudentNum(String s) {
		studentNum = s;
	}

	public void setName(String n) {
		name = n;
	}

	public String getStudenNum() {
		return studentNum;
	}

	public String getName() {
		return name;
	}

	public SinglyLinkedList<Club> getClub() {
		return club;
	}
}