package b.multithreading.c.synchronization.dynamic;

import a.collections.pro.c.list.DynamicContainer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
/**
 * Class DynamicList.
 * @author Egor Novikov
 * E-mail: e.novikov@yahoo.com
 * @version 1$
 * @since 0.1
 */
public class DynamicList<T> extends DynamicContainer<T> {

  private T[] iterator;

  private ArrayList<T> list = new ArrayList<>();
  /**
   * Constructor.
   * @param size - start size.
   */
  public DynamicList(int size) {
    super(size);
  }

  @Override
  public synchronized void add(T object) {
    super.add(object);
  }

  @Override
  public synchronized T get(int index) {
    return super.get(index);
  }

  private void copy() {
    Collections.addAll(list, super.getList());
  }

  @Override
  public synchronized Iterator iterator() {
    copy();
    return list.iterator();
  }
}
