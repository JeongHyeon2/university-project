package week_2;

import java.util.Scanner;
public class MatrixCalculator {
	static Scanner sc = new Scanner(System.in);
	static Matrix m1 , m2;
	static boolean cont=true;
	public static void main(String[] args) throws menuexception, multiply_matrixexception {
		System.out.println("20200284 ������ ȯ��!");
	
		// ���� ����
		int sel_menu;
		do {
			// �޴� ǥ��
			display_menu();
			// �޴� ����
			sel_menu=selected_menu();
			
			try{
				handle_menu(sel_menu);
			}catch(menuexception e) {
				System.out.println(e);
			}
			}while(cont);
	}
	
	private static void display_menu() { // �޴� �����ֱ�
			System.out.println("--------------------");
			System.out.println("1.��� ����");
			System.out.println("2.��� ����");
			System.out.println("3.��� ����");
			System.out.println("4.��� ����");
			System.out.println("5.��� ����");
			System.out.println("--------------------");
	}
	
	private static int selected_menu() { // �޴� ����
			 Scanner sc = new Scanner(System.in);
			 System.out.print("���ϴ� �޴���?: ");
			 int m = sc.nextInt();
			 return m;
	}
	// �޴� ���ý� ����Ǵ� �޼ҵ�
	private static void handle_menu(int m) throws menuexception, multiply_matrixexception {
			switch(m) {
			case 1:try {
					make_matrix(); // ��� ����
				} catch (make_matrixexception e) {
					System.out.println(e);
				} break;
			case 2:try {
					add_matrix();// ��� ����
				} catch (add_matrixexception e) {
					System.out.println(e);
				}break;
			case 3:try {
					subtract_matrix(); // ��� ����
				} catch (subtractexception e) {
				    System.out.println(e);
				}break;
			case 4:try{ 
				multiply_matrix();break; // ��� ����
			}catch(multiply_matrixexception e) {
				System.out.println(e);
			} break;
			
			case 5: System.out.println("20200284 ������ 'Good Bye!'");cont = false; break;
			default:throw new menuexception("��ȿ���� ���� �޴�");
			}
		}
	
		private static void make_matrix() throws make_matrixexception {//��� ����
			int d1,d2;
			System.out.print("ù ��° ����� ũ���?: ");
			d1 = sc.nextInt();
			d2 = sc.nextInt();
			if(d1<0||d2<0) throw new make_matrixexception("��� ���� ����");
			m1 = new Matrix(d1,d2);
	
			System.out.print("�� ��° ����� ũ���?: ");
			d1 = sc.nextInt();
			d2 = sc.nextInt();
			if(d1<0||d2<0) throw new make_matrixexception("��� ���� ����");
			m2 = new Matrix(d1,d2);
			for(int i=0;i<m1.getcols();i++) {
				System.out.print((i+1)+"���� ������ �Է��� �ּ���.");
				for(int j=0;j<m1.getrows();j++) {
					int v= sc.nextInt();
					m1.setcell(i, j, v);
				}	
		}
        System.out.println("ù ��° ���");
		m1.print_matrix(); // ��� ���
		for(int i=0;i<m2.getcols();i++) {
			System.out.print((i+1)+"���� ������ �Է��� �ּ���.");
			for(int j=0;j<m2.getrows();j++) {
				int v= sc.nextInt();
				m2.setcell(i, j, v);
			}	
	}
		System.out.println("�� ��° ���");
	    m2.print_matrix();
		
		}

		private static void add_matrix() throws add_matrixexception {
			Matrix r = m1.add(m2);
			System.out.println("��� ���");
			r.print_matrix();	
		}
		private static void subtract_matrix() throws subtractexception {
			Matrix r = m1.subtract(m2);
			System.out.println("��� ���");
			r.print_matrix();	
		}
		private static void multiply_matrix() throws multiply_matrixexception {
			Matrix r;
		    r = m1.multiply(m2);
			System.out.println("��� ���");
			r.print_matrix();	
			}
}
	
class Matrix{
	private int [][] matrix;
	private int dim1,dim2;
	public Matrix(int rows, int cols) {
		matrix = new int [rows][cols];
		dim1=rows; dim2=cols;
	}
	public Matrix(int rows, int cols, int[][] data) {
		matrix = new int [rows] [cols];
		dim1=rows; dim2=cols;
		for(int r=0;r<dim1;r++) {
			for(int c=0;c<dim2;c++) {
				matrix[r][c]=data[r][c];
			}
		}
	}
	public Matrix add(Matrix other) throws add_matrixexception { // ��� ����
		if(this.dim1==other.dim1 && this.dim2==other.dim2) {
			Matrix rmatrix= new Matrix(this.dim1,this.dim2);
			for(int r=0;r<this.dim1;r++) {
				for(int c=0;c<this.dim2;c++) {
					rmatrix.matrix[r][c]=this.matrix[r][c] + other.matrix[r][c];
				}
			}
			return rmatrix;
			
		}
		else throw new add_matrixexception("��� ���� ����");
		
	}
	public Matrix subtract(Matrix other) throws subtractexception { // ��� ����
		if(this.dim1==other.dim1 && this.dim2==other.dim2) {
			Matrix rmatrix= new Matrix(this.dim1,this.dim2);
			for(int r=0;r<this.dim1;r++) {
				for(int c=0;c<this.dim2;c++) {
					rmatrix.matrix[r][c]=this.matrix[r][c] - other.matrix[r][c];
				}
			}
			return rmatrix;
			
		}
		else throw new subtractexception("��� ���� ����");
		
	}
     public Matrix multiply(Matrix other) throws multiply_matrixexception { // ��� ����
    	  if(this.dim2==other.dim1) {
    		  Matrix rmatrix = new Matrix(this.dim1,other.dim2);
    		  for(int r=0;r< rmatrix.dim1;r++) {
    			 for(int c=0;c< rmatrix.dim2;c++) {
    				 rmatrix.matrix[r][c] = 0;
    				 for(int k=0;k<this.dim2;k++) rmatrix.matrix[r][c]+=this.matrix[r][k]*other.matrix[k][c];
    				 }
    		  }
    		  return rmatrix;
    	  }
    	  else throw new multiply_matrixexception("��� ���� ����");
     }

 	public void print_matrix() { // ��� ���
 		for(int r=0;r<dim1;r++) {
   		 for(int c=0;c<dim2;c++) {
   			System.out.print(this.matrix[r][c]+" ");
   		 }
   		System.out.println();	
   	 }
 	}
 	// getter, setter
     public int getrows() { return dim1; }
     public int getcols() { return dim2;}
     public int getcell(int r,int c) {return matrix[r][c];}
     public void setcell(int r, int c, int v) {matrix[r][c]=v;}
 }
//Exception
	class menuexception extends Exception {
		public menuexception(String m) {
			super(m);
		}
	}
	class make_matrixexception extends Exception {
		public make_matrixexception(String m) {
			super(m);
		}
		public make_matrixexception() {
			super();
		}
	}
	class multiply_matrixexception extends Exception{
		public multiply_matrixexception(String m) {
			super(m);
		}
	public multiply_matrixexception() {
		super();
		}
	}
	class add_matrixexception extends Exception{
	public add_matrixexception(String m) {
		super(m);
	    }
	public add_matrixexception() {
		super();
		}
	}
	class subtractexception extends Exception{
	public subtractexception(String m) {
		super(m);
	    }
	public subtractexception() {
		super();
		}
	}




