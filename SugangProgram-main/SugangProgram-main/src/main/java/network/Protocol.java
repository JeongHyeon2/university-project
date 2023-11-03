package network;

import java.io.*;
import java.nio.ByteBuffer;

public class Protocol implements Serializable{

    //type
    public static final int PT_UNDEFINED = 0;	// 타입이 지정되어 있지 않은 경우
    public static final int PT_EXIT = 1;        // 프로그램 종료
    public static final int CREATE_REQ = 2;
    public static final int CREATE_RES = 3;
    public static final int READ_REQ = 4;
    public static final int READ_RES = 5;
    public static final int UPDATE_REQ = 6;
    public static final int UPDATE_RES = 7;
    public static final int DELETE_REQ = 8;
    public static final int DELETE_RES = 9;
    public static final int LOGIN_REQ = 10;	// 로그인 요청
    public static final int LOGIN_RES = 11;	// 로그인 응답

    //code
    public static final int PC_UNDEFINED = 0;   // 코드가 지정되어 있지 않은 경우

    // 로그인 code
    public static final int RES_LOGIN_FAIL = 1;
    public static final int RES_LOGIN_SUCCESS = 2;

    //READ_RES code
    public static final int READ_RES_all_students = 0;
    public static final int READ_RES_one_student = 1;
    public static final int READ_RES_all_professor = 2;
    public static final int READ_RES_one_professor = 3;
    public static final int READ_RES_all_opened_lecture = 4;
    public static final int READ_RES_opened_lecture_professor = 5;
    public static final int READ_RES_plan = 6;
    public static final int READ_RES_applied_lecture_students = 7;
    public static final int READ_RES_time_table_by_professor = 8;
    public static final int READ_RES_opened_lecture = 9;
    public static final int READ_RES_plan_by_student = 10;
    public static final int READ_RES_time_table_by_student = 11;
    public static final int READ_RES_all_lecture = 12;
    public static final int READ_RES_all_account = 13;
    public static final int READ_RES_all_building = 14;
    public static final int READ_RES_all_period = 15;
    public static final int READ_RES_all_applied_lecture = 16;
    public static final int READ_RES_all_lecture_room = 17;
    public static final int READ_RES_FAIL = 18;
    public static final int READ_RES_students_by_professor = 19;

    //READ_REQ code
    public static final int READ_REQ_all_students = 0;
    public static final int READ_REQ_one_student = 1;
    public static final int READ_REQ_all_professor = 2;
    public static final int READ_REQ_one_professor = 3;
    public static final int READ_REQ_all_opened_lecture = 4;
    public static final int READ_REQ_opened_lecture_professor = 5;
    public static final int READ_REQ_plan = 6;
    public static final int READ_REQ_applied_lecture_students = 7;
    public static final int READ_REQ_time_table_by_professor = 8;
    public static final int READ_REQ_opened_lecture = 9;
    public static final int READ_REQ_plan_by_student = 10;
    public static final int READ_REQ_time_table_by_student = 11;
    public static final int READ_REQ_all_lecture = 12;
    public static final int READ_REQ_all_account = 13;
    public static final int READ_REQ_all_building = 14;
    public static final int READ_REQ_all_period = 15;
    public static final int READ_REQ_all_applied_lecture = 16;
    public static final int READ_REQ_all_lecture_room = 17;
    public static final int READ_REQ_students_by_professor = 18;

    //CREATE_REQ code
    public static final int CREATE_REQ_students = 0;
    public static final int CREATE_REQ_professors = 1;
    public static final int CREATE_REQ_opened_lectures = 2;
    public static final int CREATE_REQ_lectures = 3;
    public static final int CREATE_REQ_accounts = 4;
    public static final int CREATE_REQ_admins = 5;
    public static final int CREATE_REQ_buildings = 6;
    public static final int CREATE_REQ_lecture_rooms = 7;
    public static final int CREATE_REQ_periods = 8;
    public static final int CREATE_REQ_applied_lectures = 9;

    //CREATE_RES code
    public static final int CREATE_RES_FAIL = 0;
    public static final int CREATE_RES_SUCCESS = 1;

    //UPDATE_REQ code
    public static final int UPDATE_REQ_students = 0;
    public static final int UPDATE_REQ_professors = 1;
    public static final int UPDATE_REQ_opened_lectures = 2;
    public static final int UPDATE_REQ_lectures = 3;
    public static final int UPDATE_REQ_accounts = 4;
    public static final int UPDATE_REQ_periods = 5;
    public static final int UPDATE_REQ_professors_plan = 6;
    public static final int UPDATE_REQ_professors_confitmed_plan = 7;

    //UPDATE_RES code
    public static final int UPDATE_RES_FAIL = 0;
    public static final int UPDATE_RES_SUCCESS = 1;

