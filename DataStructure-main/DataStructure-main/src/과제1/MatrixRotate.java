package ����1;

public class MatrixRotate {
	static int size = 4; //��� ������
	static Matrix m = new Matrix(size); // ���� ���
	static Matrix m_right = new Matrix(size); //������ 90�� ���
	static Matrix m_left = new Matrix(size);// ���� 90�� ���
	static Matrix m_transposition = new Matrix(size); // ��ġ ���
	
	public static void main(String[] args) {
		m.make_random();	// ���� ��� ����	
		m_right.right(m);   // ���������� 90�� ȸ��
		m_left.left(m);		// �������� 90�� ȸ��
		m_transposition.transposition(m, size, size); // ��� ��ġ
		print_first_line(); //ù° ���� ��� 
		
	for(int i=0;i<m.getSize();i++) {  // �� ���� ���� ���پ� ��� . �� ũ�⸸ŭ �ݺ�
			print_partition();
			m.print_line(i); print_partition();
			m_right.print_line(i); print_partition();
			m_left.print_line(i); print_partition();
			m_transposition.print_line(i); print_partition();
			System.out.println();
		}
	}
	// "|" ���
	static void print_partition() {
		System.out.print("| ");
	}
	// ù��° �� ���
	static void print_first_line() { 
		for(int i=0;i<2*size;i++) System.out.print(" ");
		System.out.print("�������");
		for(int i=0;i<4*size;i++) System.out.print(" "); // ���� �ϳ��� 4���� ���� ��� �� ���� ���
		System.out.print("�������� 90�� ȸ��");
		for(int i=0;i<4*size;i++) System.out.print(" ");
		System.out.print("�������� 90�� ȸ��");
		for(int i=0;i<4*size;i++) System.out.print(" ");
		System.out.print("��ġ���");
		System.out.println();
	}
}
