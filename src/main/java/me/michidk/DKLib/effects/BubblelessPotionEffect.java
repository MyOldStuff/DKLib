package me.michidk.DKLib.effects;

import me.michidk.DKLib.DKLib;
import me.michidk.DKLib.libs.protocol.PacketType;
import me.michidk.DKLib.libs.protocol.event.ProtocolPacket;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

/**
 * give a player a potion effect, without bubbles
 * orginal idea by https://forums.bukkit.org/members/crazedev.90671369/
 *
 * @author crazedev
 *
 * rewritten by me
 */
public class BubblelessPotionEffect
{
    //Adds the potion effect without the graphical bubbles
    public static void addBubblelessPotionEffect(Player p, PotionEffect pe)
    {
        try {
            ProtocolPacket packet = new ProtocolPacket(PacketType.Server.ENTITY_EFFECT);
            packet.setInt(0, p.getEntityId());
            packet.setByte(0, (byte) (pe.getType().getId() & 0xFF));
            packet.setByte(1, (byte) (pe.getAmplifier() & 0xFF));
            if(pe.getDuration() > 32767){
                packet.setShort(0, (short) 32767);
            } else {
                packet.setShort(0, (short) pe.getDuration());
            }
            DKLib.getProtocolManager().sendPacket(packet.getHandle(), p);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    //Remove the potion effect
    public static void removeBubblelessPotionEffect(Player p, PotionEffectType pe)
    {
        try {
            ProtocolPacket packet = new ProtocolPacket(PacketType.Server.ENTITY_EFFECT);
            packet.setInt(0, p.getEntityId());
            packet.setByte(0, (byte) (pe.getId() & 0xFF));
            DKLib.getProtocolManager().sendPacket(packet.getHandle(), p);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
