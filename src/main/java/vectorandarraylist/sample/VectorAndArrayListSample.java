package vectorandarraylist.sample;

import java.util.ArrayList;
import java.util.Vector;

public class VectorAndArrayListSample {
  static Vector<Integer> vector = new Vector<Integer>();

  public static void main(String[] args) {
    ArrayList<Integer> list = new ArrayList<Integer>();

    long start = System.currentTimeMillis();
    int count = 1000* 1000;
    for (int i = 0; i < count; i++)
      list.add(i);
    long end = System.currentTimeMillis();
    System.out.println("ArrayList进行" + count + "次插入操作耗时：" + (end - start) + "ms");
    start = System.currentTimeMillis();
    for (int i = 0; i < count; i++)
      vector.add(i);
    end = System.currentTimeMillis();
    System.out.println("Vector进行" + count + "次插入操作耗时：" + (end - start) + "ms");

    VectorAndArrayListReader t1 = new VectorAndArrayListReader();
    t1.start();
    VectorAndArrayListReader t2 = new VectorAndArrayListReader();
    t2.start();
  }

  static class VectorAndArrayListReader extends Thread {

    public VectorAndArrayListReader() {
    }

    @Override
    public void run() {
      if (vector != null) {
        synchronized (vector) {
          for (int i = 0; i < vector.size(); i++) {
            vector.remove(i);
            Object object = vector.get(i);
            System.out.println(object);
          }
        }
      }
    }
  }
}
