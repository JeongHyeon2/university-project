package week_3;

public class student {
	int number,grade;
	String name, teacher;
	public student(int n,int g, String name, String t) {
		number=n;grade=g;this.name=name;teacher=t;
		}
	public void setnumber(int n) {number=n;}
	public void setgrader(int g) {grade=g;}
	public void setname(String name) {this.name=name;}
	public void setteacher(String t) {teacher=t;}
	public int getnumber() {return number;}
	public int getgrade() {return grade;}
	public String getname() {return name;}
	public String getteacher() {return teacher;}
	
}
