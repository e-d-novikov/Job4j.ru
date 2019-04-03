package a.collections.pro.f.tree;

import java.util.Optional;
/**
 * @author Egor Novikov
 * E-mail: enovikovdev@gmail.com
 * @version $Id$
 * @since 0.1
 */
public interface SimpleTree<E extends Comparable<E>> extends Iterable<E> {
    /**
     * Добавить элемент child в parent.
     * Parent может иметь список child.
     * @param parent parent.
     * @param child child.
     * @return
     */
    boolean add(E parent, E child);

    Optional<Node<E>> findBy(E value);
}