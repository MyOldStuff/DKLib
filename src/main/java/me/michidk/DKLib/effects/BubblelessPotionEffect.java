package me.michidk.DKLib.effects;

import com.comphenix.protocol.ProtocolLibrary;
import me.michidk.DKLib.packetWrapperReduced.WrapperPlayServerEntityEffect;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.lang.reflect.InvocationTargetException;

/**
 * give a player a potion effect, without bubbles
 * orginal idea by https://forums.bukkit.org/members/crazedev.90671369/
 *
 * @author crazedev
 *
 * rewritten by me
 *
 * YOU NEED PROTOCOLLIB FOR THIS CLASS!!!!
 */
public class BubblelessPotionEffect
{


    //Adds the potion effect without the graphical bubbles
    public static void addBubblelessPotionEffect(Player p, PotionEffect pe)
    {
        WrapperPlayServerEntityEffect packet = new WrapperPlayServerEntityEffect();
        packet.setEntityId(p.getEntityId());
        packet.setEffect(pe.getType());
        packet.setAmplifier((byte) pe.getAmplifier());
        packet.setDuration((short) pe.getDuration());

        try
        {
            ProtocolLibrary.getProtocolManager().sendServerPacket(p, packet.getHandle());
        }
        catch (InvocationTargetException e)
        {
            e.printStackTrace();
        }
    }

    //Remove the potion effect
    public static void removeBubblelessPotionEffect(Player p, PotionEffectType pe)
    {
        WrapperPlayServerEntityEffect packet = new WrapperPlayServerEntityEffect();
        packet.setEntityId(p.getEntityId());
        packet.setEffect(pe);

        try
        {
            ProtocolLibrary.getProtocolManager().sendServerPacket(p, packet.getHandle());
        }
        catch (InvocationTargetException e)
        {
            e.printStackTrace();
        }
    }


}
