import java.io.*;
import java.net.*;
public class GetNameAddress
{
   public static void main(String args[]){
      InetAddress addr = null;
      InetAddress[] addrArr = null;
      String hostname;
      BufferedReader br;
      System.out.println("ȣ��Ʈ �̸� �Ǵ� IP �ּҸ� �Է��ϼ���");
	  br = new BufferedReader(new InputStreamReader(System.in));

      try{
         if((hostname = br.readLine()) != null){
            addr = InetAddress.getByName(hostname); // ������ �̸����� InetAddress ��ü ����
            System.out.println("ȣ��Ʈ �̸��� "+addr.getHostName());
            System.out.println("IP �ּҴ� "+addr.getHostAddress());

			addrArr = InetAddress.getAllByName(hostname); // ������ �̸����� InetAddress �迭 ��ü ����
			for(int i =0; i < addrArr.length;i++){
				System.out.println("IP ���["+i+"] : " + addrArr[i]);
			}

         }
         InetAddress laddr = InetAddress.getLocalHost();
         System.out.println("���� ȣ��Ʈ �̸��� "+laddr.getHostName());
         System.out.println("���� IP �ּҴ� "+laddr.getHostAddress());
      }catch(UnknownHostException e){
         System.out.println(e);
      }catch(IOException e){
         System.out.println(e);
      }
   }
}