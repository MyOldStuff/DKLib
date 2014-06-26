package me.michidk.DKLib.libs.protocol;

import me.michidk.DKLib.libs.protocol.event.PacketListener;
import me.michidk.DKLib.libs.protocol.event.ProtocolPacket;
import me.michidk.DKLib.libs.protocol.injector.ChannelInjector;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;

/** 
 * @author Marco
 * @version 1.1.0
 */

public class ProtocolManager {
	
	private static final Method getHandle = ReflectionUtil.getMethod(ReflectionUtil.getCraftBukkitClass("entity.CraftPlayer"), "getHandle");
	private static final Field getPlayerConnection = ReflectionUtil.getField(ReflectionUtil.getMinecraftClass("EntityPlayer"), "playerConnection");
	private static final Method sendPacket = ReflectionUtil.getMethod(ReflectionUtil.getMinecraftClass("PlayerConnection"), "sendPacket");
	
	private PacketController packetController;
	
	public ProtocolManager(Plugin plugin){
		packetController = new PacketController();
		Bukkit.getPluginManager().registerEvents(new ChannelInjector(packetController), plugin);
	}
	
	public void registerListener(PacketListener listener, PacketType ... types){
		for(PacketType type: types){
			ArrayList<PacketListener> packetListeners = type.getPacketListener();
			if(!packetListeners.contains(listener)){
				packetListeners.add(listener);
			}
			if(!packetController.getPacketTypes().contains(type)){
				packetController.getPacketTypes().add(type);
			}
		}
	}
	
	public void sendPacket(Object packet, Player ... players){
		sendPacket(new Object[]{packet}, players);
	}
	
	public void sendPacket(Object[] packets, Player ... players){
		if(packets == null || packets.length == 0){
			return;
		}
		if(players == null || players.length == 0){
			players = Bukkit.getOnlinePlayers();
		}
		for(Player player: players){
			sendPacket(packets, player);
		}
	}
	
	private void sendPacket(Object[] packets, Player player){
		if(player == null){
			return;
		}
		try {
			Object entityPlayer = getHandle.invoke(player);
			Object playerConnection = getPlayerConnection.get(entityPlayer);
			for(Object packet: packets){
				if(packet instanceof ProtocolPacket){
					packet = ((ProtocolPacket)packet).getHandle();
				}
				sendPacket.invoke(playerConnection, packet);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
