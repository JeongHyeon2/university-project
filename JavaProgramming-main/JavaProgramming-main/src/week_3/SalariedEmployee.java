package week_3;

public class SalariedEmployee extends Employee {
	int Salary,annualsalary;
	public SalariedEmployee(String n, String num, int s) {
		super(n,num);
	}
	public SalariedEmployee() { this("Null", "Null", 0); }
   

	public void setSalary(int s) {
		Salary=s;
	}
	public int getSalary() {
		return Salary;
	}
	public int getannualsalary() {
		return annualsalary;
	}
	public void setannualsalary(int s) {
		annualsalary=s;
	}
	
	public void computeSalary(int s) {
		annualsalary=computesalary(s);
	};
		
}
