package a.collections.pro.c.list;

import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
/**
 * Class ClosedListTest tests methods from ClosedList.
 * @author Egor Novikov
 * E-mail: enovikovdev@gmail.com
 * @version 1
 * @since 0.1
 */
public class ClosedListTest {

    ClosedList test = new ClosedList();
    Node first = new Node(1);
    Node two = new Node(2);
    Node third = new Node(3);
    Node four = new Node(4);

    @Test
    public void whenHasCycle() {
        first.next = two;
        two.next = third;
        third.next = four;
        four.next = first;
        assertThat(test.hasCycle(first), is(true));
    }

    @Test
    public void whenHasCycleCentre() {
        first.next = two;
        two.next = third;
        third.next = two;
        four.next = null;
        assertThat(test.hasCycle(first), is(true));
    }

    @Test
    public void whenHasNotCycle() {
        first.next = two;
        two.next = third;
        third.next = four;
        four.next = null;
        assertThat(test.hasCycle(first), is(false));
    }
}