    //DELETE_REQ code
    public static final int DELETE_REQ_students = 0;
    public static final int DELETE_REQ_professors = 1;
    public static final int DELETE_REQ_opened_lectures = 2;
    public static final int DELETE_REQ_lectures = 3;
    public static final int DELETE_REQ_accounts = 4;
    public static final int DELETE_REQ_admins = 5;
    public static final int DELETE_REQ_buildings = 6;
    public static final int DELETE_REQ_lecture_rooms = 7;
    public static final int DELETE_REQ_periods = 8;
    public static final int DELETE_REQ_applied_lectures = 9;

    //DELETE_RES code
    public static final int DELETE_RES_FAIL = 0;
    public static final int DELETE_RES_SUCCESS = 1;

    //len
    public static final int LEN_LOGIN_ID=20;	// ID 길이
    public static final int LEN_LOGIN_PASSWORD=20;	// PWD 길이
    public static final int LEN_PROTOCOL_TYPE=1;	// 프로토콜 타입 길이
    public static final int LEN_PROTOCOL_CODE=1;
    public static final int LEN_PROTOCOL_DATALENGTH=2;
    public static final int LEN_MAX = 100000;		//최대 데이터 길이

    protected int protocolType;
    protected int protocolCode;
    private byte[] packet;	// 프로토콜과 데이터의 저장공간이 되는 바이트 배열
    private int dataLength = 0; // ,구분자로 데이터를 분리할 것이기 때문에 , 의 개수를 의미한다.

    public Protocol(){					// 생성자
        this(PT_UNDEFINED, PC_UNDEFINED);
    }

    public Protocol(int protocolType, int code){	// 생성자
        this.protocolType = protocolType;
        this.protocolCode = code;
        getPacket(protocolType, code);

    }

