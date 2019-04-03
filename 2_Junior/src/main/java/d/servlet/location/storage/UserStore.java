package d.servlet.location.storage;

import d.servlet.location.objects.User;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
/**
 * Класс UserStore - хранилище пользователей на основе ConcurrentHashMap.
 * @author Egor Novikov
 * E-mail: enovikovdev@gmail.com
 * @version 1$
 * @since 0.1
 */
public class UserStore {

    private static volatile UserStore instance = new UserStore();
    private final ConcurrentHashMap<String, User> store = new ConcurrentHashMap<>();

    public static UserStore getInstance() {
        return instance;
    }
    /**
     * Метод приводит строку к виду первая буква в верхнем регистре остальные в нижнем.
     * @param string - строка.
     * @return - строка после обработки.
     */
    private String standartCase(String string) {
        String word = string.toLowerCase();
        return word.substring(0, 1).toUpperCase() + word.substring(1);
    }
    /**
     * Метод добавляет пользователя в хранилище.
     * @param user - пользователь.
     */
    public void add(User user) {
        String name = standartCase(user.getName());
        String surname = standartCase(user.getSurname());
        store.put(user.getName(), new User(name, surname, user.getSex(), user.getCountry(), user.getRegion(), user.getCity(), user.getDescription()));
    }
    /**
     * Метод возвращает список всех пользователей.
     * @return - список пользователей.
     */
    public ArrayList<User> getAll() {
        ArrayList<User> users = new ArrayList<>();
        for (Map.Entry<String, User> entry : store.entrySet()) {
         users.add(entry.getValue());
        }
        return users;
    }
}
