package ����3;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		while (true) {

			System.out.print("���� �Է��ϼ���(����� quit): ");
			String s = sc.nextLine();

			if (s.equals("quit")) {
				System.out.println("�����մϴ�.");
				return;
			}
			try {
				Calculator c = new Calculator();
				c.cal(s); // �� �Է½� ���

				System.out.println("postfixNotation : " + c.getPostfixNotation());
				System.out.println("��: " + c.getAnswer());
			} catch (Exception e) {
				System.out.println("[����] ������ �� ���� ����");
			}
			System.out.println();
		}

	}
}