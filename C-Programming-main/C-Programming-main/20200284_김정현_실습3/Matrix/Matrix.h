#pragma once
class Matrix {
public:
	Matrix();
	void print() const;
	Matrix add(Matrix m) const;
	Matrix multi(Matrix m) const;
	
private:
	static const int MATRIX_SIZE=3;
	int arr[MATRIX_SIZE][MATRIX_SIZE];
};

