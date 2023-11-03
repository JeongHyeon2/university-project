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

	//�и� 0�϶�
	if (denominator == 0) {
		cout << "�и�� 0�̵Ǹ� �ȵ˴ϴ�. DEFAULT ������ �����մϴ�." << endl;
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
		cout << "0���� ���� �� �����ϴ�." << endl;
		Fraction result; //�����ڿ��� �ڵ����� DEFAULT ������ ����
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
	if (f.numerator == 0) outputStream << f.numerator; //���ڰ� 0�϶� 0 ���
	else if (f.denominator == 1) outputStream << f.numerator; //�и� 1�϶� ���ڸ� ���
	else if (f.denominator == -1) outputStream << "-" << f.numerator; //�и� -1�϶� (-1*)���� ���
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
			cout << "�и�� 0�� �� �� �����ϴ�. ���Է� �޽��ϴ�." << endl;
			cout << "���ڿ� �и� ������ �Է��ϼ��� : " << endl;
		}
	} while (deno == 0);
}