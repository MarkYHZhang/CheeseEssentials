package me.cheesepro.ce.mainpack;

import me.cheesepro.ce.extra.ConsoleSender;
import me.cheesepro.ce.extra.Messenger;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

/**
 * Created by Mark on 14/10/2014.
 */
public class cmdJump implements CommandExecutor{

    private CEMain plugin;
    private final Messenger msg;

    public cmdJump(CEMain plugin){
        this.plugin = plugin;
        this.msg = new Messenger(plugin);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(sender instanceof Player){
            Player p = (Player) sender;
            if(label.equalsIgnoreCase("jump")){
//                if(!(getTargetBlock(p, 1000).getType().equals(Material.AIR))) {
                    Location old = p.getLocation();
                    float pitch = old.getPitch();
                    float yaw = old.getYaw();
                    p.teleport(getTargetBlock(p, 99).getLocation().add(0, p.getWorld().getHighestBlockYAt(p.getEyeLocation()), 0));
                    Location after = p.getLocation();
                    double x = after.getX();
                    double y = after.getY();
                    double z = after.getZ();
                    Location loc = p.getLocation();
                    loc.setPitch(pitch);
                    loc.setYaw(yaw);
                    loc.setX(x);
                    loc.setY(y);
                    loc.setZ(z);
                    p.teleport(loc);
                    msg.m(p, "e", "Teleported to eye pointing location");
//                }else{
//                    msg.w(p, "d", "Target block is too far");
//                }
            }
        }else{
            ConsoleSender.noConsole();
        }
        return false;
    }


    public Block getTargetBlock(Player player, int range) {
        Location loc = player.getEyeLocation();
        Vector dir = loc.getDirection().normalize();

        Block b = null;

        for (int i = 0; i <= range; i++) {
            b = loc.add(dir).getBlock();
        }

        return b;
    }

}
