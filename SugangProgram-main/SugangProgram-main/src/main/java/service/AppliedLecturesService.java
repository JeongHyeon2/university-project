package service;

import persistence.MyBatisConnectionFactory;
import persistence.dao.AppliedLecturesDAO;
import persistence.dao.OpenedLecturesDAO;
import persistence.dto.AppliedLecturesDTO;
import persistence.dto.OpenedLecturesDTO;
import persistence.dto.StudentsDTO;
import persistence.dao.*;
import persistence.dto.*;

import java.util.List;
import java.util.Scanner;

public class AppliedLecturesService {

    private final AppliedLecturesDAO appliedLecturesDAO;
    Scanner sc;

    public AppliedLecturesService(AppliedLecturesDAO appliedLecturesDAO) {
        this.appliedLecturesDAO = appliedLecturesDAO;
        sc = new Scanner(System.in);
    }

    public void create(AppliedLecturesDTO appliedLecturesDTO) {
        appliedLecturesDAO.insert(appliedLecturesDTO);
    }

    public List<AppliedLecturesDTO> read() {
        AppliedLecturesDTO appliedLecturesDTO = new AppliedLecturesDTO();
        return appliedLecturesDAO.selectAll(appliedLecturesDTO);
    }

    public List<AppliedLecturesDTO> readByStudent() {
        AppliedLecturesDTO appliedLecturesDTO = new AppliedLecturesDTO();
        System.out.print("학번:");
        appliedLecturesDTO.setStudent_id(sc.nextInt());
        return appliedLecturesDAO.selectByStudent(appliedLecturesDTO);
    }

    public List<OpenedLecturesDTO> readBySchedule(String studentId) {
        AppliedLecturesDTO appliedLecturesDTO = new AppliedLecturesDTO();
        appliedLecturesDTO.setStudent_id(Integer.parseInt(studentId));
        return appliedLecturesDAO.selectBySchedule(appliedLecturesDTO);
    }

    public List<AppliedLecturesDTO> readByStudent(String studentId) {
        AppliedLecturesDTO appliedLecturesDTO = new AppliedLecturesDTO();
        appliedLecturesDTO.setStudent_id(Integer.parseInt(studentId));
        return appliedLecturesDAO.selectByStudent(appliedLecturesDTO);
    }

    public List<StudentsDTO> readStudentByProfessor(String professorId,String lectureId,int classId,int page) {
        OpenedLecturesDTO openedLecturesDTO= new OpenedLecturesDTO();
        openedLecturesDTO.setLecture_id(lectureId);
        openedLecturesDTO.setClass_id(classId);
        openedLecturesDTO.setProfessor_id(professorId);
        return appliedLecturesDAO.selectStudentsByProfessor(openedLecturesDTO);
    }

    public void delete(String studentId,String lectureId,int classId) {
        OpenedLecturesDAO openedLecturesDAO = new OpenedLecturesDAO(MyBatisConnectionFactory.getSqlSessionFactory());
        OpenedLecturesService openedLecturesService = new OpenedLecturesService(openedLecturesDAO);
        List<OpenedLecturesDTO> list= openedLecturesService.readByLectureClassId(lectureId,classId);
        openedLecturesService.updateAppliedStudentsNum(lectureId,classId,(list.get(0).getApplied_students_number()-1));
        AppliedLecturesDTO appliedLecturesDTO = new AppliedLecturesDTO(lectureId,classId,Integer.parseInt(studentId));
        appliedLecturesDAO.delete(appliedLecturesDTO);
    }

//    public List<Map<String, Object>> readTest(int page) {
//        System.out.println("교수 코드, 강의 코드, 분반 코드, 페이지");
//        Map<String, Object> param = new HashMap<>();
//        param.put("professor_id", "P0002");
//        param.put("lecture_id", "CS0035");
//        param.put("class_id", 1);
//        param.put("page", page);
//        return appliedLecturesDAO.readTest(param);
//    }

}
