#pragma once
#include<iostream>

using namespace std;
class Fraction
{
public:
	Fraction(int numerator, int denominator);
	Fraction();

	Fraction operator+(const Fraction& f) const;
	Fraction operator-(const Fraction& f) const;
	Fraction operator*(const Fraction& f) const;
	Fraction operator/(const Fraction& f) const;

	bool operator<(const Fraction& f) const;
	bool operator>(const Fraction& f) const;
	bool operator==(const Fraction& f) const;

	friend ostream& operator <<(ostream& outputStream, const Fraction& f);
	friend istream& operator >>(istream& inputStream, Fraction& f);
private:
	int numerator; // 분자
	int denominator; // 분모
	const static int DEFAULT_NUMERATOR = 0;
	const static int DEFAULT_DENOMINATOR = 1;
	int smallerAbsNum(int num1, int num2);
	void testFraction();

};



