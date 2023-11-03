package assignment_6;

import java.util.Random;
import java.util.Scanner;

public class GuessNumber {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Random rnd = new Random();
		
		System.out.println("내가 선택한 숫자를 맞춰보세요 (0~99)");
		int answer = rnd.nextInt(100);
		
		while (true) {
			System.out.print("숫자 입력: ");
			int num = sc.nextInt();
			if (num > answer)
				System.out.println("내가 선택한 숫자보다 높습니다.");
			else if (num < answer)
				System.out.println("내가 선택한 숫자보다 낮습니다.");
			else {
				System.out.println("정답입니다!");
				break;
			}
		}
		sc.close();
	}

}
