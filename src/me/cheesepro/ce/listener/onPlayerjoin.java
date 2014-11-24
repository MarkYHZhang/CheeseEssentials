package me.cheesepro.ce.listener;

import me.cheesepro.ce.extra.Messenger;
import me.cheesepro.ce.file.hubloc;
import me.cheesepro.ce.file.playerdata;
import me.cheesepro.ce.mainpack.CEMain;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.EventHandler;

import java.util.Random;

/**
 * Created by Mark on 05/10/2014.
 */
public class onPlayerjoin implements Listener{

    CEMain plugin;
    String motd;
    playerdata settings = playerdata.getInstance();
    hubloc hublocation = hubloc.getInstance();
    Messenger msg;

    public onPlayerjoin(CEMain main)
    {
        main.getServer().getPluginManager().registerEvents(this, main);
        this.plugin = main;
        msg = new Messenger(plugin);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){
        String pn = e.getPlayer().getName();
        String joinmsg = plugin.getConfig().getString("messages.join").replaceAll("&", "§");
        joinmsg = joinmsg.replaceAll("%name%", pn);
        e.setJoinMessage(joinmsg);
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
            World w = Bukkit.getServer().getWorld(hublocation.getData().getString("hub."+".world"));
            float pitch = Float.parseFloat(hublocation.getData().getString("hub."+".pitch"));
            float yaw = Float.parseFloat(hublocation.getData().getString("hub."+".yaw"));
            double x = hublocation.getData().getDouble("hub."+".x");
            double y = hublocation.getData().getDouble("hub."+".y");
            double z = hublocation.getData().getDouble("hub."+".z");
            Location loc = new Location(w, x, y, z);
            loc.setPitch(pitch);
            loc.setYaw(yaw);
            p.teleport(loc);
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
