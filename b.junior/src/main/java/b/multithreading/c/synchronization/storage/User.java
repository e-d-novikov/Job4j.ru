package b.multithreading.c.synchronization.storage;
/**
 * Class User.
 *
 * @author Egor Novikov
 * E-mail: e.novikov@yahoo.com
 * @version 1
 * @since 0.1
 */
public class User {

    private int id;
    private int amount;

    public User(int id, int amount) {
        this.id = id;
        this.amount = amount;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }
}
