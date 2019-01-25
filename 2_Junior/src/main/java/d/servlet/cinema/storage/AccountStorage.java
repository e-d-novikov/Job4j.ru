package d.servlet.cinema.storage;

import d.servlet.cinema.objects.Account;
import org.apache.commons.dbcp2.BasicDataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 * Класс AccountStorage обеспечивает доступ к базе данных cinema таблице accounts.
 * @author Egor Novikov
 * E-mail: e.novikov@yahoo.com
 * @version 1$
 * @since 0.1
 */
public class AccountStorage {

    private static final BasicDataSource SOURCE = new BasicDataSource();
    private static final AccountStorage INSTANCE = new AccountStorage();

    public AccountStorage() {
        SOURCE.setDriverClassName("org.postgresql.Driver");
        SOURCE.setUrl("jdbc:postgresql://localhost:5432/cinema");
        SOURCE.setUsername("postgres");
        SOURCE.setPassword("password");
        SOURCE.setMinIdle(5);
        SOURCE.setMaxIdle(10);
        SOURCE.setMaxOpenPreparedStatements(100);
    }

    public static AccountStorage getInstance() {
        return INSTANCE;
    }

    public Integer createAccount(Account account) {
        Integer result = null;
        PreparedStatement psFirst = null;
        PreparedStatement psSecond = null;
        String insert = "INSERT INTO accounts(name, phone) VALUES(?, ?);";
        String id = "SELECT id FROM accounts WHERE name = ? AND phone = ?;";
        ResultSet rs = null;
        try (Connection connection = SOURCE.getConnection()) {
            psFirst = connection.prepareStatement(insert);
            psFirst.setString(1, account.getName());
            psFirst.setString(2, account.getPhone());
            psFirst.executeUpdate();
            psSecond = connection.prepareStatement(id);
            psSecond.setString(1, account.getName());
            psSecond.setString(2, account.getPhone());
            rs = psSecond.executeQuery();
            while (rs.next()) {
               result = rs.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public Account getAccountFromID(int id) {
        Account account = null;
        PreparedStatement ps = null;
        ResultSet result = null;
        String sql = "SELECT * FROM accounts WHERE id = ?;";
        try (Connection connection = SOURCE.getConnection()) {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            result = ps.executeQuery();
            while (result.next()) {
                account = new Account(result.getInt("id"),
                        result.getString("name"),
                        result.getString("phone"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return account;
    }
}
