package parallel;

import java.util.concurrent.Semaphore;

public class SemaphoreSample {

  public static void main(String[] args) {
    int CountOfRooms = 10;
    int CountOfPersons = 15;
    Semaphore semaphore = new Semaphore(CountOfRooms);
    for (int i = 0; i < CountOfPersons; i++) {
      new Person(i, semaphore).start();
    }
  }

  static class Person extends Thread {
    private int num;
    private Semaphore semaphore;

    public Person(int num, Semaphore semaphore) {
      this.num = num;
      this.semaphore = semaphore;
    }

    @Override
    public void run() {
      try {
        semaphore.acquire();
        System.out.println("Person - " + this.num + "使用了Room...");
        Thread.sleep(2000);
        System.out.println("Person - " + this.num + "退出了Room");
        semaphore.release();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}
