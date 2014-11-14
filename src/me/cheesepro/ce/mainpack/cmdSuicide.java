package me.cheesepro.ce.mainpack;

import me.cheesepro.ce.extra.ConsoleSender;
import me.cheesepro.ce.extra.Messenger;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Mark on 09/11/2014.
 */
public class cmdSuicide implements CommandExecutor{

    private CEMain plugin;
    private final Messenger msg;

    public cmdSuicide(CEMain plugin){
        this.plugin = plugin;
        this.msg = new Messenger(plugin);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if(cmd.getLabel().equalsIgnoreCase("suicide")){
                p.setHealth(0.0);
                for(Player all : Bukkit.getOnlinePlayers()){
                    msg.w(all, "e", p.getName() + " took the easy way out...");
                }
            }
        }else{
            ConsoleSender.noConsole();
        }
        return false;
    }

}
