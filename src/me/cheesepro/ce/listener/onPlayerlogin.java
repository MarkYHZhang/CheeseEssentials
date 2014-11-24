package me.cheesepro.ce.listener;

import me.cheesepro.ce.extra.Messenger;
import me.cheesepro.ce.file.bandata;
import me.cheesepro.ce.file.hubloc;
import me.cheesepro.ce.file.playerdata;
import me.cheesepro.ce.mainpack.CEMain;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mark on 21/11/2014.
 */
public class onPlayerlogin  implements Listener {

    CEMain plugin;
    Messenger msg;
    bandata blist = bandata.getInstance();

    public onPlayerlogin(CEMain main)
    {
        main.getServer().getPluginManager().registerEvents(this, main);
        this.plugin = main;
        msg = new Messenger(plugin);
    }

    @EventHandler (priority = EventPriority.HIGHEST)
    public void banCheck(final PlayerLoginEvent e) {
        Player p = e.getPlayer();
        List namelist = new ArrayList();
        if (blist.getData().getString("list") != null) {
            for (String name : blist.getData().getConfigurationSection("list").getKeys(false)) {
                namelist.add(name);
            }
            if (namelist.contains(p.getName())) {
                String author = blist.getData().getString("list." + p.getName() + ".author");
                String banmsg = blist.getData().getString("list." + p.getName() + ".msg");
                String finalmsg = ChatColor.RED.toString() + ChatColor.BOLD + "--========<" + ChatColor.DARK_RED.toString() + ChatColor.BOLD + "BANNED" + ChatColor.RED.toString() + ChatColor.BOLD + ">========--" + "\n" + "\n" + ChatColor.DARK_PURPLE.toString() + ChatColor.BOLD + author + "\n" + "\n" + ChatColor.YELLOW.toString() + ChatColor.BOLD + banmsg + "\n" + "\n" + ChatColor.RED.toString() + ChatColor.BOLD + "*===========================*";
                e.disallow(PlayerLoginEvent.Result.KICK_OTHER, finalmsg);
            }
        }
    }
}
