#include <iostream>

using namespace std;
int main() {
	int dan, number, numLine;

	cout << "몇단까지 출력할까요?: ";
	cin >> dan;
	cout << "몇까지 출력할까요?: ";
	cin >> number;
	cout << "한줄에 몇단씩 출력할까요?: ";
	cin >> numLine;
	for (int i = 0; i <= dan / numLine; i++) {
		for (int j = 0; j < number; j++) {
			for (int k = 0; k < numLine; k++) {
				cout << (numLine * i + 1 + k) << " x " << j + 1 << " = " << (numLine * i + 1 + k) * (j + 1) << "\t";
				if ((numLine * i + 1 + k) >= dan) break;
			}
			cout << "\n";
		}
		cout << "\n";
	}
}