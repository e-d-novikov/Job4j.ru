package b.oop.a.tracker;
/**
 * Interface UserAction.
 * @author Egor Novikov (e.novikov@yahoo.com)
 * @version 1
 * @since 14/06/2018
 */
public interface UserAction {

    int key();

    void execute(Input input, Tracker tracker);

    String info();
}