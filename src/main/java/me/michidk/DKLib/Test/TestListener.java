package me.michidk.DKLib.Test;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

/**
 * Copyright by michidk
 * Date: 01.12.13
 * Time: 18:13
 */
public class TestListener implements Listener
{

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e)
    {
        Bukkit.broadcastMessage("TÄÄSCHD!");
    }

}
