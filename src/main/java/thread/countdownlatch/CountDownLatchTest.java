package thread.countdownlatch;
import java.util.concurrent.CountDownLatch;

public class CountDownLatchTest {
	private static volatile int num;

	public static void main(String[] args) throws InterruptedException {
		long begin = System.currentTimeMillis();
		CountDownLatch c = new CountDownLatch(100);
		for (int i = 0; i < 30; i++) {
			Thread t = new Thread(new ThreadTest(i, c));
			t.start();
		}
		c.await();
		System.out.println("consumed time:" + (System.currentTimeMillis() - begin) + ",end...." + num);
	}

	private static class ThreadTest implements Runnable {
		private int index;
		private CountDownLatch c;
		public ThreadTest(int i, CountDownLatch c) {
			this.index = i;
			this.c = c;
		}

		@Override
		public void run() {
			num++;
			c.countDown();
			System.out.println("index:" + index + ",num:" + num);
			
		}

	}

}
