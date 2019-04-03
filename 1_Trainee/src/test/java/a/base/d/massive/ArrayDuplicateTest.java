package a.base.d.massive;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
/**
 * Class ArrayDuplicateTest tests methods from class ArrayDuplicate.
 *
 * @author Egor Novikov
 * E-mail: enovikovdev@gmail.com
 * @version 1$
 * @since 0.1
 */
public class ArrayDuplicateTest {

  @Test
  public void whenThisMassiv() {
    ArrayDuplicate check = new ArrayDuplicate();
    String[] input = new String[]{"Хорошо", "Хорошо", "Отлично", "Удовлетворительно", "Отлично"};
    String[] result = new String[]{"Хорошо", "Отлично", "Удовлетворительно"};
    String[] res = check.remove(input);
    assertThat(res, is(result));
  }
}
