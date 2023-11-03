package network.client;

import network.Protocol;
import view.*;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;

public class SugangProgram_Admin_Client {
    private OutputStream os;
    private InputStream is;
    Scanner sc;

    public SugangProgram_Admin_Client(OutputStream os, InputStream is) {
        sc = new Scanner(System.in);
        this.os = os;
        this.is = is;
    }

    public void run() throws IOException {
            menu();
    }

    private View view = new View();

    private void menu() throws IOException {
        while (true) {
            System.out.println("Select Menu 0.종료 1.Student 2.Professor 3.Lecture 4.Opened_lecture 5.Period 6.관리자");
            int menu = sc.nextInt();
            switch (menu) {
                case 0:
                    Protocol protocol = new Protocol(Protocol.PT_EXIT,Protocol.PC_UNDEFINED);
                    os.write(protocol.getPacket());
                    return;
                case 1:
                    student();
                    break;
                case 2:
                    professor();
                    break;
                case 3:
                    lecture();
                    break;
                case 4:
                    opened_lecture();
                    break;
                case 5:
                    coursePeriod();
                    break;
                case 6:
                   admin();
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
                            System.out.println("생성 성공!");
                            break;
                        case Protocol.CREATE_RES_FAIL:
                            System.out.println("실패!");
                            break;
                    }
                    break;
                case Protocol.READ_RES:
                    switch (packetCode) {
                        case Protocol.READ_RES_all_students:
                            data = new String(buf);
                            print(data);
                            break;
                        case Protocol.READ_RES_all_lecture:
                            data = new String(buf);
                            print(data);
                            break;
                        case Protocol.READ_RES_all_professor:
                            data = new String(buf);
                            print(data);
                            break;
                        case Protocol.READ_RES_all_period:
                            data = new String(buf);
                            print(data);
                            break;
                        case Protocol.READ_RES_opened_lecture:
                            data = new String(buf);
                            print(data);
                            break;
                        case Protocol.READ_RES_all_account:
                            data = new String(buf);
                            print(data);
                            break;
                    }
                    break;
                case Protocol.UPDATE_RES:
                    switch (packetCode) {
                        case Protocol.UPDATE_RES_SUCCESS:
                            System.out.println("수정 성공!");
                            break;
                        case Protocol.UPDATE_RES_FAIL:
                            System.out.println("수정 실패");
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
    private void admin() throws IOException {
        System.out.println("Select Menu 1.관리자생성 2.계정조회 3.관리자삭제");
        int menu = sc.nextInt();
        switch (menu) {
            case 1:
                createAdmin();
                break;
            case 2:
                readAccount();
                break;
            case 3:
                deleteAdmin();
                break;

        }

    }
    private void deleteAdmin() throws IOException {
        sc.nextLine();
        System.out.print("ID:");
        String id = sc.nextLine();
        Protocol protocol = new Protocol(Protocol.DELETE_REQ,Protocol.DELETE_REQ_admins);
        protocol.setData(id);
        os.write(protocol.getPacket());
    }

    private void createAdmin() throws IOException {
        sc.nextLine();
        System.out.println("ID | 비밀번호");
        String [] arr = sc.nextLine().split(" ");
        Protocol protocol = new Protocol(Protocol.CREATE_REQ,Protocol.CREATE_REQ_admins);
        protocol.setData(arr[0]+" "+arr[1]);
        os.write(protocol.getPacket());
    }
    private void readAccount() throws IOException {
        Protocol protocol = new Protocol(Protocol.READ_REQ,Protocol.READ_REQ_all_account);
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


    private void coursePeriod() throws IOException {
        System.out.println("Select Menu 1.수강신청기간설정 2.강의계획서기간설정 3.기간조회");
        int menu = sc.nextInt();
        switch (menu) {
            case 1:
                periodUpdate_C();
                break;
            case 2:
                planPeriodUpdate_C();
                break;
            case 3:
                readPeriods_C();
                break;

        }
    }
    private void professor() throws IOException {
        System.out.println("Select Menu 1.교수생성 2.교수조회 3.교수삭제");
        int menu = sc.nextInt();
        switch (menu) {
            case 1:
                professorCreate_C();
                break;
            case 2:
                professorRead_C();
                break;
            case 3:
                professorDelete_C();
                break;
        }
    }
    private void readPeriods_C() throws IOException {
        Protocol protocol = new Protocol(Protocol.READ_REQ,Protocol.READ_REQ_all_period);
        os.write(protocol.getPacket());
    }

    private void professorCreate_C() throws IOException {
        System.out.println("교원번호 | 이름 | 전화번호 | 생년월일");
        String pId = sc.next(), name = sc.next(), phNum = sc.next(), bD = sc.next();
        Protocol protocol = new Protocol(Protocol.CREATE_REQ, Protocol.CREATE_REQ_professors);
        protocol.setData(pId + " " + name + " " + phNum + " " + bD);
        os.write(protocol.getPacket());

    }


    private void professorRead_C() throws IOException {
        Protocol protocol = new Protocol(Protocol.READ_REQ, Protocol.READ_REQ_all_professor);
        os.write(protocol.getPacket());
    }



    private void professorDelete_C() throws IOException {
        System.out.print("교원번호:");
        String pId = sc.next();
        Protocol protocol = new Protocol(Protocol.DELETE_REQ, Protocol.DELETE_REQ_professors);
        protocol.setData(pId);
        os.write(protocol.getPacket());
    }



    private void planPeriodUpdate_C() throws IOException {
        sc.nextLine();
        System.out.print("시작 날짜:");
        String start = sc.nextLine();
        System.out.print("끝 날짜:");
        String end = sc.nextLine();
        Protocol protocol = new Protocol(Protocol.UPDATE_REQ, Protocol.UPDATE_REQ_periods);
        protocol.setData("0 "+start+" "+end);
        os.write(protocol.getPacket());
    }

    private void periodUpdate_C() throws IOException {
        System.out.print("학년:");
        int grade = sc.nextInt();
        sc.nextLine();
        System.out.print("시작 날짜:");
        String start = sc.nextLine();
        System.out.print("끝 날짜:");
        String end = sc.nextLine();
        Protocol protocol = new Protocol(Protocol.UPDATE_REQ,Protocol.UPDATE_REQ_periods);
        protocol.setData(grade+" "+start+" "+end);
        os.write(protocol.getPacket());

    }


    private void opened_lecture() throws IOException {
        System.out.println("Select Menu 1.create 2.read 3.delete 4.update");
        int menu = sc.nextInt();
        switch (menu) {
            case 1:
                opened_lectureCreate_C();
                break;
            case 2:
                opened_lectureRead_C();
                break;
            case 3:
                opened_lectureDelete_C();
                break;
            case 4:
                opened_lectureUpdate_C();
                break;

        }
    }

    private void student() throws IOException {
        System.out.println("Select Menu 1.학생생성 2.학생조회 3.학생삭제");
        int menu = sc.nextInt();
        switch (menu) {
            case 1:
                studentCreate_C();
                break;
            case 2:
                studentRead_C();
                break;
            case 3:
                studentDelete_C();
                break;
        }

    }

    private void lecture() throws IOException {
        System.out.println("Select Menu 1.create 2.read 3.update 4.delete");
        int menu = sc.nextInt();
        switch (menu) {
            case 1:
                lectureCreate_C();
                break;
            case 2:
                lectureRead_C();
                break;
            case 3:
                lectureUpdate_C();
                break;
            case 4:
                lectureDelete_C();
                break;
        }
    }
    private void lectureDelete_C() throws IOException {
        sc.nextLine();
        System.out.print("과목코드:");
        String s = sc.nextLine();
        Protocol protocol = new Protocol(Protocol.DELETE_REQ, Protocol.DELETE_REQ_lectures);
        protocol.setData(s);
        os.write(protocol.getPacket());
    }

    private void studentDelete_C() throws IOException {
        sc.nextLine();
        System.out.print("학번:");
        String s = sc.nextLine();
        Protocol protocol = new Protocol(Protocol.DELETE_REQ, Protocol.DELETE_REQ_students);
        protocol.setData(s);
        os.write(protocol.getPacket());
    }
    private void opened_lectureUpdate_C() throws IOException {
        sc.nextLine();
        System.out.println("개설과목 수정 요청");
        System.out.println("과목코드 | 분반코드 | 교수코드 | 건물 | 강의실 | 강의시간 | 최대 수강 인원");
        String tmpStr = sc.nextLine();
        String[] arr = tmpStr.split(" ");

        Protocol protocol = new Protocol(Protocol.UPDATE_REQ, Protocol.UPDATE_REQ_opened_lectures);
        protocol.setData(arr[0] + " " + arr[1] + " " + arr[2] + " " + arr[3]  + " " + arr[4] + " " + arr[5] + " " + arr[6]);
        os.write(protocol.getPacket());
    }


    //opened_lecture client
    private void opened_lectureCreate_C() throws IOException {
        sc.nextLine();
        System.out.println("개설과목 생성 요청");
        System.out.println("과목코드 | 분반코드 | 교수코드 | 건물 | 강의실 | 강의시간 | 최대 수강 인원");
        String tmpStr = sc.nextLine();
        String[] arr = tmpStr.split(" ");

        Protocol protocol = new Protocol(Protocol.CREATE_REQ, Protocol.CREATE_REQ_opened_lectures);
        protocol.setData(arr[0] + " " + arr[1] + " " + arr[2] + " " + arr[3]  + " " + arr[4] + " " + arr[5] + " " + arr[6]);
        os.write(protocol.getPacket());

    }

    private void opened_lectureRead_C() throws IOException {
        System.out.println("개설과목 조회 요청");
        Protocol protocol = new Protocol(Protocol.READ_REQ, Protocol.READ_REQ_opened_lecture);
        os.write(protocol.getPacket());

    }

    private void opened_lectureDelete_C() throws IOException {
        sc.nextLine();
        System.out.println("입력: 과목 코드 ㅣ 분반코드 ");
        String [] arr = sc.nextLine().split(" ");
        Protocol protocol = new Protocol(Protocol.DELETE_REQ, Protocol.DELETE_REQ_opened_lectures);
        protocol.setData(arr[0]+" "+arr[1]);
        os.write(protocol.getPacket());
    }


    private void lectureCreate_C() throws IOException {
        sc.nextLine();
        System.out.println("입력: 과목 코드 ㅣ 이수 학년 | 과목명 | 이수학기 | 학점 ");
        String [] arr = sc.nextLine().split(" ");

        Protocol protocol = new Protocol(Protocol.CREATE_REQ, Protocol.CREATE_REQ_lectures);
        protocol.setData(arr[0] + " " + arr[1] + " " + arr[2] + " " + arr[3] + " " + arr[4]);
        os.write(protocol.getPacket());
    }

    private void lectureRead_C() throws IOException {
        Protocol protocol = new Protocol(Protocol.READ_REQ,Protocol.READ_REQ_all_lecture);
        os.write(protocol.getPacket());
    }

    private void lectureUpdate_C() throws IOException {
        sc.nextLine();
        System.out.println("입력: 과목 코드 ㅣ 이수 학년 | 과목명 | 이수학기 | 학점 ");
        String [] arr = sc.nextLine().split(" ");
        Protocol protocol = new Protocol(Protocol.UPDATE_REQ,Protocol.UPDATE_REQ_lectures);
        protocol.setData(arr[0] + " " + arr[1] + " " + arr[2] + " " + arr[3] + " " + arr[4]);
        os.write(protocol.getPacket());
    }

    private void studentCreate_C() throws IOException {
        sc.nextLine();
        System.out.println("학번 | 이름 | 학년 | 생년월일 | 전화번호");
        String[] arr = sc.nextLine().split(" ");
        Protocol protocol = new Protocol(Protocol.CREATE_REQ, Protocol.CREATE_REQ_students);
        protocol.setData(arr[0] + " " + arr[1] + " " + arr[2] + " " + arr[3] + " " + arr[4]);
        os.write(protocol.getPacket());
    }

    private void studentRead_C() throws IOException {
       Protocol protocol = new Protocol(Protocol.READ_REQ,Protocol.READ_REQ_all_students);
       os.write(protocol.getPacket());
    }


}
