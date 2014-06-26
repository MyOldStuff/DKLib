package me.michidk.DKLib.libs.protocol;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author michidk
 *
 * based on SoThatsIt and Johnkings code
 */
public class ReflectionUtil
{

    public static final String OBC_DIR = Bukkit.getServer().getClass().getPackage().getName();
    public static final String NMS_DIR = OBC_DIR.replaceFirst("org.bukkit.craftbukkit", "net.minecraft.server");
    public static final String VERSION = OBC_DIR.replaceFirst("org.bukkit.craftbukkit", "").replaceAll(".", "");

    public static void sendPacket(Player p, Object packet) {
        try {
            Object nmsPlayer = getHandle(p);
            Field con_field = nmsPlayer.getClass().getField("playerConnection");
            Object con = con_field.get(nmsPlayer);
            Method packet_method = getMethod(con.getClass(), "sendPacket");
            packet_method.invoke(con, packet);
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    public static void sendPacket(Object packet){
        for(Player p: Bukkit.getOnlinePlayers())
            sendPacket(p, packet);
    }

    public static Class<?> getMinecraftClass(String name){
        try {
            return Class.forName(NMS_DIR + "." + name);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Class<?> getCraftBukkitClass(String name){
        try {
            return Class.forName(OBC_DIR + "." + name);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Object getHandle(World world) {
        Object nms_entity = null;
        Method entity_getHandle = getMethod(world.getClass(), "getHandle");
        try {
            nms_entity = entity_getHandle.invoke(world);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return nms_entity;
    }

    public static Object getHandle(Entity entity) {
        Object nms_entity = null;
        Method entity_getHandle = getMethod(entity.getClass(), "getHandle");
        try {
            nms_entity = entity_getHandle.invoke(entity);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return nms_entity;
    }

    public static Field getField(Class<?> clazz, String fieldName){
        try {
            Field field = clazz.getDeclaredField(fieldName);
            field.setAccessible(true);
            return field;
        } catch (NoSuchFieldException | SecurityException e){
            e.printStackTrace();
            return null;
        }
    }

    public static Field getFieldByType(Class<?> clazz, Class<?> type){
        Field[] fields = clazz.getDeclaredFields();
        for(Field field : fields){
            if(field.getType().equals(type)){
                field.setAccessible(true);
                return field;
            }
        }
        return null;
    }

    public static Set<Field> getDeclaredFields(Class<?> clazz){
        Set<Field> fields = new LinkedHashSet<>();
        Class<?> current = clazz;
        while(current != null){
            fields.addAll(Arrays.asList(current.getDeclaredFields()));
            current = current.getSuperclass();
        }
        for(Field field: fields){
            field.setAccessible(true);
        }
        return fields;
    }

    //by SoThatsIt
    public static Method getMethod(Class<?> cl, String method, Class<?>[] args) {
        for (Method m : cl.getMethods()) {
            if (m.getName().equals(method) && ClassListEqual(args, m.getParameterTypes())) {
                return m;
            }
        }
        return null;
    }

    public static Method getMethod(Class<?> cl, String method, Integer args) {
        for (Method m : cl.getMethods()) {
            if (m.getName().equals(method) && args.equals(new Integer(m.getParameterTypes().length))) {
                return m;
            }
        }
        return null;
    }

    //by Johnking
    public static Method getMethod(Class<?> clazz, String methodName){
        try {
            Method method = clazz.getDeclaredMethod(methodName);
            method.setAccessible(true);
            return method;
        } catch (NoSuchMethodException | SecurityException e) {
            for(Method method: clazz.getMethods()){
                if(method.getName().equals(methodName)){
                    method.setAccessible(true);
                    return method;
                }
            }
            return null;
        }
    }


    public static boolean ClassListEqual(Class<?>[] l1, Class<?>[] l2) {
        boolean equal = true;

        if (l1.length != l2.length)
            return false;
        for (int i = 0; i < l1.length; i++) {
            if (l1[i] != l2[i]) {
                equal = false;
                break;
            }
        }

        return equal;
    }

}