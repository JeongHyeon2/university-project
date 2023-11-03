package week_5;

import java.util.Scanner;
import java.util.ArrayList;

public class as {
   static Scanner sc = new Scanner(System.in);
   static final int MaxSize = 100;   // 저장할 수 있는 최대 크기
   static ArrayList<Score> scores = new ArrayList<Score>(MaxSize);
//   static Score[] scores = new Score[MaxSize]; 
   static Score scr;
   static int selMenu; // 사용자가 입력한 메뉴 번호
   static int sid, skor, seng, smath, cnt = -1;
   static String sname;
   static boolean exit = false;
   public static void main(String[] args) {
      System.out.println("20200561 서범창 환영");
      do {
         //메뉴 표시
         display_menu();
         //메뉴 선택
         selMenu = select_menu();
         //메뉴 처리
         handle_menu(selMenu);
      } while (exit == false);
      sc.close();
      System.out.println("20200561 서범창 Good Bye!\n");
   }
   private static void display_menu() {
      System.out.println("\n┌──┤       menu       ├──┐");
      System.out.println("│          │");
      System.out.println("│      1. 조회(전체)    │");
      System.out.println("│          │");
      System.out.println("│      2. 조회(개인별)    │");
      System.out.println("│          │");
      System.out.println("│      3. 성적 입력        │");
      System.out.println("│          │");
      System.out.println("│      4. 성적 수정       │");
      System.out.println("│          │");
      System.out.println("│      5. 성적 삭제         │");
      System.out.println("│          │");
      System.out.println("│      6. 성적 저장         │");
      System.out.println("│          │");
      System.out.println("│      7. 프로그램 종료    │");
      System.out.println("│          │");
      System.out.println("└────────────────────────┘\n");      
   }
   private static int select_menu() {
      System.out.print(" Type >> ");
      int input = sc.nextInt();
      return input;
   }
   private static void handle_menu(int selMenu) {
      switch(selMenu) {
      case 1:
         retrieve_all(); break;
      case 2:
         retrieve_individual(); break;
      case 3:
         input_scores(); break;
      case 4:
         modify_scores(); break;
      case 5:
         remove_scores(); break;
      case 6:
         System.out.println("파일을 저장했습니다 !!");
         break;
      case 7:
         System.out.println("프로그램을 종료합니다 !!"); exit = true; break;
      default:
         System.out.println("오류 !! 올바른 숫자를 입력해 주세요 !!");
      }
   }
   // 학생 전체의 정보 출력
   private static void retrieve_all() {
      if(cnt >= 0) {
         for(int i = 0; i <= cnt; i++) {
            scr = scores.get(i);
            System.out.println(scr.getId() + " " + scr.getName() + " " + scr.getKor() + " " + scr.getEng() + " " + scr.getMath());
         }
      }
      else { System.out.println("입력 정보가 없습니다 !!"); }
   
   }
   // 원하는 학생의 정보 출력
   private static void retrieve_individual() {
      System.out.print("찾는 학생의 학번을 입력 >> ");
      sid = sc.nextInt();
      int i = 0;
      for(; i <= cnt; i++) {
         scr = scores.get(i);
         if (scr.getId() == sid ) { System.out.println(scr.getId() + " " + scr.getName() + " " + scr.getKor() + " " + scr.getEng() + " " + scr.getMath()); break; }
      }
      if(i > cnt) { System.out.println("오류 !! 해당 학번이 존재하지 않습니다 !!"); }   
   }
   // 원하는 학생의 정보 추가
   private static void input_scores()  {
      System.out.print("학생의 학번 >> "); sid = sc.nextInt();
      int i = 0;
      for(i = 0; i <= cnt; i++) {
         scr = scores.get(i);
         if(scr.getId() == sid) { System.out.println("오류 !! 해당 학번이 이미 존재합니다 !!"); break;}
      }
      if(i > cnt) {
         System.out.print("학생의 이름 >> "); sname = sc.next();
         System.out.print("학생의 국어 성적 >> "); skor = sc.nextInt();
         System.out.print("학생의 영어 성적 >> "); seng = sc.nextInt();
         System.out.print("학생의 수학 성적 >> "); smath = sc.nextInt();
         // scores 배열 선언
         scr = new Score(sid, sname, skor, seng, smath);
         scores.add(scr);
      }
   }
   // 원하는 학생의 정보 수정
   private static void modify_scores() {
      System.out.print("찾는 학생의 학번을 입력 >> ");
      sid = sc.nextInt();
      scr = new Score();
      int idx = get_student_index(sid);
      if (idx >= 0) {
         System.out.print("학생 이름 (" + scr.getName() + ") 미변경 시 현재 값을 그대로 입력 >> "); sname = sc.next();
         System.out.print("국어 성적 (" + scr.getKor() + ") 미변경 시 현재 값을 그대로 입력 >> "); skor = sc.nextInt();
         System.out.print("영어 성적 (" + scr.getEng() + ") 미변경 시 현재 값을 그대로 입력 >> "); seng = sc.nextInt();
         System.out.print("수학 성적 (" + scr.getMath() + ") 미변경 시 현재 값을 그대로 입력 >> "); smath = sc.nextInt();               
         scr.setId(sid); scr.setName(sname); scr.setKor(skor); scr.setEng(seng); scr.setMath(smath);
         System.out.println("정보가 변경되었습니다 !! 변경된 정보는 다음과 같습니다!!");
         System.out.println(scr.getId() + " " + scr.getName() + " " + scr.getKor() + " " + scr.getEng() + " " + scr.getMath());
         scores.set(idx, scr);
      }
      else { System.out.println("오류 !! 찾는 학생의 정보가 없습니다 !!"); }
   }
   // 원하는 학생의 정보 제거
   private static void remove_scores() {
      System.out.print("찾는 학생의 학번을 입력 >> ");
      sid = sc.nextInt();  
      int idx = get_student_index(sid);
      if (idx >= 0) {
         scores.remove(idx);
         System.out.println("해당 학번의 정보가 초기화되었습니다 !!");   
      }
      else { System.out.println("오류 !! 찾는 학생의 정보가 없습니다 !!"); }
   }
   // 원하는 학생이 존재하는지 확인 및 위치 리턴
   private static int get_student_index(int sid) {
      int i = 0;
      for(i = 0; i <= cnt; i++) {
         scr = scores.get(i);
         if (scr.getId() == sid ) { break; }
      }
      if(i > cnt) { System.out.println("오류 !! 해당 학번이 존재하지 않습니다 !!"); return -1;}
      return i;
   }
}
class Score {
   // 클래스 내 필드 선언
   private int ID, Kor, Eng, Math;
   private String Name;
   
   // 생성자 정의
   public Score() { this(0,  "",  0,  0,  0); }
   public Score(int id, String name, int kor, int eng, int math) { ID = id; Name = name; Kor = kor; Eng = eng; Math = math; } 
   
   // getter 정의
   public int getId() { return ID; } // { return this.ID; } 가능
   public String getName() { return Name; }
   public int getKor() { return Kor; }
   public int getEng() { return Eng; } 
   public int getMath() { return Math; }
   
   // setter 정의
   public void setInfo(int id, String name, int kor, int eng, int math) { ID = id; Name = name; Kor = kor; Eng = eng; Math = math; } // 한번에 모든 정보 입력하는 setter 함수
   public void setId(int id) { ID = id; } // { this.ID = id; } 가능
   public void setName(String name) { Name = name; }
   public void setKor(int kor) { Kor = kor; }
   public void setEng(int eng) { Eng = eng; }
   public void setMath(int math) { Math = math; }
   
   // 추가 함수
   public double getEvg() {
      return (Kor + Eng + Math) / 3.0;
   }
}