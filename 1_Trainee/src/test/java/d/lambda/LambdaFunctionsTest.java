package d.lambda;

import org.junit.Test;
import java.util.ArrayList;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.MatcherAssert.assertThat;
/**
 * Class LambdaFunctionsTest tests method from class LambdaFunctions.
 *
 * @author Egor Novikov
 * E-mail: enovikovdev@gmail.com
 * @version 1
 * @since 0.1
 */
public class LambdaFunctionsTest {

    @Test
    public void whenLog() {
        LambdaFunctions test = new LambdaFunctions();
        ArrayList<Double> result = new ArrayList<>();
        result.add(Math.log((double) 1));
        result.add(Math.log((double) 2));
        result.add(Math.log((double) 3));
        result.add(Math.log((double) 4));
        assertThat(test.diapason(1, 4, x -> Math.log((double) x)), is(result));
    }

    @Test
    public void whenPow() {
        LambdaFunctions test = new LambdaFunctions();
        ArrayList<Double> result = new ArrayList<>();
        result.add(Math.pow(1, 2));
        result.add(Math.pow(2, 2));
        result.add(Math.pow(3, 2));
        result.add(Math.pow(4, 2));
        assertThat(test.diapason(1, 4, x -> Math.pow(x, 2)), is(result));
    }

    @Test
    public void whenSum() {
        LambdaFunctions test = new LambdaFunctions();
        ArrayList<Double> result = new ArrayList<>();
        result.add((double) 2);
        result.add((double) 3);
        result.add((double) 4);
        result.add((double) 5);
        assertThat(test.diapason(1, 4, x -> x + 1), is(result));
    }
}
