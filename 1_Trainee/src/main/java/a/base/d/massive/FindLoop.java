package a.base.d.massive;
/**
 * Class FindLoop.
 *
 * @author Egor Novikov
 * E-mail: enovikovdev@gmail.com
 * @version 1$
 * @since 0.1
 */
public class FindLoop {
  /**
   * Method realize searching for an element in an array.
   * @param data - array;
   * @param element - element;
   * @return - index of element, if search was successful , else -1.
   */
  public int indexOf(int[] data, int element) {
    int result = -1;
    for (int index = 0; index != data.length; index++) {
      if (data[index] == element) {
        result = index;
        break;
      }
    }
    return result;
  }
}
