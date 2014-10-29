package me.cheesepro.ce.mainpack;

import me.cheesepro.ce.extra.ConsoleSender;
import me.cheesepro.ce.extra.Messenger;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
/**
 * Created by Mark on 18/10/2014.
 */
public class cmdHat implements CommandExecutor{

    private CEMain plugin;
    private final Messenger msg;
    ItemStack olditem;

    public cmdHat(CEMain plugin){
        this.plugin = plugin;
        this.msg = new Messenger(plugin);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if(label.equalsIgnoreCase("hat")){
                if (!(p.getItemInHand().getType() == Material.AIR)) {
                    ItemStack air = new ItemStack(Material.AIR);
                    ItemStack item = new ItemStack(p.getItemInHand());
                    if(p.getInventory().getHelmet()!=null) {
                        olditem = new ItemStack(p.getInventory().getHelmet());
                    }
                    p.getInventory().setHelmet(item);
                    p.getInventory().setItemInHand(olditem);
                    p.getInventory().remove(item);
                    msg.m(p, "a", "Enjoy your new hat");
                }else if(p.getItemInHand().getType() == Material.AIR && p.getInventory().getHelmet()!=null) {
                    ItemStack air = new ItemStack(Material.AIR);
                    ItemStack item = new ItemStack(p.getInventory().getHelmet());
                    p.getInventory().setHelmet(air);
                    p.getInventory().addItem(item);
                    msg.m(p, "e", "Hat removed");
                }else {
                    msg.w(p, "5", "No item in hand");
                }
            }
        }else{
            ConsoleSender.noConsole();
        }
        return false;
    }
}
