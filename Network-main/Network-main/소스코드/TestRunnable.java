class TimerRunnable implements Runnable {
	int n = 0;
	public void run() {
		System.out.println("������ ����");
		while(true) { // ���ѷ��� ����
			System.out.println(n); 
			n++;
			 try {
				Thread.sleep(1000); //1�� ���� ���� �� �� ���
			}
			catch(InterruptedException e){return;}
		}
	}
}

public class TestRunnable {
	public static void main(String [] args) {
		Runnable r = new TimerRunnable();
		Thread th = new Thread(r);
		th.start();

	}
}

