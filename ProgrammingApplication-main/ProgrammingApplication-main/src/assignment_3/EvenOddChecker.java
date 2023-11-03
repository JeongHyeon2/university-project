package assignment_3;

import java.util.Scanner;

public class EvenOddChecker {
	public static void main(String [] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Input? ");
		int a = sc.nextInt();
		if(a%2==0) {
			System.out.println(a+" is Even number");
		}
		else {
			System.out.println(a+" is Odd number");	
		}
	}
}