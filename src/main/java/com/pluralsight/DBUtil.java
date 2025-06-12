package com.pluralsight;


import org.apache.commons.dbcp2.BasicDataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DBUtil {
    private static BasicDataSource dataSource;

    static {
        dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/CarDealership");
        dataSource.setUsername("root");
        dataSource.setPassword("sifre"); // Şifreni güncelle
    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}