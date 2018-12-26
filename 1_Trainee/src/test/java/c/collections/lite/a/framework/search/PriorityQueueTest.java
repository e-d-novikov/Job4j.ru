package c.collections.lite.a.framework.search;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
/**
 * Class PriorityQueueTest tests methods from class PriorityQueue.
 * @author Egor Novikov (e.novikov@yahoo.com)
 * @version 1
 * @since 1
 */
public class PriorityQueueTest {
    @Test
    public void whenHigherPriority() {
        PriorityQueue queue = new PriorityQueue();
        queue.put(new Task("low", 5));
        queue.put(new Task("urgent", 1));
        queue.put(new Task("middle", 3));
        String[] result = queue.result();
        assertThat(result, is(new String[]{"urgent", "middle", "low"}));
    }

    @Test
    public void whenHigherPriorityTwo() {
        PriorityQueue queue = new PriorityQueue();
        queue.put(new Task("low", 5));
        queue.put(new Task("middle", 3));
        queue.put(new Task("one", 20));
        queue.put(new Task("two", 13));
        queue.put(new Task("three", 8));
        queue.put(new Task("urgent", 1));
        String[] result = queue.result();
        assertThat(result, is(new String[]{"urgent", "middle", "low", "three", "two", "one"}));
    }

    @Test
    public void whenHigherPriorityThree() {
        PriorityQueue queue = new PriorityQueue();
        queue.put(new Task("one", 10));
        queue.put(new Task("low", 5));
        queue.put(new Task("urgent", 1));
        queue.put(new Task("two", 10));
        queue.put(new Task("three", 10));
        queue.put(new Task("middle", 3));
        String[] result = queue.result();
        assertThat(result, is(new String[]{"urgent", "middle", "low", "one", "two", "three"}));
    }
}