package me.cheesepro.ce.mainpack;

import me.cheesepro.ce.extra.ConsoleSender;
import me.cheesepro.ce.extra.Messenger;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

/**
 * Created by Mark on 19/11/2014.
 */
public class cmdHead implements CommandExecutor{

    private CEMain plugin;
    private final Messenger msg;

    public cmdHead(CEMain plugin){
        this.plugin = plugin;
        this.msg = new Messenger(plugin);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (cmd.getLabel().equalsIgnoreCase("head")) {
                if(args.length==1) {
                    ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (short) SkullType.PLAYER.ordinal());
                    SkullMeta meta = (SkullMeta) skull.getItemMeta();
                    meta.setOwner(args[0]);
                    meta.setDisplayName(ChatColor.GREEN + args[0] + "'s Head");
                    skull.setItemMeta(meta);
                    p.getInventory().addItem(skull);
                    msg.m(p, "a", "Got " + args[0] + "'s head");
                }else{
                    ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (short) SkullType.PLAYER.ordinal());
                    SkullMeta meta = (SkullMeta) skull.getItemMeta();
                    meta.setOwner(p.getName());
                    meta.setDisplayName(ChatColor.GREEN + p.getName() + "'s Head");
                    skull.setItemMeta(meta);
                    p.getInventory().addItem(skull);
                    msg.m(p, "a", "Got your head");
                }
            }
        }else{
            ConsoleSender.noConsole();
        }
        return false;
    }

}
