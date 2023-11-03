package network.client;

import network.Protocol;

import java.net.*;
import java.io.*;

public class Client {

    public static void main(String[] args) throws Exception, ClassNotFoundException, InterruptedException {

        String id = null,pwd=null; //클라이언트 id,password
        Socket socket = new Socket("192.168.224.174", 4444); // ip = 192.168.224.174

        OutputStream os = socket.getOutputStream();
        InputStream is = socket.getInputStream();

        Protocol protocol = new Protocol();
        byte[] buf = protocol.getPacket();
        while (true) {
            is.read(buf);
            int packetType = buf[0]; //프로토콜 타입
            int packetCode = buf[1]; //프로토콜 코드
            int datalength = buf[2]; // 데이터 길이

            protocol.setPacket(packetType, packetCode, datalength, buf);
            if (packetType == Protocol.PT_EXIT) {
                System.out.println("종료");
                break;
            }
            switch (packetType) {
                case Protocol.LOGIN_REQ:
                    System.out.println("서버가 로그인 정보 요청");
                    BufferedReader userIn = new BufferedReader(new InputStreamReader(System.in));
                    System.out.print("ID: ");
                    id = userIn.readLine();
                    System.out.print("PASSWORD: ");
                    pwd = userIn.readLine();

                    protocol = new Protocol(Protocol.LOGIN_RES, Protocol.PC_UNDEFINED);
                    String data = id + " " + pwd;
                    protocol.setData(data);
                    os.write(protocol.getPacket());
                    break;

                case Protocol.RES_LOGIN_SUCCESS:// 로그인 성공 시 각 프로그램 실행
                    switch (protocol.getData()){
                        case "1":
                            SugangProgram_Admin_Client sugangProgram_adminClient = new SugangProgram_Admin_Client(os,is);
                            sugangProgram_adminClient.run();
                            break;
                        case "2":
                            SugangProgram_Professor_Client sugangProgram_professorClient = new SugangProgram_Professor_Client(id,pwd,os,is);
                            sugangProgram_professorClient.run();
                            break;
                        case "3":
                            SugangProgram_Student_Client sugangProgram_studentClient = new SugangProgram_Student_Client(id,pwd,os,is);
                            sugangProgram_studentClient.menu();
                             break;
                    }
                    break;
                case Protocol.RES_LOGIN_FAIL:
                    System.out.println("로그인 실패");
                    break;

            }
            os.flush();
        }

    }

}
