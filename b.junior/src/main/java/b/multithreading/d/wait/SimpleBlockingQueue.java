package b.multithreading.d.wait;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;
import java.util.LinkedList;
import java.util.Queue;

@ThreadSafe
public class SimpleBlockingQueue<T> {

    @GuardedBy("this")
    private final Queue<T> queue = new LinkedList<>();
    private int limit;

    public SimpleBlockingQueue(int limit) {
        this.limit = limit;
    }

    public void offer(T value) throws InterruptedException {
        synchronized (this.queue) {
            queue.offer(value);
            if (queue.size() == limit) {
                queue.wait();
            }
        }
    }

    public T poll() throws InterruptedException {
        T result = null;
        synchronized (this.queue) {
            if (queue.size() == 1) {
                queue.notify();
            }
            result = queue.poll();
            return result;
        }
    }

    public Queue<T> getQueue() {
        return this.queue;
    }
}
