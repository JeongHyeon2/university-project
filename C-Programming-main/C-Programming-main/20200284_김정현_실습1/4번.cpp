#include <iostream>

using namespace std;
int main() {
	int dan, number, numLine;

	cout << "��ܱ��� ����ұ��?: ";
	cin >> dan;
	cout << "����� ����ұ��?: ";
	cin >> number;
	cout << "���ٿ� ��ܾ� ����ұ��?: ";
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