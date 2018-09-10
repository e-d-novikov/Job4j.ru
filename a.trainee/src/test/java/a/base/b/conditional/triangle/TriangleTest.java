package a.base.b.conditional.triangle;

import a.base.a.types.point.Point;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
/**
 * Class TriangleTest tests methods from class Triangle.
 *
 * @author Egor Novikov
 * E-mail: e.novikov@yahoo.com
 * @version 1$
 * @since 0.1
 */
public class TriangleTest {

  @Test
  public void whenAreaSetThreePointsThenTriangleArea() {
    Point a = new Point(8, 4);
    Point b = new Point(22, 10);
    Point c = new Point(12, 6);
    Triangle triangle = new Triangle(a, b, c);
    double result = triangle.area();
    double expected = 2.000000000000062;
    assertThat(result, is(expected));
  }
}
