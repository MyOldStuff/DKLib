package me.michidk.DKLib.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * ChatUtils
 * <p/>
 * Modified by:
 * michidk
 * <p/>
 * Original by:
 *
 * @author HeIIsing
 */

public class ChatUtils
{

    /**
     * sends a empty line to a player
     *
     * @param player if null the message is send to all online players
     * @param lines  number of lines
     */
    public static void broadcastEmptyLines(Player player, int lines)
    {
        List<Player> pList = new ArrayList<>();

        if (player == null)
            pList = Arrays.asList(Bukkit.getOnlinePlayers());
        else
            pList.add(player);

        for (Player p : pList)
            for (int i = 0; i < lines; i++)
                p.sendMessage("");

    }

    /**
     * send a centered message that fills the full chat window
     *
     * @param player  if null the message is send to all online players
     * @param message the messages
     * @author HeIIsing
     */
    public static void broadcastCentered(Player player, String message)
    {
        String[] messages = {message};
        broadcastCentered(player, messages);
    }

    /**
     * sends centered messages that fills the full chat window
     *
     * @param player  if null the message is send to all online players
     * @param message the messages as String[]
     * @author HeIIsing
     */
    public static void broadcastCentered(Player player, String[] message)
    {
        List<Player> pList = new ArrayList<>();

        if (player == null)
            pList = Arrays.asList(Bukkit.getOnlinePlayers());
        else
            pList.add(player);


        if (message.length < 9)
        {
            broadcastEmptyLines(player, (int) Math.ceil(((10 - message.length) / 2d)));
            for (String line : message)
                for (Player p : pList)
                    p.sendMessage(line);
            broadcastEmptyLines(player, (int) Math.floor(((10 - message.length) / 2d)));
        }
        else
            for (String line : message)
                for (Player p : pList)
                    p.sendMessage(line);
    }

    /**
     * replaces all color codes with chat colors
     *
     * @param message the message with color codes
     * @return the message with chat colors
     */
    public static String replaceColorCodes(String message)
    {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    /**
     * remove all colors from message
     *
     * @param message string that contains the color codes
     * @return string without color codes
     */
    public static String removeColorCodes(String message)
    {
        return ChatColor.stripColor(message);
    }


}
