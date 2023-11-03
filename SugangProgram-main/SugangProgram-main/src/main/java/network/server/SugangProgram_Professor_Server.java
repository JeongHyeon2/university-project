package network.server;

import network.Protocol;
import persistence.MyBatisConnectionFactory;
import persistence.dao.AccountsDAO;
import persistence.dao.AppliedLecturesDAO;
import persistence.dao.OpenedLecturesDAO;
import persistence.dao.ProfessorsDAO;
import persistence.dto.AccountsDTO;
import persistence.dto.AppliedLecturesDTO;
import persistence.dto.OpenedLecturesDTO;
import persistence.dto.ProfessorsDTO;
import service.AccountsService;
import service.OpenedLecturesService;
import service.ProfessorsService;
import view.View;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Scanner;

public class SugangProgram_Professor_Server {
    private OutputStream os;
    private InputStream is;
    Scanner sc;
    private View view = new View();
    public SugangProgram_Professor_Server(OutputStream os, InputStream is) {
        sc = new Scanner(System.in);
        this.os = os;
        this.is = is;
    }

    // server method -------------

    // 담당 강의 계획서 조회
    public String read_openedLectures_professor(String id) {
        OpenedLecturesDAO openedLecturesDAO = new OpenedLecturesDAO(MyBatisConnectionFactory.getSqlSessionFactory());
        OpenedLecturesService openedLecturesService = new OpenedLecturesService(openedLecturesDAO);
        return view.readAll(openedLecturesService.readByProfessor(id));
    }
    public String read_info(String id){
        ProfessorsDAO professorsDAO = new ProfessorsDAO(MyBatisConnectionFactory.getSqlSessionFactory());
        ProfessorsDTO professorsDTO = new ProfessorsDTO();
        professorsDTO.setProfessor_id(id);
        return view.readAll(professorsDAO.selectById(professorsDTO));
    }

    // 강의 계획서 조회
    public String read_plan_professor(String id) {
        OpenedLecturesDAO openedLecturesDAO = new OpenedLecturesDAO(MyBatisConnectionFactory.getSqlSessionFactory());
        OpenedLecturesService openedLecturesService = new OpenedLecturesService(openedLecturesDAO);
        StringBuilder sb = new StringBuilder();
        List<OpenedLecturesDTO> list=  openedLecturesService.readByProfessor(id);
        if(list.isEmpty()) return null;
        for(int i=0;i<list.size();i++){
            sb.append("&amp과목코드:"+list.get(i).getLecture_id()+" ");
            sb.append("분반코드:"+list.get(i).getClass_id()+" ");
            sb.append("강의계획서:"+list.get(i).getPlan()+"\n&amp");
        }
        return sb.toString();

    }

    // 수강 신청 학생 목록 조회
    public String read_applid_student_professor(String pId,String lectureId, String classId) {
        AppliedLecturesDAO appliedLecturesDAO = new AppliedLecturesDAO(MyBatisConnectionFactory.getSqlSessionFactory());
        return view.readAll(appliedLecturesDAO.selectStudent(pId, lectureId, Integer.parseInt(classId)));
    }

    //강의 계획서 수정
    public void updata_plan_professor(String lectureId, String classId, String plan ) {
        OpenedLecturesDAO openedLecturesDAO = new OpenedLecturesDAO(MyBatisConnectionFactory.getSqlSessionFactory());
        OpenedLecturesDTO openedLecturesDTO = new OpenedLecturesDTO();
        openedLecturesDTO.setLecture_id(lectureId);
        openedLecturesDTO.setClass_id(Integer.parseInt(classId));
        openedLecturesDTO.setPlan(plan);
        openedLecturesDAO.updatePlan(openedLecturesDTO);
    }

    //시간표 조회
    public String read_timeTable_professor(String id) {
        OpenedLecturesDAO openedLecturesDAO = new OpenedLecturesDAO(MyBatisConnectionFactory.getSqlSessionFactory());

        List<OpenedLecturesDTO> schedule_dto = openedLecturesDAO.selectScheduleByProfessorId(id);

        StringBuilder sb = new StringBuilder();
        for(int i=0;i<schedule_dto.size();i++){
            sb.append("&amp과목코드:"+schedule_dto.get(i).getLecture_id());
            sb.append(" 분반코드:"+schedule_dto.get(i).getClass_id());
            sb.append(" 시간:"+schedule_dto.get(i).getSchedule()+"\n&amp");
        }
        return sb.toString();

    }

    //개인 정보 변경
    public void updata_personal_data_professor(String id, String name, String phonNum, String birth_date) {
        ProfessorsDAO professorsDAO = new ProfessorsDAO(MyBatisConnectionFactory.getSqlSessionFactory());
        ProfessorsDTO professorsDTO = new ProfessorsDTO();
        professorsDTO.setProfessor_id(id);
        professorsDTO.setName(name);
        professorsDTO.setPhone_num(phonNum);
        professorsDTO.setBirth_date(birth_date);
        professorsDAO.updateAll(professorsDTO);
    }

    public String read_timeTable(String id){
        AppliedLecturesDAO appliedLecturesDAO = new AppliedLecturesDAO(MyBatisConnectionFactory.getSqlSessionFactory());
        AppliedLecturesDTO appliedLecturesDTO = new AppliedLecturesDTO();
        appliedLecturesDTO.setStudent_id(Integer.parseInt(id));
        List<OpenedLecturesDTO> list = appliedLecturesDAO.selectBySchedule(appliedLecturesDTO);
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<list.size();i++){
            sb.append(list.get(i).getSchedule()+" ");
        }
        return sb.toString();
    }
}
