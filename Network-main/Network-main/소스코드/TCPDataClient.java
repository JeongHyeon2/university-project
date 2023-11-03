import java.io.*;
import java.net.*;
public class TCPDataClient
{
	public static int DT_LEN = 4;	// Ű����κ��� �Է¹��� ������ ���̸� ������ ���� ũ�� (4����Ʈ)

	public static void main(String args[]){
		Socket theSocket = null;
		String host;
		String theLine;

		if(args.length>0){
			host=args[0]; //  �Է¹��� ȣ��Ʈ�� ���
		}else{
			host="localhost"; // ���� ȣ��Ʈ�� ���
		}
		try{
			theSocket = new Socket(host, 5000); // ���� ����

			InputStream is = theSocket.getInputStream();
			DataInputStream dis = new DataInputStream(is);

			OutputStream os = theSocket.getOutputStream();
			DataOutputStream dos = new DataOutputStream(os);

			BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));

			while(true){
				System.out.print("������ �Է�: ");
				theLine = userInput.readLine(); // Ű����κ��� ������ �Է�
				if(theLine.equals("quit")) break; // ���α׷� ����

				byte[] payload = theLine.getBytes();
				byte[] sendData = new byte[payload.length + DT_LEN];

				int index = 0;
				// ������ ���� ����
				//byte[] payloadSize = intToByteArray(payload.length, DT_LEN);
				byte[] payloadSize = intToBytes(payload.length);
				sendData[0] = (byte)payloadSize[0];
				sendData[1] = (byte)payloadSize[1];
				sendData[2] = (byte)payloadSize[2];
				sendData[3] = (byte)payloadSize[3];
				index = 4;

			   // ������ ���� �߰�
				for(int i = 0; i < payload.length; i++){
					sendData[index] = payload[i];
					index++;
				}

				dos.write(sendData);
				dos.flush();
			}
		}catch(UnknownHostException e){
			System.err.println(args[0]+" ȣ��Ʈ�� ã�� �� �����ϴ�.");
		}catch(IOException e){
			System.err.println(e);
		}finally{
			try{
				theSocket.close(); // ������ �ݴ´�.
			}catch(IOException e){
				System.out.println(e);
			}
		}
	}


	private static byte[] intToBytes(final int data) { 
		return new byte[] { 
			(byte)((data >> 24) & 0xff), 
			(byte)((data >> 16) & 0xff), 
			(byte)((data >> 8) & 0xff), 
			(byte)((data >> 0) & 0xff), 
		}; 
	} 

}   


/*
	public static byte[] intToByteArray(int value, int sizeofdata) {
		byte[] b = new byte[sizeofdata];
		for(int i=0; i < sizeofdata; i++) {
			int offset = (b.length - 1 - i) * 8;
			b[i] = (byte)((value >>> offset) & 0xFF);
		}
		return b;
	}
*/
