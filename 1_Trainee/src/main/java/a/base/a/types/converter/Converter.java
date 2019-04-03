package a.base.a.types.converter;
/**
 * The class implements the currency converter.
 *
 * @author Egor Novikov
 * E-mail: enovikovdev@gmail.com
 * @version 1$
 * @since 0.1
 */
public class Converter {
  /**
   * Value one euro.
   */
  private int euro;
  /**
   * Value one dollar.
   */
  private int dollar;
  /**
   * Constructor
   * @param euro - value one euro;
   * @param dollar - value one dollar;
   */
  public Converter(int euro, int dollar) {
    this.euro = euro;
    this.dollar = dollar;
  }
  /**
   * The method translates rubles into euros.
   * @param value - amount in rubles;
   * @return - amount of euro;
   */
  public int rubleToEuro(int value) {
    return (value * euro);
  }
  /**
   * The method translates euros into rubles.
   * @param value - amount of euro;
   * @return - amount in rubles;
   */
  public int euroToRuble(int value) {
    return (value / euro);
  }
  /**
   * The method translates rubles into dollars.
   * @param value - amount of rubles;
   * @return - amount in dollars;
   */
  public int rubleToDollar(int value) {
    return (value * dollar);
  }
  /**
   * The method translates dollars into rubles.
   * @param value - amount in dollars;
   * @return - amount of rubles;
   */
  public int dollarToRuble(int value) {
    return (value / dollar);
  }
}
