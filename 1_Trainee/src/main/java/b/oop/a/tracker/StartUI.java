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
 * @author Egor Novikov
 * E-mail: enovikovdev@gmail.com
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
     *
     */
    private final MenuTracker menu;
    /**
     * Constructor.
     * @param input -input.
     * @param tracker- tracker.
     */
    public StartUI(final Input input, final Tracker tracker, final MenuTracker menu) {
        this.input = input;
        this.tracker = tracker;
        this.menu = menu;
    }
    /**
     * The method is responsible for displaying menu items.
     */
    public void init() {
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
        ValidateInput validateInput = new ValidateInput(new ConsoleInput());
        Tracker tracker = new Tracker();
        MenuTracker menu = new MenuTracker(validateInput, tracker);
        StartUI ui = new StartUI(validateInput, tracker, menu);
        menu.fillAction(ui);
        ui.init();
    }
}