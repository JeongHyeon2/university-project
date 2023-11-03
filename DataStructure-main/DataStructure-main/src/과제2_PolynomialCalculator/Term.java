package 과제2_PolynomialCalculator;

class Term {
	private int coef; // 계수
	private int exp; // 차수

	public Term(int c, int e) {
		coef = c;
		exp = e;
	}

	public Term() {
	}

	// getter, setter
	public void setCoef(int c) {
		this.coef = c;
	}

	public void setExp(int e) {
		this.exp = e;
	}

	public int getCoef() {
		return coef;
	}

	public int getExp() {
		return exp;
	}
}