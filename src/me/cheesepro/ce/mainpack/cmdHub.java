package me.cheesepro.ce.mainpack;

import me.cheesepro.ce.extra.ConsoleSender;
import me.cheesepro.ce.extra.Messenger;
import me.cheesepro.ce.file.hubloc;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Mark on 05/11/2014.
 */
public class cmdHub implements CommandExecutor{

    private CEMain plugin;
    private final Messenger msg;

    hubloc settings = hubloc.getInstance();

    public cmdHub(CEMain plugin){
        this.plugin = plugin;
        this.msg = new Messenger(plugin);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if(label.equalsIgnoreCase("hub")){
                if (settings.getData().getConfigurationSection("hub") == null) {
                    msg.w(p, "5", "No hub as been set");
                    return true;
                }
                World w = Bukkit.getServer().getWorld(settings.getData().getString("hub."+".world"));
                float pitch = Float.parseFloat(settings.getData().getString("hub."+".pitch"));
                float yaw = Float.parseFloat(settings.getData().getString("hub."+".yaw"));
                double x = settings.getData().getDouble("hub."+".x");
                double y = settings.getData().getDouble("hub."+".y");
                double z = settings.getData().getDouble("hub."+".z");
                Location loc = new Location(w, x, y, z);
                loc.setPitch(pitch);
                loc.setYaw(yaw);
                p.teleport(loc);
                msg.m(p, "a", "Teleported");
                return true;
            }else if(label.equalsIgnoreCase("sethub")){
                settings.getData().set("hub."+".world", p.getLocation().getWorld().getName());
                settings.getData().set("hub."+".pitch", p.getLocation().getPitch());
                settings.getData().set("hub."+".yaw", p.getLocation().getYaw());
                settings.getData().set("hub."+".x", p.getLocation().getX());
                settings.getData().set("hub."+".y", p.getLocation().getY());
                settings.getData().set("hub."+".z", p.getLocation().getZ());
                settings.saveData();
                msg.m(p, "a", "Hub set!");
            }else if(label.equals("delhub")){
                settings.getData().set("hub", null);
                settings.saveData();
                msg.m(p, "a", "Removed hub!");
            }
        }else{
            ConsoleSender.noConsole();
        }
        return false;
    }

}
