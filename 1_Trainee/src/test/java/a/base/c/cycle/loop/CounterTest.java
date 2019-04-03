package a.base.c.cycle.loop;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
/**
 * Class CounterTest tests methods from class Counter.
 *
 * @author Egor Novikov
 * E-mail: enovikovdev@gmail.com
 * @version 1$
 * @since 0.1
 */
public class CounterTest {

  @Test
  public void whenStart1Finish10() {
    Counter test = new Counter();
    int result = test.add(1, 10);
    assertThat(result, is(30));
  }
}
