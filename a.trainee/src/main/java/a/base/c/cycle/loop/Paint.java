package a.base.c.cycle.loop;

import java.util.function.BiPredicate;
/**
 * Class Paint paint pyramid.
 *
 * @author Egor Novikov
 * E-mail: e.novikov@yahoo.com
 * @version 1$
 * @since 0.1
 */
public class Paint {
  /**
   * Method paint pyramid.
   * @param height - height pyramid;
   * @return - pyramid
   */
  public String pyramid(int height) {
    return this.loopBy(height, 2 * height - 1, (row, column) -> row >= height - column - 1 && row + height - 1 >= column);
  }

  /**
   * Method calculate and paint pyramid.
   * @param height - height;
   * @param weight - weight;
   * @param predict - predict;
   * @return - half pyramid.
   */
  private String loopBy(int height, int weight, BiPredicate<Integer, Integer> predict) {
    StringBuilder screen = new StringBuilder();
    for (int row = 0; row != height; row++) {
      for (int column = 0; column != weight; column++) {
        if (predict.test(row, column)) {
          screen.append("^");
        } else {
          screen.append(" ");
        }
      }
      screen.append(System.lineSeparator());
    }
    return screen.toString();
  }
}
