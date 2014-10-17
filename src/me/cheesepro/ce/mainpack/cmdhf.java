package me.cheesepro.ce.mainpack;

import me.cheesepro.ce.extra.ConsoleSender;
import me.cheesepro.ce.extra.Messenger;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Mark on 07/10/2014.
 */
public class cmdhf implements CommandExecutor{

    private CEMain plugin;
    private final Messenger msg;

    public cmdhf(CEMain plugin){
        this.plugin = plugin;
        this.msg = new Messenger(plugin);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(sender instanceof Player){
            Player p = (Player) sender;
            if(label.equalsIgnoreCase("heal")){
                if(args.length==1){
                    Player target = p.getServer().getPlayer(args[0]);
                    if(target!=null){
                        target.setHealth(20.0);
                        target.setSaturation(20);
                        msg.main(target, ChatColor.YELLOW.toString() + ChatColor.BOLD + "Healed");
                        msg.main(p, ChatColor.YELLOW.toString() + ChatColor.BOLD + "Player " + target.getName() + " is healed");
                    }else{
                        msg.warn(p, ChatColor.RED.toString() + ChatColor.BOLD + "Target player is not online");
                    }
                }else{
                    p.setHealth(20.0);
                    p.setSaturation(20);
                    msg.main(p, ChatColor.YELLOW.toString() + ChatColor.BOLD + "Healed");
                }
            }else if(label.equalsIgnoreCase("feed")){
                if(args.length==1){
                    Player target = p.getServer().getPlayer(args[0]);
                    if(target!=null){
                        target.setSaturation(20);
                        msg.main(target, ChatColor.YELLOW.toString() + ChatColor.BOLD + "Fed");
                        msg.main(p, ChatColor.YELLOW.toString() + ChatColor.BOLD + "Player " + target.getName() + " is fed");
                    }else{
                        msg.warn(p, ChatColor.RED.toString() + ChatColor.BOLD + "Target player is not online");
                    }
                }else{
                    p.setSaturation(20);
                    msg.main(p, ChatColor.YELLOW.toString() + ChatColor.BOLD + "Fed");
                }
            }
        }else{
            ConsoleSender.noConsole();
        }
        return false;
    }
}
