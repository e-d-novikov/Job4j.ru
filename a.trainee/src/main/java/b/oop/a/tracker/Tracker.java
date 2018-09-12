package b.oop.a.tracker;

import java.util.ArrayList;
import java.util.Random;
import java.util.function.Predicate;

/**
 * Class Tracker keeps applications, and has methods for editing.
 * @author Egor Novikov (e.novikov@yahoo.com)
 * @version 1.0
 * @since 14/06/2018
 */
public class Tracker {
    /**
     * Array for storing applications.
     */
    private final ArrayList<Item> items = new ArrayList<>();
    /**
     * The cell pointer for the new application.
     */
    private int position = 0;
    /**
     * Random number.
     */
    private static final Random RN = new Random();
    /**
     * The method realizing the addition of an application to the repository.
     * @param item - new application.
     */
    private Predicate<Item> predicate = x -> x != null;

    public Item add(Item item) {
        item.setId(this.generateId());
        items.add(item);
        position++;
        return item;
    }
    /**
     * Method modifies the application in the repository.
     * @param id - ID.
     * @param newItem - updated application.
     */
    public void replace(String id, Item newItem) {
        for (Item item : items) {
            if (item.getId().equals(id)) {
                newItem.setId(id);
                items.set(items.indexOf(item), newItem);
                break;
            }
        }
    }
    /**
     * Method that realizes the removal of an application from the repository.
     * @param id - ID.
     */
    public boolean delete(String id) {
        boolean result = false;
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getId().equals(id)) {
                items.remove(i);
                result = true;
            }
        }
        return result;
    }
    /**
     * Method generates a unique key for the application.
     * @return - unique key.
     */
    private String generateId() {
        return String.valueOf(System.currentTimeMillis() + RN.nextInt());
    }
    /**
     * The method searches for an application by ID.
     * @param id - ID.
     * @return - new unique key.
     */
    public Item findById(String id) {
        Item result = null;
        for (Item item : items) {
            if (predicate.test(item) && item.getId().equals(id)) {
                result = item;
                break;
            }
        }
        return result;
    }
    /**
     * Method returns a list of orders by name.
     * @param name - name of application.
     * @return - list of applications.
     */
    public ArrayList<Item> findByName(String name) {
        ArrayList<Item> result = new ArrayList<>();
        for (Item item : items) {
            if (item.getName().equals(name)) {
                result.add(item);
            }
        }
        return result;
    }
    /**
     * Method receives a list of all applications.
     * @return lost of application.
     */
    public ArrayList<Item> getAll() {
        return this.items;
    }
    /**
     * The method is responsible for the availability of applications.
     * @return - true, if there are applications, else false.
     */
    boolean isEmpty() {
        return position > 0;
    }
}