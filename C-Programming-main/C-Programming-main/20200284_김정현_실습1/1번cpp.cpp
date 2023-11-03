#include <iostream>

using namespace std;
int main() {
	

	while (1) {
		cout << "부동소수점 값을 입력하세요(0에서 종료): ";
		double num;
		cin >> num;

		if (!num)break;

		int integer = num;
		double decimal = num - integer;

		cout << "정수부: " << integer << " 소수부: " << decimal<<endl;
	}
	return 0;
}