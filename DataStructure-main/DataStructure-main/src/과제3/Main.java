package 과제3;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		while (true) {

			System.out.print("식을 입력하세요(종료는 quit): ");
			String s = sc.nextLine();

			if (s.equals("quit")) {
				System.out.println("종료합니다.");
				return;
			}
			try {
				Calculator c = new Calculator();
				c.cal(s); // 식 입력시 계산

				System.out.println("postfixNotation : " + c.getPostfixNotation());
				System.out.println("답: " + c.getAnswer());
			} catch (Exception e) {
				System.out.println("[오류] 이해할 수 없는 수식");
			}
			System.out.println();
		}

	}
}