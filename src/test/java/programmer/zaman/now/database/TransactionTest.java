package programmer.zaman.now.database;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TransactionTest {

    @Test
    void testCommit() throws SQLException {

        Connection connection = ConnectionUtil.getDataSource().getConnection();
        connection.setAutoCommit(false);

        String sql = "INSERT INTO comments(email, comment) VALUES (?, ?)";


        for (int index = 0; index < 100; index++) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "alvenio@test.com");
            preparedStatement.setString(2, "Test");
            preparedStatement.executeBatch();
            preparedStatement.close();
        }
        System.out.println("Berhasil menambahkan data");


        connection.commit();
        connection.close();
    }

    @Test
    void testRollback() throws SQLException {

        Connection connection = ConnectionUtil.getDataSource().getConnection();
        connection.setAutoCommit(false);

        String sql = "INSERT INTO comments(email, comment) VALUES (?, ?)";


        for (int index = 0; index < 100; index++) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "alvenio@test.com");
            preparedStatement.setString(2, "Test");
            preparedStatement.executeBatch();
            preparedStatement.close();
        }
//        System.out.println("Berhasil menambahkan data");

        connection.rollback();
        connection.close();
    }
}
