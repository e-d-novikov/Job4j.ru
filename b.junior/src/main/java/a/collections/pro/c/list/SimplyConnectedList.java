package a.collections.pro.c.list;
/**
 * Class SimpleArrayList.
 * A class implements a simply-connected list, each element has a link to the next.
 * @author Egor Novikov
 * E-mail: e.novikov@yahoo.com
 * @version 1
 * @since 0.1
 */
public class SimplyConnectedList<E> {
    /**
     * Size.
     */
    private int size;
    /**
     * Top of list.
     */
    private Node<E> first;
    /**
     * The method inserts data into the top of the list.
     */
    public void add(E date) {
        Node<E> newLink = new Node<>(date);
        newLink.next = this.first;
        this.first = newLink;
        this.size++;
    }
    /**
     * Method for removing the first item in the list.
     */
    public void delete() {
        this.first = this.first.next;
        size--;
    }
    /**
     * Method of obtaining an element by index.
     */
    public E get(int index) {
        Node<E> result = this.first;
        for (int i = 0; i < index; i++) {
            result = result.next;
        }
        return result.date;
    }
    /**
     * Method of obtaining the size of the collection.
     */
    public int getSize() {
        return this.size;
    }
    /**
     * The class is intended for data storage.
     */
    private static class Node<E> {
        E date;
        Node<E> next;

        Node(E date) {
            this.date = date;
        }
    }
}
