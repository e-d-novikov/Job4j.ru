package a.base.a.types.point;
/**
 * Class Point describes a point in the coordinate system.
 *
 * @author Egor Novikov
 * E-mail: enovikovdev@gmail.com
 * @version 1$
 * @since 0.1
 */
public class Point {
  /**
   * Coordinate X.
   */
  private int x;
  /**
   * Coordinate Y.
   */
  private int y;
  /**
   * Constructor.
   * @param x - Coordinate X.
   * @param y - Coordinate Y.
   */
  public  Point(int x, int y) {
    this.x = x;
    this.y = y;
  }
  /**
   * Method calculates distance between points.
   * @param that - second point;
   * @return distance.
   */
  public double distanceTo(Point that) {
    Point a = this;
    Point b = that;
    int x1 = a.x;
    int y1 = a.y;
    int x2 = b.x;
    int y2 = b.y;
    double result = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    return result;
  }
  /**
   * Method displays on the screen distance betwenn two points.
   * @param args - no args.
   */
  public static void main(String[] args) {
    Point a = new Point(8, 4);
    Point b = new Point(22, 10);
    System.out.println(a.distanceTo(b));
  }
}
