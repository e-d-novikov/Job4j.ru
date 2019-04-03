package a.collections.pro.c.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import a.collections.pro.b.generic.SimpleArray;
import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;
/**
 * Class DynamicSimplyConnectedList.
 * Dynamic container based on simply connected list.
 * @author Egor Novikov
 * E-mail: enovikovdev@gmail.com
 * @version 1
 * @since 0.1
 */
@ThreadSafe
public class DynamicSimplyConnectedList<E> extends SimpleArray<E> {
    /**
     * Size.
     */
    private int size;
    /**
     * Modification.
     */
    private int modification;
    /**
     * Top of list
     */
    @GuardedBy("this")
    private Node<E> head;
    /**
     * End of list.
     */
    @GuardedBy("this")
    private Node<E> tail;
    /**
     * Method adds items to the list.
     * @param data - data.
     */
    public synchronized void add(E data) {
        if (size == 0) {
            Node<E> newLink = new Node<E>(null, data, null);
            this.head = newLink;
            size++;
        } else if (size == 1) {
            Node<E> newLink = new Node<E>(head, data, null);
            this.head.next = newLink;
            this.tail = newLink;
            this.size++;
        } else {
            Node<E> newLink = new Node<E>(tail, data, null);
            this.tail.next = newLink;
            this.tail = newLink;
            this.size++;
        }
    }
    /**
     * Method return data from list.
     * @param index - index.
     * @return - data.
     */
    public synchronized E get(int index) {
        Node<E> result = this.head;
        for (int i = 0; i < index; i++) {
            result = result.next;
        }
        return result.data;
    }
    /**
     * The method checks for modifications.
     * @return - true, if there were changes, else false.
     */
    private boolean change() {
        boolean result = false;
        if (size != modification) {
            result = true;
        }
        return result;
    }
    /**
     * Cell.
     * @param <E> - data.
     */
    private static class Node<E> {
        /**
         * Data.
         */
        E data;
        /**
         * Link to previous object.
         */
        Node<E> previous;
        /**
         * Link to next object.
         */
        Node<E> next;
        /**
         * Constructor.
         * @param previous - link to previous object;
         * @param data - data;
         * @param next - link to next object.
         */
        Node(Node<E> previous, E data, Node<E> next) {
            this.data = data;
            this.previous = previous;
            this.next = next;
        }
    }
    /**
     * Iterator.
     * If there were container modifications throws an error ConcurrentModificationException.
     * @return - Iterator.
     */
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            /**
             * Index of iterator.
             */
            private int index;
            /**
             * The method checks for the presence of the next element in the array.
             * @return - true, if the item is, else false.
             */
            @Override
            public boolean hasNext() {
                boolean result = false;
                if (index == 0) {
                    modification = size;
                }
                if (change()) {
                    throw new ConcurrentModificationException("ConcurrentModificationException");
                }
                if (index < size) {
                    result = true;
                    index++;
                }
                return result;
            }
            /**
             * The method return next element.
             * @return - next element.
             */
            @Override
            public E next() {
                if (index == 0) {
                    modification = size;
                }
                if (change()) {
                    throw new ConcurrentModificationException("ConcurrentModificationException");
                }
                E result = get(index);
                index++;
                return result;
            }
        };
    }
}
