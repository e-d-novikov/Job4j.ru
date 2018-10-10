package b.oop.a.tracker;

import java.util.ArrayList;
import java.util.function.Consumer;
/**
 * Class MenuTracker the class describes menu items.
 *
 * @author Egor Novikov (e.novikov@yahoo.com)
 * @version 1
 * @since 14/06/2018
 */
public class MenuTracker {
    /**
     * Input.
     */
    private Input input;
    /**
     * Tracker.
     */
    private Tracker tracker;
    /**
     * User actions.
     */
    private ArrayList<UserAction> actions = new ArrayList<>();
    private Consumer<String> printItem = x -> System.out.println(x);
    /**
     * Constructor.
     * @param input - input;
     * @param tracker - tracker.
     */
    public MenuTracker(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }
    /**
     * Menu items.
     */
    public void fillAction() {
        actions.add(new AddItem(1, "Add application"));
        actions.add(new EditItem(2, "Edit application"));
        actions.add(new DeleteItem(3, "Remove application"));
        actions.add(new GetAll(4, "Current applications"));
        actions.add(new FindByID(5, "Search by ID"));
        actions.add(new FindByName(6, "Search by name"));
        actions.add(new Exit(7, "Exit"));
    }
    /**
     * Method for selecting a menu item.
     * @param key - key menu item.
     */
    public void select(int key) {
        actions.get(key - 1).execute(this.input, this.tracker);
    }
    /**
     * Menu heading.
     */
    public void show() {
        printItem.accept("Application Editor");
        printItem.accept("Please select action:");
        for (UserAction action : this.actions) {
            if (action != null) {
                printItem.accept(action.info());
            }
        }
    }
    /**
     * Class adds an application.
     */
    public class AddItem extends BaseAction {
        /**
         * Menu item for add application.
         * @param key - menu item number.
         * @param name - menu item name.
         */
        public AddItem(int key, String name) {
            super(key, name);
        }
        /**
         *
         * @param input - input.
         * @param tracker- tracker.
         */
        public void execute(Input input, Tracker tracker) {
            String name = input.ask("Please enter a name:");
            String description = input.ask("Please enter the description of the application:");
            tracker.add(name, description);
            System.out.println("Application created");
        }
    }
    /**
     * Class edit application.
     */
    public class EditItem extends BaseAction {
        /**
         * Menu item for edit application.
         * @param key - menu item number.
         * @param name - menu item name.
         */
        public EditItem(int key, String name) {
            super(key, name);
        }
        /**
         *
         * @param input - input.
         * @param tracker - tracker.
         */
        public void execute(Input input, Tracker tracker) {
            if (tracker.isEmpty()) {
                System.out.println("Edit application");
                String id = input.ask("Enter the application ID:");
                String name = input.ask("Enter the name of the application:");
                String desc = input.ask("Please enter the description of the application:");
                tracker.replace(Integer.parseInt(id), name, desc);
                System.out.println("Application updated");
            } else {
                System.out.println("Currently there are no applications available");
            }
        }
    }
    /**
     * Application delete class.
     */
    public class DeleteItem extends BaseAction {
        /**
         * Menu item for delete applications.
         * @param key - menu item number.
         * @param name - menu item name.
         */
        public DeleteItem(int key, String name) {
            super(key, name);
        }
        /**
         * Method of deleting an application.
         * @param input - input.
         * @param tracker - tracker.
         */
        public void execute(Input input, Tracker tracker) {
            if (tracker.isEmpty()) {
                System.out.println("Delete an order");
                String id = input.ask("Enter the application ID:");
                tracker.delete(Integer.parseInt(id));
            } else {
                System.out.println("Currently there are no applications available");
            }
        }
    }
    /**
     * Class of receipt of all applications.
     */
    public class GetAll extends BaseAction {
        /**
         * Menu item for get all applications.
         * @param key - menu item number.
         * @param name - menu item name.
         */
        public GetAll(int key, String name) {
            super(key, name);
        }
        /**
         * Method of receipt of all applications.
         * @param input - input.
         * @param tracker - tracker.
         */
        public void execute(Input input, Tracker tracker) {
            if (tracker.isEmpty()) {
                System.out.println("Current applications:");
                tracker.getAll();
            } else {
                System.out.println("Currently there are no applications available");
            }
        }
    }
    /**
     * Application search class by ID.
     */
    public class FindByID extends BaseAction {
        /**
         * Menu item for find by id.
         * @param key - menu item number.
         * @param name - menu item name.
         */
        public FindByID(int key, String name) {
            super(key, name);
        }

        /**
         * Method of searching for an application for ID.
         * @param input - input.
         * @param tracker - tracker.
         */
        public void execute(Input input, Tracker tracker) {
            if (tracker.isEmpty()) {
                System.out.println("Search by ID");
                String id = input.ask("Enter the application ID: ");
                tracker.findById(Integer.parseInt(id));
            } else {
                System.out.println("Currently there are no applications available");
            }
        }
    }
    /**
     * Application search class by name.
     */
    public class FindByName extends BaseAction {
        /**
         * Menu item for find by name.
         * @param key - menu item number.
         * @param name - menu item name.
         */
        public FindByName(int key, String name) {
            super(key, name);
        }
        /**
         * Method of searching for an application for name.
         * @param input - input.
         * @param tracker - tracker.
         */
        public void execute(Input input, Tracker tracker) {
            if (tracker.isEmpty()) {
                System.out.println("Search by name");
                String name = input.ask("Enter the application name: ");
                tracker.findByName(name);
            } else {
                System.out.println("Currently there are no applications available");
            }
        }
    }
    /**
     * Class to exit the application.
     */
    public class Exit extends BaseAction {
        /**
         * Menu item for exit.
         * @param key - menu item number.
         * @param name - menu item name.
         */
        public Exit(int key, String name) {
            super(key, name);
        }
        /**
         * Method to exit the application.
         * @param input - input.
         * @param tracker - tracker.
         */
        public void execute(Input input, Tracker tracker) {

        }
    }
}
