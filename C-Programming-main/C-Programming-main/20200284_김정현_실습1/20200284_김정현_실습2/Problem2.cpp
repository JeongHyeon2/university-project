#include<iostream>
using namespace std;

int getFactorial(int num);
int main() {

	int num;
	cout << "수를 입력하세요: ";
	cin >> num;
	cout << num << "! = " << getFactorial(num) << endl;


}

int getFactorial(int n) {

	//50!을 하면 값이 0이 나온다. int의 범위를 값이 초과했기 때문에 또는 표현범위를 넘어서. 
	// 배열을 이용하여 팩토리얼의 값을 하나하나 저장한다.

	int result = 1;
	for (int i = 1; i <= n; i++) {
		result *= i;
	}
	return result;
}