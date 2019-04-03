package a.collections.pro.a.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;
/**
 * Class EvenNumbers.
 *
 * @author Egor Novikov
 * E-mail: enovikovdev@gmail.com
 * @version 1
 * @since 0.1
 */
public class EvenNumbers implements Iterator {
    /**
     * Array with numbers.
     */
    private int[] array;
    /**
     * Index iterator.
     */
    private int index = 0;

    /**
     * Constructor.
     * @param array - Array with numbers.
     */
    EvenNumbers(int[] array) {
        this.array = array;
    }
    /**
     * The method return next even number, else throws an exception NoSuchElementException.
     * @return - next even number.
     */
    @Override
    public Object next() {
        int result = 0;
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        result = array[index];
        index++;
        return result;
    }
    /**
     * The method checks for the presence of the next element in the array.
     * @return - true, if the item is, else false.
     */
    @Override
    public boolean hasNext() {
        boolean result = false;
        for (int i = index; i < array.length; i++) {
            if (array[i] % 2 == 0) {
                result = true;
                index = i;
                break;
            }
        }
        return result;
    }
}
