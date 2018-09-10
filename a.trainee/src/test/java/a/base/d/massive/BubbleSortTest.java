package a.base.d.massive;

import org.junit.Test;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
/**
 * Class BubbleSortTest tests methods from class BubbleSort.
 *
 * @author Egor Novikov
 * E-mail: e.novikov@yahoo.com
 * @version 1$
 * @since 0.1
 */
public class BubbleSortTest {

  @Test
  public void whenOneFourArray() {
    BubbleSort bubble = new BubbleSort();
    int[] input = new int[] {3, 2, 1, 4};
    int[] result = bubble.sort(input);
    int[] expect = new int[] {1, 2, 3, 4};
    assertThat(result, is(expect));
  }

  @Test
  public void whenOneFiveArray() {
    BubbleSort bubble = new BubbleSort();
    int[] input = new int[] {5, 2, 3, 1, 4};
    int[] result = bubble.sort(input);
    int[] expect = new int[] {1, 2, 3, 4, 5};
    assertThat(result, is(expect));
  }

  @Test
  public void whenOneTenArray() {
    BubbleSort bubble = new BubbleSort();
    int[] input = new int[] {5, 2, 3, 1, 4, 6, 10, 8, 9, 7};
    int[] result = bubble.sort(input);
    int[] expect = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    assertThat(result, is(expect));
  }
}
