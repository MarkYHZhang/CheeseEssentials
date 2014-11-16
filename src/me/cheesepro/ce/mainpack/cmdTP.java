package me.cheesepro.ce.mainpack;

import me.cheesepro.ce.extra.ConsoleSender;
import me.cheesepro.ce.extra.Messenger;
import me.cheesepro.ce.file.playerdata;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.Console;

/**
 * Created by Mark on 11/10/2014.
 */
public class cmdTP implements CommandExecutor{

    private CEMain plugin;
    private final Messenger msg;
    playerdata settings = playerdata.getInstance();

    public cmdTP(CEMain plugin){
        this.plugin = plugin;
        this.msg = new Messenger(plugin);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(sender instanceof Player){
            Player p = (Player) sender;
            String pn = p.getName();
            Server server = p.getServer();
            if(label.equalsIgnoreCase("tp")){
                if(args.length==1){
                    Player target = p.getServer().getPlayer(args[0]);
                    if(target!=null) {
                        p.teleport(target);
                        msg.main(p, ChatColor.BLUE.toString() + ChatColor.BOLD + "Teleported to " + target.getName());
                    }else{
                        msg.warn(p, ChatColor.RED.toString() + ChatColor.BOLD + "Invalid target Player");
                    }
                }else if(args.length==2){
                    if(p.hasPermission("CheeseEss.tp.other")) {
                        Player target1 = p.getServer().getPlayer(args[0]);
                        Player target2 = p.getServer().getPlayer(args[1]);
                        if (target1 != null && target2 != null) {
                            target1.teleport(target2);
                            msg.main(p, ChatColor.BLUE.toString() + ChatColor.BOLD + "Teleported " + target1.getName() + " to " + target2.getName());
                            msg.main(target1, ChatColor.BLUE.toString() + ChatColor.BOLD + "Teleported to " + target2.getName());
                        } else {
                            msg.warn(p, ChatColor.RED.toString() + ChatColor.BOLD + "Invalid target Players");
                        }
                    }
                }else{
                    msg.tip(p, ChatColor.GOLD.toString() + ChatColor.BOLD + "/tp [target]");
                    msg.tip(p, ChatColor.GOLD.toString() + ChatColor.BOLD + "/tp [target1] [target2]");
                }
            }else if(label.equalsIgnoreCase("tpall")){
                for(Player all : Bukkit.getOnlinePlayers()){
                    all.teleport(p);
                }
                p.getServer().broadcastMessage(ChatColor.YELLOW.toString() + ChatColor.BOLD + "ALL PLAYERS TELEPORTED TO " + p.getName().toUpperCase());
            }else if(label.equalsIgnoreCase("back")){
                float pitch = Float.parseFloat(settings.getData().getString(pn + ".previousloc.pitch"));
                float yaw = Float.parseFloat(settings.getData().getString(pn + ".previousloc.yaw"));
                int x = Integer.parseInt(settings.getData().getString(pn + ".previousloc.X"));
                int y = Integer.parseInt(settings.getData().getString(pn + ".previousloc.Y"));
                int z = Integer.parseInt(settings.getData().getString(pn + ".previousloc.Z"));
                World w = server.getWorld(settings.getData().getString(pn + ".previousloc.W"));
                Location loc = new Location(w, x, y, z);
                loc.setPitch(pitch);
                loc.setYaw(yaw);
                p.teleport(loc);
                msg.main(p, ChatColor.GREEN.toString() + ChatColor.BOLD + "Teleported to your last location");
            }else if(label.equalsIgnoreCase("logoutloc")){
                float pitch = Float.parseFloat(settings.getData().getString(pn + ".logoutloc.pitch"));
                float yaw = Float.parseFloat(settings.getData().getString(pn + ".logoutloc.yaw"));
                int x = Integer.parseInt(settings.getData().getString(pn + ".logoutloc.X"));
                int y = Integer.parseInt(settings.getData().getString(pn + ".logoutloc.Y"));
                int z = Integer.parseInt(settings.getData().getString(pn + ".logoutloc.Z"));
                World w = server.getWorld(settings.getData().getString(pn + ".logoutloc.W"));
                Location loc = new Location(w, x, y, z);
                loc.setPitch(pitch);
                loc.setYaw(yaw);
                p.teleport(loc);
                msg.main(p, ChatColor.GREEN.toString() + ChatColor.BOLD + "Teleported to your logout location");
            }
        }else{
            if(label.equalsIgnoreCase("tp")) {
                if (args.length == 2) {
                    Player target1 = Bukkit.getServer().getPlayer(args[0]);
                    Player target2 = Bukkit.getServer().getPlayer(args[1]);
                    if (target1 != null && target2 != null) {
                        target1.teleport(target2);
                        ConsoleSender.msgConsole(ChatColor.BLUE.toString() + ChatColor.BOLD + "Teleported" + target1.getName() + " to " + target2.getName());
                        msg.main(target1, ChatColor.BLUE.toString() + ChatColor.BOLD + "Teleported to " + target2.getName());
                    } else {
                        ConsoleSender.msgConsole(ChatColor.RED.toString() + ChatColor.BOLD + "Invalid target Players");
                    }
                } else {
                    ConsoleSender.msgConsole(ChatColor.GOLD.toString() + ChatColor.BOLD + "/tp [target1] [target2]");
                }
            }else if(label.equalsIgnoreCase("tpall")){
                if(args.length==1){
                    Player target = Bukkit.getServer().getPlayer(args[0]);
                    if(target!=null){
                        for(Player all : Bukkit.getServer().getOnlinePlayers()){
                            all.teleport(target);
                        }
                        ConsoleSender.msgConsole(ChatColor.YELLOW.toString() + ChatColor.BOLD + "ALL PLAYERS TELEPORTED TO " + target.getName().toUpperCase());
                    }else{
                        ConsoleSender.msgConsole(ChatColor.RED.toString() + ChatColor.BOLD + "Target is Invalid!");
                    }
                }else{
                    ConsoleSender.msgConsole(ChatColor.GREEN.toString() + ChatColor.BOLD + "/tpall [target]");
                }
            }
        }
        return false;
    }


}
