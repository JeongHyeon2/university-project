package FinalTest;

import java.util.Scanner;

public class GradeFinding {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		String s = "20170217	A+/A\r\n" + 
				"20170806	B\r\n" + 
				"20180056	A+\r\n" + 
				"20180181	A\r\n" + 
				"20180283	A+\r\n" + 
				"20180330	A\r\n" + 
				"20180340	B+\r\n" + 
				"20180457	A/B+\r\n" + 
				"20180500	A\r\n" + 
				"20180578	B+\r\n" + 
				"20180584	A\r\n" + 
				"20180596	A\r\n" + 
				"20180689	B\r\n" + 
				"20180819	B\r\n" + 
				"20180910	B+\r\n" + 
				"20180950	A\r\n" + 
				"20180977	A\r\n" + 
				"20181107	B\r\n" + 
				"20181170	B\r\n" + 
				"20181260	B\r\n" + 
				"20190608	B+\r\n" + 
				"20191203	A+\r\n" + 
				"20200284	A+\r\n" + 
				"20200384	B\r\n" + 
				"20200743	A+\r\n" + 
				"20200830	B+\r\n" + 
				"20200881	A\r\n" + 
				"20200979	B+\r\n" + 
				"20201211	B+\r\n" + 
				"20201294	B+eof";

		int[] arr = new int[9];
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if(c=='/') i+=2;
			switch (c) {
			case 'A':
				if (s.charAt(i + 1) == '+')
					arr[0]++;
				else
					arr[1]++;
				break;
			case 'B':
				if (s.charAt(i + 1) == '+')
					arr[2]++;
				else
					arr[3]++;
				break;
			case 'C':
				if (s.charAt(i + 1) == '+')
					arr[4]++;
				else
					arr[5]++;
				break;
			case 'D':
				if (s.charAt(i + 1) == '+')
					arr[6]++;
				else
					arr[7]++;
				break;
			case 'F':
				arr[8]++;
				break;

			}
		}
		System.out.println("A+ : "+arr[0]+"명");
		System.out.println("A  : "+arr[1]+"명");
		System.out.println("B+ : "+arr[2]+"명");
		System.out.println("B  : "+arr[3]+"명");
		System.out.println("C+ : "+arr[4]+"명");
		System.out.println("C  : "+arr[5]+"명");
		System.out.println("D+ : "+arr[6]+"명");
		System.out.println("D  : "+arr[7]+"명");
		System.out.println("F  : "+arr[8]+"명");
		int sum=0;
		for(int i=0;i<arr.length;i++) {
			sum+=arr[i];
		}
		System.out.println("전체 인원 : "+sum);
		System.out.println("A+비율 : "+(double)arr[0]/sum*100);
		System.out.println("A비율 : "+(double)arr[1]/sum*100);
		System.out.println("B+비율 : "+(double)arr[2]/sum*100);
		System.out.println("B비율 : "+(double)arr[3]/sum*100);
		System.out.println("C+비율 : "+(double)arr[4]/sum*100);
		System.out.println("C비율 : "+(double)arr[5]/sum*100);
		System.out.println("D+비율 : "+(double)arr[6]/sum*100);
		System.out.println("D비율 : "+(double)arr[7]/sum*100);
		System.out.println("F비율 : "+(double)arr[8]/sum*100);





	}

}
