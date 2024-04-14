package programmer.zaman.now.database;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Type;
import java.sql.*;

public class MetaDataTest {

    @Test
    void testDatabaseMetaData() throws SQLException {

        Connection connection = ConnectionUtil.getDataSource().getConnection();
        DatabaseMetaData databaseMetaData = connection.getMetaData();

        System.out.println(databaseMetaData.getDatabaseProductName());
        System.out.println(databaseMetaData.getDatabaseProductVersion());

        ResultSet resultSet = databaseMetaData.getTables("java_database_pzn", null, null, null);
        while (resultSet.next()) {
            String tableName = resultSet.getString("TABLE_NAME");
            System.out.println("Table: " + tableName);
        }

        connection.close();
    }

    @Test
    void testParameterMetaData() throws SQLException {
        Connection connection = ConnectionUtil.getDataSource().getConnection();
        String sql = "INSERT INTO comments(email, comment) VALUES (?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        ParameterMetaData parameterMetaData = preparedStatement.getParameterMetaData();

        System.out.println(parameterMetaData.getParameterCount());
//        System.out.println(parameterMetaData.getParameterTypeName(1));

        preparedStatement.close();
        connection.close();
    }

    @Test
    void testResultSetMetaData() throws SQLException {
        Connection connection = ConnectionUtil.getDataSource().getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM sample_time");

        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

//        System.out.println(resultSetMetaData.getColumnCount());
        for (int index = 1; index <= resultSetMetaData.getColumnCount(); index++) {
            System.out.println("Nama : " + resultSetMetaData.getColumnName(index));
            System.out.println("Type : " + resultSetMetaData.getColumnType(index));
            System.out.println("Type Name : " + resultSetMetaData.getColumnTypeName(index));
            if (resultSetMetaData.getColumnType(index) == Types.INTEGER) {
                System.out.println("INI INTEGER");
            }
        }

        //Type dari java.sql.Types...

        resultSet.close();
        statement.close();
        connection.close();
    }
}
