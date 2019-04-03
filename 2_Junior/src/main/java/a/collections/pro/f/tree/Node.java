package a.collections.pro.f.tree;

import java.util.ArrayList;
import java.util.List;
/**
 * @author Egor Novikov
 * E-mail: enovikovdev@gmail.com
 * @version $Id$
 * @since 0.1
 */
public class Node<E extends Comparable<E>> {

    E value;
    List<Node<E>> children = new ArrayList<>();

    public Node(final E value) {
        this.value = value;
    }
}