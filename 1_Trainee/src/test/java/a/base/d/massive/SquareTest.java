package a.base.d.massive;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
/**
 * Class SquareTest tests methods from class Square.
 *
 * @author Egor Novikov
 * E-mail: enovikovdev@gmail.com
 * @version 1$
 * @since 0.1
 */
public class SquareTest {

  @Test
  public void whenBound3Then149() {
    int bound = 3;
    Square square = new Square();
    int[] rst = square.calculate(bound);
    int[] expect = new int[] {1, 4, 9};
    assertThat(rst, is(expect));
  }

  @Test
  public void whenBound5Then1491625() {
    int bound = 5;
    Square square = new Square();
    int[] rst = square.calculate(bound);
    int[] expect = new int[] {1, 4, 9, 16, 25};
    assertThat(rst, is(expect));
  }

  @Test
  public void whenBound9Then149162536496481() {
    int bound = 9;
    Square square = new Square();
    int[] rst = square.calculate(bound);
    int[] expect = new int[] {1, 4, 9, 16, 25, 36, 49, 64, 81};
    assertThat(rst, is(expect));
  }
}
