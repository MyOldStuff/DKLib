package me.michidk.DKLib.event;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;

/**
 * block move event from hCore
 * @author Hellsing
 *
 * ATTENTION:
 * before using this event you must, register the EventCaller as Listener
 */
public class PlayerBlockMoveEvent extends PlayerEvent implements Cancellable
{
    private static final HandlerList handlers = new HandlerList();

    private Location from;
    private Location to;

    private boolean cancel;

    public PlayerBlockMoveEvent(Player who, Location from, Location to)
    {
        super(who);

        this.from = from;
        this.to = to;
    }

    public Location getFrom()
    {
        return from;
    }

    public Location getTo()
    {
        return to;
    }

    @Override
    public boolean isCancelled()
    {
        return cancel;
    }

    @Override
    public void setCancelled(boolean cancel)
    {
        this.cancel = cancel;
    }

    @Override
    public HandlerList getHandlers()
    {
        return handlers;
    }

    public static HandlerList getHandlerList()
    {
        return handlers;
    }
}
