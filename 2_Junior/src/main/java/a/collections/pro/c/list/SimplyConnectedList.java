package a.collections.pro.c.list;
/**
 * Class SimpleArrayList.
 * A class implements a simply-connected list, each element has a link to the next.
 * @author Egor Novikov
 * E-mail: e.novikov@yahoo.com
 * @version 1
 * @since 0.1
 */
public class SimplyConnectedList<K, V> {
    /**
     * Size.
     */
    private int size;
    /**
     * Top of list.
     */
    private Node<K, V> first;

    /**
     * The method inserts data into the top of the list.
     */
    public void add(K key, V data) {
        Node<K, V> newLink = new Node<>(key, data);
        newLink.next = this.first;
        this.first = newLink;
        this.size++;
    }

    /**
     * Method for removing the first item in the list.
     */
    public void delete(K key) {
        if (first.key == key) {
            first = first.next;
        } else {
            Node<K, V> current = first;
            Node<K, V> previous;
            for (int i = 0; i < size; i++) {
                previous = current;
                current = current.next;
                if (current.key == key) {
                    previous.next = current.next;
                    //first = current;
                    size--;
                    break;
                }
                if (current.next == null) {
                    break;
                }
            }
        }
    }

    /**
     * Method of obtaining an element by index.
     */
    public V get(K key) {
        V result = null;
        if (first.key.equals(key)) {
            result = first.data;
        } else {
            Node<K, V> current = first;
            for (int i = 0; i < size; i++) {
                current = current.next;
                if (current.key.equals(key)) {
                    result = current.data;
                    break;
                }
                if (current.next == null) {
                    break;
                }
            }
        }
        return result;
    }

    public K getKey(int index) {
        K result = null;
        if (index == 0) {
            result = first.key;
        } else {
            Node<K, V> current = first;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
            result = current.key;
        }
        return result;
    }

    public V getData(int index) {
        V result = null;
        if (index == 0) {
            result = first.data;
        } else {
            Node<K, V> current = first;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
            result = current.data;
        }
        return result;
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
    public static class Node<K, V> {

        K key;
        V data;
        //int hash;
        Node<K, V> next;

        Node(K key, V data) {
            this.key = key;
            this.data = data;
            //this.hash = data.hashCode();
        }
    }
}
