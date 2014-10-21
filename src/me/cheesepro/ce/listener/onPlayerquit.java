package me.cheesepro.ce.listener;

import me.cheesepro.ce.extra.Messenger;
import me.cheesepro.ce.mainpack.CEMain;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.List;

/**
 * Created by Mark on 20/10/2014.
 */
public class onPlayerquit implements Listener{

    CEMain plugin;
    private final Messenger msg;

    public onPlayerquit(CEMain main) {
        main.getServer().getPluginManager().registerEvents(this, main);
        this.plugin = main;
        this.msg = new Messenger(plugin);
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e){
        Player p = e.getPlayer();
        String pn = p.getName();
        String quitmsg = plugin.getConfig().getString("messages.quit").replaceAll("&", "§");
        quitmsg = quitmsg.replaceAll("%name%", pn);
        e.setQuitMessage(quitmsg);
    }

}
