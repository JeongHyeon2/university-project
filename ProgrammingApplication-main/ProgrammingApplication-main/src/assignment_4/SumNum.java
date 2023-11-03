package assignment_4;

import java.util.Scanner;

public class SumNum {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int sum;
		
		System.out.print("Input Number1 ? ");
		int num1=sc.nextInt();
		
		System.out.print("Input Number2 ? ");
		int num2=sc.nextInt();
		
		if(num1>num2) {
			sum= num1*(num1+1)/2 - (num2-1)*(num2)/2;
		}
		else  {
			sum= num2*(num2+1)/2- (num1-1)*num1/2;
		}
		System.out.println("작은 수부터 큰 수까지의 합: "+sum);
	}

}
