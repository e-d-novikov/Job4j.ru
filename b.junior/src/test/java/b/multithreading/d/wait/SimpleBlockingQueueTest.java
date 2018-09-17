package b.multithreading.d.wait;

import org.junit.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.IntStream;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SimpleBlockingQueueTest {

    @Test
    public void whenFetchAllThenGetIt() throws InterruptedException {
        final CopyOnWriteArrayList<Integer> buffer = new CopyOnWriteArrayList<>();
        final SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(5);
        Thread producer = new Thread(
                () -> {
                    for (int i = 0; i < 5; i++) {
                        try {
                            queue.offer(i);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    //IntStream.range(0, 5).forEach(
                    //        queue.getQueue()::offer
                    //);
                }
        );
        producer.start();
        Thread consumer = new Thread(
                () -> {
                    while (producer.isAlive()) {
                        try {
                            buffer.add(queue.poll());
                            Thread.sleep(25);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            Thread.currentThread().interrupt();
                        }
                    }
                }
        );
        consumer.start();
        producer.join();
        //consumer.interrupt();
        consumer.join();
        assertThat(buffer, is(Arrays.asList(0, 1, 2, 3, 4)));
    }

/*
    @Test
    public void whenTwoThreadTenElements() throws InterruptedException {

        SimpleBlockingQueue<Integer> test = new SimpleBlockingQueue<>(3);
        ArrayList<Integer> outgoing = new ArrayList<Integer>();
        for (int i = 0; i < 10; i++) {
            outgoing.add(i + 1);
        }

        ArrayList<Integer> result = new ArrayList<>();

        Thread producer = new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < outgoing.size(); i++) {
                    System.out.println("Элемент № " + (i + 1) + " добавлен");
                    try {
                        test.offer(outgoing.get(i));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        Thread customer = new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    System.out.println("Элемент № " + (i + 1) + " извлечен");
                    try {
                        result.add(test.poll());
                        Thread.sleep(25);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        producer.start();
        customer.start();
        producer.join();
        customer.join();
        assertThat(outgoing, is(result));
    }
    */
}
