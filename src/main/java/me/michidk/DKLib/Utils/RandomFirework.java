package me.michidk.DKLib.utils;

import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.meta.FireworkMeta;

import java.util.Random;

/**
 * Copyright by michidk
 * Date: 21.11.13
 * Time: 18:05
 */
public class RandomFirework
{

    private static final int RGB_MAX = 255;

    private static Random r = new Random();

    /**
     * spawns a random firework (or only the explosion effect)
     *
     * @param location the location where the firework spawns
     */
    public static void spawnRandomFirework(Location location)
    {
        FireworkEffect fe = getRandomFireworkEffect();

        org.bukkit.entity.Firework fw = (org.bukkit.entity.Firework) location.getWorld().spawnEntity(location, EntityType.FIREWORK);
        FireworkMeta fm = fw.getFireworkMeta();

        fm.addEffect(fe);

        int rp = r.nextInt(2) + 1;
        fm.setPower(rp);

        fw.setFireworkMeta(fm);

    }

    /**
     * generates a random FireworkEffect
     *
     * @return random FireworkEffect
     */
    public static FireworkEffect getRandomFireworkEffect()
    {
        Color color = getRandomBukkitColor();
        Color fade = getRandomBukkitColor();

        int rt = r.nextInt(3) + 1;
        FireworkEffect.Type type = FireworkEffect.Type.BALL;
        if (rt == 1) type = FireworkEffect.Type.BALL;
        if (rt == 2) type = FireworkEffect.Type.BALL_LARGE;
        if (rt == 3) type = FireworkEffect.Type.BURST;
        if (rt == 4) type = FireworkEffect.Type.CREEPER;

        return FireworkEffect.builder().flicker(r.nextBoolean()).withColor(color).withFade(fade).with(type).trail(r.nextBoolean()).build();
    }

    /**
     * @return a random Color
     */
    public static Color getRandomColor()
    {
        return Color.fromRGB(r.nextInt(RGB_MAX), r.nextInt(RGB_MAX), r.nextInt(RGB_MAX));
    }

    /**
     * @return a random predefined Color
     */
    public static Color getRandomBukkitColor()
    {
        Color c = Color.AQUA;
        int rt = r.nextInt(16);
        switch (rt)
        {
            case 0:
                c = Color.WHITE;
            case 1:
                c = Color.SILVER;
            case 2:
                c = Color.GRAY;
            case 3:
                c = Color.BLACK;
            case 4:
                c = Color.RED;
            case 5:
                c = Color.MAROON;
            case 6:
                c = Color.YELLOW;
            case 7:
                c = Color.OLIVE;
            case 8:
                c = Color.LIME;
            case 9:
                c = Color.GREEN;
            case 10:
                c = Color.AQUA;
            case 11:
                c = Color.TEAL;
            case 12:
                c = Color.BLUE;
            case 13:
                c = Color.NAVY;
            case 14:
                c = Color.FUCHSIA;
            case 15:
                c = Color.PURPLE;
            case 16:
                c = Color.ORANGE;
        }

        return c;
    }
}
