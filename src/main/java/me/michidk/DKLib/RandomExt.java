package me.michidk.DKLib;

import java.util.Random;

/**
 * This just encapsulates java.util.Random and provides some extra functions
 * @author Hellsing
 */
public class RandomExt
{
    private Random random;

    public RandomExt(Random random)
    {
        this.random = random;
    }

    public int randInt(int min, int max)
    {
        return min + random.nextInt(Math.abs(max - min + 1));
    }

    public double randDouble(double min, double max)
    {
        return min + random.nextDouble() * Math.abs(max - min);
    }

    public int randInt(int max)
    {
        return random.nextInt(max);
    }

    public double randDouble(double max)
    {
        return random.nextDouble() * max;
    }

    public boolean percentChance(double chance)
    {
        return random.nextDouble() < chance;
    }

    public Random getRandom()
    {
        return random;
    }
}
