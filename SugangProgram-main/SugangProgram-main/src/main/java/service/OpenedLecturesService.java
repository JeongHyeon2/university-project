package service;

import persistence.dao.*;
import persistence.dto.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class OpenedLecturesService {

    private final OpenedLecturesDAO openedLecturesDAO;
    Scanner sc;

    public OpenedLecturesService(OpenedLecturesDAO openedLecturesDAO) {
        this.openedLecturesDAO = openedLecturesDAO;
        sc = new Scanner(System.in);
    }

    public void create() {
        OpenedLecturesDTO openedLecturesDTO;
        System.out.println("등록에 필요한 과목 정보: 과목코드, 분반코드, 교수코드, 건물, 강의실, 강의시간, 최대 수강 인원");
        String tmpStr = sc.nextLine();
        String[] arr = tmpStr.split(" ");
        int tmp1 = Integer.parseInt(arr[1]), tmp2 = Integer.parseInt(arr[6]);
        openedLecturesDTO = new OpenedLecturesDTO(arr[0],tmp1,arr[2],arr[3],arr[4],arr[5],tmp2);
        openedLecturesDAO.insert(openedLecturesDTO);
    }
    

    public List<OpenedLecturesDTO> read() {
        OpenedLecturesDTO openedLecturesDTO = new OpenedLecturesDTO();
        return openedLecturesDAO.selectAll(openedLecturesDTO);
    }

    public List<OpenedLecturesDTO> readByGrade(int grade) {
        return openedLecturesDAO.selectByGrade(grade);
    }

    public List<OpenedLecturesDTO> readByProfessor(String professorId) {
        ProfessorsDTO professorsDTO = new ProfessorsDTO();
        professorsDTO.setProfessor_id(professorId);
        return openedLecturesDAO.selectByProfessorId(professorsDTO);
    }

    public List<OpenedLecturesDTO> readByLectureClassId(String lectureId, int classId) {
        return openedLecturesDAO.selectByLectureAndClassId(lectureId,classId);
    }

    public List<Map<String, Object>> readByGradeAndProfessor() {
        System.out.println("선택할 학년과 교수 코드를 입력하세요.");
        Integer tG = sc.nextInt();
        String pId = sc.next();
        Map<String, Object> param = new HashMap<>();
        param.put("target_grade", tG);
        param.put("professor_id", pId);
        return openedLecturesDAO.selectByGradeAndProfessor(param);
    }

    public void update() {
        OpenedLecturesDTO openedLecturesDTO = new OpenedLecturesDTO();
        System.out.println("바꾸고 싶은 과목 코드와 분반 코드를 띄워서 입력하세요.");
        openedLecturesDTO.setLecture_id(sc.next());
        openedLecturesDTO.setClass_id(sc.nextInt());

        System.out.println("room_id 또는 capacity 를 선택하세요.");
        String input = sc.next();
        if(input.equals("room_id")) {
            System.out.println("건물 | 강의실");
            openedLecturesDTO.setBuilding_id(sc.next());
            openedLecturesDTO.setRoom_id(sc.next());
            openedLecturesDAO.updateLectureRoom(openedLecturesDTO);
        }
        else if(input.equals("capacity")) {
            System.out.println("바꿀 수강생 정원을 입력하세요.");
            openedLecturesDTO.setCapacity(sc.nextInt());
            openedLecturesDAO.updateCapacity(openedLecturesDTO);
        }
    }

    public void updateAll() {
        OpenedLecturesDTO openedLecturesDTO = new OpenedLecturesDTO();
        openedLecturesDTO.setLecture_id(sc.next());
        openedLecturesDTO.setClass_id(sc.nextInt());
        openedLecturesDTO.setProfessor_id(sc.next());
        openedLecturesDTO.setBuilding_id(sc.next());
        openedLecturesDTO.setRoom_id(sc.next());
        openedLecturesDTO.setSchedule(sc.next());
        openedLecturesDTO.setApplied_students_number(sc.nextInt());
        openedLecturesDTO.setCapacity(sc.nextInt());
        openedLecturesDTO.setCan_be_applied(sc.nextBoolean());
        openedLecturesDTO.setCan_modify_plan(sc.nextBoolean());
        openedLecturesDTO.setPlan(sc.next());
        openedLecturesDAO.updateAll(openedLecturesDTO);
    }

    public void updateProfessor() {
        OpenedLecturesDTO openedLecturesDTO = new OpenedLecturesDTO();
        openedLecturesDTO.setLecture_id(sc.next());
        openedLecturesDTO.setClass_id(sc.nextInt());
        openedLecturesDTO.setProfessor_id(sc.next());
        openedLecturesDAO.updateProfessor(openedLecturesDTO);
    }

    public void updateLectureRoom() {
        OpenedLecturesDTO openedLecturesDTO = new OpenedLecturesDTO();
        openedLecturesDTO.setLecture_id(sc.next());
        openedLecturesDTO.setClass_id(sc.nextInt());
        openedLecturesDTO.setBuilding_id(sc.next());
        openedLecturesDTO.setRoom_id(sc.next());
        openedLecturesDAO.updateLectureRoom(openedLecturesDTO);
    }

    public void updateSchedule() {
        OpenedLecturesDTO openedLecturesDTO = new OpenedLecturesDTO();
        openedLecturesDTO.setLecture_id(sc.next());
        openedLecturesDTO.setClass_id(sc.nextInt());
        openedLecturesDTO.setSchedule(sc.next());
        openedLecturesDAO.updateSchedule(openedLecturesDTO);
    }

    public void updateAppliedStudentsNum(String lectureId, int classId, int applied_students_number) {
        OpenedLecturesDTO openedLecturesDTO = new OpenedLecturesDTO();
        openedLecturesDTO.setApplied_students_number(applied_students_number);
        openedLecturesDTO.setLecture_id(lectureId);
        openedLecturesDTO.setClass_id(classId);
        openedLecturesDAO.updateAppliedStudentsNum(openedLecturesDTO);
    }

    public void updateCapacity() {
        OpenedLecturesDTO openedLecturesDTO = new OpenedLecturesDTO();
        openedLecturesDTO.setLecture_id(sc.next());
        openedLecturesDTO.setClass_id(sc.nextInt());
        openedLecturesDTO.setCapacity(sc.nextInt());
        openedLecturesDAO.updateCapacity(openedLecturesDTO);
    }

    public void updateCanBeAppliedTrue(int grade) {
        LecturesDTO lecturesDTO = new LecturesDTO();
        lecturesDTO.setTarget_grade(grade);
        openedLecturesDAO.updateCanBeAppliedTrue(lecturesDTO);
    }

    public void updateCanBeAppliedFalse(int grade) {
        LecturesDTO lecturesDTO = new LecturesDTO();
        lecturesDTO.setTarget_grade(grade);
        openedLecturesDAO.updateCanBeAppliedFalse(lecturesDTO);
    }

    public void updateConfirmedPlanTrue() {
        OpenedLecturesDTO openedLecturesDTO = new OpenedLecturesDTO();
        openedLecturesDTO.setCan_modify_plan(true);
        openedLecturesDAO.updateConfirmedPlanTrue();
    }

    public void updateConfirmedPlanFalse() {
        openedLecturesDAO.updateConfirmedPlanFalse();
    }

    public void updatePlan() {
        OpenedLecturesDTO openedLecturesDTO = new OpenedLecturesDTO();
        openedLecturesDTO.setLecture_id(sc.next());
        openedLecturesDTO.setClass_id(sc.nextInt());
        openedLecturesDTO.setPlan(sc.nextLine());
        //엔터없이 문단 뭉태이로 입력하세요 ??
        openedLecturesDAO.updatePlan(openedLecturesDTO);
    }

    public void delete() {
        OpenedLecturesDTO openedLecturesDTO = new OpenedLecturesDTO();
        System.out.println("삭제할 과목과 분반을 입력하세요.");
        openedLecturesDTO.setLecture_id(sc.next());
        openedLecturesDTO.setClass_id(sc.nextInt());
        openedLecturesDAO.delete(openedLecturesDTO);
    }

}
