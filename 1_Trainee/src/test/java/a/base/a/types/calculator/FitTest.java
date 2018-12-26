package a.base.a.types.calculator;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
/**
 * In the class FitTest tests methods from the class Fit.
 *
 * @author Egor Novikov
 * E-mail: e.novikov@yahoo.com
 * @version 1$
 * @since 0.1
 */
public class FitTest {

  @Test
  public void manWeight() {
    Fit fit = new Fit();
    double weight = fit.manWeight(180);
    double result = ((180 - 100) * 1.15);
    assertThat(weight, is(result));
  }

  @Test
  public void womanWeight() {
    Fit fit = new Fit();
    double weight = fit.womanWeight(170);
    double result = ((170 - 110) * 1.15);
    assertThat(weight, is(result));
  }
}
