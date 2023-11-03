package assignment_3;

import java.util.Scanner;

public class VowelChecker {
		public static void main(String [] args) {
			Scanner sc = new Scanner(System.in);
			System.out.print("Input? ");
			char c = sc.nextLine().charAt(0);
			sc.close();
			switch(c) {
			case'A':case'E':case'I':case'O':case'U':
			case'a':case'e':case'i':case'o':case'u':System.out.println(c+" is Vowel"); break;
			default :System.out.println(c+" is Consonant"); break;
		}	
	}
}


