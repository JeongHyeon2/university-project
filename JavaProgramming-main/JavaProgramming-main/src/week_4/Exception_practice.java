package week_4;

public class Exception_practice {
	public static void main(String args[]){
	
			try{
				int arr[] = new int[5];
				arr[4] = 20/0; // 예외발생
				System.out.println("First print statement in try block");
			}
			catch(ArithmeticException e){ // 여기서 받음
				System.out.println("Warning: ArithmeticException");
			}
			catch(ArrayIndexOutOfBoundsException e){
				System.out.println("Warning: ArrayIndexOutOfBoundsException");
			}
			catch(Exception e){
				System.out.println("Warning: Some Other exception");
			}
			System.out.println("try-catch 블락 밖...");
		    }
}
