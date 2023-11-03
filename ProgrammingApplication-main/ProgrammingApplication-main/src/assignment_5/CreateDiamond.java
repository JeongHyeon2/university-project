package assignment_5;

import java.util.Scanner;

public class CreateDiamond {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int size;
		while (true) {
			System.out.print("Input Number? ");
			size = sc.nextInt();
			if (size % 2 == 1)
				break;
		}
		for (int i = 0; i < size / 2 + 1; i++) {
			for (int j = 0; j < size; j++) {
				if (j >= (size / 2) - i && j <= (size / 2) + i)
					System.out.print("*");
				else
					System.out.print(" ");
			}
			System.out.println();
		}
		for (int i = 0; i < size / 2; i++) {
			for (int j = 0; j < size; j++) {
				if (j > i && j < size - i - 1)
					System.out.print("*");
				else
					System.out.print(" ");
			}
			System.out.println();
		}
		sc.close();
	}
}
