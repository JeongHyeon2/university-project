package assignment_6;

import java.util.Scanner;

public class Factorial {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("숫자 입력? ");
		int num = sc.nextInt();
		
		System.out.println(num+"! = "+factorial(num));
		sc.close();
		
	}
	static int factorial(int num) {
		int result=1;
		int count=num;
		if(num<=1) return 1;
		for(int i=0;i<count;i++) {
			result*=num;
			num--;
		}
		return result;
	}
}
