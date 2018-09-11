package b.oop.a.tracker;
/**
 * Application launch class.
 * @author Egor Novikov (e.novikov@yahoo.com)
 * @version 1
 * @since 14/06/2018
 */
public class StartUI {
    /**
     * Array for storing menu items.
     */
    private int[] ranges = new int[]{1, 2, 3, 4, 5, 6, 7};
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
        menu.fillAction();
        do {
            menu.show();
            menu.select(input.ask("Пункт меню:", ranges));
        } while (!"да".equals(this.input.ask("Закончить работу с редактором? да/нет")));
    }
    /**
     * Method launches the application.
     * @param args
     */
    public static void main(String[] args) {
        new StartUI(new ValidateInput(new ConsoleInput()), new Tracker()).init();
    }
}