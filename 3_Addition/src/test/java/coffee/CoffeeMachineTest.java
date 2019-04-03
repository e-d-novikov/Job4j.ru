package coffee;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
/**
 * The class CoffeeMachineTest tests methods from class CoffeeMachine.
 * @author Egor Novikov
 * E-mail: enovikovdev@gmail.com
 * @version 1
 * @since 0.1
 */
public class CoffeeMachineTest {

    @Test
    public void whenNote100Price28() {
        CoffeeMachine one = new CoffeeMachine();
        int[] test = new int[]{10, 10, 10, 10, 10, 10, 10, 2};
        assertThat(one.coins(100, 28), is(test));
    }

    @Test
    public void whenNote50Price25() {
        CoffeeMachine one = new CoffeeMachine();
        int[] test = new int[]{10, 10, 5};
        assertThat(one.coins(50, 25), is(test));
    }

    @Test
    public void whenNote100Price72() {
        CoffeeMachine one = new CoffeeMachine();
        int[] test = new int[]{10, 10, 5, 2, 1};
        assertThat(one.coins(100, 72), is(test));
    }
}
