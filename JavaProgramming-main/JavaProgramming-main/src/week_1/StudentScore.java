package week_1;

public class StudentScore {
	//필드선언
	private int sID, kor, eng, math;
    private	String sName;
	
  	// 생성자 정의
	
    public StudentScore(int sid,String name, int kor, int eng, int math) {
		sID=sid; sName=name; this.kor=kor; this.eng=eng; this.math=math;
		}
    public StudentScore() {
    	this(0,"",0,0,0);
    }
    // getter
    public int getsID() {return this.sID;}
    public String getsName() {return this.sName;}
    public int getkor() {return this.kor;}
    public int geteng() {return this.eng;}
    public int getmath() {return this.math;}
    
    // setter
    public void setsID(int sid) {sID=sid;}
    public void setsName(String sname) {sName=sname;}
    public void setkor(int kor) {this.kor=kor;}
    public void seteng(int eng) {this.eng=eng;}
    public void setmath(int math) {this.math=math;}
    
    // 추가 메소드
    public double getAverage() {
    	return (this.kor + this.eng+ this.math)/3.0;
    }

   

}



	



