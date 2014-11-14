package me.cheesepro.ce.mainpack;

import me.cheesepro.ce.extra.ConsoleSender;
import me.cheesepro.ce.extra.Messenger;
import me.cheesepro.ce.file.homeloc;
import me.cheesepro.ce.file.homeloc;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachmentInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Mark on 05/11/2014.
 */
public class cmdHome implements CommandExecutor{

    private CEMain plugin;
    private final Messenger msg;

    homeloc settings = homeloc.getInstance();

    public cmdHome(CEMain plugin){
        this.plugin = plugin;
        this.msg = new Messenger(plugin);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            UUID uuid = p.getUniqueId();
            String id = uuid.toString();
            if(label.equalsIgnoreCase("homelist")){
                if(settings.getData().getString("homes." + id)==null){
                    msg.w(p, "5", "No homes has been set");
                    return true;
                }
                List homelist = new ArrayList();
                for(String homes : settings.getData().getConfigurationSection("homes." + id).getKeys(false)){
                    homelist.add(homes);
                }
                String list = homelist.toString();
                msg.m(p, "f", "=============[Homes]=============");
                msg.m(p, "e", list);
            }

            if(label.equalsIgnoreCase("home")){
                if (args.length == 0) {
                    msg.w(p, "5",  "Please specify a name!");
                    return true;
                }
                if (settings.getData().getConfigurationSection("homes." + id + "." + args[0]) == null) {
                    msg.w(p, "5", "Home " + args[0] + " does not exist!");
                    return true;
                }
                World w = Bukkit.getServer().getWorld(settings.getData().getString("homes." + id + "." + args[0] + ".world"));
                float pitch = Float.parseFloat(settings.getData().getString("homes." + id + "." + args[0] + ".pitch"));
                float yaw = Float.parseFloat(settings.getData().getString("homes." + id + "." + args[0] + ".yaw"));
                double x = settings.getData().getDouble("homes." + id + "." + args[0] + ".x");
                double y = settings.getData().getDouble("homes." + id + "." + args[0] + ".y");
                double z = settings.getData().getDouble("homes." + id + "." + args[0] + ".z");
                Location loc = new Location(w, x, y, z);
                loc.setPitch(pitch);
                loc.setYaw(yaw);
                p.teleport(loc);
                msg.m(p, "a",  "Teleported to home " + args[0] + "!");
            }else if(label.equalsIgnoreCase("sethome")){
                if (args.length == 0) {
                    msg.w(p, "5", "Please specify a name!");
                    return true;
                }
                for (PermissionAttachmentInfo perm : p.getEffectivePermissions()) {
                    if (perm.getPermission().startsWith("cheeseess.homemax.")) {
                        String numberOnly= perm.getPermission().replaceAll("[^0-9]", "");
                        int homeCount = 0;
                        try {
                              homeCount = Integer.parseInt(numberOnly);
                                List homenum = new ArrayList();
                                if(settings.getData().getString("homes." + id)!=null) {
                                    for (String homes : settings.getData().getConfigurationSection("homes." + id).getKeys(false)) {
                                        homenum.add(homes);
                                    }
                                    int has = homenum.size() + 1;
                                    if (has <= homeCount) {
                                        settings.getData().set("homes." + id + "." + args[0] + ".world", p.getLocation().getWorld().getName());
                                        settings.getData().set("homes." + id + "." + args[0] + ".pitch", p.getLocation().getPitch());
                                        settings.getData().set("homes." + id + "." + args[0] + ".yaw", p.getLocation().getYaw());
                                        settings.getData().set("homes." + id + "." + args[0] + ".x", p.getLocation().getX());
                                        settings.getData().set("homes." + id + "." + args[0] + ".y", p.getLocation().getY());
                                        settings.getData().set("homes." + id + "." + args[0] + ".z", p.getLocation().getZ());
                                        settings.saveData();
                                        msg.m(p, "a", "Set home " + args[0] + "!");
                                        return true;
                                    } else {
                                        msg.w(p, "4", "Reached maximum home limit");
                                        return true;
                                    }
                                }else{
                                    settings.getData().set("homes." + id + "." + args[0] + ".world", p.getLocation().getWorld().getName());
                                    settings.getData().set("homes." + id + "." + args[0] + ".pitch", p.getLocation().getPitch());
                                    settings.getData().set("homes." + id + "." + args[0] + ".yaw", p.getLocation().getYaw());
                                    settings.getData().set("homes." + id + "." + args[0] + ".x", p.getLocation().getX());
                                    settings.getData().set("homes." + id + "." + args[0] + ".y", p.getLocation().getY());
                                    settings.getData().set("homes." + id + "." + args[0] + ".z", p.getLocation().getZ());
                                    settings.saveData();
                                    msg.m(p, "a", "Set home " + args[0] + "!");
                                    return false;
                                }
                        } catch (NumberFormatException ex) {
                            ConsoleSender.msgConsole("CheeseEss.home.int != CheeseEss.home.string");
                            return true;
                        }
                    }
                }

            }else if(label.equalsIgnoreCase("delhome")){
                if (args.length == 0) {
                    msg.w(p, "5", "Please specify a name!");
                    return true;
                }
                if (settings.getData().getConfigurationSection("homes." + id + "." + args[0]) == null) {
                    msg.w(p, "5", "home " + args[0] + " does not exist!");
                    return true;
                }
                settings.getData().set("homes." + id + "." + args[0], null);
                settings.saveData();
                msg.m(p, "a", "Removed home " + args[0] + "!");
            }
        }else{
            ConsoleSender.noConsole();
        }
        return false;
    }

}
