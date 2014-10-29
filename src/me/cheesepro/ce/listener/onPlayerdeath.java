package me.cheesepro.ce.listener;

import me.cheesepro.ce.extra.Messenger;
import me.cheesepro.ce.mainpack.CEMain;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

/**
 * Created by Mark on 20/10/2014.
 */
public class onPlayerdeath implements Listener{

    CEMain plugin;
    private final Messenger msg;

    public onPlayerdeath(CEMain main) {
        main.getServer().getPluginManager().registerEvents(this, main);
        this.plugin = main;
        this.msg = new Messenger(plugin);
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent e){
//        String pn = e.getEntity().getPlayer().getName();

        String m = e.getDeathMessage();
        String pn = e.getEntity().getPlayer().getName();
        if(m.equalsIgnoreCase("null")){
            for(Player all: Bukkit.getOnlinePlayers()){
                msg.w(all, "e", pn + " died");
            }
        }
        for(Player all: Bukkit.getOnlinePlayers()){
            msg.w(all, "e", m);
        }
        e.setDeathMessage("");
    }

}
