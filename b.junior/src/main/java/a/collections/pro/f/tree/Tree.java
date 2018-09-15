package a.collections.pro.f.tree;

import java.util.*;

public class Tree<E extends Comparable<E>> implements SimpleTree<E> {

    private Node<E> root;
    private ArrayList<E> unPack = new ArrayList<>();
    private int position = 0;
    private boolean binary = true;

    public Tree(E value) {
        root = new Node<>(value);
        position++;
    }

    @Override
    public boolean add(E parent, E child) {
        boolean result = false;
        if (!findBy(child).isPresent()) {
            if (findBy(parent).isPresent()) {
                findBy(parent).get().add(new Node<>(child));
                position++;
                result = true;
            }
        }
        return result;
    }

    @Override
    public Optional<Node<E>> findBy(E value) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (el.eqValue(value)) {
                rsl = Optional.of(el);
                break;
            }
            for (Node<E> child : el.leaves()) {
                data.offer(child);
            }
        }
        return rsl;
    }

    public void unPack(Node<E> value) {
        if (!unPack.contains(value.getValue())) {
            unPack.add(value.getValue());
        }
        if (value.leaves().size() > 0) {
            for (int i = 0; i < value.leaves().size(); i++) {
                unPack.add(value.leaves().get(i).getValue());
                if (value.leaves().get(i).leaves().size() > 0) {
                    unPack(value.leaves().get(i));
                }
            }
        }
    }

    public void isBinaryTest(Node<E> node) {
        if (node.leaves().size() > 0 && node.leaves().size() <= 2) {
            for (Node check : node.leaves()) {
                if (check.leaves().size() > 0 && check.leaves().size() <= 2) {
                    isBinaryTest(check);
                }
            }
        } else if (node.leaves().size() == 0) {
            binary = true;
        } else {
            binary = false;
        }
    }

    public boolean isBunary() {
        isBinaryTest(root);
        boolean result = binary;
        return result;
    }


    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {

            private int index = 0;

            @Override
            public boolean hasNext() {
                boolean result = false;
                if (index < position) {
                    result = true;
                }
                return result;
            }

            @Override
            public E next() {
                E result = null;
                unPack(root);
                if (index < position) {
                    if (index == 0) {
                        result = root.getValue();
                        index++;
                    } else {
                        result = unPack.get(index);
                        index++;
                    }
                } else {
                    throw new NoSuchElementException();
                }
                return result;
            }
        };
    }
}
