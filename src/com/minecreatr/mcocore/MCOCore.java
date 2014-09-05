package com.minecreatr.mcocore;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import net.minecraft.util.com.google.common.collect.Iterables;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerChatTabCompleteEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.PluginMessageListener;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

/**
 * Created on 9/2/2014
 *
 * @author minecreatr
 */
public class MCOCore extends JavaPlugin implements PluginMessageListener{

    private static MCOCore instance;
    public static final String channelName = "MCOCORECOMMUNES";
    private boolean isRequesting = false;

    public void onEnable(){
        if (instance==null){
            instance= (MCOCore)Bukkit.getPluginManager().getPlugin(this.getName());
        }
        this.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        this.getServer().getMessenger().registerIncomingPluginChannel(this, "BungeeCord", this);
    }
    public void onDisable(){

    }

    public void onPluginMessageReceived(String channel, Player player, byte[] message){
        if (!channel.equals("BungeeCord")) {
            return;
        }
        ByteArrayDataInput in = ByteStreams.newDataInput(message);
        String subchannel = in.readUTF();
        if (subchannel.equals(channelName)) {
            short len = in.readShort();
            byte[] msgbytes = new byte[len];
            in.readFully(msgbytes);
            DataInputStream msgin = new DataInputStream(new ByteArrayInputStream(msgbytes));
            MessageRecieveEvent event = new MessageRecieveEvent(msgin, player);
            getServer().getPluginManager().callEvent(event);
        }
    }

    public static MCOCore instance(){
        if (instance==null){
            instance = (MCOCore) Bukkit.getPluginManager().getPlugin("MCOCore");
        }
        return instance;
    }
    public ByteArrayOutputStream getData(){
        return new ByteArrayOutputStream();
    }

    public String getServerByPlayer(UUID id){
        return "";
    }

    public void sendMessage(String server, ByteArrayOutputStream data){
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("Forward");
        out.writeUTF(server);
        out.writeUTF(channelName);
        out.writeShort(data.toByteArray().length);
        out.write(data.toByteArray());
        Player player = Iterables.getFirst(Bukkit.getOnlinePlayers(), null);
        player.sendPluginMessage(this, "BungeeCord", out.toByteArray());

    }


}
