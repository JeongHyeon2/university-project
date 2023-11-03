package 과제3;

public class Calculator {
	private LinkedListQueue<Token> infix = new LinkedListQueue<Token>(); // 중위표기
	private LinkedListQueue<Token> postfix = new LinkedListQueue<Token>(); // 후위표기
	private ArrayStack<Token> stack = new ArrayStack<Token>();
	private String postfixNotation = "";

	public Calculator() throws Exception {
		stack.push(new Token("#")); // 스택 바닥
	}

	public String getAnswer() {
		return stack.pop().getToken();
	}

	public String getPostfixNotation() {
		return postfixNotation;
	}

	public void cal(String s) throws Exception {
		
		stringToQueue(s);
		infixToPostfix();
		calculate();
	}

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

	private void infixToPostfix() throws Exception {
		int count = infix.size();

		for (int i = 0; i < count; i++) {
			tokenInsertToStack(infix.dequeue());
		}
		while (!stack.isEmpty()) {
			Token tmp = stack.pop();
			if (tmp.getICP() == 0)
				throw new Exception("수식 오류!"); // 스택에 괄호가 남아있으면 오류
			if (!tmp.getToken().equals("#"))
				postfix.enqueue(tmp);
		}
	}

	private void tokenInsertToStack(Token t) throws Exception {
		if (t.isOperator()) // 연산자면 stack에 넣기
		{
			if (t.getToken().equals(")")) // 닫는 괄호일때
			{
				while (true) {
					Token token = stack.pop();
					if (token.getToken().equals("("))
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

	private void calculate() throws Exception {
		int size = postfix.size();

		for (int i = 0; i < size; i++) {
			Token t = postfix.dequeue();
			postfixNotation += t.getToken() + " ";

			if (t.isOperator()) { // 연산자일때
				try {
					if (t.getToken().equals("m") || t.getToken().equals("~")) { // 단항 연산자일때
						int num1 = Integer.parseInt(stack.pop().getToken());
						stack.push(new Token(calculate(num1, t.getToken())));
					} else {// 이항 연산자일때
						int num1 = Integer.parseInt(stack.pop().getToken());
						int num2 = Integer.parseInt(stack.pop().getToken());

						stack.push(new Token(calculate(num2, num1, t.getToken()))); // 계산 한 후 stack에 저장
					}
				} catch (Exception e) { // 이항연산자가 들어올때 pop된 두개의 숫자가 없으면 오류 발생
					throw new Exception();
				}

			} else { // 숫자일때
				stack.push(t);
			}
		}
	}

	private int calculate(int num1, int num2, String s) throws Exception {
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

	private int calculate(int num1, String s) throws Exception {
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
