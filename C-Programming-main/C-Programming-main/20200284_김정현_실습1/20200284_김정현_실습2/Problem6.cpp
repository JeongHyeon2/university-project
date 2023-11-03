#include<iostream>
using namespace std;

const int MATRIX_SIZE = 3;
void matrixAdd(int arr1[][MATRIX_SIZE], int arr2[][MATRIX_SIZE], int addArr[][MATRIX_SIZE]);
void matrixMulti(int arr1[][MATRIX_SIZE], int arr2[][MATRIX_SIZE], int multiArr[][MATRIX_SIZE]);
void print(int arr[][MATRIX_SIZE]);
void resetArr(int arr[][MATRIX_SIZE]);

int main() {
	int arr1[MATRIX_SIZE][MATRIX_SIZE] = { {1,2,3},{4,5,6},{7,8,9} };
	int arr2[MATRIX_SIZE][MATRIX_SIZE] = { {1,-1,0 }, { 0,-1,1 }, { -1,1,0} };
	int resultArr[MATRIX_SIZE][MATRIX_SIZE] = {};
	cout << "Ã¹¹øÂ° Çà·Ä"<<endl;
	print(arr1);
	cout << "µÎ¹øÂ° Çà·Ä" << endl;
	print(arr2);

	cout << "µ¡¼À Çà·Ä" << endl;

	matrixAdd(arr1, arr2, resultArr);
	print(resultArr);

	cout << "°ö¼À Çà·Ä" << endl;

	matrixMulti(arr1, arr2, resultArr);
	print(resultArr);

	return 0;
}
void matrixAdd(int arr1[][MATRIX_SIZE], int arr2[][MATRIX_SIZE], int addArr[][MATRIX_SIZE]) {
	resetArr(addArr);
	for (int i = 0; i < MATRIX_SIZE; i++) {
		for (int j = 0; j < MATRIX_SIZE; j++) {
			addArr[i][j] = arr1[i][j] + arr2[i][j];
		}
	}
}
void matrixMulti(int arr1[][MATRIX_SIZE], int arr2[][MATRIX_SIZE], int multiArr[][MATRIX_SIZE]) {
	resetArr(multiArr);
	for (int i = 0; i < MATRIX_SIZE; i++) {
		for (int j = 0; j < MATRIX_SIZE; j++) {
			for (int k = 0; k < MATRIX_SIZE; k++) {
				multiArr[i][j] += arr1[i][k] * arr2[k][j];
			}
		}
	}
}
void resetArr(int arr [][MATRIX_SIZE]) {
	for (int i = 0; i < MATRIX_SIZE; i++) {
		for (int j = 0; j < MATRIX_SIZE; j++) {
			arr[i][j] = 0;
		}
	}
}
void print(int arr[][MATRIX_SIZE]) {
	
	for (int i = 0; i < MATRIX_SIZE; i++){
		cout << "l";
		for (int j = 0; j < MATRIX_SIZE; j++) {
			printf("%4d ", arr[i][j]);

		}
		cout << "  l"<<endl;
	}
	
}