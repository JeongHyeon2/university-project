package assignment_6;

import java.util.Scanner;

public class Factorial2 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("���� �Է�? ");
		int num = sc.nextInt();
		
		for(int i=1;i<num;i++) {
			if(i==num-1) System.out.print(i+"! = ");
			else System.out.print(i+"! * ");
		} 
		System.out.print(factorial(num));
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
