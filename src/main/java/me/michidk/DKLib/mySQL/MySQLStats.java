package me.michidk.DKLib.mySQL;

import org.bukkit.Bukkit;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;

/**
 * Copyright by michidk
 * Date: 13.12.13
 * Time: 13:26
 */
public class MySQLStats
{

    private MySQLData mySQLData;
    private MySQL mySQL;

    /**
     * init a MySQLStat connection
     * you only have to do this once
     *
     * @param mySQLData the mysql data with the sql connection data
     */
    public MySQLStats(MySQLData mySQLData)
    {
        this.mySQLData = mySQLData;
        mySQL = new MySQL(mySQLData);
    }

    /**
     * increase the stats
     *
     * @param table the table in which are the stats saved e.g SurvivalGamesStats
     * @param name  the name of the object for the stats e.g. playerName
     * @param key   the key which should be increased e.g kills
     * @param value the value which is add to the stats, e.g 1
     */
    public void add(String table, String name, String key, int value)
    {

        set(table, name, key, get(table, name, key) + value);
    }

    /**
     * increase the stats +1
     */
    public void add(String table, String name, String key)
    {
        add(table, name, key, 1);
    }

    /**
     * decrease the stats
     */
    public void remove(String table, String name, String key, int value)
    {
        set(table, name, key, get(table, name, key) - value);
    }

    private int get(String table, String name, String key)
    {
        int result = 0;
        try
        {

            //select key
            PreparedStatement ps = mySQL.getConnection().prepareStatement("SELECT " + key + " FROM " + table + " WHERE name = ?;");
            ps.setString(1, name);

            //get key
            ResultSet results = ps.executeQuery();
            if (!results.next())
            {
                result = 0;
            }
            else
            {
                result = results.getInt(1);
            }

        }
        catch (SQLException e)
        {
            Bukkit.getLogger().log(Level.SEVERE, "Could not send Stats to MySQL! because: " + e.getMessage());
        }
        return result;
    }

    private void set(String table, String name, String key, int value)
    {
        try
        {

            //select key
            PreparedStatement ps = mySQL.getConnection().prepareStatement("SELECT " + key + " FROM " + table + " WHERE name = ?;");
            ps.setString(1, name);

            //get key
            ResultSet results = ps.executeQuery();
            if (!results.next())
            {

                //neu eintragen
                PreparedStatement ps2 = mySQL.getConnection().prepareStatement("INSERT INTO " + table + " VALUES(?, ?);");
                ps2.setString(1, key);
                ps2.setInt(2, value);
                ps2.executeUpdate();

            }
            else
            {

                //setzen / updaten
                PreparedStatement ps2 = mySQL.getConnection().prepareStatement("UPDATE " + table + " SET coins=? WHERE name=?;");
                ps2.setString(1, key);
                ps2.setInt(2, value);
                ps2.executeUpdate();

            }

        }
        catch (SQLException e)
        {
            Bukkit.getLogger().log(Level.SEVERE, "Could not send Stats to MySQL! because: " + e.getMessage());
        }
    }
}
