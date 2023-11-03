package week_6;
////class List<T>{
////	// 필드 선언
////	private int size,i;
////	private T[] array;
////	// 생성자 선언
////	public List(int size) 
////	{this.size=size;
////	array=(T[])new Object[size];
////	i=0;
////	}
////	// 메서드 정의
////	public T getAt(int idx) {
////		if(array[idx]!=null) return array[idx]; 
////		else throw new NullException("존재 하지 않는 idx값");} // List에 저장된 객체 중에 idx 위치의 객체 반환
////	public void setAt(int idx, T ob) {
////		if(array[idx]!=null)array[idx]=ob;
////		else throw new NullException("존재 하지 않는 idx값");} // List의 idx 위치의 객체를 ob로 치환
////	public void add(T ob) {
////		if(size-1>i) {
////			array[i]=ob;i++;
////		}
////		else throw new FullException("용량 초과");} // List의 끝에 객체 ob를 추가
////	public void addAt(int idx, T ob) {
////		if(size-1>i) {
////		for(int j=idx;j<size-1;j++)array[j+1]=array[j];
////		array[idx]=ob;i++;
////		} 
////		else {
////			throw new FullException("용량 초과");
////		}
////		}// List의 idx 위치에 객체 ob를 추가
////	public void deleteAt(int idx) {
////		if(array[idx]!=null) {
////			for(int i=idx;i<size-1;i++) array[i]=array[i+1];
////			i--;
////		}else throw new NullException("존재하지 않는 idx값");
////	} // List의 idx 위치의 객체를 삭제 
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
//        System.out.println("20xxxxxxxx 김개똥님 환영합니다!");
//
//        do {
//            display_menu();
//
//            selMenu = select_menu();
//
//            handle_menu(selMenu);
//        } while(exit == false);
//        System.out.println("20xxxxxxxx 김개똥님 Good Bye!");
//        sc.close();
//    }
//
//    private static void display_menu() {
//        System.out.println("=============================================");
//        System.out.println("1. 조회(전체)");
//        System.out.println("2. 조회(개인별)");
//        System.out.println("3. 성적 입력");
//        System.out.println("4. 성적 수정");
//        System.out.println("5. 성적 삭제");
//        System.out.println("6. 성적 저장");
//        System.out.println("7. 프로그램 종료");
//        System.out.println("=============================================");
//    }
//
//    private static int select_menu() {
//        System.out.print("원하는 메뉴 번호를 입력하세요 : ");
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
//    // 전체 학생들의 성적을 출력
//    private static void retrieve_all() {
//        int count = studentList.getCount();
//
//        for(int i = 0; i < count; i++) {
//            System.out.println(studentList.getAt(i).getSID() + " " + studentList.getAt(i).getSName() +
//                    " : 국어 " + studentList.getAt(i).getKor() +
//                    ", 영어 " + studentList.getAt(i).getEng() +
//                    ", 수학 " + studentList.getAt(i).getMath());
//        }
//    }
//
//    // 입력한 학번의 성적을 출력
//    private static void retrieve_individual() {
//        System.out.print("조회하려는 학번을 입력하십시오 : ");
//        sid = sc.nextInt();
//        int j = get_student_index(sid);
//        if(j == -1) {
//            System.out.println("성적이 입력되지 않은 학번입니다.");
//        }
//        else {
//            System.out.println(studentList.getAt(j).getSID() + " " + studentList.getAt(j).getSName() +
//                    " : 국어 " + studentList.getAt(j).getKor() +
//                    ", 영어 " + studentList.getAt(j).getEng() +
//                    ", 수학 " + studentList.getAt(j).getMath());
//        }
//    }
//
//    // 성적 입력
//    private static void input_scores() {
//        System.out.print("학생 학번 : ");
//        sid = sc.nextInt();
//        int k = get_student_index(sid);
//
//        if(k == -1) {
//            sc.nextLine(); // 학생 이름을 받아들일 때 sc.nextLine()이 작동하지 않는 문제 수정
//            System.out.print("학생 이름 : ");
//            sname = sc.nextLine();
//            System.out.print("국어 성적 : ");
//            kScore = sc.nextInt();
//            System.out.print("영어 성적 : ");
//            eScore = sc.nextInt();
//            System.out.print("수학 성적 : ");
//            mScore = sc.nextInt();
//
//            Student student = new Student(sid,sname,kScore,eScore,mScore);
//            studentList.add(student);
//        }
//        else {
//            System.out.println("동일한 학번의 성적이 이미 입력되어 있습니다.");
//        }
//    }
//
//    // 성적 수정
//    private static void modify_scores() {
//        System.out.print("원하는 학생의 학번은 ? ");
//        sid = sc.nextInt();
//        int l = get_student_index(sid);
//        if(l == -1) {
//            System.out.println("해당 학번의 학생이 존재하지 않습니다.");
//        }
//        else {
//            sc.nextLine(); // 학생 이름을 받아들일 때 sc.nextLine()이 작동하지 않는 문제 수정
//            System.out.print("학생 이름 (" + studentList.getAt(l).getSName() + ", 미변경 시 현재 값 그대로 입력) ? ");
//            sname = sc.nextLine();
//            System.out.print("국어 성적 (" +studentList.getAt(l).getKor() + ", 미변경 시 현재 값 그대로 입력) ? ");
//            kScore = sc.nextInt();
//            System.out.print("영어 성적 (" + studentList.getAt(l).getEng() + ", 미변경 시 현재 값 그대로 입력) ? ");
//            eScore = sc.nextInt();
//            System.out.print("수학 성적 (" + studentList.getAt(l).getMath() + ", 미변경 시 현재 값 그대로 입력) ? ");
//            mScore = sc.nextInt();
//
//            Student student = new Student(sid,sname,kScore,eScore,mScore);
//            studentList.setAt(l, student);
//        }
//    }
//
//    // 성적 삭제
//    private static void delete_scores() {
//        System.out.print("원하는 학생의 학번은 ? ");
//        sid = sc.nextInt();
//        int m = get_student_index(sid);
//        if(m == -1) {
//            System.out.println("해당 학번의 학생이 존재하지 않습니다.");
//        }
//        else {
//            studentList.deleteAt(m);
//        }
//    }
//
//    // 해당 학번의 인덱스 찾기
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
//    // 필드 선언
//    private int sID, kor, eng, math;
//    private String sName;
//
//    // 생성자 정의
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
//    // getter 정의
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
//    //setter 정의
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
//    // 추가의 메서드들
//    public double getAverage() {
//        return ((double) (kor + eng + math)/3);
//    }
//}
//public class List<T> {
//    // 필드 선언
//    private int size;
//    private int pos;
//    private T[] array;
//
//    // 생성자 선언
//    public List() {
//        this(10);
//    }
//    public List(int size) {
//        this.size = size;
//        pos = -1;
//        array = (T[]) new Object[size];
//    }
//
//    // 메서드 정의
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
