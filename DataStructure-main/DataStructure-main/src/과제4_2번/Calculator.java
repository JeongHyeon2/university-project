package ����4_2��;

import java.util.Scanner;

public class Calculator {
	private LinkedListQueue<Token> infix = new LinkedListQueue<Token>(); // ����ǥ��
	private LinkedListQueue<Token> postfix = new LinkedListQueue<Token>(); // ����ǥ��
	private ArrayStack<Token> stack = new ArrayStack<Token>(); //token ����
	private ArrayStack<TreeNode> btstack = new ArrayStack<TreeNode>(); // ��� ����
	private String postfixNotation = "";
	private BinaryTree bt = new BinaryTree();

	public static void main(String[] args) {
		for(int i=0;i<80;i++)
		System.out.print("-"+" ");
	}
	public Calculator() throws Exception {
		stack.push(new Token("#"));
	}
	public void run() {
		Scanner sc = new Scanner(System.in);
		

		while (true) {
			System.out.print("���� �Է��ϼ���(����� quit): ");
			String s = sc.nextLine();

			if (s.equals("quit")) {
				System.out.println("�����մϴ�.");
				return;
			}
			try {
				Calculator c = new Calculator();

				c.calculate(s);
				System.out.println("postfixNotation : " + c.getPostfixNotation());
				
				System.out.print("prefix notation : ");
				c.preorder();

				c.printTree();
				System.out.println("��: " + c.getAnswer());
			} catch (Exception e) {
				System.out.println("[Error] ���� ����");
			} // �� �Է½� ���
		}
	}

	public String getAnswer() {
		return bt.getRoot().getValue() + "";
	}

	public void preorder() {
		bt.preorder();
	}

	public void printTree() {
		bt.printTree();
	}

	public String getPostfixNotation() {
		return postfixNotation;
	}

	public void calculate(String s) throws Exception {
		stringToQueue(s);
		infixToPostfix();
		postfixToTree();
		bt.setRoot(btstack.pop());
	}

	// String�� Token��ȯ �� queue�� ����
	private void stringToQueue(String s) throws Exception {
		String sArray[] = s.trim().split(" "); // �յ� ���� ���� �� �迭�� ����
		for (int i = 0; i < sArray.length; i++) {
			if (sArray[i].equals("-")) {
				if (i == 0)
					infix.enqueue(new Token("m")); // ���׿����� -�� ���
				else {
					if (isOperator(sArray[i - 1]))
						infix.enqueue(new Token("m"));// ���׿����� -�� ���
					else
						infix.enqueue(new Token(sArray[i])); // ���׿����� -�� ���
				}
			} else
				infix.enqueue(new Token(sArray[i]));
		}
	}

	// ����ǥ�⸦ ����ǥ��� ��ȯ
	private void infixToPostfix() throws Exception {
		int count = infix.size();

		for (int i = 0; i < count; i++) {
			tokenInsertToStack(infix.dequeue());
		}
		while (!stack.isEmpty()) {
			Token tmp = stack.pop();
			if (tmp.getICP() == 0)
				throw new Exception("���� ����!"); // ���ÿ� ��ȣ�� ���������� ����
			if (!tmp.getValue().equals("#"))
				postfix.enqueue(tmp);
		}
	
	}

	// queue�� token�� stack�� �ֱ�
	private void tokenInsertToStack(Token t) throws Exception {

		if (t.isOperator()) // �����ڸ� stack�� �ֱ�
		{
			if (t.getValue().equals(")")) // �ݴ� ��ȣ�϶�
			{
				int size=stack.getTop()+1;
				for(int i=0;i<size;i++) {
					Token token = stack.pop();
					if (token.getValue().equals("("))
						break;
					postfix.enqueue(token);
				}

			} else { // �ݴ� ��ȣ�� �ƴ� �������϶�
				if (stack.top().getISP() == 1) { // ���� �������϶�
					while (stack.top().getISP() < t.getICP()) {
						Token top = stack.pop();
						postfix.enqueue(top);

					}
				} else { // �ƴҶ�
					while (stack.top().getISP() <= t.getICP()) {
						Token top = stack.pop();
						postfix.enqueue(top);

					}
				}
				stack.push(t);

			}
		} else { // ���ڸ� ���
			postfix.enqueue(t);
		}

	}

	// postfix�� tree�� ��ȯ
	private void postfixToTree() throws Exception {
		int size = postfix.size();
		for (int i = 0; i < size; i++) {
			Token t = postfix.dequeue();
			postfixNotation += t.getValue() + " ";

			if (isOperator(t.getValue())) { // �������϶�

				if (t.getValue().equals("m") || t.getValue().equals("~")) { // ���� �������϶�
					TreeNode pop = btstack.pop();
					TreeNode node = new TreeNode(t, pop, null);
					node.setValue(cal(pop.getValue(), t.getValue()));
					btstack.push(node);
				} else {// ���� �������϶�
					TreeNode pop1 = btstack.pop();
					TreeNode pop2 = btstack.pop();

					TreeNode node = new TreeNode(t, pop2, pop1);
					node.setValue(cal(pop2.getValue(), pop1.getValue(), t.getValue()));
					btstack.push(node);
				}

			} else { // �ǿ������϶�
				TreeNode node = new TreeNode(t, null, null);
				node.setValue(Integer.parseInt(t.getValue()));
				btstack.push(node);
			}
		}

	}

	private int cal(int num1, int num2, String s) throws Exception {
		switch (s) {
		case "*":
			return num1 * num2;
		case "/":
			return num1 / num2;
		case "%":
			return num1 % num2;
		case "+":
			return num1 + num2;
		case "-":
			return num1 - num2;
		case "<<":
			return num1 << num2;
		case ">>":
			return num1 >> num2;
		case "&":
			return num1 & num2;
		case "^":
			return num1 ^ num2;
		case "|":
			return num1 | num2;
		}
		throw new Exception();
	}

	private int cal(int num1, String s) throws Exception {
		switch (s) {
		case "m":
			return -1 * num1;
		case "~":
			return ~num1;
		}
		throw new Exception();
	}

	private boolean isOperator(String s) {
		try {
			Integer.parseInt(s); // s �� ���ڷ� ��ȯ �Ǹ� �����ڰ� �ƴϹǷ� false
			return false;
		} catch (NumberFormatException e) {
			return true;
		}
	}

}
