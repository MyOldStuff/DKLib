package me.michidk.DKLib.effects;

import me.michidk.DKLib.Main;
import me.michidk.DKLib.libs.protocol.PacketType;
import me.michidk.DKLib.libs.protocol.ReflectionUtil;
import me.michidk.DKLib.libs.protocol.event.PacketEvent;
import me.michidk.DKLib.libs.protocol.event.PacketListener;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ItemGlow implements PacketListener{

    private Class<?>  itemArray = Array.newInstance(ReflectionUtil.getMinecraftClass("ItemStack"), 0).getClass();
    private Class<?> nbtTagCompound = ReflectionUtil.getMinecraftClass("NBTTagCompound");
    private Class<?> nbtTagList = ReflectionUtil.getMinecraftClass("NBTTagList");
    private Field nbtField = ReflectionUtil.getField(ReflectionUtil.getMinecraftClass("ItemStack"), "tag");
    private Method setMethod = ReflectionUtil.getMethod(nbtTagCompound, "set");
    private Method asBukkitCopy = ReflectionUtil.getMethod(ReflectionUtil.getCraftBukkitClass("inventory.CraftItemStack"), "asBukkitCopy");

    private static final Enchantment GLOW_FLAG = Enchantment.SILK_TOUCH;
    private static final int GLOW_FLAG_LEVEL = 32;

    //prevents instantiation
    private ItemGlow(){

    }

    static {
        Main.getProtocolManager().registerListener(new ItemGlow(), PacketType.Server.WINDOW_ITEMS, PacketType.Server.SET_SLOT);
    }

    public static void setGlowing(ItemStack stack, boolean glowing) {
        if (glowing) {
            // if the item already has a real enchantment, let's not overwrite it!
            if (!stack.getItemMeta().hasEnchant(GLOW_FLAG)) {
                stack.addUnsafeEnchantment(GLOW_FLAG, GLOW_FLAG_LEVEL);
            }
        } else if (stack.getEnchantmentLevel(GLOW_FLAG) == GLOW_FLAG_LEVEL) {
            stack.removeEnchantment(GLOW_FLAG);
        }
    }

    @Override
    public void onPacket(PacketEvent event) {
        if(event.getPacket().getPacketType() == PacketType.Server.WINDOW_ITEMS){
            addGlow((Object[]) event.getPacket().getObject(itemArray, 0));
        } else if(event.getPacket().getPacketType() == PacketType.Server.SET_SLOT){
            addGlow(new Object[]{event.getPacket().getObject(ReflectionUtil.getMinecraftClass("ItemStack"), 0)});
        }
    }

    private void addGlow(Object[] items) {
        try{
            for(Object item: items){
                ItemStack stack = (ItemStack) asBukkitCopy.invoke(null, item);
                if(stack.getEnchantmentLevel(Enchantment.SILK_TOUCH) == 32){
                    try{
                        Object compound = nbtField.get(item);
                        if(compound == null){
                            compound = nbtTagCompound.newInstance();
                            nbtField.set(item, compound);
                        }
                        setMethod.invoke(compound, "ench", nbtTagList.newInstance());
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}