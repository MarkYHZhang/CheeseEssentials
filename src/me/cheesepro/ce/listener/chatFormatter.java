package me.cheesepro.ce.listener;

import me.cheesepro.ce.extra.Messenger;
import me.cheesepro.ce.mainpack.CEMain;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

/**
 * Created by Mark on 18/10/2014.
 */
public class chatFormatter implements Listener {

    CEMain plugin;
    private final Messenger msg;


    public chatFormatter(CEMain main) {
        main.getServer().getPluginManager().registerEvents(this, main);
        this.plugin = main;
        this.msg = new Messenger(plugin);
    }

    @EventHandler(priority= EventPriority.HIGH)
    public void onChat(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();
        if(!(e.getFormat().equalsIgnoreCase("<" + "%s" + "> " + "%s"))) {
            if (p.hasPermission("CheeseEss.chat.player")) {
                String playerprefix = plugin.getConfig().getString("chat_format.group_prefix.player");
                playerprefix = playerprefix.replaceAll("&", "§");
                String playername = plugin.getConfig().getString("chat_format.group_name_color.player");
                playername = playername.replaceAll("&", "§");
                String playerchat = plugin.getConfig().getString("chat_format.group_chat_color.player");
                playerchat = playerchat.replaceAll("&", "§");
                String playerchatsign = plugin.getConfig().getString("chat_format.group_chatsign.player");
                playerchatsign = playerchatsign.replaceAll("&", "§");
                e.setFormat(playerprefix + " " + playername + "%s" + playerchatsign + " " + playerchat + "%s");
            }
            if (p.hasPermission("CheeseEss.chat.helper")) {
                String helperprefix = plugin.getConfig().getString("chat_format.group_prefix.helper");
                helperprefix = helperprefix.replaceAll("&", "§");
                String helpername = plugin.getConfig().getString("chat_format.group_name_color.helper");
                helpername = helpername.replaceAll("&", "§");
                String helperchat = plugin.getConfig().getString("chat_format.group_chat_color.helper");
                helperchat = helperchat.replaceAll("&", "§");
                String helperchatsign = plugin.getConfig().getString("chat_format.group_chatsign.helper");
                helperchatsign = helperchatsign.replaceAll("&", "§");
                e.setFormat(helperprefix + " " + helpername + "%s" + helperchatsign + " " + helperchat + "%s");
            }
            if (p.hasPermission("CheeseEss.chat.builder")) {
                String builderprefix = plugin.getConfig().getString("chat_format.group_prefix.builder");
                builderprefix = builderprefix.replaceAll("&", "§");
                String buildername = plugin.getConfig().getString("chat_format.group_name_color.builder");
                buildername = buildername.replaceAll("&", "§");
                String builderchat = plugin.getConfig().getString("chat_format.group_chat_color.builder");
                builderchat = builderchat.replaceAll("&", "§");
                String builderchatsign = plugin.getConfig().getString("chat_format.group_chatsign.builder");
                builderchatsign = builderchatsign.replaceAll("&", "§");
                e.setFormat(builderprefix + " " + buildername + "%s" + builderchatsign + " " + builderchat + "%s");
            }
            if (p.hasPermission("CheeseEss.chat.mod")) {
                String modprefix = plugin.getConfig().getString("chat_format.group_prefix.mod");
                modprefix = modprefix.replaceAll("&", "§");
                String modname = plugin.getConfig().getString("chat_format.group_name_color.mod");
                modname = modname.replaceAll("&", "§");
                String modchat = plugin.getConfig().getString("chat_format.group_chat_color.mod");
                modchat = modchat.replaceAll("&", "§");
                String modchatsign = plugin.getConfig().getString("chat_format.group_chatsign.mod");
                modchatsign = modchatsign.replaceAll("&", "§");
                e.setFormat(modprefix + " " + modname + "%s" + modchatsign + " " + modchat + "%s");
            }
            if (p.hasPermission("CheeseEss.chat.admin")) {
                String adminprefix = plugin.getConfig().getString("chat_format.group_prefix.admin");
                adminprefix = adminprefix.replaceAll("&", "§");
                String adminname = plugin.getConfig().getString("chat_format.group_name_color.admin");
                adminname = adminname.replaceAll("&", "§");
                String adminchat = plugin.getConfig().getString("chat_format.group_chat_color.admin");
                adminchat = adminchat.replaceAll("&", "§");
                String adminchatsign = plugin.getConfig().getString("chat_format.group_chatsign.admin");
                adminchatsign = adminchatsign.replaceAll("&", "§");
                e.setFormat(adminprefix + " " + adminname + "%s" + adminchatsign + " " + adminchat + "%s");
            }
            if (p.hasPermission("CheeseEss.chat.owner")) {
                String ownerprefix = plugin.getConfig().getString("chat_format.group_prefix.owner");
                ownerprefix = ownerprefix.replaceAll("&", "§");
                String ownername = plugin.getConfig().getString("chat_format.group_name_color.owner");
                ownername = ownername.replaceAll("&", "§");
                String ownerchat = plugin.getConfig().getString("chat_format.group_chat_color.owner");
                ownerchat = ownerchat.replaceAll("&", "§");
                String ownerchatsign = plugin.getConfig().getString("chat_format.group_chatsign.owner");
                ownerchatsign = ownerchatsign.replaceAll("&", "§");
                e.setFormat(ownerprefix + " " + ownername + "%s" + ownerchatsign + " " + ownerchat + "%s");
            }
        }else{
            e.setFormat("<" + "%s" + "> " + "%s");
        }

    }

    @EventHandler (priority = EventPriority.HIGHEST)
    public void colorcoding(AsyncPlayerChatEvent e){
        Player p = e.getPlayer();
        if(p.hasPermission("CheeseEss.chat.color")){
            String msg = e.getMessage().replaceAll("&", "§");
            e.setMessage(msg);
        }
    }
}
