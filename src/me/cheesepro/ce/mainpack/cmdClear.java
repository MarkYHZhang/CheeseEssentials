package me.cheesepro.ce.mainpack;

import me.cheesepro.ce.extra.ConsoleSender;
import me.cheesepro.ce.extra.Messenger;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Mark on 01/11/2014.
 */
public class cmdClear implements CommandExecutor{

    private CEMain plugin;
    private final Messenger msg;

    public cmdClear(CEMain plugin){
        this.plugin = plugin;
        this.msg = new Messenger(plugin);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if(label.equalsIgnoreCase("clear")){
                if(p.hasPermission("CheeseEss.clear.other")) {
                    if (args.length == 1) {
                        Player target = p.getServer().getPlayer(args[0]);
                        if (target != null) {
                            target.getInventory().clear();
                            msg.m(target, "5", "Inventory cleared!");
                            msg.m(p, "5", "Cleared " + target.getName() + "'s inventory");
                        } else {
                            msg.w(p, "4", "Target not found");
                        }
                    } else {
                        p.getInventory().clear();
                        msg.m(p, "5", "Inventory cleared!");
                    }
                }else{
                    p.getInventory().clear();
                    msg.m(p, "5", "Inventory cleared!");
                }
            }
        }else{
            ConsoleSender.noConsole();
        }
        return false;
    }

}
