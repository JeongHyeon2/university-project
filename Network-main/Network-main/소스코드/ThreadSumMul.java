// 2�� ������ ���� �� ����
class ThreadSum extends Thread{

	@Override
	public void run() {
		// 1���� 10���� ���ؼ� ��� ����ϱ�
		int sum=0;
		for(int i=1 ; i <= 10 ; i++ ) {
			sum = sum + i ;
			System.out.println(i + "���ϱ�: " + sum);
		}
		
		System.out.println("1~10 �հ� ���: " + sum);
	}
}

class ThreadMul extends Thread{

	@Override
	public void run() {
		// 1���� 10���� ���ؼ� ��� ����ϱ�
		int multiply=1;
		for(int i=1 ; i <= 10 ; i++ ) {
			multiply = multiply * i ;
			System.out.println(i + "���ϱ�: " + multiply);
		}
		
		System.out.println("1~10 ���ϱ� ���: " + multiply);
	}
}

public class ThreadSumMul {
	public static void main(String [] args) {
		ThreadSum ths = new ThreadSum();
		ths.start();

		ThreadMul thm = new ThreadMul();
		thm.start();
	}
}

