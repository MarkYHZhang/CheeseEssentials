package me.cheesepro.ce.mainpack;

import me.cheesepro.ce.extra.ConsoleSender;
import me.cheesepro.ce.extra.Messenger;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

/**
 * Created by Mark on 09/11/2014.
 */
public class cmdVanish implements CommandExecutor{

    private CEMain plugin;
    private final Messenger msg;

    public cmdVanish(CEMain plugin){
        this.plugin = plugin;
        this.msg = new Messenger(plugin);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if(cmd.getLabel().equalsIgnoreCase("vanish")){
                if(!p.hasPotionEffect(PotionEffectType.INVISIBILITY)) {
                    if (p.hasPermission("CheeseEss.vanish.complete")) {
                        for(Player all : Bukkit.getOnlinePlayers()){
                            all.hidePlayer(p);
                        }
                        p.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 100000, 100000));
                        msg.m(p, "e", "Vanished completely");
                    } else {
                        p.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 100000, 100000));
                        msg.m(p, "e", "Vanished");
                    }
                }else{
                    p.removePotionEffect(PotionEffectType.INVISIBILITY);
                }
            }
        }else{
            ConsoleSender.noConsole();
        }

        return false;
    }

}
