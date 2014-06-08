/*
 *  PacketWrapper - Contains wrappers for each packet in Minecraft.
 *  Copyright (C) 2012 Kristian S. Stangeland
 *
 *  This program is free software; you can redistribute it and/or modify it under the terms of the 
 *  GNU Lesser General Public License as published by the Free Software Foundation; either version 2 of 
 *  the License, or (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; 
 *  without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. 
 *  See the GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License along with this program; 
 *  if not, write to the Free Software Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 
 *  02111-1307 USA
 */

package me.michidk.DKLib.packetWrapperReduced;

import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.potion.PotionEffectType;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;

public class WrapperPlayServerRemoveEntityEffect extends AbstractPacket {
    public static final PacketType TYPE = PacketType.Play.Server.REMOVE_ENTITY_EFFECT;

    public WrapperPlayServerRemoveEntityEffect() {
        super(new PacketContainer(TYPE), TYPE);
        handle.getModifier().writeDefaults();
    }

    public WrapperPlayServerRemoveEntityEffect(PacketContainer packet) {
        super(packet, TYPE);
    }

    /**
     * Retrieve entity ID of a player.
     * @return The current Entity ID
     */
    public int getEntityId() {
        return handle.getIntegers().read(0);
    }

    /**
     * Set entity ID of a player.
     * @param value - new value.
     */
    public void setEntityId(int value) {
        handle.getIntegers().write(0, value);
    }

    /**
     * Retrieve the entity.
     * @param world - the current world of the entity.
     * @return The entity.
     */
    public Entity getEntity(World world) {
        return handle.getEntityModifier(world).read(0);
    }

    /**
     * Retrieve the entity.
     * @param event - the packet event.
     * @return The entity.
     */
    public Entity getEntity(PacketEvent event) {
        return getEntity(event.getPlayer().getWorld());
    }

    /**
     * Retrieve the ID of the effect to remove.
     * @return The current Effect ID
     */
    public byte getEffectId() {
        return handle.getBytes().read(1);
    }

    /**
     * Set the ID of the effect to remove.
     * @param value - new value.
     */
    public void setEffectId(byte value) {
        handle.getBytes().write(1, value);
    }

    /**
     * Retrieve the effect.
     * @return The current effect
     */
    @SuppressWarnings("deprecation")
    public PotionEffectType getEffect() {
        return PotionEffectType.getById(getEffectId());
    }

    /**
     * Set the effect id.
     * @param value - new value.
     */
    @SuppressWarnings("deprecation")
    public void setEffect(PotionEffectType value) {
        setEffectId((byte) value.getId());
    }
}