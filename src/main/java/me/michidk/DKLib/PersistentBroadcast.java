package me.michidk.DKLib;

import me.michidk.DKLib.utils.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.Plugin;

import java.util.Arrays;
import java.util.List;

/**
 * Copyright by michidk
 * Created: 28.09.2014.
 */
public class PersistentBroadcast implements Listener {

    private List<Player> players;
    private String[] messages;
    private int time;  //in seconds

    private int resendRate = 5; //one message per 5 seconds

    private Plugin plugin;
    private int schedulerID;

    public PersistentBroadcast(Plugin plugin, String[] messages, int time) {
        this(plugin, Bukkit.getOnlinePlayers(), messages, time);
    }

    public PersistentBroadcast(Plugin plugin, Player[] players, String[] messages, int time) {
        this(plugin, Arrays.asList(players), messages, time);
    }

    public PersistentBroadcast(Plugin plugin, List<Player> players, String[] messages, int time) {
        this.players = players;
        this.messages = messages;
        this.time = time;

        Bukkit.getPluginManager().registerEvents(this, plugin);
        schedulerID = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new BroadcastRunnable(this), 0L, 20L);
        sendMessages();
    }

    public void sendMessages() {
        for (Player p:players) {
            ChatUtils.broadcastCentered(p, messages);
        }
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        if (players.contains(e.getPlayer())) {
            e.setCancelled(true);
        }
    }

    public void end() {
        HandlerList.unregisterAll(this);
        Bukkit.getScheduler().cancelTask(schedulerID);
    }

    public List<Player> getPlayers() {
        return players;
    }

    public String[] getMessages() {
        return messages;
    }

    //will be called once per second
    class BroadcastRunnable implements Runnable {

        private PersistentBroadcast persistentBroadcast;
        int time;
        int resend;

        public BroadcastRunnable(PersistentBroadcast persistentBroadcast) {
            this.persistentBroadcast = persistentBroadcast;
            time = persistentBroadcast.time;
            resend = persistentBroadcast.resendRate;
        }

        @Override
        public void run() {
            if (resend <= 0) {
                resend = resendRate;
                persistentBroadcast.sendMessages();
            }
            resend--;

            if (time <= 0) {
                persistentBroadcast.end();
            }
            time--;
        }
    }

}
