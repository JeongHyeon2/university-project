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
		cout << "날짜 이동은 년월일 또는 +(다음날) 또는 -(전날), D-day 계산은 +/-날짜, 종료는 Q : ";
		string s;
		cin >> s;
		if (s._Equal("Q") || s._Equal("q")) {
			cout << "종료합니다.";
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
			cout << "잘못된 입력입니다." << endl;
		}

	}
}