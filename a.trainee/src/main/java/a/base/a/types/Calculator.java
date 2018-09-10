package a.base.a.types;
/**
 * Class Calculator.
 * Contains the methods of the elementary calculator
 *
 * @author Egor Novikov
 * E-mail: e.novikov@yahoo.com
 * @version 1
 * @since 0.1
 */
public class Calculator {
  /**
   * result
   */
  private double result;
  /**
   * The method adds two numbers.
   * @param first - first number;
   * @param second - second number;
   */
  public void add(double first, double second) {
    this.result = first + second;
  }
  /**
   * The method subtracts from the first number the second
   * @param first - first number;
   * @param second - second number;
   */
  public void subtract(double first, double second) {
    this.result = first - second;
  }
  /**
   * The method multiple two numbers.
   * @param first - first number;
   * @param second - second number;
   */
  public void multiple(double first, double second) {
    this.result = first * second;
  }
  /**
   * The method divides the first number by the second
   * @param first - first number;
   * @param second - second number;
   */
  public void div(double first, double second) {
    this.result = first / second;
  }
  /**
   * The method returns result.
   */
  public double getResult() {
    return this.result;
  }
}
