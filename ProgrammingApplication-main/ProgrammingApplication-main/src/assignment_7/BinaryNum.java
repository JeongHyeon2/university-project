package assignment_7;

import java.util.ArrayList;
import java.util.Scanner;

public class BinaryNum {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("10진수 정수 입력(20억 이하): ");
		int num = sc.nextInt();
		ArrayList<Integer> array = new ArrayList<Integer>();
		
		while(true) {
			array.add(num%2);	
			num/=2;
			if(num==0) break;
		}
		System.out.print("2진수 변환 결과: ");
		for(int i=array.size()-1;i>=0;i--) System.out.print(array.get(i));
		sc.close();

	}

}
