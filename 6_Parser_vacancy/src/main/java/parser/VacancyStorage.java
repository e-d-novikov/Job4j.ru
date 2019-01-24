package parser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.Properties;

public class VacancyStorage implements AutoCloseable {

    private static final Logger LOG = LogManager.getLogger(VacancyStorage.class.getName());
    private static final VacancyStorage INSTANCE = new VacancyStorage();

    private Connection connection;

    private String url;
    private String username;
    private String password;
    private String driver;

    private VacancyStorage() {
        try {
            getProperties();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            connection();
            createTable();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static VacancyStorage getInstance() {
        return INSTANCE;
    }

    private void getProperties() throws IOException {
        Properties props = new Properties();
        try (InputStream stream = getClass().getClassLoader().getResourceAsStream("config.properties")) {
            props.load(stream);
        }
        url = props.getProperty("url");
        username = props.getProperty("username");
        password = props.getProperty("password");
        driver = props.getProperty("driver");
    }

    private void connection() throws SQLException, ClassNotFoundException {
        Class.forName(driver);
        connection = DriverManager.getConnection(url, username, password);
    }

    public void createNewRecord(Vacancy vacancy) {
        String sql = "INSERT INTO vacancy (name, text, link, date) VALUES(?, ?, ?, ?);";
        try (PreparedStatement ps  = connection.prepareStatement(sql)) {
            ps.setString(1, vacancy.getName());
            ps.setString(2, vacancy.getText());
            ps.setString(3, vacancy.getLink());
            ps.setTimestamp(4, vacancy.getDate());
            ps.executeUpdate();
        } catch (SQLException e) {
            LOG.error("Connection error!");
        }
    }

    private void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS vacancy("
                + "id serial primary key, "
                + "name text, "
                + "text text, "
                + "link text, "
                + "date timestamp);";
        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
        } catch (SQLException e) {
            LOG.error("Connection error!");
        }
    }

    private boolean checkTable() {
        boolean result = false;
        int count = 0;
        String sql = "SELECT count(*) FROM vacancy as count";
        try (PreparedStatement ps  = connection.prepareStatement(sql)) {
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                count = resultSet.getInt("count");
            }
            if (count > 0) {
                result = true;
            }
        } catch (SQLException e) {
            LOG.error("Connection error!");
        }
        return result;
    }

    public LocalDateTime getLastRecordDate() {
        LocalDateTime dateTime = LocalDateTime.now().minusYears(1);
        String sql = "SELECT max(date) FROM vacancy";
        try (PreparedStatement ps  = connection.prepareStatement(sql)) {
            ResultSet result = ps.executeQuery();
            while (result.next()) {
                dateTime = result.getTimestamp("id").toLocalDateTime();
            }
        } catch (SQLException e) {
            LOG.error("Connection error!");
        }
        return dateTime;
    }

    private void printVacancy() {
        String sql = "SELECT * FROM vacancy";
        try (PreparedStatement ps  = connection.prepareStatement(sql)) {
            ResultSet result = ps.executeQuery();
            while (result.next()) {
                System.out.println(result.getInt("id"));
                System.out.println(result.getString("name"));
            }
        } catch (SQLException e) {
            LOG.error("Connection error!");
        }
    }

    @Override
    public void close() throws Exception {
        connection.close();
    }
}
