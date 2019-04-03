package b.oop.a.tracker.inputs;

import b.oop.a.tracker.interfaces.Input;
/**
 * Class StubInput.
 * @author Egor Novikov
 * E-mail: enovikovdev@gmail.com
 * @version 1
 * @since 14/06/2018
 */
public class StubInput implements Input {
    /**
     * Contains a sequence of responses from the user.
     */
    private final String[] value;
    /**
     * Number of method ask calls.
     */
    private int position;
    /**
     * Contructor.
     * @param value - menu items.
     */
    public StubInput(final String[] value) {
        this.value = value;
    }
    /**
     * User response.
     */
    @Override
    public String ask(String question) {
        return this.value[this.position++];
    }

    public int ask(String question, int[] range) {
        return Integer.parseInt(this.ask(question));
    }
}