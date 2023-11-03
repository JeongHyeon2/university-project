package assignment_10;

import java.util.Scanner;

public class Practice {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("¼ö: ");
		String num = sc.nextLine();
		char[] data = num.toCharArray();
		int decimalNum = 0;
		for(int i=0;i<data.length;i++)
		{
			if(data[i]=='1') decimalNum+=Math.pow(2, i);
		}
		System.out.println(decimalNum);
	}

}
