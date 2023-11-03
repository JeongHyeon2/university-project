package assignment_3;
import java.util.Scanner;

public class Divisor {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Input Number? ");
		int num = sc.nextInt();
		
		for(int quo=1;quo<=num;quo++) {
			if(num%quo==0) System.out.print(quo+" ");
		}
		sc.close();
	}
}
