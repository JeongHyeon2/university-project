package week_6;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Vector;

class Student implements Serializable { // 직렬화 하기 위해 인터페이스 구현
	String name;
	transient int id;
	String address;
	
	public Student(int id, String name, String add) {
		this.id = id;
		this.name = name;
		this.address = add;
	}
	public int getID() {return id;}
	public String getName() {return name;}
	public String getAddress() {return address;}
}
public class ProgramBasic { 
public static void main(String[] args) { 
	Student s1 = new Student(2011, "kill", "the");
	Student s2 = new Student(2011, "pr", "at Park");
	Vector v = new Vector(); // 동적배열에 객체 추가
	v.add(s1);
	v.add(s2);

	try {
		FileOutputStream fos = 
		      new FileOutputStream(new File("c:\\Temp\\test3.txt"));
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(v); // 직렬화
		System.out.println("성공적으로 마쳤습니다.");
	} catch (IOException e) {
		e.printStackTrace();
	}
}
}
