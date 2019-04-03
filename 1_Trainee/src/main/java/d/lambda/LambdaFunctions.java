package d.lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
/**
 * Class LambdaFunctions.
 *
 * @author Egor Novikov
 * E-mail: enovikovdev@gmail.com
 * @version 1
 * @since 0.1
 */
public class LambdaFunctions {
    /**
     * The method performs counting of functions in the range.
     * @param start - start;
     * @param end - end;
     * @param func - function;
     * @return - result.
     */
    public List<Double> diapason(int start, int end, Function<Double, Double> func) {
        ArrayList<Double> result = new ArrayList<>();
        for (int i = start; i <= end; i++) {
            result.add(func.apply((double) i));
        }
        return result;
    }
}
