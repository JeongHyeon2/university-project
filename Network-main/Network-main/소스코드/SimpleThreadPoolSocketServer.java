import java.io.*;
import java.net.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SimpleThreadPoolSocketServer {
	// ������ ��Ʈ�� �����մϴ�.
	private static final int PORT = 5000;
	// ������ Ǯ�� �ִ� ������ ������ �����մϴ�.
	private static final int THREAD_CNT = 2;
	private static ExecutorService threadPool = Executors.newFixedThreadPool(THREAD_CNT);
	public static void main(String[] args) {

		try {
			// �������� ����
			ServerSocket serverSocket = new ServerSocket(PORT);

			// ���ϼ����� ����ɶ����� ���ѷ���
			while(true){
				// ���� ���� ��û�� �ö����� ����մϴ�.
				Socket socket = serverSocket.accept();
				try{
					// ��û�� ���� ������ Ǯ�� ������� ������ �־��ݴϴ�.
					// ���Ĵ� ������ ������ ó���մϴ�.
					threadPool.execute(new ConnectionWrap(socket));
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

// ���� ó���� ���� Ŭ�����Դϴ�.
class ConnectionWrap implements Runnable{

	private Socket theSocket = null;
	InputStream is;
	BufferedReader reader;
	OutputStream os;
	BufferedWriter writer;
	String theLine;

	public ConnectionWrap(Socket socket) {
		this.theSocket = socket;
	}

	@Override
	public void run() {

		try {
			is = theSocket.getInputStream();
			reader = new BufferedReader(new InputStreamReader(is));
			os = theSocket.getOutputStream();
			writer = new BufferedWriter(new OutputStreamWriter(os));
			while((theLine = reader.readLine()) != null ){ // Ŭ���̾�Ʈ ������ ����
				System.out.println(theLine); // ���� ������ ȭ�� ���
				writer.write(theLine+'\r'+'\n'); 
				writer.flush(); // Ŭ���̾�Ʈ�� ������ ������
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				theSocket.close(); // ���� ����
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}