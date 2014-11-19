package me.cheesepro.ce.mainpack;

import me.cheesepro.ce.extra.Messenger;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mark on 19/11/2014.
 */
public class cmdList implements CommandExecutor{

    private CEMain plugin;
    private final Messenger msg;

    public cmdList(CEMain plugin){
        this.plugin = plugin;
        this.msg = new Messenger(plugin);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (label.equalsIgnoreCase("list")) {
                List list = new ArrayList();
                for(Player all : Bukkit.getOnlinePlayers()){
                    list.add(all.getName());
                }
                String names = list.toString();
                int count = list.size();
                msg.m(p, "f", "There are " + count + " players online:");
                msg.t(p, "e", names);
            }
        }
        return false;
    }

}
