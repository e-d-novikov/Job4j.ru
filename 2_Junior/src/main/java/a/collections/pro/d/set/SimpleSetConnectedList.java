package a.collections.pro.d.set;

import java.util.Iterator;
import java.util.NoSuchElementException;
/**
 * Class SimpleSetConnectedList.
 * Collection Set linked list based.
 * @author Egor Novikov
 * E-mail: enovikovdev@gmail.com
 * @version 1
 * @since 0.1
 */
public class SimpleSetConnectedList<E> implements Iterable<E> {
    /**
     * Size.
     */
    private int size;
    /**
     * Top of set.
     */
    private Node<E> head;
    /**
     * End of set.
     */
    private Node<E> tail;
    /**
     * Node.
     * @param <E> - object.
     */
    private static class Node<E> {
        /**
         * Data.
         */
        E data;
        /**
         * Link for previous object.
         */
        Node<E> previous;
        /**
         * Link for next object.
         */
        Node<E> next;
        /**
         * Constructor.
         * @param previous - link for previous object;
         * @param data - data;
         * @param next - link for next object.
         */
        Node(Node<E> previous, E data, Node<E> next) {
            this.data = data;
            this.previous = previous;
            this.next = next;
        }
    }
    /**
     * Method adds object to the set.
     * @param data - data.
     */
    public void add(E data) {
        if (validate(data)) {
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
    }
    /**
     * Method return object.
     * @param index - index.
     */
    public E get(int index) {
        Node<E> result = this.head;
        for (int i = 0; i < index; i++) {
            result = result.next;
        }
        return result.data;
    }
    /**
     * Method check for duplicates.
     * @param data - data.
     * @return - true, if validate successfully, else false.
     */
    private boolean validate(E data) {
        boolean result = true;
        Node<E> valid = this.head;
        for (int i = 0; i < size; i++) {
            if (valid.data == data) {
                result = false;
                break;
            }
            valid = valid.next;
        }
        return result;
    }
    /**
     * Iterator.
     * @return - iterator.
     */
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            /**
             * Index iterator.
             */
            private int index;
            /**
             * The method checks for the presence of the next element in the array.
             * @return - true, if the item is, else false.
             */
            @Override
            public boolean hasNext() {
                boolean result = false;
                if (index < size) {
                    result = true;
                }
                return result;
            }
            /**
             * The method return next element.
             * @return - next element.
             */
            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                E result = get(index);
                index++;
                return result;
            }
        };
    }
}
