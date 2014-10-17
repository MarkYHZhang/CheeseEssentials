package me.cheesepro.ce.listener;

import me.cheesepro.ce.extra.Messenger;
import me.cheesepro.ce.extra.UUIDFetcher;
import me.cheesepro.ce.file.playerdata;
import me.cheesepro.ce.mainpack.CEMain;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.server.ServerListPingEvent;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Random;

/**
 * Created by Mark on 05/10/2014.
 */
public class onPlayerjoin implements Listener{

    CEMain plugin;
    String motd;
    playerdata settings = playerdata.getInstance();
    Messenger msg;

    public onPlayerjoin(CEMain main)
    {
        main.getServer().getPluginManager().registerEvents(this, main);
        this.plugin = main;
        msg = new Messenger(plugin);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){
        Random random = new Random();
        int r = random.nextInt(5 - 1 + 1) + 1;
        if(r==1) {
            Player p = e.getPlayer();
            motd = plugin.getConfig().getString("motd.game1");
            motd = motd.replaceAll("&", "§");
            setMotd(p, motd);
        }else if(r==2){
            Player p = e.getPlayer();
            motd = plugin.getConfig().getString("motd.game2");
            motd = motd.replaceAll("&", "§");
            setMotd(p, motd);
        }else if(r==3){
            Player p = e.getPlayer();
            motd = plugin.getConfig().getString("motd.game3");
            motd = motd.replaceAll("&", "§");
            setMotd(p, motd);
        }else if(r==4){
            Player p = e.getPlayer();
            motd = plugin.getConfig().getString("motd.game4");
            motd = motd.replaceAll("&", "§");
            setMotd(p, motd);
        }else if(r==5){
            Player p = e.getPlayer();
            motd = plugin.getConfig().getString("motd.game5");
            motd = motd.replaceAll("&", "§");
            setMotd(p, motd);
        }
    }

    @EventHandler
    public  void newPlayerJoin(PlayerJoinEvent e){
        Player p = e.getPlayer();
        String pn = p.getName();
        if(!(p.hasPlayedBefore())){
            settings.getData().set(pn + ".logoutloc", "loc");
            settings.saveData();
            settings.getData().set(pn + ".previousloc", "loc");
            settings.saveData();
            p.getServer().broadcastMessage(ChatColor.YELLOW.toString() + ChatColor.BOLD + "Welcome " + ChatColor.WHITE.toString() + ChatColor.BOLD + p.getName() + ChatColor.YELLOW.toString() + ChatColor.BOLD +" to " + ChatColor.AQUA.toString() + ChatColor.BOLD + "MineFutureV2");
        }
    }

    public void setMotd(Player p,String in){
        msg.motd(p,in);
    }



}
