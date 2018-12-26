package a.collections.pro.c.list;

import java.util.*;
import a.collections.pro.b.generic.SimpleArray;
/**
 * Class DinamicContainer.
 * Dynamic list based on an array.
 * @author Egor Novikov
 * E-mail: e.novikov@yahoo.com
 * @version 1
 * @since 0.1
 */

public class DynamicContainer<E extends Object> extends SimpleArray<E> {
    /**
     * Position.
     */
    private int position;
    /**
     * Container.
     */
    private E[] array;
    /**
     * Amount of modification.
     */
    private int modification = 0;
    /**
     * Expected amount of modification.
     */
    private int expectedModification = 0;
    /**
     * Constructor.
     * @param size - start size.
     */
    public DynamicContainer(int size) {
        this.array = (E[]) new Object[size];
    }
    /**
     * Method adds an object to the container.
     * @param object
     */
    public void add(E object) {
        reSize();
        array[position++] = object;
        modification++;
    }
    /**
     * The method return object from container.
     * @param index - index.
     * @return - object.
     */
    public E get(int index) {
        return array[index];
    }

    public E[] getList() {
        return Arrays.copyOf(this.array, this.array.length);
    }
    /**
     * The method checks for modifications.
     * @return - true, if there were changes, else false.
     */
    private boolean change() {
        boolean result = false;
        if (expectedModification != modification) {
            result = true;
        }
        return result;
    }
    /**
     * Method increases the size of the container as necessary
     */
    private void reSize() {
        if (position >= array.length) {
            E[] newArray = (E[]) new Object[array.length * 2];
            System.arraycopy(array, 0, newArray, 0, array.length);
            this.array = newArray;
        }
    }
    /**
     * ListIterator.
     * If there were container modifications throws an error ConcurrentModificationException.
     * @return - ListIterator.
     */
    @Override
    public Iterator iterator() {
        return new Iterator<E>() {

            private int index = 0;

            @Override
            public boolean hasNext() {
                if (index == 0) {
                    expectedModification = modification;
                }
                if (change()) {
                    throw new ConcurrentModificationException();
                }
                boolean result = false;
                if (index < position) {
                    result = true;
                    index++;
                }
                return result;
            }

            @Override
            public E next() {
                if (index == 0) {
                    expectedModification = modification;
                }
                if (change()) {
                    throw new ConcurrentModificationException();
                }
                E result = null;
                if (index < position) {
                    result = array[index];
                    index++;
                } else {
                    throw new NoSuchElementException();
                }
                return result;
            }
        };
    }
}
