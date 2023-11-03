#include "Matrix.h"
#include <stdlib.h>
#include <iostream>
using namespace std;

Matrix::Matrix() {

	srand(time(0));
	for (int i = 0; i < MATRIX_SIZE; i++) {
		for (int j = 0; j < MATRIX_SIZE; j++) {
			arr[i][j] = rand() % 20 - 10;
		}
	}
}

void Matrix::print() const {
	for (int i = 0; i < MATRIX_SIZE; i++) {
		cout << "l";
		for (int j = 0; j < MATRIX_SIZE; j++) {
			printf("%4d ", arr[i][j]);

		}
		cout << "  l" << endl;
	}
	cout << "\n";
}

Matrix Matrix::add(Matrix m) const {
	Matrix result;
	for (int i = 0; i < MATRIX_SIZE; i++) {
		for (int j = 0; j < MATRIX_SIZE; j++) {
			result.arr[i][j] = arr[i][j] + m.arr[i][j];
		}
	}
	return result;
}

Matrix Matrix::multi(Matrix m) const {
	Matrix result;
	for (int i = 0; i < MATRIX_SIZE; i++) {
		for (int j = 0; j < MATRIX_SIZE; j++) {
			for (int k = 0; k < MATRIX_SIZE; k++) {
				result.arr[i][j] += arr[i][k] * m.arr[k][j];
			}
		}
	}
	return result;
}