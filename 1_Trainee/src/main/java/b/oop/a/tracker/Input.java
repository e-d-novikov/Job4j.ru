package b.oop.a.tracker;
/**
 * Interface Input describes user actions.
 * @author Egor Novikov (e.novikov@yahoo.com)
 * @version 1
 * @since 14/06/2018
 */
public interface Input {

    String ask(String question);

    int ask(String question, int[] range);
}