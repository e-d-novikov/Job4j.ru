package b.multithreading.g.control.bomberman;

import java.util.concurrent.locks.ReentrantLock;
/**
 * Класс ячейки.
 * @author Egor Novikov
 * E-mail: enovikovdev@gmail.com
 * @version 1$
 * @since 0.1
 */
public class Cell extends ReentrantLock {

  private final Hero object;

  public Cell() {
    this.object = null;
  }

  public Cell(Hero hero) {
  this.object = hero;
  }
}
