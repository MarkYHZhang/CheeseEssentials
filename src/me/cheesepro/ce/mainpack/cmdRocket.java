package me.cheesepro.ce.mainpack;

import me.cheesepro.ce.extra.Messenger;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

/**
 * Created by Mark on 08/11/2014.
 */
public class cmdRocket implements CommandExecutor{

    private CEMain plugin;
    private Messenger msg;

    public cmdRocket(CEMain plugin){
        this.plugin = plugin;
        this.msg = new Messenger(plugin);
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (cmd.getLabel().equalsIgnoreCase("rocket")) {
                if(args.length==1){
                    Player target = p.getServer().getPlayer(args[0]);
                    if(target!=null){
                        Vector v = target.getVelocity();
                        target.setVelocity(v.setZ(20));
                        msg.m(p, "5", "Rocketed " + target.getName());
                        msg.m(target, "e", "You are rocketed");
                    }else if(args[0].equalsIgnoreCase("all")){
                        for(Player all : Bukkit.getServer().getOnlinePlayers()){
                            Vector v = all.getVelocity();
                            all.setVelocity(v.setZ(20));
                            msg.m(all, "e", "You are rocketed!");
                        }
                        msg.m(p, "5", "rocketed everyone!");

                    }else{
                        msg.w(p, "4", "Target not found!");
                    }
                }else{
                    msg.t(p, "e", "/rocket [name]");
                }

            }
        }
        return false;
    }
}
