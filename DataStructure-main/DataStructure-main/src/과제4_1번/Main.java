package ����4_1��;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("��� ������ �Է��ϼ���: ");
		CompleteBinaryTree cbt = new CompleteBinaryTree(sc.nextInt());
		
		cbt.printTree();
		System.out.print("preorder : ");
		cbt.preorder();
		
		System.out.print("inorder : ");
		cbt.inorder();
		
		System.out.print("postorder : ");
		cbt.postorder();	
		
		System.out.print("levelorder : ");
		cbt.levelorder();
	
		
	}
}
