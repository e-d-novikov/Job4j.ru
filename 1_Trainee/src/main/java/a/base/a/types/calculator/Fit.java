package a.base.a.types.calculator;
/**
 * Class Fit calculates the ideal weight for a man and a woman.
 *
 * @author Egor Novikov
 * E-mail: enovikovdev@gmail.com
 * @version 1$
 * @since 0.1
 */
public class Fit {
  /**
   * The method calculates the ideal weight for a man.
   * @param height growth;
   * @return ideal weight.
   */
  public double manWeight(double height) {
    return ((height - 100) * 1.15);
  }
  /**
   * The method calculates the ideal weight for a woman.
   * @param height growth;
   * @return ideal weight.
   */
  public double womanWeight(double height) {
    return ((height - 110) * 1.15);
  }
}
