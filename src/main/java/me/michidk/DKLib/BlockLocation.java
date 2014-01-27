package me.michidk.DKLib;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;

/**
 * stores the location of a block
 *
 * @author michidk
 */
public class BlockLocation
{

    private int x;
    private int y;
    private int z;
    private String world;

    public BlockLocation()
    {

    }

    /**
     *
     * @param x - the x-coordinate
     * @param y - the y-coordinate
     * @param z - the z-coordinate
     * @param world - the name of the world
     */
    public BlockLocation(int x, int y, int z, String world)
    {
        this.x = x;
        this.y = y;
        this.z = z;
        this.world = world;
    }

    /**
     *
     * @param x - the x-coordinate
     * @param y - the y-coordinate
     * @param z - the z-coordinate
     * @param world - the world
     */
    public BlockLocation(int x, int y, int z, World world)
    {
        this.x = x;
        this.y = y;
        this.z = z;
        this.world = world.getName();
    }

    /**
     *
     * @return - the bukkit block
     */
    public Block toBlock()
    {
        return getWorld().getBlockAt(x, y, z);
    }

    /**
     *
     * @return - the location of the block
     */
    public Location toLocation()
    {
        return toBlock().getLocation();
    }

    /**
     * create a new BlockLocation from a existing location
     *
     * @param location - the location
     * @return - the block object
     */
    public static BlockLocation fromLocation(Location location)
    {
        return new BlockLocation(location.getBlockX(), location.getBlockY(), location.getBlockZ(), location.getWorld().getName());
    }

    /**
     * create a new BlockLocation from a existing block
     *
     * @param block - the block
     * @return - the block object
     */
    public static BlockLocation fromBlock(Block block)
    {
        return new BlockLocation().fromLocation(block.getLocation());
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    public int getZ()
    {
        return z;
    }

    /**
     *
     * @return - returns the name of the world
     */
    public String getWorldName()
    {
        return world;
    }

    /**
     *
     * @return - retrns the bukkit world
     */
    public World getWorld()
    {
        return Bukkit.getWorld(getWorldName());
    }
}
