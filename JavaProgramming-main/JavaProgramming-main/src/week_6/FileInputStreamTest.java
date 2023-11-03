package week_6;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class FileInputStreamTest { 
    public static void main(String[] args) {
	
	try {
		FileOutputStream fout = new 
			FileOutputStream("c:\\Temp\\hangul.txt");
		OutputStreamWriter out = new OutputStreamWriter(fout); 
		System.out.println("���ڵ� ���� ������ " + out.getEncoding());
		out.write("�̰� �Ǵµ� �� ");
		
		out.close();
		//fout.close();
		System.out.println("���������� ���⸦ ���ƽ��ϴ�.");

		FileInputStream fin = new
			FileInputStream("c:\\Temp\\hangul.txt");
		InputStreamReader in = new InputStreamReader(fin, "MS949"); 
		int c;
	 	System.out.println("���ڵ� ���� ������ " + in.getEncoding());
		while ((c = in.read()) != -1) {
			System.out.print((char)c);
	}
	in.close();
	fin.close();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} 
     }
}

		
