package b.multithreading.b.jmm;
/**
 * Class CounterTester.
 * @author Egor Novikov
 * E-mail: enovikovdev@gmail.com
 * @version 1$
 * @since 0.1
 */
public class CounterTester {

    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter();
        for (int i = 0; i < 200; i++) {
            CounterThread ct = new CounterThread(counter);
            ct.start();
        }
        Thread.sleep(1000);
        System.out.println("Counter:" + counter.getCounter());
    }
}

class Counter {
    private long counter = 0L;

    public synchronized void increaseCounter() {
        counter++;
    }

    public long getCounter() {
        return counter;
    }
}

class CounterThread extends Thread {
    private Counter counter;

    public CounterThread(Counter counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            counter.increaseCounter();
        }
    }

}
//В данном примере мы запускаем 200 потоков класса CounterThread
//Каждый поток получает ссылку на один единственный объект Counter.
//В процессе выполнения поток вызывает у этого объекта метод increaseCounter одну тысячу раз.
//Метод увеличивает переменную counter на 1.
//Результат должен быть 200000, но в нашем случае получаем постоянно разное число в диапазоне до 200000.
//Проблема в том, что мы из 200 потоков одновременно пытаемся вызвать метод increaseCounter.
//При одновременном считывании значения переменной, несколько потоков выполняют приращение, и записывают значение одновременно.
//Данная проблема решается с использованием ключевого слова synchronized.
//Метод increaseCounter() одновременно может быть вызван только одним потоком.