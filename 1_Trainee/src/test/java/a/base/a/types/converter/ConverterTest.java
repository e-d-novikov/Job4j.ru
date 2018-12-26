package a.base.a.types.converter;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * In the class CoverterTest tests methods from the class Converter.
 *
 * @author Egor Novikov
 * E-mail: e.novikov@yahoo.com
 * @version 1$
 * @since 0.1
 */
public class ConverterTest {

  @Test
  public void when70RubToEuroThen1() {
    Converter converter = new Converter(70, 60);
    int result = converter.rubleToEuro(1);
    assertThat(result, is(70));
  }

  @Test
  public void when1EuroToRubThen70() {
    Converter converter = new Converter(70, 60);
    int result = converter.euroToRuble(70);
    assertThat(result, is(1));
  }

  @Test
  public void when60RubToDollarThen1() {
    Converter converter = new Converter(70, 60);
    int result = converter.rubleToDollar(1);
    assertThat(result, is(60));
  }

  @Test
  public void when1DollarTo60Rub() {
    Converter converter = new Converter(70, 60);
    int result = converter.dollarToRuble(60);
    assertThat(result, is(1));
  }
}
