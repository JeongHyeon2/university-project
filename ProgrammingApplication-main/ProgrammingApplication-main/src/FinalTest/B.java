package FinalTest;

import java.util.Scanner;

public class B {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("문자열 입력: ");
		String s = sc.nextLine();
		char[] chararray = s.toCharArray();

		int[] num = new int[5];
		
	
		for(int i=0;i<chararray.length;i++) {
			if(chararray[i]=='A'||chararray[i]=='a') num[0]++;
			if(chararray[i]=='E'||chararray[i]=='e') num[1]++;
			if(chararray[i]=='I'||chararray[i]=='i') num[2]++;
			if(chararray[i]=='O'||chararray[i]=='o') num[3]++;
			if(chararray[i]=='U'||chararray[i]=='u') num[4]++;
		}
		int conNum=0;
		int otherNum=0;

		for(int i=0;i<chararray.length;i++) {
			if(isChar(chararray[i])&&!isVowel(chararray[i])) conNum++;
			if(!isChar(chararray[i])&&!isVowel(chararray[i])) otherNum++;
		}
		System.out.print("A = "+num[0]+" E = "+num[1]+" I = "+num[2]+" O = "+num[3]+" U = "+num[4]);
		System.out.print(" Consonant = "+conNum+" Others = "+otherNum);

		sc.close();

	}

	public static boolean isChar(char c) {

		switch (c) {
		case 'a':
		case 'b':
		case 'c':
		case 'd':
		case 'e':
		case 'f':
		case 'g':
		case 'h':
		case 'i':
		case 'j':
		case 'k':
		case 'l':
		case 'n':
		case 'm':
		case 'o':
		case 'p':
		case 't':
		case 'u':
		case 'v':
		case 'w':
		case 'x':
		case 'y':
		case 'z':
		case 'r':
		case 's':
		case 'q':
			
		case 'A':
		case 'B':
		case 'C':
		case 'D':
		case 'E':
		case 'F':
		case 'G':
		case 'H':
		case 'I':
		case 'J':
		case 'K':
		case 'L':
		case 'N':
		case 'M':
		case 'O':
		case 'P':
		case 'T':
		case 'U':
		case 'V':
		case 'W':
		case 'X':
		case 'Y':
		case 'Z':
		case 'R':
		case 'S':
		case 'Q':

			return true;
		}
		return false;
	}

	public static boolean isVowel(char c) {

		switch (c) {
		case 'a':
		case 'e':
		case 'i':
		case 'o':
		case 'u':
		case 'A':
		case 'E':
		case 'I':
		case 'O':
		case 'U':
			return true;
		}
		return false;
	}

}
