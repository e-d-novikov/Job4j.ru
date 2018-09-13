package a.collections.pro.c.list;

import org.junit.Before;
import org.junit.Test;
import java.util.ConcurrentModificationException;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
/**
 * Class DynamicContainerTest tests methods from class DynamicContainer.
 * Dynamic list based on an array.
 * @author Egor Novikov
 * E-mail: e.novikov@yahoo.com
 * @version 1
 * @since 0.1
 */
public class DynamicContainerTest {

    private DynamicContainer test = new DynamicContainer(10);
    private ListIterator iterator = test.iterator();
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

    @Test(expected = ConcurrentModificationException.class)
    public void whenConcurentExeptionPrevious() {
        iterator.next();
        Object testobj3 = new Object();
        test.add(testobj3);
        iterator.hasPrevious();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenConcurentExeptionNextIndex() {
        iterator.next();
        Object testobj3 = new Object();
        test.add(testobj3);
        iterator.nextIndex();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenConcurentExeptionPreviousIndex() {
        iterator.next();
        Object testobj3 = new Object();
        test.add(testobj3);
        iterator.previousIndex();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenConcurentExeptionRemove() {
        iterator.next();
        Object testobj3 = new Object();
        test.add(testobj3);
        iterator.remove();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenConcurentExeptionSet() {
        iterator.next();
        Object testobj3 = new Object();
        test.add(testobj3);
        iterator.set(testobj3);
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenConcurentExeptionAdd() {
        iterator.next();
        Object testobj3 = new Object();
        test.add(testobj3);
        iterator.add(testobj3);
    }

    @Test(expected = NoSuchElementException.class)
    public void whenPrevious() {
        assertThat(iterator.next(), is(testobj));
        assertThat(iterator.next(), is(testobj1));
        assertThat(iterator.next(), is(testobj2));
        assertThat(iterator.previous(), is(testobj1));
        assertThat(iterator.previous(), is(testobj));
        iterator.previous();
    }

    @Test
    public void whenNextIndex() {
        assertThat(iterator.nextIndex(), is(1));
        assertThat(iterator.nextIndex(), is(2));
        assertThat(iterator.nextIndex(), is(3));
    }

    @Test
    public void whenPreviousIndex() {
        assertThat(iterator.nextIndex(), is(1));
        assertThat(iterator.nextIndex(), is(2));
        assertThat(iterator.nextIndex(), is(3));
        assertThat(iterator.previousIndex(), is(2));
        assertThat(iterator.previousIndex(), is(1));
        assertThat(iterator.previousIndex(), is(0));
        assertThat(iterator.previousIndex(), is(-1));
    }

    @Test(expected = IllegalStateException.class)
    public void whenRemoveIllegalStateException() {
        iterator.remove();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenRemove() {
        iterator.hasNext();
        iterator.remove();
        assertThat(test.get(0), is(testobj1));
        assertThat(test.get(1), is(testobj2));
        iterator.hasNext();
    }

    @Test(expected = NoSuchElementException.class)
    public void whenSet() {
        Object testobj3 = new Object();
        iterator.set(testobj3);
        assertThat(iterator.next(), is(testobj3));
        assertThat(iterator.next(), is(testobj1));
        assertThat(iterator.next(), is(testobj2));
        iterator.next();
    }

    @Test(expected = NoSuchElementException.class)
    public void whenAdd() {
        Object testobj3 = new Object();
        iterator.add(testobj3);
        assertThat(iterator.next(), is(testobj3));
        assertThat(iterator.next(), is(testobj));
        assertThat(iterator.next(), is(testobj1));
        assertThat(iterator.next(), is(testobj2));
        iterator.next();
    }
}
