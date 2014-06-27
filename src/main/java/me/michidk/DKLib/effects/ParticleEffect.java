package me.michidk.DKLib.effects;

import me.michidk.DKLib.DKLib;
import me.michidk.DKLib.libs.protocol.PacketType;
import me.michidk.DKLib.libs.protocol.event.ProtocolPacket;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

/**
 * ParticleEffect
 *
 * from https://github.com/desht/dhutils
 *
 * This particle effect library is based on code created by DarkBlade12 based off content from microgeek
 * You are free to use it, modify it and redistribute it under the condition to give credit to me and microgeek
 * Modified by desht:
 * - got rid of various name/id methods from the enum (the id had no meaning to the protocol)
 * - use ProtocolLib instead of reflection - safer and more concise
 * - use Bukkit Material instead of int for icon/tile crack effects
 */
public enum ParticleEffect {

    HUGE_EXPLOSION("hugeexplosion"),
    LARGE_EXPLODE("largeexplode"),
    FIREWORKS_SPARK("fireworksSpark"),
    BUBBLE("bubble"),
    SUSPEND("suspend"),
    DEPTH_SUSPEND("depthSuspend"),
    TOWN_AURA("townaura"),
    CRIT("crit"),
    MAGIC_CRIT("magicCrit"),
    MOB_SPELL("mobSpell"),
    MOB_SPELL_AMBIENT("mobSpellAmbient"),
    SPELL("spell"),
    INSTANT_SPELL("instantSpell"),
    WITCH_MAGIC("witchMagic"),
    NOTE("note"),
    PORTAL("portal"),
    ENCHANTMENT_TABLE("enchantmenttable"),
    EXPLODE("explode"),
    FLAME("flame"),
    LAVA("lava"),
    FOOTSTEP("footstep"),
    SPLASH("splash"),
    LARGE_SMOKE("largesmoke"),
    CLOUD("cloud"),
    RED_DUST("reddust"),
    SNOWBALL_POOF("snowballpoof"),
    DRIP_WATER("dripWater"),
    DRIP_LAVA("dripLava"),
    SNOW_SHOVEL("snowshovel"),
    SLIME("slime"),
    HEART("heart"),
    ANGRY_VILLAGER("angryVillager"),
    HAPPY_VILLAGER("happyVillager");

    private String name;

    ParticleEffect(String name) {
        this.name = name;
    }

    String getName() {
        return name;
    }

    /**
     * Plays a particle effect at a location which is only shown to a specific player.
     */
    public void play(Player p, Location loc, float offsetX, float offsetY, float offsetZ, float speed, int amount) {
        sendPacket(p, createNormalPacket(this, loc, offsetX, offsetY, offsetZ, speed, amount));
    }

    /**
     * Plays a particle effect at a location which is shown to all players in the current world.
     */
    public void play(Location loc, float offsetX, float offsetY, float offsetZ, float speed, int amount) {
        ProtocolPacket packet = createNormalPacket(this, loc, offsetX, offsetY, offsetZ, speed, amount);
        for (Player p : loc.getWorld().getPlayers()) {
            sendPacket(p, packet);
        }
    }

    /**
     * Plays a particle effect at a location which is shown to all players within a certain range in the current world.
     */
    public void play(Location loc, double range, float offsetX, float offsetY, float offsetZ, float speed, int amount) {
        ProtocolPacket packet = createNormalPacket(this, loc, offsetX, offsetY, offsetZ, speed, amount);
        range *= range;
        for (Player p : loc.getWorld().getPlayers()) {
            if (p.getLocation().distanceSquared(loc) <= range) {
                sendPacket(p, packet);
            }
        }
    }

    /**
     * Plays a tilecrack effect at a location which is only shown to a specific player.
     */
    public static void playTileCrack(Player p, Location loc, Material mat, byte data, float offsetX, float offsetY, float offsetZ, int amount) {
        sendPacket(p, createTileCrackPacket(mat, data, loc, offsetX, offsetY, offsetZ, amount));
    }

