class TimerThread extends Thread {
	int n = 0;
	boolean flag = false; // false�� �ʱ�ȭ
	public void finish() { flag = true; }
	public void run() {
		while(true) {
			System.out.println(n); // ȭ�鿡 ī��Ʈ �� ���
			n++;
			try {
				sleep(1000);
				if(flag == true)
					return; // ������ ����
			}
			catch(InterruptedException e){
				return;
			}
		}
	}
}

class ThreadFlag{
	public static void main(String [] args) {
		TimerThread th = new  TimerThread();
		th.start();
		
		th.finish(); // TimerThread ���� ����
	}
}

