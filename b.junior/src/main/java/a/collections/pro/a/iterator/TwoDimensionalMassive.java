package a.collections.pro.a.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;
/**
 * Class TwoDimensionalMassive.
 *
 * @author Egor Novikov
 * E-mail: e.novikov@yahoo.com
 * @version 1
 * @since 0.1
 */
public class TwoDimensionalMassive implements Iterator<Integer> {
    /**
     * Two dimensional array.
     */
    private final int[][] array;
    /**
     * External array index.
     */
    private int external = 0;
    /**
     * Interior array index.
     */
    private int interior = 0;
    /**
     * Constructor.
     * @param array - two dimensional array.
     */
    TwoDimensionalMassive(int[][] array) {
        this.array = array;
    }
    /**
     * The method return next element, else throws an exception NoSuchElementException.
     * @return - next element.
     */
    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException("ConcurrentModificationException");
        }
        int result = array[external][interior];
        if (interior < array[external].length - 1) {
            interior++;
        } else if (interior == array[external].length - 1) {
            external++;
            interior = 0;
        }
        return result;
    }
    /**
     * The method checks for the presence of the next element in the array.
     * @return - true, if the item is, else false.
     */
    @Override
    public boolean hasNext() {
        boolean result = true;
        if (external >= array.length || interior >= array[external].length) {
            result = false;
        }
        return result;
    }
}