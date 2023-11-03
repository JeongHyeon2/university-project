package 과제1;

import java.util.Random;

public class Matrix {
	private int[][] matrix_array; // 행렬 배열
	private int size; // 행,열 크기 (정방행렬)

	public Matrix(int n) {
		this.size = n;
		matrix_array = new int[size][size];
	}

	// 랜덤으로 성분 결정
	public void make_random() {
		Random rnd = new Random();
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++)
				this.matrix_array[i][j] = (rnd.nextInt(999) + 1);
		}
	}

	// 왼쪽으로 90도 회전
	public void left(Matrix m) {
		for (int r = 0; r < size; r++) {
			for (int c = 0; c < size; c++) {
				this.matrix_array[r][c] = m.matrix_array[c][size - 1 - r];
			}
		}
	}

	// 오른쪽으로 90도 회전
	public void right(Matrix m) {
		for (int r = 0; r < size; r++) {
			for (int c = 0; c < size; c++) {
				this.matrix_array[r][c] = m.matrix_array[size - 1 - c][r];
			}
		}
	}

	// 전치
	public void transposition(Matrix m, int row, int col) {
		if (row - 1 >= 0 && col - 1 >= 0) { // 인덱스가 0 이상 일때만 실행
			this.matrix_array[row - 1][col - 1] = m.matrix_array[col - 1][row - 1]; // 행렬 전치
			if (col - 1 > 0)
				this.transposition(m, row, col - 1); // 1열씩 감소 (재귀)
			else if (col - 1 == 0)
				this.transposition(m, row - 1, size); // 1행씩 감소 (재귀)
		}
	}

	// 행렬 한 행씩 출력
	public void print_line(int r) {
		String s;
		for (int i = 0; i < size; i++) {
			if (matrix_array[r][i] < 100 && matrix_array[r][i] > 10)
				s = matrix_array[r][i] + "  "; // 두자리 수이면 공백 2칸
			else if (matrix_array[r][i] < 10)
				s = matrix_array[r][i] + "   "; // 한자리 수이면 공백 3칸
			else
				s = matrix_array[r][i] + " "; // 세자리 수이면 공백1칸
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