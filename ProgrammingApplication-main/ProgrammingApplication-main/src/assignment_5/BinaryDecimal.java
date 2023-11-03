package assignment_5;

import java.util.Scanner;

public class BinaryDecimal {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Input Binary Number? ");
		char[] c = sc.next().toCharArray();
		int result=0;
		
		for(int i=c.length-1;i>=0;i--) {
			if(c[i]=='1') result+=Math.pow(2, c.length-1-i);
		}
		System.out.println("Decimal Number: "+result);
	}
}
