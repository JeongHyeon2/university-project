package °úÁ¦1;

import java.util.Scanner;

public class Fibonacci1 {
	static int val;
	public static void main(String[] args) {
		Scanner sc= new Scanner(System.in);
		System.out.print("n°ª : ");
		int n=sc.nextInt();
		System.out.println(f(n));
	}
	 static int f(int n) {
		if(n<=1) return n;
		else return val=f(n-1)+f(n-2);	
	}

}
