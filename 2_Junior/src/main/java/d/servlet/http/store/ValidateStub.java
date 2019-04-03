package d.servlet.http.store;

import d.servlet.http.object.User;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
/**
 * ValidateStub хранилиже пользователей на базе Map.
 * @author Egor Novikov
 * E-mail: enovikovdev@gmail.com
 * @version 1$
 * @since 0.1
 */
public class ValidateStub extends ValidateService {
    /**
     * Хранилиже пользователей на базе Map.
     */
    private final Map<String, User> store = new HashMap<>();
    /**
     * Метод добавляет пользователя в хранилище.
     * @param user - пользователь.
     */
    @Override
    public void add(User user) {
        store.put(user.getLogin(), user);
    }
    /**
     * Метод обновляет данные пользователя.
     * @param user - пользователь.
     */
    @Override
    public void update(User user) {
        store.put(user.getLogin(), user);
    }
    /**
     * Метод удаляет пользователя из хранилища.
     * @param login - логин.
     */
    @Override
    public void delete(String login) {
        store.remove(login);
    }
    /**
     * Метод возвращает список всех пользователей.
     * @return - ArrayList пользователей.
     */
    @Override
    public ArrayList<User> findAll() {
        ArrayList<User> result = new ArrayList<>();
        for (String key : store.keySet()) {
            result.add(store.get(key));
        }
        return result;
    }
    /**
     * Метод осуществляет поиск по ID
     * @param login
     * @return
     */
    @Override
    public User findById(String login) {
        User result = null;
        if (store.containsKey(login)) {
            result = store.get(login);
        }
        return result;
    }
}
