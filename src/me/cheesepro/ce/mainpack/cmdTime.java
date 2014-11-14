package me.cheesepro.ce.mainpack;

import me.cheesepro.ce.extra.ConsoleSender;
import me.cheesepro.ce.extra.Messenger;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Mark on 09/11/2014.
 */
public class cmdTime implements CommandExecutor{

    private CEMain plugin;
    private final Messenger msg;

    public cmdTime(CEMain plugin){
        this.plugin = plugin;
        this.msg = new Messenger(plugin);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label,String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if(cmd.getLabel().equalsIgnoreCase("day")){
                World world = p.getWorld();
                world.setTime(0);
                msg.m(p, "e", "Time set to day");
            }else if(cmd.getLabel().equalsIgnoreCase("night")){
                World world = p.getWorld();
                world.setTime(18000);
                msg.m(p, "e", "Time set to night");
            }
        }else{
            ConsoleSender.noConsole();
        }
        return false;
    }

}
