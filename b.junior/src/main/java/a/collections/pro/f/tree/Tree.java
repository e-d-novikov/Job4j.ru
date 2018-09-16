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
                findBy(parent).get().children.add(new Node<>(child));
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
            if (el.value.equals(value)) {
                rsl = Optional.of(el);
                break;
            }
            for (Node<E> child : el.children) {
                data.offer(child);
            }
        }
        return rsl;
    }

    public void unPack(Node<E> value) {
        if (!unPack.contains(value.value)) {
            unPack.add(value.value);
        }
        if (value.children.size() > 0) {
            for (int i = 0; i < value.children.size(); i++) {
                unPack.add(value.children.get(i).value);
                if (value.children.get(i).children.size() > 0) {
                    unPack(value.children.get(i));
                }
            }
        }
    }

    public void isBinaryTest(Node<E> node) {
        if (node.children.size() > 0 && node.children.size() <= 2) {
            for (Node check : node.children) {
                if (check.children.size() > 0 && check.children.size() <= 2) {
                    isBinaryTest(check);
                }
            }
        } else if (node.children.size() == 0) {
            binary = true;
        } else {
            binary = false;
        }
    }

    public boolean isBinary() {
        isBinaryTest(root);
        boolean result = binary;
        return result;
    }


    @Override
    public Iterator<E> iterator() {
        unPack(root);
        Iterator<E> iteratorRoot = unPack.iterator();
        return new Iterator<E>() {

            @Override
            public boolean hasNext() {
                return iteratorRoot.hasNext();
            }

            @Override
            public E next() {
                return iteratorRoot.next();
            }
        };
    }
}
