package b.multithreading.f.pools;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/**
 * Class EmailNotification.
 * @author Egor Novikov
 * E-mail: e.novikov@yahoo.com
 * @version 1$
 * @since 0.1
 */
public class EmailNotification {

  ExecutorService pool = Executors.newFixedThreadPool(
          Runtime.getRuntime().availableProcessors()
  );

  public void emailTo(User user) {
    pool.submit(new Runnable() {
      public void run() {
        String subject = "Notification " + user.name + " to email " + user.email + ".";
        String body = "Add a new event to " + user.name;
        send(subject, body, user.email);
      }
    });
  }

  public void close() {
    pool.shutdown();
  }

  public void send(String suject, String body, String email) {

  }
}
