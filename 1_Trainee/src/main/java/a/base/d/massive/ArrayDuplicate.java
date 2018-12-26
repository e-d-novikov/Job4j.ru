package a.base.d.massive;

import java.util.Arrays;
/**
 * Class ArrayDuplicate.
 *
 * @author Egor Novikov
 * E-mail: e.novikov@yahoo.com
 * @version 1$
 * @since 0.1
 */
public class ArrayDuplicate {
  /**
   * Method removes duplicate elements from an array.
   * @param array - array;
   * @return - array without duplicate.
   */
  public String[] remove(String[] array) {
    int c = 0;
    for (int i = 0; i < array.length - 1; i++) {
      int j = i + 1;
      for (int t = j; t < array.length - 1; t++) {
        if (array[i] == array[t]) {
          for (int e =  t; e < array.length - 1; e++) {
            array[e] = array[e + 1];
          }
          c++;
        }
      }
    }
    return Arrays.copyOf(array, array.length - c);
  }
}
