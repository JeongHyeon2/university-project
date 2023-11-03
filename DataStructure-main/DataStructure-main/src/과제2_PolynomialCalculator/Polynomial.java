package 과제2_PolynomialCalculator;

import java.util.Scanner;

class Polynomial {
	private int term; // 0이 아닌 항의 수
	private int capacity;// Term 배열 크기
	private Term[] termArray;// Term 배열
	
	public Polynomial() {
		term=0;
		capacity=4;
		termArray = new Term[capacity];
	}
	//다항식 설정
	public void setPol() {
		Scanner sc = new Scanner(System.in);
		while (true) {
			int c= sc.nextInt();
			int e = sc.nextInt();
			if (c != 0 || e != 0)
				this.addLast(new Term(c, e)); // c가 0이고 e도 0 이면 항 추가 x
			if (e == 0)
				break; // 차수가 0이 입력 될 경우 종료
		}
	}
	// 마지막에 항 추가
	public void addLast(Term t) {
		if (isFull())
			expandArray();
		termArray[term] = t;
		term++;
	}

	// t를 k번째 인덱스 위치에 끼워넣기
	public void initTerm(Term t, int k) {
		if (isFull())
			expandArray();
		for (int i = term - 1; i >= k; i--) {
			termArray[i + 1] = termArray[i];
		}
		termArray[k] = t;
		term++;
	}

	public void expandArray() { // 배열이 가득 차면 2배로 증가
		capacity *= 2;
		Term[] tmp = new Term[capacity];
		for (int i = 0; i < term; i++) { // 복사
			Term temp = new Term(termArray[i].getCoef(), termArray[i].getExp());
			tmp[i] = temp;
		}
		termArray = tmp;
	}

	// 다항식 곱
	public Polynomial multiply(Polynomial p) {
		Polynomial result = new Polynomial();
		for (int j = 0; j < p.getTerm(); j++) {
			for (int i = 0; i < this.getTerm(); i++) {
				// t에 p1과 p2의 곱을 저장
				Term t = new Term(this.getTermArray()[i].getCoef() * p.getTermArray()[j].getCoef(),
						this.getTermArray()[i].getExp() + p.getTermArray()[j].getExp());
				result.add(t);
			}
		}
		return result;
	}
	// 곱셈의 중간 결과들 더하기
	public void add(Term t) {
		int count = -1; // 배열 인덱스
		while (true) {
			count++; // k가 마지막까지 탐색했을 때 배열의 마지막에 저장 (새로 들어온 지수가 기존에 있던 모든 지수보다 작을 때)
			if (count == this.getTerm()) {
				this.addLast(t);
				break;
			}
			if (t.getExp() == this.termArray[count].getExp()) { // 지수가 같을 때
				this.termArray[count].setCoef(t.getCoef() + this.termArray[count].getCoef());
				break;
			}
			if (t.getExp() > this.termArray[count].getExp()) { // 새로 들어온 항의 지수가 클 때
				this.initTerm(t, count);
				break;
			}
		}
		count = -1;

	}

	public void print() { // 출력
		for (int i = 0; i < term; i++) {
			int c = getTermArray()[i].getCoef();
			int e = getTermArray()[i].getExp();
			if (c == 1) { // 차수가 0이면 상수항만 출력, 계수가 1이면 계수 출력 생략
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

	// 배열이 가득 찼는지 판별
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