package ����2_PolynomialCalculator;

public class Main {
	public static void main(String[] args) {
		Polynomial p1 = new Polynomial();
		Polynomial p2 = new Polynomial();
		Polynomial result = new Polynomial();
		System.out.println("ù��° ���׽��� ���-���� ������ �Է��ϼ���.(������ 0�� �Ǹ� ����)");
		p1.setPol();
		System.out.println("�ι�° ���׽��� ���-���� ������ �Է��ϼ���.(������ 0�� �Ǹ� ����)");
		p2.setPol();
		result = p1.multiply(p2);
		result.print();
	}
}
