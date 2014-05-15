package me.michidk.DKLib.event;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

/**
 * Copyright by michidk
 * Date: 22.12.13
 * Time: 22:31
 * <p/>
 * ATTENTION:
 * before using any events of this lib, register the EventCaller as Listener or install DKLib on your server
 */
public class EventCaller implements Listener
{

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onPlayerMove(PlayerMoveEvent event)
    {

        if ((event.getFrom().getBlockX() != event.getTo().getBlockX()) || (event.getFrom().getBlockY() != event.getTo().getBlockY()) || (event.getFrom().getBlockZ() != event.getTo().getBlockZ()))
        {
            PlayerBlockMoveEvent call = new PlayerBlockMoveEvent(event.getPlayer(), event.getFrom(), event.getTo());
            Bukkit.getPluginManager().callEvent(call);
            if (call.isCancelled()) event.getPlayer().teleport(event.getFrom());
        }

        if ((event.getFrom().getBlockX() != event.getTo().getBlockX()) ||  (event.getFrom().getBlockZ() != event.getTo().getBlockZ()))
        {
            PlayerBlockHorzMoveEvent call = new PlayerBlockHorzMoveEvent(event.getPlayer(), event.getFrom(), event.getTo());
            Bukkit.getPluginManager().callEvent(call);
            if (call.isCancelled()) event.getPlayer().teleport(event.getFrom());
        }

        //old code
        /*if (event.getFrom().getBlockX() != event.getTo().getBlockX() || event.getFrom().getBlockY() != event.getTo().getBlockY()
                || event.getFrom().getBlockZ() != event.getTo().getBlockZ())
        {
            // Players block location changed
            PlayerBlockMoveEvent moveEvent = new PlayerBlockMoveEvent(event.getPlayer(), event.getFrom(), event.getTo());
            Bukkit.getPluginManager().callEvent(moveEvent);

            // Cancel the move events depending on the result
            event.setCancelled(moveEvent.isCancelled());
        }*/
    }


}
