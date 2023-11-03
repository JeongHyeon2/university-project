package assignment_7;

import java.util.Scanner;

public class NumberShift {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("10개의 숫자 입력: ");
		int [] intArray = new int[10];
		for(int i=0;i<10;i++) {
			intArray[i]=sc.nextInt();	
		}
		System.out.print("이동 방향 <왼쪽L, 오른쪽R>? ");
		char c = sc.next().charAt(0);
		
		System.out.print("이동값? ");
		int value = sc.nextInt();
		
		int [] clone = intArray.clone();
		
		if(c=='L') {
			for(int i=0;i<10;i++) 
				intArray[i]=clone[(i+value)%10];
		}
		else {
			for(int i=value;i<10+value;i++) 
				intArray[i%10]=clone[(i-value)%10];
		}
		
		for(int i=0;i<10;i++) System.out.print(intArray[i]+" ");
		sc.close();
	}

}
