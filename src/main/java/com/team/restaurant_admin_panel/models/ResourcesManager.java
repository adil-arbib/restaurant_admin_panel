package com.team.restaurant_admin_panel.models;

import java.sql.*;

public final class ResourcesManager {

    private static Connection connection;

    private static final String SERVAR_NAME = "localhost";
    private static final String DB_NAME = "restaurant";
    private static final String URL = "jdbc:mysql://" + SERVAR_NAME + "/" + DB_NAME;
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";


    private ResourcesManager(){}

    public static void connect() throws SQLException {
        connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
    }

    public static ResultSet executeQuery(String query) throws SQLException {
        if(connection == null) connect();
        Statement statement = connection.createStatement();
        return statement.executeQuery(query);
    }

    public static Connection getConnection() throws SQLException {
        if(connection == null) connect();
        return connection;
    }



    public static void close() throws SQLException {
        connection.close();
    }


}
