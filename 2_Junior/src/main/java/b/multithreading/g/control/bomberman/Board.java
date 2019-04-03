package b.multithreading.g.control.bomberman;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
/**
 * Class.
 * @author Egor Novikov
 * E-mail: enovikovdev@gmail.com
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
   */
  private void move() throws InterruptedException {
    board[x][y].lock();
    int z = route();
    int g = route();
    if (g > 0) {
      if (x == 0 && y == 0) {
        if (board[x + 1][y].tryLock(500, TimeUnit.MILLISECONDS)) {
          board[x][y] = new Cell();
          board[x + 1][y] = new Cell(new Hero());
          x++;
        } else if (board[x][y + 1].tryLock(500, TimeUnit.MILLISECONDS)) {
          board[x][y] = new Cell();
          board[x][y + 1] = new Cell(new Hero());
          y++;
        }
      } else if (x == board.length - 1 && y == board.length - 1) {
        if (board[x - 1][y].tryLock(500, TimeUnit.MILLISECONDS)) {
          board[x][y] = new Cell();
          board[x - 1][y] = new Cell(new Hero());
          x--;
        } else if (board[x][y - 1].tryLock(500, TimeUnit.MILLISECONDS)) {
          board[x][y] = new Cell();
          board[x][y - 1] = new Cell(new Hero());
          y--;
        }
      } else if (x == 0) {
        if (board[x + 1][y].tryLock(500, TimeUnit.MILLISECONDS)) {
          board[x][y] = new Cell();
          board[x + 1][y] = new Cell(new Hero());
          x++;
        } else if (board[x][y + z].tryLock(500, TimeUnit.MILLISECONDS)) {
          board[x][y] = new Cell();
          board[x][y + z] = new Cell(new Hero());
          y = y + z;
        }
      } else if (y == 0) {
        if (board[x][y + 1].tryLock(500, TimeUnit.MILLISECONDS)) {
          board[x][y] = new Cell();
          board[x][y + 1] = new Cell(new Hero());
          y++;
        } else if (board[x + z][y].tryLock(500, TimeUnit.MILLISECONDS)) {
          board[x][y] = new Cell();
          board[x + z][y] = new Cell(new Hero());
          x = x + z;
        }
      } else if (x == board.length) {
        if (board[x - 1][y].tryLock(500, TimeUnit.MILLISECONDS)) {
          board[x][y] = new Cell();
          board[x - 1][y] = new Cell(new Hero());
          x--;
        } else if (board[x][y + z].tryLock(500, TimeUnit.MILLISECONDS)) {
          board[x][y] = new Cell();
          board[x][y + z] = new Cell(new Hero());
          y = y + z;
        }
      } else if (y == board.length) {
        if (board[x][y - 1].tryLock(500, TimeUnit.MILLISECONDS)) {
          board[x][y] = new Cell();
          board[x][y - 1] = new Cell(new Hero());
          y--;
        } else if (board[x + z][y].tryLock(500, TimeUnit.MILLISECONDS)) {
          board[x][y] = new Cell();
          board[x + z][y] = new Cell(new Hero());
          x = x + z;
        }
      } else {
        if (board[x + z][y].tryLock(500, TimeUnit.MILLISECONDS)) {
          board[x][y] = new Cell();
          board[x + z][y] = new Cell(new Hero());
          x = x + z;
        } else if (board[x][y + z].tryLock(500, TimeUnit.MILLISECONDS)) {
          board[x][y] = new Cell();
          board[x][y + z] = new Cell(new Hero());
          y = y + z;
        }
      }
    } else if (g < 0) {
      if (x == 0 && y == 0) {
        if (board[x][y + 1].tryLock(500, TimeUnit.MILLISECONDS)) {
          board[x][y] = new Cell();
          board[x][y + 1] = new Cell(new Hero());
          y++;
        } else if (board[x + 1][y].tryLock(500, TimeUnit.MILLISECONDS)) {
          board[x][y] = new Cell();
          board[x + 1][y] = new Cell(new Hero());
          x++;
        }
      } else if (x == board.length - 1 && y == board.length - 1) {
        if (board[x][y - 1].tryLock(500, TimeUnit.MILLISECONDS)) {
          board[x][y] = new Cell();
          board[x][y - 1] = new Cell(new Hero());
          y--;
        } else if (board[x - 1][y].tryLock(500, TimeUnit.MILLISECONDS)) {
          board[x][y] = new Cell();
          board[x - 1][y] = new Cell(new Hero());
          x--;
        }
      } else if (x == 0) {
        if (board[x][y + z].tryLock(500, TimeUnit.MILLISECONDS)) {
          board[x][y] = new Cell();
          board[x][y + z] = new Cell(new Hero());
          y = y + z;
        } else if (board[x + 1][y].tryLock(500, TimeUnit.MILLISECONDS)) {
          board[x][y] = new Cell();
          board[x + 1][y] = new Cell(new Hero());
          x++;
        }
      } else if (y == 0) {
        if (board[x + z][y].tryLock(500, TimeUnit.MILLISECONDS)) {
          board[x][y] = new Cell();
          board[x + z][y] = new Cell(new Hero());
          x = x + z;
        } else if (board[x][y + 1].tryLock(500, TimeUnit.MILLISECONDS)) {
          board[x][y] = new Cell();
          board[x][y + 1] = new Cell(new Hero());
          y++;
        }
      } else if (x == board.length) {
        if (board[x][y + z].tryLock(500, TimeUnit.MILLISECONDS)) {
          board[x][y] = new Cell();
          board[x][y + z] = new Cell(new Hero());
          y = y + z;
        } else if (board[x - 1][y].tryLock(500, TimeUnit.MILLISECONDS)) {
          board[x][y] = new Cell();
          board[x - 1][y] = new Cell(new Hero());
          x--;
        }
      } else if (y == board.length) {
        if (board[x + z][y].tryLock(500, TimeUnit.MILLISECONDS)) {
          board[x][y] = new Cell();
          board[x + z][y] = new Cell(new Hero());
          x = x + z;
        } else if (board[x][y - 1].tryLock(500, TimeUnit.MILLISECONDS)) {
          board[x][y] = new Cell();
          board[x][y - 1] = new Cell(new Hero());
          y--;
        }
      } else {
        if (board[x][y + z].tryLock(500, TimeUnit.MILLISECONDS)) {
          board[x][y] = new Cell();
          board[x][y + z] = new Cell(new Hero());
          y = y + z;
        } else if (board[x + z][y].tryLock(500, TimeUnit.MILLISECONDS)) {
          board[x][y] = new Cell();
          board[x + z][y] = new Cell(new Hero());
          x = x + z;
        }
      }
    }
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
    private Thread hero = new Thread() {
      @Override
      public void run() {
        while (true) {
          try {
            /**
             * Передвижение героя в случайном порядке.
             */
            move();
            Thread.sleep(1000);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
      }
    };
}
