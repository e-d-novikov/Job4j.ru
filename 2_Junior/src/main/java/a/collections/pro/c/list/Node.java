package a.collections.pro.c.list;
/**
 * Node.
 * @author Egor Novikov
 * E-mail: enovikovdev@gmail.com
 * @version 1
 * @since 0.1
 */
public class Node<T> {
    T value;
    Node<T> next;

    Node(T value) {
        this.value = value;
    }
}
