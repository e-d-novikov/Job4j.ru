package a.base.d.massive;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
/**
 * Class FindLoopTest tests methods from class FindLoop.
 *
 * @author Egor Novikov
 * E-mail: e.novikov@yahoo.com
 * @version 1$
 * @since 0.1
 */
public class FindLoopTest {

  @Test
  public void whenArrayHasLengh5Then0() {
    FindLoop find = new FindLoop();
    int[] input = new int[] {5, 10, 3};
    int value = 5;
    int result = find.indexOf(input, value);
    int expect = 0;
    assertThat(result, is(expect));
  }

  @Test
  public void whenArrayHasLengh16Then3() {
    FindLoop find = new FindLoop();
    int[] input = new int[] {15, 22, 9, 16};
    int value = 16;
    int result = find.indexOf(input, value);
    int expect = 3;
    assertThat(result, is(expect));
  }

  @Test
  public void whenArrayHasLengh45Thenmin1() {
    FindLoop find = new FindLoop();
    int[] input = new int[] {8, 6, 16, 48, 94, 55};
    int value = 45;
    int result = find.indexOf(input, value);
    int expect = -1;
    assertThat(result, is(expect));
  }
}
