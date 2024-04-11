package programmer.zaman.now.database;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionPoolTest {

    @Test
    void testHikariCP() {
        HikariConfig config = new HikariConfig();
        config.setDriverClassName("com.mysql.cj.jdbc.Driver");
        config.setJdbcUrl("jdbc:mysql://localhost:3306/java_database_pzn");
        config.setUsername("root");
        config.setPassword("");

        // konfigurasi pool
        config.setMaximumPoolSize(10);
        config.setMinimumIdle(5);
        config.setIdleTimeout(60_000); //60detik
        config.setMaxLifetime(10 * 60_000);

        try {
            HikariDataSource dataSource = new HikariDataSource(config);
            Connection connection = dataSource.getConnection();
            System.out.println("Sukses mengambil connection");

            connection.close();
            System.out.println("Sukses mengembalikan connection");

            dataSource.close();
            System.out.println("Sukses menutup pool");
        } catch (SQLException exception) {
            Assertions.fail(exception);
        }
    }

    @Test
    void testUtil() throws SQLException{
        Connection connection = ConnectionUtil.getDataSource().getConnection();
    }
}
