package me.cheesepro.ce.mainpack;

import me.cheesepro.ce.extra.Messenger;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Mark on 08/11/2014.
 */
public class cmdCchat implements CommandExecutor{

    private CEMain plugin;
    private final Messenger msg;

    public cmdCchat(CEMain plugin){
        this.plugin = plugin;
        this.msg = new Messenger(plugin);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (cmd.getLabel().equalsIgnoreCase("cchat")) {
                for(int i = 1; i<101; i++){
                    Bukkit.broadcastMessage("");
                }
                return false;
            }
        }
        return false;
    }

}
