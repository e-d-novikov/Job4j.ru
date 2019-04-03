package a.collections.pro.a.iterator;

import org.junit.Test;
import java.util.Iterator;
import java.util.NoSuchElementException;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.MatcherAssert.assertThat;
/**
 * Class TwoDimensionalMassiveTest tests methods from class TwoDimensionalMassive.
 *
 * @author Egor Novikov
 * E-mail: enovikovdev@gmail.com
 * @version 1
 * @since 0.1
 */
public class TwoDimensionalMassiveTest {

    private Iterator<Integer> it;

    @Test
    public void testsThatNextMethodDoesntDependsOnPriorHasNextInvocationOne() {
        it = new TwoDimensionalMassive(new int[][]{{1}, {3, 4}, {7}});
        assertThat(it.next(), is(1));
        assertThat(it.next(), is(3));
        assertThat(it.next(), is(4));
        assertThat(it.next(), is(7));
    }

    @Test
    public void sequentialHasNextInvocationDoesntAffectRetrievalOrderOne() {
        it = new TwoDimensionalMassive(new int[][]{{1}, {3, 4}, {7}});
        assertThat(it.hasNext(), is(true));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(1));
        assertThat(it.next(), is(3));
        assertThat(it.next(), is(4));
        assertThat(it.next(), is(7));
    }

    @Test
    public void hasNextNextSequentialInvocationOne() {
        it = new TwoDimensionalMassive(new int[][]{{1}, {3, 4}, {7}});
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(1));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(3));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(4));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(7));
        assertThat(it.hasNext(), is(false));
    }

    @Test
    public void hasNextNextSequentialInvocation() {
        it = new TwoDimensionalMassive(new int[][]{{1, 2, 3}, {4, 5, 6}});
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(1));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(2));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(3));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(4));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(5));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(6));
        assertThat(it.hasNext(), is(false));
    }

    @Test
    public void testsThatNextMethodDoesntDependsOnPriorHasNextInvocation() {
        it = new TwoDimensionalMassive(new int[][]{{1, 2, 3}, {4, 5, 6}});
        assertThat(it.next(), is(1));
        assertThat(it.next(), is(2));
        assertThat(it.next(), is(3));
        assertThat(it.next(), is(4));
        assertThat(it.next(), is(5));
        assertThat(it.next(), is(6));
    }

    @Test
    public void sequentialHasNextInvocationDoesntAffectRetrievalOrder() {
        it = new TwoDimensionalMassive(new int[][]{{1, 2, 3}, {4, 5, 6}});
        assertThat(it.hasNext(), is(true));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(1));
        assertThat(it.next(), is(2));
        assertThat(it.next(), is(3));
        assertThat(it.next(), is(4));
        assertThat(it.next(), is(5));
        assertThat(it.next(), is(6));
    }

    @Test(expected = NoSuchElementException.class)
    public void shoulThrowNoSuchElementException() {
        it = new TwoDimensionalMassive(new int[][]{});
        it.next();
    }
}