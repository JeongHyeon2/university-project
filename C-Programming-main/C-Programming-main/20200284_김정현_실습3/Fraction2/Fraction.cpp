#include "Fraction.h"
#include <stdlib.h>
#include <iostream>

using namespace std;
Fraction::Fraction(int nume, int deno) :numerator(nume), denominator(deno)
{
	testFraction();

}
Fraction::Fraction()
{
	numerator = DEFAULT_NUMERATOR;
	denominator = DEFAULT_DENOMINATOR;
}

void Fraction::testFraction() {

	//분모가 0일때
	if (denominator == 0) {
		cout << "분모는 0이되면 안됩니다. DEFAULT 값으로 변경합니다." << endl;
		numerator = DEFAULT_NUMERATOR;
		denominator = DEFAULT_DENOMINATOR;
		return;
	}

	//분모 분자가 둘 다 음수일때
	if (numerator < 0 && denominator < 0) {
		numerator *= (-1);
		denominator *= (-1);
	}

	//약분
	for (int i = smallerAbsNum(denominator, numerator); i > 1; i--) {
		// i가 분모 분자의 약수일 때
		if (denominator % i == 0 && numerator % i == 0) {
			denominator /= i;
			numerator /= i;
		}
	}

}


int Fraction::smallerAbsNum(int num1, int num2) {
	int absNum1 = abs(num1);
	int absNum2 = abs(num2);
	if (absNum1 > absNum2) return absNum2;
	else return absNum1;
}


Fraction Fraction::operator+(const Fraction& f) const {
	Fraction result(f.numerator * denominator + numerator * f.denominator, f.denominator * denominator);
	return result;
}
Fraction Fraction::operator-(const Fraction& f) const {
	Fraction result(numerator * f.denominator - f.numerator * denominator, f.denominator * denominator);
	return result;
}
Fraction Fraction::operator*(const Fraction& f) const {
	Fraction result(numerator * f.numerator, denominator * f.denominator);
	return result;
}
Fraction Fraction::operator/(const Fraction& f) const {
	if (f.numerator == 0) {
		cout << "0으로 나눌 수 없습니다." << endl;
		Fraction result; //생성자에서 자동으로 DEFAULT 값으로 설정
		return result;
	}
	else {
		Fraction result(numerator * f.denominator, denominator * f.numerator);
		return result;
	}
}

bool Fraction::operator<(const Fraction& f) const {
	Fraction result(numerator * f.denominator - f.numerator * denominator, f.denominator * denominator);
	if (result.denominator * result.numerator < 0) return true;
	else return false;
}

bool Fraction::operator>(const Fraction& f) const {
	Fraction result(numerator * f.denominator - f.numerator * denominator, f.denominator * denominator);
	if (result.denominator * result.numerator > 0) return true;
	else return false;
}

bool Fraction::operator==(const Fraction& f) const {
	Fraction result(numerator * f.denominator - f.numerator * denominator, f.denominator * denominator);
	if (result.denominator * result.numerator == 0) return true;
	else return false;
}

ostream& operator<<(ostream& outputStream, const Fraction& f)
{
	if (f.numerator == 0) outputStream << f.numerator; //분자가 0일때 0 출력
	else if (f.denominator == 1) outputStream << f.numerator; //분모가 1일때 분자만 출력
	else if (f.denominator == -1) outputStream << "-" << f.numerator; //분모가 -1일때 (-1*)분자 출력
	else outputStream << f.numerator << "/" << f.denominator;
	return outputStream;
}

istream& operator >>(istream& is, Fraction& f)
{
	int nume, deno;
	do {
		is >> nume >> deno;
		if (deno != 0) {
			f = Fraction(nume, deno);
			return is;
		}
		else {
			cout << "분모는 0이 될 수 없습니다. 재입력 받습니다." << endl;
			cout << "분자와 분모를 정수로 입력하세요 : " << endl;
		}
	} while (deno == 0);
}