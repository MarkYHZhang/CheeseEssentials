package me.cheesepro.ce.listener;

import me.cheesepro.ce.mainpack.CEMain;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

import java.util.Random;

/**
 * Created by Mark on 06/10/2014.
 */
public class onServerPing implements Listener{

    CEMain plugin;

    public onServerPing(CEMain main)
    {
        main.getServer().getPluginManager().registerEvents(this, main);
        this.plugin = main;
    }

    @EventHandler
    public void onPing(ServerListPingEvent e){
        Random random = new Random();
        int r = random.nextInt(5 - 1 + 1) + 1;
        if(r==1){
            String motd = plugin.getConfig().getString("motd.sys1");
            motd = motd.replaceAll("&", "\u00A7");
            setMotd(e, motd);
        }else if(r==2){
            String motd = plugin.getConfig().getString("motd.sys2");
            motd = motd.replaceAll("&", "\u00A7");
            setMotd(e, motd);
        }else if(r==3){
            String motd = plugin.getConfig().getString("motd.sys3");
            motd = motd.replaceAll("&", "\u00A7");
            setMotd(e, motd);
        }else if(r==4){
            String motd = plugin.getConfig().getString("motd.sys4");
            motd = motd.replaceAll("&", "\u00A7");
            setMotd(e, motd);
        }else if(r==5){
            String motd = plugin.getConfig().getString("motd.sys5");
            motd = motd.replaceAll("&", "\u00A7");
            setMotd(e, motd);
        }
    }

    public void setMotd(ServerListPingEvent e, String msg){
        String prefix = plugin.getConfig().getString("prefix.sysmotd");
        prefix = prefix.replaceAll("&", "§");
        e.setMotd(prefix + " " + msg + ChatColor.GRAY + " [MC 1.7.10/1.8]");
    }
}
