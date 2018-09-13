package a.collections.pro.c.list;

import org.junit.Before;
import org.junit.Test;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
/**
 * Class DynamicSimplyConnectedListTest tests methods from class DynamicSimplyConnectedList.
 *
 * @author Egor Novikov
 * E-mail: e.novikov@yahoo.com
 * @version 1
 * @since 0.1
 */
public class DynamicSimplyConnectedListTest {

    private DynamicSimplyConnectedList list = new DynamicSimplyConnectedList<>();
    private Iterator iterator = list.iterator();

    @Before
    public void beforeTest() {
        list.add(1);
        list.add(2);
        list.add(3);
    }

    @Test
    public void whenAddThreeElementsThenUseGetOneResultTwo() {
        assertThat(list.get(0), is(1));
        assertThat(list.get(1), is(2));
        assertThat(list.get(2), is(3));
    }

    @Test
    public void whenHasNext() {
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.hasNext(), is(false));
    }

    @Test(expected = NullPointerException.class)
    public void whenNext() {
        assertThat(iterator.next(), is(1));
        assertThat(iterator.next(), is(2));
        assertThat(iterator.next(), is(3));
        iterator.next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenHasNextModification() {
        assertThat(iterator.hasNext(), is(true));
        list.add(4);
        assertThat(iterator.hasNext(), is(false));
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenNextModification() {
        iterator.next();
        list.add(4);
        iterator.next();
    }
}
