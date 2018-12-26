package b.multithreading.f.pools;

import java.util.LinkedList;
import java.util.List;
import b.multithreading.d.wait.SimpleBlockingQueue;
/**
 * Class ThreadPool.
 * @author Egor Novikov
 * E-mail: e.novikov@yahoo.com
 * @version 1$
 * @since 0.1
 */
public class ThreadPool {

  private final List<Thread> threads = new LinkedList<>();
  private final SimpleBlockingQueue<Runnable> tasks = new SimpleBlockingQueue<>(10);

  public ThreadPool() {
    int size = Runtime.getRuntime().availableProcessors();
    Thread thread = new Thread(
            () -> {
              while (!Thread.currentThread().isInterrupted()) {
                try {
                  tasks.poll();
                  Thread.sleep(25);
                } catch (InterruptedException e) {
                  e.printStackTrace();
                  Thread.currentThread().interrupt();
                }
              }
            }
    );
    for (int i = 0; i < size; i++) {
      threads.add(thread);
    }
  }

  public void work(Runnable job) {
    try {
      tasks.offer(job);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public void shutdown() {
   for (Thread thread : threads) {
     thread.interrupt();
   }
  }
}
