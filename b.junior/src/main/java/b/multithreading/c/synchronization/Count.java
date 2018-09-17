package b.multithreading.c.synchronization;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;
/**
 * Class Count.
 *
 * @author Egor Novikov
 * E-mail: e.novikov@yahoo.com
 * @version 1
 * @since 0.1
 */
@ThreadSafe
public class Count {

    @GuardedBy("this")
    private int value;

    public synchronized void increment() {
        this.value++;
    }

    public synchronized int get() {
        return this.value;
    }
}