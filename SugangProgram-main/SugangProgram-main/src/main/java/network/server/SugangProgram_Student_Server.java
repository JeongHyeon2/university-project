package network.server;
import network.Protocol;
import persistence.MyBatisConnectionFactory;
import persistence.dto.*;
import persistence.dao.*;
import service.AppliedLecturesService;
import service.OpenedLecturesService;
import view.View;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Scanner;

public class SugangProgram_Student_Server {
    private OutputStream os;
    private InputStream is;
    Scanner sc;
    private View view = new View();
    public SugangProgram_Student_Server(OutputStream os, InputStream is) {
        sc = new Scanner(System.in);
        this.os = os;
        this.is = is;
    }

    public void update_info(String id, String phNum) throws IOException {
        StudentsDAO studentsDAO = new StudentsDAO(MyBatisConnectionFactory.getSqlSessionFactory());
        StudentsDTO studentsDTO = new StudentsDTO();
        studentsDTO.setStudent_id(Integer.parseInt(id));
        studentsDTO.setPhone_num(phNum);
        studentsDAO.updatePhNum(studentsDTO);

    }
    public String read_info(String id){
        StudentsDAO studentsDAO = new StudentsDAO(MyBatisConnectionFactory.getSqlSessionFactory());
        StudentsDTO studentsDTO = new StudentsDTO();
        studentsDTO.setStudent_id(Integer.parseInt(id));
       return view.readAll( studentsDAO.selectById(studentsDTO));
    }
    public String read_timeTable(String id) {
        AppliedLecturesDAO appliedLecturesDAO = new AppliedLecturesDAO(MyBatisConnectionFactory.getSqlSessionFactory());
        AppliedLecturesDTO appliedLecturesDTO = new AppliedLecturesDTO();
        appliedLecturesDTO.setStudent_id(Integer.parseInt(id));
        List<OpenedLecturesDTO> list = appliedLecturesDAO.selectBySchedule(appliedLecturesDTO);
        if(list.size()==0) return null;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append("&amp과목코드:"+list.get(i).getLecture_id() + " ");
            sb.append("분반코드:"+list.get(i).getClass_id() + " ");
            sb.append("시간:"+list.get(i).getSchedule() + " \n&amp");
        }
        return sb.toString();
    }
    public String read_plan(String lId,String cId) throws IOException {
        OpenedLecturesDAO openedLecturesDAO = new OpenedLecturesDAO(MyBatisConnectionFactory.getSqlSessionFactory());
        List<OpenedLecturesDTO> list = openedLecturesDAO.selectPlanByLectureAndClassId(lId,Integer.parseInt(cId));
        StringBuilder sb = new StringBuilder();
        if(list.isEmpty()) return null;
        for(int i=0;i<list.size();i++){
            sb.append("&amp과목코드:"+list.get(i).getLecture_id()+" ");
            sb.append("분반코드:"+list.get(i).getClass_id()+" ");
            sb.append("강의계획서:"+list.get(i).getPlan()+" \n&amp");
        }
        return sb.toString();
    }
    public String opened_lectureReadAll() {
        OpenedLecturesDAO openedLecturesDAO = new OpenedLecturesDAO(MyBatisConnectionFactory.getSqlSessionFactory());
        OpenedLecturesService openedLecturesService = new OpenedLecturesService(openedLecturesDAO);
        return view.readAll(openedLecturesService.read());
    }
    public void deleteAppliedLecture(String id,String lectureId, String classId) throws IOException {
        try {
            AppliedLecturesDAO appliedLecturesDAO = new AppliedLecturesDAO(MyBatisConnectionFactory.getSqlSessionFactory());
            AppliedLecturesService appliedLecturesService = new AppliedLecturesService(appliedLecturesDAO);
            appliedLecturesService.delete(id, lectureId, Integer.parseInt(classId));
        } catch (Exception e) {
            Protocol protocol = new Protocol(Protocol.DELETE_RES, Protocol.DELETE_RES_FAIL);
            os.write(protocol.getPacket());
        }
    }
    public String readAppliedLecture(String id) {
        AppliedLecturesDAO appliedLecturesDAO = new AppliedLecturesDAO(MyBatisConnectionFactory.getSqlSessionFactory());
        AppliedLecturesService appliedLecturesService = new AppliedLecturesService(appliedLecturesDAO);
        return view.readAll(appliedLecturesService.readByStudent(id));
    }
    public void createAppliedLecture(String id,String lectureId, int classId) throws IOException {
        AppliedLecturesDAO appliedLecturesDAO = new AppliedLecturesDAO(MyBatisConnectionFactory.getSqlSessionFactory());
        AppliedLecturesService appliedLecturesService = new AppliedLecturesService(appliedLecturesDAO);
        OpenedLecturesDAO openedLecturesDAO = new OpenedLecturesDAO(MyBatisConnectionFactory.getSqlSessionFactory());
        OpenedLecturesService openedLecturesService = new OpenedLecturesService(openedLecturesDAO);

        List<AppliedLecturesDTO> list = appliedLecturesService.readByStudent(id);

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getLecture_id().equals(lectureId)) {
                Protocol protocol = new Protocol(Protocol.CREATE_RES, Protocol.CREATE_RES_FAIL);
                protocol.setData("&amp중복된 과목코드입니다!\n&amp");
                os.write(protocol.getPacket());
                return;
            }
        }
        List<OpenedLecturesDTO> list1 = appliedLecturesService.readBySchedule(id);
        List<OpenedLecturesDTO> list2 = openedLecturesService.readByLectureClassId(lectureId, classId);
        if (!list2.get(0).isCan_be_applied()) {
            Protocol protocol = new Protocol(Protocol.CREATE_RES, Protocol.CREATE_RES_FAIL);
            protocol.setData("&amp수강신청 기간이 아닙니다.\n&amp");
            os.write(protocol.getPacket());
            return;
        }

        if (list2.get(0).getApplied_students_number() >= list2.get(0).getCapacity()) {
            Protocol protocol = new Protocol(Protocol.CREATE_RES, Protocol.CREATE_RES_FAIL);
            protocol.setData("&amp수강 인원이 초과하였습니다!\n&amp");
            os.write(protocol.getPacket());
            return;
        }

        if (!list1.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < list1.size(); i++) {
                sb.append(list1.get(i).getSchedule());
                sb.append("/");
            }
            String[] scheduleArr1 = sb.toString().split("/");


            StringBuilder sb2 = new StringBuilder();
            for (int i = 0; i < list2.size(); i++) {
                sb2.append(list2.get(i).getSchedule());
                sb2.append("/");
            }

            String[] scheduleArr2 = sb2.toString().split("/");

            if (isDuplicatedSchedule(scheduleArr1, scheduleArr2)) {
                Protocol protocol = new Protocol(Protocol.CREATE_RES, Protocol.CREATE_RES_FAIL);
                protocol.setData("&amp중복된 시간입니다!\n&amp");
                os.write(protocol.getPacket());
                return;
            }

        }


        AppliedLecturesDTO appliedLecturesDTO = new AppliedLecturesDTO(lectureId, classId, Integer.parseInt(id));
        openedLecturesService.updateAppliedStudentsNum(lectureId, classId, (list2.get(0).getApplied_students_number() + 1));
        appliedLecturesService.create(appliedLecturesDTO);
        Protocol protocol = new Protocol(Protocol.CREATE_RES, Protocol.CREATE_RES_SUCCESS);
        os.write(protocol.getPacket());
    }
    private boolean isDuplicatedSchedule(String[] scheduleArr1, String[] scheduleArr2) {
        for (int i = 0; i < scheduleArr1.length; i++) {
            for (int j = 0; j < scheduleArr2.length; j++) {

                if (scheduleArr1[i].charAt(0) == scheduleArr2[j].charAt(0)) {
                    if (isDuplicatedSchedule(scheduleArr1[i], scheduleArr2[j])) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    private boolean isDuplicatedSchedule(String s1, String s2) {
        s1 = s1.substring(1);
        s2 = s2.substring(1);
        for (int i = 0; i < s1.length(); i++) {
            for (int j = 0; j < s2.length(); j++) {
                if (s1.charAt(i) == s2.charAt(j)) return true;
            }
        }
        return false;
    }
}
