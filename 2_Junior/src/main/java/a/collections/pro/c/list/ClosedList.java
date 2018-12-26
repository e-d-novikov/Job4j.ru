package a.collections.pro.c.list;
/**
 * Class ClosedList.
 * @author Egor Novikov
 * E-mail: e.novikov@yahoo.com
 * @version 1
 * @since 0.1
 */
public class ClosedList<E> {
    /**
     * The method check closed list.
     * @param first - link to first object.
     * @return - true, if list close, else false.
     */
    public boolean hasCycle(Node<E> first) {
        boolean result = false;
        Node<E> node = first;
        Node<E> next = first.next;
        int index = 1;
        for (int i = 0; i < 2; i++) {
            for (int j = index; j > 0; j--) {
                if (node == next) {
                    result = true;
                    break;
                }
                node = node.next;
            }
            next = next.next;
            node = first;
            if (next == null || result) {
                break;
            }
            index++;
            i = 0;
        }
        return result;
    }
}


