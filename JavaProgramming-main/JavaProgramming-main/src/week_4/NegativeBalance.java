package week_4;

public class NegativeBalance extends RuntimeException{
	NegativeBalance(){
		super();
	}
	NegativeBalance	(String m){
		super(m);
	}
}