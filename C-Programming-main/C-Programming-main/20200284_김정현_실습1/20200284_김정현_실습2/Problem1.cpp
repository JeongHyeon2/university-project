#include<iostream>
using namespace std;

int main() {
	cout << "���� 100������ �ڿ��� �ϳ��� �����ϰ� �ֽ��ϴ�."<<endl;
	srand(time(0));
	int answer = rand() % 100 + 1;
	int num,count=0;

	while (1) {

		cout << "���ڸ� �����ؼ� �Է��ϼ���: ";
		cin >> num;
		count++;
		if (num > answer) cout << "���� �����ϰ� �ִ� ���ڴ� " << num << " ���ٴ� ���� �����Դϴ�." << endl;
		else if (num < answer)cout << "���� �����ϰ� �ִ� ���ڴ� " << num << " ���ٴ� ū �����Դϴ�." << endl;
		else {
			cout << "������ϴ�!!!" << count << "�� ���� �����ϼ̳׿�!";
			break;
		}
	}
	return 0;
}