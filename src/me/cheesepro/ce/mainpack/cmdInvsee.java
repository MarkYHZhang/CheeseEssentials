package me.cheesepro.ce.mainpack;

import me.cheesepro.ce.extra.Messenger;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Mark on 18/10/2014.
 */
public class cmdInvsee implements CommandExecutor{

    private CEMain plugin;
    private final Messenger msg;

    public cmdInvsee(CEMain plugin){
        this.plugin = plugin;
        this.msg = new Messenger(plugin);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (label.equalsIgnoreCase("invsee")) {
                if (args.length == 1) {
                    Player target = p.getServer().getPlayer(args[0]);
                    if (target != null) {
                        p.openInventory(target.getInventory());
                        msg.m(p, "e", "Inventory of " + target.getName() + " opened");
                    } else {
                        msg.w(p, "5", "Target not found");
                    }
                } else {
                    msg.t(p, "b", "/invsee [name]");
                }

            }
        }
        return false;
    }
}
