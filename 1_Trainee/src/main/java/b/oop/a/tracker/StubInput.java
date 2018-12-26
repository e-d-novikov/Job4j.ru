package b.oop.a.tracker;

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