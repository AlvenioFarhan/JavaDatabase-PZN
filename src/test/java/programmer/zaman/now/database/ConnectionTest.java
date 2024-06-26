package programmer.zaman.now.database;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.function.Try;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionTest {

    @BeforeAll
    static void beforeAll() {
        try {
            Driver mysqlDriver = new com.mysql.cj.jdbc.Driver();
            DriverManager.registerDriver(mysqlDriver);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    @Test
    void testConnection() {
        String jdbcUrl = "jdbc:mysql://localhost:3306/java_database_pzn";
        String username = "root";
        String password = "";

        try {
            Connection connection = DriverManager.getConnection(jdbcUrl, username, password);

            System.out.println("Sukses connect ke database");
        } catch (SQLException exception) {
            Assertions.fail(exception);
        }
    }

    @Test
    void testConnectionClose() {
        String jdbcUrl = "jdbc:mysql://localhost:3306/java_database_pzn";
        String username = "root";
        String password = "";

        try( Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {
            System.out.println("Sukses connect ke database");

//            connection.close();
        } catch (SQLException exception) {
            Assertions.fail(exception);
        }
    }
}
