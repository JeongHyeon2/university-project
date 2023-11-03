package ����1;

import java.util.Random;

public class Matrix {
	private int[][] matrix_array; // ��� �迭
	private int size; // ��,�� ũ�� (�������)

	public Matrix(int n) {
		this.size = n;
		matrix_array = new int[size][size];
	}

	// �������� ���� ����
	public void make_random() {
		Random rnd = new Random();
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++)
				this.matrix_array[i][j] = (rnd.nextInt(999) + 1);
		}
	}

	// �������� 90�� ȸ��
	public void left(Matrix m) {
		for (int r = 0; r < size; r++) {
			for (int c = 0; c < size; c++) {
				this.matrix_array[r][c] = m.matrix_array[c][size - 1 - r];
			}
		}
	}

	// ���������� 90�� ȸ��
	public void right(Matrix m) {
		for (int r = 0; r < size; r++) {
			for (int c = 0; c < size; c++) {
				this.matrix_array[r][c] = m.matrix_array[size - 1 - c][r];
			}
		}
	}

	// ��ġ
	public void transposition(Matrix m, int row, int col) {
		if (row - 1 >= 0 && col - 1 >= 0) { // �ε����� 0 �̻� �϶��� ����
			this.matrix_array[row - 1][col - 1] = m.matrix_array[col - 1][row - 1]; // ��� ��ġ
			if (col - 1 > 0)
				this.transposition(m, row, col - 1); // 1���� ���� (���)
			else if (col - 1 == 0)
				this.transposition(m, row - 1, size); // 1�྿ ���� (���)
		}
	}

	// ��� �� �྿ ���
	public void print_line(int r) {
		String s;
		for (int i = 0; i < size; i++) {
			if (matrix_array[r][i] < 100 && matrix_array[r][i] > 10)
				s = matrix_array[r][i] + "  "; // ���ڸ� ���̸� ���� 2ĭ
			else if (matrix_array[r][i] < 10)
				s = matrix_array[r][i] + "   "; // ���ڸ� ���̸� ���� 3ĭ
			else
				s = matrix_array[r][i] + " "; // ���ڸ� ���̸� ����1ĭ
			System.out.print(s);
		}
	}

	// getter,setter
	public int getSize() {
		return size;
	}

	public void setSize(int s) {
		size = s;
	}

}