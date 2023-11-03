import java.io.*;
import java.net.*;

public class TCPDataServer
{
	public static int MAX_LEN = 200;	// �ִ� ������ ũ��
	public static int DT_LEN = 4;	// Ű����κ��� �Է¹��� ������ ���̸� ������ ���� ũ�� (4����Ʈ)
	private ServerSocket server;

	public TCPDataServer(int portnum)
	{
		try
		{
			server = new ServerSocket(portnum);
		}
		catch (Exception err)
		{
			System.out.println(err);
		}
	}

	public void serve()
	{
		try
		{
			Socket theSocket = server.accept();

			while (true)
			{
				InputStream is = theSocket.getInputStream();
				DataInputStream dis = new DataInputStream(is);

				// ������ ũ�� ����
				byte[] recvHeader = new byte[DT_LEN];   // ���ſ� ������ ũ�� �迭 ����
				int totalBytesRcvd = 0;  // Total bytes received so far
				int bytesRcvd;           // Bytes received in last read
				while (totalBytesRcvd < DT_LEN) {
					if ((bytesRcvd = dis.read(recvHeader, totalBytesRcvd, DT_LEN - totalBytesRcvd)) == -1)
						throw new SocketException("Connection close prematurely");
						totalBytesRcvd += bytesRcvd;
				}

				int dataLength = byteArrayToInt(recvHeader);
//				System.out.println("Received Length: " + dataLength);

				// ������ ����
				byte[] recvData = new byte[MAX_LEN];   // ���ſ� ������ �迭 ����
				totalBytesRcvd = 0;  // Total bytes received so far
				while (totalBytesRcvd < dataLength) {
					if ((bytesRcvd = dis.read(recvData, totalBytesRcvd, dataLength - totalBytesRcvd)) == -1)
					throw new SocketException("Connection close prematurely");
						totalBytesRcvd += bytesRcvd;
				}
				System.out.println("Received Data(" + dataLength + " ����Ʈ): " + new String(recvData));
			}
		}
		catch (Exception err)
		{
			System.err.println(err);
		}

	}

	private static int byteArrayToInt(byte[] data) { 
		if (data == null || data.length != 4) return 0x0; 
		// ---------- 
		return (int)( // NOTE: type cast not necessary for int 
				(0xff & data[0]) << 24  | 
				(0xff & data[1]) << 16  | 
				(0xff & data[2]) << 8   | 
				(0xff & data[3]) << 0 
				); 
	}

	public static void main(String[] args)
	{
		TCPDataServer s = new TCPDataServer(5000);
		s.serve();
	}

}



/*
	public static int byteArrayToInt(byte[] b) {
		int value = 0;
		int length = b.length;	// length of byte array
		for(int i=0; i < length; i++) {
			int shift = (length - 1 - i) * 8;
			value += (b[i] & 0x000000FF) << shift;
		}
		return value;
	}
*/

