package me.michidk.DKLib.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

/**
 * @author michidk
 */

public class DebugHelper
{

    private static void message(String message)
    {
        System.out.println(message);
        for(Player player: Bukkit.getOnlinePlayers()) player.sendMessage(message);
    }

    public static String debug(String message)
    {
        String debugMessage = getMessage(message);
        message(debugMessage);
        return debugMessage;
    }

    public static void debug()
    {
        debug(null);
    }

    public static String getMessage(String message)
    {
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();

        StringBuilder sb = new StringBuilder();
        sb.append("debug");

        if (message != null) sb.append("(" + message+ ")");

        sb.append(":");
        sb.append(stackTraceElements[1].toString());

        return sb.toString();
    }

}
