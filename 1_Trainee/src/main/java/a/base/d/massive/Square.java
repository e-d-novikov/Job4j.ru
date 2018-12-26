package a.base.d.massive;
/**
 * Class Square.
 *
 * @author Egor Novikov
 * E-mail: e.novikov@yahoo.com
 * @version 1$
 * @since 0.1
 */
public class Square {
  /**
   * Method fills the array with numbers squared.
   * @param bound - last number;
   * @return - array of squares of numbers.
   */
  public int[] calculate(int bound) {
    int[] rst = new int[bound];
    int j = 0;
    for (int i = 1; i <= bound; i++) {
      rst[j] = i * i;
      j++;
    }
    return rst;
  }
}
