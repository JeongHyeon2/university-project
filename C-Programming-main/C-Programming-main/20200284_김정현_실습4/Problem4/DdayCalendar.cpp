#include"DdayCalendar.h"
#include <iostream>
#include "Date.h"

DdayCalendar::DdayCalendar() {

}
void DdayCalendar::run() {
	Date date;
	bool cnt = true;
	while (cnt) {
		cout << date;
		cout << "��¥ �̵��� ����� �Ǵ� +(������) �Ǵ� -(����), D-day ����� +/-��¥, ����� Q : ";
		string s;
		cin >> s;
		if (s._Equal("Q") || s._Equal("q")) {
			cout << "�����մϴ�.";
			cnt = false;
		}
		else if (s._Equal("+")) {
			++date;
		}
		else if (s._Equal("-")) {
			--date;
		}
		else if (s[0] == '+' || s[0] == '-') {
			date.setDday(s);
		}
		else if (s.length() == 8) {
			date.setDate(s);
		}
		else {
			cout << "�߸��� �Է��Դϴ�." << endl;
		}

	}
}