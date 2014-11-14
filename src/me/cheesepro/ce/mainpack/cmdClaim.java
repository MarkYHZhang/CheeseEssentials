package me.cheesepro.ce.mainpack;

import me.cheesepro.ce.extra.ConsoleSender;
import me.cheesepro.ce.extra.Messenger;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.Console;

/**
 * Created by Mark on 10/11/2014.
 */
public class cmdClaim implements CommandExecutor{

    private CEMain plugin;
    private final Messenger msg;

    public cmdClaim(CEMain plugin){
        this.plugin = plugin;
        this.msg = new Messenger(plugin);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if(cmd.getLabel().equalsIgnoreCase("claim")){
                ItemStack item = new ItemStack(Material.GOLD_SPADE, 1);
                ItemMeta im = item.getItemMeta();
                im.setDisplayName(ChatColor.YELLOW.toString() + ChatColor.BOLD + "Claiming shovel");
                item.setItemMeta(im);
                p.getInventory().addItem(item);
                msg.m(p, "a", "The objective of claiming areas is to prevent your build from griefers.");
                msg.t(p, "5", "To start claiming all you have to do is to right click two points(blocks) using the Gold shovel that we provide you [At least 10 x 10]");
            }
        }else{
            ConsoleSender.noConsole();
        }
        return false;
    }

}
