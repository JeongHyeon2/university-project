package week_3;

public class Employee {
	private String name;
	private String number;
	 public Employee() { this("Null", "Null"); }
	 public Employee(String n, String num) {
		name=n; number=num;
	}
	 
	public void setname(String n) {
		name=n;
	}
	public void setnum(String num) {
		number=num;
	}
	public String getname() {
		return name;
	}
	public String getnum() {
		return number;
	}
	 public int computesalary(int annualsalary) { return annualsalary / 12; } 
	


}

