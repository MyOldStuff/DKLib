package me.michidk.DKLib.effects;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import com.comphenix.protocol.Packets;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.ConnectionSide;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.wrappers.nbt.NbtCompound;
import com.comphenix.protocol.wrappers.nbt.NbtFactory;

/**
 * from https://github.com/desht/dhutils
 *
 * Use ProtocolLib to add a glow to unenchanted items.  With thanks to Comphenix; see
 * https://gist.github.com/aadnk/4580551
 */
public class ItemGlow {
    private static boolean inited = false;

    private static final Enchantment GLOW_FLAG = Enchantment.SILK_TOUCH;
    private static final int GLOW_FLAG_LEVEL = 32;

    /**
     * Initialise the ItemGlow system.
     *
     * @param plugin the plugin instance
     */
    public static void init(Plugin plugin) {
        PacketAdapter adapter = new PacketAdapter(plugin, ConnectionSide.SERVER_SIDE, ListenerPriority.HIGH, Packets.Server.SET_SLOT, Packets.Server.WINDOW_ITEMS) {
            @Override
            public void onPacketSending(PacketEvent event) {
                if (event.getPacketID() == Packets.Server.SET_SLOT) {
                    addGlow(new ItemStack[] { event.getPacket().getItemModifier().read(0) });
                } else {
                    addGlow(event.getPacket().getItemArrayModifier().read(0));
                }
            }
        };
        ProtocolLibrary.getProtocolManager().addPacketListener(adapter);
        inited = true;
    }

    /**
     * Set the glowing status of an item stack.
     *
     * @param stack the item stack to modify
     * @param glowing true to make the item glow, false to stop it glowing
     */
    public static void setGlowing(ItemStack stack, boolean glowing) {
        if (!inited) {
            throw new IllegalStateException("ItemGlow system has not been initialised.  Call ItemGlow.init(plugin) first.");
        }
        if (glowing) {
            // if the item already has a real enchantment, let's not overwrite it!
            if (!stack.getItemMeta().hasEnchant(GLOW_FLAG)) {
                stack.addUnsafeEnchantment(GLOW_FLAG, GLOW_FLAG_LEVEL);
            }
        } else if (stack.getEnchantmentLevel(GLOW_FLAG) == GLOW_FLAG_LEVEL) {
            stack.removeEnchantment(GLOW_FLAG);
        }
    }

    private static void addGlow(ItemStack[] stacks) {
        for (ItemStack stack : stacks) {
            if (stack != null) {
                // Only update those stacks that have our flag enchantment
                if (stack.getEnchantmentLevel(GLOW_FLAG) == 32) {
                    NbtCompound compound = (NbtCompound) NbtFactory.fromItemTag(stack);
                    compound.put(NbtFactory.ofList("ench"));
                }
            }
        }
    }
}