package me.michidk.DKLib.effects;

import me.michidk.DKLib.packetWrapperReduced.WrapperPlayServerEntityEffect;
import com.comphenix.protocol.ProtocolLibrary;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.Set;

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

    //Stores the players recently used effects
    private Set<Integer> playersBuffs = new HashSet<Integer>();

    //Adds the potion effect without the graphical bubbles
    public void addBubblelessPotionEffect(Player p, PotionEffect pe)
    {
        playersBuffs.add(pe.getType().getId());

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
    public void removeBubblelessPotionEffect(Player p, PotionEffectType pe)
    {
        playersBuffs.remove(pe.getId());

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

    //Removes all of the players stored potion effects
    public void removeAllBubblelessPotionEffect(Player p)
    {
        for (Integer i : playersBuffs)
        {
            removeBubblelessPotionEffect(p, PotionEffectType.getById(i));
            playersBuffs.remove(i);
        }
    }

    //Checks if a player has a certain potion effect
    public boolean hasBubblelessPotionEffect(PotionEffectType pet)
    {
        if (playersBuffs.contains(pet.getId()))
        {
            return true;
        }
        else
        {
            return false;
        }
    }


}
