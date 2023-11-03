package week_3;

public class IndustrialStudent extends student implements IGraduateStudent {

	public IndustrialStudent(int n, int g, String name, String t) {
		super(n, g, name, t);
	}
	GraduateStudent GS = new GraduateStudent(20200284, 1, "kim", "kim", null);
	public void setmajor(String m) {GS.setmajor(m);}
    public String getmajor() {return null;}
	
}
