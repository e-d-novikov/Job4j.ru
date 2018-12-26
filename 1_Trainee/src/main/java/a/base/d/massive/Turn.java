package a.base.d.massive;
/**
 * Class Turn.
 *
 * @author Egor Novikov
 * E-mail: e.novikov@yahoo.com
 * @version 1$
 * @since 0.1
 */
public class Turn {
  /**
   * Method returns an array in reverse order.
   * @param array - array;
   * @return - array in reverse order.
   */
  public int[] turn(int[] array) {
    int firstIndex = 0;
    int lastIndex = array.length - 1;
    for (int index = 0; index < (array.length / 2); index++) {
      int first = array[firstIndex];
      int srcond = array[lastIndex];
      array[lastIndex] = first;
      array[firstIndex] = srcond;
      firstIndex++;
      lastIndex--;
    }
    return array;
  }
}
