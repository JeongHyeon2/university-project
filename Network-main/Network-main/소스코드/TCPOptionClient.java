import java.io.*;
import java.net.*;

public class TCPOptionClient
{
	public static void main(String args[]){
		Socket theSocket = null;
		String host;
		InputStream is;
		BufferedReader reader, userInput;
		OutputStream os;
		BufferedWriter writer;
		String theLine;

		try{
			theSocket = new Socket();
			theSocket.setReuseAddress(true);
			theSocket.setTcpNoDelay(true);
			SocketAddress addr = new InetSocketAddress("www.kumoh.ac.kr", 80);
			theSocket.connect(addr);

			is = theSocket.getInputStream();
			reader = new BufferedReader(new InputStreamReader(is));
			userInput = new BufferedReader(new InputStreamReader(System.in));
			os = theSocket.getOutputStream();
			writer = new BufferedWriter(new OutputStreamWriter(os));
			System.out.println("������ ������ �Է��Ͻʽÿ�.");
			while(true){
				theLine = userInput.readLine(); // �����͸� �Է��Ѵ�.
				if(theLine.equals("quit")) break; // ���α׷� ����
				writer.write(theLine+'\r'+'\n'); 
				writer.flush(); // ������ ������ ���� 
				System.out.println(reader.readLine()); //�ǵ��� �ް�, ȭ�鿡 ����Ѵ�.
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
}
