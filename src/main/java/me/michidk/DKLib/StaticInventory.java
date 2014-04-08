package me.michidk.DKLib;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.HashMap;

/**
 * this class is usefull for lobby servers, in which players should always have the same inventory
 * this class take care, that players dont throw items away, and that the items are always on the right position
 * it also handles rejoin (get same inventory as before)
 *
 * @author michidk
 */
public class StaticInventory implements Listener
{

    private Plugin plugin;
    private boolean enabled = true;

    private Inventory defaultInventory;
    HashMap<String, Inventory> cache = new HashMap<>(); //player as string, his cached inventory

    /**
     *
     * @param plugin                the instance of your "extends JavaPlugin" class
     * @param defaultInventory      the default inventory, that every player gets (if its not modified for that player)
     */
    public StaticInventory(Plugin plugin, Inventory defaultInventory)
    {
        this.plugin = plugin;
        enabled = false;

        this.plugin.getServer().getPluginManager().registerEvents(this, plugin);

        this.defaultInventory = this.defaultInventory;

        for (Player p: Bukkit.getOnlinePlayers())
        {
            setCurrentInventory(p, defaultInventory);
        }
    }


    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent e)
    {
        if (!enabled) return;

        updateCurrentInventory(e.getPlayer());
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e)
    {
        if (!enabled) return;

        e.setCancelled(true);
        e.getWhoClicked().closeInventory();
    }

    @EventHandler
    public void onPlayerDrop(PlayerDropItemEvent e)
    {
        if (!enabled) return;

        e.setCancelled(true);
        updateCurrentInventory(e.getPlayer());
    }

    @EventHandler
    public void onPickUp(PlayerPickupItemEvent e)
    {
        if (!enabled) return;

        e.setCancelled(true);
    }



    /**
     * add a player to the manager...
     * private: because player will be added if he joins
     * @param player                the player that should be added
     */
    private void addPlayer(Player player)
    {
        setCurrentInventory(player, defaultInventory);
    }

    /**
     *
     * @param player                that player, whos inventory should be modified
     * @param slot                  the number of the slot (begins with 0)
     * @param item                  the item, that should set to the slot
     */
    public void changeSlot(Player player, int slot, ItemStack item)
    {
        Inventory tempInv = getCurrentInventory(player.getName());
        tempInv.setItem(slot, item);
        setCurrentInventory(player, tempInv);
    }

    /**
     * @return                      the inventory of the @param player
     */
    public Inventory getCurrentInventory (String player)
    {
        return cache.get(player);
    }

    /**
     * modify the current Inventory of a player
     */
    public void setCurrentInventory (Player player, Inventory inventory)
    {
        cache.put(player.getName(), inventory);
        updateCurrentInventory(player);
    }

    /**
     * renew
     */
    public void updateCurrentInventory(Player player)
    {
        Inventory currentInventory = cache.get(player.getName());
        player.getInventory().setContents(currentInventory.getContents());
        player.updateInventory();
    }

    /**
     * @return                      the default inventory, that was defined on init
     */
    public Inventory getDefaultInventory()
    {
        return defaultInventory;
    }

    public boolean isEnabled()
    {
        return enabled;
    }

    public void enable()
    {
        this.enabled = true;
    }

    public void disable()
    {
        this.enabled = false;
    }
}
