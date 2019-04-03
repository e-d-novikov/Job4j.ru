package b.multithreading.f.pools;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/**
 * Class EmailNotification.
 * @author Egor Novikov
 * E-mail: enovikovdev@gmail.com
 * @version 1$
 * @since 0.1
 */
public class EmailNotification {

  private ExecutorService pool = Executors.newFixedThreadPool(
          Runtime.getRuntime().availableProcessors()
  );

  public void emailTo(User user) {
    pool.submit(new Runnable() {
      public void run() {
        String subject = String.format("Notification %s to email %s.", user.getName(), user.getEmail());
        String body = String.format("Add a new event to %s", user.getName());
        send(subject, body, user.getEmail());
      }
    });
  }

  public void close() {
    pool.shutdown();
    while (!pool.isTerminated()) {
      try {
        Thread.sleep(100);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

  public void send(String suject, String body, String email) {

  }
}
