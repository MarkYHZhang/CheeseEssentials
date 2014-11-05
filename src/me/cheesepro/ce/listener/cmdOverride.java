package me.cheesepro.ce.listener;

import me.cheesepro.ce.extra.Messenger;
import me.cheesepro.ce.mainpack.CEMain;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.help.HelpTopic;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mark on 08/10/2014.
 */
public class cmdOverride implements Listener{

    CEMain plugin;private final Messenger msg;


    public cmdOverride(CEMain main)
    {
        main.getServer().getPluginManager().registerEvents(this, main);
        this.plugin = main;
        this.msg = new Messenger(plugin);
    }

    @EventHandler
    public void onDisabledCMD(PlayerCommandPreprocessEvent e){
        Player p = e.getPlayer();
        if (e.getMessage().toLowerCase().startsWith("/server")){
            e.setCancelled(true);
            Bukkit.broadcastMessage(ChatColor.GRAY.toString() + e.getPlayer().getName() + " Has connected to" + e.getMessage().replaceAll("/", " "));
        }
        if(p.hasPermission("super.cmd.exists")) {
            msg.main(p, ChatColor.RED.toString() + ChatColor.BOLD + "Command Executed");
        }else{
            if (e.getMessage().toLowerCase().startsWith("/plugins") || e.getMessage().toLowerCase().startsWith("/pl")) {
                e.setCancelled(true);
                msg.tip(p, "This server runs on a Custom Coded Plugin");
            } else if (e.getMessage().toLowerCase().startsWith("/help")) {
                List cmdlist = new ArrayList();
                for (HelpTopic cmdLabel : Bukkit.getServer().getHelpMap().getHelpTopics()) {
                    if (cmdLabel.canSee(p)){
                        msg.m(p, "e", cmdLabel.getName() + " -" + cmdLabel.getShortText());
                    }
                }
                e.setCancelled(true);
            } else if (e.getMessage().toLowerCase().startsWith("/ver") || e.getMessage().toLowerCase().startsWith("/version") || e.getMessage().toLowerCase().startsWith("/bungee")) {
                e.setCancelled(true);
                msg.tip(p, ChatColor.YELLOW.toString() + ChatColor.BOLD + "Version: " + ChatColor.AQUA + ChatColor.BOLD + "MineFutureV2" + ChatColor.RED.toString() + ChatColor.BOLD + " BETA");
            } else if (e.getMessage().toLowerCase().contains("/pex")) {
                e.setCancelled(true);
                msg.warn(p, ChatColor.LIGHT_PURPLE.toString() + ChatColor.BOLD + "Invalid Command type \"/help\" for help");
            } else if (e.getMessage().toLowerCase().contains("//")) {
                e.setCancelled(true);
                msg.warn(p, ChatColor.LIGHT_PURPLE.toString() + ChatColor.BOLD + "Invalid Command type \"/help\" for help");
            }
        }

    }

    @EventHandler
    public void onInvalidCMD(PlayerCommandPreprocessEvent event) {
        if (!event.isCancelled()) {
            Player player = event.getPlayer();
            String cmd = event.getMessage().split(" ")[0];
            HelpTopic topic = Bukkit.getServer().getHelpMap().getHelpTopic(cmd);
            if (topic == null) {
                msg.warn(player, ChatColor.LIGHT_PURPLE.toString() + ChatColor.BOLD + "Invalid Command type \"/help\" for help");
                event.setCancelled(true);
            }
        }
    }

}
