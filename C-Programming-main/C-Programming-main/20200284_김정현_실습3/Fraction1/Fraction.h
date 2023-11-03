#pragma once

class Fraction {
public:
	Fraction(int numerator, int denominator);
	Fraction();
	void print();
	Fraction add(Fraction f);
	Fraction subtract(Fraction f);
	Fraction multiply(Fraction f);
	Fraction divide(Fraction f);
private:
	int numerator; // 분자
	int denominator; // 분모
	int smallerAbsNum(int num1, int num2);
	const static int DEFAULT_NUMERATOR = 0;
	const static int DEFAULT_DENOMINATOR = 1;
	void testFraction();
};