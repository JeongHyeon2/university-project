package week_6;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Vector;

class Student implements Serializable { // ����ȭ �ϱ� ���� �������̽� ����
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
	Vector v = new Vector(); // �����迭�� ��ü �߰�
	v.add(s1);
	v.add(s2);

	try {
		FileOutputStream fos = 
		      new FileOutputStream(new File("c:\\Temp\\test3.txt"));
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(v); // ����ȭ
		System.out.println("���������� ���ƽ��ϴ�.");
	} catch (IOException e) {
		e.printStackTrace();
	}
}
}
