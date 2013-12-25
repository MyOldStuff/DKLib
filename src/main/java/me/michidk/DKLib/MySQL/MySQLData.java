package me.michidk.DKLib.MySQL;

import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.bukkit.configuration.serialization.SerializableAs;

import java.util.HashMap;
import java.util.Map;

/**
 * Copyright by michidk
 * Date: 13.12.13
 * Time: 13:26
 */
@SerializableAs("MySQL")
public class MySQLData implements ConfigurationSerializable
{

    private final String hostname;
    private final String port;
    private final String database;
    private final String user;
    private final String password;

    static
    {
        ConfigurationSerialization.registerClass(MySQLData.class);
    }

    public MySQLData(String hostname, String port, String database, String username, String password)
    {
        this.hostname = hostname;
        this.port = port;
        this.database = database;
        this.user = username;
        this.password = password;
    }

    public  MySQLData(Map<String, Object> result)
    {
        this.hostname = (String) result.get("hostname");
        this.port = (String) result.get("port");
        this.database = (String) result.get("database");
        this.user = (String) result.get("user");
        this.password = (String) result.get("password");
    }

    @Override
    public Map<String, Object> serialize()
    {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("hostname", hostname);
        result.put("port", port);
        result.put("database", database);
        result.put("user", user);
        result.put("password", password);
        return result;
    }

    public String getHostname()
    {
        return hostname;
    }

    public String getPort()
    {
        return port;
    }

    public String getDatabase()
    {
        return database;
    }

    public String getUser()
    {
        return user;
    }

    public String getPassword()
    {
        return password;
    }
}
