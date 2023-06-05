package stanislav.danylenko.jdbc.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public final class HikariCPDataSource {

    private static final HikariConfig CONFIG = new HikariConfig();
    private static final HikariDataSource DATA_SOURCE;

    static {
        CONFIG.setJdbcUrl("jdbc:postgresql://localhost:5441/test-jdbc");
        CONFIG.setUsername("user");
        CONFIG.setPassword("password");
        CONFIG.addDataSourceProperty("cachePrepStmts", "true");
        CONFIG.addDataSourceProperty("prepStmtCacheSize", "250");
        CONFIG.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        DATA_SOURCE = new HikariDataSource(CONFIG);
    }

    public static Connection getConnection() throws SQLException {
        return DATA_SOURCE.getConnection();
    }

    private HikariCPDataSource(){}
}