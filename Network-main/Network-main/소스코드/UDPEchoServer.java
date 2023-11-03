import java.net.*;
import java.io.*;

public class UDPEchoServer {

       private static final int ECHOMAX = 255; // Maximum size of echo datagram		
       public static void main(String[] args){
             if(args.length != 1){
                    System.out.println("���� : java UDPEchoServer port");
                    System.exit(1);
             }

             int port = 0;
             try{
                    port = Integer.parseInt(args[0]);
             }catch(Exception e){
                    System.out.println("Port ��ȣ�� ���� ������ �Է��ؼ� �ּ���.");
                    System.exit(1);
             }

             DatagramSocket dsock = null;
             try{
                    System.out.println("���� �������Դϴ�.");
                    dsock = new DatagramSocket(port);	// DatagramSocket ��ü ����

                    byte[] buffer = new byte[ECHOMAX];
                    DatagramPacket receivePacket = new DatagramPacket(buffer, ECHOMAX);

                    while(true){
                           // Ŭ���̾�Ʈ�κ��� DatagramPacket ���� ���
                           dsock.receive(receivePacket);

                           // ������ �����͸� String ��ü�� �����ϰ� ���
                           String msg = new String(receivePacket.getData(), 0, receivePacket.getLength());
                           System.out.println("���� ������: " + msg);

                           if(msg.equals("quit")) break;

                           // ������ ������ Ŭ���̾�Ʈ���� ����
                           DatagramPacket sendPacket = new DatagramPacket(receivePacket.getData(), receivePacket.getData().length, receivePacket.getAddress(), receivePacket.getPort());
                           dsock.send(sendPacket);

						   receivePacket.setLength(ECHOMAX);	// Reset length to avoid shrinking buffer
                    }

                    System.out.println("UDPEchoServer�� �����մϴ�.");
             }catch(Exception e){
                    System.out.println(e);
             }finally{
                    if(dsock != null) dsock.close();
             }
       }
}
