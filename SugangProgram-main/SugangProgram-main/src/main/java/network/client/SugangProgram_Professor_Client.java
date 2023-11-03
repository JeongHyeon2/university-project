package network.client;


import network.Protocol;
import view.View;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Scanner;

public class SugangProgram_Professor_Client {
    private OutputStream os;
    private InputStream is;
    private Scanner sc;
    private String id;
    private String password;
    private View view = new View();

    public SugangProgram_Professor_Client(String id, String password, OutputStream os, InputStream is) {
        sc = new Scanner(System.in);
        this.id = id;
        this.password = password;
        this.os = os;
        this.is = is;
    }

    public SugangProgram_Professor_Client(OutputStream os, InputStream is) {
        sc = new Scanner(System.in);
        this.os = os;
        this.is = is;
    }
    public void run() throws IOException {
        menu();
    }


    public void menu() throws IOException {
        while (true) {
            // 수정 변경 / 개인정보 및 비밀번호 수정
            // 강의 계획서 입력 수정
            // 조회 : 교과목, 강의 계획서, 수강 신청 학생 목록, 담당 교과목 시간표
            System.out.println("0.종료 1.담당 교과목 조회 2.담당 교과목 강의계획서 조회 3.담당 교과목 수강신청 학생 목록 조회 4.강의계획서 입력/수정  5.시간표 조회 6.개인정보 수정 7.비밀번호 수정 8. 개인정보조회");
            int menu = sc.nextInt();
            switch (menu) {
                case 0:
                    Protocol protocol = new Protocol(Protocol.PT_EXIT,Protocol.PC_UNDEFINED);
                    os.write(protocol.getPacket());
                    return;
                case 1:
                    read_openedLectures_professor_C(id);
                    break;
                case 2:
                    read_plan_professor_C(id);
                    break;
                case 3:
                    read_applied_student_professor_C();
                    break;
                case 4:
                    update_plan_professor_C();
                    break;
                case 5:
                    read_timeTable_professor_C(id);
                    break;
                case 6:
                    update_personal_data_professor_C(id);
                    break;
                case 7:
                    update_password();
                    break;
                case 8:
                    read_info();
                    break;

            }

            Protocol protocol = new Protocol();
            byte[] buf = protocol.getPacket();

            is.read(buf);
            int packetType = buf[0];
            int packetCode = buf[1];
            int datalength = buf[2];
//                byte[] bytes = new byte[2];
//                bytes[0] = buf[2];
//                bytes[1] = buf[3];
//                int datalength = ByteBuffer.wrap(bytes).getInt();


            protocol.setPacket(packetType, packetCode, datalength, buf);
            String data = null;
            String[] arr = null;
            switch (packetType) {
                case Protocol.UPDATE_RES:
                    switch (packetCode) {
                        case Protocol.CREATE_RES_SUCCESS:
                            System.out.println("완료");
                            break;
                        case Protocol.CREATE_RES_FAIL:
                            System.out.println("실패");
                            break;
                    }
                    break;
                case Protocol.READ_RES:
                    switch (packetCode) {
                        case Protocol.READ_RES_one_student:
                            System.out.println("서버로부터 학생정보를 받았습니다.");
                            data = new String(buf);
                            print(data);
                            break;
                        case Protocol.READ_RES_all_opened_lecture:
                            System.out.println("서버로부터 개설교과목 정보를 받았습니다.");
                            data = new String(buf);
                            print(data);
                            break;
                        case Protocol.READ_RES_plan_by_student:
                            System.out.println("서버로부터 강의계획서 정보를 받았습니다.");
                            data = new String(buf);
                            print(data);
                            break;
                        case Protocol.READ_RES_time_table_by_student:
                            System.out.println("서버로부터 시간표 정보를 받았습니다.");
                            data = new String(buf);
                            print(data);
                            break;
                        case Protocol.READ_RES_opened_lecture_professor:
                            System.out.println("서버로부터 개설교과목 정보를 받았습니다.");
                            data = new String(buf);
                            print(data);
                            break;
                        case Protocol.READ_RES_plan:
                            System.out.println("서버로부터 강의계획서 정보를 받았습니다.");
                            data = new String(buf);
                            print(data);
                            break;
                        case Protocol.READ_RES_applied_lecture_students:
                            System.out.println("서버로부터 수강신청 학생 정보를 받았습니다.");
                            data = new String(buf);
                            print(data);
                            break;
                        case Protocol.READ_RES_time_table_by_professor:
                            System.out.println("서버로부터 시간표 정보를 받았습니다.");
                            data = new String(buf);
                            print(data);
                            break;
                        case Protocol.READ_RES_one_professor:
                            System.out.println("서버로부터 개인정보를 받았습니다.");
                            data = new String(buf);
                            print(data);
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

        }

    }
    private void read_info() throws IOException {
        Protocol protocol = new Protocol(Protocol.READ_REQ,Protocol.READ_REQ_one_professor);
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

    // client method -------------
    private  void update_password() throws IOException {
        sc.nextLine();
        System.out.print("변경할 비밀번호:");
        Protocol protocol = new Protocol(Protocol.UPDATE_REQ,Protocol.UPDATE_REQ_accounts);
        protocol.setData(id+" "+sc.nextLine());
        os.write(protocol.getPacket());
    }
    // 개설 교과목 조회
    private void read_openedLectures_professor_C(String id) throws IOException {
        Protocol protocol = new Protocol(Protocol.READ_REQ, Protocol.READ_REQ_opened_lecture_professor);
        protocol.setData(id);
        os.write(protocol.getPacket());
    }

    // 강의 계획서 조회
    private void read_plan_professor_C(String id) throws IOException {
        Protocol protocol = new Protocol(Protocol.READ_REQ, Protocol.READ_REQ_plan);
        protocol.setData(id);
        os.write(protocol.getPacket());
    }

    // 수강신청 학생 조회
    private void read_applied_student_professor_C() throws IOException {
        sc.nextLine();
        System.out.println("과목 코드 | 분반 코드");
        String s = sc.nextLine();
        String[] arr = s.split(" ");
        Protocol protocol = new Protocol(Protocol.READ_REQ, Protocol.READ_REQ_all_building);
        protocol.setData(id+" "+arr[0] + " " + arr[1]);
        os.write(protocol.getPacket());
    }

    //강의 계획서 수정
    private void update_plan_professor_C() throws IOException {

        sc.nextLine();
        System.out.println("과목 코드 | 분반 코드");
        String s = sc.nextLine();
        System.out.println("강의 계획서 작성");
        String plan_str = sc.nextLine();
        String[] arr = s.split(" ");

        Protocol protocol = new Protocol(Protocol.UPDATE_REQ, Protocol.UPDATE_REQ_professors_plan);
        protocol.setData(arr[0] + "&amp" + arr[1] + "&amp" + plan_str);
        os.write(protocol.getPacket());
    }

    // 시간표 조회
    private void read_timeTable_professor_C(String id) throws IOException {
        Protocol protocol = new Protocol(Protocol.READ_REQ, Protocol.READ_REQ_time_table_by_professor);
        protocol.setData(id);
        os.write(protocol.getPacket());
    }

    // 개인 정보 수정
    private void update_personal_data_professor_C(String id) throws IOException {

        sc.nextLine();
        // 다른거 변경 될 필요가 없어서 , 로 데이터 전송
        System.out.println(" 이름 | 전화번호 | 생일");
        String s = sc.nextLine();
        String[] arr = s.split(" ");
        Protocol protocol = new Protocol(Protocol.UPDATE_REQ, Protocol.UPDATE_REQ_professors);
        protocol.setData(id + " " + arr[0] + " " + arr[1] + " " + arr[2]);
        os.write(protocol.getPacket());
    }



}
