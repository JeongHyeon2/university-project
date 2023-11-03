package assignment_2;

import java.util.Scanner;

public class Matrix_Calculator {
	static Scanner sc = new Scanner(System.in);
	static Matrix m1,m2;
	public static void main(String[] args) throws Matrix_Exception{
		int menu; //�޴�
		boolean cont = true;
		while(cont) {
		System.out.println("---��İ���---\n1.����\n2.����\n3.����\n4.��ġ\n-------------");		
		menu = sc.nextInt();
		switch(menu) {
		case 1:Make_Matrix_2(); Add();break;
		case 2: Make_Matrix_2();Substract(); break;
		case 3: Make_Matrix_2();Multiply(); break;
		case 4: Make_Matrix_1() ;Transposition(); break;
		default:System.out.println("�߸��� �Է��Դϴ�.");
			}
		System.out.print("��� ���� y/n :");
		char c = sc.next().charAt(0);
		if(c=='y'||c=='Y') cont =true;
		else {System.out.println("���α׷� ����");cont =false;}
		}
	}
	private static void Make_Matrix_2() throws Matrix_Exception{ //��� ����(2��)
		int r,c;
		System.out.println("ù �� ° ����� ũ�⸦ �Է��� �ּ��� : ");
		r=sc.nextInt(); c=sc.nextInt();
		m1=new Matrix(r,c);
		m1.make_Matrix();
		m1.printMatrix();
		
		System.out.print("�� �� ° ����� ũ�⸦ �Է��� �ּ��� :");
		r = sc.nextInt(); c = sc.nextInt();
		m2=new Matrix(r,c);
		m2.make_Matrix();
		m2.printMatrix();
	}
	private static void Make_Matrix_1() throws Matrix_Exception{ //��� ���� (1��)
		int r,c;
		System.out.println("����� ũ�⸦ �Է��� �ּ��� : ");
		r=sc.nextInt(); c=sc.nextInt();
		m1=new Matrix(r,c);
		m1.make_Matrix();
		m1.printMatrix();
	}
	private static void Add() throws Matrix_Exception { //���ϱ�
		Matrix m = m1.add_Matrix(m2);
		System.out.println("��� ���");
		m.printMatrix();
	}
	private static void Substract() throws Matrix_Exception {//����
		Matrix m = m1.Substract_Matrix(m2);
		System.out.println("��� ���");
		m.printMatrix();
	}
	private static void Multiply() throws Matrix_Exception {//���ϱ�
		Matrix m = m1.Multiply_Matrix(m2);
		System.out.println("��� ���");
		m.printMatrix();
	}
	private static void Transposition() {//��ġ
		Matrix m = m1.Transposition_Matrix(m1);
		System.out.println("��� ���");
		m.printMatrix();
	}
}
 class Matrix {
	private int col,row; //��,��
	private int matrix_array[][]; // ��� �迭
	public Matrix(int col, int row) {
		this.col=col; this.row=row;
		matrix_array= new int [col][row];
	}
	//��� ���
	public void printMatrix() {
		for(int i=0;i<col;i++) {
		for(int j=0;j<row;j++) {
			System.out.print(matrix_array[i][j]+" ");
				}System.out.println();
		}
	}
	//��� ����
	public void make_Matrix() throws Matrix_Exception {
		Scanner sc=new Scanner(System.in);
		int check;
		for(int i=0;i<col;i++) {
			check=0;
			System.out.print((i+1)+"���� ������ �Է��� �ּ��� : ");
			for(int j=0;j<row;j++) {
				int n= sc.nextInt(); check++;
				setMatrix(i, j, n);
			}	if(check!=row) throw new Matrix_Exception("��� ���� ����");

		}
	}
	//��� ���ϱ�
	public Matrix add_Matrix(Matrix m) throws Matrix_Exception {
		Matrix result = new Matrix(col,row);
		if(col==m.col&&row==m.row) {
		for(int i=0;i<col;i++) {
			for(int j=0;j<row;j++) {
				result.matrix_array[i][j]=matrix_array[i][j]+m.matrix_array[i][j];
			}
		}
		return result;
		}else throw new Matrix_Exception("��� ����!");
	}
	//��� ����
	public Matrix Substract_Matrix(Matrix m) throws Matrix_Exception {
		Matrix result = new Matrix(col,row);
		if(col==m.col&&row==m.row) {
			for(int i=0;i<col;i++) {
				for(int j=0;j<row;j++) {
					result.matrix_array[i][j]=matrix_array[i][j]-m.matrix_array[i][j];
				}
			}
			return result;
			}else throw new Matrix_Exception("��� ����!");
	}
	//��� ���ϱ�
	public Matrix Multiply_Matrix(Matrix m) throws Matrix_Exception {
			Matrix result = new Matrix(col,m.row);
			if(row==m.col) {
				for(int i=0;i< result.col;i++) {
	    			 for(int j=0;j< result.row;j++) {
	    				 result.matrix_array[i][j] = 0;
	    				 for(int k=0;k<row;k++) result.matrix_array[i][j]+=matrix_array[i][k]*m.matrix_array[k][j];
	    				 }
	    		  }
	    		  return result;
			}else throw new Matrix_Exception("��� ����!");
 }
	//��� ��ġ
	public Matrix Transposition_Matrix(Matrix m) {
		Matrix result = new Matrix(row,col);
		for(int i=0;i<col;i++) {
			for(int j=0;j<row;j++) {
				result.matrix_array[j][i]=matrix_array[i][j];
			}
		}
		return result;
	}
	
	//��� ���� ��ü
	public void setMatrix(int col,int row,int num) {
		matrix_array[col][row]=num;
	}
	//getter,setter
	public int getCol() {
		return col;
	}
	public void setCol(int col) {
		this.col=col;
	}
	public int getRow() {
		return row;
	}
	public void setRow(int row) {
		this.row=row;
		}
	}
   class Matrix_Exception extends Exception {
		public Matrix_Exception(String m) {
			System.out.println("��� ����!");
		}
}

