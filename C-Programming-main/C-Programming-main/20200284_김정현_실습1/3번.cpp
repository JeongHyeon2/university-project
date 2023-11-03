#include <iostream>

using namespace std;
int main() {
	cout << "크기(3보다 큰 홀수): ";
	int num;
	cin >> num;
	
	for (int i = 0; i < num/2+1; i++) {
		for (int j = num/2; j > i; j--) {
			cout<<" ";
		}

		for (int j = 0; j < 2 * i + 1; j++) {
			cout << "*";
		}
		printf("\n");
	}

	for (int i = 1; i <= num/2; i++) {
		for (int j = 0; j < i; j++) {
			cout << " ";
		}

		for (int j = num; j > 2 * i; j--) {
			cout << "*";
		}
		printf("\n");
	}

	return 0;
}