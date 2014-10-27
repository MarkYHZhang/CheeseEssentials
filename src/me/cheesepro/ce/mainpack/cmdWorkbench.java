package me.cheesepro.ce.mainpack;

import me.cheesepro.ce.extra.ConsoleSender;
import me.cheesepro.ce.extra.Messenger;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.Console;

/**
 * Created by Mark on 20/10/2014.
 */
public class cmdWorkbench implements CommandExecutor{

    private CEMain plugin;
    private final Messenger msg;

    public cmdWorkbench(CEMain plugin){
        this.plugin = plugin;
        this.msg = new Messenger(plugin);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if(label.equalsIgnoreCase("workbench")){
                if(args.length==1){
                    Player target = p.getServer().getPlayer(args[0]);
                    if(target!=null){
                        target.openWorkbench(target.getLocation(), true);
                        msg.m(target, "a", "Opened Workbench");
                        msg.m(p, "a", "Opened Workbench for " + target.getName());
                    }else{
                        msg.w(p, "5", "Target not found");
                    }
                }else{
                    p.openWorkbench(p.getLocation(), true);
                    msg.m(p, "a", "Opened Workbench");
                }
            }
        }else{
            ConsoleSender.noConsole();
        }
        return false;
    }
}
