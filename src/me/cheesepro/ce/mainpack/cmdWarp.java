package me.cheesepro.ce.mainpack;

import me.cheesepro.ce.extra.ConsoleSender;
import me.cheesepro.ce.extra.Messenger;
import me.cheesepro.ce.file.warploc;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mark on 02/11/2014.
 */
public class cmdWarp implements CommandExecutor{

    private CEMain plugin;
    private final Messenger msg;

    warploc settings = warploc.getInstance();

    public cmdWarp(CEMain plugin){
        this.plugin = plugin;
        this.msg = new Messenger(plugin);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if(label.equalsIgnoreCase("warplist")){
                if(settings.getData().getString("warps")==null){
                    msg.w(p, "5", "No warps has been set");
                    return true;
                }
                List warplist = new ArrayList();
                for(String warps : settings.getData().getConfigurationSection("warps").getKeys(false)){
                    warplist.add(warps);
                }
                String list = warplist.toString();
                if(list.equalsIgnoreCase("[]")){
                    msg.w(p, "5", "No warps has been set");
                    return true;
                }
                msg.m(p, "a", "======================[Warps]======================");
                msg.m(p, "e", list);
            }

            if(label.equalsIgnoreCase("warp")){
                if (args.length == 0) {
                    msg.w(p, "5",  "Please specify a name!");
                    return true;
                }
                if (settings.getData().getConfigurationSection("warps." + args[0]) == null) {
                    msg.w(p, "5", "Warp " + args[0] + " does not exist!");
                    return true;
                }
                World w = Bukkit.getServer().getWorld(settings.getData().getString("warps." + args[0] + ".world"));
                float pitch = Float.parseFloat(settings.getData().getString("warps." + args[0] + ".pitch"));
                float yaw = Float.parseFloat(settings.getData().getString("warps." + args[0] + ".yaw"));
                double x = settings.getData().getDouble("warps." + args[0] + ".x");
                double y = settings.getData().getDouble("warps." + args[0] + ".y");
                double z = settings.getData().getDouble("warps." + args[0] + ".z");
                Location loc = new Location(w, x, y, z);
                loc.setPitch(pitch);
                loc.setYaw(yaw);
                p.teleport(loc);
                msg.m(p, "a",  "Teleported to " + args[0] + "!");
            }else if(label.equalsIgnoreCase("setwarp")){
                if (args.length == 0) {
                    msg.w(p, "5", "Please specify a name!");
                    return true;
                }
                settings.getData().set("warps." + args[0] + ".world", p.getLocation().getWorld().getName());
                settings.getData().set("warps." + args[0] + ".pitch", p.getLocation().getPitch());
                settings.getData().set("warps." + args[0] + ".yaw", p.getLocation().getYaw());
                settings.getData().set("warps." + args[0] + ".x", p.getLocation().getX());
                settings.getData().set("warps." + args[0] + ".y", p.getLocation().getY());
                settings.getData().set("warps." + args[0] + ".z", p.getLocation().getZ());
                settings.saveData();
                msg.m(p, "a", "Set warp " + args[0] + "!");
            }else if(label.equalsIgnoreCase("delwarp")){
                if (args.length == 0) {
                    msg.w(p, "5", "Please specify a name!");
                    return true;
                }
                if (settings.getData().getConfigurationSection("warps." + args[0]) == null) {
                    msg.w(p, "5", "Warp " + args[0] + " does not exist!");
                    return true;
                }
                settings.getData().set("warps." + args[0], null);
                settings.saveData();
                msg.m(p, "a", "Removed warp " + args[0] + "!");
            }
        }else{
            ConsoleSender.noConsole();
        }
        return false;
    }

}
