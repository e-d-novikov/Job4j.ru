package a.base.c.cycle.loop;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
/**
 * Class BoardTest tests method chessBoard from class Board.
 *
 * @author Egor Novikov
 * E-mail: enovikovdev@gmail.com
 * @version 1$
 * @since 0.1
 */
public class BoardTest {
  @Test
  public void when3x3() {
    Board test = new Board();
    String result = test.chessBoard(3, 3);
    String ln = System.lineSeparator();
    assertThat(result, is(String.format("X X%s X %sX X%s", ln, ln, ln)));
  }
  @Test
  public void when5x4() {
    Board test = new Board();
    String result = test.chessBoard(5, 4);
    String ln = System.lineSeparator();
    assertThat(result, is(String.format("X X X%s X X %sX X X%s X X %s", ln, ln, ln, ln)));
  }
}
