package me.michidk.DKLib.libs.protocol;

import me.michidk.DKLib.libs.protocol.event.PacketEvent;
import me.michidk.DKLib.libs.protocol.event.PacketListener;
import me.michidk.DKLib.libs.protocol.event.ProtocolPacket;
import net.minecraft.util.io.netty.channel.Channel;
import org.bukkit.entity.Player;

import java.util.ArrayList;

/** 
 * @author Marco
 * @version 1.1.0
 */

public class PacketController {

	private ArrayList<PacketType> types;
	
	public PacketController(){
		this.types = new ArrayList<>();
	}
	
	public ArrayList<PacketType> getPacketTypes(){
		return types;
	}
	
	public boolean manage(Object packet, Channel channel, Player player){
		PacketType type = getPacketType(packet.getClass());
		if(type == null){
			return false;
		}
		ProtocolPacket protocolPacket = new ProtocolPacket(type, packet);
		PacketEvent event = new PacketEvent(player, protocolPacket, channel);
		for(PacketListener listener: type.getPacketListener()){
			try {
				listener.onPacket(event);
			} catch (Exception e){
				e.printStackTrace();
			}
		}
		return event.isCancelled();
	}
	
	private PacketType getPacketType(Class<?> clazz){
		for(PacketType type: types){
			if(type.getPacketClass() == clazz){
				return type;
			}
		}
		return null;
	}
}
