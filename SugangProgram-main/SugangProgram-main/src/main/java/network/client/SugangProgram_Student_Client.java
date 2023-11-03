package network.client;

import network.Protocol;
import view.View;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import java.util.Scanner;

public class SugangProgram_Student_Client {
    private OutputStream os;
    private InputStream is;
    private Scanner sc;
    private String id;
    private String password;
    private View view = new View();

    public SugangProgram_Student_Client(String id, String password, OutputStream os, InputStream is) {
        sc = new Scanner(System.in);
        this.id = id;
        this.password = password;
        this.os = os;
        this.is = is;
    }

    public SugangProgram_Student_Client(OutputStream os, InputStream is) {
        sc = new Scanner(System.in);
        this.os = os;
        this.is = is;
    }

    public void menu() throws Exception {
        while (true) {

            System.out.println("0.종료 1.수강신청 2.수강신청조회 3.수강신청취소 4.개설교과목조회 5.선택교과목 강의계획서조회 6.시간표조회 7.개인정보 수정 8.비밀번호 수정 9.개인정보 조회");
            int menu = sc.nextInt();
            switch (menu) {
                case 0:
                    Protocol protocol = new Protocol(Protocol.PT_EXIT,Protocol.PC_UNDEFINED);
                    os.write(protocol.getPacket());
                    return;
                case 1:
                    createAppliedLecture_C();
                    break;
                case 2:
                    readAppliedLecture_C(id);
                    break;
                case 3:
                    deleteAppliedLecture_C();
                    break;
                case 4:
                    opened_lectureReadAll_C();
                    break;
                case 5:
                    read_plan_C();
                    break;
                case 6:
                    read_timeTable_C(id);
                    break;
                case 7:
                    update_info_C();
                    break;
                case 8:
                    update_password_C();
                    break;
                case 9:
                   readStudentInfo();
                default:
                    break;
            }

            Protocol protocol = new Protocol();
            byte[] buf = protocol.getPacket();
            is.read(buf);
            int packetType = buf[0];
            int packetCode = buf[1];
            int datalength = buf[2];

            protocol.setPacket(packetType, packetCode, datalength, buf);
            String data = null;
            String[] arr = null;
            switch (packetType) {
                case Protocol.CREATE_RES:
                    switch (packetCode) {
                        case Protocol.CREATE_RES_SUCCESS:
                            System.out.println("완료");
                            break;
                        case Protocol.CREATE_RES_FAIL:
                            data = new String(buf);
                            print(data);
                            break;
                    }
                    break;
                case Protocol.READ_RES:
                    switch (packetCode) {
                        case Protocol.READ_RES_applied_lecture_students:
                            System.out.println("서버로부터 수강 신청 정보를 받았습니다.");
                            //  data=protocol.getData();
                            data = new String(buf);
                           print(data);
                            break;
                        case Protocol.READ_RES_all_opened_lecture:
                            System.out.println("서버로부터 개설교과목 정보를 받았습니다.");
                            //  data=protocol.getData();
                            data = new String(buf);
                            print(data);
                            break;
                        case Protocol.READ_RES_plan_by_student:
                            System.out.println("서버로부터 강의계획서 정보를 받았습니다.");
                            //  data=protocol.getData();
                            data = new String(buf);
                            print(data);
                            break;
                        case Protocol.READ_RES_time_table_by_student:
                            System.out.println("서버로부터 시간표 정보를 받았습니다.");
                            //  data=protocol.getData();
                            data = new String(buf);
                            print(data);
                            break;
                        case Protocol.READ_RES_one_student:
                            System.out.println("서버로부터 학생 정보를 받았습니다.");
                            //  data=protocol.getData();
                            data = new String(buf);
                            print(data);
                            break;
                        case Protocol.READ_RES_FAIL:
                            System.out.println("정보를 불러오지 못했습니다.");
                            break;
                    }
                    break;
                case Protocol.UPDATE_RES:
                    switch (packetCode) {
                        case Protocol.UPDATE_RES_SUCCESS:
                            System.out.println("수정 성공!");
                            break;
                        case Protocol.UPDATE_RES_FAIL:
                            System.out.println("수정 실패!");
                            break;
                    }
                    break;
                case Protocol.DELETE_RES:
                    switch (packetCode) {
                        case Protocol.DELETE_RES_SUCCESS:
                            System.out.println("완료");
                            break;
                        case Protocol.DELETE_RES_FAIL:
                            System.out.println("실패");
                            break;
                    }
                    break;

            }
            os.flush();
        }

    }
    private void  readStudentInfo() throws IOException {
        Protocol protocol = new Protocol(Protocol.READ_REQ,Protocol.READ_REQ_one_student);
        protocol.setData(id);
        os.write(protocol.getPacket());
    }
    private void print(String data){
        try {
            String[] arr = data.split("&amp");
            for (int i = 1; i < arr.length - 1; i++) {
                System.out.print(arr[i]);
            }
        }catch (Exception e){
            System.out.println("정보를 불러오지 못했습니다. ");
        }
    }

