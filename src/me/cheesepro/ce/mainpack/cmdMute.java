package me.cheesepro.ce.mainpack;

import me.cheesepro.ce.extra.Messenger;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import java.util.ArrayList;

/**
 * Created by Mark on 08/11/2014.
 */
public class cmdMute implements CommandExecutor, Listener{

    private CEMain plugin;
    private final Messenger msg;

    public cmdMute(CEMain plugin){
        this.plugin = plugin;
        this.msg = new Messenger(plugin);
    }

    public ArrayList<Player> mute = new ArrayList<Player>();
    
    @EventHandler
    public void onPlayerMute(AsyncPlayerChatEvent e) {
        if (mute.contains(e.getPlayer())) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerCommand(PlayerCommandPreprocessEvent e) {
        if (mute.contains(e.getPlayer())) {
            e.setCancelled(true);
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;
        if (cmd.getName().equalsIgnoreCase("mute")) {
            if (args.length != 1) {
                msg.w(p, "4", "Try /mute <player>");
                return true;
            }else {
                Player mutePlayer = Bukkit.getPlayer(args[0]);
                if (mutePlayer == null) {
                    msg.w(p, "e", args[0] + " is not online");
                    return true;
                }
                mute.add(mutePlayer);
                msg.w(p, "4", "Player " + mutePlayer.getName() + " muted!");
                return true;
            }
        }
        if (cmd.getName().equalsIgnoreCase("unmute")) {
            if (args.length != 1) {
                msg.w(p, "4", "Try /unmute <player>");
                return true;
            }else {
                Player mutePlayer = Bukkit.getPlayer(args[0]);
                if (mutePlayer == null) {
                    msg.w(p, "e", args[0]  + " is not online");
                    return true;
                }
                mute.remove(mutePlayer);
                msg.w(p, "4", "Player " + mutePlayer.getName() + " unmuted!");
                return true;
            }
        }
        return false;
    }

}
