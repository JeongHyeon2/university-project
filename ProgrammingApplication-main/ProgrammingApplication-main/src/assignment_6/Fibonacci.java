package assignment_6;

import java.util.Scanner;

public class Fibonacci {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("���� �Է� : ");
		int num = sc.nextInt();
		System.out.println("�Ǻ���ġ ���� ��: " + fibonacci(num));
	}

	static int fibonacci(int n) {
		int val1 = 0, val2 = 1, val3 = 0;
		if (n == 1)
			return 0;
		if (n == 2)
			return 1;
		for (int i = 0; i < n - 2; i++) {
			val3 = val2 + val1;
			val1 = val2;
			val2 = val3;
		}
		return val3;
	}
}
