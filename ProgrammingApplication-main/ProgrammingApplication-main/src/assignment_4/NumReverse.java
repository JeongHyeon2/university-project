package assignment_4;

import java.util.Scanner;

public class NumReverse {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Input Number? ");
		int num = sc.nextInt();
		int quo=num/10;
		int rem=num%10;
		
		while(true) {
			System.out.print(rem+" ");
			rem = quo % 10;
			quo = quo / 10;
			if(quo==0) {
				System.out.print(rem);
				break;
			}
		}
		sc.close();
	}
}