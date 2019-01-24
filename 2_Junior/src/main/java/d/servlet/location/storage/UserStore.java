package d.servlet.location;

import d.servlet.location.objects.User;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class UserStore {

    private static volatile UserStore instance = new UserStore();
    private final ConcurrentHashMap<String, User> store = new ConcurrentHashMap<>();

    public static UserStore getInstance() {
        return instance;
    }

    private String standartCase(String string) {
        String word = string.toLowerCase();
        return word.substring(0, 1).toUpperCase() + word.substring(1);
    }

    public void add(User user) {
        String name = standartCase(user.getName());
        String surname = standartCase(user.getSurname());
        store.put(user.getName(), new User(name, surname, user.getSex(), user.getCountry(), user.getRegion(), user.getCity(), user.getDescription()));
    }

    public ArrayList<User> getAll() {
        ArrayList<User> users = new ArrayList<>();
        for (Map.Entry<String, User> entry : store.entrySet()) {
         users.add(entry.getValue());
        }
        return users;
    }
}
