package a.base.d.massive;
/**
 * Class Check.
 *
 * @author Egor Novikov
 * E-mail: enovikovdev@gmail.com
 * @version 1$
 * @since 0.1
 */
public class Check {
  /**
   * Method checks that all elements in the array are the same.
   * @param data - array;
   * @return - true, if all elements in the array are the same, else false.
   */
  public boolean mono(boolean[] data) {
    boolean result = true;
    for (int index = 0; index < data.length - 1; index++) {
      if (data[index] != data[index + 1]) {
        result = false;
      }
    }
    return result;
  }
}
