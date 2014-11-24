package me.cheesepro.ce.mainpack;

import me.cheesepro.ce.extra.ConsoleSender;
import me.cheesepro.ce.extra.Messenger;
import me.cheesepro.ce.file.bandata;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mark on 21/11/2014.
 */
public class cmdBan implements CommandExecutor{

    private CEMain plugin;
    private final Messenger msg;
    bandata settings = bandata.getInstance();

    public cmdBan(CEMain plugin){
        this.plugin = plugin;
        this.msg = new Messenger(plugin);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (cmd.getLabel().equalsIgnoreCase("ban")) {
                if(args.length==0){
                    msg.t(p, "e", "/ban [name] {message}");
                }

                if(args.length==1){
                    OfflinePlayer target = Bukkit.getServer().getOfflinePlayer(args[0]);
                    if(target!=null){
                        settings.getData().set("list." + target.getName() + ".author", "BY: " + p.getName());
                        settings.getData().set("list." + target.getName() + ".msg", "Ban hammer as broken!");
                        settings.saveData();
                        if(target.isOnline()) {
                            Player otarget = Bukkit.getServer().getPlayer(target.getName());
                            otarget.kickPlayer(ChatColor.RED.toString() + ChatColor.BOLD + "--========<" + ChatColor.DARK_RED.toString() + ChatColor.BOLD + "BANNED" + ChatColor.RED.toString() + ChatColor.BOLD + ">========--" + "\n" + "\n" + ChatColor.DARK_PURPLE.toString() + ChatColor.BOLD + "BY: " + p.getName() + "\n" + "\n" + ChatColor.RED.toString() + ChatColor.BOLD + "*===========================*");
                        }
                        Bukkit.getServer().broadcastMessage(ChatColor.YELLOW.toString() + ChatColor.BOLD + "PLAYER " + target.getName().toUpperCase() + " HAS BEEN BANNED");
                    }else{
                        msg.w(p, "4", "Player not found");
                    }
                }
                String banmsg="";
                for(int i=1;i<args.length;i++){
                    banmsg = banmsg + args[i] + " ";
                }
                if(args.length>1){
                    OfflinePlayer target = Bukkit.getServer().getOfflinePlayer(args[0]);
                    if(target!=null){
                        settings.getData().set("list." + target.getName() + ".author", "BY: " + p.getName());
                        settings.getData().set("list." + target.getName() + ".msg", banmsg);
                        settings.saveData();
                        if(target.isOnline()) {
                            Player otarget = Bukkit.getServer().getPlayer(target.getName());
                            otarget.kickPlayer(ChatColor.RED.toString() + ChatColor.BOLD + "--========<" + ChatColor.DARK_RED.toString() + ChatColor.BOLD + "BANNED" + ChatColor.RED.toString() + ChatColor.BOLD + ">========--" + "\n" + "\n" + ChatColor.DARK_PURPLE.toString() + ChatColor.BOLD + "BY: " + p.getName() + "\n" + "\n" + ChatColor.YELLOW.toString() + ChatColor.BOLD + banmsg + "\n" + "\n" + ChatColor.RED.toString() + ChatColor.BOLD + "===================");
                        }
                        Bukkit.getServer().broadcastMessage(ChatColor.YELLOW.toString() + ChatColor.BOLD + "PLAYER " + target.getName().toUpperCase() + " HAS BEEN BANNED");
                    }else{
                        msg.w(p, "4", "Player not found");
                    }
                }

            }else if(cmd.getLabel().equalsIgnoreCase("unban")){
                if (args.length == 0) {
                    msg.t(p, "e", "/unban [name]");
                    return true;
                }
                if (settings.getData().getConfigurationSection("list." + args[0]) == null) {
                    msg.w(p, "5", "Player not found");
                    return true;
                }
                settings.getData().set("list." + args[0], null);
                settings.saveData();
                msg.m(p, "a", "Unbanned " + args[0] + "!");
            }else if(cmd.getLabel().equalsIgnoreCase("banlist")){
                if(settings.getData().getString("list")==null){
                    msg.w(p, "5", "No one has been banned yet");
                    return true;
                }
                List banlist = new ArrayList();
                for(String bans : settings.getData().getConfigurationSection("list").getKeys(false)){
                    banlist.add(bans);
                }
                String list = banlist.toString();
                if(list.equalsIgnoreCase("[]")){
                    msg.w(p, "5", "No one has been banned yet");
                    return true;
                }
                msg.m(p, "4", "======================[BANS]======================");
                msg.m(p, "a", list);
            }
        }else{
            if (cmd.getLabel().equalsIgnoreCase("ban")) {
                if(args.length==0){
                    ConsoleSender.msgConsole("/ban [name] {message}");
                }

                if(args.length==1){
                    OfflinePlayer target = Bukkit.getServer().getOfflinePlayer(args[0]);
                    if(target!=null){
                        settings.getData().set("list." + target.getName() + ".author", "BY: CONSOLE");
                        settings.getData().set("list." + target.getName() + ".msg", "Ban hammer as broken!");
                        settings.saveData();
                        if(target.isOnline()) {
                            Player otarget = Bukkit.getServer().getPlayer(target.getName());
                            otarget.kickPlayer(ChatColor.RED.toString() + ChatColor.BOLD + "--========<" + ChatColor.DARK_RED.toString() + ChatColor.BOLD + "BANNED" + ChatColor.RED.toString() + ChatColor.BOLD + ">========--" + "\n" + "\n" + ChatColor.DARK_PURPLE.toString() + ChatColor.BOLD + "BY: CONSOLE" + "\n" + "\n" + ChatColor.RED.toString() + ChatColor.BOLD + "*===========================*");
                        }
                        Bukkit.getServer().broadcastMessage(ChatColor.YELLOW.toString() + ChatColor.BOLD + "PLAYER " + target.getName().toUpperCase() + " HAS BEEN BANNED");
                    }else{
                        ConsoleSender.msgConsole("Player not found");
                    }
                }
                String banmsg="";
                for(int i=1;i<args.length;i++){
                    banmsg = banmsg + args[i] + " ";
                }
                if(args.length>1){
                    OfflinePlayer target = Bukkit.getServer().getOfflinePlayer(args[0]);
                    if(target!=null){
                        settings.getData().set("list." + target.getName() + ".author", "BY: CONSOLE");
                        settings.getData().set("list." + target.getName() + ".msg", banmsg);
                        settings.saveData();
                        if(target.isOnline()) {
                            Player otarget = Bukkit.getServer().getPlayer(target.getName());
                            otarget.kickPlayer(ChatColor.RED.toString() + ChatColor.BOLD + "--========<" + ChatColor.DARK_RED.toString() + ChatColor.BOLD + "BANNED" + ChatColor.RED.toString() + ChatColor.BOLD + ">========--" + "\n" + "\n" + ChatColor.DARK_PURPLE.toString() + ChatColor.BOLD + "BY: CONSOLE" + "\n" + "\n" + ChatColor.YELLOW.toString() + ChatColor.BOLD + banmsg + "\n" + "\n" + ChatColor.RED.toString() + ChatColor.BOLD + "*===========================*");
                        }
                        Bukkit.getServer().broadcastMessage(ChatColor.YELLOW.toString() + ChatColor.BOLD + "PLAYER " + target.getName().toUpperCase() + " HAS BEEN BANNED");
                    }else{
                        ConsoleSender.msgConsole("Player not found");
                    }
                }

            }else if(cmd.getLabel().equalsIgnoreCase("unban")){
                if (args.length == 0) {
                    ConsoleSender.msgConsole("/unban [name]");
                    return true;
                }
                if (settings.getData().getConfigurationSection("list." + args[0]) == null) {
                    ConsoleSender.msgConsole("Player not found");
                    return true;
                }
                settings.getData().set("list." + args[0], null);
                settings.saveData();
                ConsoleSender.msgConsole("Unbanned " + args[0] + "!");
            }else if(cmd.getLabel().equalsIgnoreCase("banlist")){
                if(settings.getData().getString("list")==null){
                    ConsoleSender.msgConsole("No one has been banned yet");
                    return true;
                }
                List banlist = new ArrayList();
                for(String bans : settings.getData().getConfigurationSection("list").getKeys(false)){
                    banlist.add(bans);
                }
                String list = banlist.toString();
                if(list.equalsIgnoreCase("[]")){
                    ConsoleSender.msgConsole("No one has been banned yet");
                    return true;
                }
                ConsoleSender.msgConsole("======================[BANS]======================");
                ConsoleSender.msgConsole(list);
            }
        }
        return false;
    }
    
}