package me.cheesepro.ce.mainpack;

import me.cheesepro.ce.extra.ConsoleSender;
import me.cheesepro.ce.extra.Messenger;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Mark on 10/10/2014.
 */
public class cmdFly implements CommandExecutor{

    private CEMain plugin;
    private Messenger msg;

    public cmdFly(CEMain plugin){
        this.plugin = plugin;
        this.msg = new Messenger(plugin);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(sender instanceof Player){
            Player p = (Player) sender;
            if (label.equalsIgnoreCase("fly")){
                if(args.length==1){
                    Player target = p.getServer().getPlayer(args[0]);
                    if(target!=null){
                        if(target.getGameMode() == GameMode.CREATIVE) {
                            msg.warn(p, ChatColor.RED.toString() + ChatColor.BOLD + "Target's GAMEMODE is Creative, execution ignored");
                        }else{
                            if (target.getAllowFlight() == true) {
                                target.setAllowFlight(false);
                                target.setFlying(false);
                                msg.main(target, ChatColor.GREEN.toString() + ChatColor.BOLD + "You may not fly now");
                                msg.main(p, ChatColor.GREEN.toString() + ChatColor.BOLD + "Target may not fly now");
                            } else {
                                target.setAllowFlight(true);
                                target.setFlying(true);
                                msg.main(target, ChatColor.GREEN.toString() + ChatColor.BOLD + "You may fly now");
                                msg.main(p, ChatColor.GREEN.toString() + ChatColor.BOLD + "Target may fly now");
                            }
                        }
                    }else{
                        msg.warn(p, ChatColor.RED.toString() + ChatColor.BOLD + "Target player is not online");
                    }
                }else{
                    if(p.getGameMode() == GameMode.CREATIVE) {
                        msg.warn(p, ChatColor.RED.toString() + ChatColor.BOLD + "Your GAMEMODE is Creative, execution ignored");
                    }else {
                        if (p.getAllowFlight() == true) {
                            p.setAllowFlight(false);
                            p.setFlying(false);
                            msg.main(p, ChatColor.GREEN.toString() + ChatColor.BOLD + "You may not fly now");
                        } else {
                            p.setAllowFlight(true);
                            p.setFlying(true);
                            msg.main(p, ChatColor.GREEN.toString() + ChatColor.BOLD + "You may fly now");
                        }
                    }
                }
            }
        }else{
            ConsoleSender.noConsole();
        }
        return false;
    }
}
