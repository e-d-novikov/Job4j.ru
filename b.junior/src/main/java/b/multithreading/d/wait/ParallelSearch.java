package b.multithreading.d.wait;

import net.jcip.annotations.ThreadSafe;

@ThreadSafe
public class ParallelSearch {

    public static void main(String[] args) throws InterruptedException {

        SimpleBlockingQueue<Integer> test = new SimpleBlockingQueue<>(2);

        Thread producer = new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    try {
                        test.offer(i);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        };
        Thread consumer = new Thread() {
            @Override
            public void run() {
                while (producer.isAlive()) {
                    try {
                        System.out.println(test.poll());
                        Thread.sleep(25);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        Thread.currentThread().interrupt();
                    }
                }
            }
        };
        producer.start();
        consumer.start();
        producer.join();
        consumer.join();
    }
}

