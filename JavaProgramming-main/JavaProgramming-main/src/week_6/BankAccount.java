package week_6;
public class BankAccount{
	private int balance;
	public BankAccount() {}
	public BankAccount(int b) {
		balance=b;
	}
	public void deposit (int d) {
		balance+=d;
	}
	public void withdraw(int w) throws NegativeBalance {
		if(balance<w) throw new NegativeBalance("ÀÜ¾× ºÎÁ·");
		else balance-=w;
	}
	public void setbalance(int b) {
		balance=b;
	}
	public int getbalance() {
		return balance;
	}
	public static void main(String[ ]args) throws NegativeBalance{
		BankAccount ba = new BankAccount();
		ba.setbalance(10000);
		ba.withdraw(15000);
		
		
	}
}
class NegativeBalance extends Exception{
	public NegativeBalance() {}
	public NegativeBalance(String msg) {
		System.out.println(msg);
	}
}
