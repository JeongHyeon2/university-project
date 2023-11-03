import java.io.*;
public class Protocol implements Serializable{
	//�������� Ÿ�Կ� ���� ����
	public static final int PT_UNDEFINED = -1;	// ���������� �����Ǿ� ���� ���� ���
	public static final int PT_EXIT = 0;		// ���α׷� ����
	public static final int PT_REQ_LOGIN = 1;	// �α��� ��û
	public static final int PT_RES_LOGIN = 2;	// �α��� ����
	public static final int PT_LOGIN_RESULT=3;	// ���� ���
	public static final int LEN_LOGIN_ID=20;	// ID ����
	public static final int LEN_LOGIN_PASSWORD=20;	// PWD ����
	public static final int LEN_LOGIN_RESULT=2;	// �α��� ���� �� ����
	public static final int LEN_PROTOCOL_TYPE=1;	// �������� Ÿ�� ����
	public static final int LEN_MAX = 1000;		//�ִ� ������ ����
	protected int protocolType;
	private byte[] packet;	// �������ݰ� �������� ��������� �Ǵ� ����Ʈ �迭

	public Protocol(){					// ������
		this(PT_UNDEFINED);
	}

	public Protocol(int protocolType){	// ������
		this.protocolType = protocolType;
		getPacket(protocolType);
	}

	// �������� Ÿ�Կ� ���� ����Ʈ �迭 packet�� length�� �ٸ�
	public byte[] getPacket(int protocolType){
	    if(packet==null){
			switch(protocolType){
				case PT_REQ_LOGIN :
					packet = new byte[LEN_PROTOCOL_TYPE];
					break;
				case PT_RES_LOGIN :
					packet = new byte[LEN_PROTOCOL_TYPE+LEN_LOGIN_ID+LEN_LOGIN_PASSWORD];
					break;
				case PT_UNDEFINED : 
					packet = new byte[LEN_MAX];
					break;
				case PT_LOGIN_RESULT : 
					packet = new byte[LEN_PROTOCOL_TYPE+LEN_LOGIN_RESULT];
					break;
				case PT_EXIT : 
					packet = new byte[LEN_PROTOCOL_TYPE];
					break;
			} // end switch
	    } // end if
	    packet[0] = (byte)protocolType;	// packet ����Ʈ �迭�� ù ��° ����Ʈ�� �������� Ÿ�� ����
	    return packet;
	}

	// �α����� ����/������ ��� ���� �������ݷκ��� �����Ͽ� ���ڿ��� ����
	public String getLoginResult(){
		// String�� ���� �����ڸ� ��� : String(byte[] bytes, int offset, int length)
		return new String(packet, LEN_PROTOCOL_TYPE, LEN_LOGIN_RESULT).trim();
	}

	// String ok�� byte[]�� ���� packet�� �������� Ÿ�� �ٷ� �ڿ� �߰�
	public void setLoginResult(String ok){
		// arraycopy(Object src, int srcPos, Object dest, int destPos, int length)
		System.arraycopy(ok.trim().getBytes(), 0, packet, LEN_PROTOCOL_TYPE, ok.trim().getBytes().length);
		packet[LEN_PROTOCOL_TYPE + ok.trim().getBytes().length] = '\0';
	}

	public void setProtocolType(int protocolType){
		this.protocolType = protocolType;
	}

	public int getProtocolType(){
		return protocolType;
	}

	public byte[] getPacket(){
		return packet;
	}

	// Default �����ڷ� ������ �� Protocol Ŭ������ packet �����͸� �ٲٱ� ���� �޼���
	public void setPacket(int pt, byte[] buf){
		packet = null;
		packet = getPacket(pt);
		protocolType = pt;
		System.arraycopy(buf, 0, packet, 0, packet.length);
	}

	public String getId(){
		// String(byte[] bytes, int offset, int length) 
		return new String(packet, LEN_PROTOCOL_TYPE, LEN_LOGIN_ID).trim();
	}

	// byte[] packet�� String ID�� byte[]�� ����� �������� Ÿ�� �ٷ� �ڿ� �߰�
	public void setId(String id){
		System.arraycopy(id.trim().getBytes(), 0, packet, LEN_PROTOCOL_TYPE, id.trim().getBytes().length);
		packet[LEN_PROTOCOL_TYPE + id.trim().getBytes().length] = '\0';
	}

	// �н������ byte[]���� �α��� ���̵� �ٷ� �ڿ� ����
	public String getPassword(){
		return new String(packet, LEN_PROTOCOL_TYPE+LEN_LOGIN_ID, LEN_LOGIN_PASSWORD).trim();
	}

	public void setPassword(String password){
		System.arraycopy(password.trim().getBytes(), 0, packet, LEN_PROTOCOL_TYPE+LEN_LOGIN_ID, password.trim().getBytes().length);
		packet[LEN_PROTOCOL_TYPE+LEN_LOGIN_ID + password.trim().getBytes().length] = '\0';
	}
}
