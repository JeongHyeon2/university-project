package week_1;

import java.util.Scanner;

public class StudentClass2 {
	static final int MaxSize=100;
	
	static StudentScore[] scores = new StudentScore[MaxSize];
	
	
	static int Menu; // ���õ� �޴� ��ȣ ����
	Scanner sc = new Scanner(System.in);
    static int sCount=0;
    static  boolean cont=true;
    
	
	public static void main(String[] args) {
		System.out.println("20200284 ������ ȯ��");
		//���� ����
		  
		
	   
		//�޴� ǥ��, �޴�����, �޴�ó���ݺ�
		do {
			//�޴�ǥ��
			display_menu();
			//�޴� ����
			Menu = selected_menu();
			//�޴�ó��
			handle_menu(Menu);
			
		}while(cont);
		
		
	}
	private static void display_menu() {
		
		System.out.println("====================");
		System.out.println("1. ��ȸ(��ü)");
	    System.out.println("2. ��ȸ(���κ�)");
	    System.out.println("3. ���� �Է�");
	    System.out.println("4. ���� ����");
	    System.out.println("5. ���� ����");
	    System.out.println("6. ���� ����");
	    System.out.println("7. ���α׷� ����");
	    System.out.println("====================");
		
	}
	private static int selected_menu() {
		System.out.print("���ϴ� �޴� ��ȣ�� �Է��ϼ��� : ");
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
		
		default : System.out.println("�߸��� �Է��Դϴ�."); break;
		
		}
		
	}
	//��ü �л� ���� ���
	private static void retrieve_all() {
	    for(int i=0;i<sCount;i++) 
        System.out.println("�̸� : "+scores[i].getsName()+ " �й� : "+scores[i].getsID()+" ���� ���� : "+scores[i].getkor()+" ���� ���� : "+scores[i].geteng()+" ���� ���� : "+scores[i].getmath());
	}
	//���� �л� ���� ���
	private static void retrieve_indi() {
		Scanner sc = new Scanner(System.in);
		System.out.print("���ϴ� �л��� �й��� �Է��ϼ��� : ");
	    int sid=sc.nextInt();
	    int idx=get_student_index(sid);
		if(idx>=0) System.out.println("�̸� : "+scores[idx].getsName()+ " �й� : "+scores[idx].getsID()+" ���� ���� : "+scores[idx].getkor()+" ���� ���� : "+scores[idx].geteng()+" ���� ���� : "+scores[idx].getmath());
		else System.out.println("�ش� �й��� �л��� �������� �ʽ��ϴ�.");
		
	    }
	// ���� �Է�
	private static void input_score() {
		Scanner sc = new Scanner(System.in);
		String sname;
		int kScore;
		int eScore;
		int mScore;	
		System.out.print("�л� �й� : "); 
		int sid= sc.nextInt();
        int idx=get_student_index(sid);
		if(idx>=0) System.out.println("�ߺ��� �й��Դϴ�.");
		else {
			System.out.print("�л� �̸� : "); sname= sc.next();
	        System.out.print("���� ���� : "); kScore= sc.nextInt();
	        System.out.print("���� ���� : "); eScore= sc.nextInt();
	        System.out.print("���� ���� : "); mScore= sc.nextInt();
	        scores[sCount]=new StudentScore(sid, sname, kScore, eScore, mScore);
	        scores[sCount].setsID(sid); scores[sCount].setsName(sname); scores[sCount].setkor(kScore); scores[sCount].seteng(eScore); scores[sCount].setmath(mScore);
	        sCount++;
			}
		
     }
	//���� ����
	private static void modify_score() {
		Scanner sc = new Scanner(System.in);
		int i=0;
		String sname;
		int kScore;
		int eScore;
		int mScore;	
		System.out.print("���ϴ� �л��� �й��� �Է��ϼ��� : ");
		int sid=sc.nextInt();
		int idx=get_student_index(sid);
		if(idx>=0) {
			 System.out.print("�л� �̸� ("+scores[i].getsName()+") ?"); sname= sc.next();
   		  System.out.print("���� ���� ("+scores[i].getkor()+") ?"); kScore= sc.nextInt();
   		  System.out.print("���� ���� ("+scores[i].geteng()+") ?"); eScore= sc.nextInt();
   		  System.out.print("���� ���� ("+scores[i].getmath()+") ?"); mScore= sc.nextInt();
   		  scores[i].setsID(sid);; scores[i].setsName(sname); scores[i].setkor(kScore); scores[i].seteng(eScore); scores[i].setmath(mScore);
			
		}
		else System.out.println("�ش� �й��� �л��� �������� �ʽ��ϴ�.");
		
	}
	//���� ����
	private static void delete_score() {
		Scanner sc = new Scanner(System.in);
		System.out.print("�й��� �Է��ϼ���: ");
		int sid=sc.nextInt();
		int idx= get_student_index(sid);
		if(idx>=0) {
			for(int j=idx;j<(sCount-1);j++)
  		  {
				scores[j]=scores[j+1];
  		 }
  		  sCount--;
			}
		else System.out.println("�ش� �й��� �л��� �������� �ʽ��ϴ�.");
		
		
	}
	// ���α׷� ����
	private static void stop() {
		System.out.println("���α׷��� �����մϴ�.");
		System.out.println("20200284 ������ GoodBye!");
	}
	//���ϴ� �л� �ε��� �� ã��
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
		

	


