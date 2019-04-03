package a.base.d.massive;

/**
 * Class Matrix.
 *
 * @author Egor Novikov
 * E-mail: enovikovdev@gmail.com
 * @version 1$
 * @since 0.1
 */
public class Matrix {
  /**
   * Method create multiplication table.
   * @param size - size multiplication table;
   * @return - multiplication table.
   */
  public int[][] multiple(int size) {
    int[][] table = new int[size][size];
    for (int i = 0; i < size; i++) {
      for (int j = 0; j < size; j++) {
        table[i][j] = (i + 1) * (j + 1);
      }
    }
    return table;
  }
}
