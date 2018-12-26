package a.collections.pro.c.list;

import a.collections.pro.b.generic.SimpleArray;
import java.util.Iterator;
/**
 * Queue.
 * @author Egor Novikov
 * E-mail: e.novikov@yahoo.com
 * @version 1
 * @since 0.1
 */
public class SimpleQueue<E> extends SimpleArray<E> {
    /**
     * Size.
     */
    private int size;
    /**
     * Top of queue.
     */
    private Node<E> first;
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
        E result = get(size - 1);
        for (int i = size - 2; i >= 0; i--) {
            if (i == size - 2) {
                Node<E> inter = new Node<E>(null, get(size - 2), null);
                this.intermediate = inter;
            } else {
                Node<E> inter = new Node<E>(intermediate, get(i), null);
                intermediate.next = inter;
                this.intermediate = inter;
            }
        }
        size--;
        this.first = this.intermediate;
        return result;
    }
    /**
     * Method adds object to queue.
     * @param object - object.
     */
    public void add(E object) {
        if (size == 0) {
            Node<E> newLink = new Node<E>(null, object, null);
            this.first = newLink;
            this.size++;
        } else {
            Node<E> newLink = new Node<E>(first, object, null);
            first.next = newLink;
            first = newLink;
            this.size++;
        }
    }
    /**
     * Method return data from Queue.
     * @param index - index.
     * @return - data.
     */
    public E get(int index) {
        Node<E> result = this.first;
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
