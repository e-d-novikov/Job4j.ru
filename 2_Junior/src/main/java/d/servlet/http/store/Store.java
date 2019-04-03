package d.servlet.http.store;

import d.servlet.http.object.User;

import java.util.ArrayList;
/**
 * Интерфейс Store.
 * @author Egor Novikov
 * E-mail: enovikovdev@gmail.com
 * @version 1$
 * @since 0.1
 */
public interface Store {

    void add(User user);

    void update(User user);

    void delete(String login);

    ArrayList<User> findAll();

    User findByLogin(String login);

    boolean availableId(int id);

    int getSize();

    boolean isCredentional(String login, String password);
}
