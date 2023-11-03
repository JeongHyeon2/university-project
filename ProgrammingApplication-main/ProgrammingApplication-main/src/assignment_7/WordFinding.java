package assignment_7;

import java.util.Scanner;

public class WordFinding {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String[] array = new String[10];

		for (int i = 0; i < 10; i++) {
			System.out.print((i + 1) + "번째 단어? ");
			array[i] = sc.nextLine().toLowerCase();
		}
		for (int i = 0; i < 10; i++) {
			if (isContinuous(array[i]))
				System.out.println(array[i]);
		}
		sc.close();
	}

	public static boolean isContinuous(String s) {
		char[] cArray = s.toCharArray();
		for (int i = 0; i < cArray.length - 1; i++) {
			if (isVowel(cArray[i]) && isVowel(cArray[i + 1]))
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
			return true;
		}
		return false;
	}

}
