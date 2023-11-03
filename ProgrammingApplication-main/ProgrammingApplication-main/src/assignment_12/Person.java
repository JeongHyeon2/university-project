package assignment_12;

public class Person{
	private String name;
	private String phoneNum;

	public Person(String n, String pn) {
		name = n;
		phoneNum = pn;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

}
