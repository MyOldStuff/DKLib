package me.michidk.DKLib.libs.protocol.injector;

import me.michidk.DKLib.libs.protocol.PacketController;
import net.minecraft.util.io.netty.channel.ChannelDuplexHandler;
import net.minecraft.util.io.netty.channel.ChannelHandlerContext;
import net.minecraft.util.io.netty.channel.ChannelPromise;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.logging.Level;

/** 
 * @author Marco
 * @version 1.1.0
 */

public class ChannelPacketHandler extends ChannelDuplexHandler{

	private final PacketController packetController;
	private Player player;
	
	public ChannelPacketHandler(PacketController packetController){
		this.packetController = packetController;
	}
	
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object packet) throws Exception{
		boolean cancelled = false;
		try {
			cancelled = packetController.manage(packet, ctx.channel(), player);
		} catch (Exception e) {
			Bukkit.getLogger().log(Level.WARNING, "Failed to manage incomming packet!");
		}
		if(!cancelled && packet != null){
			super.channelRead(ctx, packet);
		}
	}
	
	@Override
	public void write(ChannelHandlerContext ctx, Object packet, ChannelPromise promise) throws Exception{
		boolean cancelled = false;
		try {
			cancelled = packetController.manage(packet, ctx.channel(), player);
		} catch (Exception e) {
			Bukkit.getLogger().log(Level.WARNING, "Failed to manage outgoing packet!");
		}
		if(!cancelled && packet != null){
			super.write(ctx, packet, promise);
		}
	}
	
	public void setPlayer(Player player){
		this.player = player;
	}
}
