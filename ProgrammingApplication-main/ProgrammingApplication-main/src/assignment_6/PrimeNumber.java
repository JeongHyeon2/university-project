package assignment_6;

import java.util.Scanner;

public class PrimeNumber {
	public static void main(String [] args) {
		Scanner sc = new Scanner(System.in);
		while (true) {
			System.out.print("���� �Է�: ");
			int number = sc.nextInt();
			if (number < 0) break;

			if (isPrime(number)) 
				System.out.println("�Էµ� ���� "+number+" ��/�� �Ҽ���");
			else 				
				System.out.println("�Էµ� ���� "+number+" ��/�� �Ҽ��� �ƴ�");	
		}
		sc.close();
	}
	static boolean isPrime(int num) {

		if (num < 2) return false;
		if (num == 2) return true;
		
		for (int i = 2; i < num; i++) {
			if (num % i == 0) return false;
		}
		return true;
	}

}
