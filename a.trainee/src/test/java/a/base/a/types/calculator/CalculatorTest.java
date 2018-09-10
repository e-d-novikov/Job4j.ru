package a.base.a.types.calculator;

import a.base.a.types.calculator.Calculator;
import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
/**
 * In the class CalculatorTest tests methods from the class Calculator
 * @author Egor Novikov
 * E-mail: e.novikov@yahoo.com
 * @version 1$
 * @since 0.1
 */
public class CalculatorTest {

  @Test
  public void whenAddOnePlusOneThenTwo() {
    Calculator calc = new Calculator();
    calc.add(1, 1);
    double result = calc.getResult();
    double expected = 2;
    assertThat(result, is(expected));
  }

  @Test
  public void whenSubtractingFromOneOneThenZero() {
    Calculator calc = new Calculator();
    calc.subtract(1, 1);
    double result = calc.getResult();
    double expected = 0;
    assertThat(result, is(expected));
  }

  @Test
  public void whenTwoAreMultipliedByTwoWeGetFour() {
    Calculator calc = new Calculator();
    calc.multiple(2, 2);
    double result = calc.getResult();
    double expected = 4;
    assertThat(result, is(expected));
  }

  @Test
  public void whenFourDivideByTwoWeGetTwo() {
    Calculator calc = new Calculator();
    calc.div(4, 2);
    double result = calc.getResult();
    double expected = 2;
    assertThat(result, is(expected));
  }
}
