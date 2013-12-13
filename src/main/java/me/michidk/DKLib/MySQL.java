package me.michidk.DKLib;

import org.bukkit.Bukkit;

import java.sql.*;
import java.util.logging.Level;


/**
 * Connects to and uses a MySQL database
 *
 * Modified by:
 * michidk
 *
 * Original by:
 * @author -_Husky_-
 * @author tips48
 */
public class MySQL {

    private MySQLData mySQLData;
    private Connection connection;

    /**
     * Creates a new MySQL instance
     *
     * @param mySQLData the mysql data for the connection
     */
    public MySQL(MySQLData mySQLData) {
        this.mySQLData = mySQLData;

        openConnection();
    }

    private Connection openConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://" + mySQLData.getHostname() + ":" + mySQLData.getPort() + "/" + mySQLData.getDatabase(), mySQLData.getUser(), mySQLData.getPassword());
        } catch (SQLException e) {
            Bukkit.getLogger().log(Level.SEVERE, "Could not connect to MySQL server! because: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            Bukkit.getLogger().log(Level.SEVERE, "JDBC Driver not found!");
        }

        if (connection == null)
        {
            Bukkit.getLogger().log(Level.SEVERE, "Could not connect to MySQL server!");
        }

        return connection;
    }

    public boolean checkConnection() {
        return connection != null;
    }

    public Connection getConnection() {
        return connection;
    }

    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                Bukkit.getLogger().log(Level.SEVERE, "Error closing the MySQL Connection!");
                e.printStackTrace();
            }
        }
    }

    public ResultSet querySQL(String query) {
        Connection c = null;

        if (checkConnection()) {
            c = getConnection();
        } else {
            c = openConnection();
        }

        Statement s = null;

        try {
            s = c.createStatement();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }

        ResultSet ret = null;

        try {
            ret = s.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        closeConnection();

        return ret;
    }

    public void updateSQL(String update) {

        Connection c = null;

        if (checkConnection()) {
            c = getConnection();
        } else {
            c = openConnection();
        }

        Statement s = null;

        try {
            s = c.createStatement();
            s.executeUpdate(update);
        } catch (SQLException e1) {
            e1.printStackTrace();
        }

        closeConnection();

    }

}