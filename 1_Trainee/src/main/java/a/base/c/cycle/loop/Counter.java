package a.base.c.cycle.loop;

/**
 * Class Counter.
 *
 * @author Egor Novikov
 * E-mail: enovikovdev@gmail.com
 * @version 1$
 * @since 0.1
 */
public class Counter {
  /**
   * Method add calculates the sum of even numbers in the range.
   * @param start - start of range;
   * @param finish - end of range
   * @return - sum of even numbers.
   */
  public int add(int start, int finish) {
    int result = 0;
    for (int  i = start; i <= finish; i++) {
      if (i % 2 == 0) {
        result = result + i;
      }
    }
    return result;
  }
}
