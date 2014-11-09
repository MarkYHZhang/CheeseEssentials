package me.cheesepro.ce.mainpack;

import me.cheesepro.ce.extra.ConsoleSender;
import me.cheesepro.ce.extra.Messenger;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Mark on 08/11/2014.
 */
public class cmdSpam implements CommandExecutor{

    private CEMain plugin;
    private final Messenger msg;

    public cmdSpam(CEMain plugin){
        this.plugin = plugin;
        this.msg = new Messenger(plugin);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if(cmd.getLabel().equalsIgnoreCase("spam")){
                if(args.length>0) {
                    String input = "";
                    for (int i = 0; i < args.length; i++) {
                        input = input + args[i] + " ";
                    }
                    input = input.replaceAll("&", "§");
                    for (int i = 1; i < 100; i++) {
                        Bukkit.broadcastMessage(ChatColor.RED.toString() + ChatColor.BOLD + "[Spam] " + ChatColor.YELLOW + p.getName() + ChatColor.WHITE + ":" + ChatColor.WHITE + " " + input);
                    }
                }else{
                    msg.m(p, "e", "/spam [message]");
                }
            }
        }else {
            ConsoleSender.noConsole();
        }
        return false;
    }

}
