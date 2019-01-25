package d.servlet.cinema.storage;

import d.servlet.cinema.objects.Account;
import d.servlet.cinema.objects.Ticket;
import org.apache.commons.dbcp2.BasicDataSource;
import java.sql.*;
import java.util.ArrayList;
/**
 * Класс CinemaStorage обеспечивает доступ к базе данных cinema таблице hall.
 * @author Egor Novikov
 * E-mail: e.novikov@yahoo.com
 * @version 1$
 * @since 0.1
 */
public class CinemaStorage {

    private static final BasicDataSource SOURCE = new BasicDataSource();
    private static final CinemaStorage INSTANCE = new CinemaStorage();

    public CinemaStorage() {
        SOURCE.setDriverClassName("org.postgresql.Driver");
        SOURCE.setUrl("jdbc:postgresql://localhost:5432/cinema");
        SOURCE.setUsername("postgres");
        SOURCE.setPassword("password");
        SOURCE.setMinIdle(5);
        SOURCE.setMaxIdle(10);
        SOURCE.setMaxOpenPreparedStatements(100);
    }

    public static CinemaStorage getInstance() {
        return INSTANCE;
    }

    public static void buyTicket(int id, String name, String phone) {
        Statement firstStatement = null;
        PreparedStatement secondStatement = null;
        String begin = "BEGIN;";
        String select = "SELECT id_account FROM hall WHERE id = ? FOR UPDATE;";
        try (Connection connection = SOURCE.getConnection()) {
            connection.setAutoCommit(false);
            firstStatement = connection.createStatement();
            firstStatement.execute(begin);
            secondStatement = connection.prepareStatement(select);
            secondStatement.setInt(1, id);
            secondStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        int accountID = AccountStorage.getInstance().createAccount(new Account(name, phone));

        String update = "UPDATE hall SET id_account = ? WHERE id = ?;";
        PreparedStatement thirdStatement = null;
        try (Connection connection = SOURCE.getConnection()) {
            thirdStatement = connection.prepareStatement(update);
            thirdStatement.setInt(1, accountID);
            thirdStatement.setInt(2, id);
            thirdStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String commit = "COMMIT;";
        Statement fourthStatement = null;
        try (Connection connection = SOURCE.getConnection()) {
            fourthStatement = connection.createStatement();
            fourthStatement.execute(commit);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Ticket getPlaceFromID(int id) {
        Ticket ticket = null;
        String sql = "SELECT * FROM hall WHERE id = ?;";
        PreparedStatement ps = null;
        try (Connection connection = SOURCE.getConnection()) {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet result = ps.executeQuery();
            while (result.next()) {
                ticket = new Ticket(result.getInt("id"),
                        result.getInt("row"),
                        result.getInt("place"),
                        result.getInt("price"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tickets;
    }
}
