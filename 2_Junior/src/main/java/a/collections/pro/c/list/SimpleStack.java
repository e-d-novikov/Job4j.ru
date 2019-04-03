package a.collections.pro.c.list;

import a.collections.pro.b.generic.SimpleArray;
import java.util.Iterator;
/**
 * Stack.
 * @author Egor Novikov
 * E-mail: enovikovdev@gmail.com
 * @version 1
 * @since 0.1
 */
public class SimpleStack<E> extends SimpleArray<E> {
    /**
     * Size.
     */
    private int size;
    /**
     * End of list.
     */
    private Node<E> previous;
    /**
     * Intermediate data.
     */
    private Node<E> intermediate;
    /**
     * Item.
     * @param <E> - Object.
     */
    private static class Node<E> {
        /**
         * Data.
         */
        E data;
        /**
         * Next object.
         */
        Node<E> next;
        /**
         * Previous object.
         */
        Node<E> previous;
        /**
         * Constructor.
         * @param previous - link to previous object;
         * @param data - data;
         * @param next - link to next object.
         */
        Node(Node<E> previous, E data, Node<E> next) {
            this.data = data;
            this.next = next;
            this.previous = previous;
        }
    }
    /**
     * Method returns an object and deletes it
     * @return - object.
     */
    public E poll() {
        Node<E> result = this.previous;
        for (int i = size; i > 0; i--) {
            if (i == size) {
                Node<E> inter = new Node<E>(null, get(size - 1), null);
                this.intermediate = inter;
            } else {
                Node<E> inter = new Node<E>(intermediate, get(i), null);
                intermediate.next = inter;
                this.intermediate = inter;
            }
        }
        size--;
        this.previous = this.intermediate;
        return result.data;
    }
    /**
     * Method adds object to queue.
     * @param data - object.
     */
    public void add(E data) {
        if (size == 0) {
            Node<E> newLink = new Node<E>(null, data, null);
            this.previous = newLink;
            this.size++;
        } else {
            Node<E> newLink = new Node<E>(previous, data, null);
            previous.next = newLink;
            previous = newLink;
            this.size++;
        }
    }
    /**
     * Method return data from Queue.
     * @param index - index.
     * @return - data.
     */
    public E get(int index) {
        Node<E> result = this.previous;
        for (int i = 0; i < index; i++) {
            result = result.previous;
        }
        return result.data;
    }
    /**
     * Iterator.
     * @return - iterator.
     */
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            /**
             * Index of iterator.
             */
            int index = 0;
            /**
             * The method checks for the presence of the next element in the array.
             * @return - true, if the item is, else false.
             */
            @Override
            public boolean hasNext() {
                boolean result = false;
                if (index >= 0 && index < size) {
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
                E result = null;
                if (index >= 0 && index < size) {
                    result = get(index);
                    index++;
                }
                return result;
            }
        };
    }
}
