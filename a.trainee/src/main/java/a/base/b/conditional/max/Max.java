package a.base.b.conditional.max;
/**
 * Class Max calculate maximum of numbers.
 *
 * @author Egor Novikov
 * E-mail: e.novikov@yahoo.com
 * @version 1$
 * @since 0.1
 */
public class Max {
  /**
   * Method calculate maximum of two numbers.
   * @param first - first number;
   * @param second - second number;
   * @return - greater of two;
   */
  public int max(int first, int second) {
    return first > second ? first : second;
  }
  /**
   * Method calculate maximum of three numbers.
   * @param first - first number;
   * @param second - second number;
   * @param third - third number;
   * @return - greater of three;
   */
  public int maxThree(int first, int second, int third) {
    return this.max((this.max(first, second)), third);
  }
}
