package programmer.zaman.now.database;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class BatchTest {

    @Test
    void testStatement() throws SQLException {

        Connection connection = ConnectionUtil.getDataSource().getConnection();
        Statement statement = connection.createStatement();

        String sql = "INSERT INTO comments(email, comment) VALUES ('alvenio@gmail.com', 'Hello')";

        for (int index = 0; index < 1000; index++) {
            statement.addBatch(sql);
        }
        System.out.println("Berhasil menambahkan data");

        statement.executeBatch();

        statement.close();
        connection.close();
    }

    @Test
    void testPreparedStatement() throws SQLException {

        Connection connection = ConnectionUtil.getDataSource().getConnection();
        String sql = "INSERT INTO comments(email, comment) VALUES (?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);


        for (int index = 0; index < 1000; index++) {
            preparedStatement.clearParameters();
            preparedStatement.setString(1,"alvenio@test.com");
            preparedStatement.setString(2,"Test");
            preparedStatement.addBatch();
        }
        System.out.println("Berhasil menambahkan data");

        preparedStatement.executeBatch();

        preparedStatement.close();
        connection.close();
    }
}
