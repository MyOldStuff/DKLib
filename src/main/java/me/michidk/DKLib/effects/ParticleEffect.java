package me.michidk.DKLib.effects;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.PacketContainer;
import org.apache.commons.lang.Validate;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.lang.reflect.InvocationTargetException;

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
    /**
     * @appearance Huge explosions
     * @displayed by TNT and creepers
     */
    HUGE_EXPLOSION("hugeexplosion"),
    /**
     * @appearance Smaller explosions
     * @displayed by TNT and creepers
     */
    LARGE_EXPLODE("largeexplode"),
    /**
     * @appearance Little white sparkling stars
     * @displayed by Fireworks
     */
    FIREWORKS_SPARK("fireworksSpark"),
    /**
     * @appearance Bubbles
     * @displayed in water
     */
    BUBBLE("bubble"),
    /**
     * @appearance Unknown
     */
    SUSPEND("suspend"),
    /**
     * @appearance Little gray dots
     * @displayed in the Void and water
     */
    DEPTH_SUSPEND("depthSuspend"),
    /**
     * @appearance Little gray dots
     * @displayed by Mycelium
     */
    TOWN_AURA("townaura"),
    /**
     * @appearance Light brown crosses
     * @displayed by critical hits
     */
    CRIT("crit"),
    /**
     * @appearance Cyan stars
     * @displayed by hits with an enchanted weapon
     */
    MAGIC_CRIT("magicCrit"),
    /**
     * @appearance Little black/gray clouds
     * @displayed by torches, primed TNT and end portals
     */
    SMOKE("smoke"),
    /**
     * @appearance Colored swirls
     * @displayed by potion effects
     */
    MOB_SPELL("mobSpell"),
    /**
     * @appearance Transparent colored swirls
     * @displayed by beacon effect
     */
    MOB_SPELL_AMBIENT("mobSpellAmbient"),
    /**
     * @appearance Colored swirls
     * @displayed by splash potions
     */
    SPELL("spell"),
    /**
     * @appearance Colored crosses
     * @displayed by instant splash potions (instant health/instant damage)
     */
    INSTANT_SPELL("instantSpell"),
    /**
     * @appearance Colored crosses
     * @displayed by witches
     */
    WITCH_MAGIC("witchMagic"),
    /**
     * @appearance Colored notes
     * @displayed by note blocks
     */
    NOTE("note"),
    /**
     * @appearance Little purple clouds
     * @displayed by nether portals, endermen, ender pearls, eyes of ender and ender chests
     */
    PORTAL("portal"),
    /**
     * @appearance: White letters
     * @displayed by enchantment tables that are near bookshelves
     */
    ENCHANTMENT_TABLE("enchantmenttable"),
    /**
     * @appearance White clouds
     */
    EXPLODE("explode"),
    /**
     * @appearance Little flames
     * @displayed by torches, furnaces, magma cubes and monster spawners
     */
    FLAME("flame"),
    /**
     * @appearance Little orange blobs
     * @displayed by lava
     */
    LAVA("lava"),
    /**
     * @appearance Gray transparent squares
     */
    FOOTSTEP("footstep"),
    /**
     * @appearance Blue drops
     * @displayed by water, rain and shaking wolves
     */
    SPLASH("splash"),
    /**
     * @appearance Blue droplets
     * @displayed on water when fishing
     */
    WAKE("wake"),
    /**
     * @appearance Black/Gray clouds
     * @displayed by fire, minecarts with furance and blazes
     */
    LARGE_SMOKE("largesmoke"),
    /**
     * @appearance Large white clouds
     * @displayed on mob death
     */
    CLOUD("cloud"),
    /**
     * @appearance Little colored clouds
     * @displayed by active redstone wires and redstone torches
     */
    RED_DUST("reddust"),
    /**
     * @appearance Little white parts
     * @displayed by cracking snowballs and eggs
     */
    SNOWBALL_POOF("snowballpoof"),
    /**
     * @appearance Blue drips
     * @displayed by blocks below a water source
     */
    DRIP_WATER("dripWater"),
    /**
     * @appearance Orange drips
     * @displayed by blocks below a lava source
     */
    DRIP_LAVA("dripLava"),
    /**
     * @appearance White clouds
     */
    SNOW_SHOVEL("snowshovel"),
    /**
     * @appearance Little green parts
     * @displayed by slimes
     */
    SLIME("slime"),
    /**
     * @appearance Red hearts
     * @displayed when breeding
     */
    HEART("heart"),
    /**
     * @appearance Dark gray cracked hearts
     * @displayed when attacking a villager in a village
     */
    ANGRY_VILLAGER("angryVillager"),
    /**
     * @appearance Green stars
     * @displayed by bone meal and when trading with a villager
     */
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
        PacketContainer packet = createNormalPacket(this, loc, offsetX, offsetY, offsetZ, speed, amount);
        for (Player p : loc.getWorld().getPlayers()) {
            sendPacket(p, packet);
        }
    }

    /**
     * Plays a particle effect at a location which is shown to all players within a certain range in the current world.
     */
    public void play(Location loc, double range, float offsetX, float offsetY, float offsetZ, float speed, int amount) {
        PacketContainer packet = createNormalPacket(this, loc, offsetX, offsetY, offsetZ, speed, amount);
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
        PacketContainer packet = createTileCrackPacket(mat, data, loc, offsetX, offsetY, offsetZ, amount);
        for (Player p : loc.getWorld().getPlayers()) {
            sendPacket(p, packet);
        }
    }

    /**
     * Plays a tilecrack effect at a location which is shown to all players within a certain range in the current world.
     */
    public static void playTileCrack(Location loc, double range, Material mat, byte data, float offsetX, float offsetY, float offsetZ, int amount) {
        PacketContainer packet = createTileCrackPacket(mat, data, loc, offsetX, offsetY, offsetZ, amount);
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
        PacketContainer packet = createIconCrackPacket(mat, loc, offsetX, offsetY, offsetZ, amount);
        for (Player p : loc.getWorld().getPlayers()) {
            sendPacket(p, packet);
        }
    }

    /**
     * Plays an iconcrack effect at a location which is shown to all players within a certain range in the current world.
     */
    public static void playIconCrack(Location loc, double range, Material mat, float offsetX, float offsetY, float offsetZ, int amount) {
        PacketContainer packet = createIconCrackPacket(mat, loc, offsetX, offsetY, offsetZ, amount);
        range *= range;
        for (Player p : loc.getWorld().getPlayers()) {
            if (p.getLocation().distanceSquared(loc) <= range) {
                sendPacket(p, packet);
            }
        }
    }

    private PacketContainer createNormalPacket(ParticleEffect effect, Location loc, float offsetX, float offsetY, float offsetZ, float speed, int amount) {
        return createPacket(effect.getName(), loc, offsetX, offsetY, offsetZ, speed, amount);
    }

    private static PacketContainer createTileCrackPacket(Material mat, byte data, Location loc, float offsetX, float offsetY, float offsetZ, int amount) {
        return createPacket("tilecrack_" + mat.getId() + "_" + data, loc, offsetX, offsetY, offsetZ, 0.1F, amount);
    }

    private static PacketContainer createIconCrackPacket(Material mat, Location loc, float offsetX, float offsetY, float offsetZ, int amount) {
        return createPacket("iconcrack_" + mat.getId(), loc, offsetX, offsetY, offsetZ, 0.1F, amount);
    }

    private static PacketContainer createPacket(String effectName, Location loc, float offsetX, float offsetY, float offsetZ, float speed, int amount) {
        PacketContainer particlePacket = new PacketContainer(63);
        Validate.isTrue(amount > 0, "Amount of particles must be greater than 0");
        particlePacket.getStrings().write(0, effectName);
        particlePacket.getFloat().write(0, (float)loc.getX()).write(1, (float)loc.getY()).write(2, (float)loc.getZ());
        particlePacket.getFloat().write(3, offsetX).write(4, offsetY).write(5, offsetZ);
        particlePacket.getFloat().write(6, speed);
        particlePacket.getIntegers().write(0, amount);
        return particlePacket;
    }

    private static void sendPacket(Player p, PacketContainer packet) {
        try {
            ProtocolLibrary.getProtocolManager().sendServerPacket(p, packet);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}

