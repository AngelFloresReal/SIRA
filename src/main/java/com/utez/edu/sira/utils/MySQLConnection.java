package com.utez.edu.sira.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class MySQLConnection {
    private static final String BUNDLE_NAME = "db_connection";
    private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME);

    public static Connection getConnections() {
        Connection connection = null;
        try{
            String url = RESOURCE_BUNDLE.getString("url");
            String username = RESOURCE_BUNDLE.getString("username");
            String password = RESOURCE_BUNDLE.getString("password");
            Class.forName(RESOURCE_BUNDLE.getString("driver"));
            connection = DriverManager.getConnection(url, username, password);
        }catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
        return connection;
    }
}

