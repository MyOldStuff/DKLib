package me.michidk.DKLib.libs.protocol.event;

import me.michidk.DKLib.libs.protocol.ClassAccessor;
import me.michidk.DKLib.libs.protocol.PacketType;

/**
 * @author Marco
 * @version 1.1.0
 */

public class ProtocolPacket {

	private final ClassAccessor accessor;
	private final PacketType type;
	private Object handle;
	
	public ProtocolPacket(PacketType type) throws Exception{
		this.type = type;
		this.handle = type.getPacketClass().newInstance();
		this.accessor = type.getClassAccessor();
	}
	
	public ProtocolPacket(PacketType type, Object handle){
		this.type = type;
		this.handle = handle;
		this.accessor = type.getClassAccessor();
	}
	
	public PacketType getPacketType(){
		return type;
	}
	
	public Object getHandle(){
		return handle;
	}
	
	public byte getByte(int index){
		return accessor.getValue(Byte.TYPE, index, this.handle);
	}
	
	public void setByte(int index, byte value){
		accessor.setValue(Byte.TYPE, index, this.handle, value);
	}
	
	public short getShort(int index){
		return accessor.getValue(Short.TYPE, index, this.handle);
	}
	
	public void setShort(int index, short value){
		accessor.setValue(Short.TYPE, index, this.handle, value);
	}
	
	public int getInt(int index){
		return accessor.getValue(Integer.TYPE, index, this.handle);
	}
	
	public void setInt(int index, int value){
		accessor.setValue(Integer.TYPE, index, this.handle, value);
	}
	
	public long getLong(int index){
		return accessor.getValue(Long.TYPE, index, this.handle);
	}
	
	public void setLong(int index, long value){
		accessor.setValue(Long.TYPE, index, this.handle, value);
	}
	
	public float getFloat(int index){
		return accessor.getValue(Float.TYPE, index, this.handle);
	}
	
	public void setFloat(int index, float value){
		accessor.setValue(Float.TYPE, index, this.handle, value);
	}
	
	public double getDouble(int index){
		return accessor.getValue(Double.TYPE, index, this.handle);
	}
	
	public void setDouble(int index, double value){
		accessor.setValue(Double.TYPE, index, this.handle, value);
	}
	
	public String getString(int index){
		return accessor.getValue(String.class, index, this.handle);
	}
	
	public void setString(int index, String value){
		accessor.setValue(String.class, index, this.handle, value);
	}
	
	public <T>T getObject(Class<T> clazz, int index){
		return accessor.getValue(clazz, index, this.handle);
	}
	
	public void setObject(Class<?> clazz, int index, Object value){
		accessor.setValue(clazz, index, this.handle, value);
	}
}