    private void update_password_C() throws IOException {
        sc.nextLine();
        System.out.print("새 비밀번호:");
        String s = sc.nextLine();
        Protocol protocol = new Protocol(Protocol.UPDATE_REQ, Protocol.UPDATE_REQ_accounts);
        protocol.setData(id + " " + s);
        os.write(protocol.getPacket());
        password = s;
    }



    private void update_info_C() throws IOException {
        sc.nextLine();
        System.out.print("전화번호:");
        String s = sc.nextLine();
        Protocol protocol = new Protocol(Protocol.UPDATE_REQ, Protocol.UPDATE_REQ_students);
        protocol.setData(id + " " + s);
        os.write(protocol.getPacket());
    }



    private void read_timeTable_C(String id) throws IOException {
        Protocol protocol = new Protocol(Protocol.READ_REQ, Protocol.READ_REQ_time_table_by_student);
        protocol.setData(id);
        os.write(protocol.getPacket());
    }



    private void read_plan_C() throws IOException {
        sc.nextLine();
        System.out.println("과목 코드 | 분반 코드");
        String s = sc.nextLine();
        String[] arr = s.split(" ");
        Protocol protocol = new Protocol(Protocol.READ_REQ, Protocol.READ_REQ_plan_by_student);
        protocol.setData(arr[0] + " " + arr[1]);
        os.write(protocol.getPacket());
    }

    private void opened_lectureReadAll_C() throws IOException {
        Protocol protocol = new Protocol(Protocol.READ_REQ, Protocol.READ_REQ_all_opened_lecture);
        os.write(protocol.getPacket());
    }



    private void deleteAppliedLecture_C() throws IOException {
        sc.nextLine();
        System.out.println("과목 코드 | 분반 코드");
        String s = sc.nextLine();
        String[] arr = s.split(" ");
        Protocol protocol = new Protocol(Protocol.DELETE_REQ, Protocol.DELETE_REQ_applied_lectures);
        protocol.setData(id+" "+arr[0] + " " + arr[1]);
        os.write(protocol.getPacket());
    }



    private void readAppliedLecture_C(String id) throws IOException {
        Protocol protocol = new Protocol(Protocol.READ_REQ, Protocol.READ_REQ_applied_lecture_students);
        protocol.setData(id);
        os.write(protocol.getPacket());
    }



    private void createAppliedLecture_C() throws IOException {
        sc.nextLine();
        System.out.print("과목 코드:");
        String lectureId = sc.nextLine();
        System.out.print("분반 코드:");
        int classId = sc.nextInt();

        Protocol protocol = new Protocol(Protocol.CREATE_REQ, Protocol.CREATE_REQ_applied_lectures);
        String data = id+" "+lectureId + " " + classId;
        protocol.setData(data);
        os.write(protocol.getPacket());
    }






}
