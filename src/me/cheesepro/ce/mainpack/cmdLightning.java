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
public class cmdLightning implements CommandExecutor{

    private CEMain plugin;
    private Messenger msg;

    public cmdLightning(CEMain plugin){
        this.plugin = plugin;
        this.msg = new Messenger(plugin);
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(sender instanceof Player){
            if(label.equalsIgnoreCase("lightning")){
                if(args.length!=1){
                    msg.t(((Player) sender).getPlayer(), "e", "/lightning [playername]");
                }else if(args.length==1) {
                    Player targetPlayer = sender.getServer().getPlayer(args[0]);
                    if (targetPlayer != null) {
                        targetPlayer.getWorld().strikeLightning(targetPlayer.getLocation());
                        msg.w(targetPlayer, "a", "Lightning Striked by " + sender.getName());
                        msg.m(((Player) sender).getPlayer(), "5", "Player " + targetPlayer.getName() + " successfully Smited!");

                    }else{
                        msg.w(((Player) sender).getPlayer(), "5", "Target not found");
                    }
                }
            }
        }else{
            if(label.equalsIgnoreCase("lightning")){
                if(args.length!=1){
                    Bukkit.getLogger().info(ChatColor.YELLOW + "/lightning [playername]");
                }else if(args.length==1){
                    Player targetPlayer = Bukkit.getServer().getPlayer(args[0]);
                    if(targetPlayer!=null) {
                        targetPlayer.getWorld().strikeLightning(targetPlayer.getLocation());
                        msg.w(targetPlayer, "a", "Lightning Striked by Console");
                        Bukkit.getLogger().info("Player " + targetPlayer.getName() + " successfully Smited!");
                    }else{
                        ConsoleSender.msgConsole("Target not found");
                    }
                }
            }
        }
        return false;
    }

}
