package thread.interrupt;

public class ThreadFlag extends Thread {
	private volatile boolean exit = false;

	public void run() {
		while (!exit) {
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("exit:" + System.currentTimeMillis());
		}
	}

	public static void main(String[] args) throws InterruptedException {
		ThreadFlag t = new ThreadFlag();
		t.start();
		long begin = System.currentTimeMillis();
		System.out.println(begin);
		sleep(5000);

		t.exit = true;
		t.join();
		System.out.println("thred is shutdown...");
		System.out.println(System.currentTimeMillis() - begin);
	}
}
