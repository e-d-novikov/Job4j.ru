package b.oop.a.tracker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
/**
 * Class Tracker keeps applications, and has methods for editing.
 * @author Egor Novikov (e.novikov@yahoo.com)
 * @version 1.0
 * @since 14/06/2018
 */
public class Tracker implements AutoCloseable {

    private static final Logger LOG = LoggerFactory.getLogger(Tracker.class);
    private static String url = "jdbc:postgresql://localhost:5432/items";
    private static String username = "postgres";
    private static String password = "password";
    private static Connection connection = null;

    public Tracker() {
        createTable();
    }
    /**
     * Method adds the application in the repository.
     * @param name - name;
     * @param description - description.
     */
    public void add(String name, String description) {
        PreparedStatement ps = null;
        String sql = "INSERT INTO items(name, description, create_date) VALUES(?, ?, ?);";
        try {
            connection = DriverManager.getConnection(url, username, password);
            ps = connection.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, description);
            ps.setString(3, LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd MMMM uuuuг. HH:mm")));
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
    /**
     * Method modifies the application in the repository.
     * @param id - ID.
     */
    public void replace(int id, String name, String description) {
        PreparedStatement ps = null;
        String sql = "UPDATE items SET name = ?, description = ?, create_date = ? where id = ?;";
        try {
            connection = DriverManager.getConnection(url, username, password);
            ps = connection.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, description);
            ps.setString(3, LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd MMMM uuuuг. HH:mm")));
            ps.setInt(4, id);
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
    /**
     * Method that realizes the removal of an application from the repository.
     * @param id - ID.
     */
    public void delete(int id) {
        PreparedStatement ps = null;
        String sql = "DELETE FROM items WHERE id = ?;";
        try {
            connection = DriverManager.getConnection(url, username, password);
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
    /**
     * The method searches for an application by ID.
     * @param id - ID.
     * @return - new unique key.
     */
    public void findById(int id) {
        PreparedStatement ps = null;
        ResultSet result = null;
        String sql = "SELECT * FROM items WHERE id = ?;";
        try {
            connection = DriverManager.getConnection(url, username, password);
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            result = ps.executeQuery();
            while (result.next()) {
                System.out.println(String.format("%d %s %s %s", result.getInt("id"), result.getString("name"), result.getString("description"), result.getString("create_date")));
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
    }
    /**
     * Method returns a list of orders by name.
     * @param name - name of application.
     * @return - list of applications.
     */
    public void findByName(String name) {
        ResultSet result = null;
        String sql = "SELECT * FROM items WHERE name = ?";
        try {
            connection = DriverManager.getConnection(url, username, password);
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, name);
            result = ps.executeQuery();
            while (result.next()) {
                System.out.println(String.format("%d %s %s %s", result.getInt("id"), result.getString("name"), result.getString("description"), result.getString("create_date")));
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
    }
    /**
     * Method receives a list of all applications.
     * @return lost of application.
     */
    public void getAll() {
        ResultSet result = null;
        String sql = "SELECT * FROM items";
        try {
            connection = DriverManager.getConnection(url, username, password);
            PreparedStatement ps = connection.prepareStatement(sql);
            result = ps.executeQuery();
            while (result.next()) {
                System.out.println(String.format("%d %s %s %s", result.getInt("id"), result.getString("name"), result.getString("description"), result.getString("create_date")));
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
    }
    /**
     * The method is responsible for the availability of applications.
     * @return - true, if there are applications, else false.
     */

    public static void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS items("
                + "id serial primary key,"
                + "name character varying (200),"
                + "description character varying (2000),"
                + "create_date character varying (100));";
        try {
            connection = DriverManager.getConnection(url, username, password);
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

    public boolean isEmpty() {
        boolean result = false;
        ResultSet rs = null;
        String sql = "SELECT count(*) FROM items as count";
        try {
            connection = DriverManager.getConnection(url, username, password);
            PreparedStatement ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            int count = 0;
            while (rs.next()) {
                count = rs.getInt("count");
            }
            if (count > 0) {
                result = true;
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
        return result;
    }

    @Override
    public void close() throws Exception {

    }
}