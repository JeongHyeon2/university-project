package 과제2_PolynomialCalculator;

public class Main {
	public static void main(String[] args) {
		Polynomial p1 = new Polynomial();
		Polynomial p2 = new Polynomial();
		Polynomial result = new Polynomial();
		System.out.println("첫번째 다항식을 계수-지수 쌍으로 입력하세요.(지수가 0이 되면 종료)");
		p1.setPol();
		System.out.println("두번째 다항식을 계수-지수 쌍으로 입력하세요.(지수가 0이 되면 종료)");
		p2.setPol();
		result = p1.multiply(p2);
		result.print();
	}
}
