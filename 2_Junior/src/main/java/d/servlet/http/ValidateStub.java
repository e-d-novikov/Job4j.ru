package d.servlet.http;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ValidateStub extends ValidateService {

    private final Map<String, User> store = new HashMap<>();

    @Override
    public void add(User user) {
        store.put(user.getLogin(), user);
    }

    @Override
    public void update(User user) {
        store.put(user.getLogin(), user);
    }

    @Override
    public void delete(String login) {
        store.remove(login);
    }

    @Override
    public ArrayList<User> findAll() {
        ArrayList<User> result = new ArrayList<>();
        for (String key : store.keySet()) {
            result.add(store.get(key));
        }
        return result;
    }

    @Override
    public User findById(String login) {
        User result = null;
        if (store.containsKey(login)) {
            result = store.get(login);
        }
        return result;
    }
}
