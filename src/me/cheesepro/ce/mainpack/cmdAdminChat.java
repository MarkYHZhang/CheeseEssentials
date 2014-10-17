package me.cheesepro.ce.mainpack;

import me.cheesepro.ce.extra.ConsoleSender;
import me.cheesepro.ce.extra.Messenger;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

/**
 * Created by Mark on 10/10/2014.
 */
public class cmdAdminChat implements CommandExecutor, Listener{

    private CEMain plugin;
    private final Messenger msg;

    public cmdAdminChat(CEMain plugin){
        this.plugin = plugin;
        this.msg = new Messenger(plugin);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(sender instanceof Player){
            Player p = (Player) sender;
            if(label.equalsIgnoreCase("adminchat")){
                if(args.length==2){
                    String option = args[0];
                    if(option!=null){
                        if(option.equalsIgnoreCase("add")){
                            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + args[1] + " add super.adminchat");
                            msg.main(p, ChatColor.GREEN.toString() + ChatColor.BOLD + "Target added to adminchat accessible players");
                        }else if(option.equalsIgnoreCase("remove")){
                            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + args[1] + " remove super.adminchat");
                            msg.main(p, ChatColor.GREEN.toString() + ChatColor.BOLD + "Target removed from adminchat accessible players");
                        }else{
                            msg.warn(p, ChatColor.YELLOW.toString() + ChatColor.BOLD + "Invalid option input!");
                            msg.tip(p, ChatColor.LIGHT_PURPLE.toString() + ChatColor.BOLD + "Options: [add/remove]");
                        }
                    }else{
                        msg.warn(p, ChatColor.YELLOW.toString() + ChatColor.BOLD + "Arguments are invalid");
                        msg.tip(p, ChatColor.LIGHT_PURPLE.toString() + ChatColor.BOLD + "/adminchat [option] [playername]");
                        msg.tip(p, ChatColor.LIGHT_PURPLE.toString() + ChatColor.BOLD + "Options: [add/remove]");
                    }
                }else{
                    msg.tip(p, ChatColor.LIGHT_PURPLE.toString() + ChatColor.BOLD + "/adminchat [option] [playername]");
                    msg.tip(p, ChatColor.LIGHT_PURPLE.toString() + ChatColor.BOLD + "Options: [add/remove]");
                }
            }
        }else{
            if(label.equalsIgnoreCase("adminchat")){
                if(args.length==2){
                    String option = args[0];
                    if(option!=null){
                        if(option.equalsIgnoreCase("add")){
                            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + args[1] + " add super.adminchat");
                            ConsoleSender.msgConsole(ChatColor.GREEN.toString() + ChatColor.BOLD + "Target added to adminchat accessible players");
                        }else if(option.equalsIgnoreCase("remove")){
                            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + args[1] + " remove super.adminchat");
                            ConsoleSender.msgConsole(ChatColor.GREEN.toString() + ChatColor.BOLD + "Target removed from adminchat accessible players");
                        }else{
                            ConsoleSender.msgConsole(ChatColor.YELLOW.toString() + ChatColor.BOLD + "Invalid option input!");
                            ConsoleSender.msgConsole(ChatColor.LIGHT_PURPLE.toString() + ChatColor.BOLD + "Options: [add/remove]");
                        }
                    }else{
                        ConsoleSender.msgConsole(ChatColor.YELLOW.toString() + ChatColor.BOLD + "Arguments are invalid");
                        ConsoleSender.msgConsole(ChatColor.LIGHT_PURPLE.toString() + ChatColor.BOLD + "/adminchat [option] [playername]");
                        ConsoleSender.msgConsole(ChatColor.LIGHT_PURPLE.toString() + ChatColor.BOLD + "Options: [add/remove]");
                    }
                }else{
                    ConsoleSender.msgConsole(ChatColor.LIGHT_PURPLE.toString() + ChatColor.BOLD + "/adminchat [option] [playername]");
                    ConsoleSender.msgConsole(ChatColor.LIGHT_PURPLE.toString() + ChatColor.BOLD + "Options: [add/remove]");
                }
            }
        }
        return false;
    }






}
