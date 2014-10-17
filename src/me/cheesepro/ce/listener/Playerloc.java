package me.cheesepro.ce.listener;

import me.cheesepro.ce.file.playerdata;
import me.cheesepro.ce.mainpack.CEMain;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerTeleportEvent;

/**
 * Created by Mark on 11/10/2014.
 */
public class Playerloc implements Listener{

    CEMain plugin;
    playerdata settings = playerdata.getInstance();

    public Playerloc(CEMain main)
    {
        main.getServer().getPluginManager().registerEvents(this, main);
        this.plugin = main;
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e){
        Player p = e.getPlayer();
        String pn = p.getName();
        int x = e.getPlayer().getLocation().getBlockX();
        int y = e.getPlayer().getLocation().getBlockY();
        int z = e.getPlayer().getLocation().getBlockZ();
        float pitch = e.getPlayer().getLocation().getPitch();
        float yaw = e.getPlayer().getLocation().getYaw();
        String w = e.getPlayer().getLocation().getWorld().getName().toString();
        settings.getData().set(pn + ".logoutloc.pitch", Float.valueOf(pitch));
        settings.getData().set(pn + ".logoutloc.yaw", Float.valueOf(yaw));
        settings.getData().set(pn + ".logoutloc.X", Integer.valueOf(x));
        settings.getData().set(pn + ".logoutloc.Y", Integer.valueOf(y));
        settings.getData().set(pn + ".logoutloc.Z", Integer.valueOf(z));
        settings.getData().set(pn + ".logoutloc.W", w);
        settings.saveData();
    }

    @EventHandler
    public void onPlayerTP(PlayerTeleportEvent e){
        Player p = e.getPlayer();
        String pn = p.getName();
        int x = e.getPlayer().getLocation().getBlockX();
        int y = e.getPlayer().getLocation().getBlockY();
        int z = e.getPlayer().getLocation().getBlockZ();
        float pitch = e.getPlayer().getLocation().getPitch();
        float yaw = e.getPlayer().getLocation().getYaw();
        String w = e.getPlayer().getLocation().getWorld().getName().toString();
        settings.getData().set(pn + ".previousloc.pitch", Float.valueOf(pitch));
        settings.getData().set(pn + ".previousloc.yaw", Float.valueOf(yaw));
        settings.getData().set(pn + ".previousloc.X", Integer.valueOf(x));
        settings.getData().set(pn + ".previousloc.Y", Integer.valueOf(y));
        settings.getData().set(pn + ".previousloc.Z", Integer.valueOf(z));
        settings.getData().set(pn + ".previousloc.W", w);
        settings.saveData();
    }   
}
