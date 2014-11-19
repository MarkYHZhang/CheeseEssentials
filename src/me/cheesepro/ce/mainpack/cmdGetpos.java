package me.cheesepro.ce.mainpack;

import me.cheesepro.ce.extra.Messenger;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Mark on 18/11/2014.
 */
public class cmdGetpos implements CommandExecutor{

    private CEMain plugin;
    private final Messenger msg;

    public cmdGetpos(CEMain plugin){
        this.plugin = plugin;
        this.msg = new Messenger(plugin);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (label.equalsIgnoreCase("getpos")) {
                Location loc = p.getLocation();
                double x = Math.round(loc.getX()*100.0)/100.0;
                double y = Math.round(loc.getY()*100.0)/100.0;
                double z = Math.round(loc.getZ()*100.0)/100.0;
                msg.t(p, "5", "X: " + x);
                msg.t(p, "5", "Y: " + y);
                msg.t(p, "5", "Z: " + z);
            }
        }
        return false;
    }
}
