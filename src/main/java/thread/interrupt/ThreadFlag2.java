package thread.interrupt;
import java.io.IOException;

public class ThreadFlag2 extends Thread {

	public void run() {
		try {
			sleep(50000); // 延迟50秒
		} catch (InterruptedException e) {
			System.out.println(e.getMessage());
		}
	}

	public static void main(String[] args) throws InterruptedException, IOException {
		ThreadFlag2 t = new ThreadFlag2();
		t.start();
		System.out.println("在50秒之内按任意键中断线程!");  
        System.in.read();  
        t.interrupt();  
        t.join();  
        System.out.println("线程已经退出!");  
	}
}