    // 프로토콜 타입에 따라 바이트 배열 packet의 length가 다름
    public byte[] getPacket(int protocolType, int code){
        if(packet==null){
            switch(protocolType){
                // 로그인 요청type
                case LOGIN_REQ :
                    packet = new byte[LEN_PROTOCOL_TYPE+LEN_PROTOCOL_CODE+LEN_PROTOCOL_DATALENGTH];
                    break;
                // 로그인 응답type
                case LOGIN_RES :
                    switch(code){
                        case PC_UNDEFINED : // 아이디 패스워트 전송
                            packet = new byte[LEN_PROTOCOL_TYPE+LEN_PROTOCOL_CODE+LEN_LOGIN_ID+LEN_LOGIN_PASSWORD+LEN_PROTOCOL_DATALENGTH];
                            break;
                        case RES_LOGIN_SUCCESS: // 로그인 성공 결과 전송
                            packet = new byte[LEN_PROTOCOL_TYPE+LEN_PROTOCOL_CODE+LEN_PROTOCOL_DATALENGTH];
                            break;
                        case RES_LOGIN_FAIL :
                            packet = new byte[LEN_PROTOCOL_TYPE+LEN_PROTOCOL_CODE+LEN_PROTOCOL_DATALENGTH];
                            break;
                    }break;
                // 기본 생성type
                case PT_UNDEFINED :
                    packet = new byte[LEN_MAX];
                    break;
                // 종료type
                case PT_EXIT :
                    packet = new byte[LEN_PROTOCOL_TYPE+LEN_PROTOCOL_CODE+LEN_PROTOCOL_DATALENGTH];
                    break;
                // 조회 요청type
                case READ_REQ:
                    switch(code){
                        case READ_REQ_all_students:
                            packet = new byte[LEN_MAX];
                            break;
                        case READ_REQ_one_student:
                            packet = new byte[LEN_MAX];
                            break;
                        case READ_REQ_all_professor:
                            packet = new byte[LEN_MAX];
                            break;
                        case READ_REQ_one_professor:
                            packet = new byte[LEN_MAX];
                            break;
                        case READ_REQ_all_opened_lecture:
                            packet = new byte[LEN_MAX];
                            break;
                        case READ_REQ_opened_lecture_professor:
                            packet = new byte[LEN_MAX];
                            break;
                        case READ_REQ_plan:
                            packet = new byte[LEN_MAX];
                            break;
                        case READ_REQ_applied_lecture_students:
                            packet = new byte[LEN_MAX];
                            break;
                        case READ_REQ_time_table_by_professor:
                            packet = new byte[LEN_MAX];
                            break;
                        case READ_REQ_opened_lecture:
                            packet = new byte[LEN_MAX];
                            break;
                        case READ_REQ_plan_by_student:
                            packet = new byte[LEN_MAX];
                            break;
                        case READ_REQ_time_table_by_student:
                            packet = new byte[LEN_MAX];
                            break;
                        case READ_REQ_all_lecture:
                            packet = new byte[LEN_MAX];
                            break;
                        case READ_REQ_all_account:
                            packet = new byte[LEN_MAX];
                            break;
                        case READ_REQ_all_building:
                            packet = new byte[LEN_MAX];
                            break;
                        case READ_REQ_all_period:
                            packet = new byte[LEN_MAX];
                            break;
                        case READ_REQ_all_applied_lecture:
                            packet = new byte[LEN_MAX];
                            break;
                        case READ_REQ_all_lecture_room:
                            packet = new byte[LEN_MAX];
                            break;
                    }break;
                // 조회 응답type
                case READ_RES:
                    switch (code){
                        case READ_RES_all_students:
                            packet = new byte[LEN_MAX];
                            break;
                        case READ_RES_one_student:
                            packet = new byte[LEN_MAX];
                            break;
                        case READ_RES_all_professor:
                            packet = new byte[LEN_MAX];
                            break;
                        case READ_RES_one_professor:
                            packet = new byte[LEN_MAX];
                            break;
                        case READ_RES_all_opened_lecture:
                            packet = new byte[LEN_MAX];
                            break;
                        case READ_RES_opened_lecture_professor:
                            packet = new byte[LEN_MAX];
                            break;
                        case READ_RES_plan:
                            packet = new byte[LEN_MAX];
                            break;
                        case READ_RES_applied_lecture_students:
                            packet = new byte[LEN_MAX];
                            break;
                        case READ_RES_time_table_by_professor:
                            packet = new byte[LEN_MAX];
                            break;
                        case READ_RES_opened_lecture:
                            packet = new byte[LEN_MAX];
                            break;
                        case READ_RES_plan_by_student:
                            packet = new byte[LEN_MAX];
                            break;
                        case READ_RES_time_table_by_student:
                            packet = new byte[LEN_MAX];
                            break;
                        case READ_RES_all_lecture:
                            packet = new byte[LEN_MAX];
                            break;
                        case READ_RES_all_account:
                            packet = new byte[LEN_MAX];
                            break;
                        case READ_RES_all_building:
                            packet = new byte[LEN_MAX];
                            break;
                        case READ_RES_all_period:
                            packet = new byte[LEN_MAX];
                            break;
                        case READ_RES_all_applied_lecture:
                            packet = new byte[LEN_MAX];
                            break;
                        case READ_RES_all_lecture_room:
                            packet = new byte[LEN_MAX];
                            break;
                        case READ_RES_FAIL:
                            packet = new byte[LEN_PROTOCOL_TYPE+LEN_PROTOCOL_CODE+LEN_PROTOCOL_DATALENGTH];
                            break;

                    } break;
                // 생성 요청 type
                case CREATE_REQ :
                    switch(code){
                        case CREATE_REQ_students:
                            packet = new byte[LEN_MAX];
                            break;
                        case CREATE_REQ_professors:
                            packet = new byte[LEN_MAX];
                            break;
                        case CREATE_REQ_opened_lectures:
                            packet = new byte[LEN_MAX];
                            break;
                        case CREATE_REQ_lectures:
                            packet = new byte[LEN_MAX];
                            break;
                        case CREATE_REQ_accounts:
                            packet = new byte[LEN_MAX];
                            break;
                        case CREATE_REQ_admins:
                            packet = new byte[LEN_MAX];
                            break;
                        case CREATE_REQ_buildings:
                            packet = new byte[LEN_MAX];
                            break;
                        case CREATE_REQ_lecture_rooms:
                            packet = new byte[LEN_MAX];
                            break;
                        case CREATE_REQ_periods:
                            packet = new byte[LEN_MAX];
                            break;
                        case CREATE_REQ_applied_lectures:
                            packet = new byte[LEN_MAX];
                            break;
                    }break;
                // 생성 응답 type
                case CREATE_RES :
                    switch (code){
                        case CREATE_RES_FAIL:
                            packet = new byte[LEN_MAX];
                            break;
                        case CREATE_RES_SUCCESS:
                            packet = new byte[LEN_PROTOCOL_TYPE+LEN_PROTOCOL_CODE+LEN_PROTOCOL_DATALENGTH];
                            break;
                    }break;
                // 변경 요청 type
                case UPDATE_REQ :
                    switch (code){
                        case UPDATE_REQ_students:
                            packet = new byte[LEN_MAX];
                            break;
                        case UPDATE_REQ_professors:
                            packet = new byte[LEN_MAX];
                            break;
                        case UPDATE_REQ_opened_lectures:
                            packet = new byte[LEN_MAX];
                            break;
                        case UPDATE_REQ_lectures:
                            packet = new byte[LEN_MAX];
                            break;
                        case UPDATE_REQ_accounts:
                            packet = new byte[LEN_MAX];
                            break;
                        case UPDATE_REQ_periods:
                            packet = new byte[LEN_MAX];
                            break;
                        case UPDATE_REQ_professors_plan:
                            packet = new byte[LEN_MAX];
                            break;
                        case UPDATE_REQ_professors_confitmed_plan:
                            packet = new byte[LEN_MAX];
                            break;

                    }break;
                // 변경 응답 type
                case UPDATE_RES :
                    switch (code){
                        case UPDATE_RES_FAIL:
                            packet = new byte[LEN_PROTOCOL_TYPE+LEN_PROTOCOL_CODE+LEN_PROTOCOL_DATALENGTH];
                            break;
                        case UPDATE_RES_SUCCESS:
                            packet = new byte[LEN_PROTOCOL_TYPE+LEN_PROTOCOL_CODE+LEN_PROTOCOL_DATALENGTH];
                            break;
                    }break;

                // 삭제 요청 type
                case DELETE_REQ :
                    switch (code){
                        case DELETE_REQ_students:
                            packet = new byte[LEN_MAX];
                            break;
                        case DELETE_REQ_professors:
                            packet = new byte[LEN_MAX];
                            break;
                        case DELETE_REQ_opened_lectures:
                            packet = new byte[LEN_MAX];
                            break;
                        case DELETE_REQ_lectures:
                            packet = new byte[LEN_MAX];
                            break;
                        case DELETE_REQ_accounts:
                            packet = new byte[LEN_MAX];
                            break;
                        case DELETE_REQ_admins:
                            packet = new byte[LEN_MAX];
                            break;
                        case DELETE_REQ_buildings:
                            packet = new byte[LEN_MAX];
                            break;
                        case DELETE_REQ_lecture_rooms:
                            packet = new byte[LEN_MAX];
                            break;
                        case DELETE_REQ_periods:
                            packet = new byte[LEN_MAX];
                            break;
                        case DELETE_REQ_applied_lectures:
                            packet = new byte[LEN_MAX];
                            break;
                    }break;

                // 삭제 응답 type
                case DELETE_RES :
                    switch (code){
                        case DELETE_RES_FAIL:
                            packet = new byte[LEN_PROTOCOL_TYPE+LEN_PROTOCOL_CODE+LEN_PROTOCOL_DATALENGTH];
                            break;
                        case DELETE_RES_SUCCESS:
                            packet = new byte[LEN_PROTOCOL_TYPE+LEN_PROTOCOL_CODE+LEN_PROTOCOL_DATALENGTH];
                            break;
                    }break;

            } // end switch
        } // end if
        packet[0] = (byte)protocolType;	// packet 바이트 배열의 첫 번째 바이트에 프로토콜 타입 설정
        packet[1] = (byte)protocolCode;
        int inByte = dataLength/2;
        packet[2] = (byte)inByte;
        packet[3] = (byte)inByte;

        //  packet[2] = (byte)dataLength;
        return packet;
    }



