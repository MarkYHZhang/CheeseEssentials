package me.cheesepro.ce.listener;

import me.cheesepro.ce.extra.Messenger;
import me.cheesepro.ce.mainpack.CEMain;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;

/**
 * Created by Mark on 02/11/2014.
 */
public class warpSign implements Listener {

    private CEMain plugin;
    private final Messenger msg;

    String wname;

    public warpSign(CEMain plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        this.plugin = plugin;
        this.msg = new Messenger(plugin);
    }

    @EventHandler
    public void signChange(SignChangeEvent e) {
        Player p = e.getPlayer();
        String[] lines = e.getLines();
            if (lines[0].equalsIgnoreCase("[Warp]")) {
                if (!lines[1].isEmpty()) {
                    e.setLine(0, ChatColor.GREEN + "[Warp]");
                    msg.w(p, "5", "Successfully made a Warp sign.");
                } else {
                    msg.w(p, "4", "Please state a warp on the second line.");
                    e.setCancelled(true);
                }
            }
    }

    @EventHandler
    public void clickSign(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        if(e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            Block block = e.getClickedBlock();
            if (((block.getType() == Material.SIGN) || (block.getType() == Material.SIGN_POST) || (block.getType() == Material.WALL_SIGN)) &&
                    ((block.getState() instanceof Sign))) {
                Sign sign = (Sign) block.getState();
                if (ChatColor.stripColor(sign.getLine(0)).equalsIgnoreCase("[Warp]")) {
                        Bukkit.getServer().dispatchCommand(p, "warp " + sign.getLine(1));
                }
            }
        }


//    @EventHandler
//    public void signPlace(SignChangeEvent e) {
//        if(e.getLine(0).equalsIgnoreCase("[warp]")) {
//            e.setLine(0, ChatColor.GREEN.toString() + ChatColor.BOLD + "[Warp]");
//            wname = e.getLine(1);
//        }
//    }
//
//    @EventHandler
//    public void signInteract(PlayerInteractEvent e) {
//        Player p = e.getPlayer();
//        if(e.getAction() == Action.RIGHT_CLICK_BLOCK) {
//            Block block = e.getClickedBlock();
//            if(block.getType() == Material.SIGN || block.getType() == Material.SIGN_POST || block.getType() == Material.WALL_SIGN) {
//                Sign sign = (Sign) e.getClickedBlock().getState();
//                if(sign.getLine(0).equalsIgnoreCase("[warp]")) {
//                    Bukkit.getServer().dispatchCommand(p, "warp " + wname);
//                }
//            }
//        }
//    }


    }
}
