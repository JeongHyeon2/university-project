package assignment_6;

import java.util.Random;
import java.util.Scanner;

public class GuessNumber {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Random rnd = new Random();
		
		System.out.println("���� ������ ���ڸ� ���纸���� (0~99)");
		int answer = rnd.nextInt(100);
		
		while (true) {
			System.out.print("���� �Է�: ");
			int num = sc.nextInt();
			if (num > answer)
				System.out.println("���� ������ ���ں��� �����ϴ�.");
			else if (num < answer)
				System.out.println("���� ������ ���ں��� �����ϴ�.");
			else {
				System.out.println("�����Դϴ�!");
				break;
			}
		}
		sc.close();
	}

}
