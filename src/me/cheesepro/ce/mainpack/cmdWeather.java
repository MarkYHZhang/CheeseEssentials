package me.cheesepro.ce.mainpack;

import me.cheesepro.ce.extra.ConsoleSender;
import me.cheesepro.ce.extra.Messenger;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Mark on 15/11/2014.
 */
public class cmdWeather implements CommandExecutor{

    private CEMain plugin;
    private final Messenger msg;

    public cmdWeather(CEMain plugin){
        this.plugin = plugin;
        this.msg = new Messenger(plugin);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            World w = p.getWorld();
            if(cmd.getLabel().equalsIgnoreCase("weather")){
                if(args.length==1) {
                    if (args[0].equalsIgnoreCase("sun")) {
                        w.setStorm(false);
                        w.setThundering(false);
                        w.setThunderDuration(0);
                        msg.m(p, "a", "Set weather to sun");
                    } else if (args[0].equalsIgnoreCase("storm")) {
                        w.setStorm(true);
                        msg.m(p, "a", "Set weather to storm");
                    } else if (args[0].equalsIgnoreCase("rain")) {
                        w.setThundering(true);
                        msg.m(p, "a", "Set weather to rain");
                    } else {
                        msg.t(p, "e", "/weather [sun/storm/rain]");
                    }
                }else{
                    msg.t(p, "e", "/weather [sun/storm/rain]");
                }
            }
        }else{
            ConsoleSender.noConsole();
        }
        return false;
    }

}
