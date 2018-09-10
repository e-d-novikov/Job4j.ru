package a.base.c.cycle.loop;
/**
 * Class Board.
 *
 * @author Egor Novikov
 * E-mail: e.novikov@yahoo.com
 * @version 1$
 * @since 0.1
 */
public class Board {
  /**
   * Method print chess board.
   * @param width - number of cells in length;
   * @param height - number of cells in width;
   * @return - chess board.
   */
  public String chessBoard(int width, int height) {
    StringBuilder screen = new StringBuilder();
    String ln = System.lineSeparator();
    int i = 0;
    int j = 0;
    for (j = 0; j < height; j++) {
      for (i = 0; i < width; i++) {
        if ((i + j) % 2 == 0) {
          screen.append("X");
        } else {
          screen.append(" ");
        }
      }
      screen.append(ln);
    }
    return screen.toString();
  }
}
