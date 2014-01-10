package me.michidk.DKLib.utils;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * Copyright by michidk
 * Date: 09.01.14
 * Time: 22:40
 */

public class LocationUtil
{

    /**
     * calculates vectors in the near of a vector
     *
     * @param vector - the start vector (the mid)
     * @param radius - the radius of the circle
     * @param hight  - the hight
     * @param hollow - if true the method returns only the outside layer
     * @param sphere - if true the vectors a spherelike
     * @return - a list of vectors
     */
    public static List<Vector> getCircleVectors(Vector vector, Integer radius, Integer hight, Boolean hollow, Boolean sphere)
    {
        List<Vector> vectors = new ArrayList<Vector>();
        int cx = vector.getBlockX();
        int cy = vector.getBlockY();
        int cz = vector.getBlockZ();
        for (int x = cx - radius; x <= cx + radius; x++)
            for (int z = cz - radius; z <= cz + radius; z++)
                for (int y = (sphere ? cy - radius : cy); y < (sphere ? cy + radius : cy + hight); y++)
                {
                    double dist = (cx - x) * (cx - x) + (cz - z) * (cz - z) + (sphere ? (cy - y) * (cy - y) : 0);
                    if (dist < radius * radius && !(hollow && dist < (radius - 1) * (radius - 1)))
                    {
                        vectors.add(new Vector(x, y, z));
                    }
                }

        return vectors;
    }

    /**
     * calculates the surrounding blocks of a block
     *
     * @param block     - the start block
     * @param blacklist - the blocks with on of these materials are not in the List
     * @return - a list of surrounding blocks from block, without the blocks that have a material from the blacklist
     */
    public List<Block> getSurroundingBlocks(Block block, Material[] blacklist)
    {
        HashSet<Block> blocks = new HashSet<Block>();

        BlockFace[] faces = new BlockFace[]{
                BlockFace.UP,
                BlockFace.DOWN,
                BlockFace.NORTH,
                BlockFace.EAST,
                BlockFace.SOUTH,
                BlockFace.WEST
        };

        List<Material> bannedList = Arrays.asList(blacklist);

        for (BlockFace f : faces)
        {
            Block s = block.getRelative(f);
            if (!bannedList.contains(s.getType()))
            {
                blocks.add(s);
            }
        }

        List<Block> blockList = new ArrayList<Block>();

        for (Block bloc : blocks)
        {
            blockList.add(bloc);
        }

        return blockList;
    }


}
