package me.michidk.DKLib.utils;


import net.minecraft.server.v1_7_R2.EntityPlayer;
import net.minecraft.server.v1_7_R2.MinecraftServer;
import net.minecraft.server.v1_7_R2.PlayerInteractManager;
import net.minecraft.util.com.mojang.authlib.GameProfile;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_7_R2.CraftServer;
import org.bukkit.entity.Player;

import java.io.File;

/**
 * Copyright by michidk
 * Date: 09.01.14
 * Time: 21:44
 */

public class PlayerUtil
{

    /**
     * Returns an offline player that can be manipulated exactly like an online player.
     *
     * @param player Playername to retrieve.
     * @return The player object. Null if we can't find the player's data.
     * from Tux2.TuxTwoLib;
     */
    public static Player getOfflinePlayer(String player)
    {
        Player pplayer = null;
        try
        {
            //See if the player has data files

            // Find the player folder
            File playerfolder = new File(Bukkit.getWorlds().get(0).getWorldFolder(), "players");

            // Find player name
            for (File playerfile : playerfolder.listFiles())
            {
                String filename = playerfile.getName();
                String playername = filename.substring(0, filename.length() - 4);

                if (playername.trim().equalsIgnoreCase(player))
                {
                    //This player plays on the server!
                    MinecraftServer server = ((CraftServer) Bukkit.getServer()).getServer();
                    EntityPlayer entity = new EntityPlayer(server, server.getWorldServer(0), new GameProfile(null, playername), new PlayerInteractManager(server.getWorldServer(0)));
                    Player target = entity.getBukkitEntity();
                    if (target != null)
                    {
                        target.loadData();
                        return target;
                    }
                }
            }
        }
        catch (Exception e)
        {
            return null;
        }
        return pplayer;
    }
}
