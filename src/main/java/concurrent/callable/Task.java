package concurrent.callable;

import java.util.concurrent.Callable;

public class Task implements Callable<Integer> {

  @Override
  public Integer call() throws Exception {
    System.out.println("This is the task from Sub-Task....");
    Thread.sleep(2000);
    int sum = 0;
    for(int i=0;i<100;i++)
        sum += i;
    return sum;
  }

}
