package a.base.d.massive;

/**
 * Class MatrixCheck.
 *
 * @author Egor Novikov
 * E-mail: enovikovdev@gmail.com
 * @version 1$
 * @since 0.1
 */
public class MatrixCheck {
  /**
   * Method check array filled true or false by diagonals.
   * @param data - array;
   * @return - if array filled true or false by diagonals, else false.
   */
  public boolean check(boolean[][] data) {
    boolean result = false;
    boolean f = data[0][0];
    int g = data.length - 1;
    for (int i = 0; i < data.length; i++) {
      if (data[i][i] == data[g][i] == f) {
        g--;
        result = true;
      } else {
        i = data.length;
        result = false;
      }
    }
    return result;
  }
}
