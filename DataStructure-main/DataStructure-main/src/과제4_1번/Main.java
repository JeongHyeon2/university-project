package 과제4_1번;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("노드 개수를 입력하세요: ");
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
