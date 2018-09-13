package a.collections.pro.c.list;

import java.util.*;
import a.collections.pro.b.generic.SimpleArray;
import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;
/**
 * Class DinamicContainer.
 * Dynamic list based on an array.
 * @author Egor Novikov
 * E-mail: e.novikov@yahoo.com
 * @version 1
 * @since 0.1
 */
@ThreadSafe
public class DynamicContainer<E extends Object> extends SimpleArray<E> {
    /**
     * Position.
     */
    private int position;
    /**
     * Container.
     */
    @GuardedBy("this")
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
    public synchronized void add(E object) {
        reSize();
        array[position++] = object;
        modification++;
    }
    /**
     * The method return object from container.
     * @param index - index.
     * @return - object.
     */
    public synchronized E get(int index) {
        return array[index];
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
    public ListIterator<E> iterator() {
        return new ListIterator<E>() {
            private int index = 0;
            private int next = 0;
            private int previous = 0;
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
                    next++;
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
                    next++;
                } else {
                    throw new NoSuchElementException();
                }
                return result;
            }
            @Override
            public boolean hasPrevious() {
                boolean result = false;
                if (index == 0) {
                    expectedModification = modification;
                }
                if (change()) {
                    throw new ConcurrentModificationException();
                }
                index--;
                if (index >= 0) {
                    result = true;
                }
                return result;
            }
            @Override
            public E previous() {
                index--;
                if (change()) {
                    throw new ConcurrentModificationException();
                }
                E result = null;
                if (index > 0 && index < position) {
                    expectedModification = modification;
                    result = get(index - 1);
                    previous++;
                } else {
                    index++;
                    throw new NoSuchElementException();
                }
                return result;
            }
            @Override
            public int nextIndex() {
                if (index == 0) {
                    expectedModification = modification;
                }
                if (change()) {
                    throw new ConcurrentModificationException();
                }
                int result = position;
                if (index < position) {
                    result = index + 1;
                    index++;
                }
                return result;
            }
            @Override
            public int previousIndex() {
                if (index == 0) {
                    expectedModification = modification;
                }
                if (change()) {
                    throw new ConcurrentModificationException();
                }
                int result = -1;
                if (index >= 0) {
                    result = index - 1;
                    index--;
                }
                return result;
            }
            @Override
            public void remove() {
                if (index == 0) {
                    expectedModification = modification;
                }
                if (change()) {
                    throw new ConcurrentModificationException();
                }
                if (next > 0 || previous > 0) {
                    System.arraycopy(array, index, array, index - 1, position - index);
                    modification++;
                } else {
                    throw new IllegalStateException();
                }
            }
            @Override
            public void set(E object) {
                if (index == 0) {
                    expectedModification = modification;
                }
                if (change()) {
                    throw new ConcurrentModificationException();
                }
                if (index < position) {
                    array[index] = object;
                }
            }
            @Override
            public void add(E object) {
                if (index == 0) {
                    expectedModification = modification;
                }
                if (change()) {
                    throw new ConcurrentModificationException();
                }
                if (index < position) {
                    System.arraycopy(array, index, array, index + 1, position - index);
                    array[index] = object;
                    position++;
                    modification++;
                }
            }
        };
    }
}
