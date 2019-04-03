package a.base.d.massive;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
/**
 * Class ArrayCharTest tests methods from ArrayChar.
 *
 * @author Egor Novikov
 * E-mail: enovikovdev@gmail.com
 * @version 1$
 * @since 0.1
 */
public class ArrayCharTest {

  @Test
  public void whenStartWithPrefixThenTrue() {
    ArrayChar word = new ArrayChar("Hello");
    boolean result = word.startWith("He");
    assertThat(result, is(true));
  }

  @Test
  public void whenNotStartWithPrefixThenFalse() {
    ArrayChar word = new ArrayChar("Hello");
    boolean result = word.startWith("Hi");
    assertThat(result, is(false));
  }
}
