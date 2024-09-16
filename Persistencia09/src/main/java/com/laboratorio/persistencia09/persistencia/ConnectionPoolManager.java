package com.laboratorio.persistencia09.persistencia;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.Duration;
import org.apache.commons.dbcp2.BasicDataSource;

public class ConnectionPoolManager {
    private static BasicDataSource dataSource;
    
    static {
        dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/crud");
        dataSource.setUsername("root");
        dataSource.setPassword("1234");
        dataSource.setMaxIdle(3);
        dataSource.setMaxWait(Duration.ofSeconds(5));
    }
    
    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}