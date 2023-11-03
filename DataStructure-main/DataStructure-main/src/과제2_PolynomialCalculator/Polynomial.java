package ����2_PolynomialCalculator;

import java.util.Scanner;

class Polynomial {
	private int term; // 0�� �ƴ� ���� ��
	private int capacity;// Term �迭 ũ��
	private Term[] termArray;// Term �迭
	
	public Polynomial() {
		term=0;
		capacity=4;
		termArray = new Term[capacity];
	}
	//���׽� ����
	public void setPol() {
		Scanner sc = new Scanner(System.in);
		while (true) {
			int c= sc.nextInt();
			int e = sc.nextInt();
			if (c != 0 || e != 0)
				this.addLast(new Term(c, e)); // c�� 0�̰� e�� 0 �̸� �� �߰� x
			if (e == 0)
				break; // ������ 0�� �Է� �� ��� ����
		}
	}
	// �������� �� �߰�
	public void addLast(Term t) {
		if (isFull())
			expandArray();
		termArray[term] = t;
		term++;
	}

	// t�� k��° �ε��� ��ġ�� �����ֱ�
	public void initTerm(Term t, int k) {
		if (isFull())
			expandArray();
		for (int i = term - 1; i >= k; i--) {
			termArray[i + 1] = termArray[i];
		}
		termArray[k] = t;
		term++;
	}

	public void expandArray() { // �迭�� ���� ���� 2��� ����
		capacity *= 2;
		Term[] tmp = new Term[capacity];
		for (int i = 0; i < term; i++) { // ����
			Term temp = new Term(termArray[i].getCoef(), termArray[i].getExp());
			tmp[i] = temp;
		}
		termArray = tmp;
	}

	// ���׽� ��
	public Polynomial multiply(Polynomial p) {
		Polynomial result = new Polynomial();
		for (int j = 0; j < p.getTerm(); j++) {
			for (int i = 0; i < this.getTerm(); i++) {
				// t�� p1�� p2�� ���� ����
				Term t = new Term(this.getTermArray()[i].getCoef() * p.getTermArray()[j].getCoef(),
						this.getTermArray()[i].getExp() + p.getTermArray()[j].getExp());
				result.add(t);
			}
		}
		return result;
	}
	// ������ �߰� ����� ���ϱ�
	public void add(Term t) {
		int count = -1; // �迭 �ε���
		while (true) {
			count++; // k�� ���������� Ž������ �� �迭�� �������� ���� (���� ���� ������ ������ �ִ� ��� �������� ���� ��)
			if (count == this.getTerm()) {
				this.addLast(t);
				break;
			}
			if (t.getExp() == this.termArray[count].getExp()) { // ������ ���� ��
				this.termArray[count].setCoef(t.getCoef() + this.termArray[count].getCoef());
				break;
			}
			if (t.getExp() > this.termArray[count].getExp()) { // ���� ���� ���� ������ Ŭ ��
				this.initTerm(t, count);
				break;
			}
		}
		count = -1;

	}

	public void print() { // ���
		for (int i = 0; i < term; i++) {
			int c = getTermArray()[i].getCoef();
			int e = getTermArray()[i].getExp();
			if (c == 1) { // ������ 0�̸� ����׸� ���, ����� 1�̸� ��� ��� ����
				if (e == 0)
					System.out.print(c);
				else
					System.out.print("x^" + e + " + ");
			}
			else {
				if (e == 0)
					System.out.print(c);
				else
					System.out.print(c + "x^" + e + " + ");
			}
		}
	}

	// �迭�� ���� á���� �Ǻ�
	public boolean isFull() {
		return capacity == term;
	}

	// getter,setter
	public Term[] getTermArray() {
		return this.termArray;
	}

	public void setTerm(int t) {
		this.term = t;
	}

	public int getTerm() {
		return this.term;
	}

	public int getCapacity() {
		return capacity;
	}
}