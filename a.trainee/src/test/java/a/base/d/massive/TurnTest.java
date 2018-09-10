package a.base.d.massive;

import org.junit.Test;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
/**
 * Class TurnTest tests methods from class Turn.
 *
 * @author Egor Novikov
 * E-mail: e.novikov@yahoo.com
 * @version 1$
 * @since 0.1
 */
public class TurnTest {

  @Test
  public void whenTurnArrayWithEvenAmountOfElementsThenTurnedArray() {
    Turn turner = new Turn();
    int[] input = new int[] {1, 2, 3, 4};
    int[] result = turner.turn(input);
    int[] expect = new int[] {4, 3, 2, 1};
    assertThat(result, is(expect));
  }

  @Test
  public void whenTurnArrayWithOddAmountOfElementsThenTurnedArray() {
    Turn turner = new Turn();
    int[] input = new int[] {1, 2, 3, 4, 5};
    int[] result = turner.turn(input);
    int[] expect = new int[] {5, 4, 3, 2, 1};
    assertThat(result, is(expect));
  }
}
