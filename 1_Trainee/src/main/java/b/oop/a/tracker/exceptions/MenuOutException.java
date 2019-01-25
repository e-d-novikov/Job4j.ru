package b.oop.a.tracker.exceptions;
/**
 * Class MenuOutException.
 * Error when selecting the wrong menu item.
 * @author Egor Novikov (e.novikov@yahoo.com)
 * @version 1
 * @since 14/06/2018
 */
public class MenuOutException extends RuntimeException {
    public MenuOutException(String msg) {
        super(msg);
    }
}