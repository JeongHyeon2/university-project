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
	if (!(day == monthArray[month - 1]) && !(day == 28 && month == 2)) { // ������ �ƴϰ� 2��28���� �ƴҶ�
		day++;
		return;
	}
	if (day == monthArray[month - 1]) {// �����϶�
		if (month == MAX_MONTH) { //12���϶�
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
		if (!isLeapYear(year)) { // ������ �ƴ� 2�� 28���϶�
			month++;
			day = 1;
		}
		else {	//�����϶�
			day++;
		}

	}

}

void Date::dayMinus()
{
	if (day != 1) { //���ʰ� �ƴҶ�
		day--;
	}
	else { // n�� 1�� �϶�
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
	dDayDate = printDate(); //dDayDate�� dDay��¥ ����
	//���� ��¥�� ����
	year = y;
	month = m;
	day = d;
}

string Date::printDate() const {
	string s;
	s = to_string(year) + "�� " + to_string(month) + "�� " + to_string(day) + "��";
	return s;
}

string Date::printDday() const
{
	string s;
	if (dDay == 0)
		s = "D-day ����";
	else if (dDay > 0)
		s = "D+" + to_string(dDay);
	else
		s = "D-" + to_string(abs(dDay));
	return s;
}

void Date::setDate(const string& date)
{
	if (date.length() != 8) {
		cout << "��¥�� 8�ڸ��� �Է����ּ���!" << endl;
		return;
	}
	int d = stoi(date.substr(6, 2)); //������ 2�ڸ��� day
	int m = stoi(date.substr(4, 2)); //�״��� 2�ڸ��� month
	int y = stoi(date.substr(0, 4)); //ó������ 4�ڸ��� year

	if (isCorrectDate(d, m, y)) {
		day = d;
		month = m;
		year = y;
		calculateDdayDate();
	}
	else {
		cout << "�߸��� ��¥�Դϴ�. �ٽ� �Է����ּ���!" << endl;
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
	outputStream << "[���� ��¥]" << date.printDate() << " [" << date.printDday() << "] " << date.dDayDate << endl;
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
			if (d == 29 && isLeapYear(y)) { // 29���̸鼭 �����̸� ��
				return true;
			}
			else if (d != 29) return true; // 29���� �ƴϾ �� �������� ����

		}

	}
	return false;
}

bool Date::isLeapYear(int year) const
{
	return((year % 4 == 0 && year % 100 != 0) || year % 400 == 0);
}
