package assignment_3;

import java.util.Scanner;

public class Number {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int max=Integer.MIN_VALUE, min=Integer.MAX_VALUE, n, count=0;
		float sum=0;
		while(true) {
			System.out.print("Input number? ");
			n=sc.nextInt();
			if(n<0) break;
	
			if(n>max) max=n;
				
			if(n<min) min=n;
				
			sum+=n; 
			count++;
		   }
		sc.close();
		System.out.println("�ּڰ� : "+min);
		System.out.println("�ִ� : "+max);
		System.out.println("��հ� : "+sum/count);
	}

}
