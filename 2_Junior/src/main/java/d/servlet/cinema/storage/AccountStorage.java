package d.servlet.cinema.storage;

import d.servlet.cinema.objects.Account;
import org.apache.commons.dbcp2.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
/**
 * Класс AccountStorage обеспечивает доступ к базе данных cinema таблице accounts.
 * @author Egor Novikov
 * E-mail: e.novikov@yahoo.com
 * @version 1$
 * @since 0.1
 */
public class AccountStorage {

    private final Logger LOG = LoggerFactory.getLogger(CinemaStorage.class);
    private final BasicDataSource SOURCE = new BasicDataSource();
    private static final AccountStorage INSTANCE = new AccountStorage();

    public AccountStorage() {
        Properties props = new Properties();
        try (InputStream stream = getClass().getClassLoader().getResourceAsStream("cinema.properties")) {
            props.load(stream);
        } catch (IOException e) {
            LOG.error("Error!", e);
        }
        SOURCE.setDriverClassName(props.getProperty("driver"));
        SOURCE.setUrl(props.getProperty("url"));
        SOURCE.setUsername(props.getProperty("login"));
        SOURCE.setPassword(props.getProperty("password"));
        SOURCE.setMinIdle(5);
        SOURCE.setMaxIdle(10);
        SOURCE.setMaxOpenPreparedStatements(100);
    }

    public static AccountStorage getInstance() {
        return INSTANCE;
    }

    public Integer createAccount(Account account) {
        Integer result = null;
        String insert = "INSERT INTO accounts(name, phone) VALUES(?, ?);";
        String id = "SELECT id FROM accounts WHERE name = ? AND phone = ?;";
        try (Connection connection = SOURCE.getConnection()) {
            PreparedStatement psFirst = connection.prepareStatement(insert);
            psFirst.setString(1, account.getName());
            psFirst.setString(2, account.getPhone());
            psFirst.executeUpdate();
            psFirst.close();
            PreparedStatement psSecond = connection.prepareStatement(id);
            psSecond.setString(1, account.getName());
            psSecond.setString(2, account.getPhone());
            ResultSet rs = psSecond.executeQuery();
            while (rs.next()) {
               result = rs.getInt("id");
            }
            rs.close();
            psSecond.close();
        } catch (SQLException e) {
            LOG.error("Connection error!", e);
        }
        return result;
    }

    public Account getAccountFromID(int id) {
        Account account = null;
        String sql = "SELECT * FROM accounts WHERE id = ?;";
        try (Connection connection = SOURCE.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet result = ps.executeQuery();
            while (result.next()) {
                account = new Account(result.getInt("id"),
                        result.getString("name"),
                        result.getString("phone"));
            }
            result.close();
            ps.close();
        } catch (SQLException e) {
            LOG.error("Connection error!", e);
        }
        return account;
    }
}
