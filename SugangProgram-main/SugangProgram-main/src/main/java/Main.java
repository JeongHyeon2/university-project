import persistence.MyBatisConnectionFactory;
import persistence.dao.AppliedLecturesDAO;
import service.AppliedLecturesService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main<E> {

    public static void main(String[] args) {
//        SugangProgram_Admin sugangProgram_admin = new SugangProgram_Admin();
//        sugangProgram_admin.run();

//        network.SugangProgram_Student sugangProgram_student = new network.SugangProgram_Student();
//        sugangProgram_student.run();

//        SugangProgram_Professor sugangProgram_professor = new SugangProgram_Professor();
//        sugangProgram_professor.run();

//        ReadStudent();
    }

//    public static void ReadStudent() {
//        AppliedLecturesDAO dao = new AppliedLecturesDAO(MyBatisConnectionFactory.getSqlSessionFactory());
//        Scanner sc = new Scanner(System.in);
//        int page = 0;
//        Map<String, Object> param = new HashMap<>();
//        param.put("professor_id", "P0002");
//        param.put("lecture_id", "CS0035");
//        param.put("class_id", 1);
//
//        System.out.println(page + 1 + " " + "page (종료 : 0)");
//        List<Map<String,Object>> tmpList;
//
//        while(true){
//            page = sc.nextInt();
//            param.put("page", page);
//            tmpList = dao.readTest(param);
//            if(page == -1){
//                break;
//            }
//            else if(page == 0){
//                System.out.println(tmpList.toString());
//                System.out.println(page + " " + "page  (종료 : 0)");
//            }
//            else if(page == 1){
//                System.out.println(tmpList.toString());
//                System.out.println(page + " " + "page  (종료 : 0)");
//            }
//            else{
//                System.out.println(tmpList.toString());
//                System.out.println(page + " " + "page  (종료 : 0)");
//            }
//
//        }
//    }

}
