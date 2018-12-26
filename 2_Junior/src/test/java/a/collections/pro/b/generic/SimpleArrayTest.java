package a.collections.pro.b.generic;

import org.junit.Before;
import org.junit.Test;
import java.util.NoSuchElementException;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
/**
 * Class SimpleArrayTest tests methods from class SimpleArray.
 *
 * @author Egor Novikov
 * E-mail: e.novikov@yahoo.com
 * @version 1
 * @since 0.1
 */
public class SimpleArrayTest {

    SimpleArray<Integer> test = new SimpleArray<Integer>();

    @Before
    public void setUp() {
        test.add(1);
        test.add(2);
        test.add(3);
    }

    @Test
    public void whenIndexTwo() {
        assertThat(test.get(2), is(3));
    }

    @Test
    public void whenDeleteSecondElement() {
        test.delete(1);
        assertThat(test.get(1), is(3));
    }

    @Test
    public void whenSetSecondElement() {
        test.set(1, 5);
        assertThat(test.get(1), is(5));
    }

    @Test(expected = NoSuchElementException.class)
    public void hasNextNextSequentialInvocation() {
        assertThat(test.iterator().next(), is(1));
        assertThat(test.iterator().hasNext(), is(true));
        assertThat(test.iterator().next(), is(2));
        assertThat(test.iterator().hasNext(), is(true));
        assertThat(test.iterator().next(), is(3));
        assertThat(test.iterator().hasNext(), is(false));
        test.iterator().next();
    }
}
