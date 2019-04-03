package b.oop.a.tracker.inputs;

import b.oop.a.tracker.exceptions.MenuOutException;
import b.oop.a.tracker.interfaces.Input;
/**
 * Class ValidateInput checks the correctness of the entered data.
 * @author Egor Novikov
 * E-mail: enovikovdev@gmail.com
 * @version 1
 * @since 14/06/2018
 */
public class ValidateInput implements Input {

    private final Input input;

    public ValidateInput(final Input input) {
        this.input = input;
    }

    @Override
    public String ask(String question) {
        return this.input.ask(question);
    }

    public int ask(String question, int[] range) {
        boolean invalid = true;
        int value = -1;
        do {
                try {
                    value = this.input.ask(question, range);
                    invalid = false;
                } catch (MenuOutException moe) {
                    System.out.println("Please choose the right menu item");
                } catch (NumberFormatException nfe) {
                    System.out.println("To select an action, use the numbers 1 to 7");
                }
        } while (invalid);
        return value;
    }
}