package 과제4_2번;

import java.util.Scanner;

public class Calculator {
	private LinkedListQueue<Token> infix = new LinkedListQueue<Token>(); // 중위표기
	private LinkedListQueue<Token> postfix = new LinkedListQueue<Token>(); // 후위표기
	private ArrayStack<Token> stack = new ArrayStack<Token>(); //token 스택
	private ArrayStack<TreeNode> btstack = new ArrayStack<TreeNode>(); // 노드 스택
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
			System.out.print("식을 입력하세요(종료는 quit): ");
			String s = sc.nextLine();

			if (s.equals("quit")) {
				System.out.println("종료합니다.");
				return;
			}
			try {
				Calculator c = new Calculator();

				c.calculate(s);
				System.out.println("postfixNotation : " + c.getPostfixNotation());
				
				System.out.print("prefix notation : ");
				c.preorder();

				c.printTree();
				System.out.println("답: " + c.getAnswer());
			} catch (Exception e) {
				System.out.println("[Error] 수식 오류");
			} // 식 입력시 계산
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

	// String을 Token변환 후 queue에 저장
	private void stringToQueue(String s) throws Exception {
		String sArray[] = s.trim().split(" "); // 앞뒤 공백 제거 후 배열에 저장
		for (int i = 0; i < sArray.length; i++) {
			if (sArray[i].equals("-")) {
				if (i == 0)
					infix.enqueue(new Token("m")); // 단항연산자 -일 경우
				else {
					if (isOperator(sArray[i - 1]))
						infix.enqueue(new Token("m"));// 단항연산자 -일 경우
					else
						infix.enqueue(new Token(sArray[i])); // 이항연산자 -일 경우
				}
			} else
				infix.enqueue(new Token(sArray[i]));
		}
	}

	// 전위표기를 후위표기로 변환
	private void infixToPostfix() throws Exception {
		int count = infix.size();

		for (int i = 0; i < count; i++) {
			tokenInsertToStack(infix.dequeue());
		}
		while (!stack.isEmpty()) {
			Token tmp = stack.pop();
			if (tmp.getICP() == 0)
				throw new Exception("수식 오류!"); // 스택에 괄호가 남아있으면 오류
			if (!tmp.getValue().equals("#"))
				postfix.enqueue(tmp);
		}
	
	}

	// queue의 token을 stack에 넣기
	private void tokenInsertToStack(Token t) throws Exception {

		if (t.isOperator()) // 연산자면 stack에 넣기
		{
			if (t.getValue().equals(")")) // 닫는 괄호일때
			{
				int size=stack.getTop()+1;
				for(int i=0;i<size;i++) {
					Token token = stack.pop();
					if (token.getValue().equals("("))
						break;
					postfix.enqueue(token);
				}

			} else { // 닫는 괄호가 아닌 연산자일때
				if (stack.top().getISP() == 1) { // 단항 연산자일때
					while (stack.top().getISP() < t.getICP()) {
						Token top = stack.pop();
						postfix.enqueue(top);

					}
				} else { // 아닐때
					while (stack.top().getISP() <= t.getICP()) {
						Token top = stack.pop();
						postfix.enqueue(top);

					}
				}
				stack.push(t);

			}
		} else { // 숫자면 출력
			postfix.enqueue(t);
		}

	}

	// postfix를 tree로 변환
	private void postfixToTree() throws Exception {
		int size = postfix.size();
		for (int i = 0; i < size; i++) {
			Token t = postfix.dequeue();
			postfixNotation += t.getValue() + " ";

			if (isOperator(t.getValue())) { // 연산자일때

				if (t.getValue().equals("m") || t.getValue().equals("~")) { // 단항 연산자일때
					TreeNode pop = btstack.pop();
					TreeNode node = new TreeNode(t, pop, null);
					node.setValue(cal(pop.getValue(), t.getValue()));
					btstack.push(node);
				} else {// 이항 연산자일때
					TreeNode pop1 = btstack.pop();
					TreeNode pop2 = btstack.pop();

					TreeNode node = new TreeNode(t, pop2, pop1);
					node.setValue(cal(pop2.getValue(), pop1.getValue(), t.getValue()));
					btstack.push(node);
				}

			} else { // 피연산자일때
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
			Integer.parseInt(s); // s 가 숫자로 변환 되면 연산자가 아니므로 false
			return false;
		} catch (NumberFormatException e) {
			return true;
		}
	}

}
