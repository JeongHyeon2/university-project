package week_4;

public class BankAccount {
	int balance;
	public BankAccount(int b) {balance=b;}
	public void deposit(int m) {balance+=m;}
	public void withdraw(int m) throws NegativeBalance {
		if(balance<m) throw new NegativeBalance("ÀÜ¾× ºÎÁ·");
		else balance-=m;}
	public void setbalance(int b) {balance=b;}
	public int getbalance() {return balance;}
}
