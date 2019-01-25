package c.sql.sqlite;

import c.sql.sqlite.objects.Config;
import c.sql.sqlite.objects.Field;
import c.sql.sqlite.objects.User;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 * Class StoreSQL.
 * @author Egor Novikov
 * E-mail: e.novikov@yahoo.com
 * @version 1
 * @since 0.1
 */
public class StoreSQL {

    private Connection conn;

    public StoreSQL(Config config, int n) throws SQLException, ClassNotFoundException {
        connect(config);
        dropTable();
        createTable();
        generate(n);
    }

    private void connect(Config config) throws ClassNotFoundException, SQLException {
        conn = null;
        Class.forName("org.sqlite.JDBC");
        conn = DriverManager.getConnection("jdbc:sqlite:" + config);
    }

    private void createTable() throws ClassNotFoundException, SQLException {
        Statement statement = conn.createStatement();
        statement.execute("CREATE TABLE IF NOT EXISTS entry (field integer);");
        statement.close();
    }

    private void generate(int n) throws SQLException {
        try {
            conn.setAutoCommit(false);
            PreparedStatement ps = conn.prepareStatement("INSERT INTO entry (field) VALUES (?)");
            for (int i = 1; i <= n; i++) {
                ps.setInt(1, i);
                ps.addBatch();
            }
            ps.executeBatch();
            conn.setAutoCommit(true);
        } catch (SQLException e) {
            conn.rollback();
        }
    }

    private void dropTable() throws SQLException {
        Statement statement = conn.createStatement();
        statement.execute("DROP TABLE IF EXISTS entry");
        statement.close();
    }

    public User getData() throws SQLException {
        List<Field> numbers = new ArrayList<>();
        Statement statement = conn.createStatement();
        ResultSet resSet = statement.executeQuery("SELECT * FROM entry");
        while (resSet.next()) {
            numbers.add(new Field(new Integer(resSet.getInt("field"))));
        }
        User result = new User(numbers);
        resSet.close();
        statement.close();
        return result;
    }

    public void closeConnection() throws SQLException {
        conn.close();
    }
}
