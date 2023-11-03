package assignment_6;

import java.util.Scanner;

public class PrimeNumber {
	public static void main(String [] args) {
		Scanner sc = new Scanner(System.in);
		while (true) {
			System.out.print("정수 입력: ");
			int number = sc.nextInt();
			if (number < 0) break;

			if (isPrime(number)) 
				System.out.println("입력된 정수 "+number+" 은/는 소수임");
			else 				
				System.out.println("입력된 정수 "+number+" 은/는 소수가 아님");	
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
