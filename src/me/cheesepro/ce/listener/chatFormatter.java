package me.cheesepro.ce.listener;

import me.cheesepro.ce.extra.Messenger;
import me.cheesepro.ce.mainpack.CEMain;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.permissions.PermissionAttachmentInfo;

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

    @EventHandler(priority = EventPriority.HIGH)
    public void onChat(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();
        if(!(e.getFormat().equalsIgnoreCase("<" + "%s" + "> " + "%s"))) {
            for (PermissionAttachmentInfo perm : p.getEffectivePermissions()) {
                if (perm.getPermission().startsWith("cheeseess.chat.")) {
                    String rank = perm.getPermission().replaceAll("cheeseess.chat.", "");
                    String rankprefix = plugin.getConfig().getString("chat_format.group_prefix." + rank).replaceAll("&", "§");
                    String rankname = plugin.getConfig().getString("chat_format.group_name_color." + rank).replaceAll("&", "§");
                    String rankchat = plugin.getConfig().getString("chat_format.group_chat_color." + rank).replaceAll("&", "§");
                    String rankchatsign = plugin.getConfig().getString("chat_format.group_chatsign." + rank).replaceAll("&", "§");
                    e.setFormat(rankprefix + " " + rankname + "%s" + rankchatsign + " " + rankchat + "%s");
                }
            }
        }else{
            e.setFormat("<" + "%s" + "> " + "%s");
        }

    }

    @EventHandler (priority = EventPriority.HIGHEST)
    public void colorcoding(AsyncPlayerChatEvent e){
        Player p = e.getPlayer();
        if(p.hasPermission("cheeseess.chat.color")){
            String msg = e.getMessage().replaceAll("&", "§");
            e.setMessage(msg);
        }
    }
}
