package a.base.c.cycle.loop;

/**
 * Class Factorial.
 *
 * @author Egor Novikov
 * E-mail: e.novikov@yahoo.com
 * @version 1$
 * @since 0.1
 */
public class Factorial {
  /**
   * Method factorial calculate factorial of number.
   * @param number - number;
   * @return - factorial;
   */
  public int factorial(int number) {
    int result = 1;
    if (number > 0) {
      for (int i = 1; i <= number; i++) {
        result = result * i;
      }
    }
    return result;
  }
}
