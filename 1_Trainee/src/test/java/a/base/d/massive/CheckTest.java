package a.base.d.massive;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
/**
 * Class CheckTest tests methods from class Check.
 *
 * @author Egor Novikov
 * E-mail: e.novikov@yahoo.com
 * @version 1$
 * @since 0.1
 */
public class CheckTest {

  @Test
  public void whenDataMonoByTrueThenTrue() {
    Check check = new Check();
    boolean[] input = new boolean[] {true, true, true};
    boolean result = check.mono(input);
    assertThat(result, is(true));
  }

  @Test
  public void whenDataNotMonoByTrueThenFalse() {
    Check check = new Check();
    boolean[] input = new boolean[] {false, true, false, true};
    boolean result = check.mono(input);
    assertThat(result, is(false));
  }
}
