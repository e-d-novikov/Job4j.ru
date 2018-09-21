package b.multithreading.g.control.bomberman;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
/**
 * Class.
 * @author Egor Novikov
 * E-mail: e.novikov@yahoo.com
 * @version 1$
 * @since 0.1
 */
public class Board {
  /**
   * Игровое поле.
   */
  private final ReentrantLock[][] board = new ReentrantLock[20][20];
  /**
   * X.
   */
  private int x = 0;
  /**
   * Y.
   */
  private int y = 0;
  /**
   * Передвижение героя.
   * @param source - ячейка из которой совершается ход.
   * @param dist - ячейка в которую совершается ход.
   */
  private void move(ReentrantLock source, ReentrantLock dist) {
    source = new Cell();
    dist = new Cell(new Hero());
    System.out.println(String.valueOf(x + " " + y));
  }
  /**
   * Генерация случайного числа.
   * @return - либо -1 либо 1.
   */
  private int route() {
    int random = (int) (Math.random() * 2);
    int result = 0;
    if (random == 0) {
      result = -1;
    } else {
      result = 1;
    }
    return result;
  }
  /**
   * Заполнение игрового поля ячейками.
   */
  private void addToBoard() {
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board.length; j++) {
        if (i == 0 && j == 0) {
          board[i][j] = new Cell(new Hero());
        } else {
          board[i][j] = new Cell();
        }
      }
    }
  }
  /**
   * Поток передвижения героя.
   */
    Thread hero = new Thread() {
      @Override
      public void run() {
        while (true) {
          int z = route();
          int g = route();
          board[x][y].lock();
          try {
            /**
             * Передвижение героя в случайном порядке.
             */
            if (g > 0) {
              if (x == 0 && y == 0) {
                if (board[x + 1][y].tryLock(500, TimeUnit.MILLISECONDS)) {
                  move(board[x][y], board[x + 1][y]);
                  x++;
                } else if (board[x][y + 1].tryLock(500, TimeUnit.MILLISECONDS)) {
                  move(board[x][y], board[x][y + 1]);
                  y++;
                }
              } else if (x == board.length - 1 && y == board.length - 1) {
                if (board[x - 1][y].tryLock(500, TimeUnit.MILLISECONDS)) {
                  move(board[x][y], board[x - 1][y]);
                  x--;
                } else if (board[x][y - 1].tryLock(500, TimeUnit.MILLISECONDS)) {
                  move(board[x][y], board[x][y - 1]);
                  y--;
                }
              } else if (x == 0) {
                if (board[x + 1][y].tryLock(500, TimeUnit.MILLISECONDS)) {
                  move(board[x][y], board[x + 1][y]);
                  x++;
                } else if (board[x][y + z].tryLock(500, TimeUnit.MILLISECONDS)) {
                  move(board[x][y], board[x][y + z]);
                  y = y + z;
                }
              } else if (y == 0) {
                if (board[x][y + 1].tryLock(500, TimeUnit.MILLISECONDS)) {
                  move(board[x][y], board[x][y + 1]);
                  y++;
                } else if (board[x + z][y].tryLock(500, TimeUnit.MILLISECONDS)) {
                  move(board[x][y], board[x + z][y]);
                  x = x + z;
                }
              } else if (x == board.length) {
                if (board[x - 1][y].tryLock(500, TimeUnit.MILLISECONDS)) {
                  move(board[x][y], board[x - 1][y]);
                  x--;
                } else if (board[x][y + z].tryLock(500, TimeUnit.MILLISECONDS)) {
                  move(board[x][y], board[x][y + z]);
                  y = y + z;
                }
              } else if (y == board.length) {
                if (board[x][y - 1].tryLock(500, TimeUnit.MILLISECONDS)) {
                  move(board[x][y], board[x][y - 1]);
                  y--;
                } else if (board[x + z][y].tryLock(500, TimeUnit.MILLISECONDS)) {
                  move(board[x][y], board[x + z][y]);
                  x = x + z;
                }
              } else {
                if (board[x + z][y].tryLock(500, TimeUnit.MILLISECONDS)) {
                  move(board[x][y], board[x + z][y]);
                  x = x + z;
                } else if (board[x][y + z].tryLock(500, TimeUnit.MILLISECONDS)) {
                  move(board[x][y], board[x][y + z]);
                  y = y + z;
                }
              }
            } else if (g < 0) {
              if (x == 0 && y == 0) {
                if (board[x][y + 1].tryLock(500, TimeUnit.MILLISECONDS)) {
                  move(board[x][y], board[x][y + 1]);
                  y++;
                } else if (board[x + 1][y].tryLock(500, TimeUnit.MILLISECONDS)) {
                  move(board[x][y], board[x + 1][y]);
                  x++;
                }
              } else if (x == board.length - 1 && y == board.length - 1) {
                if (board[x][y - 1].tryLock(500, TimeUnit.MILLISECONDS)) {
                  move(board[x][y], board[x][y - 1]);
                  y--;
                } else if (board[x - 1][y].tryLock(500, TimeUnit.MILLISECONDS)) {
                  move(board[x][y], board[x - 1][y]);
                  x--;
                }
              } else if (x == 0) {
                if (board[x][y + z].tryLock(500, TimeUnit.MILLISECONDS)) {
                  move(board[x][y], board[x][y + z]);
                  y = y + z;
                } else if (board[x + 1][y].tryLock(500, TimeUnit.MILLISECONDS)) {
                  move(board[x][y], board[x + 1][y]);
                  x++;
                }
              } else if (y == 0) {
                if (board[x + z][y].tryLock(500, TimeUnit.MILLISECONDS)) {
                  move(board[x][y], board[x + z][y]);
                  x = x + z;
                } else if (board[x][y + 1].tryLock(500, TimeUnit.MILLISECONDS)) {
                  move(board[x][y], board[x][y + 1]);
                  y++;
                }
              } else if (x == board.length) {
                if (board[x][y + z].tryLock(500, TimeUnit.MILLISECONDS)) {
                  move(board[x][y], board[x][y + z]);
                  y = y + z;
                } else if (board[x - 1][y].tryLock(500, TimeUnit.MILLISECONDS)) {
                  move(board[x][y], board[x - 1][y]);
                  x--;
                }
              } else if (y == board.length) {
                if (board[x + z][y].tryLock(500, TimeUnit.MILLISECONDS)) {
                  move(board[x][y], board[x + z][y]);
                  x = x + z;
                } else if (board[x][y - 1].tryLock(500, TimeUnit.MILLISECONDS)) {
                  move(board[x][y], board[x][y - 1]);
                  y--;
                }
              } else {
                if (board[x][y + z].tryLock(500, TimeUnit.MILLISECONDS)) {
                  move(board[x][y], board[x][y + z]);
                  y = y + z;
                } else if (board[x + z][y].tryLock(500, TimeUnit.MILLISECONDS)) {
                  move(board[x][y], board[x + z][y]);
                  x = x + z;
                }
              }
            }
            Thread.sleep(1000);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
      }
    };
}
