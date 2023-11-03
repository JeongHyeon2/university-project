#include<iostream>
using namespace std;

void printArr(int *arr, int size) {
	for (int i = 0; i < size; i++) cout << arr[i] << " ";
	cout << endl;
}
int main() {
	const int MAX = 5;

	int arr1[MAX] = { 1,2,3,4,5 }, arr2[MAX], * p;

	//	arr2 = arr1; // arr1의 내용을 arr2에 복사
	for (int i = 0; i < MAX; i++) {
		arr2[i] = arr1[i];
	}

	//	p = arr1;  // arr1의 내용을 p로 표현되는 동적배열에 복사
	p = new int[MAX];
	for (int i = 0; i < MAX; i++) {
		p[i] = arr1[i];
	}


	p[0] = 99;

	printArr(arr1, MAX); printArr(arr2, MAX); printArr(p, MAX);
	delete[] p;
	p = NULL;

	return 0;
}
