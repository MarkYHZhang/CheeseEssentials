package me.cheesepro.ce.listener;

import me.cheesepro.ce.extra.Messenger;
import me.cheesepro.ce.mainpack.CEMain;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

/**
 * Created by Mark on 03/11/2014.
 */
public class coloredSign implements Listener{

    private CEMain plugin;
    private final Messenger msg;

    String wname;

    public coloredSign(CEMain plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        this.plugin = plugin;
        this.msg = new Messenger(plugin);
    }

    @EventHandler (priority = EventPriority.HIGHEST)
    public void signChange(SignChangeEvent e) {
        String[] lines = e.getLines();
        if(lines[0]!=null){
            e.setLine(0, lines[0].replaceAll("&", "§"));
        }
        if(lines[1]!=null){
            e.setLine(1, lines[1].replaceAll("&", "§"));
        }
        if (lines[2]!=null){
            e.setLine(2, lines[2].replaceAll("&", "§"));
        }
        if (lines[3]!=null){
            e.setLine(3, lines[3].replaceAll("&", "§"));
        }
    }

}
