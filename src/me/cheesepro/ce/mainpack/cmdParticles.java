package me.cheesepro.ce.mainpack;

import me.cheesepro.ce.extra.ConsoleSender;
import me.cheesepro.ce.extra.Messenger;
import me.cheesepro.ce.file.playerdata;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Mark on 15/10/2014.
 */
public class cmdParticles implements CommandExecutor{

    private CEMain plugin;
    private final Messenger msg;
    playerdata settings = playerdata.getInstance();

    public cmdParticles(CEMain plugin){
        this.plugin = plugin;
        this.msg = new Messenger(plugin);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(sender instanceof Player){
            Player p = (Player) sender;
            if(label.equalsIgnoreCase("particles")){
                if(args.length==1){
                    String option = args[0];
                    while(true){
                        if(option.equalsIgnoreCase("on")){
                            playEffect(p.getLocation().subtract(0, 1, 0), Effect.SMOKE, 5);
                        }else{
                            break;
                        }
                    }
                }else{

                }
            }
        }else{
            ConsoleSender.noConsole();
        }
        return false;
    }

    public void playEffect (Location loc, Effect effect, int num) {

        World world = loc.getWorld();
        world.playEffect(loc, effect, num);
    }
}
