package d.servlet.http;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

public enum MemoryStore implements Store<User> {

    INSTANCE;
    private final ConcurrentHashMap<Integer, User> users = new ConcurrentHashMap<>();

    @Override
    public void add(User user) {
        users.put(user.getId(), user);
    }

    @Override
    public void update(User user) {
        users.put(user.getId(), user);
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

    @Override
    public boolean isCredentional(String login, String password) {
        return false;
    }
}
