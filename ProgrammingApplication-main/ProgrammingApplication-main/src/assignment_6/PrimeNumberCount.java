package assignment_6;

import java.util.Scanner;

public class PrimeNumberCount {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		while (true) {
			int primenum = 0; //�Ҽ� ���� ����
			System.out.print("���� �Է�: ");
			int number = sc.nextInt();
			if (number < 0) break;
			

			for (int i = 0; i <= number; i++) {
				if (isPrime(i)) primenum++;
			}
			
			System.out.println("�Ҽ� ����: " + primenum + "\n");
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
