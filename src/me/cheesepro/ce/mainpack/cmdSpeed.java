package me.cheesepro.ce.mainpack;

import me.cheesepro.ce.extra.ConsoleSender;
import me.cheesepro.ce.extra.Messenger;
import me.cheesepro.ce.file.playerdata;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Mark on 13/10/2014.
 */
public class cmdSpeed implements CommandExecutor{

    private CEMain plugin;
    private final Messenger msg;
    playerdata settings = playerdata.getInstance();

    public cmdSpeed(CEMain plugin){
        this.plugin = plugin;
        this.msg = new Messenger(plugin);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
        if(sender instanceof Player) {
            Player p = (Player) sender;
            if(label.equalsIgnoreCase("speed")){
                if(args.length==1){
                    String option = args[0];
                    if(option.equalsIgnoreCase("normal")) {
                        if (p.isFlying()) {
                            p.setFlySpeed(0.1F);
                            msg.main(p, ChatColor.WHITE.toString() + ChatColor.BOLD + "Flying speed updated to normal");
                        } else {
                            p.setWalkSpeed(0.2F);
                            msg.main(p, ChatColor.WHITE.toString() + ChatColor.BOLD + "Walking speed updated to normal");
                        }
                    }else if(option.equalsIgnoreCase("fast")){
                        if (p.isFlying()) {
                            p.setFlySpeed(0.60F);
                            msg.main(p, ChatColor.WHITE.toString() + ChatColor.BOLD + "Flying speed updated to fast");
                        } else {
                            p.setWalkSpeed(0.60F);
                            msg.main(p, ChatColor.WHITE.toString() + ChatColor.BOLD + "Walking speed updated to fast");
                        }
                    }else if(option.equalsIgnoreCase("super")){
                        if (p.isFlying()) {
                            p.setFlySpeed(1);
                            msg.main(p, ChatColor.WHITE.toString() + ChatColor.BOLD + "Flying speed updated to super");
                        } else {
                            p.setWalkSpeed(1);
                            msg.main(p, ChatColor.WHITE.toString() + ChatColor.BOLD + "Walking speed updated to super");
                        }
                    }else{
                        msg.tip(p, ChatColor.WHITE.toString() + ChatColor.BOLD + "/speed [option]");
                        msg.tip(p, ChatColor.WHITE.toString() + ChatColor.BOLD + "Options: [normal]  [fast]  [super]");
                    }

                }else{
                    msg.tip(p, ChatColor.WHITE.toString() + ChatColor.BOLD + "/speed [option]");
                    msg.tip(p, ChatColor.WHITE.toString() + ChatColor.BOLD + "Options: [normal]  [fast]  [super]");
                }
            }
        }else{
            ConsoleSender.noConsole();
        }
        return false;
    }

}
