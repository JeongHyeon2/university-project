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

	//�и� 0�϶�
	if (denominator == 0) {
		cout << "�и�� 0�̵Ǹ� �ȵ˴ϴ�. DEFAULT������ �����մϴ�." << endl;
		numerator = DEFAULT_NUMERATOR;
		denominator = DEFAULT_DENOMINATOR;
		return;
	}

	//�и� ���ڰ� �� �� �����϶�
	if (numerator < 0 && denominator < 0) {
		numerator *= (-1);
		denominator *= (-1);
	}

	//���
	for (int i = smallerAbsNum(denominator, numerator); i > 1; i--) {
		// i�� �и� ������ ����� ��
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
	if (numerator == 0) cout << numerator; //���ڰ� 0�϶� 0 ���
	else if (denominator == 1) cout << numerator; //�и� 1�϶� ���ڸ� ���
	else if (denominator == -1) cout << "-" << numerator; //�и� -1�϶� (-1*)���� ���
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
		cout << "0���� ���� �� �����ϴ�." << endl;
		Fraction result; //�����ڿ��� �ڵ����� default ������ ����
		return result;
	}
	else {
		Fraction result(numerator * f.denominator, denominator * f.numerator);
		return result;
	}
}
