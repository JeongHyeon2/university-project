#include <iostream>

using namespace std;
int main() {
	while (1) {
		cout << "정수 값을 입력하세요(0에서 종료): ";
		int num;
		cin >> num;

		if (!num)break;
		
		while (1) {
			num = num / 10;

			if (num < 10 && num > -10) break;
		}
		cout << "제일 큰 자리수: " << num << endl;
		
	}
	return 0;

}