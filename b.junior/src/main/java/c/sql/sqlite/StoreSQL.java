package c.sql.sqlite;

import c.sql.sqlite.objects.Config;
import c.sql.sqlite.objects.Field;
import c.sql.sqlite.objects.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StoreSQL {

    //TODO Файл базы данных -> файл XML через JAXB -> файл XML через XSTL -> через SAX вывод в консоль

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
            Statement statement = conn.createStatement();
            for (int i = 1; i <= n; i++) {
                String sql = "INSERT INTO entry (field) VALUES (" + i + ")";
                statement.executeUpdate(sql);
            }
            conn.commit();
            statement.close();
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
