package com.revature.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtility {

    private ConnectionUtility(){}
    public static Connection instance;

    private final static String url = "";
    private final static String user = "";
    private final static String pass = "";

    public static Connection getConnection() throws SQLException {

        if(instance == null || instance.isClosed()) {
            instance = DriverManager.getConnection(url, user, pass);
        }
        return instance;
    }
}
