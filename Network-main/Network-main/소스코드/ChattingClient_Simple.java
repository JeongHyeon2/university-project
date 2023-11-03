import java.io.*;
import java.net.*;
public class ChattingClient_Simple
{
   public static void main(String args[]){
      Socket theSocket = null;
      String host;
      InputStream is;
      BufferedReader reader, userInput;
      OutputStream os;
      BufferedWriter writer;
      String theLine;
      if(args.length>0){
         host=args[0]; //  �Է¹��� ȣ��Ʈ�� ���
      }else{
         host="localhost"; // ���� ȣ��Ʈ�� ���
      }
      try{
         theSocket = new Socket(host, 5000); // ���� ����
         is = theSocket.getInputStream();
         reader = new BufferedReader(new InputStreamReader(is));
         userInput = new BufferedReader(new InputStreamReader(System.in));
         os = theSocket.getOutputStream();
         writer = new BufferedWriter(new OutputStreamWriter(os));

         //read input from server
         theLine = (String)reader.readLine();
         System.out.println("Server> " + theLine);

         while(true){
            System.out.print("���� �Է�: ");
			theLine = userInput.readLine(); // ������ �Է�
            if(theLine.equals("bye")) break; // ���α׷� ����
            writer.write(theLine+'\r'+'\n'); 
            writer.flush(); // ������ ������ ���� 
            System.out.println("Server> " + reader.readLine()); //������ ������ ������ �а�, ȭ�� ���
         }
      }catch(UnknownHostException e){
         System.err.println(args[0]+" ȣ��Ʈ�� ã�� �� �����ϴ�.");
      }catch(IOException e){
         System.err.println(e);
      }finally{
         try{
            theSocket.close(); // ���� ����
         }catch(IOException e){
            System.out.println(e);
         }
      }
   }
}   