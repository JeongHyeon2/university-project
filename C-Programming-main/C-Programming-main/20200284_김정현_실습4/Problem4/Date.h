#pragma once
#include <string>

using namespace std;

class Date {
public:
	Date();
	void setDate(const string& date);
	void setDday(const string& dDay);
	Date operator++();
	Date operator--();
	friend ostream& operator <<(ostream& outputStream, const Date& date);
private:
	int year,month, day;
	int dDay=0;
	string dDayDate; //D-day 날짜를 저장
	bool isCorrectDate(int day, int month, int year) const;
	bool isLeapYear(int year) const;
	static const int MAX_MONTH = 12;
	const int monthArray[MAX_MONTH] = { 31,29,31,30,31,30,31,31,30,31,30,31 };
	void dayPlus();	
	void dayMinus();
	void calculateDdayDate(); //D-day 날짜 계산
	string printDate() const;
	string printDday() const;
};

