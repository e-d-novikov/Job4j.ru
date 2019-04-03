package a.base.b.conditional.max;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
/**
 * Class MaxTest tests methods from Max class.
 *
 * @author Egor Novikov
 * E-mail: enovikovdev@gmail.com
 * @version 1$
 * @since 0.1
 */
public class MaxTest {

  @Test
  public void whenFirstLessSecond() {
    Max maximum = new Max();
    int result = maximum.max(4, 2);
    assertThat(result, is(4));
  }

  @Test
  public void whenFirstLessSecondLessThird() {
    Max maximum = new Max();
    int result = maximum.maxThree(4, 2, 6);
    assertThat(result, is(6));
  }
}
