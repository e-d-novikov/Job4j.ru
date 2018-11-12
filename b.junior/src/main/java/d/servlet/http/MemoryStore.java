package d.servlet.http;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

public class MemoryStore implements Store {

    private static volatile MemoryStore instance = null;
    private final ConcurrentHashMap<Integer, User> users;

    public MemoryStore() {
        users = new ConcurrentHashMap<>();
    }

    public static MemoryStore getInstance() {
        if (instance == null) {
            synchronized (MemoryStore.class) {
                if (instance == null) {
                    instance = new MemoryStore();
                }
            }
        }
        return instance;
    }

    @Override
    public void add(User user) {
        users.put(user.getId(), user);
    }

    @Override
    public void update(int id, String name, String login, String email, String date) {
        users.put(id, new User(id, name, login, email, date));
    }

    @Override
    public void delete(int id) {
        users.remove(id);
    }

    @Override
    public ArrayList<User> findAll() {
        ArrayList<User> result = new ArrayList<>();
        for (Integer id : users.keySet()) {
            result.add(users.get(id));
        }
        return result;
    }

    @Override
    public User findById(int id) {
        return users.get(id);
    }

    @Override
    public boolean availableId(int id) {
        boolean result = false;
        if (users.containsKey(id)) {
            result = true;
        }
        return result;
    }

    @Override
    public int getSize() {
        return users.size();
    }
}
