#include <iostream>

using namespace std;
int main() {
	while (1) {
		cout << "���� ���� �Է��ϼ���(0���� ����): ";
		int num;
		cin >> num;

		if (!num)break;
		
		while (1) {
			num = num / 10;

			if (num < 10 && num > -10) break;
		}
		cout << "���� ū �ڸ���: " << num << endl;
		
	}
	return 0;

}