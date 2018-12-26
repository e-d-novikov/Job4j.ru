package b.multithreading.c.synchronization.dynamic;

import java.util.*;
import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
/**
 * Class DynamicListTest.
 * @author Egor Novikov
 * E-mail: e.novikov@yahoo.com
 * @version 1$
 * @since 0.1
 */
public class DynamicListTest {

  DynamicList<Integer> test = new DynamicList<>(10);

  @Test
  public void hasNextNextSequentialInvocation() {
    test.add(1);
    test.add(2);
    Iterator iterator = test.iterator();
    assertThat(test.get(0), is(1));
    assertThat(test.get(1), is(2));
    assertThat(iterator.next(), is(1));
    assertThat(iterator.next(), is(2));
  }
}
