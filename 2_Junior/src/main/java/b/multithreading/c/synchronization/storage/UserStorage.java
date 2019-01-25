package b.multithreading.c.synchronization.storage;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;
import java.util.ArrayList;
/**
 * Class UserStorage.
 * @author Egor Novikov
 * E-mail: e.novikov@yahoo.com
 * @version 1
 * @since 0.1
 */
@ThreadSafe
public class UserStorage {

    @GuardedBy("this")
    private final ArrayList<User> storage;

    public UserStorage() {
        storage = new ArrayList<>();
    }

    public synchronized void add(User user) {
        storage.add(user);
    }

    public synchronized void update(User user) {
        for (int i = 0; i < storage.size(); i++) {
            if (storage.get(i).getId() == user.getId()) {
                storage.set(i, user);
            }
        }
    }

    public synchronized void delete(int id) {
        for (int i = 0; i < storage.size(); i++) {
            if (storage.get(i).getId() == id) {
                storage.remove(i);
            }
        }
    }

    public synchronized void transfer(int fromId, int toId, int amount) {
        for (int i = 0; i < storage.size(); i++) {
            if (storage.get(i).getId() == fromId) {
                if (storage.get(i).getAmount() >= amount) {
                    storage.set(i, new User(fromId, storage.get(i).getAmount() - amount));
                }
            }
        }
        for (int i = 0; i < storage.size(); i++) {
            if (storage.get(i).getId() == toId) {
                storage.set(i, new User(toId, storage.get(i).getAmount() + amount));
            }
        }
    }

    public synchronized int getUserAmountFromId(int id) {
        int result = -1;
        for (int i = 0; i < storage.size(); i++) {
            if (storage.get(i).getId() == id) {
                result = storage.get(i).getAmount();
            }
        }
        return result;
    }
}
