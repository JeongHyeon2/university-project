#include<iostream>
using namespace std;

int main() {
	cout << "나는 100이하의 자연수 하나를 생각하고 있습니다."<<endl;
	srand(time(0));
	int answer = rand() % 100 + 1;
	int num,count=0;

	while (1) {

		cout << "숫자를 짐작해서 입력하세요: ";
		cin >> num;
		count++;
		if (num > answer) cout << "내가 생각하고 있는 숫자는 " << num << " 보다는 작은 숫자입니다." << endl;
		else if (num < answer)cout << "내가 생각하고 있는 숫자는 " << num << " 보다는 큰 숫자입니다." << endl;
		else {
			cout << "맞췄습니다!!!" << count << "번 만에 성공하셨네요!";
			break;
		}
	}
	return 0;
}