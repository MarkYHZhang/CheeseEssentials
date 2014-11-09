package me.cheesepro.ce.mainpack;

import me.cheesepro.ce.extra.Messenger;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import me.cheesepro.ce.extra.ConsoleSender;
import org.bukkit.help.HelpTopic;

/**
 * Created by Mark on 06/10/2014.
 */
public class cmdgm implements CommandExecutor{

    private CEMain plugin;
    private final Messenger msg;

    public cmdgm(CEMain plugin){
        this.plugin = plugin;
        this.msg = new Messenger(plugin);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(sender instanceof Player){
            Player p = (Player) sender;
            if(label.equalsIgnoreCase("gmc")){
                if(args.length==1){
                    if(p.hasPermission("CheeseEss.gm.other")) {
                        Player target = p.getServer().getPlayer(args[0]);
                        if (target != null) {
                            target.setGameMode(GameMode.CREATIVE);
                            msg.main(target, ChatColor.GREEN + "" + ChatColor.BOLD + "Your gamemode has been updated to Creative");
                            msg.main(p, ChatColor.GREEN + "" + ChatColor.BOLD + "Player " + target.getName() + "'s gamemode has been updated to Creative");
                        } else {
                            msg.warn(p, ChatColor.RED + "" + ChatColor.BOLD + "Player " + args[0] + " is not online");
                        }
                    }else{
                        msg.w(p, "4", "You don't have Permission!!");
                    }
                }else{
                    p.setGameMode(GameMode.CREATIVE);
                    msg.main(p, ChatColor.GREEN + "" + ChatColor.BOLD + "Your gamemode has been updated to Creative");
                }
            }else if(label.equalsIgnoreCase("gms")){
                if(args.length==1){
                    if(p.hasPermission("CheeseEss.gm.other")) {
                        Player target = p.getServer().getPlayer(args[0]);
                        if (target != null) {
                            target.setGameMode(GameMode.SURVIVAL);
                            msg.main(target, ChatColor.GREEN + "" + ChatColor.BOLD + "Your gamemode has been updated to Survival");
                            msg.main(p, ChatColor.GREEN + "" + ChatColor.BOLD + "Player " + target.getName() + "'s gamemode has been updated to Survival");
                        } else {
                            msg.warn(p, ChatColor.RED + "" + ChatColor.BOLD + "Player " + target.getName() + " is not online!");
                        }
                    }else{
                        msg.w(p, "4", "You don't have Permission!!");
                    }
                }else{
                    p.setGameMode(GameMode.SURVIVAL);
                    msg.main(p, ChatColor.GREEN + "" + ChatColor.BOLD + "Your gamemode has been updated to Survival");
                }
            }
        }else{
            ConsoleSender.noConsole();
        }
        return false;
    }

}
