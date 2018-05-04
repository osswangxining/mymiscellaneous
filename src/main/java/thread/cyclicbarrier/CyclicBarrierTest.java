package thread.cyclicbarrier;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierTest {

	public static void main(String[] args) {
		CyclicBarrier cb = new CyclicBarrier(5, new Runnable() {

			@Override
			public void run() {
				System.out.println("线程组执行结束");  
			}

		});
		
		for (int i = 0; i < 5; i++) {  
            new Thread(new Thread1(i,cb)).start();  
        }  
		
		for (int i = 0; i < 10; i++) {  
            new Thread(new Thread1(i,cb)).start();  
        }  
	}

	static class Thread1 implements Runnable {
		private int id;
		private CyclicBarrier cb;
		
		public Thread1(int id, CyclicBarrier cb) {
			this.id = id;
			this.cb = cb;
		}
		
		@Override
		public void run() {
			synchronized(this) {
				System.out.println("id:" + id);
				
				try {
					cb.await();
					 System.out.println("线程组任务" + id + "结束，其他任务继续");  
				} catch (InterruptedException | BrokenBarrierException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}
}
