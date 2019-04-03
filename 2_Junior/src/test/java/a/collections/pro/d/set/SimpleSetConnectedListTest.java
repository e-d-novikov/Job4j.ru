package a.collections.pro.d.set;

import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
/**
 * Class SimpleSetConnectedListTest tests methods from SimpleSetConnectedList.
 * @author Egor Novikov
 * E-mail: enovikovdev@gmail.com
 * @version 1
 * @since 0.1
 */
public class SimpleSetConnectedListTest {

    SimpleSetConnectedList test = new SimpleSetConnectedList();
    Iterator iterator = test.iterator();

    @Test(expected = NoSuchElementException.class)
    public void whenHasCycle() {
        test.add(1);
        test.add(2);
        test.add(2);
        test.add(3);
        test.add(1);
        test.add(4);
        assertThat(iterator.next(), is(1));
        assertThat(iterator.next(), is(2));
        assertThat(iterator.next(), is(3));
        assertThat(iterator.next(), is(4));
        iterator.next();
    }
}
