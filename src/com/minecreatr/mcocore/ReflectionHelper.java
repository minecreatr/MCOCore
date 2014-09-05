package com.minecreatr.mcocore;

import org.bukkit.command.CommandException;

import java.lang.reflect.Field;

/**
 * Created on 9/4/2014
 *
 * @author minecreatr
 */
public class ReflectionHelper {

    public static Object getValue(Object obj, String name) throws CommandException{
        try {
            final Field f = obj.getClass().getDeclaredField(name);
            f.setAccessible(true);
            return f.get(obj);
        } catch (Exception exception){
            MCOCore.instance().getLogger().info("Error getting value "+name+" in object "+obj.getClass().toString());
            throw new CommandException("Error getting value "+name+" in object "+obj.getClass().toString(), exception);
        }
    }

    public static void setValue(Object obj, String name, Object value) throws CommandException{
        try {
            final Field f = obj.getClass().getDeclaredField(name);
            f.setAccessible(true);
            f.set(obj, value);
        } catch (Exception exception){
            MCOCore.instance().getLogger().info("Error setting value "+name+" in object "+obj.getClass().toString()+" to "+value.toString());
            throw new CommandException("Error setting value "+name+" in object "+obj.getClass().toString()+" to "+value.toString(), exception);
        }
    }
}
