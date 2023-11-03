import java.io.*;
import java.net.*;

public class ShutDownEx {
 public static void main(String[] ar){

  //�ʱ�ȭ
  InetAddress ia = null;
  Socket soc = null;
  PrintWriter out = null;
  BufferedReader br = null;

  try{
   ia = InetAddress.getByName("www.kumoh.ac.kr");  // �б� ���� �ּҸ� ��� InetAddress ��ü�� �����Ѵ�.
   soc = new Socket(ia, 80);    //ia ��ü�� ����� IP �ּҷ� �����Ͽ� TCP�� 80�� ��Ʈ�� ������ ��û�Ѵ�.

   out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(soc.getOutputStream())));
   //��� ��Ʈ���� �����Ѵ�.

   br = new BufferedReader(new InputStreamReader(soc.getInputStream())); 
   //�Է� ��Ʈ���� �����Ѵ�.

  }catch(IOException ie){
   System.err.println("���� ���� : "+ie.toString());
   System.exit(-1);
  }
  
  try{
   out.println("GET http://www.kumoh.ac.kr/main.do HTTP/1.0\r\n\n");
   //��û ���Ŀ� �°� main ������ ��û�Ѵ�.
   out.flush();
   //���۸� ����. ������ �ۼ��� ������ ������ �����Ѵ�.
   
   //�������� ������ �� �����͸� �Է� �޴´�.
   while(true){
    String str = br.readLine();
    //�������� ���۵Ǿ� ���� ������ �о� ���δ�.

    if (str == null){ //���̻� �о�� �����Ͱ� ���ٸ�
     break;
    }
    System.out.println(str);
   }
   soc.shutdownOutput();  //���� ��� ��Ʈ�� ��ü�� �ݴ´�.
   soc.shutdownInput();    //���� �Է� ��Ʈ�� ��ü�� �ݴ´�.
   soc.close();                //������ �����Ѵ�.
  }catch(IOException ie){}
 }
}
