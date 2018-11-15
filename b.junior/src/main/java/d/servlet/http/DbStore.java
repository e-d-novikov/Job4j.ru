package d.servlet.http;

import org.apache.commons.dbcp2.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class DbStore implements Store<User> {

    private static final Logger LOG = LoggerFactory.getLogger(DbStore.class);
    private static final BasicDataSource SOURCE = new BasicDataSource();
    private static DbStore instance = new DbStore();


    public DbStore() {
        SOURCE.setDriverClassName("org.postgresql.Driver");
        SOURCE.setUrl("jdbc:postgresql://localhost:5432/users");
        SOURCE.setUsername("postgres");
        SOURCE.setPassword("password");
        SOURCE.setMinIdle(5);
        SOURCE.setMaxIdle(10);
        SOURCE.setMaxOpenPreparedStatements(100);
        createTable();
    }

    public static DbStore getInstance() {
        return instance;
    }

    private void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS users("
                + "id serial primary key,"
                + "user_id int,"
                + "name character varying (200),"
                + "login character varying (200),"
                + "email character varying (200),"
                + "date character varying (100));";
        Connection connection = null;
        try {
            connection = SOURCE.getConnection();
            Statement statment = connection.createStatement();
            statment.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    LOG.error(e.getMessage(), e);
                }
            }
        }
    }

    @Override
    public void add(User user) {
        PreparedStatement ps = null;
        String sql = "INSERT INTO users(user_id, name, login, email, date) VALUES(?, ?, ?, ?, ?);";
        Connection connection = null;
        try {
        connection = SOURCE.getConnection();
        ps = connection.prepareStatement(sql);
            ps.setInt(1, user.getId());
            ps.setString(2, user.getName());
            ps.setString(3, user.getLogin());
            ps.setString(4, user.getEmail());
            ps.setString(5, user.getDate());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    LOG.error(e.getMessage(), e);
                }
            }
        }
    }

    @Override
    public void update(User user) {
        PreparedStatement ps = null;
        String sql = "UPDATE users SET name = ?, login = ?, email = ?, date = ? where user_id = ?;";
        Connection connection = null;
        try {
            connection = SOURCE.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1, user.getName());
            ps.setString(2, user.getLogin());
            ps.setString(3, user.getEmail());
            ps.setString(4, LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd MMMM uuuuг. HH:mm")));
            ps.setInt(5, user.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    LOG.error(e.getMessage(), e);
                }
            }
        }
    }

    @Override
    public void delete(int id) {
        PreparedStatement ps = null;
        String sql = "DELETE FROM users WHERE user_id = ?";
        Connection connection = null;
        try {
            connection = SOURCE.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    LOG.error(e.getMessage(), e);
                }
            }
        }
    }

    @Override
    public ArrayList<User> findAll() {
        ArrayList<User> users = new ArrayList<>();
        ResultSet result = null;
        String sql = "SELECT * FROM users";
        Connection connection = null;
        try {
            connection = SOURCE.getConnection();
            Statement statment = connection.createStatement();
            result = statment.executeQuery(sql);
            while (result.next()) {
                users.add(new User(result.getInt("user_id"),
                        result.getString("name"),
                        result.getString("login"),
                        result.getString("email"),
                        result.getString("date")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    LOG.error(e.getMessage(), e);
                }
            }
        }
        return users;
    }

    @Override
    public User findById(int id) {
        User user = null;
        PreparedStatement ps = null;
        ResultSet result = null;
        String sql = "SELECT * FROM users WHERE user_id = ?;";
        Connection connection = null;
        try {
            connection = SOURCE.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            result = ps.executeQuery();
            while (result.next()) {
                user = new User(result.getInt("user_id"),
                        result.getString("name"),
                        result.getString("login"),
                        result.getString("email"),
                        result.getString("date"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    LOG.error(e.getMessage(), e);
                }
            }
        }
        return user;
    }

    @Override
    public boolean availableId(int id) {
        boolean available = false;
        String sql = "SELECT * FROM users WHERE user_id = ?";
        Connection connection = null;
        ResultSet result = null;
        PreparedStatement ps = null;
        try {
            connection = SOURCE.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            result = ps.executeQuery();
            if (result.next()) {
                available = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    LOG.error(e.getMessage(), e);
                }
            }
        }
        return available;
    }

    @Override
    public int getSize() {
        int count = -1;
        String sql = "SELECT count(*) FROM users as count";
        Connection connection = null;
        ResultSet result = null;
        try {
            connection = SOURCE.getConnection();
            Statement statement = connection.createStatement();
            statement.execute(sql);
            result = statement.getResultSet();
            while (result.next()) {
                count = result.getInt("count");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    LOG.error(e.getMessage(), e);
                }
            }
        }
        return count;
    }
}
