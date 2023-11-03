#include <iostream>

using namespace std;
int main() {
	cout << "ют╥б: ";
	int number;
	cin >> number;
	for (int i = 1; i <= number; i++) {

		int sum=0;

		for (int j = 1; j < i; j++) {
			if (i % j == 0) sum += j;
		}

		if (sum == i) {
			cout << i << "(";
			for (int j = 1; j < i; j++) {
				if (i % j == 0) {
					if (i/2 <= j) cout << j;
					else cout << j << "+";
				}
			}
			cout <<")"<<endl;

		}
	}

}