#include <iostream>

using namespace std;
int main() {
	

	while (1) {
		cout << "�ε��Ҽ��� ���� �Է��ϼ���(0���� ����): ";
		double num;
		cin >> num;

		if (!num)break;

		int integer = num;
		double decimal = num - integer;

		cout << "������: " << integer << " �Ҽ���: " << decimal<<endl;
	}
	return 0;
}