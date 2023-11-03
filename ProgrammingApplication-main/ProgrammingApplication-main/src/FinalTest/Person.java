package FinalTest;

public class Person {
	private String name;
	private int korean;
	private int eng;
	private int math;
	private int key;
	public Person(int k,String n,int ko,int e,int m) {
		key=k;
		name=n;
		korean=ko;
		eng=e;
		math=m;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getKey() {
		return key;
	}
	public int getKorean() {
		return korean;
	}
	public void setKorean(int korean) {
		this.korean = korean;
	}
	public int getEng() {
		return eng;
	}
	public void setEng(int eng) {
		this.eng = eng;
	}
	public int getMath() {
		return math;
	}
	public void setMath(int math) {
		this.math = math;
	}
	public int getAvg() {
		return (korean+eng+math)/3;
	}
	

	


}
