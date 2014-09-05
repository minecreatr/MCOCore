package com.minecreatr.mcocore.command;

import com.minecreatr.mcocore.ClassInitializationException;
import com.minecreatr.mcocore.ReflectionHelper;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;
import org.bukkit.craftbukkit.v1_7_R4.CraftServer;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Field;

/**
 * Created on 8/31/2014
 * @author minecreatr
 */
public class CommandLoader {

    public JavaPlugin plugin;
    private CommandMap cmap;

    public CommandLoader(JavaPlugin p){
        this.plugin=p;
        cmap = (CommandMap) ReflectionHelper.getValue(Bukkit.getServer(), "commandMap");
    }

    public void registerCommand(AbstractCommand command){
        cmap.register("", command);
    }
}
