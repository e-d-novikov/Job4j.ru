package a.collections.pro.c.list;

import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
/**
 * Class SimplyConnectedListTest tests methods from class SimplyConnectedList.
 *
 * @author Egor Novikov
 * E-mail: e.novikov@yahoo.com
 * @version 1
 * @since 0.1
 */
public class SimplyConnectedListTest {

    private SimplyConnectedList<Integer> list;

    @Before
    public void beforeTest() {
        list = new SimplyConnectedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
    }

    @Test
    public void whenAddThreeElementsThenUseGetOneResultTwo() {
        assertThat(list.get(0), is(3));
        assertThat(list.get(1), is(2));
        assertThat(list.get(2), is(1));
    }

    @Test
    public void whenAddThreeElementsThenUseGetSizeResultThree() {
        assertThat(list.getSize(), is(3));
    }

    @Test
    public void whenAddThreeElementsAndDeleteOne() {
        list.delete();
        assertThat(list.get(0), is(2));
        assertThat(list.get(1), is(1));
    }
}
