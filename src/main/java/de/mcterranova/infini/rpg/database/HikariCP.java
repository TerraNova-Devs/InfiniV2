package de.mcterranova.infini.rpg.database;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import de.mcterranova.infini.Infini;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

public class HikariCP {

    //static Dotenv secret;

    private final Infini plugin;
    private final String user;
    private final String password;
    public HikariDataSource dataSource;

    public HikariCP(Infini plugin) throws SQLException {
        this.plugin = plugin;
        //secret = Dotenv.configure().directory().filename(".env").load();

        //user = secret.get("USERNAME");
        user = "minecraft";
        //System.out.println(user);
        //password = secret.get("PASSWORD");
        password = "minecraft";
        //System.out.println(password);

        HikariConfig config = getHikariConfig();
        dataSource = new HikariDataSource(config);
        try (Connection connection = dataSource.getConnection()) {
            DatabaseMetaData metaData = connection.getMetaData();
            System.out.println("Driver Name: " + metaData.getDriverName());
            System.out.println("Driver Version: " + metaData.getDriverVersion());
            System.out.println("Database Product Name: " + metaData.getDatabaseProductName());
            System.out.println("Database Product Version: " + metaData.getDatabaseProductVersion());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        prepareTables();
    }

    private @NotNull HikariConfig getHikariConfig() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://localhost/network");
        config.setUsername(user);
        config.setPassword(password);
        config.setMaximumPoolSize(20);
        config.setMinimumIdle(20);
        config.setMaxLifetime(1800000);
        config.setKeepaliveTime(0);
        config.setConnectionTimeout(5000);
        config.setLeakDetectionThreshold(100000);
        config.setPoolName("InfiniHikariPool");
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        //before true
        config.addDataSourceProperty("useServerPrepStmts", "false");
        config.addDataSourceProperty("useLocalSessionState", "true");
        config.addDataSourceProperty("useLocalTransactionState", "true");
        //before true
        config.addDataSourceProperty("rewriteBatchedStatements", "false");
        config.addDataSourceProperty("cacheServerConfiguration", "true");
        config.addDataSourceProperty("cacheResultSetMetadata", "true");
        config.addDataSourceProperty("elideSetAutoCommits", "true");
        config.addDataSourceProperty("maintainTimeStats", "false");
        return config;
    }

    private void prepareTables() throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            final String[] databaseSchema = new String(Objects.requireNonNull(plugin.getResource("database/mysql_schema.sql")).readAllBytes(), StandardCharsets.UTF_8).split("--");
            try (Statement statement = connection.createStatement()) {
                for (String tableCreationStatement : databaseSchema) {
                    statement.execute(tableCreationStatement);
                }
            } catch (SQLException e) {
                throw new IllegalStateException("Failed SQL: " + e.getMessage() + ". Ensure syntax correctness.", e);
            }

        } catch (SQLException | IOException e) {
            throw new IllegalStateException("Failed to establish a connection to the MySQL database.", e);
        }

    }

    public void closeConnection() throws SQLException {
        dataSource.close();
    }

}