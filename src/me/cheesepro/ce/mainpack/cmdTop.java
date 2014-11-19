package me.cheesepro.ce.mainpack;

import me.cheesepro.ce.extra.Messenger;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Mark on 19/11/2014.
 */
public class cmdTop implements CommandExecutor {

    private CEMain plugin;
    private final Messenger msg;

    public cmdTop(CEMain plugin){
        this.plugin = plugin;
        this.msg = new Messenger(plugin);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (label.equalsIgnoreCase("top")) {
                float pitch = p.getLocation().getPitch();
                float yaw = p.getLocation().getYaw();
                Location top = p.getWorld().getHighestBlockAt(p.getLocation()).getLocation().add(0, 1, 0);
                top.setPitch(pitch);
                top.setYaw(yaw);
                p.teleport(top);
                msg.m(p, "e", "Teleported to the top");
            }
        }
        return false;
    }

}
