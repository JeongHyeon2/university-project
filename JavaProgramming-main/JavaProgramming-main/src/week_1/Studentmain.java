package week_1;

import java.util.Scanner;

public class Studentmain {
	public static void main(String[] args) {
		System.out.println("20200284 김정현 환영");
		
		//변수선언 (학번, 이름, 국어, 영어, 수학 성적)
		final int MaxSize=100;
		int[] sID = new int [MaxSize];
		String [] sName = new String[MaxSize];
		int [] Korean = new int [MaxSize];
		int [] English = new int [MaxSize];
		int [] Math = new int [MaxSize];
		int Menu; // 선택된 메뉴 번호 저장
		Scanner sc = new Scanner(System.in);
	    int sCount=0;
	    int sid,kScore,eScore,mScore;
	    String sname;
	    boolean cont=true;
		
		//메뉴표시
		do {
			//메뉴표시
			System.out.println("====================");
			System.out.println("1. 조회(전체)");
		    System.out.println("2. 조회(개인별)");
		    System.out.println("3. 성적 입력");
		    System.out.println("4. 성적 수정");
		    System.out.println("5. 성적 삭제");
		    System.out.println("6. 성적 저장");
		    System.out.println("7. 프로그램 종료");
		    System.out.println("====================");

			//메뉴선택
			System.out.print("원하는 메뉴 번호를 입력하세요 : ");
			Menu=sc.nextInt();
			
			//메뉴처리
			switch(Menu) {
			case 1: for(int i=0;i<sCount;i++) 
				System.out.println("이름 : "+sName[i]+ " 학번 : "+sID[i]+" 국어 점수 : "+Korean[i]+" 영어 점수 : "+English[i]+" 수학 점수 : "+Math[i]);
			    break;
			case 2: System.out.print("원하는 학생의 학번을 입력하세요 : ");
			      sid=sc.nextInt();
			      for(int i=0;i<sCount;i++) {
			    	  if(sid==sID[i]) { System.out.println("이름 : "+sName[i]+ " 학번 : "+sID[i]+" 국어 점수 : "+Korean[i]+" 영어 점수 : "+English[i]+" 수학 점수 : "+Math[i]);
			      break;
			    	  }
			      }
			     break;
			case 3: System.out.print("학생 학번 : "); sid= sc.nextInt();
			int i;
			       for(i =0;i<sCount;i++) {
			    	   if(sid==sID[i]) { System.out.print("중복된 학번입니다.");
			    	   break;}
			       }
			       if(i==sCount) {
			        System.out.print("학생 이름 : "); sname= sc.next();
			        System.out.print("국어 성적 : "); kScore= sc.nextInt();
			        System.out.print("영어 성적 : "); eScore= sc.nextInt();
			        System.out.print("수학 성적 : "); mScore= sc.nextInt();
			        sID[sCount] = sid; sName[sCount] = sname; Korean[sCount] = kScore; English[sCount] = eScore; Math[sCount] = mScore;
			        sCount++;
			       }
				break;
			case 4: System.out.print("원하는 학생의 학번을 입력하세요 : ");
		      sid=sc.nextInt();
		      for(i=0;i<sCount;i++) {
		    	  if(sid==sID[i]) {
		    		  System.out.print("학생 이름 ("+sName[i]+") ?"); sname= sc.next();
		    		  System.out.print("국어 성적 ("+Korean[i]+") ?"); kScore= sc.nextInt();
		    		  System.out.print("영어 성적 ("+English[i]+") ?"); eScore= sc.nextInt();
		    		  System.out.print("수학 성적 ("+Math[i]+") ?"); mScore= sc.nextInt();
				        sID[i] = sid; sName[i] = sname; Korean[i] = kScore; English[i] = eScore; Math[i] = mScore;
				        
		    	  };
		      
		      }
		      if(i==sCount) System.out.println("해당 학번의 학생이 존재하지 않습니다.");
				break;
			case 5: System.out.print("학번을 입력하세요: ");
			sid=sc.nextInt();
			for(i=0;i<sCount;i++) {
		    	  if(sid==sID[i]) {
		    		  for(int j=i;j<(sCount-1);j++)
		    		  {
		    			  sID[j]=sID[j+1];
		    			  sName[j]=sName[j+1];
		    			  Korean[j]=Korean[j+1];
		    			  English[j]=English[j+1];
		    			  Math[j]=Math[j+1];
		    		  }
		    		  sCount--;
		    	  }
			}
			 if(i==sCount) System.out.println("해당 학번의 학생이 존재하지 않습니다.");
				break;
			
			case 6:break;
			case 7:System.out.println("프로그램을 종료합니다.");
			System.out.println("20200284 김정현 GoodBye!");
			cont=false;
			break;
			default : System.out.println("잘못된 입력입니다."); break;
			
			}
			
			
		}while(cont);
		sc.close();
		
	}

}
