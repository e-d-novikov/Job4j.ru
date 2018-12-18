package d.servlet.json;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class UserStore {

    private static volatile UserStore instance = new UserStore();
    private final ConcurrentHashMap<String, User> store = new ConcurrentHashMap<>();

    public static UserStore getInstance() {
        return instance;
    }

    public void add(User user) {
        store.put(user.getName(), user);
    }

    public ArrayList<User> getAll() {
        ArrayList<User> users = new ArrayList<>();
        for (Map.Entry<String, User> entry : store.entrySet()) {
         users.add(entry.getValue());
        }
        return users;
    }
}
