package model;

import java.sql.*;
import java.util.Properties;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.

db.mysql.url="jdbc:mysql://localhost:3306/db?characterEncoding=UTF-8&useSSL=false"
*/

public class SQLConnector {

    /** The name of the MySQL account to use (or empty for anonymous) */
    private static final String userName = "root";

    /** The password for the MySQL account (or empty for anonymous) */
    private static final String password = "Laptop@1234";

    /** The name of the computer running MySQL */
    private static final String serverName = "localhost";

    /** The port of the MySQL server (default is 3306) */
    private static final int portNumber = 3306;

    /** The name of the database we are testing with (this default is installed with MySQL) */
    private static final String dbName = "movie_review";

    private static Connection mySQLconnection;

    /**
     * Get a new database connection
     *
     * @return
     * @throws SQLException
     */
    public static void createConnection() throws SQLException {
        Properties connectionProps = new Properties();
        connectionProps.put("user", userName);
        connectionProps.put("password", password);

        SQLConnector.mySQLconnection = DriverManager.getConnection("jdbc:mysql://" + serverName + ":" + portNumber +
                "/" + dbName + "?characterEncoding=UTF-8&useSSL=false", connectionProps);
    }

    public static ResultSet fetchValues(String sql) {
        ResultSet rs = null;
        try {
            Statement stmt = mySQLconnection.createStatement();
            rs = stmt.executeQuery(sql);
        } catch (SQLException ignored) {
            System.out.println(ignored);
        }
        return rs;
    }

    public static boolean createRecord(String sql) {
        try {
            Statement stmt = mySQLconnection.createStatement();
            stmt.executeUpdate(sql);
            return true;
        } catch (SQLException ignored) {
            return false;
        }
    }

    public static boolean updateRecord(String sql) {
        try {
            Statement stmt = mySQLconnection.createStatement();
            stmt.executeUpdate(sql);
            return true;
        } catch (SQLException ignored) {
            return false;
        }
    }

    public static boolean deleteRecord(String sql) {
        try {
            Statement stmt = mySQLconnection.createStatement();
            stmt.executeUpdate(sql);
            return true;
        } catch (SQLException ignored) {
            return false;
        }
    }
}


