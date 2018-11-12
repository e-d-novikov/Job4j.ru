package d.servlet.http;

import java.util.ArrayList;

public interface Store {

    void add(User user);

    void update(int id, String name, String login, String email, String date);

    void delete(int id);

    ArrayList<User> findAll();

    User findById(int id);

    boolean availableId(int id);

    int getSize();
}
