package b.multithreading.c.synchronization.storage;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;
/**
 * Class UserStorage.
 *
 * @author Egor Novikov
 * E-mail: e.novikov@yahoo.com
 * @version 1
 * @since 0.1
 */
@ThreadSafe
public class UserStorage {

    @GuardedBy("this")
    private User[] storage;
    private int size;

    public UserStorage(int size) {
        this.storage = new User[size];
    }

    public synchronized void add(User user) {
        reSize();
        storage[size] = user;
        size++;
    }

    public synchronized void update(User user) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getId() == user.getId()) {
                storage[i] = user;
            }
        }
    }

    public synchronized void delete(int id) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getId() == id) {
                System.arraycopy(storage, i + 1, storage, i, storage.length - i - 1);
                size--;
            }
        }
    }

    public synchronized void transfer(int fromId, int toId, int amount) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getId() == fromId) {
                if (storage[i].getAmount() >= amount) {
                    storage[i].setAmount(storage[i].getAmount() - amount);
                }
            }
        }
        for (int i = 0; i < size; i++) {
            if (storage[i].getId() == toId) {
                storage[i].setAmount(storage[i].getAmount() + amount);
            }
        }
    }

    public int getUserAmountFromId(int id) {
        int result = -1;
        for (int i = 0; i < size; i++) {
            if (storage[i].getId() == id) {
                result = storage[i].getAmount();
            }
        }
        return result;
    }

    private void reSize() {
        if (size >= storage.length) {
            User[] newArray = new User[storage.length * 2];
            System.arraycopy(storage, 0, newArray, 0, storage.length);
            this.storage = newArray;
        }
    }
}
