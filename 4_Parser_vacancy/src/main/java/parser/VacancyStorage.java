package parser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.Properties;
/**
 * Класс VacancyStorage отвечает за взаимодействие с базой данных.
 * @author Egor Novikov
 * E-mail: enovikovdev@gmail.com
 * @version 1$
 * @since 0.1
 */
public class VacancyStorage implements AutoCloseable {

    private static final Logger LOG = LogManager.getLogger(VacancyStorage.class.getName());
    private static final VacancyStorage INSTANCE = new VacancyStorage();

    private Connection connection;

    private String url;
    private String username;
    private String password;
    private String driver;
    /**
     * Конструктор.
     */
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
    /**
     * Метод получает настройки из файла настроек.
     * @throws IOException
     */
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
    /**
     * Метод отвечает за соединение с базой данных.
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    private void connection() throws SQLException, ClassNotFoundException {
        Class.forName(driver);
        connection = DriverManager.getConnection(url, username, password);
    }
    /**
     * Метод делает запись новой вакансии.
     * @param vacancy - вакансия.
     */
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
    /**
     * Метод создает таблицу если она еще не создана.
     */
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
    /**
     * Метод получает дату последней записи.
     * @return - дата последней записи, если записей не было то возвращает дату год назад.
     */
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
    /**
     * Метод закрывает соединение с базой данных.
     * @throws Exception
     */
    @Override
    public void close() throws Exception {
        connection.close();
    }
}
