#include<iostream>
using namespace std;

int getFactorial(int num);
int main() {

	int num;
	cout << "���� �Է��ϼ���: ";
	cin >> num;
	cout << num << "! = " << getFactorial(num) << endl;


}

int getFactorial(int n) {

	//50!�� �ϸ� ���� 0�� ���´�. int�� ������ ���� �ʰ��߱� ������ �Ǵ� ǥ�������� �Ѿ. 
	// �迭�� �̿��Ͽ� ���丮���� ���� �ϳ��ϳ� �����Ѵ�.

	int result = 1;
	for (int i = 1; i <= n; i++) {
		result *= i;
	}
	return result;
}