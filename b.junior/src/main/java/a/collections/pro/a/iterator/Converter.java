package a.collections.pro.a.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;
/**
 * Class EvenNumbersTest tests methods from class EvenNumbers.
 *
 * @author Egor Novikov
 * E-mail: e.novikov@yahoo.com
 * @version 1
 * @since 0.1
 */
public class Converter {
    /**
     * Iterator iterator.
     * @param iterators - iterators.
     * @return - iterator
     */
    Iterator<Integer> convert(Iterator<Iterator<Integer>> iterators) {
        return new Iterator<Integer>() {
            Iterator<Integer> it;
            @Override
            public boolean hasNext() {
                boolean result = false;
                if (it == null || !it.hasNext()) {
                    while (iterators.hasNext()) {
                        it = iterators.next();
                        if (it.hasNext()) {
                            result = true;
                            break;
                        }
                    }
                }
                if (it != null && it.hasNext()) {
                    result = true;
                }
                return result;
            }

            @Override
            public Integer next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return it.next();
            }
        };
    }
}


