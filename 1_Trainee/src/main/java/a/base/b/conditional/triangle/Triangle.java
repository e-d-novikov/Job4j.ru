package a.base.b.conditional.triangle;

import a.base.a.types.point.Point;
/**
 * Class Triangle calculate area triangle on coordinate points.
 *
 * @author Egor Novikov
 * E-mail: enovikovdev@gmail.com
 * @version 1$
 * @since 0.1
 */
public class Triangle {
  /**
   * Point a;
   */
  private Point a;
  /**
   * Point b;
   */
  private Point b;
  /**
   * Point c;
   */
  private Point c;
  /**
   * Constructor
   * @param a - Point a;
   * @param b - Point b;
   * @param c - Point c;
   */
  public Triangle(Point a, Point b, Point c) {
    this.a = a;
    this.b = b;
    this.c = c;
  }
  /**
   * Method calculates a half -perimeter to the lengths of the sides.
   * @param ab - distance between points a and b;
   * @param ac - distance between points a and c;
   * @param bc - distance between points b and c;
   * @return - perimeter.
   */
  public double period(double ab, double ac, double bc) {
    return (ab + ac + bc) / 2;
  }
  /**
   * Methot calculate area triangle.
   * @return - return area, if triangle exist else -1, if there is no triangle;
   */
  public double area() {
    double rsl = -1;
    double ab = this.a.distanceTo(this.b);
    double ac = this.a.distanceTo(this.c);
    double bc = this.b.distanceTo(this.c);
    double p = this.period(ab, ac, bc);
    if (this.exist(ab, ac, bc)) {
      rsl =  Math.sqrt(p * (p - ab) * (p - ac) * (p - bc));
    }
    return rsl;
  }
  /**
   * Method checks whether it is possible to construct a triangle with such lengths of sides.
   * @param ab - length from point a to b;
   * @param ac - length from point a to c;
   * @param bc - length from point b to c;
   * @return - true, if perhaps, and false if impossible.
   */
  private boolean exist(double ab, double ac, double bc) {
    return (((ab + ac) > bc) && ((ac + bc) > ab) && ((bc + ab) > ac));
  }
}
