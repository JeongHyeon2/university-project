package assignment_6;

import java.util.Scanner;

public class FactorialRecursion {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("���� �Է�? ");
		int num = sc.nextInt();
		
		if(num<0) System.out.println("�߸��� �Է��Դϴ�.");
		else System.out.println(num+"!"+" = "+factorial(num));
		sc.close();
	}
	
	static int factorial(int num) {
		
		if(num<=1) return 1;
		return num*=factorial(num-1) ; 
	}

}
