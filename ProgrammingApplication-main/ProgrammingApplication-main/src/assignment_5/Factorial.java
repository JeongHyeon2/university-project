package assignment_5;

import java.util.Scanner;

public class Factorial {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int result=1;
		
		System.out.print("Input ? ");
		int num=sc.nextInt();
		sc.close();
		
		System.out.print(num+"! = ");
		
		for(int i=num;i>0;i--) {
			result*=i;
			if(i==1) {
				System.out.print(i+" = ");
			}
			else {
				System.out.print(i+" * ");
			}
		}
		System.out.print(result);
	}
}