package week_5;

import java.util.Scanner;
import java.util.ArrayList;

public class as {
   static Scanner sc = new Scanner(System.in);
   static final int MaxSize = 100;   // ������ �� �ִ� �ִ� ũ��
   static ArrayList<Score> scores = new ArrayList<Score>(MaxSize);
//   static Score[] scores = new Score[MaxSize]; 
   static Score scr;
   static int selMenu; // ����ڰ� �Է��� �޴� ��ȣ
   static int sid, skor, seng, smath, cnt = -1;
   static String sname;
   static boolean exit = false;
   public static void main(String[] args) {
      System.out.println("20200561 ����â ȯ��");
      do {
         //�޴� ǥ��
         display_menu();
         //�޴� ����
         selMenu = select_menu();
         //�޴� ó��
         handle_menu(selMenu);
      } while (exit == false);
      sc.close();
      System.out.println("20200561 ����â Good Bye!\n");
   }
   private static void display_menu() {
      System.out.println("\n��������       menu       ��������");
      System.out.println("��          ��");
      System.out.println("��      1. ��ȸ(��ü)    ��");
      System.out.println("��          ��");
      System.out.println("��      2. ��ȸ(���κ�)    ��");
      System.out.println("��          ��");
      System.out.println("��      3. ���� �Է�        ��");
      System.out.println("��          ��");
      System.out.println("��      4. ���� ����       ��");
      System.out.println("��          ��");
      System.out.println("��      5. ���� ����         ��");
      System.out.println("��          ��");
      System.out.println("��      6. ���� ����         ��");
      System.out.println("��          ��");
      System.out.println("��      7. ���α׷� ����    ��");
      System.out.println("��          ��");
      System.out.println("����������������������������������������������������\n");      
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
         System.out.println("������ �����߽��ϴ� !!");
         break;
      case 7:
         System.out.println("���α׷��� �����մϴ� !!"); exit = true; break;
      default:
         System.out.println("���� !! �ùٸ� ���ڸ� �Է��� �ּ��� !!");
      }
   }
   // �л� ��ü�� ���� ���
   private static void retrieve_all() {
      if(cnt >= 0) {
         for(int i = 0; i <= cnt; i++) {
            scr = scores.get(i);
            System.out.println(scr.getId() + " " + scr.getName() + " " + scr.getKor() + " " + scr.getEng() + " " + scr.getMath());
         }
      }
      else { System.out.println("�Է� ������ �����ϴ� !!"); }
   
   }
   // ���ϴ� �л��� ���� ���
   private static void retrieve_individual() {
      System.out.print("ã�� �л��� �й��� �Է� >> ");
      sid = sc.nextInt();
      int i = 0;
      for(; i <= cnt; i++) {
         scr = scores.get(i);
         if (scr.getId() == sid ) { System.out.println(scr.getId() + " " + scr.getName() + " " + scr.getKor() + " " + scr.getEng() + " " + scr.getMath()); break; }
      }
      if(i > cnt) { System.out.println("���� !! �ش� �й��� �������� �ʽ��ϴ� !!"); }   
   }
   // ���ϴ� �л��� ���� �߰�
   private static void input_scores()  {
      System.out.print("�л��� �й� >> "); sid = sc.nextInt();
      int i = 0;
      for(i = 0; i <= cnt; i++) {
         scr = scores.get(i);
         if(scr.getId() == sid) { System.out.println("���� !! �ش� �й��� �̹� �����մϴ� !!"); break;}
      }
      if(i > cnt) {
         System.out.print("�л��� �̸� >> "); sname = sc.next();
         System.out.print("�л��� ���� ���� >> "); skor = sc.nextInt();
         System.out.print("�л��� ���� ���� >> "); seng = sc.nextInt();
         System.out.print("�л��� ���� ���� >> "); smath = sc.nextInt();
         // scores �迭 ����
         scr = new Score(sid, sname, skor, seng, smath);
         scores.add(scr);
      }
   }
   // ���ϴ� �л��� ���� ����
   private static void modify_scores() {
      System.out.print("ã�� �л��� �й��� �Է� >> ");
      sid = sc.nextInt();
      scr = new Score();
      int idx = get_student_index(sid);
      if (idx >= 0) {
         System.out.print("�л� �̸� (" + scr.getName() + ") �̺��� �� ���� ���� �״�� �Է� >> "); sname = sc.next();
         System.out.print("���� ���� (" + scr.getKor() + ") �̺��� �� ���� ���� �״�� �Է� >> "); skor = sc.nextInt();
         System.out.print("���� ���� (" + scr.getEng() + ") �̺��� �� ���� ���� �״�� �Է� >> "); seng = sc.nextInt();
         System.out.print("���� ���� (" + scr.getMath() + ") �̺��� �� ���� ���� �״�� �Է� >> "); smath = sc.nextInt();               
         scr.setId(sid); scr.setName(sname); scr.setKor(skor); scr.setEng(seng); scr.setMath(smath);
         System.out.println("������ ����Ǿ����ϴ� !! ����� ������ ������ �����ϴ�!!");
         System.out.println(scr.getId() + " " + scr.getName() + " " + scr.getKor() + " " + scr.getEng() + " " + scr.getMath());
         scores.set(idx, scr);
      }
      else { System.out.println("���� !! ã�� �л��� ������ �����ϴ� !!"); }
   }
   // ���ϴ� �л��� ���� ����
   private static void remove_scores() {
      System.out.print("ã�� �л��� �й��� �Է� >> ");
      sid = sc.nextInt();  
      int idx = get_student_index(sid);
      if (idx >= 0) {
         scores.remove(idx);
         System.out.println("�ش� �й��� ������ �ʱ�ȭ�Ǿ����ϴ� !!");   
      }
      else { System.out.println("���� !! ã�� �л��� ������ �����ϴ� !!"); }
   }
   // ���ϴ� �л��� �����ϴ��� Ȯ�� �� ��ġ ����
   private static int get_student_index(int sid) {
      int i = 0;
      for(i = 0; i <= cnt; i++) {
         scr = scores.get(i);
         if (scr.getId() == sid ) { break; }
      }
      if(i > cnt) { System.out.println("���� !! �ش� �й��� �������� �ʽ��ϴ� !!"); return -1;}
      return i;
   }
}
class Score {
   // Ŭ���� �� �ʵ� ����
   private int ID, Kor, Eng, Math;
   private String Name;
   
   // ������ ����
   public Score() { this(0,  "",  0,  0,  0); }
   public Score(int id, String name, int kor, int eng, int math) { ID = id; Name = name; Kor = kor; Eng = eng; Math = math; } 
   
   // getter ����
   public int getId() { return ID; } // { return this.ID; } ����
   public String getName() { return Name; }
   public int getKor() { return Kor; }
   public int getEng() { return Eng; } 
   public int getMath() { return Math; }
   
   // setter ����
   public void setInfo(int id, String name, int kor, int eng, int math) { ID = id; Name = name; Kor = kor; Eng = eng; Math = math; } // �ѹ��� ��� ���� �Է��ϴ� setter �Լ�
   public void setId(int id) { ID = id; } // { this.ID = id; } ����
   public void setName(String name) { Name = name; }
   public void setKor(int kor) { Kor = kor; }
   public void setEng(int eng) { Eng = eng; }
   public void setMath(int math) { Math = math; }
   
   // �߰� �Լ�
   public double getEvg() {
      return (Kor + Eng + Math) / 3.0;
   }
}