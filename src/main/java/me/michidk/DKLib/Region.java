package me.michidk.DKLib;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Region.java by Hellsing modified by michidk
 * https://github.com/michidk/hCore/blob/master/src/main/java/me/hellsing/hCore/Region.java
 */
public class Region implements ConfigurationSerializable
{
    private String regionName;
    private String worldName;

    private Vector min;
    private Vector max;

    public Region(String regionName, String worldName, Vector min, Vector max)
    {
        this.regionName = regionName.toLowerCase();
        this.worldName = worldName;
        this.min = min;
        this.max = max;
    }

    public Region(Map<String, Object> map)
    {
        this.regionName = (String) map.get("region-name");
        this.worldName = (String) map.get("world-name");
        this.min = (Vector) map.get("min");
        this.max = (Vector) map.get("max");
    }

    public String getRegionName()
    {
        return regionName;
    }

    public boolean isInside(Player player)
    {
        if (player.getWorld().getName().equals(worldName))
        {
            double x = player.getLocation().getX();
            double y = player.getLocation().getY();
            double z = player.getLocation().getZ();

            if (x >= min.getBlockX() && x <= max.getBlockX() && y >= min.getBlockY() && y <= max.getBlockY() && z >= min.getBlockZ() && z <= max.getBlockZ())
                return true;
        }

        return false;
    }

    public List<Player> getPlayers()
    {
        World world = Bukkit.getWorld(worldName);
        List<Player> playerList = new ArrayList<Player>();

        if (world != null)
        {
            for (Player player : world.getPlayers())
            {
                if (isInside(player))
                {
                    playerList.add(player);
                }
            }
        }

        return playerList;
    }

    @Override
    public Map<String, Object> serialize()
    {
        Map<String, Object> result = new LinkedHashMap<String, Object>();

        result.put("region-name", regionName);
        result.put("world-name", worldName);
        result.put("min", min.serialize());
        result.put("max", max.serialize());

        return result;
    }

}
