package a.collections.pro.d.set;

import java.util.Iterator;
import java.util.NoSuchElementException;
/**
 * Class SimpleSet.
 * Collection Set array-based.
 * @author Egor Novikov
 * E-mail: enovikovdev@gmail.com
 * @version 1
 * @since 0.1
 */
public class SimpleSet<E extends Object> implements Iterable<E> {
    /**
     * Set.
     */
    private E[] set;
    /**
     * Amount objects.
     */
    private int position = 0;
    /**
     * Constructor.
     * @param size - start size.
     */
    public SimpleSet(int size) {
        this.set = (E[]) new Object[size];
    }
    /**
     * Method adds items to the list.
     * @param data - data.
     */
    public void add(E data) {
        reSize();
        if (validate(data)) {
            set[position] = data;
            position++;
        }
    }
    /**
     * Method check for duplicates.
     * @param data - data.
     * @return - true, if validate successfully, else false.
     */
    private boolean validate(E data) {
        boolean result = true;
        if (position > 0) {
            for (int i = 0; i < position; i++) {
                if (set[i] == data) {
                    result = false;
                }
            }
        }
        return result;
    }
    /**
     * Check for duplicates.
     */
    private void reSize() {
        if (position >= set.length) {
            E[] newArray = (E[]) new Object[set.length * 2];
            System.arraycopy(set, 0, newArray, 0, set.length);
            this.set = newArray;
        }
    }
    /**
     * Iterator.
     * @return - iterator.
     */
    @Override
    public Iterator iterator() {
        return new Iterator() {
            /**
             * Index of iterator.
             */
            private int index = 0;
            /**
             * The method checks for the presence of the next element in the array.
             * @return - true, if the item is, else false.
             */
            @Override
            public boolean hasNext() {
                boolean result = false;
                if (index < position - 1 && set[index] != null) {
                    result = true;
                    index++;
                }
                return result;
            }
            /**
             * The method return next element.
             * @return - next element.
             */
            @Override
            public E next() {
                if (index >= position - 1) {
                    throw new NoSuchElementException();
                }
                E result = null;
                if (index < position - 1 && set[index] != null) {
                    result = set[index];
                    index++;
                }
                return result;
            }
        };
    }
}