    public byte[] getPacket(){
        return packet;
    }

    public int getDataLength(){
        return dataLength;
    }

    public void setDataLength(String dataLine){
        byte[] buff = dataLine.getBytes();
        dataLength = buff.length;
        packet[2] = (byte)dataLength;
    }
    public void setProtocolType(int protocolType){
        this.protocolType = protocolType;
    }

    public int getProtocolType(){
        return protocolType;
    }

    // Default 생성자로 생성한 후 Protocol 클래스의 packet 데이터를 바꾸기 위한 메서드
    public void setPacket(int pt, int cd, int dl, byte[] buf){
        packet = null;
        packet = getPacket(pt, cd);
        protocolType = pt;
        protocolCode = cd;
        dataLength = dl;
        System.arraycopy(buf, 0, packet, 0, packet.length);
    }

    public void setData(String data){
        System.arraycopy(data.trim().getBytes(), 0, packet, LEN_PROTOCOL_TYPE+LEN_PROTOCOL_CODE+LEN_PROTOCOL_DATALENGTH, data.trim().getBytes().length);
        packet[LEN_PROTOCOL_TYPE + LEN_PROTOCOL_CODE+LEN_PROTOCOL_DATALENGTH+data.trim().getBytes().length] = '\0';
        setDataLength(data);
    }
    public String getData(){
        String dataLine = new String(packet, LEN_PROTOCOL_TYPE+LEN_PROTOCOL_CODE+LEN_PROTOCOL_DATALENGTH, dataLength+LEN_PROTOCOL_TYPE+LEN_PROTOCOL_CODE+LEN_PROTOCOL_DATALENGTH).trim();
        return dataLine;
    }


}
