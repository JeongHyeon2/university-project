package ����4_3��;

import java.util.Scanner;

public class BSTManager {
	private Scanner sc = new Scanner(System.in);
	public BSTManager() {
		
	}

	public void run() {
		while (true) {
			BinarySearchTree bst = new BinarySearchTree();
			System.out.print("���ڿ����� �Է��ϼ��� (����:quit) : ");
			String s = sc.nextLine();
			if (s.equals("quit")) {
				System.out.println("�����մϴ�.");
				return;
			}
			String[] arr = s.split(" ");
			for (int i = 0; i < arr.length; i++) {
				bst.put(arr[i]);
			}
			bst.printTree();

		}
	}

}
