package programmer.zaman.now.database;

import org.junit.jupiter.api.Test;

import java.sql.*;

public class AutoIncrementTest {

    @Test
    void testAutoIncrement() throws SQLException {

        Connection connection = ConnectionUtil.getDataSource().getConnection();
        String sql = "INSERT INTO comments(email, comment) VALUES (?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        // wajib menambahkan Statement.RETURN_GENERATED_KEYS jika ingin mengetahui id nya


        preparedStatement.setString(1, "alvenio@test.com");
        preparedStatement.setString(2, "Test");


        preparedStatement.executeUpdate();

        ResultSet resultSet = preparedStatement.getGeneratedKeys();
        if (resultSet.next()) {
            System.out.println("ID comment: " + resultSet.getInt(1));
        }
        System.out.println("Berhasil menambahkan data");

        preparedStatement.close();
        connection.close();
    }
}
