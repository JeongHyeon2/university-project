package assignment_9;

public class Person {
	private int id;
	private String name;
	private String call;
	private String department;
	private String address;

	public Person(int i, String n, String c, String d, String a) {
		id = i;
		name = n;
		call = c;
		department = d;
		address = a;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCall() {
		return call;
	}

	public void setCall(String call) {
		this.call = call;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}
