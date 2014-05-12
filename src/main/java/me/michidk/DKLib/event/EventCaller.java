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
 *
 * ATTENTION:
 * before using any events of this lib, register the EventCaller as Listener
 */
public class EventCaller implements Listener
{

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onPlayerMove(PlayerMoveEvent event)
    {
        if((event.getFrom().getBlockX() != event.getTo().getBlockX()) || (event.getFrom().getBlockY() != event.getTo().getBlockY()) || (event.getFrom().getBlockZ() != event.getTo().getBlockZ())){
            PlayerBlockMoveEvent call = new PlayerBlockMoveEvent(event.getPlayer(), event.getFrom(), event.getTo());
            Bukkit.getPluginManager().callEvent(call);
            event.setCancelled(call.isCancelled());
        }
    }


}
