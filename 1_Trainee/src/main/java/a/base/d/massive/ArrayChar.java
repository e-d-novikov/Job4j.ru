package a.base.d.massive;

/**
 * Class ArrayChar.
 *
 * @author Egor Novikov
 * E-mail: enovikovdev@gmail.com
 * @version 1$
 * @since 0.1
 */
public class ArrayChar {
  /**
   * Array symbols from line.
   */
  private char[] data;

  /**
   * Constructor.
   * @param line - string.
   */
  public ArrayChar(String line) {
    this.data = line.toCharArray();
  }
  /**
   * Method determines whether the string starts with a prefix.
   * @param prefix - prefix.
   * @return if the word begins with a prefix return true, else false.
   */
  public boolean startWith(String prefix) {
    boolean result = true;
    char[] value = prefix.toCharArray();
    if (value.length <= data.length) {
      for (int index = 0; index < value.length; index++) {
        if (value[index] != data[index]) {
          result = false;
        }
      }
    } else {
      result = false;
    }
    return result;
  }
}
