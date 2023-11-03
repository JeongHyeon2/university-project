package 과제3;

public class Token {
	private String operator; // 연산자
	private int operand; // 피연산자
	private int isp,icp; 
	public Token(String s) throws Exception {	
		if(isOperator(s)) { // 연산자이면
			operator =s; // 연산자에 저장
			setPriority(s);
		}
		else operand=Integer.parseInt(s); // 피연산자(숫자)이면 피연산자에 저장
	}
	public Token(int n) {
		operand=n;
	}
	public boolean isOperator(String s) {
		try {
			Integer.parseInt(s); // s 가 숫자로 변환 되면 연산자가 아니므로 false
			return false;
		} catch (NumberFormatException e) {
			return true; 
		}
	}
	public boolean isOperator() {
		if(operator==null) return false;
		return true;
	}
	public String getToken() {
		if(operator==null) return Integer.toString(operand); // 연산자가 null (즉, token이 숫자일 경우)
		return operator;
	}
	public int getISP() {
		return isp;
	}
	public int getICP() {
		return icp;
	}
	private void setPriority(String s) throws Exception {
		switch(s) {
		case "(": case ")":	isp=8; icp=0; break;
		case "m": case "~": isp=1; icp=1; break;
		case "*": case "/": case"%":isp=2; icp=2; break;
		case "+": case "-": isp=3; icp=3; break;
		case "<<": case ">>": isp=4; icp=4; break;
		case "&": isp=5; icp=5; break;
		case "^": isp=6; icp=6; break;
		case "|": isp=7; icp=7; break;
		case "#": isp=10; icp=10; break;
		default : throw new Exception();	
		}
	}
}


