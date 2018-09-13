package a.collections.pro.b.generic;
/**
 * Class SimpleArray.
 * Universal wrapper over an array.
 * @author Egor Novikov
 * E-mail: e.novikov@yahoo.com
 * @version 1
 * @since 0.1
 */
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleArray<T> implements Iterable<T> {
    /**
     * Array.
     */
    private T[] array;
    /**
     * Size
     */
    private int size = 1;
    /**
     * The method add object in array.
     * @param object - object.
     */
    public void add(T object) {
        if (size == 1) {
            array = (T[]) new Object[size];
            array[size - 1] = object;
            size++;
        } else {
            array = Arrays.copyOf(array, size);
            array[size - 1] = object;
            size++;
        }
    }
    /**
     * The method change object.
     * @param index - index;
     * @param object - object;
     * @return - true, if change successfully, else false.
     */
    public boolean set(int index, T object) {
        boolean result = false;
        if (index < size) {
            array[index] = object;
            result = true;
        }
        return result;
    }
    /**
     * The method delete object.
     * @param index - index.
     */
    public void delete(int index) {
        if (index < size) {
            System.arraycopy(this.array, index + 1, array, index, size - index - 2);
            size--;
        }
    }
    /**
     * The method return object.
     * @param index - index.
     * @return - object.
     */
    public T get(int index) {
        return array[index];
    }
    /**
     * Iterator.
     * @return - iterator.
     */
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            /**
             * Index iterator.
             */
            private int index = 0;
            /**
             * The method checks for the presence of the next element in the array.
             * @return - true, if the item is, else false.
             */
            @Override
            public boolean hasNext() {
                boolean result = false;
                if (index < size - 1) {
                    result = true;
                    index++;
                }
                return result;
            }
            /**
             * The method return next element, else throws an exception NoSuchElementException.
             * @return - next element.
             */
            @Override
            public T next() {
                T result = null;
                if (index < size - 1) {
                    throw new NoSuchElementException();
                }
                result = array[index];
                index++;
                return result;
            }
        };
    }
}
