package me.michidk.DKLib.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

/**
 * Copyright by michidk
 * Date: 09.01.14
 * Time: 22:33
 */

public class XPUtil
{

    /**
     * set the xp bar progress
     *
     * @param p       - the players, whos xp bar will be changed
     * @param current - the current progress
     * @param max     - the max progress
     *                <p/>
     *                if you want to use a percentage, set max to 100
     *                then is current your percentage
     */
    public static void setXPBarProgress(Player p, int current, int max)
    {
        p.setLevel(current);
        p.setExp(1 - (float) (MathHelper.getPercentage(current, max)));
    }

    public static void setXPBarProgress(int current, int max)
    {
        for (Player p : Bukkit.getOnlinePlayers())
            setXPBarProgress(p, current, max);
    }

}
