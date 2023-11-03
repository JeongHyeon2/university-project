package assignment_5;

import java.util.Scanner;

public class CreateRectangle {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		while (true) {
			System.out.print("Input Shape Size: ");

			int size = sc.nextInt();
			if(size<0) break;
			for (int i = 0; i < size; i++) {
				for (int j = 0; j < size; j++) {
					if (i == 0 || i == size - 1) {
						System.out.print("*");
					} else {
						if (j == 0 || j == size - 1) {
							System.out.print("*");
						} else {
							System.out.print(" ");
						}
					}
				}
				System.out.println();
			}
		}
		sc.close();
	}

}
