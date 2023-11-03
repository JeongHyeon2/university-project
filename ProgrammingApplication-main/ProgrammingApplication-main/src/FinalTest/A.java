package FinalTest;

import java.util.Scanner;

public class A {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("숫자 입력? ");
		int num = sc.nextInt();
		
		System.out.println(num+"! = "+factorial(num));
		
		String s = ""+factorial(num);
		char[] arr = s.toCharArray();
		String[] sarr = new String[1000];
		int sum=0;
		for(int i=0;i<arr.length;i++) {
			sarr[i]=arr[i]+"";
		}
		for(int i=0;i<arr.length;i++) {
			sum+=Integer.parseInt(sarr[i]);
		}
		System.out.println("Sum of each digit = "+sum);
	
		sc.close();
		
	}
	static int factorial(int num) {
		int result=1;
		int count=num;
		if(num<=1) return 1;
		for(int i=0;i<count;i++) {
			result*=num;
			num--;
		}
		return result;
	}

}
