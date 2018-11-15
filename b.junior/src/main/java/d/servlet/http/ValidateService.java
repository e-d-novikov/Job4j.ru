package d.servlet.http;

import java.util.ArrayList;

public class ValidateService {

    private static volatile ValidateService instance = new ValidateService();
    private final Store memory;

    public ValidateService() {
        memory = DbStore.getInstance();
    }

    public static ValidateService getInstance() {
        return instance;
    }

    public void add(User user) {
        if (!memory.availableId(user.getId())) {
            memory.add(user);
            System.out.println("Пользователь добавлен");
        } else {
            System.out.println("Пользователь с таким ID уже существует");
        }
    }

    public void update(User user) {
        if (memory.availableId(user.getId())) {
            memory.update(user);
            System.out.println("Пользователь обновлен");
        } else {
            System.out.println("Пользователя с таким ID не существует");
        }
    }

    public void delete(int id) {
        if (memory.availableId(id)) {
            memory.delete(id);
            System.out.println("Пользователь удален");
        } else {
            System.out.println("Пользователя с таким ID не существует");
        }
    }

    public ArrayList<User> findAll() {
        ArrayList<User> result = null;
        if (memory.getSize() > 0) {
            result = memory.findAll();
        }
        return result;
    }

    public User findById(int id) {
        User result = null;
        if (memory.availableId(id)) {
            result = (User) memory.findById(id);
        }
        return result;
    }
}
