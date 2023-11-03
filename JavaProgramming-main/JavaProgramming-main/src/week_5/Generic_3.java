
package week_5;

import java.util.ArrayList;
import java.util.Scanner;



public class Generic_3 {
static final int MaxSize=100;
static ArrayList<StudentScore> scores = new ArrayList<StudentScore>(MaxSize);
	
	
	static int Menu; // 선택된 메뉴 번호 저장
	Scanner sc = new Scanner(System.in);
    static int sCount=0;
    static  boolean cont=true;
    static StudentScore ss;
    
	
	public static void main(String[] args) {
		System.out.println("20200284 김정현 환영");
		//변수 선언
		  
		
	   
		//메뉴 표시, 메뉴선택, 메뉴처리반복
		do {
			//메뉴표시
			display_menu();
			//메뉴 선택
			Menu = selected_menu();
			//메뉴처리
			handle_menu(Menu);
			
		}while(cont);
		
		
	}
	private static void display_menu() {
		
		System.out.println("====================");
		System.out.println("1. 조회(전체)");
	    System.out.println("2. 조회(개인별)");
	    System.out.println("3. 성적 입력");
	    System.out.println("4. 성적 수정");
	    System.out.println("5. 성적 삭제");
	    System.out.println("6. 성적 저장");
	    System.out.println("7. 프로그램 종료");
	    System.out.println("====================");
		
	}
	private static int selected_menu() {
		System.out.print("원하는 메뉴 번호를 입력하세요 : ");
		Scanner sc = new Scanner(System.in);
		int Menu=sc.nextInt();

		return Menu;
		
	}
	private static void handle_menu(int Menu) {
		
		switch(Menu) {
		case 1: retrieve_all();
		        break;
		case 2: retrieve_indi();	
		        break;
		case 3: input_score();
			    break;
		case 4: modify_score();
				break;
		case 5: delete_score();
		        break;
		case 6:break;
		case 7:stop();
		cont=false;
		break;
		
		default : System.out.println("잘못된 입력입니다."); break;
		
		}
		
	}
	//전체 학생 성적 출력
	private static void retrieve_all() {
	     for(int i = 0; i <= sCount; i++) {
            ss = scores.get(i);
           System.out.println(ss.getsID() + " " + ss.getsName() + " " + ss.getkor() + " " + ss.geteng() + " " + ss.getmath());}
         }
	    
	//개인 학생 성적 출력
	private static void retrieve_indi() {
		Scanner sc = new Scanner(System.in);
		System.out.print("원하는 학생의 학번을 입력하세요 : ");
	    int sid=sc.nextInt();
	    int idx=get_student_index(sid);
		int i=0;
		for(; i <= sCount; i++) {
	         ss = scores.get(i);
	         if (ss.getsID() == sid ) { System.out.println(ss.getsID() + " " + ss.getsName() + " " + ss.getkor() + " " + ss.geteng() + " " + ss.getmath()); break; }
	      }
		if(i > sCount) { System.out.println("해당 학번이 존재하지 않습니다 "); }   
	    }
	// 성적 입력
	private static void input_score() {
		Scanner sc = new Scanner(System.in);
		String sname;
		int kScore;
		int eScore;
		int mScore;	
		System.out.print("학생 학번 : "); 
		int sid= sc.nextInt();
        int idx=get_student_index(sid);
		if(idx>=0) System.out.println("중복된 학번입니다.");
		else {
			System.out.print("학생 이름 : "); sname= sc.next();
	        System.out.print("국어 성적 : "); kScore= sc.nextInt();
	        System.out.print("영어 성적 : "); eScore= sc.nextInt();
	        System.out.print("수학 성적 : "); mScore= sc.nextInt();
	        ss = new StudentScore(sid, sname, kScore, eScore, mScore);
	        scores.add(ss);
			}
		
     }
	//성적 수정
	private static void modify_score() {
		Scanner sc = new Scanner(System.in);
		int i=0;
		String sname;
		int kScore;
		int eScore;
		int mScore;	
		System.out.print("원하는 학생의 학번을 입력하세요 : ");
		int sid=sc.nextInt();
		int idx=get_student_index(sid);
		ss = new StudentScore();
		
		if(idx>=0) {
			 System.out.print("학생 이름 ("+ss.getsName()+") ?"); sname= sc.next();
   		  System.out.print("국어 성적 ("+ss.getkor()+") ?"); kScore= sc.nextInt();
   		  System.out.print("영어 성적 ("+ss.geteng()+") ?"); eScore= sc.nextInt();
   		  System.out.print("수학 성적 ("+ss.getmath()+") ?"); mScore= sc.nextInt();
   		 ss.setsID(sid); ss.setsName(sname); ss.setkor(kScore); ss.seteng(eScore); ss.setmath(mScore);
   		scores.set(idx, ss);
		}
		else System.out.println("해당 학번의 학생이 존재하지 않습니다.");
		
	}
	//성적 삭제
	private static void delete_score() {
		Scanner sc = new Scanner(System.in);
		System.out.print("학번을 입력하세요: ");
		int sid=sc.nextInt();
		int idx= get_student_index(sid);
		if(idx>=0) {
			scores.remove(idx);
  		  sCount--;
			}
		else System.out.println("해당 학번의 학생이 존재하지 않습니다.");
		
		
	}
	// 프로그램 종료
	private static void stop() {
		System.out.println("프로그램을 종료합니다.");
		System.out.println("20200284 김정현 GoodBye!");
	}
	//원하는 학생 인덱스 값 찾기
	private static int get_student_index (int sid){
		int i;
		for( i=0;i<sCount;i++) {
	    	  if(ss.getsID()==sid) {
	    		 break;
	    	  }
		}
		 if(i==sCount) return -1;
		 else return i;
		
	
}}
   class StudentScore {
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
    class List<T> {
 		// 필드 선언
 		T[] list;
 		private int cnt=-1;
 		int index=0;
 		static int size=5;
 	    // 생성자 선언
 		public List() {
 			this(size);
 		}
 	    public List(int size) {
 	    	this.size=size;
 	    	list = (T []) new Object[size]; }
 	    // 메서드 정의
		public int getCnt() { return cnt; }
		public T getAt(int idx) {return list[idx];} // List에 저장된 객체 중에 idx 위치의 객체 반환
 		public void setAt(int idx, T ob) {list[idx]=ob;} // List의 idx 위치의 객체를 ob로 치환
 		public void add(T ob)  { 
 			if(size<=index) {
 				throw new FullException("스택이 꽉찼습니다.");
 			}
 			else{list[index]=ob; index++;} }// List의 끝에 객체 ob를 추가
 		public void addAt(int idx, T ob) {
 			if(size<=index) {throw new FullException("스택이 꽉찼습니다.");}
 			else {for(int i = index-1;i>=idx;i--)
 			{
 				list[i+1]=list[i];
 					}
 			list[idx]=ob;
 			index++;
 		} }// List의 idx 위치에 객체 ob를 추가
 		public void deleteAt(int idx) {
 			list[idx]=null; 
 		     for(int i=idx;i<index-1;i++)
 			    {list[i]=list[i+1];
 			    list[i+1]=null;} 
 		     list[index-2]=null;
 		index--;   }// List의 idx 위치의 객체를 삭제 
 	}
    
 	class FullException extends RuntimeException {
 		public FullException() {
 			this("스택 꽉 참");
 		}
 		public FullException(String exception) {
 			super(exception);
 		}
 }

