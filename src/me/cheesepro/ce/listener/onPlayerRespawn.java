package me.cheesepro.ce.listener;

import me.cheesepro.ce.extra.Messenger;
import me.cheesepro.ce.file.hubloc;
import me.cheesepro.ce.mainpack.CEMain;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

/**
 * Created by Mark on 09/11/2014.
 */
public class onPlayerRespawn implements Listener{

    hubloc settings = hubloc.getInstance();
    CEMain plugin;
    private final Messenger msg;

    public onPlayerRespawn(CEMain main) {
        main.getServer().getPluginManager().registerEvents(this, main);
        this.plugin = main;
        this.msg = new Messenger(plugin);
    }

    @EventHandler
    public void onRespawn(PlayerRespawnEvent e){
        Player p = e.getPlayer();
        World w = Bukkit.getServer().getWorld(settings.getData().getString("hub."+".world"));
        float pitch = Float.parseFloat(settings.getData().getString("hub."+".pitch"));
        float yaw = Float.parseFloat(settings.getData().getString("hub."+".yaw"));
        double x = settings.getData().getDouble("hub."+".x");
        double y = settings.getData().getDouble("hub."+".y");
        double z = settings.getData().getDouble("hub."+".z");
        Location loc = new Location(w, x, y, z);
        loc.setPitch(pitch);
        loc.setYaw(yaw);
        e.setRespawnLocation(loc);
    }

}
