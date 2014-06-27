package me.michidk.DKLib.libs.protocol.injector;

import me.michidk.DKLib.libs.protocol.PacketController;
import me.michidk.DKLib.libs.protocol.ReflectionUtil;
import net.minecraft.util.io.netty.channel.Channel;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/** 
 * @author Marco
 * @version 1.1.0
 */

public class ChannelInjector implements Listener{

	private static final Method getHandle = ReflectionUtil.getMethod(ReflectionUtil.getCraftBukkitClass("entity.CraftPlayer"), "getHandle");
	private static final Field getPlayerConnection = ReflectionUtil.getField(ReflectionUtil.getMinecraftClass("EntityPlayer"), "playerConnection");
	private static final Field getNetworkManager = ReflectionUtil.getField(ReflectionUtil.getMinecraftClass("PlayerConnection"), "networkManager");
	private static final Field getChannel = ReflectionUtil.getFieldByType(ReflectionUtil.getMinecraftClass("NetworkManager"), Channel.class);
	
	private static final String handlerName = "dk_protocol_handler";
	
	private PacketController packetController;
	
	public ChannelInjector(PacketController packetController){
		this.packetController = packetController;
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e){
		injectPlayer(e.getPlayer()).setPlayer(e.getPlayer());;
	}
	
	public Channel getChannel(Player player){
		try {
			Object entityPlayer = getHandle.invoke(player);
			Object playerConnection = getPlayerConnection.get(entityPlayer);
			Object networkManager = getNetworkManager.get(playerConnection);
			return (Channel) getChannel.get(networkManager);
		} catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public ChannelPacketHandler injectPlayer(Player player){
		return injectChannel(getChannel(player));
	}
	
	public ChannelPacketHandler injectChannel(Channel channel){
		try {
			ChannelPacketHandler packetHandler = (ChannelPacketHandler) channel.pipeline().get(handlerName);
			
			if(packetHandler == null){
				packetHandler = new ChannelPacketHandler(packetController);
				channel.pipeline().addBefore("packet_handler", handlerName, packetHandler);
			}
			return packetHandler;
		} catch(IllegalArgumentException e){
			return (ChannelPacketHandler) channel.pipeline().get(handlerName);
		}
	}
}
