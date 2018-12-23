package d.servlet.http;

import org.apache.commons.dbcp2.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.*;
import java.util.ArrayList;

public class DbStore {

    private static final Logger LOG = LoggerFactory.getLogger(DbStore.class);
    private static final BasicDataSource SOURCE = new BasicDataSource();
    private static final DbStore INSTANCE = new DbStore();

    public DbStore() {
        SOURCE.setDriverClassName("org.postgresql.Driver");
        SOURCE.setUrl("jdbc:postgresql://localhost:5432/users");
        SOURCE.setUsername("postgres");
        SOURCE.setPassword("password");
        SOURCE.setMinIdle(5);
        SOURCE.setMaxIdle(10);
        SOURCE.setMaxOpenPreparedStatements(100);
    }

    public static DbStore getInstance() {
        return INSTANCE;
    }

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
            e.printStackTrace();
        }
    }

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
            e.printStackTrace();
        }
    }

    public void deleteUser(String login) {
        PreparedStatement ps = null;
        String sql = "DELETE FROM users WHERE login = ?";
        try (Connection connection = SOURCE.getConnection()) {
            ps = connection.prepareStatement(sql);
            ps.setString(1, login);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

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
            e.printStackTrace();
        }
        return users;
    }

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
            e.printStackTrace();
        }
        return user;
    }

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
            e.printStackTrace();
        }
        return available;
    }

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
            e.printStackTrace();
        }
        return count;
    }

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
            e.printStackTrace();
        }
        if (password.equals(dbPassword)) {
            exist = true;
        }
        return exist;
    }
}
