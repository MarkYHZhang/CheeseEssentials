package me.cheesepro.ce.mainpack;

import me.cheesepro.ce.extra.ConsoleSender;
import me.cheesepro.ce.extra.Messenger;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class cmdSlap implements CommandExecutor{


    private CEMain plugin;
    private Messenger msg;

    public cmdSlap(CEMain plugin){
        this.plugin = plugin;
        this.msg = new Messenger(plugin);
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(sender instanceof Player){
            Player p = (Player) sender;
            if(cmd.getLabel().equalsIgnoreCase("slap")){
                if(args.length==1){
                    Player target = p.getServer().getPlayer(args[0]);
                    if(target!=null){
                        target.setVelocity(new Vector(10, 0, 0));
                        msg.m(p, "5", "Slapped " + target.getName());
                        msg.m(target, "e", "You are slapped");
                    }else if(args[0].equalsIgnoreCase("all")){
                        for(Player all : Bukkit.getServer().getOnlinePlayers()){
                            all.setVelocity(new Vector(10, 0, 0));
                            msg.m(all, "e", "You are slapped!");
                        }
                        msg.m(p, "5", "Slapped everyone!");

                    }else{
                        msg.w(p, "4", "Target not found!");
                    }
                }else{
                    msg.t(p, "e", "/slap [name]");
                }
            }
        }else{
            ConsoleSender.noConsole();
        }
        return false;
    }

}
