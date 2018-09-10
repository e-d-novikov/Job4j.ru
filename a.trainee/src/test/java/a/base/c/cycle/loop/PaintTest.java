package a.base.c.cycle.loop;

import org.junit.Test;
import java.util.StringJoiner;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
/**
 * Class PaintTest tests methods from class Paint.
 *
 * @author Egor Novikov
 * E-mail: e.novikov@yahoo.com
 * @version 1$
 * @since 0.1
 */
public class PaintTest {

  @Test
  public void whenPyramidHeight4() {
    Paint paint = new Paint();
    String rst = paint.pyramid(4);
    System.out.println(rst);
    assertThat(rst,
            is(
                    new StringJoiner(System.lineSeparator(), "", System.lineSeparator())
                            .add("   ^   ")
                            .add("  ^^^  ")
                            .add(" ^^^^^ ")
                            .add("^^^^^^^")
                            .toString()
            )
    );
  }

  @Test
  public void whenPyramidHeight6() {
    Paint paint = new Paint();
    String rst = paint.pyramid(6);
    System.out.println(rst);
    assertThat(rst,
            is(
                    new StringJoiner(System.lineSeparator(), "", System.lineSeparator())
                            .add("     ^     ")
                            .add("    ^^^    ")
                            .add("   ^^^^^   ")
                            .add("  ^^^^^^^  ")
                            .add(" ^^^^^^^^^ ")
                            .add("^^^^^^^^^^^")
                            .toString()
            )
    );
  }
}
