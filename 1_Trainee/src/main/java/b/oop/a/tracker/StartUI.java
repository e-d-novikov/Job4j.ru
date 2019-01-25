package b.oop.a.tracker;

import b.oop.a.tracker.inputs.ConsoleInput;
import b.oop.a.tracker.inputs.ValidateInput;
import b.oop.a.tracker.interfaces.Input;
import b.oop.a.tracker.menu.MenuTracker;
import b.oop.a.tracker.storage.Tracker;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.SQLException;

/**
 * Application launch class.
 * @author Egor Novikov (e.novikov@yahoo.com)
 * @version 1
 * @since 14/06/2018
 */
public class StartUI {
    /**
     * Working program
     */
    private boolean working = true;
    /**
     * Input.
     */
    private final Input input;
    /**
     * Tracker.
     */
    private final Tracker tracker;
    /**
     * Constructor.
     * @param input -input.
     * @param tracker- tracker.
     */
    public StartUI(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }
    /**
     * The method is responsible for displaying menu items.
     */
    public void init() {
        MenuTracker menu = new MenuTracker(this.input, this.tracker);
        menu.fillAction(this);
        do {
            menu.show();
            menu.select(input.ask("Menu item:", menu.getNumbersAction()));
        } while (this.working);
    }

    public void stop() {
        this.working = false;
    }
    /**
     * Method launches the application.
     * @param args
     */
    public static void main(String[] args) throws SQLException, IOException, URISyntaxException {
        new StartUI(new ValidateInput(new ConsoleInput()), new Tracker()).init();
    }
}