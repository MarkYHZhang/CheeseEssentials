package me.cheesepro.ce.mainpack;

import me.cheesepro.ce.extra.ConsoleSender;
import me.cheesepro.ce.extra.Messenger;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Mark on 08/11/2014.
 */
public class cmdKill implements CommandExecutor{

    private CEMain plugin;
    private final Messenger msg;

    public cmdKill(CEMain plugin){
        this.plugin = plugin;
        this.msg = new Messenger(plugin);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label,String[] args) {
        Player p = (Player) sender;
        if (sender instanceof Player) {
            if (label.equalsIgnoreCase("kill")) {
                if(args.length==1){
                    Player target = p.getServer().getPlayer(args[0]);
                    if(target!=null){
                        target.setHealth(0.0);
                        msg.m(p, "5", "Killed " + target.getName());
                    }else{
                        msg.w(p, "4", "Target not found");
                    }
                }else{
                    msg.t(p, "a", "/kill [name]");
                }
            }
        }else{
            ConsoleSender.noConsole();
        }
        return false;
    }


}
