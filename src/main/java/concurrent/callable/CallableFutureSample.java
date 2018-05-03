package concurrent.callable;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableFutureSample {

  public static void main(String[] args) {
    ExecutorService executor = Executors.newCachedThreadPool();
    Task task = new Task();
    Future<Integer> result = executor.submit(task);
    executor.shutdown();

    try {
      Thread.sleep(1000);
    } catch (InterruptedException e1) {
      e1.printStackTrace();
    }

    System.out.println("This task is from main thread....");

    try {
      System.out.println("task execution result from sub-task:" + result.get());
    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (ExecutionException e) {
      e.printStackTrace();
    }

    System.out.println("All tasks have been done.");
  }

}
