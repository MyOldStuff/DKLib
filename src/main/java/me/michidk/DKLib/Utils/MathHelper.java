package me.michidk.DKLib.utils;

/**
 * Copyright by michidk
 * Date: 09.01.14
 * Time: 21:55
 */

public class MathHelper
{

    /**
     * Round a double
     *
     * @param d - the double to round
     * @return - the rounded double as integer
     */
    public static int round(double d)
    {
        int i = (int) d;
        double result = d - i;
        if (result < 0.5D) return i;
        return i + 1;
    }

    /**
     * get the percentage: i from max
     * example use: progressbar
     *
     * @param current - the the current index
     * @param max     - the max index
     * @return
     */
    public static double getPercentage(int current, int max)
    {
        return Math.round(((double) current / (double) max) * 100D) / 100D;
    }

}
