package week_6;
////class List<T>{
////	// �ʵ� ����
////	private int size,i;
////	private T[] array;
////	// ������ ����
////	public List(int size) 
////	{this.size=size;
////	array=(T[])new Object[size];
////	i=0;
////	}
////	// �޼��� ����
////	public T getAt(int idx) {
////		if(array[idx]!=null) return array[idx]; 
////		else throw new NullException("���� ���� �ʴ� idx��");} // List�� ����� ��ü �߿� idx ��ġ�� ��ü ��ȯ
////	public void setAt(int idx, T ob) {
////		if(array[idx]!=null)array[idx]=ob;
////		else throw new NullException("���� ���� �ʴ� idx��");} // List�� idx ��ġ�� ��ü�� ob�� ġȯ
////	public void add(T ob) {
////		if(size-1>i) {
////			array[i]=ob;i++;
////		}
////		else throw new FullException("�뷮 �ʰ�");} // List�� ���� ��ü ob�� �߰�
////	public void addAt(int idx, T ob) {
////		if(size-1>i) {
////		for(int j=idx;j<size-1;j++)array[j+1]=array[j];
////		array[idx]=ob;i++;
////		} 
////		else {
////			throw new FullException("�뷮 �ʰ�");
////		}
////		}// List�� idx ��ġ�� ��ü ob�� �߰�
////	public void deleteAt(int idx) {
////		if(array[idx]!=null) {
////			for(int i=idx;i<size-1;i++) array[i]=array[i+1];
////			i--;
////		}else throw new NullException("�������� �ʴ� idx��");
////	} // List�� idx ��ġ�� ��ü�� ���� 
////}
////class FullException extends RuntimeException{
////	public FullException () {}
////	public FullException (String msg) {System.out.println(msg);}
////	}
////class NullException extends RuntimeException{
////	public NullException () {}
////	public NullException (String msg) {System.out.println(msg);}
////	}
////public class MainTest{
////	public static void main(String[]args) {
////		List<Integer> list=new List<Integer>(10);
////		list.add(1);
////		list.add(2);
////		list.add(3);
////		list.add(4);
////		System.out.println(list.getAt(3));
////		
////	}
////	
////}
//import java.util.Scanner;
//
//public class Main {
//
//    static List<Student> studentList = new List<>();
//    static int selMenu;
//    static int sid;
//    static int kScore, eScore, mScore;
//    static String sname;
//    static boolean exit = false;
//    static Scanner sc = new Scanner(System.in);
//
//    public static void main(String[] args) {
//        System.out.println("20xxxxxxxx �谳�˴� ȯ���մϴ�!");
//
//        do {
//            display_menu();
//
//            selMenu = select_menu();
//
//            handle_menu(selMenu);
//        } while(exit == false);
//        System.out.println("20xxxxxxxx �谳�˴� Good Bye!");
//        sc.close();
//    }
//
//    private static void display_menu() {
//        System.out.println("=============================================");
//        System.out.println("1. ��ȸ(��ü)");
//        System.out.println("2. ��ȸ(���κ�)");
//        System.out.println("3. ���� �Է�");
//        System.out.println("4. ���� ����");
//        System.out.println("5. ���� ����");
//        System.out.println("6. ���� ����");
//        System.out.println("7. ���α׷� ����");
//        System.out.println("=============================================");
//    }
//
//    private static int select_menu() {
//        System.out.print("���ϴ� �޴� ��ȣ�� �Է��ϼ��� : ");
//        int sel = sc.nextInt();
//        return sel;
//    }
//
//    private static void handle_menu(int menu) {
//        switch(menu) {
//            case 1:
//                retrieve_all();
//                break;
//            case 2:
//                retrieve_individual();
//                break;
//            case 3:
//                input_scores();
//                break;
//            case 4:
//                modify_scores();
//                break;
//            case 5:
//                delete_scores();
//                break;
//            case 6:
//                break;
//            case 7:
//                exit = true;
//                break;
//            default:
//        }
//    }
//
//    // ��ü �л����� ������ ���
//    private static void retrieve_all() {
//        int count = studentList.getCount();
//
//        for(int i = 0; i < count; i++) {
//            System.out.println(studentList.getAt(i).getSID() + " " + studentList.getAt(i).getSName() +
//                    " : ���� " + studentList.getAt(i).getKor() +
//                    ", ���� " + studentList.getAt(i).getEng() +
//                    ", ���� " + studentList.getAt(i).getMath());
//        }
//    }
//
//    // �Է��� �й��� ������ ���
//    private static void retrieve_individual() {
//        System.out.print("��ȸ�Ϸ��� �й��� �Է��Ͻʽÿ� : ");
//        sid = sc.nextInt();
//        int j = get_student_index(sid);
//        if(j == -1) {
//            System.out.println("������ �Էµ��� ���� �й��Դϴ�.");
//        }
//        else {
//            System.out.println(studentList.getAt(j).getSID() + " " + studentList.getAt(j).getSName() +
//                    " : ���� " + studentList.getAt(j).getKor() +
//                    ", ���� " + studentList.getAt(j).getEng() +
//                    ", ���� " + studentList.getAt(j).getMath());
//        }
//    }
//
//    // ���� �Է�
//    private static void input_scores() {
//        System.out.print("�л� �й� : ");
//        sid = sc.nextInt();
//        int k = get_student_index(sid);
//
//        if(k == -1) {
//            sc.nextLine(); // �л� �̸��� �޾Ƶ��� �� sc.nextLine()�� �۵����� �ʴ� ���� ����
//            System.out.print("�л� �̸� : ");
//            sname = sc.nextLine();
//            System.out.print("���� ���� : ");
//            kScore = sc.nextInt();
//            System.out.print("���� ���� : ");
//            eScore = sc.nextInt();
//            System.out.print("���� ���� : ");
//            mScore = sc.nextInt();
//
//            Student student = new Student(sid,sname,kScore,eScore,mScore);
//            studentList.add(student);
//        }
//        else {
//            System.out.println("������ �й��� ������ �̹� �ԷµǾ� �ֽ��ϴ�.");
//        }
//    }
//
//    // ���� ����
//    private static void modify_scores() {
//        System.out.print("���ϴ� �л��� �й��� ? ");
//        sid = sc.nextInt();
//        int l = get_student_index(sid);
//        if(l == -1) {
//            System.out.println("�ش� �й��� �л��� �������� �ʽ��ϴ�.");
//        }
//        else {
//            sc.nextLine(); // �л� �̸��� �޾Ƶ��� �� sc.nextLine()�� �۵����� �ʴ� ���� ����
//            System.out.print("�л� �̸� (" + studentList.getAt(l).getSName() + ", �̺��� �� ���� �� �״�� �Է�) ? ");
//            sname = sc.nextLine();
//            System.out.print("���� ���� (" +studentList.getAt(l).getKor() + ", �̺��� �� ���� �� �״�� �Է�) ? ");
//            kScore = sc.nextInt();
//            System.out.print("���� ���� (" + studentList.getAt(l).getEng() + ", �̺��� �� ���� �� �״�� �Է�) ? ");
//            eScore = sc.nextInt();
//            System.out.print("���� ���� (" + studentList.getAt(l).getMath() + ", �̺��� �� ���� �� �״�� �Է�) ? ");
//            mScore = sc.nextInt();
//
//            Student student = new Student(sid,sname,kScore,eScore,mScore);
//            studentList.setAt(l, student);
//        }
//    }
//
//    // ���� ����
//    private static void delete_scores() {
//        System.out.print("���ϴ� �л��� �й��� ? ");
//        sid = sc.nextInt();
//        int m = get_student_index(sid);
//        if(m == -1) {
//            System.out.println("�ش� �й��� �л��� �������� �ʽ��ϴ�.");
//        }
//        else {
//            studentList.deleteAt(m);
//        }
//    }
//
//    // �ش� �й��� �ε��� ã��
//    private static int get_student_index(int sid) {
//        int count = studentList.getCount();
//
//        for(int i = 0; i < count; i++) {
//            if(sid == studentList.getAt(i).getSID()) {
//                return i;
//            }
//        }
//        return -1;
//    }
//}
//class Student {
//    // �ʵ� ����
//    private int sID, kor, eng, math;
//    private String sName;
//
//    // ������ ����
//    public Student() {
//        this(0,"",0,0,0);
//    }
//
//    public Student(int id, String name, int kor, int eng, int math) {
//        sID = id;
//        sName = name;
//        this.kor = kor;
//        this.eng = eng;
//        this.math = math;
//    }
//
//    // getter ����
//    public int getSID() {
//        return sID;
//    }
//    public String getSName() {
//        return sName;
//    }
//    public int getKor() {
//        return kor;
//    }
//    public int getEng() {
//        return eng;
//    }
//    public int getMath() {
//        return math;
//    }
//
//    //setter ����
//    public void setSID(int sID) {
//        this.sID = sID;
//    }
//
//    public void setSName(String sName) {
//        this.sName = sName;
//    }
//    public void setKor(int kor) {
//        this.kor = kor;
//    }
//    public void setEng(int eng) {
//        this.eng = eng;
//    }
//    public void setMath(int math) {
//        this.math = math;
//    }
//
//    // �߰��� �޼����
//    public double getAverage() {
//        return ((double) (kor + eng + math)/3);
//    }
//}
//public class List<T> {
//    // �ʵ� ����
//    private int size;
//    private int pos;
//    private T[] array;
//
//    // ������ ����
//    public List() {
//        this(10);
//    }
//    public List(int size) {
//        this.size = size;
//        pos = -1;
//        array = (T[]) new Object[size];
//    }
//
//    // �޼��� ����
//    public int getCount(){
//        return pos + 1;
//    }
//
//    public T getAt(int idx) throws IndexOutOfSizeException{
//        if(idx <= pos) {
//            return array[idx];
//        } else {
//            throw new IndexOutOfSizeException();
//        }
//    }
//    public void setAt(int idx, T ob) throws IndexOutOfSizeException{
//        if(idx <= pos) {
//            array[idx] = ob;
//        } else {
//            throw new IndexOutOfSizeException();
//        }
//    }
//    public void add(T ob) {
//        if(pos < size-1) {
//            array[++pos] = ob;
//        } else {
//            throw new ArraySizeFullException();
//        }
//    }
//    public void addAt(int idx, T ob) {
//        if(idx > pos) {
//            throw new IndexOutOfSizeException();
//        }
//
//        if(pos < size-1) {
//            for(int i = pos; i >= idx; i--) {
//                array[i+1] = array[i];
//            }
//            array[idx] = ob;
//            pos++;
//        } else {
//            throw new ArraySizeFullException();
//        }
//    }
//    public void deleteAt(int idx) {
//        if(idx > pos) {
//            throw new IndexOutOfSizeException();
//        }
//
//        if(pos >= 0) {
//            for(int i = idx; i < pos; i++) {
//                array[i] = array[i+1];
//            }
//            pos--;
//        } else {
//            throw new IndexOutOfSizeException();
//        }
//    }
//}
//
//class IndexOutOfSizeException extends RuntimeException {
//    public IndexOutOfSizeException() {}
//    public IndexOutOfSizeException(String msg) {
//        super(msg);
//    }
//}
//class ArraySizeFullException extends RuntimeException {
//    public ArraySizeFullException() {}
//    public ArraySizeFullException(String msg) {
//        super(msg);
//    }
//}
//
