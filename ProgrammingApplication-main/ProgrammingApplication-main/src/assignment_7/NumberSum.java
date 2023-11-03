package assignment_7;

import java.util.Scanner;

public class NumberSum {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int [] intArray = new int[100];
		int i=0,sum=0;
		while(true) {
			System.out.print("숫자 입력: ");
			int num = sc.nextInt();
			if(num<0) break;
			
			intArray[i]=num;
			i++;
		}
		for(int j=0;j<i;j++) {
			if(j==i-1) System.out.print(intArray[j]+" = ");
			else System.out.print(intArray[j]+" + ");
			sum+=intArray[j];
		}
		System.out.print(sum);
		sc.close();
	}

}
