package week_1;

import java.util.Scanner;

public class StudentClass {
	static final int MaxSize=100;
	static int[] sID = new int [MaxSize];
	static String [] sName = new String[MaxSize];
	static int [] Korean = new int [MaxSize];
	static int [] English = new int [MaxSize];
	static int [] Math = new int [MaxSize];
	static int Menu; // ���õ� �޴� ��ȣ ����
	Scanner sc = new Scanner(System.in);
    static int sCount=0;
    static  boolean cont=true;
    
	
	public static void main(String[] args) {
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
		int sid;
		Scanner sc = new Scanner(System.in);
		String sname;
		int kScore;
		int eScore;
		int mScore;
		switch(Menu) {
		case 1: for(int i=0;i<sCount;i++) 
			System.out.println("�̸� : "+sName[i]+ " �й� : "+sID[i]+" ���� ���� : "+Korean[i]+" ���� ���� : "+English[i]+" ���� ���� : "+Math[i]);
		    break;
		case 2: System.out.print("���ϴ� �л��� �й��� �Է��ϼ��� : ");
		      sid=sc.nextInt();
		      for(int i=0;i<sCount;i++) {
		    	  if(sid==sID[i]) { System.out.println("�̸� : "+sName[i]+ " �й� : "+sID[i]+" ���� ���� : "+Korean[i]+" ���� ���� : "+English[i]+" ���� ���� : "+Math[i]);
		      break;
		    	  }
		      }
		     break;
		case 3: System.out.print("�л� �й� : "); sid= sc.nextInt();
		int i;
		       for(i =0;i<sCount;i++) {
		    	   if(sid==sID[i]) { System.out.println("�ߺ��� �й��Դϴ�.");
		    	   break;}
		       }
		       if(i==sCount) {
		        System.out.print("�л� �̸� : "); sname= sc.next();
		        System.out.print("���� ���� : "); kScore= sc.nextInt();
		        System.out.print("���� ���� : "); eScore= sc.nextInt();
		        System.out.print("���� ���� : "); mScore= sc.nextInt();
		        sID[sCount] = sid; sName[sCount] = sname; Korean[sCount] = kScore; English[sCount] = eScore; Math[sCount] = mScore;
		        sCount++;
		       }
			break;
		case 4: System.out.print("���ϴ� �л��� �й��� �Է��ϼ��� : ");
	      sid=sc.nextInt();
	      for(i=0;i<sCount;i++) {
	    	  if(sid==sID[i]) {
	    		  System.out.print("�л� �̸� ("+sName[i]+") ?"); sname= sc.next();
	    		  System.out.print("���� ���� ("+Korean[i]+") ?"); kScore= sc.nextInt();
	    		  System.out.print("���� ���� ("+English[i]+") ?"); eScore= sc.nextInt();
	    		  System.out.print("���� ���� ("+Math[i]+") ?"); mScore= sc.nextInt();
			        sID[i] = sid; sName[i] = sname; Korean[i] = kScore; English[i] = eScore; Math[i] = mScore;
			        
	    	  };
	      
	      }
	      if(i==sCount) System.out.println("�ش� �й��� �л��� �������� �ʽ��ϴ�.");
			break;
		case 5: System.out.print("�й��� �Է��ϼ���: ");
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
		 if(i==sCount) System.out.println("�ش� �й��� �л��� �������� �ʽ��ϴ�.");
			break;
		
		case 6:break;
		case 7:System.out.println("���α׷��� �����մϴ�.");
		cont=false;
		break;
		
		
		default : System.out.println("�߸��� �Է��Դϴ�.");
		 break;
		
		}
		
	}
	

}
