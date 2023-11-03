package FinalTest;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Scanner;

public class C {
		static Scanner sc = new Scanner(System.in);
		static	Person[] parr = new Person[10];
		static	int numPeople=0;
		static	int sum=0;
		
	public static void main(String[] args) {
		
		while(true) {
			System.out.print("("+sum+") >> ");
			String[] arr = sc.nextLine().replace(" ","").split(",");
			switch(arr[0]) {
			case "i": 
				parr[numPeople]=new Person(Integer.parseInt(arr[1]),arr[2],Integer.parseInt(arr[3]),Integer.parseInt(arr[4]),Integer.parseInt(arr[5]));
				sum+=(Integer.parseInt(arr[3]))+(Integer.parseInt(arr[4]))+(Integer.parseInt(arr[5]));
				numPeople++;
				break;
			case "d":
				int key=Integer.parseInt(arr[1]);
				for(int i=0;i<numPeople;i++) {
					if(parr[i].getKey()==key) {
						sum=sum-(parr[i].getEng()+parr[i].getKorean()+parr[i].getMath());
						parr[key]=parr[numPeople];
						parr[numPeople]=null;
						numPeople--;	
					}
				}
				break;
			case "p":
				System.out.println(printAll());
				break;
			case "s":
				break;
			case "w":
				saveFile(arr[1]);
				break;
			case "r":
				readFile(arr[1]);
				break;
			
			case "x":return;
			}

			
		}
	}

	static String printAll() {
		String s = "";
		for(int i=0;i<numPeople;i++) {
			s+=parr[i].getKey()+" "+parr[i].getName()+" "+parr[i].getKorean()+" "+parr[i].getEng()+" "+parr[i].getMath()+" "+parr[i].getAvg()+"\n";
		}
		return s;
	}
	static String print() {
		String s = "";
		for(int i=0;i<numPeople;i++) {
			s+=parr[i].getKey()+","+parr[i].getName()+","+parr[i].getKorean()+","+parr[i].getEng()+","+parr[i].getMath()+"\n";
		}
		return s;
	}
	static void printInc() {
		Person[] parr = new Person[10];
		
	}
	static void saveFile(String path) { // 파일 저장
		System.out.println("Saved to " + path);
		try {
			OutputStream output = new FileOutputStream(path);
			String str = print();
			byte[] by = str.getBytes();
			output.write(by);

		} catch (Exception e) {
			e.getStackTrace();
		}
	
	}
	static void readFile(String path) {// 파일 읽기
		System.out.println("Loaded from " + path);
		File file = new File(path);
		try {
			if (file.isFile()) {
				BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path)));

				String s = "";
				// 한줄씩 읽어서
				while ((s = br.readLine()) != null) {
					String[] arr = s.split(",");
	
			
					parr[numPeople]=new Person(Integer.parseInt(arr[0]),arr[1],Integer.parseInt(arr[2]),Integer.parseInt(arr[3]),Integer.parseInt(arr[4]));
					sum+=(Integer.parseInt(arr[2]))+(Integer.parseInt(arr[3]))+(Integer.parseInt(arr[4]));
					numPeople++;
					
				}
				br.close();
			}
		} catch (IOException e) {

		}
	}


}
