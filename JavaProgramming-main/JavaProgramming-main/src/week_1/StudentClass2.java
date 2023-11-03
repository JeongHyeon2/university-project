package week_1;

import java.util.Scanner;

public class StudentClass2 {
	static final int MaxSize=100;
	
	static StudentScore[] scores = new StudentScore[MaxSize];
	
	
	static int Menu; // 선택된 메뉴 번호 저장
	Scanner sc = new Scanner(System.in);
    static int sCount=0;
    static  boolean cont=true;
    
	
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
	    for(int i=0;i<sCount;i++) 
        System.out.println("이름 : "+scores[i].getsName()+ " 학번 : "+scores[i].getsID()+" 국어 점수 : "+scores[i].getkor()+" 영어 점수 : "+scores[i].geteng()+" 수학 점수 : "+scores[i].getmath());
	}
	//개인 학생 성적 출력
	private static void retrieve_indi() {
		Scanner sc = new Scanner(System.in);
		System.out.print("원하는 학생의 학번을 입력하세요 : ");
	    int sid=sc.nextInt();
	    int idx=get_student_index(sid);
		if(idx>=0) System.out.println("이름 : "+scores[idx].getsName()+ " 학번 : "+scores[idx].getsID()+" 국어 점수 : "+scores[idx].getkor()+" 영어 점수 : "+scores[idx].geteng()+" 수학 점수 : "+scores[idx].getmath());
		else System.out.println("해당 학번의 학생이 존재하지 않습니다.");
		
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
	        scores[sCount]=new StudentScore(sid, sname, kScore, eScore, mScore);
	        scores[sCount].setsID(sid); scores[sCount].setsName(sname); scores[sCount].setkor(kScore); scores[sCount].seteng(eScore); scores[sCount].setmath(mScore);
	        sCount++;
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
		if(idx>=0) {
			 System.out.print("학생 이름 ("+scores[i].getsName()+") ?"); sname= sc.next();
   		  System.out.print("국어 성적 ("+scores[i].getkor()+") ?"); kScore= sc.nextInt();
   		  System.out.print("영어 성적 ("+scores[i].geteng()+") ?"); eScore= sc.nextInt();
   		  System.out.print("수학 성적 ("+scores[i].getmath()+") ?"); mScore= sc.nextInt();
   		  scores[i].setsID(sid);; scores[i].setsName(sname); scores[i].setkor(kScore); scores[i].seteng(eScore); scores[i].setmath(mScore);
			
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
			for(int j=idx;j<(sCount-1);j++)
  		  {
				scores[j]=scores[j+1];
  		 }
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
	    	  if(scores[i].getsID()==sid) {
	    		 break;
	    	  }
		}
		 if(i==sCount) return -1;
		 else return i;
	}
}
		

	


