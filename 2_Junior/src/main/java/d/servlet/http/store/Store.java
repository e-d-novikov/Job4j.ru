package d.servlet.http.store;

import java.util.ArrayList;
/**
 * Интерфейс Store.
 * @author Egor Novikov
 * E-mail: e.novikov@yahoo.com
 * @version 1$
 * @since 0.1
 */
public interface Store<User> {

    void add(User user);

    void update(User user);

    void delete(int id);

    ArrayList<User> findAll();

    User findById(int id);

    boolean availableId(int id);

    int getSize();

    boolean isCredentional(String login, String password);
}
