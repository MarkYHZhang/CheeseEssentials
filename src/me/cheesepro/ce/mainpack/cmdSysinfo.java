package me.cheesepro.ce.mainpack;

import me.cheesepro.ce.extra.Messenger;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 * Created by Mark on 04/11/2014.
 */
public class cmdSysinfo implements CommandExecutor{

    private CEMain plugin;
    private final Messenger msg;

    public cmdSysinfo(CEMain plugin){
        this.plugin = plugin;
        this.msg = new Messenger(plugin);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (label.equalsIgnoreCase("sysinfo")) {
            sender.sendMessage(ChatColor.GOLD + "System: " + ChatColor.WHITE + System.getProperty("os.name"));
            sender.sendMessage(ChatColor.GOLD + "System Version: " + ChatColor.WHITE +  System.getProperty("os.version"));
            sender.sendMessage(ChatColor.GOLD + "System arch: " + ChatColor.WHITE +  System.getProperty("os.arch"));
            sender.sendMessage(ChatColor.GOLD + "Allowed Memory: " + ChatColor.WHITE +  Runtime.getRuntime().totalMemory()/1024/1024 + "MB");
            sender.sendMessage(ChatColor.GOLD + "Free Memory: " + ChatColor.WHITE +  Runtime.getRuntime().freeMemory()/1024/1024 + "MB");
        }
        return false;
    }
}
