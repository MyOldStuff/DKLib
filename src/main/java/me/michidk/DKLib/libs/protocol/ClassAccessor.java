package me.michidk.DKLib.libs.protocol;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

/** 
 * @author Marco
 * @version 1.1.0
 */

public class ClassAccessor {

	private final Class<?> clazz;
	private final Set<Field> data;
	private final HashMap<Class<?>, ArrayList<Field>> fieldcache;
	
	public ClassAccessor(Class<?> clazz){
		this.clazz = clazz;
		this.data = ReflectionUtil.getDeclaredFields(this.clazz);
		this.fieldcache = new HashMap<>();
	}
	
	public ArrayList<Field> withType(Class<?> type){
		ArrayList<Field> fields = this.fieldcache.get(type);
		if(fields == null){
			fields = new ArrayList<>();
			for(Field field: this.data){
				if(field.getType() == type){
					fields.add(field);
				}
			}
		}
		return fields;
	}
	
	public Field withTypeAndIndex(Class<?> type, int index){
		ArrayList<Field> fields = withType(type);
		if(fields.size() > index){
			return fields.get(index);
		} else {
			try {
				throw new NoSuchFieldException("index must be between 0 - fieldcount!");
			} catch (NoSuchFieldException e) {
				e.printStackTrace();
			}
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public <T> T getValue(Class<?> type, int index, Object handle){
		Field field = withTypeAndIndex(type, index);
		if(field == null){
			return null;
		}
		try {
			return (T) field.get(handle);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void setValue(Class<?> type, int index, Object handle, Object value){
		Field field = withTypeAndIndex(type, index);
		if(field == null){
			return;
		}
		try {
			field.set(handle, value);
		} catch (Exception e){
			e.printStackTrace();
		}
	}
}
