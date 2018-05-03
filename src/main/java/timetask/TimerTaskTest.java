package timetask;

import java.util.Timer;
import java.util.TimerTask;

public class TimerTaskTest extends TimerTask {
  private String jobName = "";
  private boolean busy;
  public TimerTaskTest(String jobName, boolean busy) {
    super();
    this.jobName = jobName;
    this.busy = busy;
  }

  @Override
  public void run() {
    if(busy) {
      try {
        Thread.sleep(5000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    System.out.println("Job: " + jobName + ", time:" + (System.currentTimeMillis()/1000));

  }

  public static void main(String[] args) {
    Timer timer = new Timer();  
    timer.schedule(new TimerTaskTest("job1", true), 1000, 1000); 
    timer.schedule(new TimerTaskTest("job2",false), 2000, 2000); 
  }

}
