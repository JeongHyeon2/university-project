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
		System.out.println("인코딩 문자 집합은 " + out.getEncoding());
		out.write("이건 되는데 왜 ");
		
		out.close();
		//fout.close();
		System.out.println("성공적으로 쓰기를 마쳤습니다.");

		FileInputStream fin = new
			FileInputStream("c:\\Temp\\hangul.txt");
		InputStreamReader in = new InputStreamReader(fin, "MS949"); 
		int c;
	 	System.out.println("디코딩 문자 집합은 " + in.getEncoding());
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

		
