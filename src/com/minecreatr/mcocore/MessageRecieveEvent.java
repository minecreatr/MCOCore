package com.minecreatr.mcocore;

import com.google.common.io.ByteArrayDataInput;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import java.io.DataInputStream;

/**
 * Created on 9/2/2014
 *
 * @author minecreatr
 */
public class MessageRecieveEvent extends Event {

    private static final HandlerList handlers = new HandlerList();
    private DataInputStream data;
    private Player player;

    public MessageRecieveEvent(DataInputStream d, Player player){
        data=d;
        player = player;
    }

    public DataInputStream getData(){
        return this.data;
    }

    public Player getPlayer(){
        return this.player;
    }


    @Override
    public HandlerList getHandlers(){
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}