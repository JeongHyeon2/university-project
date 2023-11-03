#include "Date.h"
#include <time.h>
#include <iostream>


using namespace std;
Date::Date() {
	time_t timer;
	struct tm t;

	timer = time(NULL);
	localtime_s(&t, &timer);

	year = 1900 + t.tm_year;
	month = 1 + t.tm_mon;
	day = t.tm_mday;
	dDay = 0;
}

void Date::dayPlus()
{
	if (!(day == monthArray[month - 1]) && !(day == 28 && month == 2)) { // 월말이 아니고 2월28일이 아닐때
		day++;
		return;
	}
	if (day == monthArray[month - 1]) {// 월말일때
		if (month == MAX_MONTH) { //12월일때
			year++;
			month = 1;
			day = 1;
		}
		else {
			month++;
			day = 1;
		}
		return;
	}
	if ((day == 28 && month == 2)) {  
		if (!isLeapYear(year)) { // 윤년이 아닌 2월 28일일때
			month++;
			day = 1;
		}
		else {	//윤년일때
			day++;
		}

	}

}

void Date::dayMinus()
{
	if (day != 1) { //월초가 아닐때
		day--;
	}
	else { // n월 1일 일때
		if (month == 3) {
			if (isLeapYear(year)) {
				month--;
				day = monthArray[month - 1];
			}
			else {
				day = 28;
				month--;
			}
		}
		else if (month == 1) {
			year--;
			month = MAX_MONTH;
			day = monthArray[month - 1];
		}
		else
		{
			month--;
			day = monthArray[month - 1];

		}
	}

}

void Date::calculateDdayDate()
{
	int y = year;
	int m = month;
	int d = day;

	if (dDay > 0) {
		for (int i = 0; i < abs(dDay) - 1; i++) {
			dayPlus();
		}
	}
	else if (dDay < 0) {
		for (int i = 0; i < abs(dDay); i++) {
			dayMinus();
		}
	}
	else {
		dDayDate = "";
		return;
	}
	dDayDate = printDate(); //dDayDate에 dDay날짜 저장
	//원래 날짜로 변경
	year = y;
	month = m;
	day = d;
}

string Date::printDate() const {
	string s;
	s = to_string(year) + "년 " + to_string(month) + "월 " + to_string(day) + "일";
	return s;
}

string Date::printDday() const
{
	string s;
	if (dDay == 0)
		s = "D-day 없음";
	else if (dDay > 0)
		s = "D+" + to_string(dDay);
	else
		s = "D-" + to_string(abs(dDay));
	return s;
}

void Date::setDate(const string& date)
{
	if (date.length() != 8) {
		cout << "날짜는 8자리로 입력해주세요!" << endl;
		return;
	}
	int d = stoi(date.substr(6, 2)); //끝에서 2자리가 day
	int m = stoi(date.substr(4, 2)); //그다음 2자리가 month
	int y = stoi(date.substr(0, 4)); //처음부터 4자리가 year

	if (isCorrectDate(d, m, y)) {
		day = d;
		month = m;
		year = y;
		calculateDdayDate();
	}
	else {
		cout << "잘못된 날짜입니다. 다시 입력해주세요!" << endl;
		return;
	}
}

void Date::setDday(const string& dday)
{
	dDay = stoi(dday);
	calculateDdayDate();
}

Date Date::operator++()
{
	dayPlus();
	calculateDdayDate();
	return *this;

}

Date Date::operator--()
{
	dayMinus();
	calculateDdayDate();
	return *this;
}

ostream& operator<<(ostream& outputStream, const Date& date)
{
	outputStream << "[현재 날짜]" << date.printDate() << " [" << date.printDday() << "] " << date.dDayDate << endl;
	return outputStream;
}

bool Date::isCorrectDate(int d, int m, int y) const
{
	if ((0 < m && m <= MAX_MONTH) && (0 < d && d <= monthArray[m - 1]) && y >= 0) {
		if (m != 2) {
			return true;
		}
		else
		{
			if (d == 29 && isLeapYear(y)) { // 29일이면서 윤년이면 참
				return true;
			}
			else if (d != 29) return true; // 29일이 아니어도 참 나머지는 거짓

		}

	}
	return false;
}

bool Date::isLeapYear(int year) const
{
	return((year % 4 == 0 && year % 100 != 0) || year % 400 == 0);
}
