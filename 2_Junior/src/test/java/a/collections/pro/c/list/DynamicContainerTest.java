package a.collections.pro.c.list;

import org.junit.Before;
import org.junit.Test;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
/**
 * Class DynamicContainerTest tests methods from class DynamicContainer.
 * Dynamic list based on an array.
 * @author Egor Novikov
 * E-mail: enovikovdev@gmail.com
 * @version 1
 * @since 0.1
 */
public class DynamicContainerTest {

    private DynamicContainer test = new DynamicContainer(10);
    private Iterator iterator = test.iterator();
    private Object testobj = new Object();
    private Object testobj1 = new Object();
    private Object testobj2 = new Object();

    @Before
    public void beforeTest() {
        test.add(testobj);
        test.add(testobj1);
        test.add(testobj2);
    }

    @Test
    public void whenAddThreeElementsThenUseGetOneResultThree() {
        assertThat(test.get(2), is(testobj2));
    }

    @Test
    public void whenHasNext() {
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.hasNext(), is(false));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenNext() {
        assertThat(iterator.next(), is(testobj));
        assertThat(iterator.next(), is(testobj1));
        assertThat(iterator.next(), is(testobj2));
        iterator.next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenConcurentExeptionHasNext() {
        iterator.next();
        Object testobj3 = new Object();
        test.add(testobj3);
        iterator.hasNext();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenConcurentExeptionNext() {
        iterator.next();
        Object testobj3 = new Object();
        test.add(testobj3);
        iterator.next();
    }
}
