package week_3;

public class GraduateStudent extends student implements IGraduateStudent{
	String major;
	public GraduateStudent(int n, int g, String name, String t, String m) {
		super(n, g, name, t); major=m;
	}
	public GraduateStudent() { this(0, 0, " ", "",""); }
   
	public void setmajor(String m) {
    	major=m;
    }
    public String getmajor() {
    	return major;
    }
}
interface IGraduateStudent{
	 public void setmajor(String m);
	 public String getmajor();
}
