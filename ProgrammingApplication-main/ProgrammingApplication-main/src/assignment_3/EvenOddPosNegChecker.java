package assignment_3;

import java.util.Scanner;

public class EvenOddPosNegChecker {
	public static void main(String [] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Input? ");
		int a = sc.nextInt();	
		if(a%2==0 && a>0) {
			System.out.println(a+" is Even and Positive number");
		}
		else if (a%2==0 && a<0) {
			System.out.println(a+" is Even and Negative number");
		}
		else if (a%2!=0 && a>0) {
			System.out.println(a+" is Odd and Positive number");
		}
		else if (a%2!=0 && a<0) {
			System.out.println(a+" is Odd and Negative number");
		}
		else {
			System.out.println(a+" is zero");
		}
	}
}
