package com.minecreatr.mcocore.command;

import com.minecreatr.mcocore.MCOCore;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * Created on 9/2/2014
 *
 * @author minecreatr
 */
public abstract class AbstractCommand extends Command implements CommandExecutor {

    private boolean hasPermission;
    private String permission;
    private String noPermMessage = ChatColor.RED+"You do not have permission to run this command";
    private boolean playerComplete = false;

    protected AbstractCommand(String name) {
        super(name);
        hasPermission=false;
    }

    protected AbstractCommand(String name, String perm){
        super(name);
        this.permission=perm;
        this.hasPermission=true;
    }

    protected AbstractCommand(String name, String perm, String message){
        super(name);
        this.permission=perm;
        this.hasPermission=true;
        this.noPermMessage=message;
    }


    public boolean execute(CommandSender sender, String commandLabel,String[] args) {
        if (!hasPermission) {
            return this.onCommand(sender, this, commandLabel, args);
        }
        else if (sender.hasPermission(permission)){
            return this.onCommand(sender, this, commandLabel, args);
        }
        else {
            sender.sendMessage(noPermMessage);
            return true;
        }
    }

    protected void setPlayerComplete(boolean p){
        this.playerComplete=p;
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String alias, String[] args) throws IllegalArgumentException {
        if (playerComplete){
            Iterator<? extends Player> players = MCOCore.instance().getServer().getOnlinePlayers().iterator();
            List<String> names = new ArrayList<String>();
            while (players.hasNext()){
                names.add(players.next().getName());
            }
            return names;
        }
        else {
            return null;
        }
    }

    public List toList(Collection collection){
        if (collection instanceof List){
            return (List) collection;
        }
        else {
            return new ArrayList(collection);
        }
    }
}