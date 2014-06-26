package me.michidk.DKLib.libs.protocol.event;

import net.minecraft.util.io.netty.channel.Channel;
import org.bukkit.entity.Player;

/** 
 * @author Marco
 * @version 1.1.0
 */

public class PacketEvent {

	private final Player player;
	private final ProtocolPacket packet;
	private final Channel channel;
	private boolean cancel;
	
	public PacketEvent(Player player, ProtocolPacket packet, Channel channel){
		this.player = player;
		this.packet = packet;
		this.channel = channel;
	}
	
	public Player getPlayer(){
		return player;
	}
	
	public ProtocolPacket getPacket(){
		return packet;
	}
	
	public Channel getChannel(){
		return channel;
	}
	
	public void setCancelled(boolean cancel){
		this.cancel = cancel;
	}
	
	public boolean isCancelled(){
		return cancel;
	}
}
