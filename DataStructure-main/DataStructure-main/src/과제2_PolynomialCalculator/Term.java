package ����2_PolynomialCalculator;

class Term {
	private int coef; // ���
	private int exp; // ����

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