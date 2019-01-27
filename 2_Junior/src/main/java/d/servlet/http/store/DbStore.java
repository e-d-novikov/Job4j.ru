package d.servlet.http.store;

import d.servlet.http.object.User;
import org.apache.commons.dbcp2.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

/**
 * Класс DbStore обеспечивает взаимодействие с базой данных users.
 * @author Egor Novikov
 * E-mail: e.novikov@yahoo.com
 * @version 1$
 * @since 0.1
 */
public class DbStore {

    private static final Logger LOG = LoggerFactory.getLogger(DbStore.class);
    private static final BasicDataSource SOURCE = new BasicDataSource();
    private static final DbStore INSTANCE = new DbStore();
    /**
     * Конструктор.
     */
    public DbStore() {
        Properties props = new Properties();
        try (InputStream stream = getClass().getClassLoader().getResourceAsStream("config.properties")) {
            props.load(stream);
        } catch (IOException e) {
            LOG.error("");
        }
        SOURCE.setDriverClassName(props.getProperty("driver"));
        SOURCE.setUrl(props.getProperty("url"));
        SOURCE.setUsername(props.getProperty("login"));
        SOURCE.setPassword(props.getProperty("password"));
        SOURCE.setMinIdle(5);
        SOURCE.setMaxIdle(10);
        SOURCE.setMaxOpenPreparedStatements(100);
    }

    public static DbStore getInstance() {
        return INSTANCE;
    }
    /**
     * Метод добаляет нового пользователя в базу данных.
     * @param user - пользователь.
     */
    public void createUser(User user) {
        PreparedStatement ps = null;
        String sql = "INSERT INTO users(login, password, role, user_name, user_sername, email) VALUES(?, ?, ?, ?, ?, ?);";
        try (Connection connection = SOURCE.getConnection()) {
            ps = connection.prepareStatement(sql);
            ps.setString(1, user.getLogin());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getRole());
            ps.setString(4, user.getName());
            ps.setString(5, user.getSername());
            ps.setString(6, user.getEmail());
            ps.executeUpdate();
        } catch (SQLException e) {
            LOG.error("Connection error!");
        }
    }
    /**
     * Метод вносит изменения данных пользователя в базе данных.
     * @param user - пользователь.
     */
    public void editUser(User user) {
        String sql = "UPDATE users SET login = ?, password = ?, role = ?, user_name = ?, user_sername = ?, email = ? where id = ?;";
        PreparedStatement ps = null;
        try (Connection connection = SOURCE.getConnection()) {
            ps = connection.prepareStatement(sql);
            ps.setString(1, user.getLogin());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getRole());
            ps.setString(4, user.getName());
            ps.setString(5, user.getSername());
            ps.setString(6, user.getEmail());
            ps.setInt(7, user.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            LOG.error("Connection error!");
        }
    }
    /**
     * Метод удяляет пользователя из базы данных.
     * @param login - логин.
     */
    public void deleteUser(String login) {
        PreparedStatement ps = null;
        String sql = "DELETE FROM users WHERE login = ?";
        try (Connection connection = SOURCE.getConnection()) {
            ps = connection.prepareStatement(sql);
            ps.setString(1, login);
            ps.executeUpdate();
        } catch (SQLException e) {
            LOG.error("Connection error!");
        }
    }
    /**
     * Метод возвращает всех пользователей.
     * @return - все пользователи.
     */
    public ArrayList<User> findAllUsers() {
        ArrayList<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users";
        try (Connection connection = SOURCE.getConnection()) {
            Statement statment = connection.createStatement();
            ResultSet result = statment.executeQuery(sql);
            while (result.next()) {
                users.add(new User(result.getInt("id"),
                        result.getString("login"),
                        result.getString("password"),
                        result.getString("role"),
                        result.getString("user_name"),
                        result.getString("user_sername"),
                        result.getString("email")));
            }
        } catch (SQLException e) {
            LOG.error("Connection error!");
        }
        return users;
    }
    /**
     * Метод осуществляет поиск пользователя в базе данных по логину.
     * @param login - логин.
     * @return - пользователь.
     */
    public User findById(String login) {
        User user = null;
        PreparedStatement ps = null;
        ResultSet result = null;
        String sql = "SELECT * FROM users WHERE login = ?;";
        try (Connection connection = SOURCE.getConnection()) {
            ps = connection.prepareStatement(sql);
            ps.setString(1, login);
            result = ps.executeQuery();
            while (result.next()) {
                user = new User(result.getInt("id"),
                        result.getString("login"),
                        result.getString("password"),
                        result.getString("role"),
                        result.getString("user_name"),
                        result.getString("user_sername"),
                        result.getString("email"));
            }
        } catch (SQLException e) {
            LOG.error("Connection error!");
        }
        return user;
    }
    /**
     * Метод проверяет существование в базе данных пользователя с ID.
     * @param id - ID.
     * @return - пользователь.
     */
    public boolean availableId(int id) {
        boolean available = false;
        String sql = "SELECT * FROM users WHERE id = ?";
        ResultSet result = null;
        PreparedStatement ps = null;
        try (Connection connection = SOURCE.getConnection()) {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            result = ps.executeQuery();
            if (result.next()) {
                available = true;
            }
        } catch (SQLException e) {
            LOG.error("Connection error!");
        }
        return available;
    }
    /**
     * Метод возвращает количество зарегистрированных пользователей.
     * @return - кол-во пользователей.
     */
    public int getSize() {
        int count = -1;
        String sql = "SELECT count(*) FROM users as count";
        ResultSet result = null;
        try (Connection connection = SOURCE.getConnection()) {
            Statement statement = connection.createStatement();
            statement.execute(sql);
            result = statement.getResultSet();
            while (result.next()) {
                count = result.getInt("count");
            }
        } catch (SQLException e) {
            LOG.error("Connection error!");
        }
        return count;
    }
    /**
     * Проверяет наличие пользователя с логином и паролем.
     * @param login - логин.
     * @param password - пароль.
     * @return - true если такой пользователь существует, false  в противном случае.
     */
    public boolean isCredentional(String login, String password) {
        boolean exist = false;
        String sql = "SELECT * FROM users WHERE login = ?";
        ResultSet result = null;
        PreparedStatement ps = null;
        String dbPassword = null;
        try (Connection connection = SOURCE.getConnection()) {
            ps = connection.prepareStatement(sql);
            ps.setString(1, login);
            result = ps.executeQuery();
            if (result.next()) {
                dbPassword = result.getString("password");
            }
        } catch (SQLException e) {
            LOG.error("Connection error!");
        }
        if (password.equals(dbPassword)) {
            exist = true;
        }
        return exist;
    }
}
