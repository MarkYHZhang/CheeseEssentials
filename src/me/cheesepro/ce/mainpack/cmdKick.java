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
 * Created by Mark on 21/11/2014.
 */
public class cmdKick implements CommandExecutor{

    private CEMain plugin;
    private final Messenger msg;

    public cmdKick(CEMain plugin){
        this.plugin = plugin;
        this.msg = new Messenger(plugin);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (cmd.getLabel().equalsIgnoreCase("kick")) {
                if(args.length==0){
                    msg.t(p, "e", "/kick [name] {message}");
                }

                if(args.length==1){
                    Player target = p.getServer().getPlayer(args[0]);
                    if(target!=null){
                        target.kickPlayer(ChatColor.RED.toString() + ChatColor.BOLD + "--========<" + ChatColor.DARK_RED.toString() + ChatColor.BOLD + "KICKED" + ChatColor.RED.toString() + ChatColor.BOLD + ">========--" + "\n" + "\n"  + ChatColor.DARK_PURPLE.toString() + ChatColor.BOLD + "BY: " + p.getName() + "\n" + "\n" + ChatColor.RED.toString() + ChatColor.BOLD + "*===========================*");
                        Bukkit.getServer().broadcastMessage(ChatColor.YELLOW.toString() + ChatColor.BOLD + "PLAYER " + target.getName() + " HAS BEEN KICKED");
                    }else{
                        msg.w(p, "4", "Target not found!");
                    }
                }
                String kickmsg="";
                for(int i=1;i<args.length;i++){
                    kickmsg = kickmsg + args[i] + " ";
                }
                if(args.length>1){
                    Player target = p.getServer().getPlayer(args[0]);
                    if(target!=null){
                        target.kickPlayer(ChatColor.RED.toString() + ChatColor.BOLD + "--========<" + ChatColor.DARK_RED.toString() + ChatColor.BOLD + "KICKED" + ChatColor.RED.toString() + ChatColor.BOLD + ">========--" + "\n" + "\n"  + ChatColor.DARK_PURPLE.toString() + ChatColor.BOLD + "BY: " + p.getName() + "\n" + "\n"  + ChatColor.YELLOW.toString() + ChatColor.BOLD + kickmsg + "\n" + "\n" + ChatColor.RED.toString() + ChatColor.BOLD + "*===========================*");
                        Bukkit.getServer().broadcastMessage(ChatColor.YELLOW.toString() + ChatColor.BOLD + "PLAYER " + target.getName() + " HAS BEEN KICKED");
                    }else{
                        msg.w(p, "4", "Target not found!");
                    }
                }

            }
        }else{
            if (cmd.getLabel().equalsIgnoreCase("kick")) {
                if(args.length==0){
                    ConsoleSender.msgConsole("/kick [name] {message}");
                }

                if(args.length==1){
                    Player target = Bukkit.getServer().getPlayer(args[0]);
                    if(target!=null){
                        target.kickPlayer(ChatColor.RED.toString() + ChatColor.BOLD + "--========<" + ChatColor.DARK_RED.toString() + ChatColor.BOLD + "KICKED" + ChatColor.RED.toString() + ChatColor.BOLD + ">========--" + "\n" + "\n"  + ChatColor.DARK_PURPLE.toString() + ChatColor.BOLD + "BY: CONSOLE" + "\n" + "\n" + ChatColor.RED.toString() + ChatColor.BOLD + "*===========================*");
                        Bukkit.getServer().broadcastMessage(ChatColor.YELLOW.toString() + ChatColor.BOLD + "PLAYER " + target.getName() + " HAS BEEN KICKED");
                    }else{
                        ConsoleSender.msgConsole("Target not found!");
                    }
                }
                String kickmsg="";
                for(int i=1;i<args.length;i++){
                    kickmsg = kickmsg + args[i] + " ";
                }
                if(args.length>1){
                    Player target = Bukkit.getServer().getPlayer(args[0]);
                    if(target!=null){
                        target.kickPlayer(ChatColor.RED.toString() + ChatColor.BOLD + "--========<" + ChatColor.DARK_RED.toString() + ChatColor.BOLD + "KICKED" + ChatColor.RED.toString() + ChatColor.BOLD + ">========--" + "\n" + "\n"  + ChatColor.DARK_PURPLE.toString() + ChatColor.BOLD + "BY: CONSOLE" + "\n" + "\n"  + ChatColor.YELLOW.toString() + ChatColor.BOLD + kickmsg + "\n" + "\n" + ChatColor.RED.toString() + ChatColor.BOLD + "*===========================*");
                        Bukkit.getServer().broadcastMessage(ChatColor.YELLOW.toString() + ChatColor.BOLD + "PLAYER " + target.getName() + " HAS BEEN KICKED");
                    }else{
                        ConsoleSender.msgConsole("Target not found!");
                    }
                }

            }
        }
        return false;
    }

}
