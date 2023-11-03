#include "Fraction.h"
#include <iostream>
#include <stdlib.h>

using namespace std;

Fraction::Fraction() {
	numerator = DEFAULT_NUMERATOR;
	denominator = DEFAULT_DENOMINATOR;
}
Fraction::Fraction(int nume, int deno) :numerator(nume), denominator(deno)
{
	testFraction();

}

void Fraction::testFraction() {

	//분모가 0일때
	if (denominator == 0) {
		cout << "분모는 0이되면 안됩니다. DEFAULT값으로 변경합니다." << endl;
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


void Fraction::print() {
	if (numerator == 0) cout << numerator; //분자가 0일때 0 출력
	else if (denominator == 1) cout << numerator; //분모가 1일때 분자만 출력
	else if (denominator == -1) cout << "-" << numerator; //분모가 -1일때 (-1*)분자 출력
	else cout << numerator << "/" << denominator;
	cout<<endl;
}

Fraction Fraction::add(Fraction f)
{
	Fraction result(numerator * f.denominator + f.numerator * denominator, denominator * f.denominator);
	return result;
}

Fraction Fraction::subtract(Fraction f)
{
	Fraction result(numerator * f.denominator - f.numerator * denominator, denominator * f.denominator);
	return result;
}

Fraction Fraction::multiply(Fraction f)
{
	Fraction result(numerator * f.numerator, denominator * f.denominator);
	return result;
}

Fraction Fraction::divide(Fraction f)
{
	if (f.numerator == 0) {
		cout << "0으로 나눌 수 없습니다." << endl;
		Fraction result; //생성자에서 자동으로 default 값으로 설정
		return result;
	}
	else {
		Fraction result(numerator * f.denominator, denominator * f.numerator);
		return result;
	}
}
