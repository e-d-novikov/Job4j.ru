package b.oop.a.tracker;
/**
 * Class Item describes application.
 * @author Egor Novikov (e.novikov@yahoo.com)
 * @version 1
 * @since 14/06/2018
 */
public class Item {
    /**
     * Application id.
     */
    private String id;
    /**
     * Application name.
     */
    private String name;
    /**
     * Application description.
     */
    private String description;
    /**
     * Date of creation.
     */
    private long create;
    /**
     * Constrictor.
     * @param name - name;
     * @param description - description;
     * @param create - date.
     */
    public Item(String name, String description, long create) {
        this.name = name;
        this.description = description;
        this.create = create;
    }
    /**
     * Method return application id.
     * @return - id.
     */
    public String getId() {
        return this.id;
    }
    /**
     * Method set application id.
     * @param id - new id.
     */
    public void setId(String id) {
        this.id = id;
    }
    /**
     * Method return application name.
     * @return - application name.
     */
    public String getName() {
        return this.name;
    }
    /**
     * Method submits an application in the form of a line.
     * @return - application.
     */
    public String toString() {
        return String.format("%s %s %s", this.id, this.name, this.description);
    }
}