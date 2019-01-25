package d.servlet.http.store;

import d.servlet.http.object.User;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
/**
 * Класс MemoryStore является хранилищем пользователей.
 * @author Egor Novikov
 * E-mail: e.novikov@yahoo.com
 * @version 1$
 * @since 0.1
 */
public enum MemoryStore implements Store<User> {

    INSTANCE;
    private final ConcurrentHashMap<Integer, User> users = new ConcurrentHashMap<>();
    /**
     * Метод добавляет пользователя в хранилище.
     * @param user - пользователь.
     */
    @Override
    public void add(User user) {
        users.put(user.getId(), user);
    }
    /**
     * Метод обновляет данные пользователя в хранилище.
     * @param user - пользователь.
     */
    @Override
    public void update(User user) {
        users.put(user.getId(), user);
    }
    /**
     * Метод удаляет данные пользователя из хранилища.
     * @param id - ID пользователя.
     */
    @Override
    public void delete(int id) {
        users.remove(id);
    }
    /**
     * Метод возвращает всех текущих пользователей.
     * @return - ArrayList пользователей.
     */
    @Override
    public ArrayList<User> findAll() {
        ArrayList<User> result = new ArrayList<>();
        for (Integer id : users.keySet()) {
            result.add(users.get(id));
        }
        return result;
    }
    /**
     * Метод осуществляет пользователя по ID.
     * @param id - ID пользователя.
     * @return - пользователь.
     */
    @Override
    public User findById(int id) {
        return users.get(id);
    }
    /**
     * Метод проверяет существование пользователя с ID в хранилище.
     * @param id - ID пользователя.
     * @return - true если пользователь существует, иначе false.
     */
    @Override
    public boolean availableId(int id) {
        boolean result = false;
        if (users.containsKey(id)) {
            result = true;
        }
        return result;
    }
    /**
     * Метод возвращает количество пользователей в хранилище.
     * @return - кол-во пользователей.
     */
    @Override
    public int getSize() {
        return users.size();
    }
    /**
     * Метод проверяет существование пользователя с парой логин-пароль.
     * @param login - логин;
     * @param password - пароль;
     * @return - true если такой пользователь есть, иначе false.
     */
    @Override
    public boolean isCredentional(String login, String password) {
        return false;
    }
}
