package a.base.d.massive;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
/**
 * Class MatrixCheckTest tests methods from class MatrixCheck.
 *
 * @author Egor Novikov
 * E-mail: e.novikov@yahoo.com
 * @version 1$
 * @since 0.1
 */
public class MatrixCheckTest {

  @Test
  public void whenDataMonoByTrueThenTrue() {
    MatrixCheck check = new MatrixCheck();
    boolean[][] input = new boolean[][] {
            {true, false, false, true},
            {false, true, true, false},
            {true, true, true, false},
            {true, false, false, true}
    };
    boolean result = check.check(input);
    assertThat(result, is(true));
  }

  @Test
  public void whenDataNotMonoByTrueThenFalse() {
    MatrixCheck check = new MatrixCheck();
    boolean[][] input = new boolean[][] {
            {true, true, false},
            {false, false, true},
            {true, false, true}
    };
    boolean result = check.check(input);
    assertThat(result, is(false));
  }
}
