
package week_5;

import java.util.ArrayList;
import java.util.Scanner;



public class Generic_3 {
static final int MaxSize=100;
static ArrayList<StudentScore> scores = new ArrayList<StudentScore>(MaxSize);
	
	
	static int Menu; // ���õ� �޴� ��ȣ ����
	Scanner sc = new Scanner(System.in);
    static int sCount=0;
    static  boolean cont=true;
    static StudentScore ss;
    
	
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
	     for(int i = 0; i <= sCount; i++) {
            ss = scores.get(i);
           System.out.println(ss.getsID() + " " + ss.getsName() + " " + ss.getkor() + " " + ss.geteng() + " " + ss.getmath());}
         }
	    
	//���� �л� ���� ���
	private static void retrieve_indi() {
		Scanner sc = new Scanner(System.in);
		System.out.print("���ϴ� �л��� �й��� �Է��ϼ��� : ");
	    int sid=sc.nextInt();
	    int idx=get_student_index(sid);
		int i=0;
		for(; i <= sCount; i++) {
	         ss = scores.get(i);
	         if (ss.getsID() == sid ) { System.out.println(ss.getsID() + " " + ss.getsName() + " " + ss.getkor() + " " + ss.geteng() + " " + ss.getmath()); break; }
	      }
		if(i > sCount) { System.out.println("�ش� �й��� �������� �ʽ��ϴ� "); }   
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
	        ss = new StudentScore(sid, sname, kScore, eScore, mScore);
	        scores.add(ss);
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
		ss = new StudentScore();
		
		if(idx>=0) {
			 System.out.print("�л� �̸� ("+ss.getsName()+") ?"); sname= sc.next();
   		  System.out.print("���� ���� ("+ss.getkor()+") ?"); kScore= sc.nextInt();
   		  System.out.print("���� ���� ("+ss.geteng()+") ?"); eScore= sc.nextInt();
   		  System.out.print("���� ���� ("+ss.getmath()+") ?"); mScore= sc.nextInt();
   		 ss.setsID(sid); ss.setsName(sname); ss.setkor(kScore); ss.seteng(eScore); ss.setmath(mScore);
   		scores.set(idx, ss);
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
			scores.remove(idx);
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
	    	  if(ss.getsID()==sid) {
	    		 break;
	    	  }
		}
		 if(i==sCount) return -1;
		 else return i;
		
	
}}
   class StudentScore {
	//�ʵ弱��
	private int sID, kor, eng, math;
    private	String sName;
	
  	// ������ ����
	
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
    
    // �߰� �޼ҵ�
    public double getAverage() {
    	return (this.kor + this.eng+ this.math)/3.0;
    }

   

}
    class List<T> {
 		// �ʵ� ����
 		T[] list;
 		private int cnt=-1;
 		int index=0;
 		static int size=5;
 	    // ������ ����
 		public List() {
 			this(size);
 		}
 	    public List(int size) {
 	    	this.size=size;
 	    	list = (T []) new Object[size]; }
 	    // �޼��� ����
		public int getCnt() { return cnt; }
		public T getAt(int idx) {return list[idx];} // List�� ����� ��ü �߿� idx ��ġ�� ��ü ��ȯ
 		public void setAt(int idx, T ob) {list[idx]=ob;} // List�� idx ��ġ�� ��ü�� ob�� ġȯ
 		public void add(T ob)  { 
 			if(size<=index) {
 				throw new FullException("������ ��á���ϴ�.");
 			}
 			else{list[index]=ob; index++;} }// List�� ���� ��ü ob�� �߰�
 		public void addAt(int idx, T ob) {
 			if(size<=index) {throw new FullException("������ ��á���ϴ�.");}
 			else {for(int i = index-1;i>=idx;i--)
 			{
 				list[i+1]=list[i];
 					}
 			list[idx]=ob;
 			index++;
 		} }// List�� idx ��ġ�� ��ü ob�� �߰�
 		public void deleteAt(int idx) {
 			list[idx]=null; 
 		     for(int i=idx;i<index-1;i++)
 			    {list[i]=list[i+1];
 			    list[i+1]=null;} 
 		     list[index-2]=null;
 		index--;   }// List�� idx ��ġ�� ��ü�� ���� 
 	}
    
 	class FullException extends RuntimeException {
 		public FullException() {
 			this("���� �� ��");
 		}
 		public FullException(String exception) {
 			super(exception);
 		}
 }

