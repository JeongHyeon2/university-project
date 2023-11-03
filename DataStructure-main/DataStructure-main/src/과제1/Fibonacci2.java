package °úÁ¦1;

import java.util.Scanner;

public class Fibonacci2 {
	static long val1=0;
	static long val2=1;
	static long val3;

	public static void main(String[] args) {
		Scanner sc= new Scanner(System.in);
		System.out.print("n°ª : ");
		int n=sc.nextInt();
		System.out.println(f(n));
	}
	static long f(int n) {
		if(n<=1) return n;
		else {
			for(int i=0;i<n-1;i++) {
				val3=val2+val1;
				val1=val2;
				val2=val3;
			}
			return val3;
		}
	}
}
