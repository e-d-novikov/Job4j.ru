package a.base.d.massive;

/**
 * Class BubleSort.
 *
 * @author Egor Novikov
 * E-mail: e.novikov@yahoo.com
 * @version 1$
 * @since 0.1
 */
public class BubbleSort {
  /**
   * Method realize bubbly sort.
   * @param array - array of numbers in random order;
   * @return - array of numbers by order.
   */
  public int[] sort(int[] array) {
    for (int i = array.length - 1; i > 0; i--) {
      for (int j = 0; j < i; j++) {
        if (array[j] > array[j + 1]) {
          int g = array[j];
          array[j] = array[j + 1];
          array[j + 1] = g;
        }
      }
    }
    return array;
  }
}
