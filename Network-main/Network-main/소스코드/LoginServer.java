import java.net.*;
import java.io.*;

public class LoginServer{
	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException{
	    ServerSocket sSocket = new ServerSocket(3000);
   	    System.out.println("Ŭ���̾�Ʈ ���� �����...");
	    Socket socket = sSocket.accept();
	    System.out.println("Ŭ���̾�Ʈ ����");

		// ����Ʈ �迭�� ������ ���̹Ƿ� ���� ��Ʈ�� ���� Input/OutputStream�� ����ص� ��
        OutputStream os = socket.getOutputStream();
        InputStream is = socket.getInputStream();

		// �α��� ���� ��û�� �������� ��ü ���� �� ����
		Protocol protocol = new Protocol(Protocol.PT_REQ_LOGIN);
	    os.write(protocol.getPacket());

		boolean program_stop = false;

	    while(true){
			protocol = new Protocol();			// �� Protocol ��ü ���� (�⺻ ������)
			byte[] buf = protocol.getPacket();	// �⺻ �����ڷ� ������ ������ ����Ʈ �迭�� ���̰� 1000����Ʈ�� ������
			is.read(buf);						// Ŭ���̾�Ʈ�κ��� �α������� (ID�� PWD) ����
			int packetType = buf[0];			// ���� �����Ϳ��� ��Ŷ Ÿ�� ����
			protocol.setPacket(packetType,buf);	// ��Ŷ Ÿ���� Protocol ��ü�� packet ��������� buf�� ����

			switch(packetType){
				case Protocol.PT_EXIT:			// ���α׷� ���� ���� 
					protocol = new Protocol(Protocol.PT_EXIT);
					os.write(protocol.getPacket());
					program_stop = true;
					System.out.println("��������");
					break;
				
				case Protocol.PT_RES_LOGIN:		// �α��� ���� ���� 
					System.out.println("Ŭ���̾�Ʈ�� " + "�α��� ������ ���½��ϴ�");
					String id = protocol.getId();
					String password = protocol.getPassword();
					System.out.println(id+password);

					if(id.equals("software")){
						if(password.equals("1234")){	//�α��� ����
							protocol = new Protocol(Protocol.PT_LOGIN_RESULT);
							protocol.setLoginResult("1");
							System.out.println("�α��� ����");
						 }else{	//��ȣ Ʋ��
							protocol = new Protocol(Protocol.PT_LOGIN_RESULT);
							protocol.setLoginResult("2");
							System.out.println("��ȣ Ʋ��");
						 }
					}else{	//���̵� ���� ����
						protocol = 	new Protocol(Protocol.PT_LOGIN_RESULT);
						protocol.setLoginResult("3");
						System.out.println("���̵� �������");
					}

					System.out.println("�α��� ó�� ��� ����");
					os.write(protocol.getPacket());
					break;
			}//end switch
			if(program_stop) break;

	    }//end while

	    is.close();
	    os.close();
	    socket.close();
	}
}
