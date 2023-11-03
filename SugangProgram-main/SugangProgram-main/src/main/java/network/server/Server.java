package network.server;

import network.Protocol;
import persistence.MyBatisConnectionFactory;
import persistence.dao.AccountsDAO;
import service.AccountsService;

import java.net.*;
import java.io.*;
import java.util.List;
import java.util.Scanner;

import persistence.dto.*;

public class Server extends Thread {
    Scanner sc = new Scanner(System.in);
    private Socket socket;

    //생성자
    public Server(Socket connectedClientSocket) {
        //Client와 통신할 객체를 초기값으로 받아 할당합니다.
        this.socket = connectedClientSocket;
    }

    public void run()  {
        try {
        //    ServerSocket sSocket = new ServerSocket(4444);
          //  Socket socket = sSocket.accept();
            System.out.println("클라이언트 접속 대기중...");
            System.out.println("클라이언트 접속");

            // 바이트 배열로 전송할 것이므로 필터 스트림 없이 Input/OutputStream만 사용해도 됨
            OutputStream os = socket.getOutputStream();
            InputStream is = socket.getInputStream();
            SugangProgram_Student_Server sugangProgram_studentServer = null;
            SugangProgram_Admin_Server sugangProgramAdminServer = null;
            SugangProgram_Professor_Server sugangProgram_professorServer = null;
            // 로그인 정보 요청용 프로토콜 객체 생성 및 전송

            Protocol protocol = new Protocol(Protocol.LOGIN_REQ, Protocol.PC_UNDEFINED);
            os.write(protocol.getPacket());

            boolean program_stop = false;

            while (true) {
                protocol = new Protocol();            // 새 Protocol 객체 생성 (기본 생성자)
                byte[] buf = protocol.getPacket();    // 기본 생성자로 생성할 때에는 바이트 배열의 길이가 1000바이트로 지정됨
                is.read(buf);

                int packetType = buf[0];
                int packetCode = buf[1];// 수신 데이터에서 패킷 타입 얻음
                int dataLength = buf[2];
                protocol.setPacket(packetType, packetCode, dataLength, buf);    // 패킷 타입을 Protocol 객체의 packet 멤버변수에 buf를 복사

                switch (packetType) {
                    case Protocol.PT_EXIT:            // 프로그램 종료 수신
                        protocol = new Protocol(Protocol.PT_EXIT, Protocol.PC_UNDEFINED);
                        os.write(protocol.getPacket());
                        System.out.println("클라이언트 종료");
                        break;
                    case Protocol.LOGIN_RES:        // 로그인 정보 수신
                        System.out.println("클라이언트가 " + "로그인 정보를 보냈습니다");
                        String data = protocol.getData();
                        System.out.println(data);
                        String[] arr = data.split(" ");
                        switch (login(arr[0], arr[1])) {
                            case -1: //로그인 실패
                                protocol = new Protocol(Protocol.RES_LOGIN_FAIL, Protocol.PC_UNDEFINED);
                                os.write(protocol.getPacket());
                                break;
                            case 1: //로그인 성공 1.관리자 2.교수 3.학생
                                protocol = new Protocol(Protocol.RES_LOGIN_SUCCESS, Protocol.PC_UNDEFINED);
                                protocol.setData("1");
                                sugangProgramAdminServer = new SugangProgram_Admin_Server(os, is);
                                os.write(protocol.getPacket());
                                break;
                            case 2:
                                protocol = new Protocol(Protocol.RES_LOGIN_SUCCESS, Protocol.PC_UNDEFINED);
                                protocol.setData("2");
                                sugangProgram_professorServer = new SugangProgram_Professor_Server(os, is);
                                os.write(protocol.getPacket());
                                break;
                            case 3:
                                protocol = new Protocol(Protocol.RES_LOGIN_SUCCESS, Protocol.PC_UNDEFINED);
                                protocol.setData("3");
                                sugangProgram_studentServer = new SugangProgram_Student_Server(os, is);
                                os.write(protocol.getPacket());
                                break;
                        }
                        break;
                    case Protocol.CREATE_REQ:
                        switch (packetCode) {
                            case Protocol.CREATE_REQ_admins://어드민 생성
                                System.out.println("클라이언트가 어드민 계정 정보를 보냈습니다");
                                data = protocol.getData();
                                System.out.println(data);
                                arr = data.split(" ");
                                sugangProgramAdminServer.createAdmin(arr[0],arr[1]);
                                protocol = new Protocol(Protocol.CREATE_RES, Protocol.CREATE_RES_SUCCESS);
                                os.write(protocol.getPacket());
                                break;
                            case Protocol.CREATE_REQ_applied_lectures://수강신청
                                System.out.println("클라이언트가 수강 신청 정보를 보냈습니다");
                                data = protocol.getData();
                                System.out.println(data);
                                arr = data.split(" ");
                                sugangProgram_studentServer.createAppliedLecture(arr[0], arr[1], Integer.parseInt(arr[2]));
                                break;
                            case Protocol.CREATE_REQ_students://학생 생성
                                System.out.println("클라이언트가 학생 생성 요청을 하였습니다");
                                data = protocol.getData();
                                System.out.println(data);
                                arr = data.split(" ");
                                sugangProgramAdminServer.studentCreate(arr[0], arr[1], arr[2], arr[3], arr[4]);
                                protocol = new Protocol(Protocol.CREATE_RES, Protocol.CREATE_RES_SUCCESS);
                                os.write(protocol.getPacket());
                                break;
                            case Protocol.CREATE_REQ_lectures://교과목 생성
                                System.out.println("클라이언트가 교과목 생성 요청을 하였습니다");
                                data = protocol.getData();
                                System.out.println(data);
                                arr = data.split(" ");
                                sugangProgramAdminServer.lectureCreate(arr[0], arr[1], arr[2], arr[3], arr[4]);
                                protocol = new Protocol(Protocol.CREATE_RES, Protocol.CREATE_RES_SUCCESS);
                                os.write(protocol.getPacket());
                                break;
                            case Protocol.CREATE_REQ_professors://교수 생성
                                System.out.println("클라이언트가 교수 생성 요청을 하였습니다");
                                data = protocol.getData();
                                System.out.println(data);
                                arr = data.split(" ");
                                sugangProgramAdminServer.professorCreate(arr[0], arr[1], arr[2], arr[3]);
                                protocol = new Protocol(Protocol.CREATE_RES, Protocol.CREATE_RES_SUCCESS);
                                os.write(protocol.getPacket());
                                break;
                            case Protocol.CREATE_REQ_opened_lectures:// 개설교과목 생성
                                System.out.println("클라이언트가 개설과목 생성 요청을 하였습니다");
                                data = protocol.getData();
                                System.out.println(data);
                                arr = data.split(" ");
                                sugangProgramAdminServer.opened_lectureCreate(arr[0], arr[1], arr[2], arr[3], arr[4], arr[5], arr[6]);

                                protocol = new Protocol(Protocol.CREATE_RES, Protocol.CREATE_RES_SUCCESS);
                                os.write(protocol.getPacket());
                                break;
                        }
                        break;
                    case Protocol.READ_REQ:
                        switch (packetCode) {
                            case Protocol.READ_REQ_all_account: // 계정 조회
                                System.out.println("클라이언트가 계정 조회 요청을 보냈습니다");
                                data = sugangProgramAdminServer.readAccount();
                                print(data);
                                if (data == null || data.equals("")) {
                                    protocol = new Protocol(Protocol.READ_RES, Protocol.READ_RES_FAIL);
                                } else {
                                    protocol = new Protocol(Protocol.READ_RES, Protocol.READ_RES_all_account);
                                    protocol.setData(data);
                                }
                                os.write(protocol.getPacket());
                                break;
                            case Protocol.READ_REQ_applied_lecture_students: // 수강 신청 조회
                                System.out.println("클라이언트가 수강 신청 조회 요청을 보냈습니다");
                                data = protocol.getData();
                                System.out.println(data);
                                data = sugangProgram_studentServer.readAppliedLecture(data);
                                print(data);
                                if (data == null || data.equals("")) {
                                    protocol = new Protocol(Protocol.READ_RES, Protocol.READ_RES_FAIL);
                                } else {
                                    protocol = new Protocol(Protocol.READ_RES, Protocol.READ_RES_applied_lecture_students);
                                    protocol.setData(data);
                                }
                                os.write(protocol.getPacket());
                                break;
                            case Protocol.READ_REQ_all_opened_lecture:// 개설교과목 조회
                                System.out.println("클라이언트가 개설교과목 조회 요청을 보냈습니다");
                                data = sugangProgram_studentServer.opened_lectureReadAll();
                                print(data);
                                if (data == null) {
                                    protocol = new Protocol(Protocol.READ_RES, Protocol.READ_RES_FAIL);
                                } else {
                                    protocol = new Protocol(Protocol.READ_RES, Protocol.READ_RES_all_opened_lecture);
                                    protocol.setData(data);
                                }
                                os.write(protocol.getPacket());
                                break;
                            case Protocol.READ_REQ_plan_by_student:// 강의계획서 조회
                                System.out.println("클라이언트가 강의계획서 조회 요청을 보냈습니다");
                                data = protocol.getData();
                                System.out.println(data);
                                arr = data.split(" ");
                                data = sugangProgram_studentServer.read_plan(arr[0],arr[1]);
                                print(data);
                                if (data == null) {
                                    protocol = new Protocol(Protocol.READ_RES, Protocol.READ_RES_FAIL);
                                } else {
                                    protocol = new Protocol(Protocol.READ_RES, Protocol.READ_RES_plan_by_student);
                                    protocol.setData(data);
                                }
                                os.write(protocol.getPacket());
                                break;
                            case Protocol.READ_REQ_time_table_by_student: // 시간표 조회
                                System.out.println("클라이언트가 시간표 조회 요청을 보냈습니다");
                                data = protocol.getData();
                                System.out.println(data);
                                data = sugangProgram_studentServer.read_timeTable(data);
                                print(data);
                                if (data == null || data.equals("")) {
                                    protocol = new Protocol(Protocol.READ_RES, Protocol.READ_RES_FAIL);
                                } else {
                                    protocol = new Protocol(Protocol.READ_RES, Protocol.READ_RES_time_table_by_student);
                                    protocol.setData(data);
                                }
                                os.write(protocol.getPacket());
                                break;
                            case Protocol.READ_REQ_all_students: // 전체 학생 조회
                                System.out.println("클라이언트가 전체 학생 조회 요청을 보냈습니다");
                                data = protocol.getData();
                                System.out.println(data);
                                data = sugangProgramAdminServer.studentRead();
                                print(data);
                                if (data == null || data.equals("")) {
                                    protocol = new Protocol(Protocol.READ_RES, Protocol.READ_RES_FAIL);
                                } else {
                                    protocol = new Protocol(Protocol.READ_RES, Protocol.READ_RES_all_students);
                                    protocol.setData(data);
                                }
                                os.write(protocol.getPacket());
                                break;
                            case Protocol.READ_REQ_all_lecture: // 교과목 조회
                                System.out.println("클라이언트가 전체 교과목 조회 요청을 보냈습니다");
                                data = protocol.getData();
                                System.out.println(data);
                                data = sugangProgramAdminServer.lectureRead();
                                print(data);
                                if (data == null || data.equals("")) {
                                    protocol = new Protocol(Protocol.READ_RES, Protocol.READ_RES_FAIL);
                                } else {
                                    protocol = new Protocol(Protocol.READ_RES, Protocol.READ_RES_all_lecture);
                                    protocol.setData(data);
                                }
                                os.write(protocol.getPacket());
                                break;
                            case Protocol.READ_REQ_all_professor: // 전체 교수 조회
                                System.out.println("클라이언트가 전체 교수 조회 요청을 보냈습니다");
                                data = sugangProgramAdminServer.professorRead();
                                print(data);
                                if (data == null || data.equals("")) {
                                    protocol = new Protocol(Protocol.READ_RES, Protocol.READ_RES_FAIL);
                                } else {
                                    protocol = new Protocol(Protocol.READ_RES, Protocol.READ_RES_all_professor);
                                    protocol.setData(data);
                                }
                                os.write(protocol.getPacket());
                                break;

                            case Protocol.READ_REQ_time_table_by_professor: // 교수 시간표
                                System.out.println("클라이언트가 교수 시간표 조회 요청을 보냈습니다");
                                data = protocol.getData();
                                data = sugangProgram_professorServer.read_timeTable_professor(data);
                                print(data);
                                if (data == null || data.equals("")) {
                                    protocol = new Protocol(Protocol.READ_RES, Protocol.READ_RES_FAIL);
                                } else {
                                    protocol = new Protocol(Protocol.READ_RES, Protocol.READ_RES_time_table_by_professor);
                                    protocol.setData(data);
                                }
                                os.write(protocol.getPacket());
                                break;
                            case Protocol.READ_REQ_one_student: // 학생 정보 조회
                                System.out.println("클라이언트가 학생 정보 조회 요청을 보냈습니다");
                                data = protocol.getData();
                                data = sugangProgram_studentServer.read_info(data);
                                print(data);
                                if (data == null || data.equals("")) {
                                    protocol = new Protocol(Protocol.READ_RES, Protocol.READ_RES_FAIL);
                                } else {
                                    protocol = new Protocol(Protocol.READ_RES, Protocol.READ_RES_one_student);
                                    protocol.setData(data);
                                }
                                os.write(protocol.getPacket());
                                break;
                            case Protocol.READ_REQ_one_professor: //교수 정보 조회
                                System.out.println("클라이언트가 교수 정보 조회 요청을 보냈습니다");
                                data = protocol.getData();
                                data = sugangProgram_professorServer.read_info(data);
                                print(data);
                                if (data == null || data.equals("")) {
                                    protocol = new Protocol(Protocol.READ_RES, Protocol.READ_RES_FAIL);
                                } else {
                                    protocol = new Protocol(Protocol.READ_RES, Protocol.READ_RES_one_professor);
                                    protocol.setData(data);
                                }
                                os.write(protocol.getPacket());
                                break;
                            case Protocol.READ_REQ_opened_lecture_professor: // 담담 과목 조회
                                System.out.println("클라이언트가 담당과목 조회 요청을 보냈습니다");
                                data = protocol.getData();
                                System.out.println(data);
                                data = sugangProgram_professorServer.read_openedLectures_professor(data);
                                print(data);
                                if (data == null || data.equals("")) {
                                    protocol = new Protocol(Protocol.READ_RES, Protocol.READ_RES_FAIL);
                                } else {
                                    protocol = new Protocol(Protocol.READ_RES, Protocol.READ_RES_opened_lecture_professor);
                                    protocol.setData(data);
                                }
                                os.write(protocol.getPacket());
                                break;

                            case Protocol.READ_REQ_plan: // 강의계획서 조회
                                System.out.println("클라이언트가 담당 과목 강의계획서 조회 요청을 보냈습니다");
                                data = protocol.getData();
                                System.out.println(data);
                                data = sugangProgram_professorServer.read_plan_professor(data);
                                print(data);
                                if (data == null || data.equals("")) {
                                    protocol = new Protocol(Protocol.READ_RES, Protocol.READ_RES_FAIL);
                                } else {
                                    protocol = new Protocol(Protocol.READ_RES, Protocol.READ_RES_plan);
                                    protocol.setData(data);
                                }
                                os.write(protocol.getPacket());
                                break;

                            case Protocol.READ_REQ_all_building: // 담당 교과목 수강신청 학생 목록 (building으로 대신 함)
                                System.out.println("클라이언트가 담당 과목 담당 교과목 수강신청 학생 목록 조회 요청을 보냈습니다");

                                data = protocol.getData();
                                System.out.println(data);
                                arr = data.split(" ");

                                data = sugangProgram_professorServer.read_applid_student_professor(arr[0], arr[1], arr[2]);
                                print(data);
                                if (data == null || data.equals("")) {
                                    protocol = new Protocol(Protocol.READ_RES, Protocol.READ_RES_FAIL);
                                }
                                else {
                                    protocol = new Protocol(Protocol.READ_RES, Protocol.READ_RES_one_student);
                                    protocol.setData(data);
                                }

                                os.write(protocol.getPacket());
                                break;
                            case Protocol.READ_REQ_all_period: //기간 조회 요청
                                System.out.println("클라이언트가 기간 조회 요청을 보냈습니다");
                                data = protocol.getData();
                                System.out.println(data);
                                data = sugangProgramAdminServer.periodsRead();
                                print(data);
                                if (data == null || data.equals("")) {
                                    protocol = new Protocol(Protocol.READ_RES, Protocol.READ_RES_FAIL);
                                } else {
                                    protocol = new Protocol(Protocol.READ_RES, Protocol.READ_RES_all_period);
                                    protocol.setData(data);
                                }
                                os.write(protocol.getPacket());
                                break;
                            case Protocol.READ_REQ_opened_lecture: // 개설 과목 조회
                                System.out.println("클라이언트가 개설과목 조회 요청을 보냈습니다");

                                data = sugangProgramAdminServer.opened_lectureReadAll();
                                print(data);
                                protocol = new Protocol(Protocol.READ_RES, Protocol.READ_RES_opened_lecture);
                                protocol.setData(data);
                                os.write(protocol.getPacket());
                                break;

                        }
                        break;
                    case Protocol.UPDATE_REQ:
                        switch (packetCode) {
                            case Protocol.UPDATE_REQ_students: // 학생 수정
                                data = protocol.getData();
                                System.out.println(data);
                                arr = data.split(" ");
                                System.out.println("클라이언트가 학생 정보 수정을 요청했습니다");
                                sugangProgram_studentServer.update_info(arr[0], arr[1]);
                                protocol = new Protocol(Protocol.UPDATE_RES, Protocol.UPDATE_RES_SUCCESS);
                                os.write(protocol.getPacket());
                                break;
                            case Protocol.UPDATE_REQ_accounts: //계정 수정
                                data = protocol.getData();
                                System.out.println(data);
                                arr = data.split(" ");
                                System.out.println("클라이언트가 비밀번호 수정을 요청했습니다");

                                AccountsDAO accountsDAO = new AccountsDAO(MyBatisConnectionFactory.getSqlSessionFactory());
                                AccountsDTO accountsDTO = new AccountsDTO();
                                accountsDTO.setId(arr[0]);
                                accountsDTO.setPassword(arr[1]);
                                accountsDAO.updatePW(accountsDTO);

                                protocol = new Protocol(Protocol.UPDATE_RES, Protocol.UPDATE_RES_SUCCESS);
                                os.write(protocol.getPacket());
                                break;
                            case Protocol.UPDATE_REQ_lectures: //교과목 수정
                                data = protocol.getData();
                                System.out.println(data);
                                arr = data.split(" ");
                                System.out.println("클라이언트가 교과목 수정을 요청했습니다");
                                sugangProgramAdminServer.lectureUpdate(arr[0], arr[1], arr[2], arr[3], arr[4]);
                                protocol = new Protocol(Protocol.UPDATE_RES, Protocol.UPDATE_RES_SUCCESS);
                                os.write(protocol.getPacket());
                                break;
                            case Protocol.UPDATE_REQ_periods: //기간 수정
                                data = protocol.getData();
                                System.out.println(data);
                                arr = data.split(" ");
                                System.out.println("클라이언트가 기간 설정을 요청했습니다");
                                sugangProgramAdminServer.periodUpdate(Integer.parseInt(arr[0]), arr[1], arr[2]);
                                protocol = new Protocol(Protocol.UPDATE_RES, Protocol.UPDATE_RES_SUCCESS);
                                os.write(protocol.getPacket());
                                break;
                            case Protocol.UPDATE_REQ_professors_plan: // 강의계획서 수정
                                System.out.println("클라이언트가 강의계획서 수정 요청을 보냈습니다");
                                data = protocol.getData();
                                arr = data.split("&amp");
                                sugangProgram_professorServer.updata_plan_professor(arr[0], arr[1], arr[2]);
                                protocol = new Protocol(Protocol.UPDATE_RES, Protocol.UPDATE_RES_SUCCESS);
                                os.write(protocol.getPacket());
                                break;
                            case Protocol.UPDATE_REQ_professors://개인정보 수정
                                System.out.println("클라이언트가 개인정보 수정 요청을 보냈습니다");
                                data = protocol.getData();
                                System.out.println(data);
                                arr = data.split(" ");
                                sugangProgram_professorServer.updata_personal_data_professor(arr[0], arr[1], arr[2], arr[3]);
                                protocol = new Protocol(Protocol.UPDATE_RES, Protocol.UPDATE_RES_SUCCESS);
                                os.write(protocol.getPacket());
                                break;
                            case Protocol.UPDATE_REQ_opened_lectures://개설 교과목 수정
                                System.out.println("클라이언트가 개설교과목 수정 요청을 보냈습니다");
                                data = protocol.getData();
                                System.out.println(data);
                                arr = data.split(" ");
                                sugangProgramAdminServer.opened_lectureUpdate(arr[0], arr[1], arr[2], arr[3], arr[4], arr[5], arr[6]);
                                protocol = new Protocol(Protocol.UPDATE_RES, Protocol.UPDATE_RES_SUCCESS);
                                os.write(protocol.getPacket());
                                break;
                        }
                        break;
                    case Protocol.DELETE_REQ:
                        switch (packetCode) {
                            case Protocol.DELETE_REQ_admins: // 관리자 삭제
                                data = protocol.getData();
                                System.out.println(data);
                                System.out.println("클라이언트가 어드민 삭제를 요청했습니다");
                                sugangProgramAdminServer.deleteAdmin(data);
                                protocol = new Protocol(Protocol.DELETE_RES, Protocol.DELETE_RES_SUCCESS);
                                os.write(protocol.getPacket());
                                break;
                            case Protocol.DELETE_REQ_applied_lectures: // 개설 교과목 삭제
                                data = protocol.getData();
                                System.out.println(data);
                                arr = data.split(" ");
                                System.out.println("클라이언트가 수강 신청 삭제를 요청했습니다");
                                sugangProgram_studentServer.deleteAppliedLecture(arr[0], arr[1], arr[2]);
                                protocol = new Protocol(Protocol.DELETE_RES, Protocol.DELETE_RES_SUCCESS);
                                os.write(protocol.getPacket());
                                break;
                            case Protocol.DELETE_REQ_students: //학생 삭제
                                data = protocol.getData();
                                System.out.println(data);
                                System.out.println("클라이언트가 학생 신청 삭제를 요청했습니다");
                                sugangProgramAdminServer.studentDelete(data);
                                protocol = new Protocol(Protocol.DELETE_RES, Protocol.DELETE_RES_SUCCESS);
                                os.write(protocol.getPacket());
                                break;
                            case Protocol.DELETE_REQ_lectures: // 교과목 삭제
                                data = protocol.getData();
                                System.out.println(data);
                                System.out.println("클라이언트가 교과목 신청 삭제를 요청했습니다");
                                sugangProgramAdminServer.lectureDelete(data);
                                protocol = new Protocol(Protocol.DELETE_RES, Protocol.DELETE_RES_SUCCESS);
                                os.write(protocol.getPacket());
                                break;
                            case Protocol.DELETE_REQ_professors: //교수 삭제
                                data = protocol.getData();
                                System.out.println(data);
                                System.out.println("클라이언트가 교수 삭제를 요청했습니다");
                                sugangProgramAdminServer.professorDelete(data);
                                protocol = new Protocol(Protocol.DELETE_RES, Protocol.DELETE_RES_SUCCESS);
                                os.write(protocol.getPacket());
                                break;
                            case Protocol.DELETE_REQ_opened_lectures: //개설 교과목 삭제
                                data = protocol.getData();
                                System.out.println(data);
                                arr = data.split(" ");
                                System.out.println("클라이언트가 개설 교과목 신청 삭제를 요청했습니다");
                                sugangProgramAdminServer.opened_lectureDelete(arr[0],arr[1]);
                                protocol = new Protocol(Protocol.DELETE_RES, Protocol.DELETE_RES_SUCCESS);
                                os.write(protocol.getPacket());
                                break;
                        }
                        break;
                }//end switch
                if (program_stop) break;

            }//end while

        }
        catch(Exception e){
            System.out.println(e);
            Protocol protocol = new Protocol(Protocol.PC_UNDEFINED,Protocol.PC_UNDEFINED);
        }
        finally {
            try {
                socket.close();
            } catch(IOException e) {
                System.out.println(e);

            }
        }

    }

    private static int login(String id, String password){ // 로그인
            AccountsDAO accountsDAO = new AccountsDAO(MyBatisConnectionFactory.getSqlSessionFactory());
            AccountsService accountsService = new AccountsService(accountsDAO);
            List<AccountsDTO> list = accountsService.findPassword(id);

            if (!list.isEmpty()) if (list.get(0).getPassword().equals(password)) {
                return list.get(0).getUser_type();
            }
            return -1;

    }
    private static void print(String data){ // 출력
        try {
            String[] arr = data.split("&amp");
            for (int i = 1; i < arr.length; i++) {
                System.out.print(arr[i]);
            }
        }catch (Exception e){
            System.out.println("정보를 불러오지 못했습니다. ");
        }
    }
}