    /**
     * Plays a tilecrack effect at a location which is shown to all players in the current world.
     */
    public static void playTileCrack(Location loc, Material mat, byte data, float offsetX, float offsetY, float offsetZ, int amount) {
        ProtocolPacket packet = createTileCrackPacket(mat, data, loc, offsetX, offsetY, offsetZ, amount);
        for (Player p : loc.getWorld().getPlayers()) {
            sendPacket(p, packet);
        }
    }

    /**
     * Plays a tilecrack effect at a location which is shown to all players within a certain range in the current world.
     */
    public static void playTileCrack(Location loc, double range, Material mat, byte data, float offsetX, float offsetY, float offsetZ, int amount) {
        ProtocolPacket packet = createTileCrackPacket(mat, data, loc, offsetX, offsetY, offsetZ, amount);
        range *= range;
        for (Player p : loc.getWorld().getPlayers()) {
            if (p.getLocation().distanceSquared(loc) <= range) {
                sendPacket(p, packet);
            }
        }
    }

    /**
     * Plays an iconcrack effect at a location which is only shown to a specific player.
     */
    public static void playIconCrack(Player p, Location loc, Material mat, float offsetX, float offsetY, float offsetZ, int amount) {
        sendPacket(p, createIconCrackPacket(mat, loc, offsetX, offsetY, offsetZ, amount));
    }

    /**
     * Plays an iconcrack effect at a location which is shown to all players in the current world.
     */
    public static void playIconCrack(Location loc, Material mat, float offsetX, float offsetY, float offsetZ, int amount) {
        ProtocolPacket packet = createIconCrackPacket(mat, loc, offsetX, offsetY, offsetZ, amount);
        for (Player p : loc.getWorld().getPlayers()) {
            sendPacket(p, packet);
        }
    }

    /**
     * Plays an iconcrack effect at a location which is shown to all players within a certain range in the current world.
     */
    public static void playIconCrack(Location loc, double range, Material mat, float offsetX, float offsetY, float offsetZ, int amount) {
        ProtocolPacket packet = createIconCrackPacket(mat, loc, offsetX, offsetY, offsetZ, amount);
        range *= range;
        for (Player p : loc.getWorld().getPlayers()) {
            if (p.getLocation().distanceSquared(loc) <= range) {
                sendPacket(p, packet);
            }
        }
    }

    private ProtocolPacket createNormalPacket(ParticleEffect effect, Location loc, float offsetX, float offsetY, float offsetZ, float speed, int amount) {
        return createPacket(effect.getName(), loc, offsetX, offsetY, offsetZ, speed, amount);
    }

    private static ProtocolPacket createTileCrackPacket(Material mat, byte data, Location loc, float offsetX, float offsetY, float offsetZ, int amount) {
        return createPacket("tilecrack_" + mat.getId() + "_" + data, loc, offsetX, offsetY, offsetZ, 0.1F, amount);
    }

    private static ProtocolPacket createIconCrackPacket(Material mat, Location loc, float offsetX, float offsetY, float offsetZ, int amount) {
        return createPacket("iconcrack_" + mat.getId(), loc, offsetX, offsetY, offsetZ, 0.1F, amount);
    }

    private static ProtocolPacket createPacket(String effectName, Location loc, float offsetX, float offsetY, float offsetZ, float speed, int amount) {
        try {
            ProtocolPacket packet = new ProtocolPacket(PacketType.Server.WORLD_PARTICLES);
            packet.setString(0, effectName);
            packet.setFloat(0, (float) loc.getX()); packet.setFloat(1, (float) loc.getY()); packet.setFloat(2, (float) loc.getZ());
            packet.setFloat(3, offsetX); packet.setFloat(4, offsetY); packet.setFloat(5, offsetZ);
            packet.setFloat(6, speed);
            packet.setInt(0, amount);
            return packet;
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    private static void sendPacket(Player p, ProtocolPacket packet) {
        DKLib.getProtocolManager().sendPacket(packet.getHandle(), p);
    }
}