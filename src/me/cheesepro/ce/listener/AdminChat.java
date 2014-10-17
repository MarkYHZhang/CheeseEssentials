package me.cheesepro.ce.listener;

import me.cheesepro.ce.file.playerdata;
import me.cheesepro.ce.mainpack.CEMain;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

/**
 * Created by Mark on 11/10/2014.
 */
public class AdminChat implements Listener{

    CEMain plugin;

    public AdminChat(CEMain main)
    {
        main.getServer().getPluginManager().registerEvents(this, main);
        this.plugin = main;
    }

    @EventHandler
    public void ChatListener(AsyncPlayerChatEvent e){
        Player p = e.getPlayer();
        String pn = p.getName();
        if (p.hasPermission("super.adminchat")) {
            if (e.getMessage().startsWith("@")) {
                for (Player admins : Bukkit.getServer().getOnlinePlayers()) {
                    if (admins.hasPermission("super.adminchat")) {
                        String msg = e.getMessage().replaceFirst("@", " ");
                        admins.sendMessage(ChatColor.LIGHT_PURPLE.toString() + ChatColor.BOLD + "[ADMINCHAT] " + ChatColor.RED.toString() + ChatColor.BOLD + pn + ChatColor.YELLOW.toString() + ChatColor.BOLD + msg);
                        e.setCancelled(true);
                    }
                }
            }
        }
    }


}
