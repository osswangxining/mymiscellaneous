package timetask;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledExecutorTest implements Runnable {

  private String jobName = "";
  private boolean busy;

  public ScheduledExecutorTest(String jobName, boolean busy) {
    super();
    this.jobName = jobName;
    this.busy = busy;
  }

  @Override
  public void run() {
    if (busy) {
      try {
        Thread.sleep(5000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    System.out.println("Job: " + jobName + ", time:" + (System.currentTimeMillis() / 1000));

  }

  public static void main(String[] args) {
    ScheduledExecutorService service = Executors.newScheduledThreadPool(2);
    service.scheduleAtFixedRate(new ScheduledExecutorTest("job1", true), 1000, 1000, TimeUnit.MILLISECONDS);
    service.scheduleAtFixedRate(new ScheduledExecutorTest("job2", false), 2000, 2000, TimeUnit.MILLISECONDS);
  }
}
