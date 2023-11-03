package week_4;


public class BankAccountTest {

	public static void main(String[] args){
		BankAccount b1=new BankAccount(1000);
		try{
			b1.withdraw(10000);
		}catch(NegativeBalance e){
			System.out.println(e);
			}
	}
	
}



