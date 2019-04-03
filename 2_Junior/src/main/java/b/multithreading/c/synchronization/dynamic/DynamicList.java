package b.multithreading.c.synchronization.dynamic;

import a.collections.pro.c.list.DynamicContainer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
/**
 * Class DynamicList.
 * @author Egor Novikov
 * E-mail: enovikovdev@gmail.com
 * @version 1$
 * @since 0.1
 */
public class DynamicList<T> implements Iterable<T> {

  private DynamicContainer<T> container;
  private ArrayList<T> list = new ArrayList<>();
  /**
   * Constructor.
   * @param size - start size.
   */
  public DynamicList(int size) {
    container = new DynamicContainer<>(size);
  }

  public synchronized void add(T object) {
    container.add(object);
  }

  public synchronized T get(int index) {
    return container.get(index);
  }

  private void copy() {
    Collections.addAll(list, container.getList());
  }

  @Override
  public synchronized Iterator iterator() {
    copy();
    return list.iterator();
  }
}
