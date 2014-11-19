package me.cheesepro.ce.mainpack;

import me.cheesepro.ce.extra.ConsoleSender;
import me.cheesepro.ce.extra.Messenger;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.lang.reflect.Array;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * Created by Mark on 15/11/2014.
 */
public class cmdID implements CommandExecutor{

    private CEMain plugin;
    private final Messenger msg;

    public cmdID(CEMain plugin){
        this.plugin = plugin;
        this.msg = new Messenger(plugin);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (label.equalsIgnoreCase("id")) {
                ItemStack item = new ItemStack(Material.PAPER, 1);
                ItemMeta im = item.getItemMeta();
                OfflinePlayer offp = Bukkit.getOfflinePlayer(p.getUniqueId());
//                long tTickPlayed = offp.getFirstPlayed();
//                long tSecondPlayed1 = tTickPlayed / 20L;
//                long tMinPlayed1 = tSecondPlayed1 / 60L;
//                long tHPlayed1 = tMinPlayed1 / 60L;
//                long tMinPlayed2 = tMinPlayed1 - tHPlayed1 * 60L;
//                long tSecondPlayed2 = tSecondPlayed1 - tMinPlayed1 * 60L;
                String time = getCurrentDTG(offp.getLastPlayed());
                im.setDisplayName(ChatColor.WHITE.toString() + ChatColor.BOLD + "==========[" + ChatColor.LIGHT_PURPLE.toString() + ChatColor.BOLD + "ID" + ChatColor.WHITE.toString() + ChatColor.BOLD + "]==========");
                ArrayList<String> lore = new ArrayList<String>();
                lore.add(ChatColor.GREEN.toString() + ChatColor.BOLD + "Name: " + p.getName());
                lore.add(ChatColor.YELLOW.toString() + ChatColor.BOLD + "Joined: " + time);
                lore.add(ChatColor.WHITE.toString() + ChatColor.BOLD + "========================");
                im.setLore(lore);
                item.setItemMeta(im);
                p.getInventory().addItem(item);
                msg.m(p, "a", "ID card get!");
            }
        } else {
            ConsoleSender.noConsole();
        }
        return false;
    }

    public static String getCurrentDTG(long l_time)
    {
        Date date = new Date(l_time);
        SimpleDateFormat dtgFormat = new SimpleDateFormat("[hh:mm MM/dd/yyyy]");
        return dtgFormat.format(date);
    }
}
