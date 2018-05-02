package hashedwheeltimer.sample;

import java.io.IOException;
import java.time.LocalTime;
import java.util.concurrent.TimeUnit;

import io.netty.util.HashedWheelTimer;
import io.netty.util.Timeout;
import io.netty.util.TimerTask;

public class HashedWheelTimerTest {

	public static void main(String[] args) throws IOException {
		// 创建Timer, 精度为100毫秒,
		HashedWheelTimer timer = new HashedWheelTimer(100, TimeUnit.MILLISECONDS, 16);
		System.out.println(LocalTime.now());
		TimerTask task = new MyTask();
		timer.newTimeout(task , 5, TimeUnit.SECONDS);
		
		timer.newTimeout((timeout) -> {
			System.out.println(LocalTime.now());
			System.out.println(timeout);
		}, 5, TimeUnit.SECONDS);

		// 阻塞main线程
		System.in.read();
	}

	static class MyTask implements TimerTask {

		@Override
		public void run(Timeout timeout) throws Exception {
			// TODO Auto-generated method stub
			System.out.println(timeout);
		}
		
	}
}
