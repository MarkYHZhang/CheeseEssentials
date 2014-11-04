package me.cheesepro.ce.mainpack;

import me.cheesepro.ce.extra.ConsoleSender;
import me.cheesepro.ce.extra.Messenger;
import me.cheesepro.ce.file.spawnloc;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Mark on 04/11/2014.
 */
public class cmdSpawn implements CommandExecutor{

    private CEMain plugin;
    private final Messenger msg;

    spawnloc settings = spawnloc.getInstance();

    public cmdSpawn(CEMain plugin){
        this.plugin = plugin;
        this.msg = new Messenger(plugin);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if(label.equalsIgnoreCase("spawn")){
                World pw = p.getWorld();
                String pwn = pw.getName();
                if (settings.getData().getConfigurationSection("spawns." + pwn) == null) {
                    msg.w(p, "5", "Spawn is not set in this world");
                    return true;
                }
                for(String spawns : settings.getData().getConfigurationSection("spawns").getKeys(false)){
                        float pitch = Float.parseFloat(settings.getData().getString("spawns." + pwn + ".pitch"));
                        float yaw = Float.parseFloat(settings.getData().getString("spawns." + pwn + ".yaw"));
                        double x = settings.getData().getDouble("spawns." + pwn + ".x");
                        double y = settings.getData().getDouble("spawns." + pwn + ".y");
                        double z = settings.getData().getDouble("spawns." + pwn + ".z");
                        Location loc = new Location(pw, x, y, z);
                        loc.setPitch(pitch);
                        loc.setYaw(yaw);
                        p.teleport(loc);
                        msg.m(p, "a", "Teleported");
                        return true;
                }
            }else if(label.equalsIgnoreCase("setspawn")){
                World pw = p.getWorld();
                String pwn = pw.getName();
                settings.getData().set("spawns." + pwn + ".pitch", p.getLocation().getPitch());
                settings.getData().set("spawns." + pwn + ".yaw", p.getLocation().getYaw());
                settings.getData().set("spawns." + pwn + ".x", p.getLocation().getX());
                settings.getData().set("spawns." + pwn + ".y", p.getLocation().getY());
                settings.getData().set("spawns." + pwn + ".z", p.getLocation().getZ());
                settings.saveData();
                msg.m(p, "a", "Set spawn in " + pwn + "!");
            }else if(label.equals("delspawn")){
                World pw = p.getWorld();
                String pwn = pw.getName();
                settings.getData().set("spawns." + pwn, null);
                settings.saveData();
                msg.m(p, "a", "Removed spawn in " + pwn + "!");
            }
        }else{
            ConsoleSender.noConsole();
        }
        return false;
    }

}
