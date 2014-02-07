package me.michidk.DKLib;

import java.io.*;

/**
 * @author michidk
 */

public class FileHelper
{


    public static void stringToFile(File file, String string)
    {
        BufferedWriter writer = null;
        try
        {
            writer = new BufferedWriter(new FileWriter(file));
            writer.write(string);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                if (writer != null) {
                    writer.close();
                }
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    public static String stringFromFile(File file)
    {
        String line;
        StringBuilder stringBuilder = new StringBuilder();

        BufferedReader reader;
        try
        {
            reader = new BufferedReader(new FileReader(file));

            while ((line = reader.readLine()) != null)
            {
                stringBuilder.append(line);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return stringBuilder.toString();
    }

}

