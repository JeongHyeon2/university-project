package network.server;

import service.*;
import view.*;
import persistence.MyBatisConnectionFactory;
import persistence.dao.*;
import persistence.dto.*;

import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class SugangProgram_Admin_Server {
    private OutputStream os;
    private InputStream is;
    Scanner sc;
    private View view = new View();
    public SugangProgram_Admin_Server(OutputStream os, InputStream is) {
        sc = new Scanner(System.in);
        this.os = os;
        this.is = is;
    }
    public void deleteAdmin(String id){
        AdminsDAO adminsDAO = new AdminsDAO(MyBatisConnectionFactory.getSqlSessionFactory());
        adminsDAO.delete(new AdminsDTO(Integer.parseInt(id)));
        AccountsDAO accountsDAO = new AccountsDAO(MyBatisConnectionFactory.getSqlSessionFactory());
        AccountsDTO accountsDTO = new AccountsDTO();
        accountsDTO.setId(id);
        accountsDAO.delete(accountsDTO);
    }
    public String readAccount(){
        AccountsDAO accountsDAO = new AccountsDAO(MyBatisConnectionFactory.getSqlSessionFactory());
        List<AccountsDTO> tmpList = accountsDAO.selectAll();
        return view.readAll(tmpList);
    }
    public void createAdmin(String id, String pwd){
        AccountsDAO accountsDAO = new AccountsDAO(MyBatisConnectionFactory.getSqlSessionFactory());
        accountsDAO.insert(new AccountsDTO(id,pwd,1));
        AdminsDAO adminsDAO = new AdminsDAO(MyBatisConnectionFactory.getSqlSessionFactory());
        adminsDAO.insert(new AdminsDTO(Integer.parseInt(id)));
    }
    public void professorCreate(String pId, String name, String phNum, String bD) {
        ProfessorsDAO professorsDAO = new ProfessorsDAO(MyBatisConnectionFactory.getSqlSessionFactory());
        ProfessorsDTO professorsDTO = new ProfessorsDTO(pId, name, phNum, bD);
        professorsDAO.insert(professorsDTO);

        AccountsDAO accountsDAO = new AccountsDAO(MyBatisConnectionFactory.getSqlSessionFactory());
        AccountsService accountsService = new AccountsService(accountsDAO);
        accountsService.create(pId, bD.replaceAll("-", ""), 2);
    }
    public String professorRead() {
        ProfessorsDAO professorsDAO = new ProfessorsDAO(MyBatisConnectionFactory.getSqlSessionFactory());
        ProfessorsService professorsService = new ProfessorsService(professorsDAO);
        List<ProfessorsDTO> all = professorsService.read();
        return view.readAll(all);
    }
    public void professorDelete(String pId) {
        ProfessorsDAO professorsDAO = new ProfessorsDAO(MyBatisConnectionFactory.getSqlSessionFactory());
        ProfessorsDTO professorsDTO = new ProfessorsDTO();
        professorsDTO.setProfessor_id(pId);
        professorsDAO.deleteProfessor(professorsDTO);

        AccountsDAO accountsDAO = new AccountsDAO(MyBatisConnectionFactory.getSqlSessionFactory());
        AccountsDTO accountsDTO= new AccountsDTO();
        accountsDTO.setId(pId);
        accountsDAO.delete(accountsDTO);
    }
    public void periodUpdate(int grade,String start,String end) {

        OpenedLecturesDAO openedLecturesDAO = new OpenedLecturesDAO(MyBatisConnectionFactory.getSqlSessionFactory());
        OpenedLecturesService openedLecturesService = new OpenedLecturesService(openedLecturesDAO);

        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date time = new Date();
        String time1 = format1.format(time);
        String courseDate = time1.substring(0, 10).replace("-", "");
        boolean b = Integer.parseInt(start) - Integer.parseInt(courseDate) <= 0 && Integer.parseInt(courseDate) - Integer.parseInt(end) <= 0;
        if(grade==0){

            if (b) {
                openedLecturesDAO.updateConfirmedPlanTrue();
            } else {
                openedLecturesDAO.updateConfirmedPlanFalse();
            }

            PeriodsDAO periodsDAO = new PeriodsDAO(MyBatisConnectionFactory.getSqlSessionFactory());
            PeriodsDTO periodsDTO = new PeriodsDTO();
            periodsDTO.setGrade(0);
            periodsDTO.setStart_date(start);
            periodsDTO.setEnd_date(end);
            periodsDAO.updateDates(periodsDTO);

        }else {

            if (b) {
                openedLecturesService.updateCanBeAppliedTrue(grade);
            } else {
                openedLecturesService.updateCanBeAppliedFalse(grade);
            }
            PeriodsDAO periodsDAO = new PeriodsDAO(MyBatisConnectionFactory.getSqlSessionFactory());
            PeriodsService periodService = new PeriodsService(periodsDAO);
            periodService.update(grade, start, end);
        }
    }
    public void lectureDelete(String lId){
        LecturesDAO lecturesDAO = new LecturesDAO(MyBatisConnectionFactory.getSqlSessionFactory());
        lecturesDAO.delete(lId);
    }

    public void studentDelete(String sId){
        StudentsDAO studentsDAO = new StudentsDAO(MyBatisConnectionFactory.getSqlSessionFactory());
        StudentsDTO studentsDTO = new StudentsDTO();
        studentsDTO.setStudent_id(Integer.parseInt(sId));
        studentsDAO.deleteStudent(studentsDTO);

        AccountsDAO accountsDAO = new AccountsDAO(MyBatisConnectionFactory.getSqlSessionFactory());
        AccountsDTO accountsDTO= new AccountsDTO();
        accountsDTO.setId(sId);
        accountsDAO.delete(accountsDTO);
    }
    public void lectureCreate(String lId, String cId, String name, String s, String c ) {
        LecturesDAO lecturesDAO = new LecturesDAO(MyBatisConnectionFactory.getSqlSessionFactory());
        LecturesDTO lecturesDTO = new LecturesDTO(lId,Integer.parseInt(cId),name,Integer.parseInt(s),Integer.parseInt(c));
        lecturesDAO.insert(lecturesDTO);
    }
    public String lectureRead() {
        LecturesDAO lecturesDAO = new LecturesDAO(MyBatisConnectionFactory.getSqlSessionFactory());
        return view.readAll(lecturesDAO.selectAll());
    }
    public void lectureUpdate(String lecture_id, String target_grade,String title, String semester, String credit) {
        LecturesDAO lecturesDAO = new LecturesDAO(MyBatisConnectionFactory.getSqlSessionFactory());
        LecturesDTO lecturesDTO = new LecturesDTO(lecture_id,Integer.parseInt(target_grade),title,Integer.parseInt(semester),Integer.parseInt(credit));
        lecturesDAO.updateAll(lecturesDTO);
    }
    public void studentCreate(String sId, String name, String grade, String bD, String phNum) {
        StudentsDAO studentsDAO = new StudentsDAO(MyBatisConnectionFactory.getSqlSessionFactory());
        StudentsDTO studentsDTO = new StudentsDTO(Integer.parseInt(sId), name, Integer.parseInt(grade), bD, phNum);
        studentsDAO.insert(studentsDTO);

        AccountsDAO accountsDAO = new AccountsDAO(MyBatisConnectionFactory.getSqlSessionFactory());
        AccountsService accountsService = new AccountsService(accountsDAO);
        accountsService.create(sId, bD.replaceAll("-", ""), 3);
    }

    public String studentRead() {
        StudentsDAO studentsDAO = new StudentsDAO(MyBatisConnectionFactory.getSqlSessionFactory());
        StudentsService studentsService = new StudentsService(studentsDAO);
        List<StudentsDTO> all = studentsService.read();
        return view.readAll(all);
    }
    public String periodsRead(){
        PeriodsDAO periodsDAO = new PeriodsDAO(MyBatisConnectionFactory.getSqlSessionFactory());
        List<PeriodsDTO> list = periodsDAO.selectAll(new PeriodsDTO());
        return view.readAll(list);
    }
    //opened_lecture server
    // 개설과목 생성 요청
    public void opened_lectureCreate(String lId, String cId, String pId, String bId, String rId, String schedule, String capacity) {
        OpenedLecturesDAO openedLecturesDAO = new OpenedLecturesDAO(MyBatisConnectionFactory.getSqlSessionFactory());
        OpenedLecturesDTO openedLecturesDTO;
        openedLecturesDTO = new OpenedLecturesDTO(lId,Integer.parseInt(cId),pId,bId,rId,schedule,Integer.parseInt(capacity));
        openedLecturesDAO.insert(openedLecturesDTO);
    }

    // 개설과목 조회 요청
    public String opened_lectureReadAll() {

        OpenedLecturesDAO openedLecturesDAO = new OpenedLecturesDAO(MyBatisConnectionFactory.getSqlSessionFactory());
        OpenedLecturesService openedLecturesService = new OpenedLecturesService(openedLecturesDAO);
        return view.readAll(openedLecturesService.read());
    }

    // 개설과목 삭제 요청
    public void opened_lectureDelete(String lId, String cId) {
        OpenedLecturesDAO openedLecturesDAO = new OpenedLecturesDAO(MyBatisConnectionFactory.getSqlSessionFactory());
        OpenedLecturesDTO openedLecturesDTO = new OpenedLecturesDTO();
        openedLecturesDTO.setLecture_id(lId);
        openedLecturesDTO.setClass_id(Integer.parseInt(cId));
        openedLecturesDAO.delete(openedLecturesDTO);
    }
    public void opened_lectureUpdate(String lId, String cId, String pId, String bId, String rId, String schedule, String capacity){
        OpenedLecturesDAO openedLecturesDAO = new OpenedLecturesDAO(MyBatisConnectionFactory.getSqlSessionFactory());
        OpenedLecturesDTO openedLecturesDTO;

        openedLecturesDTO = new OpenedLecturesDTO(lId,Integer.parseInt(cId),pId,bId,rId,schedule,Integer.parseInt(capacity));
        openedLecturesDAO.delete(openedLecturesDTO);
        openedLecturesDAO.insert(openedLecturesDTO);
    }

//    public void opened_lectureUpdate(String lId, String cId, String pId, String bId, String rId, String schedule, String capacity){
//        OpenedLecturesDAO openedLecturesDAO = new OpenedLecturesDAO(MyBatisConnectionFactory.getSqlSessionFactory());
//        OpenedLecturesDTO openedLecturesDTO;
//        openedLecturesDTO = new OpenedLecturesDTO(lId,Integer.parseInt(cId),pId,bId,rId,schedule,Integer.parseInt(capacity));
//        openedLecturesDAO.updateAll(openedLecturesDTO);
//    }
}
