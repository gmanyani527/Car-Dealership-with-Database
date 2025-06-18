package database;

import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;

public class DatabaseConfig {
    private static final BasicDataSource dataSource = new BasicDataSource();

    static {
        dataSource.setUrl("jdbc:mysql://localhost:3306/car_dealership"); // your DB name
        dataSource.setUsername("root");
        dataSource.setPassword("P@ssw0rd");

        // Optional: connection pool settings
        dataSource.setMinIdle(5);
        dataSource.setMaxIdle(10);
        dataSource.setMaxOpenPreparedStatements(100);
    }

    public static DataSource getDataSource() {
        return dataSource;
    }
}
