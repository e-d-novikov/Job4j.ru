package d.servlet.http;

import java.util.ArrayList;

public class ValidateService {

    private static volatile ValidateService instance = new ValidateService();
    private final DbStore memory;

    public ValidateService() {
        memory = DbStore.getInstance();
    }

    public static ValidateService getInstance() {
        return instance;
    }

    public void add(User user) {
        memory.createUser(user);
    }

    public void update(User user) {
        memory.editUser(user);
    }

    public void delete(String login) {
        memory.deleteUser(login);
    }

    public ArrayList<User> findAll() {
        ArrayList<User> result = null;
        if (memory.getSize() > 0) {
            result = memory.findAllUsers();
        }
        return result;
    }

    public User findById(String login) {
        return memory.findById(login);
    }

    public boolean isCredentional(String login, String password) {
        return memory.isCredentional(login, password);
    }
}
