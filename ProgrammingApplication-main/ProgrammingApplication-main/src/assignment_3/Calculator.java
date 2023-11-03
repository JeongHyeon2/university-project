package assignment_3;

import java.util.Scanner;

public class Calculator {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		while(true) {
			System.out.print("Input Equation (num1 op num2) :");
			float num1=sc.nextInt();
			String op = sc.next();
			float num2 = sc.nextInt();
			
			switch(op) {
			case "+": System.out.println(num1+" "+op+" "+num2+" = "+(num1+num2)); 
			      break;
			case "-": System.out.println(num1+" "+op+" "+num2+" = "+(num1-num2)); 
				  break;
			case "*": System.out.println(num1+" "+op+" "+num2+" = "+num1*num2); 
				  break;
			case "/": System.out.println(num1+" "+op+" "+num2+" = "+num1/num2); 
				  break;
		  	}
			System.out.println("반복 수행?(Yes / No)");
			String s = sc.next();
			
			if(s.equals("No")) break;
		}
	}
}