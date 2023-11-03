package 과제4_3번;

import java.util.Scanner;

public class BSTManager {
	private Scanner sc = new Scanner(System.in);
	public BSTManager() {
		
	}

	public void run() {
		while (true) {
			BinarySearchTree bst = new BinarySearchTree();
			System.out.print("문자열들을 입력하세요 (종료:quit) : ");
			String s = sc.nextLine();
			if (s.equals("quit")) {
				System.out.println("종료합니다.");
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
