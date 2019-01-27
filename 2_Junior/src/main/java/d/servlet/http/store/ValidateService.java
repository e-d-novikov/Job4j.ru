package d.servlet.http.store;

import d.servlet.http.object.User;
import java.util.ArrayList;
/**
 * Класс ValidateService осуществляет проверку данных перед работой с хранилищем.
 * @author Egor Novikov
 * E-mail: e.novikov@yahoo.com
 * @version 1$
 * @since 0.1
 */
public class ValidateService {

    private static volatile ValidateService instance = new ValidateService();
    private final DbStore memory;

    public ValidateService() {
        memory = DbStore.getInstance();
    }

    public static ValidateService getInstance() {
        return instance;
    }
    /**
     * Метод проверяет существование пользователя с такими же логином и паролем.
     * @param user - новый пользователь.
     */
    public void add(User user) {
        memory.add(user);
    }
    /**
     * Метод проверяет существование пользователя перед редактированием.
     * @param user
     */
    public void update(User user) {
        memory.update(user);
    }
    /**
     * Метод проверяет существование пользователя перед удалением.
     * @param login - логин.
     */
    public void delete(String login) {
        memory.delete(login);
    }
    /**
     * Проверяет существование зарегистрированных пользователей.
     * @return - массив пользователей.
     */
    public ArrayList<User> findAll() {
        ArrayList<User> result = null;
        if (memory.getSize() > 0) {
            result = memory.findAll();
        }
        return result;
    }
    /**
     * Выполняет поиск по логину.
     * @param login - логин.
     * @return - пользователь.
     */
    public User findById(String login) {
        return memory.findByLogin(login);
    }
    /**
     * Проверяет существование пользователей с парой логин-пароль.
     * @param login - логин.
     * @param password - пароль.
     * @return - true если пользователь существует, false в противном случае.
     */
    public boolean isCredentional(String login, String password) {
        return memory.isCredentional(login, password);
    }
}
