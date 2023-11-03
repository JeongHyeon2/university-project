#include<iostream>
using namespace std;

int main() {
	const int MAX_SIZE = 100;
	while (1) {
		cout << "10진수를 입력하세요(종료는 0): ";
		int number;
		cin >> number;
		if (number == 0) break;
		int last = 0;
		int numArr[MAX_SIZE];
		while (1) {
			if (number == 0) break;
			numArr[last] = number % 2;
			number /= 2;
			last++;
		}
		for (int i = last - 1, cnt = 0; cnt < last % 4; cnt++) {
			cout << numArr[i];
		}
		cout << " ";
		for (int i = last - 1 - last % 4, cnt = 0; i >= 0; i--, cnt++) {
			if (cnt > 0 && cnt % 4 == 0) cout << " " << numArr[i];
			else cout << numArr[i];
		}
		cout << endl;
	}
}