package service;

import persistence.dao.LecturesDAO;
import persistence.dao.*;
import persistence.dto.*;
import java.util.List;
import java.util.Scanner;

public class LecturesService {

    private final LecturesDAO lecturesDAO;
    Scanner sc;

    public LecturesService(LecturesDAO lecturesDAO) {
        this.lecturesDAO = lecturesDAO;
        sc = new Scanner(System.in);
    }

    public void create() {
        LecturesDTO lecturesDTO = new LecturesDTO();
        System.out.println("입력: 과목 코드 ㅣ 이수 학년 | 과목명 | 이수학기 | 학점 ");
        String [] arr = sc.nextLine().split(" ");
        lecturesDTO.setLecture_id(arr[0]);
        if(lecturesDAO.selectByLectureId(lecturesDTO.getLecture_id()) != null) {
            System.out.println("이미 존재하는 과목입니다.");
            return;
        }
        lecturesDTO.setTarget_grade(Integer.parseInt(arr[1]));
        lecturesDTO.setTitle(arr[2]);
        lecturesDTO.setSemester(Integer.parseInt(arr[3]));
        lecturesDTO.setCredit(Integer.parseInt(arr[4]));

        lecturesDAO.insert(lecturesDTO);
    }

    public List<LecturesDTO> read() {
        return lecturesDAO.selectAll();
    }

    public List<LecturesDTO> readByGrade(int grade){
        return lecturesDAO.selectByGrade(grade);
    }

    public void update() {
        LecturesDTO lecturesDTO = new LecturesDTO();
        System.out.println("과목명을 바꾸고 싶은 과목코드를 입력하세요.");
        System.out.print("과목 코드 입력 :");
        lecturesDTO.setLecture_id(sc.next());
        if(lecturesDAO.selectByLectureId(lecturesDTO.getLecture_id()) == null) {
            System.out.println("존재하지 않는 코드입니다.");
            return;
        }
        System.out.print("변경 과목명 입력: ");
        lecturesDTO.setTitle(sc.next());
        lecturesDAO.updateTitle(lecturesDTO);
    }

    public void updateAll() {
        LecturesDTO lecturesDTO = new LecturesDTO();
        lecturesDTO.setLecture_id(sc.next());
        lecturesDTO.setTarget_grade(sc.nextInt());
        lecturesDTO.setTitle(sc.next());
        lecturesDTO.setSemester(sc.nextInt());
        lecturesDTO.setCredit(sc.nextInt());
        lecturesDAO.updateAll(lecturesDTO);
    }

    public void updateTargetGrade() {
        LecturesDTO lecturesDTO = new LecturesDTO();
        lecturesDTO.setLecture_id(sc.next());
        lecturesDTO.setTarget_grade(sc.nextInt());
        lecturesDAO.updateTargetGrade(lecturesDTO);
    }

    public void updateTitle() {
        LecturesDTO lecturesDTO = new LecturesDTO();
        lecturesDTO.setLecture_id(sc.next());
        lecturesDTO.setTitle(sc.next());
        lecturesDAO.updateTitle(lecturesDTO);
    }

    public void updateSemester() {
        LecturesDTO lecturesDTO = new LecturesDTO();
        lecturesDTO.setLecture_id(sc.next());
        lecturesDTO.setSemester(sc.nextInt());
        lecturesDAO.updateSemester(lecturesDTO);
    }

    public void updateCredit() {
        LecturesDTO lecturesDTO = new LecturesDTO();
        lecturesDTO.setLecture_id(sc.next());
        lecturesDTO.setCredit(sc.nextInt());
        lecturesDAO.updateCredit(lecturesDTO);
    }

    public void delete() {
        System.out.println("삭제할 과목 코드를 입력하세요 ");
        String id = sc.next();
        if(lecturesDAO.selectByLectureId(id) == null) {
            System.out.println("존재하지 않는 코드입니다.");
            return;
        }
        lecturesDAO.delete(id);
    }

}
