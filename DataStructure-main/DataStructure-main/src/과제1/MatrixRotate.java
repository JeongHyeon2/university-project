package 과제1;

public class MatrixRotate {
	static int size = 4; //행렬 사이즈
	static Matrix m = new Matrix(size); // 최초 행렬
	static Matrix m_right = new Matrix(size); //오른쪽 90도 행렬
	static Matrix m_left = new Matrix(size);// 왼쪽 90도 행렬
	static Matrix m_transposition = new Matrix(size); // 전치 행렬
	
	public static void main(String[] args) {
		m.make_random();	// 랜덤 행렬 생성	
		m_right.right(m);   // 오른쪽으로 90도 회전
		m_left.left(m);		// 왼쪽으로 90도 회전
		m_transposition.transposition(m, size, size); // 행렬 전치
		print_first_line(); //첫째 라인 출력 
		
	for(int i=0;i<m.getSize();i++) {  // 각 행의 열을 한줄씩 출력 . 행 크기만큼 반복
			print_partition();
			m.print_line(i); print_partition();
			m_right.print_line(i); print_partition();
			m_left.print_line(i); print_partition();
			m_transposition.print_line(i); print_partition();
			System.out.println();
		}
	}
	// "|" 출력
	static void print_partition() {
		System.out.print("| ");
	}
	// 첫번째 줄 출력
	static void print_first_line() { 
		for(int i=0;i<2*size;i++) System.out.print(" ");
		System.out.print("최초행렬");
		for(int i=0;i<4*size;i++) System.out.print(" "); // 숫자 하나당 4개의 공백 출력 후 문자 출력
		System.out.print("우측으로 90도 회전");
		for(int i=0;i<4*size;i++) System.out.print(" ");
		System.out.print("좌측으로 90도 회전");
		for(int i=0;i<4*size;i++) System.out.print(" ");
		System.out.print("전치행렬");
		System.out.println();
	}
}
