import java.net.*;
import java.io.*;

public class UDPEchoClient {

       public static void main(String[] args){
             if(args.length != 2){
                    System.out.println("���� : java UDPEchoClient ip port");
                    System.exit(1);
             }

             String ip = args[0];
             int port = 0;
             try{
                    port = Integer.parseInt(args[1]);
             }catch(Exception e){
                    System.out.println("port ��ȣ�� ���� ������ �Է����ּ���");
                    System.exit(1);
             }

             InetAddress inetaddr = null;
             try{
                    inetaddr = InetAddress.getByName(ip);	// DatagramPacket�� �� ip �ּ�
             }catch(UnknownHostException e){
                    System.out.println("�߸��� �������̳� ip�Դϴ�.");
                    System.exit(1);
             }

             DatagramSocket dsock = null;
             try{
                    // Ű����κ��� ���ڿ� �Է�, System.in�� BufferedReader ���·� ��ȯ
                    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

                    dsock = new DatagramSocket();
					String line = null;
                    while((line = br.readLine())!= null){
                           // DatagramPacket�� �� ������ ��� ����
                           DatagramPacket sendPacket = new DatagramPacket(line.getBytes(), line.getBytes().length, inetaddr, port);
                           dsock.send(sendPacket);

                           if(line.equals("quit")) break;

                           byte[] buffer = new byte[line.getBytes().length];

                           // �����κ��� DatagramPacket ���� ���
                           DatagramPacket receivePacket = new DatagramPacket(buffer, buffer.length);
                           dsock.receive(receivePacket);

                           // ������ �����͸� String ��ü�� �����ϰ� ���
                           String msg = new String(receivePacket.getData(), 0, receivePacket.getData().length);
                           System.out.println("���� ������: " + msg);
                    }

                    System.out.println("UDPEchoClient�� �����մϴ�.");
             }catch(Exception ex){
                    System.out.println(ex);
             }finally{
                    if(dsock != null) dsock.close();
             }
       }
}

