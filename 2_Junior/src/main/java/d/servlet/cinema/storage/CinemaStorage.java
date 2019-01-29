package d.servlet.cinema.storage;

import d.servlet.cinema.objects.Account;
import d.servlet.cinema.objects.Ticket;
import org.apache.commons.dbcp2.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;
/**
 * Класс CinemaStorage обеспечивает доступ к базе данных cinema таблице hall.
 * @author Egor Novikov
 * E-mail: e.novikov@yahoo.com
 * @version 1$
 * @since 0.1
 */
public class CinemaStorage {

    private static final Logger LOG = LoggerFactory.getLogger(CinemaStorage.class);
    private static final BasicDataSource SOURCE = new BasicDataSource();
    private static final CinemaStorage INSTANCE = new CinemaStorage();

    public CinemaStorage() {
        Properties props = new Properties();
        try (InputStream stream = getClass().getClassLoader().getResourceAsStream("cinema.properties")) {
            props.load(stream);
        } catch (IOException e) {
            LOG.error("Error", e);
        }
        SOURCE.setDriverClassName(props.getProperty("driver"));
        SOURCE.setUrl(props.getProperty("url"));
        SOURCE.setUsername(props.getProperty("login"));
        SOURCE.setPassword(props.getProperty("password"));
        SOURCE.setMinIdle(5);
        SOURCE.setMaxIdle(10);
        SOURCE.setMaxOpenPreparedStatements(100);
    }

    public static CinemaStorage getInstance() {
        return INSTANCE;
    }

    public static void buyTicket(int id, String name, String phone) {
        String begin = "BEGIN;";
        String select = "SELECT id_account FROM hall WHERE id = ? FOR UPDATE;";
        try (Connection connection = SOURCE.getConnection()) {
            connection.setAutoCommit(false);
            Statement firstStatement = connection.createStatement();
            firstStatement.execute(begin);
            firstStatement.close();
            PreparedStatement secondStatement = connection.prepareStatement(select);
            secondStatement.setInt(1, id);
            secondStatement.executeQuery();
            secondStatement.close();
        } catch (SQLException e) {
            LOG.error("Connection error!", e);
        }

        int accountID = AccountStorage.getInstance().createAccount(new Account(name, phone));

        String update = "UPDATE hall SET id_account = ? WHERE id = ?;";
        try (Connection connection = SOURCE.getConnection()) {
            PreparedStatement thirdStatement = connection.prepareStatement(update);
            thirdStatement.setInt(1, accountID);
            thirdStatement.setInt(2, id);
            thirdStatement.executeUpdate();
            thirdStatement.close();
        } catch (SQLException e) {
            LOG.error("Connection error!", e);
        }

        String commit = "COMMIT;";

        try (Connection connection = SOURCE.getConnection()) {
            Statement fourthStatement = connection.createStatement();
            fourthStatement.execute(commit);
            fourthStatement.close();
        } catch (SQLException e) {
            LOG.error("Connection error!", e);
        }
    }

    public Ticket getPlaceFromID(int id) {
        Ticket ticket = null;
        String sql = "SELECT * FROM hall WHERE id = ?;";
        try (Connection connection = SOURCE.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet result = ps.executeQuery();
            while (result.next()) {
                ticket = new Ticket(result.getInt("id"),
                        result.getInt("row"),
                        result.getInt("place"),
                        result.getInt("price"));
            }
            result.close();
            ps.close();
        } catch (SQLException e) {
            LOG.error("Connection error!", e);
        }
        return ticket;
    }

    public ArrayList<Ticket> getAllPlaces() {
        ArrayList<Ticket> tickets = new ArrayList<>();
        String sql = "SELECT * FROM hall";
        try (Connection connection = SOURCE.getConnection()) {
            Statement statment = connection.createStatement();
            ResultSet result = statment.executeQuery(sql);
            while (result.next()) {
                int accountID = result.getInt("id_account");
                tickets.add(new Ticket(result.getInt("id"),
                        result.getInt("row"),
                        result.getInt("place"),
                        result.getInt("price"),
                        AccountStorage.getInstance().getAccountFromID(accountID)));
            }
            result.close();
            statment.close();
        } catch (SQLException e) {
            LOG.error("Connection error!", e);
        }
        return tickets;
    }
}
