package concurrent.callable;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class CallableFutureTaskSample {
	public static void main(String[] args) {
		ExecutorService executor = Executors.newCachedThreadPool();
		Task task = new Task();
		FutureTask<Integer> futureTask = new FutureTask<Integer>(task);

		executor.submit(futureTask);
		// can cancel the task
		futureTask.cancel(true);

		executor.shutdown();

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}

		System.out.println("This task is from main thread....");

		try {
			if (futureTask.isCancelled()) {
				System.out.println("task execution result from sub-task: cancelled");
			} else {
				System.out.println("task execution result from sub-task:" + futureTask.get());
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}

		System.out.println("All tasks have been done.");
	}
}
