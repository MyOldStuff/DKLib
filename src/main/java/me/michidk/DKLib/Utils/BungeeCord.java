package me.michidk.DKLib.utils;

import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Copyright by michidk
 * Date: 09.01.14
 * Time: 22:09
 */

public class BungeeCord
{

    /**
     * Move a player to another server
     *
     * @param plugin     - your plugin
     * @param serverName - the name of the server, from the bungeecord config, to connect
     * @param player     - the player that sould be moved to the server
     */
    public static void connect(Plugin plugin, String serverName, Player player)
    {
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(b);

        try
        {
            out.writeUTF("Connect");
            out.writeUTF(serverName);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        player.sendPluginMessage(plugin, "BungeeCord", b.toByteArray());
    }

}
